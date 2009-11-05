/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hill.CS;

import hill.excepciones.ClaveException;
import hill.excepciones.TextoException;
import hill.utils.utilsHill;
import java.util.HashSet;
import java.util.StringTokenizer;
import org.apache.commons.math.fraction.FractionConversionException;
import Jama.Matrix;
/**
 *
 * @author fakefla-kubuntu
 */
public class CSHill {

    private Matrix clave;
    private String txtCifrado;
    private String txtClaro;
    private static HashSet determinantes;

    public CSHill() {
        this.clave = new Matrix(3, 3);
        this.txtCifrado = "";
        this.txtClaro = "";
        determinantes = inicializarDeterminantes();
    }

    public CSHill(String clave) throws ClaveException {
        construirClave(clave);
        txtCifrado = "";
        txtClaro = "";
        determinantes = inicializarDeterminantes();
    }

    public String getTxtCifrado() {
        return txtCifrado;
    }

    public void setTxtCifrado(String txtCifrado) {
        this.txtCifrado = txtCifrado;
    }

    public String getTxtClaro() {
        return txtClaro;
    }

    public void setTxtClaro(String txtClaro) {
        this.txtClaro = txtClaro.toLowerCase();
    }

    public Matrix getClave() {
        return clave;
    }

    public void construirClave(String clave) throws ClaveException {
        StringTokenizer st = new StringTokenizer(clave, ",");

        if (st.countTokens() != 9) {
            throw new ClaveException("La clave debe contener 9 dígitos");
        }

        double[][] matrix = new double[3][3];
        int i = 0;

        while (st.hasMoreTokens()) {
            matrix[i / 3][i % 3] = Double.parseDouble(st.nextToken());
            i++;
        }
        int determinante = (int) utilsHill.determinante(new Matrix(matrix));
        if (!determinantes.contains(determinante)) {
            throw new ClaveException("La matriz clave no es válida");
        }
        this.clave = new Matrix(matrix);
    }

    public void cifrar(String txtClaro) throws TextoException {
        if (!txtClaro.matches("[A-Za-zñ ]+")) {
            throw new TextoException("el texto contiene caracteres inválidos");
        }
        txtClaro = txtClaro.toLowerCase();
        txtClaro = utilsHill.adjustString(txtClaro, 6);
        txtCifrado = toTexto(txtClaro, clave);
    }

    /**
     * Descifra el texto cifrado ingresado usando los atributos internos de la
     * clase, es decir
     * @param txtCifrado
     * @throws TextoException
     * @throws FractionConversionException
     * @throws Exception
     */
    public void descifrar(String txtCifrado) throws TextoException, FractionConversionException, Exception {
        if (!txtCifrado.matches("[A-Za-zñ ]+")) {
            throw new TextoException("el texto contiene caracteres inválidos");
        }
        txtCifrado = utilsHill.adjustString(txtCifrado, 6);
        txtClaro = "";
        Matrix claveInv = utilsHill.getInversa(clave);
        txtClaro=toTexto(txtCifrado, claveInv);
    }

    /**
     * Función que descifra un texto cifrado con una matriz clave ingresada
     * @param txtCifrado
     * @param clave arreglo de dos dimensiones que corresponde a la clave
     * @return
     * @throws TextoException
     * @throws FractionConversionException
     * @throws Exception
     */
    public String descifrar(String txtCifrado, double[][] clave) throws TextoException, FractionConversionException, Exception {
        if (!txtCifrado.matches("[A-Za-zñ ]+")) {
            throw new TextoException("el texto contiene caracteres inválidos");
        }
        Matrix claveMatrix = new Matrix(clave);
        double det=utilsHill.determinante(claveMatrix);
        if(!determinantes.contains(utilsHill.MMI((int)det, utilsHill.NUM_2CHARS))){
            throw new ClaveException("matriz clave inválida");
        }
        txtCifrado = utilsHill.adjustString(txtCifrado, 6);
        return toTexto(txtCifrado, claveMatrix);
    }

    /**
     * Función que descifra un texto cifrado con una matriz clave ingresada
     * @param txtCifrado
     * @param clave objeto de tipo Matrix que corresponde a la matriz clave
     * @return
     * @throws TextoException
     * @throws FractionConversionException
     * @throws Exception
     */
    public static String descifrar(String txtCifrado, Matrix clave) throws Exception {
        if (!txtCifrado.matches("[A-Za-zñ ]+")) {
            throw new TextoException("el texto contiene caracteres inválidos");
        }
        double det=utilsHill.determinante(clave);
        if(!determinantes.contains(utilsHill.MMI((int)det, utilsHill.NUM_2CHARS))){
            throw new ClaveException("matriz clave inválida");
        }
        txtCifrado = utilsHill.adjustString(txtCifrado, 6);
        return toTexto(txtCifrado, clave);
    }

    /**
     * Calcula el valor entero de cada digrama presente en la cadena de entrada
     * @param cadena, contiene la cadena a calcular, debe ser de tamaño 6 pues
     * se realiza el cálculo para 3 digramas siempre
     * @return
     * @throws TextoException
     */
    public static Matrix getVectorInt(String cadena) throws TextoException {
        if (cadena.length() != 6) {
            throw new TextoException("error en la longitud de la cadena a tratar");
        }
        Matrix vector = new Matrix(1, 3);
        for (int i = 0; i < cadena.length(); i += 2) {
            vector.set(0, i / 2, utilsHill.getNumber(cadena.charAt(i)) * utilsHill.NUM_CHARS + utilsHill.getNumber(cadena.charAt(i + 1)));
        }
        return vector;
    }

    /**
     * Inicia el hashSet para fijar los determinantes válidos a usar en los
     * cálculos
     * @return
     */
    private HashSet inicializarDeterminantes() {
        HashSet aux = new HashSet();
        for (int i = 0; i < 784; i++) {
            if (utilsHill.primosRelativos(i, 784)) {
                aux.add(i);
            }
        }
        return aux;
    }

    /**
     * Calcula el texto asociado para una cadena de 3 digramas usando la matriz
     * clave que entra como parámetro (proceso final de descifrado)
     * @param txtClaro
     * @param claveMatrix
     * @return
     * @throws TextoException
     */
    public static String toTexto(String txtClaro, Matrix claveMatrix) throws TextoException{
        String txtCifr="";
        Matrix vector = new Matrix(1, 3);
        for (int i = 0; i < txtClaro.length(); i += 6) {
            vector = getVectorInt(txtClaro.substring(i, i + 6));
            vector = vector.times(claveMatrix);
            vector.set(0, 0, utilsHill.modulo((int)Math.floor(vector.get(0, 0)), utilsHill.NUM_2CHARS));
            vector.set(0, 1, utilsHill.modulo((int) Math.floor(vector.get(0, 1)), utilsHill.NUM_2CHARS));
            vector.set(0, 2, utilsHill.modulo((int) Math.floor(vector.get(0, 2)), utilsHill.NUM_2CHARS));
            txtCifr = txtCifr.concat(Character.toString(utilsHill.getChar((int) Math.floor(vector.get(0, 0) / utilsHill.NUM_CHARS))));
            txtCifr = txtCifr.concat(Character.toString(utilsHill.getChar((int) Math.floor(vector.get(0, 0) % utilsHill.NUM_CHARS))));
            txtCifr = txtCifr.concat(Character.toString(utilsHill.getChar((int) Math.floor(vector.get(0, 1) / utilsHill.NUM_CHARS))));
            txtCifr = txtCifr.concat(Character.toString(utilsHill.getChar((int) Math.floor(vector.get(0, 1) % utilsHill.NUM_CHARS))));
            txtCifr = txtCifr.concat(Character.toString(utilsHill.getChar((int) Math.floor(vector.get(0, 2) / utilsHill.NUM_CHARS))));
            txtCifr = txtCifr.concat(Character.toString(utilsHill.getChar((int) Math.floor(vector.get(0, 2) % utilsHill.NUM_CHARS))));
        }
        return txtCifr;
    }
}
