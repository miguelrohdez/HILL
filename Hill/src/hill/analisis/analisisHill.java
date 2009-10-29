/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hill.analisis;

import Jama.Matrix;
import hill.CS.CSHill;
import hill.utils.utilsHill;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author fakefla-kubuntu
 */
public class analisisHill {

    private LinkedList supuesto;
    private CSHill cryptSyst;
    private LinkedList<String> digramas;

    public analisisHill() {
        supuesto = new LinkedList();
        cryptSyst = new CSHill();
        inicializarDigramas();
    }

    public void analizar(String txtCifrado, LinkedList<String> posiblesTextos) throws Exception {
        supuesto = utilsHill.digramasRepetidos(txtCifrado);
        analisisSupuesto();
    }

    public void analisisSupuesto() throws Exception {
        if (!supuesto.isEmpty()) {
            LinkedList<Integer> indices = new LinkedList<Integer>();
            LinkedList<String> digramasCadena = new LinkedList<String>();
            for (int i = 0; i < supuesto.size() / 3; i += 3) {
                digramasCadena.add((String) supuesto.get(i));
                indices.add((Integer) supuesto.get(i + 1));
            }
            LinkedList<Integer> colsInvar = columnasInvariantes(indices);
            if (colsInvar.size() == 3) {
                analisisSimple(digramasCadena, indices);
            } else if (colsInvar.size() == 2) {
                analisisMixto(digramasCadena, indices, colsInvar);
            } else {
                throw new Exception("La cadena de texto es muy corta para realizar el análisis");
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

    public void analisisSimple(LinkedList<String> digramasRepetidos, LinkedList<Integer> indices) {
        Matrix matrix = new Matrix(3, 3);
        Integer auxNum;

        LinkedList<LinkedList> listas = generateListas(digramasRepetidos, indices);
        for () {
            for () {
                for () {
                    
                }
            }
        }

        throw new UnsupportedOperationException("Not yet implemented");
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
}
