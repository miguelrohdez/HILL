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
     * Test of analisisSupuesto method, of class analisisHill.
     */
    @Test
    public void testAnalisisSupuesto() throws Exception {
        System.out.println("analisisSupuesto");
        analisisHill analisis = new analisisHill();
        String txtCifrado = "";
//        analisis.analisisSupuesto(txtCifrado, "","","");
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
        String hola = "endeeresuelao a s e n r  c p a s m e d traosnttearqueltadocored l i asonantolostunoradiesecial h v r u n i l b onaparonomeinz  f q g j zebecedeeefegeheiejekelemeoepeqeteuevewexeyezabacafagahaiajakamañaoapaqatauavawaxayazoaobocodoeofogohoiojolomopoqotouovoxoyozsasbscsdsfsgsislsmsnsospsqsusvsyncndnenfngnhninjnlnmnqnrnsnunviaibicidifigihijilimioipiqirisitiuivixiyizrbrcrdrgrirjrmrnrprrrsrtrurvrzlblcldlelflgliljlllmlplqlrlsltlulzdadidgdhdjdmdndydrduu uaubucudufuguhuiujulumuoupuqurusutuvuycacccechclcrctcutitltmtntrtum mambmimhmnmompmup pcpepiplpoprptpubabebiblbobrbsbuhahehihohug gagegigoglgrguvavevivovuy yayeyiyoyujajejijojufafefiflfofrfuzazeziztzmznzozuñañeñiñoñux xaxcxexhxixtxoxu ñ w x y";
        System.out.println(hola.length()+", "+hola.length()/2);
        assertEquals(expResult, result);
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
     * Test of comienzoInvalido method, of class analisisHill.
     */
    @Test
    public void testComienzoInvalido() {
        System.out.println("comienzoInvalido");
        String cadena = "";
        analisisHill instance = new analisisHill();
        boolean expResult = false;
        boolean result = instance.comienzoInvalido(cadena);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of longitudInvalida method, of class analisisHill.
     */
    @Test
    public void testLongitudInvalida() {
        System.out.println("longitudInvalida");
        String cadena = "";
        analisisHill instance = new analisisHill();
        boolean expResult = false;
        boolean result = instance.longitudInvalida(cadena);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of repetidasMalas method, of class analisisHill.
     */
    @Test
    public void testRepetidasMalas() {
        System.out.println("repetidasMalas");
        String cadena = "";
        analisisHill instance = new analisisHill();
        boolean expResult = false;
        boolean result = instance.repetidasMalas(cadena);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contienePalabras method, of class analisisHill.
     */
    @Test
    public void testContienePalabras() {
        System.out.println("contienePalabras");
        String[] divisionesCadena = null;
        analisisHill instance = new analisisHill();
        boolean expResult = false;
        boolean result = instance.contienePalabras(divisionesCadena);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of combinacionesInvalidas method, of class analisisHill.
     */
    @Test
    public void testCombinacionesInvalidas() {
        System.out.println("combinacionesInvalidas");
        String cadena = "";
        analisisHill instance = new analisisHill();
        boolean expResult = false;
        boolean result = instance.combinacionesInvalidas(cadena);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of analisisColumnas method, of class analisisHill.
     */
    @Test
    public void testAnalisisColumnas() throws Exception {
        System.out.println("analisisColumnas");
        String txtCifrado = "foueknñ lremjjdsaatxwpñxbdlsmzlfgñyqvmukmiehñ tziczwitiibxsxfcjwpprtdhdrecz nqkñzlksljwayyfñthkfeuuñmnsxfkofñilrftbdksczlfgñyqbqpjnydgrrsqwdddxcbmephapovzvavbexgbdñipgoafmkgfsnotcbtktxi sc xcoavajlxxjxnznchryxrkñurrndhvrxaprkeisfpbñynvo  izdwiñumjpucyjxpupcjtjiztoyhbuñfyahroj yfxh tdhsdzyjpnrxzbwjiogbvvul ovrftx aoxsqjwqfjyhbuñfklnswr";
        txtCifrado = utilsHill.arreglarCadena(txtCifrado);
        analisisHill instance = new analisisHill();
        Matrix expResult = null;
        //ArrayList result = instance.analisisColumnas(txtCifrado);
        //assertEquals(expResult, result);
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
     * Test of generatePerms method, of class analisisHill.
     */
    @Test
    public void testGeneratePerms() {
        System.out.println("generatePerms");
        ArrayList<Matrix> posiblesColumnas = new ArrayList<Matrix>();
        posiblesColumnas.add(new Matrix(1, 1));
        posiblesColumnas.add(new Matrix(1, 1));
        posiblesColumnas.add(new Matrix(1, 1));
        analisisHill instance = new analisisHill();
        ArrayList expResult = null;
        ArrayList<int[]> result = instance.generatePerms(posiblesColumnas);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i)[0]+","+result.get(i)[1]+","+result.get(i)[2]);
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of truncateArrays method, of class analisisHill.
     */
    @Test
    public void testTruncateArrays() {
        System.out.println("truncateArrays");
        ArrayList<int[]> arrays = null;
        int numTrunc = 0;
        analisisHill instance = new analisisHill();
        ArrayList expResult = null;
        ArrayList result = instance.truncateArrays(arrays, numTrunc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateMatrix method, of class analisisHill.
     */
    @Test
    public void testGenerateMatrix() {
        System.out.println("generateMatrix");
        ArrayList<int[]> perms = null;
        ArrayList<Matrix> posiblesColumnas = null;
        analisisHill instance = new analisisHill();
        ArrayList expResult = null;
//        ArrayList result = instance.generateMatrix(perms, posiblesColumnas);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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