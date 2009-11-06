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
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author fakefla-kubuntu
 */
public class analisisHill {

    private LinkedList<String> digramas;
    private ArrayList<String> digramasCompletos;
    private String letrasComienzoInvalido = "xyzkw";
    private String repetidasValidas = "ceolr";
    private int longitudMaxima = 14;
    private String[] palabrasClave = {"ante", "bajo", "cabe", "con", "contra", "de", "desde", "durante",
        "en", "entre", "hacia", "hasta", "mediante", "para", "por", "segun", "sin", "sobre", "tras", "via", "el", "los",
        "la", "las", "lo"};
    private String letrasCriticasDespues = "qzhgyjxpftmvdr";
    private String[] xValida = {"u", "aeitmnou ", "aeiou", "aeiolru", "aeiou", "aeiou", "acehitou",
        "aceilortu", "aeiloru", "aeilmnoru", "abeihnopu", "aeiou", "aeighjmnyoru ", "abcdegijmnoprstuvz "};
    private String letrasCriticasAntes = " yxgzhlpftrjdc";
    private String[] validaX = {"adeilnorsuz", "aedou ", "aeiou ", "adeioulnrs ", "aireuln ", "aeiocudmnx ",
        "abcefglnrsioptu ", "aeimorsu ", "aeionlrsu ", "aceilnfoprsuxz ", "abcdefgilnoprstu ", "abdeilnoru ",
        "abeilnorsu ", "aceilnoprsux "};
    private HashSet<String> digsCompletos;

    public analisisHill() {
        inicializarDigramas();
    }

    public LinkedList<String> getDigramas() {
        return digramas;
    }

    public void setDigramas(LinkedList<String> digramas) {
        this.digramas = digramas;
    }

    public ArrayList<String> getDigramasCompletos() {
        return digramasCompletos;
    }

    public void setDigramasCompletos(ArrayList<String> digramasCompletos) {
        this.digramasCompletos = digramasCompletos;
    }

    public ArrayList analizar(String txtCifrado) throws Exception {
        ArrayList result = new ArrayList();
        txtCifrado = utilsHill.adjustString(txtCifrado, 6);
        ArrayList<Matrix> posiblesColumnas = analisisColumnas(txtCifrado);
        if(posiblesColumnas.size()<3){
            throw new Exception("El texto cifrado parece estar mal formado");
        }
        PermutationGenerator permGenerator = new PermutationGenerator(posiblesColumnas.size());
        int[] aux = new int[posiblesColumnas.size()];
        Matrix posibleClave = new Matrix(3, 3);
        String auxTxt;
        while (permGenerator.hasMore()) {
            aux = truncateArray(permGenerator.getNext(), 3);
            posibleClave = setPermutation(aux, posiblesColumnas);
            try {
                auxTxt = CSHill.descifrarInv(txtCifrado, posibleClave);
                if (cadenaValida(auxTxt)) {
                    result.add(auxTxt);
                    result.add(utilsHill.getInversa(posibleClave));
                }
            } catch (Exception ex) {
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
        digramas = new LinkedList<String>();
        digramasCompletos = new ArrayList<String>();
        digsCompletos = new HashSet<String>();
        String digrAux = java.util.ResourceBundle.getBundle("hill.resources.DigramsFreqs").getString("digramas").toLowerCase();
        for (int i = 0; i < digrAux.length(); i += 2) {
            digramas.add(digrAux.substring(i, i + 2));
        }
        digrAux = java.util.ResourceBundle.getBundle("hill.resources.DigramsFreqs").getString("digramasEspCompletos").toLowerCase();
        for (int i = 0; i < digrAux.length(); i += 2) {
            digramasCompletos.add(digrAux.substring(i, i + 2));
            digsCompletos.add(digrAux.substring(i, i + 2));
        }
    }

    private boolean cadenaValida(String cadena) {
        String[] divisionesCadena = cadena.split(" ");
        if (!contienePalabras(divisionesCadena)) {
            return false;
        }
        return true;
    }

    public boolean contienePalabras(String[] divisionesCadena) {
        LinkedList<String> auxDivisiones = new LinkedList<String>();
        LinkedList<String> auxPalabras = new LinkedList<String>();
        for (int i = 0; i < divisionesCadena.length; i++) {
            String cadena = divisionesCadena[i];
            auxDivisiones.add(cadena);
        }
        for (int i = 0; i < palabrasClave.length; i++) {
            String cadena = palabrasClave[i];
            auxPalabras.add(cadena);
        }
        for (int i = 0; i < auxPalabras.size(); i++) {
            if (auxDivisiones.contains(auxPalabras.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean combinacionesInvalidas(String cadena) {
        int posicionLetraCritica;
        //se verifica en la lista de letrasCriticasDespues
        for (int i = 0; i < letrasCriticasDespues.length(); i++) {
            //si la cadena contiene una letra crítica
            if (cadena.contains(letrasCriticasDespues.substring(i, i + 1))) {
                posicionLetraCritica = cadena.indexOf(letrasCriticasDespues.substring(i, i + 1));
                //si la letra crítica no está al final

                if (posicionLetraCritica != cadena.length() - 1) {
                    //verifica si la letra siguiente a la crítica es valida (busca en el arreglo xValida)
                    if (!xValida[i].contains(cadena.substring(posicionLetraCritica + 1, posicionLetraCritica + 2))) {

                        return true;
                    }
                }
            }
        }

        //se verifica en la lista de letrasCriticasAntes
        for (int i = 0; i < letrasCriticasAntes.length(); i++) {
            //si la cadena contiene una letra crítica
            if (cadena.contains(letrasCriticasAntes.substring(i, i + 1))) {
                posicionLetraCritica = cadena.indexOf(letrasCriticasAntes.substring(i, i + 1));
                if (posicionLetraCritica > 0) {
                    //verifica si la letra anterior a la crítica es valida (busca en el arreglo validaX)
                    if (!validaX[i].contains(cadena.substring(posicionLetraCritica - 1, posicionLetraCritica))) {

                        return true;
                    }

                }
            }
        }
        return false;
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

    public int[] truncateArray(int[] array, int numTrunc) {
        int[] truncado = new int[numTrunc];
        for (int i = 0; i < numTrunc; i++) {
            truncado[i] = array[i];
        }
        return truncado;
    }

    public Matrix setPermutation(int[] perm, ArrayList<Matrix> posiblesColumnas) {

        Matrix auxMat = new Matrix(3, 3);
        for (int i = 0; i < perm.length; i++) {
            auxMat.set(0, i, posiblesColumnas.get(perm[i]).get(0, 0));
            auxMat.set(1, i, posiblesColumnas.get(perm[i]).get(1, 0));
            auxMat.set(2, i, posiblesColumnas.get(perm[i]).get(2, 0));
        }
        return auxMat;
    }
}
