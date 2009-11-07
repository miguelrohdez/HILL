/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hill.CS;

import Jama.Matrix;
import hill.excepciones.ClaveException;
import hill.excepciones.TextoException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fakefla-kubuntu
 */
public class CSHillTest {

    public CSHillTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of construirClave method, of class CSHill.
     */
    @Test
    public void testConstruirClave() {
        System.out.println("construirClave");
        String clave = "3,0,3,0,154,0,5,0,3";
        CSHill instance = new CSHill();
        Matrix expResult = null;
        Matrix result = new Matrix(3,3);
            result = null;

        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cifrar method, of class CSHill.
     */
    @Test
    public void testCifrar() throws TextoException, ClaveException {
        System.out.println("cifrar");
        /*String txtClaro = "Un Switch es un equipo que tiene como principal funcion interconectar y transmitir paquetes entre dos segmentos de red de acuerdo a las direcciones MAC Un Switch permite que la comunicacion en una red sea mas eficiente al conmutar los datos transmitidos hacia los equipos conectados a los puertos de este equipo";
        txtClaro = txtClaro.toLowerCase();
        String clave = "553,721,317,454,315,699,640,755,418";
        CSHill instance = new CSHill(clave);
        try {
        instance.cifrar(txtClaro);
        } catch (Exception e) {
        System.out.println(e.getMessage());
        }
        String expResult = "zs gpznpppyjvdxyopñvoeywbtuxvietaczkqhoipdbenbyrqbhtwqmnzkcztacsmbzzmvndiprjjnmpbzargbqqlbñnyxqzsqzbñgjtqowzdndxvpdtzeenndtqqjñfxbzcbrmn naiq oeovpujjgjdwpxkpeoppvt iymiykomawtwcsclymfo bfhoxzsffdknjjaafidñvqrynxfookcebbskd xoc genpfbiwvpuyxi jjckpynigbdm ydlqzbzgcj gldvy xñxszxcm urfcphdamijprbobjclo dasmjzvhh";
        String result = instance.getTxtCifrado();*/
        String txtClaro = "zs gpznpppyjvdxyopñvoeywbtuxvietaczkqhoipdbenbyrqbhtwqmnzkcztacsmbzzmvndiprjjnmpbzargbqqlbñnyxqzsqzbñgjtqowzdndxvpdtzeenndtqqjñfxbzcbrmn naiq oeovpujjgjdwpxkpeoppvt iymiykomawtwcsclymfo bfhoxzsffdknjjaafidñvqrynxfookcebbskd xoc genpfbiwvpuyxi jjckpynigbdm ydlqzbzgcj gldvy xñxszxcm urfcphdamijprbobjclo dasmjzvhh";
        txtClaro = txtClaro.toLowerCase();
        String clave = "591,671,84,204,214,97,214,623,679";
        CSHill instance = null;
        try {
            instance = new CSHill(clave);
            instance.cifrar(txtClaro);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String expResult = "un switch es un equipo que tiene como principal funcion interconectar y transmitir paquetes entre dos segmentos de red de acuerdo a las direcciones mac un switch permite que la comunicacion en una red sea mas eficiente al conmutar los datos transmitidos hacia los equipos conectados a los puertos de este equipo ";
        String result = instance.getTxtCifrado();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getVectorInt method, of class CSHill.
     */
    @Test
    public void testGetVectorInt() throws Exception {
        System.out.println("getVectorInt");
        String cadena = "mundo ";
        CSHill instance = new CSHill();
        double[][] aux = new double[][]{{357, 367, 447}};
        Matrix expResult = new Matrix(aux);
        Matrix result = instance.getVectorInt(cadena);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getTxtCifrado method, of class CSHill.
     */
    @Test
    public void testGetTxtCifrado() {
        System.out.println("getTxtCifrado");
        CSHill instance = new CSHill();
        String expResult = "";
        String result = instance.getTxtCifrado();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTxtCifrado method, of class CSHill.
     */
    @Test
    public void testSetTxtCifrado() {
        System.out.println("setTxtCifrado");
        String txtCifrado = "";
        CSHill instance = new CSHill();
        instance.setTxtCifrado(txtCifrado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTxtClaro method, of class CSHill.
     */
    @Test
    public void testGetTxtClaro() {
        System.out.println("getTxtClaro");
        CSHill instance = new CSHill();
        String expResult = "";
        String result = instance.getTxtClaro();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTxtClaro method, of class CSHill.
     */
    @Test
    public void testSetTxtClaro() {
        System.out.println("setTxtClaro");
        String txtClaro = "";
        CSHill instance = new CSHill();
        instance.setTxtClaro(txtClaro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of descifrar method, of class CSHill.
     */
    @Test
    public void testDescifrar() throws Exception {
        System.out.println("descifrar");
        String txtCifrado = "zs gpznpppyjvdxyopñvoeywbtuxvietaczkqhoipdbenbyrqbhtwqmnzkcztacsmbzzmvndiprjjnmpbzargbqqlbñnyxqzsqzbñgjtqowzdndxvpdtzeenndtqqjñfxbzcbrmn naiq oeovpujjgjdwpxkpeoppvt iymiykomawtwcsclymfo bfhoxzsffdknjjaafidñvqrynxfookcebbskd xoc genpfbiwvpuyxi jjckpynigbdm ydlqzbzgcj gldvy xñxszxcm urfcphdamijprbobjclo dasmjzvhh";
        String clave = "553,721,317,454,315,699,640,755,418";
        CSHill instance = new CSHill(clave);
        try {
            instance.descifrar(txtCifrado);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        String result = instance.getTxtClaro();
        String expResult = "Un Switch es un equipo que tiene como principal funcion interconectar y transmitir paquetes entre dos segmentos de red de acuerdo a las direcciones MAC Un Switch permite que la comunicacion en una red sea mas eficiente al conmutar los datos transmitidos hacia los equipos conectados a los puertos de este equipo ";
        expResult = expResult.toLowerCase();
        System.out.println("res:" + result);
        System.out.println("exp:" + expResult);
        System.out.println(instance);
        assertEquals(expResult, result);
    }

    /**
     * Test of moduloMatriz method, of class CSHill.
     */
    @Test
    public void testModuloMatriz() {
        System.out.println("moduloMatriz");
        double[][] array = null;
        double[][] expResult = null;
//        double[][] result = CSHill.moduloMatriz(array);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInversa method, of class CSHill.
     */
    @Test
    public void testGetInversa() throws Exception {
        System.out.println("getInversa");
        double[][] matr = new double[][]{{10, 5, 12}, {3, 14, 21}, {8, 9, 11}};
        double[][] matrI = new double[][]{{581.0,397.0,105.0},{671.0,238.0,738.0},{739.0,158.0,389.0}};
        Matrix matrix = new Matrix(matr);
        Matrix expResult = new Matrix(matrI);
//        Matrix result = CSHill.getInversa(matrix);
        /*double[][] matr = new double[][]{{11, 8}, {3, 7}};
        double[][] matrI = new double[][]{{7,18},{23,11}};
        Matrix matrix = new Matrix(matr);
        Matrix expResult = new Matrix(matrI);
        Matrix result = CSHill.getInversa(matrix);*/
        /*double[][] matr = new double[][]{{10, 5, 12}, {3, 14, 21}, {8, 9, 11}};
        double[][] matrI = new double[][]{{10, 5, 12}, {3, 14, 21}, {8, 9, 11}};
        Matrix matrix = new Matrix(matr);
        Matrix expResult = new Matrix(matrI);
        Matrix result = CSHill.getInversa(matrix);*/

//        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of MMI method, of class CSHill.
     */
    @Test
    public void testMMI() throws Exception {
        System.out.println("MMI");
        int a = 0;
        int m = 0;
        CSHill instance = new CSHill();
        int expResult = 0;
//        int result = instance.MMI(a, m);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClave method, of class CSHill.
     */
    @Test
    public void testGetClave() {
        System.out.println("getClave");
        CSHill instance = new CSHill();
        Matrix expResult = null;
        Matrix result = instance.getClave();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of descifrar method, of class CSHill.
     */
    @Test
    public void testDescifrar_String() throws Exception {
        System.out.println("descifrar");
        String txtCifrado = "zs gpznpppyjvdxyopñvoeywbtuxvietaczkqhoipdbenbyrqbhtwqmnzkcztacsmbzzmvndiprjjnmpbzargbqqlbñnyxqzsqzbñgjtqowzdndxvpdtzeenndtqqjñfxbzcbrmn naiq oeovpujjgjdwpxkpeoppvt iymiykomawtwcsclymfo bfhoxzsffdknjjaafidñvqrynxfookcebbskd xoc genpfbiwvpuyxi jjckpynigbdm ydlqzbzgcj gldvy xñxszxcm urfcphdamijprbobjclo dasmjzvhh";
        String clave = "553,721,317,454,315,699,640,755,418";
        CSHill instance = new CSHill(clave);
        try {
            instance.descifrar(txtCifrado);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        String result = instance.getTxtClaro();
        String expResult = "Un Switch es un equipo que tiene como principal funcion interconectar y transmitir paquetes entre dos segmentos de red de acuerdo a las direcciones MAC Un Switch permite que la comunicacion en una red sea mas eficiente al conmutar los datos transmitidos hacia los equipos conectados a los puertos de este equipo ";
        expResult = expResult.toLowerCase();
        System.out.println(result);
        System.out.println(expResult);
        assertEquals(expResult, result);
    }
}
