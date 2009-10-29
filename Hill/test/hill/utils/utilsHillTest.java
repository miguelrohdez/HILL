/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hill.utils;

import Jama.Matrix;
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
     * Test of digramasRepetidos method, of class utilsHill.
     */
    @Test
    public void testDigramasRepetidos() {
        System.out.println("digramasRepetidos");
//        String cadena = "zs gpznpppyjvdxyopñvoeywbtuxvietaczkqhoipdbenbyrqbhtwqmnzkcztacsmbzzmvndiprjjnmpbzargbqqlbñnyxqzsqzbñgjtqowzdndxvpdtzeenndtqqjñfxbzcbrmn naiq oeovpujjgjdwpxkpeoppvt iymiykomawtwcsclymfo bfhoxzsffdknjjaafidñvqrynxfookcebbskd xoc genpfbiwvpuyxi jjckpynigbdm ydlqzbzgcj gldvy xñxszxcm urfcphdamijprbobjclo dasmjzvhh";
        String cadena = "twqmsctgubopydoxbzwn bitdxvammcrrkrjdvjrdknadpuylwmxpñlwbqjeqgnsfprwfpubaeñsawsjwyht wzzzpir stpzsmtwuhisaqojhvjyvzsioycxljzzivññrnn vvmvlovhupogbyuki wzzzpir stpjdlxtptkvzaqpbkqrñojvuneispbboihpbcrmfmjatihprgpovegiyhysfxphadcmlstñbxcsqzndaugññtupyeygqoypbemmukpvrsbwptancytdev kptiimbqckyuxaxhthf dsxcutlaeszpxrhñ oyuknhyzpdxvammyzvnnl";
        LinkedList expResult = null;
        LinkedList result = utilsHill.digramasRepetidos(cadena);
        Integer num = 4;
        for(Object aux:result){
            System.out.println("result:"+aux);
        }
        assertEquals(expResult, result);
    }

}