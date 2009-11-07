/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hill.utils;

import Jama.Matrix;
import hill.excepciones.TextoException;
import java.math.BigInteger;
import org.omg.CORBA.DATA_CONVERSION;

/**
 *
 * @author fakefla-kubuntu
 */
public class utilsHill {

    public static int NUM_CHARS = 28;
    public static int NUM_2CHARS = 784;
    public static int NUM_DIGS = 10;
    public static int NUM_SALTO = 30;
    private static char[] alfabeto = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' '};

    /**
     * Función usada para saber si x, y son primos relativos
     * @param x
     * @param y
     * @return
     */
    public static boolean primosRelativos(int x, int y) {
        int a = x;
        int b = y;

        if ((a == 0) || (b == 0)) {
            if ((a + b == 1) || (a + b == -1)) {
                return true;
            } else {
                return false;
            }
        }

        while (a != b) {
            if (a < b) {
                b = b - a;
            } else {
                a = a - b;
            }
        }

        return (a == 1);
    }

    /**
     * calcula el determinante para la matriz de entrada
     * @param matrix
     * @return
     */
    public static double determinante(Matrix matrix) {
        //devuelve el determinante de la matriz módulo 784
        return utilsHill.modulo((int) Math.round(matrix.det()), utilsHill.NUM_2CHARS);
    }

    /**
     * retorna el caracter asociado al número ingresado
     * @param num
     * @returnJama.
     * @throws TextoException
     */
    public static char getChar(int num) throws TextoException {
        if (num > 27 || num < 0) {
            throw new TextoException("El número ingresado no tiene caracter correspondiente");
        }
        return alfabeto[num];
    }

    /**
     * Retorna el índice asociado al caracter ingresado
     * @param a
     * @return
     * @throws TextoException
     */
    public static int getNumber(char a) throws TextoException {
        String aux = new String(alfabeto);
        if (!aux.contains(Character.toString(a).toLowerCase())) {
            throw new TextoException("caracter ingresado no es permitido");
        }
        return aux.indexOf(Character.toString(a).toLowerCase());
    }

    /**
     * Ajusta una cadena de texto para que sea exactamente divisible en num partes
     * @param str
     * @param num
     * @return
     */
    public static String adjustString(String str, int num) {
        while (str.length() % num != 0) {
            str = str.concat(" ");
        }
        return str;
    }

    /**
     * Función que calcula el módulo entre x, y (x%y) diferenciando entre
     * positivos y negativos
     * @param x
     * @param y
     * @return
     */
    public static int modulo(int x, int y) {
        if (x >= 0) {
            return x % y;
        }
        //Math.abs calcula el valor absoluto
        return y - (Math.abs(x) % y);
    }

    /**
     * Arregla una cadena de texto para que pueda ser analizada por el programa
     * cuando esta contiene el caracter '_' para separar palabras
     * @param cadena
     * @return
     */
    public static String arreglarCadena(String cadena) {
        cadena = cadena.trim().toLowerCase();
        cadena = cadena.replace('_', ' ');
        return cadena;
    }

    /**
     * retorna el valor entero correspondiente al digrama ingresado
     * @param digrama
     * @return
     */
    public static int digramaToNum(String digrama) throws TextoException {
        if (digrama.length() != 2) {
            throw new TextoException("la longitud del digrama debe ser 2");
        }
        int aux = utilsHill.getNumber(digrama.charAt(0)) * utilsHill.NUM_CHARS + utilsHill.getNumber(digrama.charAt(1));
        return aux % NUM_2CHARS;
    }

    /**
     * retorna el valor en cadena de texto correspondiente al número ingresado
     * @param num
     * @return
     * @throws TextoException
     */
    public static String numToDigrama(int num) throws TextoException {
        num = num % NUM_2CHARS;
        String digrama = "";
        digrama = digrama.concat(Character.toString(getChar( num / NUM_CHARS)));
        digrama = digrama.concat(Character.toString(getChar( num % NUM_CHARS)));
        return digrama;
    }

    /**
     * Calcula la matriz inversa (inversa Hill) de la ingresada
     * @param matrix
     * @return
     * @throws Exception
     */
    public static Matrix getInversa(Matrix matrix) throws DATA_CONVERSION {

        double det = 0;
        try {
            det = (int) utilsHill.determinante(matrix);
            det = MMI((int) det, utilsHill.NUM_2CHARS);
        } catch (DATA_CONVERSION data_conversion) {
            throw new DATA_CONVERSION("Matriz no inversible");
        }

        double[][] array = matrix.getArrayCopy();
        //crea una matriz de la otra librería únicamente para calcular la adjunta
        org.codezealot.matrix.Matrix matAux = new org.codezealot.matrix.Matrix(matrix.getRowDimension(), matrix.getColumnDimension(), array);

        //función que Calcula la adjunta de una vez transpuesta
        matAux = matAux.getAdjugate();

        for (int i = 0; i < matrix.getRowDimension(); i++) {
            for (int j = 0; j < matrix.getColumnDimension(); j++) {
                array[i][j] = utilsHill.modulo((int) matAux.get(i, j), utilsHill.NUM_2CHARS);
            }
        }

        Matrix matriz = new Matrix(array);
        matriz = matriz.times(det);
        matriz = new Matrix(moduloMatriz(matriz.getArrayCopy()));
        return matriz;
    }

    /**
     * Calcula el MODULAR MULTIPLICATIVE INVERSE de dos números a, m.
     * Aplica el algoritmo extendido de uclides
     * @param a es el número al que se le quiere hallar el inverso
     * @param m es el número que se usará como módulo
     * @return
     * @throws Exception
     */
    public static int MMI(int a, int m) throws DATA_CONVERSION {
        if (!utilsHill.primosRelativos(a, m)) {
            throw new DATA_CONVERSION("los números deben ser primos relativos para calcular el inverso");
        }
        BigInteger num = new BigInteger(Integer.toString(a));
        BigInteger numM = new BigInteger(Integer.toString(m));
        //modInverse es un método de la clase BigInteger
        return num.modInverse(numM).intValue();
    }

    /**
     * Calcula el módulo para cada elemento de la matriz
     * @param array
     * @return
     */
    public static double[][] moduloMatriz(double[][] array) {

        //array.length devuelve el número de filas de la matriz
        for (int i = 0; i < array.length; i++) {
            //array[0].length devuelve el número de columnas de la matriz
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = utilsHill.modulo((int) array[i][j], utilsHill.NUM_2CHARS);

            }
        }
        return array;
    }

    /**
     * Determina si una matriz es inversible o no
     * @param matriz
     * @return
     */
    public static boolean inversible(Matrix matriz) {
        try {
            int det = (int) determinante(matriz);
            det = MMI((int) det, utilsHill.NUM_2CHARS);
            return true;
        } catch (DATA_CONVERSION ex) {
            return false;
        }
    }

    /**
     * Imprime la matriz ingresada en consola
     * @param matrix
     */
    public static void printMatrix(Matrix matrix) {
        String print = "";
        for (int i = 0; i < matrix.getRowDimension(); i++) {
            for (int j = 0; j < matrix.getColumnDimension(); j++) {
                print = print.concat(Double.toString(matrix.get(i, j)) + "\t");
            }
            print = print.concat("\n");
        }
        System.out.println(print);
    }

    /**
     * Retorna una matriz en String de la matriz ingresada
     * @param matrix
     * @return
     */
    public static String matrixToString(Matrix matrix) {
        String print = "";
        for (int i = 0; i < matrix.getRowDimension(); i++) {
            for (int j = 0; j < matrix.getColumnDimension(); j++) {
                print = print.concat(Integer.toString((int)matrix.get(i, j)) + "\t");
            }
            print = print.concat("\n");
        }
        return print;
    }
}
