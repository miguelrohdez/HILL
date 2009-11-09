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
    private static HashSet determinantes; //TABLA INDEXADA -> Búsquedas Rápidas

    /**
     * CONSTRUCTOR, inicializa el objeto de la clase
     */
    public CSHill() {
        this.clave = new Matrix(3, 3); //inicialmente en ceros
        this.txtCifrado = ""; //inicialmente en blanco
        this.txtClaro = ""; //inicialmente en blanco
        inicializarDeterminantes(); //pone los determinantes válidos en el hash set
    }

    /**
     * Constructor que recibe como parámetro la clave con que se desea construir
     * el cripto - sistema
     * @param clave
     * @throws ClaveException
     */
    public CSHill(String clave) throws ClaveException {
        construirClave(clave);
        txtCifrado = "";
        txtClaro = "";
        inicializarDeterminantes();
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

    /**
     * Inicializa la matriz clave a partir de una cadena de texto
     * @param clave
     * @throws ClaveException
     */
    public void construirClave(String clave) throws ClaveException {
        //Divisón de la cadena de texto cada que encuentre una coma
        StringTokenizer st = new StringTokenizer(clave, ",");

        if (st.countTokens() != 9) {
            throw new ClaveException("La clave debe contener 9 dígitos");
        }

        //Declaro arreglo de dobles para la matriz de tam 3x3
        double[][] matrix = new double[3][3];
        int i = 0;

        //mientras tenga más particiones (Tokens) ... realize
        while (st.hasMoreTokens()) {
            //se va llenando la matriz con los números a partir de los string
            //Double.parseDouble transforma de String a número real
            matrix[i / 3][i % 3] = Double.parseDouble(st.nextToken());
            i++;
        }
        //calcula el determinante de la matriz
        int determinante = (int) utilsHill.determinante(new Matrix(matrix));
        //si el determinante no es primo relativo con 784 se arroja un error
        if (!determinantes.contains(determinante)) {
            throw new ClaveException("La matriz clave no es válida");
        }
        this.clave = new Matrix(matrix);
    }

    /**
     * Cifra!
     * @param txtClaro
     * @throws TextoException
     */
    public void cifrar(String txtClaro) throws TextoException {
        //Comparación contra una expresión regular
        if (!txtClaro.matches("[A-Za-zñÑ ]+")) {
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
        if (!txtCifrado.matches("[A-Za-zñÑ ]+")) {
            throw new TextoException("el texto contiene caracteres inválidos");
        }
        txtCifrado = utilsHill.adjustString(txtCifrado, 6);
        txtClaro = "";
        Matrix claveInv = utilsHill.getInversa(clave);
        txtClaro = toTexto(txtCifrado, claveInv);
    }

    /**
     * Función que descifra un texto con una clave INVERTIDA es decir que no
     * invierte la matriz para realizar el cálculo
     * @param txtCifrado
     * @param claveInv
     * @return
     * @throws Exception
     */
    public static String descifrarInv(String txtCifrado, Matrix claveInv) throws Exception {
        if (!txtCifrado.matches("[A-Za-zñÑ ]+")) {
            throw new TextoException("el texto contiene caracteres inválidos");
        }
        double det = utilsHill.determinante(claveInv);
        if (!determinantes.contains(utilsHill.MMI((int) det, utilsHill.NUM_2CHARS))) {
            throw new ClaveException("matriz clave inválida");
        }
        txtCifrado = utilsHill.adjustString(txtCifrado, 6);
        return toTexto(txtCifrado, claveInv);
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
    public static void inicializarDeterminantes() {
        if (determinantes != null) {
            if (!determinantes.isEmpty()) {
                determinantes.clear();
            }
        }
        HashSet aux = new HashSet();
        for (int i = 0; i < 784; i++) {
            //Si es primo relativo lo agrega al hash set si no, lo ignora
            if (utilsHill.primosRelativos(i, 784)) {
                aux.add(i);
            }
        }
        determinantes = aux;
        System.out.println(determinantes);
    }

    /**
     * Calcula el texto asociado para una cadena de 3 digramas usando la matriz
     * clave que entra como parámetro (proceso final de descifrado)
     * @param txt
     * @param claveMatrix
     * @return
     * @throws TextoException
     */
    public static String toTexto(String txt, Matrix claveMatrix) throws TextoException {
        String resultTxt = "";
        Matrix vector = new Matrix(1, 3);
        for (int i = 0; i < txt.length(); i += 6) {
            vector = getVectorInt(txt.substring(i, i + 6));
            //función times multiplica matrices en este caso un vector por una matriz
            vector = vector.times(claveMatrix);
            //Calcula el módulo para los componentes del vector
            vector.set(0, 0, utilsHill.modulo((int) Math.floor(vector.get(0, 0)), utilsHill.NUM_2CHARS));
            vector.set(0, 1, utilsHill.modulo((int) Math.floor(vector.get(0, 1)), utilsHill.NUM_2CHARS));
            vector.set(0, 2, utilsHill.modulo((int) Math.floor(vector.get(0, 2)), utilsHill.NUM_2CHARS));
            //Se tiene el texto deseado en números se pasará ahora a letras
            resultTxt = resultTxt.concat(Character.toString(utilsHill.getChar((int) Math.floor(vector.get(0, 0) / utilsHill.NUM_CHARS))));
            resultTxt = resultTxt.concat(Character.toString(utilsHill.getChar((int) Math.floor(vector.get(0, 0) % utilsHill.NUM_CHARS))));
            resultTxt = resultTxt.concat(Character.toString(utilsHill.getChar((int) Math.floor(vector.get(0, 1) / utilsHill.NUM_CHARS))));
            resultTxt = resultTxt.concat(Character.toString(utilsHill.getChar((int) Math.floor(vector.get(0, 1) % utilsHill.NUM_CHARS))));
            resultTxt = resultTxt.concat(Character.toString(utilsHill.getChar((int) Math.floor(vector.get(0, 2) / utilsHill.NUM_CHARS))));
            resultTxt = resultTxt.concat(Character.toString(utilsHill.getChar((int) Math.floor(vector.get(0, 2) % utilsHill.NUM_CHARS))));
        }
        return resultTxt;
    }
}
