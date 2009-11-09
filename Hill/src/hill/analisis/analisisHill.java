/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hill.analisis;

import Jama.Matrix;
import hill.CS.CSHill;
import hill.excepciones.TextoException;
import hill.utils.PermutationGenerator;
import hill.utils.utilsHill;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author fakefla-kubuntu
 */
public class analisisHill {

    private HashSet<String> digsCompletos;

    public analisisHill() {
        inicializarDigramas();
    }

    public ArrayList analizar(String txtCifrado) throws Exception {
        ArrayList result = new ArrayList();
        //se usa el método estático para inicializar la tabla indexada para su posterior uso
        CSHill.inicializarDeterminantes();
        ArrayList<Matrix> posiblesColumnas = analisisColumnas(txtCifrado);
        PermutationGenerator permGenerator = new PermutationGenerator(posiblesColumnas.size());
        int[] aux = new int[posiblesColumnas.size()];
        Matrix posibleClave = new Matrix(3, 3);
        String auxTxt = "";
        //Arreglo que almacena las permutaciones que se van aplicando para evitar repeticiones
        HashSet<String> posiblesPerm = new HashSet<String>();
        while (permGenerator.hasMore()) {
            aux = truncateArray(permGenerator.getNext(), 3);
            if (!posiblesPerm.contains(utilsHill.arrayToString(aux))) {
                posiblesPerm.add(utilsHill.arrayToString(aux));
                posibleClave = setPermutation(aux, posiblesColumnas);
                try {
                    auxTxt = CSHill.descifrarInv(txtCifrado, posibleClave);
                    if (validByBigram(auxTxt)) {
                        result.add(auxTxt);
                        result.add(utilsHill.getInversa(posibleClave));
                    }
                } catch (Exception ex) {
                }
            }
        }

        return result;
    }

    /**
     * Inicia la variable de clase digramas con los valores que se encuentran
     * en el archivo DigramsFreqs.properties, dichos digramas están ordenados
     * de mayor a menor ocurrencia
     */
    public void inicializarDigramas() {
        digsCompletos = new HashSet<String>();
        //DEFINICIÓN DE LOS DIGRAMAS POSIBLES DEL ESPAÑOL
        String digrEsp = "endeeresuelao a s e n r  c p a s m e d traosnttearqueltadocored l i asonantolostunoradiesecial h v r u n i l b onaparonomeinz  f q g j zeaebecedeeefegeheiejekelemeñeoepeqeteuevewexeyezabacaeafagahaiajakamañaoapaqatauavawaxayazoaobocodoeofogohoiojolomoñopoqotouovoxoyozsasbscsdsfsgsislsmsnsospsqsusvsyncndnenfngnhninjnlnmnqnrnsnunviaibicidifigihijilimiñioipiqirisitiuivixiyizrbrcrdrgrirjrmrnrprrrsrtrurvrzlblcldlelflgliljlllmlplqlrlsltlulzdadidgdhdjdmdndydrduu uaubucudufuguhuiujulumuñuoupuqurusutuvuycacccechclcrctcutctitltmtntrtum mambmimhmnmompmup pcpepiplpoprptpubabebiblbobrbsbuhahehihohug gagegigoglgngrguvavevivovuy yayeyiyoyujajejijojufafefiflfofrfuzazeziztzmznzozuñañeñiñoñux xaxcxexhxixpxtxoxu ñ w x y";
        for (int i = 0; i < digrEsp.length(); i += 2) {
            digsCompletos.add(digrEsp.substring(i, i + 2));
        }
    }

    public ArrayList<Matrix> analisisColumnas(String txtCifrado) throws TextoException {
        txtCifrado = txtCifrado.substring(0, txtCifrado.length() - 6);
        Matrix auxVector = new Matrix(3, 1);
        ArrayList<Matrix> posibleCol = new ArrayList<Matrix>();
        for (int i = 0; i < utilsHill.NUM_2CHARS; i++) {
            long starti = System.currentTimeMillis();
            for (int j = 0; j < utilsHill.NUM_2CHARS; j++) {
                for (int k = 0; k < utilsHill.NUM_2CHARS; k++) {
                    auxVector.set(0, 0, i);
                    auxVector.set(1, 0, j);
                    auxVector.set(2, 0, k);
                    if (descifrarParcial(txtCifrado, auxVector)) {
                        posibleCol.add(new Matrix(auxVector.getArrayCopy()));
                        utilsHill.printMatrix(auxVector);
                    }
                }
            }
            System.out.println("i=" + i);
            long finish = System.currentTimeMillis();
            System.out.println("Elapsed time in milis = " + (finish - starti));
        }
        for (Matrix matr : posibleCol) {
            utilsHill.printMatrix(matr);
            System.out.println("\n");
        }
        return posibleCol;
    }

    public boolean descifrarParcial(String txtCifrado, Matrix vector) throws TextoException {
        Matrix result = new Matrix(1, 1);
        String aux = "";
        Matrix vectorPalabra = new Matrix(1, 3);
        for (int i = 0; i < txtCifrado.length(); i += 6) {
            aux = txtCifrado.substring(i, i + 6);
            vectorPalabra = CSHill.getVectorInt(aux);
            result = vectorPalabra.times(vector);
            result.set(0, 0, result.get(0, 0) % utilsHill.NUM_2CHARS);
//            System.out.println(utilsHill.numToDigrama((int) result.get(0, 0)));
            if (!digsCompletos.contains(utilsHill.numToDigrama((int) result.get(0, 0)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Trunca el arreglo para poder usarlo con las matrices
     * @param array
     * @param numTrunc
     * @return
     */
    public int[] truncateArray(int[] array, int numTrunc) {
        int[] truncado = new int[numTrunc];
        for (int i = 0; i < numTrunc; i++) {
            truncado[i] = array[i];
        }
        return truncado;
    }

    /**
     * A partir del arreglo de la permutación genera una matriz que es posible
     * clave
     * @param perm
     * @param posiblesColumnas
     * @return
     */
    public Matrix setPermutation(int[] perm, ArrayList<Matrix> posiblesColumnas) {

        Matrix auxMat = new Matrix(3, 3);
        for (int i = 0; i < perm.length; i++) {
            auxMat.set(0, i, posiblesColumnas.get(perm[i]).get(0, 0));
            auxMat.set(1, i, posiblesColumnas.get(perm[i]).get(1, 0));
            auxMat.set(2, i, posiblesColumnas.get(perm[i]).get(2, 0));
        }
        return auxMat;
    }

    private boolean validByBigram(String cadena) {
        cadena = cadena.substring(0, cadena.length() - 6);
        String aux = "";
        for (int i = 0; i < cadena.length() - 2; i++) {
            aux = cadena.substring(i, i + 2);
            if (!digsCompletos.contains(aux)) {
                return false;
            }
        }
        return true;
    }
}
