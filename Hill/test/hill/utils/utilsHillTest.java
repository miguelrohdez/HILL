/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hill.utils;

import Jama.Matrix;
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
public class utilsHillTest {

    public utilsHillTest() {
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
     * Test of primosRelativos method, of class utilsHill.
     */
    @Test
    public void testPrimosRelativos() {
        System.out.println("primosRelativos");
        int x = 0;
        int y = 0;
        boolean expResult = false;
        boolean result = utilsHill.primosRelativos(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of determinante method, of class utilsHill.
     */
    @Test
    public void testDeterminante() {
        System.out.println("determinante");
        Matrix matrix = null;
        double expResult = 0.0;
        double result = utilsHill.determinante(matrix);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChar method, of class utilsHill.
     */
    @Test
    public void testGetChar() throws Exception {
        System.out.println("getChar");
        int num = 1;
        char expResult = 'b';
        char result = utilsHill.getChar(num);
        assertEquals(expResult, result);
        num = 27;
        expResult = ' ';
        result = utilsHill.getChar(num);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getNumber method, of class utilsHill.
     */
    @Test
    public void testGetNumber() throws Exception {
        System.out.println("getNumber");
        char a = ' ';
        int expResult = 27;
        int result = utilsHill.getNumber(a);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of adjustString method, of class utilsHill.
     */
    @Test
    public void testAdjustString() {
        System.out.println("adjustString");
        String str = "holaholaholahola";
        int num = 6;
        String expResult = "holaholaholahola  ";
        String result = utilsHill.adjustString(str, num);
        assertEquals(expResult, result);
    }

    /**
     * Test of modulo method, of class utilsHill.
     */
    @Test
    public void testModulo() {
        System.out.println("modulo");
        int x = 0;
        int y = 0;
        int expResult = 0;
        int result = utilsHill.modulo(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of arreglarCadena method, of class utilsHill.
     */
    @Test
    public void testArreglarCadena() {
        System.out.println("arreglarCadena");
        String cadena = "";
        String expResult = "";
        String result = utilsHill.arreglarCadena(cadena);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of digramaToNum method, of class utilsHill.
     */
    @Test
    public void testDigramaToNum() throws Exception {
        System.out.println("digramaToNum");
        String digrama = "";
        int expResult = 0;
        int result = utilsHill.digramaToNum(digrama);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInversa method, of class utilsHill.
     */
    @Test
    public void testGetInversa() {
        System.out.println("getInversa");
        double[][] mat = new double[][] {{120,111,721},{770,110,121},{115,417,221}};

//        double[][] mat = new double[][]{{476,555,678},{250,345,455},{551,145,725}};
//        double[][] mat = new double[][]{{478,592,327},{295,712,592},{674,417,556}};
//        double[][] mat = new double[][]{{130,150,121},{245,580,756},{567,445,624}};
//        double[][] mat = new double[][]{{553,721,317},{454,315,699},{640,755,418}};
//        double[][] mat = new double[][]{{338,687,437},{445,132,647},{523,459,147}};
        Matrix matrix = new Matrix(mat);
        Matrix expResult = null;
        Matrix result = utilsHill.getInversa(matrix);
        utilsHill.printMatrix(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of MMI method, of class utilsHill.
     */
    @Test
    public void testMMI() {
        System.out.println("MMI");
        int a = 0;
        int m = 0;
        int expResult = 0;
        int result = utilsHill.MMI(a, m);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moduloMatriz method, of class utilsHill.
     */
    @Test
    public void testModuloMatriz_doubleArrArr() {
        System.out.println("moduloMatriz");
        double[][] array = null;
        double[][] expResult = null;
        double[][] result = utilsHill.moduloMatriz(array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inversible method, of class utilsHill.
     */
    @Test
    public void testInversible() {
        System.out.println("inversible");
        Matrix matriz = null;
        boolean expResult = false;
        boolean result = utilsHill.inversible(matriz);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printMatrix method, of class utilsHill.
     */
    @Test
    public void testPrintMatrix() {
        System.out.println("printMatrix");
        Matrix matrix = null;
        utilsHill.printMatrix(matrix);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}