/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package behandel.control;

import behandel.entity.Behandeling;
import data.control.DataController;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import praktijk.control.TherapeutManager;

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
        TherapeutManager tm = new TherapeutManager(dc);
        BehandelingManager bm = new BehandelingManager(dc, tm);
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
     * Test of isFysiotherapeutBeschikbaar method
     */
    @Test
    public void testIsFysiotherapeutBeschikbaar() {
        System.out.println("isFysiotherapeutBeschikbaar");
        bm.maakBehandeling(123456789, "AB1", 987654321, bm.parseDateString("10/12/2013 14:15"), bm.parseDateString("10/12/2013 15:30"), Behandeling.Status.Ingepland, "Opmerking test");
        boolean expResult = false;
        boolean result = bm.isFysiotherapeutBeschikbaar(987654321, bm.parseDateString("10/12/2013 14:30"), bm.parseDateString("10/12/2013 15:00"));
        Assert.assertEquals(expResult, result);
    }
    /*
     * Test of isBestaandeTherapeut method
     */
    @Test
    public void testIsBestaandeTherapeut() {
        System.out.println("isBestaandeTherapeut");
        boolean expResult = false;
        boolean result = bm.isBestaandeTherapeut(456789123); 
        Assert.assertEquals(expResult, result);   
    }
    
}