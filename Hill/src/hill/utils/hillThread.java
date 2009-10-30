/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hill.utils;

import hill.HillView;
import hill.analisis.analisisHill;

/**
 *
 * @author fakefla-kubuntu
 */
public class hillThread extends Thread {

    private String name;
    private String txtCifrado;

    public hillThread(String txtCifrado) {
        this.txtCifrado = txtCifrado;
    }

    public void run() {
        try {
            analisisHill analizador = new analisisHill();
        } catch (Exception ex) {
            //mostrarMensaje(ex.getMessage());
        }
    }
}
