/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hill.analisis;

import Jama.Matrix;
import hill.CS.CSHill;
import hill.excepciones.TextoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author fakefla-kubuntu
 */
public class analisisHill {

    private CSHill cryptSyst;
    private LinkedList<String> digramas;
    private static boolean fin;
    private String letrasComienzoInvalido="xyzkw";
    private  String repetidasValidas = "ceolr";
    private  int longitudMaxima = 14;
    private  String[] palabrasClave = {"ante", "bajo", "cabe", "con", "contra", "de", "desde", "durante",
        "en", "entre", "hacia", "hasta", "mediante", "para", "por", "segun", "sin", "sobre", "tras", "via", "el", "los",
        "la", "las", "lo"};

    public analisisHill() {
        cryptSyst = new CSHill();
        inicializarDigramas();
        fin = false;
    }

    public void analizar(String txtCifrado, String col1, String col2, String col3) throws Exception {
//        analisisSupuesto(txtCifrado, col1, col2, col3);
        analisisAleatorio(txtCifrado);
    }

    public void analisisSupuesto(String txtCifrado, String col0, String col1, String col2) throws Exception {
        int count = 0;
        if (!col0.equals("")) {
            count++;
        } else {
            col0 = "__";
        }
        if (!col1.equals("")) {
            count++;
        } else {
            col1 = "__";
        }
        if (!col2.equals("")) {
            count++;
        } else {
            col2 = "__";
        }
        if (count > 1) {

            if (count == 3) {
                String cadenaDigs = col0.concat(col1).concat(col2);
                analisisSimple(cadenaDigs);
            } else if (count == 2) {
                LinkedList<String> digramasCadena = new LinkedList<String>();
                digramasCadena.add(col0);
                digramasCadena.add(col1);
                digramasCadena.add(col2);
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

    public void analisisSimple(String cadenaDigramas) throws TextoException {
        Matrix matrix = new Matrix(3, 3);
        Integer auxNum;
        Matrix vector = CSHill.getVectorInt(cadenaDigramas);
        Matrix a = CSHill.getVectorInt(cadenaDigramas);
        Matrix b = CSHill.getVectorInt("mbiano");
        ArrayList<ArrayList<Integer>> posiblesVariables = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 9; i++) {
            posiblesVariables.add(new ArrayList<Integer>());
        }
        System.out.println("INICIO");
        System.out.println("a1=" + a.get(0, 0) + " a2=" + a.get(0, 1) + " a3=" + a.get(0, 2) + " b=" + b.get(0, 0));
        long start = System.currentTimeMillis();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 784; j++) {
                for (int k = 0; k < 784; k++) {
                    for (int l = 0; l < 784; l++) {
                        if ((j * a.get(0, 0) + k * a.get(0, 1) + l * a.get(0, 2)) == b.get(0, i)) {
                            posiblesVariables.get(i * 3).add(j);
                            posiblesVariables.get(i * 3 + 1).add(k);
                            posiblesVariables.get(i * 3 + 2).add(l);
                            System.out.println(i+","+j+","+k);
                        }
                    }
                }
            }
        }
        long finish = System.currentTimeMillis();
        System.out.println("FIN");
        System.out.println("Elapsed time in milis = " + (finish - start));
        for(Integer num : posiblesVariables.get(2)){
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
        while(!fin){
            claveSupuesta.set(0,0, Math.random()%784);
            claveSupuesta.set(0,1, Math.random()%784);
            claveSupuesta.set(0,2, Math.random()%784);
            claveSupuesta.set(1,0, Math.random()%784);
            claveSupuesta.set(1,1, Math.random()%784);
            claveSupuesta.set(1,2, Math.random()%784);
            claveSupuesta.set(2,0, Math.random()%784);
            claveSupuesta.set(2,1, Math.random()%784);
            claveSupuesta.set(2,2, Math.random()%784);
            descifrado = CSHill.descifrar(txtCifrado.substring(0, 30), claveSupuesta);
            if(inicialmenteValida(descifrado)){
                descifrado = CSHill.descifrar(txtCifrado, claveSupuesta);
                if(totalmenteValida(descifrado)){
                    fin=true;
                }
            }
        }
        return descifrado;
    }

    private boolean inicialmenteValida(String subCadena){
        return !comienzoInvalido(subCadena) & !longitudInvalida(subCadena) & !repetidasMalas(subCadena);
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
            if(auxCadena[i].length()>longitudMaxima){
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
        String[] divisionesCadena=cadena.split(" ");
            if(!contienePalabras(divisionesCadena)){
                return false;
            }
        return true;
    }

    public boolean contienePalabras(String[] divisionesCadena){
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
            if(auxDivisiones.contains(auxPalabras.get(i))){
                return true;
            }
        }
        return false;
    }
}
