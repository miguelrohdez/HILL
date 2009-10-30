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
import java.util.LinkedList;
import org.omg.CORBA.DATA_CONVERSION;

/**
 *
 * @author fakefla-kubuntu
 */
public class analisisHill {

    private CSHill cryptSyst;
    private LinkedList<String> digramas;
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

    public analisisHill() {
        cryptSyst = new CSHill();
        inicializarDigramas();
        fin = false;
    }

    public String analizar(String txtCifrado, ArrayList<String> camposMatrix) throws Exception {
//        analisisSupuesto(txtCifrado, col1, col2, col3);
        return analisisAleatorio(txtCifrado);
    }

    public void analisisSupuesto(String txtCifrado, ArrayList<String> camposMatrix) throws Exception {
        int count = 0;
        Matrix matriz = new Matrix(3, 3);
        Matrix vector = new Matrix(1, 3);
        for (int i = 0; i < palabrasClave.length; i++) {
            String aux = camposMatrix.get(i * 3).concat(camposMatrix.get(i * 3 + 1)).concat(camposMatrix.get(i * 3 + 2));
            vector = CSHill.getVectorInt(aux);
            for (int j = 0; j < palabrasClave.length; j++) {
                matriz.set(i, j,vector.get(0, j));
                if(!aux.substring((j*2), (j*2)+2).equals("__")){
                    count ++;
                }
            }
        }

        if (count > 1) {

            if (count == 3) {
                analisisSimple(matriz);
            } else if (count == 2) {
                LinkedList<String> digramasCadena = new LinkedList<String>();
                //analisisMixto(digramasCadena, indices);
            }
        } else {
            throw new Exception("No se pueden realizar supuestos para el análisis");
        }
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
        String digrAux = java.util.ResourceBundle.getBundle("hill.resources.DigramsFreqs").getString("digramas").toLowerCase();
        for (int i = 0; i < digrAux.length(); i += 2) {
            digramas.add(digrAux.substring(i, i + 2));
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
}
