/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hill.utils;

import hill.excepciones.TextoException;
import java.util.LinkedList;

/**
 *
 * @author fakefla-kubuntu
 */
public class utilsHill {

    public static int NUM_CHARS = 28;
    public static int NUM_2CHARS = 784;
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

    public static double determinante(Jama.Matrix matrix) {
        return utilsHill.modulo((int) Math.round(matrix.det()), utilsHill.NUM_2CHARS);
    }

    /**
     * retorna el caracter asociado al número ingresado
     * @param num
     * @return
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
     * Función que calcula el módulo entre x, y (x%y)
     * @param x
     * @param y
     * @return
     */
    public static int modulo(int x, int y) {
        if (x >= 0) {
            return x % y;
        }
        return y - (Math.abs(x) % y);
    }

    /**
     * Calcula los digramas repetidos separados por una distancia múltiplo de 6
     * en una cadena de texto ingresada. También calcula los índices de la
     * primera ocurrencia del digrama repetido
     * @param cadena
     * @return Retorna una lista encadenada que contiene en sus posiciones pares
     * un string que representa el digrama, en en la respectiva posición impar
     * siguiente, se encuentra el índice asociado a su primera ocurrencia
     */
    public static LinkedList digramasRepetidos(String cadena) {
        LinkedList digramsIndexes = new LinkedList<String>();
        String aux = "";
        int auxIndex;
        for (int i = 0; i < cadena.length() - 2; i += 2) {
            aux = cadena.substring(i, i + 2);
            auxIndex = cadena.indexOf(aux, i + 2);
            while (auxIndex != -1) {
                if ((auxIndex - i) % 6 == 0 && !digramsIndexes.contains(i)) {
                    digramsIndexes.add(aux);
                    digramsIndexes.add(i);
                    digramsIndexes.add((i%6)/2);
                }
                auxIndex = cadena.indexOf(aux, auxIndex + 2);
            }
        }
        return digramsIndexes;
    }

    public static String arreglarCadena(String cadena){
        cadena=cadena.trim().toLowerCase();
        cadena.replace('_', ' ');
        return cadena;
    }
}
