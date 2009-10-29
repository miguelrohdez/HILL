/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hill.excepciones;

/**
 *
 * @author fakefla-kubuntu
 */
public class ClaveException extends Exception{

    /**
     * Crea una excepción asociada con la clave
     */
    public ClaveException() {
        super("La clave no es válida");
    }

    /**
     * Crea una excepción asociada con la clave, con el mensaje deseado
     * @param mensaje el mensaje de la excepción
     */
    public ClaveException(String mensaje) {
        super(mensaje);
    }
}
