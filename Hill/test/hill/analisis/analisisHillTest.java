/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hill.analisis;

import Jama.Matrix;
import hill.utils.utilsHill;
import java.util.ArrayList;
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
//        instance.analizar(txtCifrado,);
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
        System.out.println(analisis.getDigramas());
        System.out.println(analisis.getDigramasCompletos());
    }

    /**
     * Test of getDigramas method, of class analisisHill.
     */
    @Test
    public void testGetDigramas() {
        System.out.println("getDigramas");
        analisisHill instance = new analisisHill();
        LinkedList expResult = null;
        LinkedList result = instance.getDigramas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDigramas method, of class analisisHill.
     */
    @Test
    public void testSetDigramas() {
        System.out.println("setDigramas");
        LinkedList<String> digramas = null;
        analisisHill instance = new analisisHill();
        instance.setDigramas(digramas);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDigramasCompletos method, of class analisisHill.
     */
    @Test
    public void testGetDigramasCompletos() {
        System.out.println("getDigramasCompletos");
        analisisHill instance = new analisisHill();
        ArrayList expResult = null;
        ArrayList result = instance.getDigramasCompletos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDigramasCompletos method, of class analisisHill.
     */
    @Test
    public void testSetDigramasCompletos() {
        System.out.println("setDigramasCompletos");
        ArrayList<String> digramasCompletos = null;
        analisisHill instance = new analisisHill();
        instance.setDigramasCompletos(digramasCompletos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of descifrarParcial method, of class analisisHill.
     */
    @Test
    public void testDescifrarParcial() throws Exception {
        System.out.println("descifrarParcial");
        String txtCifrado = "wumewirbytgaicowbtaazhpjzsjirsltclivwyfsoqtebysbvseuñtuhtrgocywmxevhowozhsbacyzqqdhyoñcfubzegsmaiñwmjoghhdiñfrjsoawrdtnyqnñgivffdarixulvbkocvwmtaffxvjaztxejemsofmzñchqptyjlpuhodmhjsñzhzpfkiewmvusewuggnklfpel mlrpejhymdvwzrwkhswlzqqdhyjzmufxñfqx wvowtavñaxsmxmzchytyxl hnlkwlumwpgewed ñixnudg pnqnjconpel mlp xiiqonposulñvfrbfad biejvoqj";
        txtCifrado = utilsHill.arreglarCadena(txtCifrado);
        double[][] clave = new double[][]{{776.0},{513.0},{416.0}};
        Matrix vector = new Matrix(clave);
        analisisHill instance = new analisisHill();
        ArrayList expResult = new ArrayList<String>();
        expResult.add("en");
        expResult.add("re");
        expResult.add("an");
        expResult.add(" l");
        expResult.add("le");
        expResult.add("es");
        expResult.add("re");
        expResult.add("an");
        boolean result = instance.descifrarParcial(txtCifrado, vector);
        System.out.println(expResult);
        System.out.println(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setPermutation method, of class analisisHill.
     */
    @Test
    public void testSetPermutation() {
        System.out.println("setPermutation");
        int[] perm = new int[]{3,2,0};
        ArrayList<Matrix> posiblesColumnas = new ArrayList<Matrix>();
        double[][] aux0 = new double[][]{{0},{0},{0}};
        posiblesColumnas.add(new Matrix(aux0));
        double[][] aux1 = new double[][]{{1},{1},{1}};
        posiblesColumnas.add(new Matrix(aux1));
        double[][] aux2 = new double[][]{{2},{2},{2}};
        posiblesColumnas.add(new Matrix(aux2));
        double[][] aux3 = new double[][]{{3},{3},{3}};
        posiblesColumnas.add(new Matrix(aux3));
        analisisHill instance = new analisisHill();
        Matrix expResult = null;
        Matrix result = instance.setPermutation(perm, posiblesColumnas);
        utilsHill.printMatrix(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of truncateArray method, of class analisisHill.
     */
    @Test
    public void testTruncateArray() {
        System.out.println("truncateArray");
        int[] array = null;
        int numTrunc = 0;
        analisisHill instance = new analisisHill();
        int[] expResult = null;
        int[] result = instance.truncateArray(array, numTrunc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}