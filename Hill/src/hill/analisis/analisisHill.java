/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hill.analisis;

import Jama.Matrix;
import hill.CS.CSHill;
import hill.excepciones.TextoException;
import hill.utils.utilsHill;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import org.omg.CORBA.DATA_CONVERSION;

/**
 *
 * @author fakefla-kubuntu
 */
public class analisisHill {

    private CSHill cryptSyst;
    private LinkedList<String> digramas;
    private ArrayList<String> digramasCompletos;
    private static boolean fin;
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
        cryptSyst = new CSHill();
        inicializarDigramas();
        fin = false;
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

    public ArrayList analizar(String txtCifrado, ArrayList<String> camposMatrix) throws Exception {
        ArrayList<Matrix> posiblesColumnas = analisisColumnas(txtCifrado);
        int combinPosibles = 1;

        //Calcula todas las combinaciones que se pueden hacer con el número de
        //columnas hallado en el paso anterior. Realiza una Multiplicatoria
        for (int i = 1; i <= posiblesColumnas.size(); i++) {
            combinPosibles *= i;
        }

        for (int i = 0; i < combinPosibles; i++) {
        }
        return null;
//                analisisColumnas(txtCifrado);
//        return analisisSupuesto(txtCifrado, camposMatrix);
//        return analisisAleatorio(txtCifrado);
    }

    public String analisisSupuesto(String txtCifrado, ArrayList<String> camposMatrix) throws Exception {
        int count = 0;
        Matrix matrizB = new Matrix(3, 3);
        Matrix matrizA = new Matrix(3, 3);
        Matrix matrizX = new Matrix(3, 3);
        Matrix matrizAInv = new Matrix(3, 3);
        String descifrado = "";
        Matrix vector = new Matrix(1, 3);
        ArrayList<Integer> indicesFil = new ArrayList<Integer>();
        ArrayList<Integer> indicesCol = new ArrayList<Integer>();

        //inicializa la matrizB con los caracteres de entrada de camposMatrix
        for (int i = 0; i < 3; i++) {
            String aux = camposMatrix.get(i * 3).concat(camposMatrix.get(i * 3 + 1)).concat(camposMatrix.get(i * 3 + 2));
            vector = CSHill.getVectorInt(aux);
            for (int j = 0; j < 3; j++) {
                matrizB.set(i, j, vector.get(0, j));
                if (aux.substring((j * 2), (j * 2) + 2).equals("__")) {
                    count++;
                    //se agrega a los arreglos el valor en el cual falta una
                    //entrada para saber en que posiciones se debe probar
                    indicesFil.add(i);
                    indicesCol.add(j);
                }
            }
        }
        int numDigs = utilsHill.NUM_SALTO;
        ArrayList<String> auxDigsComp = new ArrayList<String>();
        //ciclo para recorrer los digramas del español
        for (int i = 0; i < digramasCompletos.size(); i += utilsHill.NUM_DIGS) {
            //se recorren en bloques de tamaño utilsHill.NUM_DIGS
            for (int j = 0; j < utilsHill.NUM_DIGS && i + j < digramasCompletos.size(); j++) {
                auxDigsComp.add(digramasCompletos.get(i + j));
            }
        }

        //se pretende solucionar el sistema de matrices A*X=B haciendo uso de la
        //matriz inversa se tiene que X=Inv(A)*B
        while (!fin) {
            auxDigsComp.clear();
            for (int i = 0; i < numDigs; i++) {
                auxDigsComp.add(digramasCompletos.get(i));
            }
            System.out.println("analizando: " + numDigs + " digramas");
            long start = System.currentTimeMillis();
            //los 9 for generan la matrizA
            for (int i = 0; i < auxDigsComp.size() && !fin; i++) {
                for (int j = 0; j < auxDigsComp.size() && !fin; j++) {
                    for (int k = 0; k < auxDigsComp.size() && !fin; k++) {
                        for (int l = 0; l < auxDigsComp.size() && !fin; l++) {
                            for (int m = 0; m < auxDigsComp.size() && !fin; m++) {
                                for (int n = 0; n < auxDigsComp.size() && !fin; n++) {
                                    for (int o = 0; o < auxDigsComp.size() && !fin; o++) {
                                        for (int p = 0; p < auxDigsComp.size() && !fin; p++) {
                                            for (int q = 0; q < auxDigsComp.size() && !fin; q++) {
                                                if (i >= numDigs - 3 || j >= numDigs - 3 || k >= numDigs - 3 || l >= numDigs - 3 || m >= numDigs - 3 || n >= numDigs - 3 || o >= numDigs - 3 || p >= numDigs - 3 || q >= numDigs - 3) {
                                                    matrizA.set(0, 0, utilsHill.digramaToNum(auxDigsComp.get(i)));
                                                    matrizA.set(0, 1, utilsHill.digramaToNum(auxDigsComp.get(j)));
                                                    matrizA.set(0, 2, utilsHill.digramaToNum(auxDigsComp.get(k)));
                                                    matrizA.set(1, 0, utilsHill.digramaToNum(auxDigsComp.get(l)));
                                                    matrizA.set(1, 1, utilsHill.digramaToNum(auxDigsComp.get(m)));
                                                    matrizA.set(1, 2, utilsHill.digramaToNum(auxDigsComp.get(n)));
                                                    matrizA.set(2, 0, utilsHill.digramaToNum(auxDigsComp.get(o)));
                                                    matrizA.set(2, 1, utilsHill.digramaToNum(auxDigsComp.get(p)));
                                                    matrizA.set(2, 2, utilsHill.digramaToNum(auxDigsComp.get(q)));
                                                    if (utilsHill.inversible(matrizA)) {
                                                        matrizAInv = utilsHill.getInversa(matrizA);
                                                        //si matriz de datos ingresada contiene todos los datos
                                                        if (count == 0) {
                                                            matrizX = matrizAInv.times(matrizB);
                                                            matrizX = utilsHill.moduloMatriz(matrizX);
                                                            if (utilsHill.inversible(matrizX)) {
                                                                try {
                                                                    descifrado = CSHill.descifrar(txtCifrado.substring(0, 30), matrizX);
                                                                } catch (DATA_CONVERSION ex) {
                                                                }
                                                                if (!descifrado.equals("")) {
                                                                    utilsHill.printMatrix(matrizX);
                                                                    if (inicialmenteValida(descifrado)) {
                                                                        descifrado = CSHill.descifrar(txtCifrado, matrizX);
                                                                        if (totalmenteValida(descifrado)) {
                                                                            fin = true;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            for (int r = 0; r < indicesFil.size(); r++) {
                                                                switch (indicesFil.size()) {
                                                                    case 1: {
                                                                        for (int s = 0; s < utilsHill.NUM_2CHARS; s++) {
                                                                            matrizB.set(indicesFil.get(r), indicesCol.get(r), s);
                                                                        }
                                                                    }
                                                                    case 2: {
                                                                        for (int s = 0; s < utilsHill.NUM_2CHARS; s++) {
                                                                            matrizB.set(indicesFil.get(r), indicesCol.get(r), s);

                                                                        }
                                                                    }
                                                                    case 3: {
                                                                        for (int s = 0; s < utilsHill.NUM_2CHARS; s++) {
                                                                            matrizB.set(indicesFil.get(r), indicesCol.get(r), s);

                                                                        }
                                                                    }
                                                                    default: {
                                                                        throw new Exception("No se puede realizar el análisis, ingrese más datos");
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            numDigs += utilsHill.NUM_SALTO;
            long finish = System.currentTimeMillis();
            System.out.println("Elapsed time in milis = " + (finish - start));
        }
        return descifrado;
    }

    /**
     * Determina cuales son las columnas (0,1,2) que tienen un índice asociado
     * para mantenerlas fijas en un posterior análisis
     * @param indices
     * @return
     */
    public LinkedList<Integer> columnasInvariantes(LinkedList<Integer> indices) {
        LinkedList<Integer> auxList = new LinkedList<Integer>();
        for (Integer auxNum : indices) {
            auxList.add((auxNum % 6) / 2);
        }
        auxList = eliminarRepetidos(auxList);
        Collections.sort(auxList);
        return auxList;
    }

    /**
     * Elimina los elementos repetidos en una lista de enteros
     * @param indices
     * @return
     */
    public LinkedList<Integer> eliminarRepetidos(LinkedList<Integer> indices) {
        LinkedList<Integer> auxList = new LinkedList<Integer>();
        while (indices.size() != 0) {
            if (!auxList.contains(indices.getFirst())) {
                auxList.add(indices.getFirst());
            }
            indices.remove(indices.getFirst());
        }
        return auxList;
    }

    public void analisisSimple(Matrix matriz) throws TextoException {
        Integer auxNum;
        Matrix b = CSHill.getVectorInt("mbiano");
        ArrayList<ArrayList<Integer>> posiblesVariables = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 9; i++) {
            posiblesVariables.add(new ArrayList<Integer>());
        }
        System.out.println("INICIO");
        long start = System.currentTimeMillis();
        long finish = System.currentTimeMillis();
        System.out.println("FIN");
        System.out.println("Elapsed time in milis = " + (finish - start));
        for (Integer num : posiblesVariables.get(2)) {
            System.out.println(num);
        }
    }

    public void analisisMixto(LinkedList<String> digramas, LinkedList<Integer> indices, LinkedList<Integer> colsInvar) {
        throw new UnsupportedOperationException("Not yet implemented");
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

    /**
     * Genera una lista de Listas que almacena en cada una de sus 3 posiciones
     * los respectivos digramas que van en sus respectivas columnas (0,1,2)
     * @param digramasRepetidos
     * @param indices
     * @return
     */
    public LinkedList<LinkedList> generateListas(LinkedList<String> digramasRepetidos, LinkedList<Integer> indices) {
        LinkedList<LinkedList> auxLists = new LinkedList<LinkedList>();
        for (int i = 0; i < 3; i++) {
            auxLists.add(new LinkedList<String>());
        }
        for (int i = 0; i < indices.size(); i++) {
            auxLists.get((indices.get(i) % 6) / 2).add(digramasRepetidos.get(i));
            auxLists.get((indices.get(i) % 6) / 2).add(indices.get(i));
        }
        return auxLists;
    }

    private String analisisAleatorio(String txtCifrado) throws Exception {

        Matrix claveSupuesta = new Matrix(3, 3);
        String descifrado = "";
        while (!fin) {
            descifrado = "";
            claveSupuesta.set(0, 0, Math.random() % 784);
            claveSupuesta.set(0, 1, Math.random() % 784);
            claveSupuesta.set(0, 2, Math.random() % 784);
            claveSupuesta.set(1, 0, Math.random() % 784);
            claveSupuesta.set(1, 1, Math.random() % 784);
            claveSupuesta.set(1, 2, Math.random() % 784);
            claveSupuesta.set(2, 0, Math.random() % 784);
            claveSupuesta.set(2, 1, Math.random() % 784);
            claveSupuesta.set(2, 2, Math.random() % 784);
            try {
                descifrado = CSHill.descifrar(txtCifrado.substring(0, 30), claveSupuesta);
            } catch (DATA_CONVERSION ex) {
            }
            if (!descifrado.equals("")) {
                if (inicialmenteValida(descifrado)) {
                    descifrado = CSHill.descifrar(txtCifrado, claveSupuesta);
                    if (totalmenteValida(descifrado)) {
                        fin = true;
                    }
                }
            }
        }
        return descifrado;
    }

    private boolean inicialmenteValida(String subCadena) {
        if (comienzoInvalido(subCadena)) {
            return false;
        }
        if (longitudInvalida(subCadena)) {
            return false;
        }
        if (repetidasMalas(subCadena)) {
            return false;
        }
        if (combinacionesInvalidas(subCadena)) {
            return false;
        }
        return true;
    }

    /**
     * Verifica que el comienzo de la variable cadena tenga un comienzo coherente
     * @param cadena
     * @return
     */
    public boolean comienzoInvalido(String cadena) {
        if (cadena.charAt(0) == ' ' || cadena.charAt(1) == ' ') {
            return true;
        }
        if (letrasComienzoInvalido.contains(cadena.substring(0, 1))) {
            return true;
        }
        return false;
    }

    /**
     * verifica si la variable cadena tiene cadena de longitud inválida
     * @param cadena
     * @return
     */
    public boolean longitudInvalida(String cadena) {
        String[] auxCadena = cadena.split(" ");
        for (int i = 0; i < auxCadena.length; i++) {
            if (auxCadena[i].length() > longitudMaxima) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si existen repeticiones inválidas en la variable cadena
     * @param cadena
     * @return
     */
    public boolean repetidasMalas(String cadena) {
        for (int i = 0; i < cadena.length() - 1; i++) {
            if (cadena.charAt(i) == cadena.charAt(i + 1)) {
                if (!repetidasValidas.contains(cadena.substring(i, i + 1))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean totalmenteValida(String cadena) {
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

    public String getFirstAnalysis(String txtCifrado) {
        LinkedList digsRepetidos = utilsHill.digramasRepetidos(txtCifrado);
        String output = "Digrama\tPosición\tColumna\n";
        for (int i = 0; i < digsRepetidos.size(); i += 3) {
            output = output.concat((String) digsRepetidos.get(i) + "\t" + (Integer) digsRepetidos.get(i + 1) + "\t" + (Integer) digsRepetidos.get(i + 2) + "\n");
        }
        return output;
    }

    public ArrayList<Matrix> analisisColumnas(String txtCifrado) throws TextoException {
        txtCifrado = txtCifrado.substring(0, txtCifrado.length() - 6);
        Matrix auxVector = new Matrix(3, 1);
        ArrayList<Matrix> posibleCol = new ArrayList<Matrix>();
//        for (int i = 0; i < utilsHill.NUM_2CHARS; i++) {
        for (int i = 200; i < 280; i++) {
//            long starti = System.currentTimeMillis();
            for (int j = 0; j < utilsHill.NUM_2CHARS; j++) {
//                long startj = System.currentTimeMillis();
                for (int k = 0; k < utilsHill.NUM_2CHARS; k++) {
                    auxVector.set(0, 0, i);
                    auxVector.set(1, 0, j);
                    auxVector.set(2, 0, k);
                    if (descifrarParcial(txtCifrado, auxVector)) {
                        posibleCol.add(new Matrix(auxVector.getArrayCopy()));
                        utilsHill.printMatrix(auxVector);
                    }
                }
//                System.out.println("j=" + j);
//                long finishj = System.currentTimeMillis();
//                System.out.println("Elapsed time in milis = " + (finishj - startj));
            }
//            System.out.println("i=" + i);
//            long finish = System.currentTimeMillis();
//            System.out.println("Elapsed time in milis = " + (finish - starti));
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
}
