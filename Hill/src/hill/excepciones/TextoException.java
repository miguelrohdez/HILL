/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hill.excepciones;

/**
 *
 * @author fakefla-kubuntu
 */
public class TextoException extends Exception{
    public TextoException(){
        super("Error en el texto ingresado");
    }

    public TextoException(String message){
        super(message);
    }
}
