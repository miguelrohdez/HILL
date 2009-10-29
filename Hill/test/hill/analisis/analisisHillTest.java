/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hill.analisis;

import java.util.LinkedList;
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
public class analisisHillTest {

    public analisisHillTest() {
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
     * Test of analizar method, of class analisisHill.
     */
    @Test
    public void testAnalizar() throws Exception {
        System.out.println("analizar");
        String txtCifrado = "";
        LinkedList<String> posiblesTextos = null;
        analisisHill instance = new analisisHill();
        instance.analizar(txtCifrado, posiblesTextos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of analisisSupuesto method, of class analisisHill.
     */
    @Test
    public void testAnalisisSupuesto() throws Exception {
        System.out.println("analisisSupuesto");
        analisisHill analisis = new analisisHill();
        analisis.analisisSupuesto();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of columnasInvariantes method, of class analisisHill.
     */
    @Test
    public void testColumnasInvariantes() {
        System.out.println("columnasInvariantes");
        analisisHill analisis = new analisisHill();
        LinkedList<Integer> indices = new LinkedList<Integer>();
        indices.add(2);
        indices.add(98);
        indices.add(156);
        indices.add(244);
        LinkedList<Integer> expResult = new LinkedList<Integer>();
        expResult.add(0);
        expResult.add(1);
        expResult.add(2);
        LinkedList<Integer> result = analisis.columnasInvariantes(indices);
        assertEquals(expResult, result);
    }

    /**
     * Test of eliminarRepetidos method, of class analisisHill.
     */
    @Test
    public void testEliminarRepetidos() {
        System.out.println("eliminarRepetidos");
        analisisHill analisis = new analisisHill();
        LinkedList<Integer> indices = new LinkedList<Integer>();
        indices.add(2);
        indices.add(4);
        indices.add(0);
        indices.add(2);
        indices.add(4);
        indices.add(6);
        indices.add(0);
        indices.add(6);
        indices.add(6);
        LinkedList<Integer> expResult = new LinkedList<Integer>();
        expResult.add(2);
        expResult.add(4);
        expResult.add(0);
        expResult.add(6);
        LinkedList<Integer> result = analisis.eliminarRepetidos(indices);
        assertEquals(expResult, result);
    }

    /**
     * Test of analisisSimple method, of class analisisHill.
     */
    @Test
    public void testAnalisisSimple() {
        System.out.println("analisisSimple");
        analisisHill analisis = new analisisHill();
        LinkedList<String> digramas = null;
        LinkedList<Integer> indices = null;
        analisis.analisisSimple(digramas, indices);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of analisisMixto method, of class analisisHill.
     */
    @Test
    public void testAnalisisMixto() {
        System.out.println("analisisMixto");
        analisisHill analisis = new analisisHill();
        LinkedList<String> digramas = null;
        LinkedList<Integer> indices = null;
        LinkedList<Integer> colsInvar = null;
        analisis.analisisMixto(digramas, indices, colsInvar);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inicializarDigramas method, of class analisisHill.
     */
    @Test
    public void testInicializarDigramas() {
        System.out.println("inicializarDigramas");
        analisisHill analisis = new analisisHill();
        analisis.inicializarDigramas();
    }

    /**
     * Test of generateListas method, of class analisisHill.
     */
    @Test
    public void testGenerateListas() {
        System.out.println("generateListas");
        LinkedList<String> digramasRepetidos = new LinkedList<String>();
        digramasRepetidos.add(" g");
        digramasRepetidos.add("zb");
        digramasRepetidos.add("kp");
        digramasRepetidos.add("jc");
        LinkedList<Integer> indices = new LinkedList<Integer>();
        indices.add(2);
        indices.add(98);
        indices.add(156);
        indices.add(244);
        analisisHill instance = new analisisHill();
        LinkedList expResult = null;
        LinkedList result = instance.generateListas(digramasRepetidos, indices);
        assertEquals(expResult, result);
    }

}