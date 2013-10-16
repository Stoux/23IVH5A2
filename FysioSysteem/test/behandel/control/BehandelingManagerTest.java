/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package behandel.control;

import behandel.entity.Behandeling;
import data.control.DataController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import praktijk.control.TherapeutManager;
import praktijk.entity.Therapeut;

/**
 *
 * @author Nigel
 */
public class BehandelingManagerTest {
    private BehandelingManager bm;
    private TherapeutManager tm;
    private DefaultTableModel dm;
    
    public BehandelingManagerTest() {
        DataController dc = new DataController();
        dm = new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Patiënt BSN", "Fysiotherapeut BSN", "Behandelcode", "Datum", "Begin tijd", "Eind tijd", "Status", "Opmerkingen"
            }
        );
        bm = new BehandelingManager(dc, tm);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    
    /*
     * Test of testParseDateString method
     */
    @Test
    public void testParseDateString() {
        System.out.println("getBehandeling");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date datum = new Date();
        try {
            datum = sdf.parse("14/10/2013 15:00");
        } catch (ParseException ex) {
            System.out.println("Fout testParseDateString() " + ex.toString());  
        }
        Date parse = bm.parseDateString("14/10/2013 15:00");
        Assert.assertEquals(datum, parse);  
    }
    
    /**
     * Test of getBehandeling method.
     */
    @Test
    public void testGetBehandeling() {
        System.out.println("getBehandeling");
        Behandeling maak = bm.maakBehandeling(123456789, "AB1", 987654321, bm.parseDateString("10/12/2013 14:15"), bm.parseDateString("10/12/2013 15:30"), Behandeling.Status.Voltooid, "Opmerking test");
        Behandeling get = bm.getBehandeling(1);
        Assert.assertEquals(maak, get);
    }
    
    /**
     * Test of updateBehandeling method.
     */
    @Test
    public void testUpdateBehandeling() {
        System.out.println("updateBehandeling");
        bm.maakBehandeling(123456789, "AB1", 987654321, bm.parseDateString("10/12/2013 14:15"), bm.parseDateString("10/12/2013 15:30"), Behandeling.Status.Voltooid, "Opmerking test");
        Behandeling voor = bm.getBehandeling(1);
        bm.updateBehandeling(1, "CD2", 456987123, bm.parseDateString("10/12/2013 09:30"), bm.parseDateString("10/12/2013 10:15"), Behandeling.Status.Ingepland, "Opmerking nummero 2");
        Behandeling na = bm.getBehandeling(1);
        Assert.assertNotSame(voor, na);
    }
    /**
     * Test of getBehandelingen method
     */
    @Test
    public void testGetBehandelingen() {
        System.out.println("getBehandelingen");
        ArrayList<Behandeling> voor = bm.getBehandelingen();
        bm.maakBehandeling(123456789, "AB1", 987654321, bm.parseDateString("10/12/2013 14:15"), bm.parseDateString("10/12/2013 15:30"), Behandeling.Status.Voltooid, "Opmerking test");
        ArrayList<Behandeling> na = bm.getBehandelingen();
        Assert.assertNotSame(voor, na);
    }
    /**
     * Test of deleteBehandeling method
     */
    @Test
    public void testDeleteBehandeling() {
        System.out.println("deleteBehandeling");
        bm.maakBehandeling(123456789, "AB1", 987654321, bm.parseDateString("10/12/2013 14:15"), bm.parseDateString("10/12/2013 15:30"), Behandeling.Status.Voltooid, "Opmerking test");
        Behandeling voor = bm.getBehandeling(1);
        bm.deleteBehandeling(1);
        Behandeling na = bm.getBehandeling(1);
        Assert.assertNotSame(voor, na);
    }
    /**
     * Test of isFysiotherapeutBeschikbaar method (positief)
     */
    @Test
    public void testIsFysiotherapeutBeschikbaarPositief() {
        System.out.println("isFysiotherapeutBeschikbaarPositief");
        bm.maakBehandeling(123456789, "AB1", 987654321, bm.parseDateString("10/12/2013 15:30"), bm.parseDateString("10/12/2013 16:30"), Behandeling.Status.Ingepland, "Opmerking test");
        boolean expResult = true;
        boolean result = bm.isFysiotherapeutBeschikbaar(987654321, bm.parseDateString("10/12/2013 14:30"), bm.parseDateString("10/12/2013 15:00"));
        Assert.assertEquals(expResult, result);
    }
    
    /**
     * Test of isFysiotherapeutBeschikbaar method (negatief)
     */
    @Test
    public void testIsFysiotherapeutBeschikbaarNegatief() {
        System.out.println("isFysiotherapeutBeschikbaarNegatief");
        bm.maakBehandeling(123456789, "AB1", 987654321, bm.parseDateString("10/12/2013 14:15"), bm.parseDateString("10/12/2013 15:30"), Behandeling.Status.Ingepland, "Opmerking test");
        boolean expResult = false;
        boolean result = bm.isFysiotherapeutBeschikbaar(987654321, bm.parseDateString("10/12/2013 14:30"), bm.parseDateString("10/12/2013 15:00"));
        Assert.assertEquals(expResult, result);
    }
    
    /*
     * Test of isBestaandeTherapeut method (positief)
     */
    @Test
    public void testIsBestaandeTherapeutPositief() {
        System.out.println("isBestaandeTherapeutPositief");
        tm.voegToe(new Therapeut("Kees", "van", "Voort", bm.parseDateString("19/08/1978"), Therapeut.Geslacht.Mannelijk, 456789123, "5964AB", "103", "010390349", 8964745));
        boolean expResult = true;
        boolean result = bm.isBestaandeTherapeut(456789123); 
        Assert.assertEquals(expResult, result);   
    }
    
    /*
     * Test of isBestaandeTherapeut method
     */
    @Test
    public void testIsBestaandeTherapeutNegatief() {
        System.out.println("isBestaandeTherapeutNegatief");
        tm.voegToe(new Therapeut("Henk", "de", "Koe", bm.parseDateString("07/10/1963"), Therapeut.Geslacht.Mannelijk, 111222333, "2651CD", "2", "01016541", 8964745));
        boolean expResult = false;
        boolean result = bm.isBestaandeTherapeut(456789123); 
        Assert.assertEquals(expResult, result);   
    }
}