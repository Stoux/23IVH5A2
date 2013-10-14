/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rapportage.control;

import behandel.control.BehandelingManager;
import data.control.DataController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import praktijk.control.TherapeutManager;

/**
 *
 * @author Jan
 */
public class CumulatiefControlTest {
    private CumulatiefControl cm;
    private DefaultTableModel dm;
    
    public CumulatiefControlTest() {
        DataController dc = new DataController();
        TherapeutManager tp = new TherapeutManager(dc);
        BehandelingManager bm = new BehandelingManager(dc, tp);
        dm = new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Bedrijfscode", "Therapeutcode", "Behandelingscode", "Aantal Behandelingen", "Datum", "Opmerkingen"
            }
        );
        cm = new CumulatiefControl(dm , bm);
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
     * Test of getGegevens method, of class CumulatiefControl.
     */
    @Test
    public void testGetGegevens() {
        System.out.println("getGegevens");
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        Date beginDatum = new Date();
        Date eindDatum = new Date();
        try {
            beginDatum = sf.parse("14-10-2013 10:00");
            eindDatum = sf.parse("14-10-2013 15:00");
        } catch (ParseException ex) {
            System.out.println("Derp: testGetGegevens(CumulatiefControlTest) " + ex.toString());
        }
        boolean expResult = true;
        boolean result = cm.getGegevens(beginDatum, eindDatum, dm);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getGegevens method, of class CumulatiefControl.
     */
    @Test
    public void testGetGegevensFouteBeginDatum() {
        System.out.println("getGegevens");
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        Date beginDatum = new Date();
        Date eindDatum = new Date();
        try {
            beginDatum = sf.parse("25-10-2013 10:00");
            eindDatum = sf.parse("14-10-2013 15:00");
        } catch (ParseException ex) {
            System.out.println("Derp: testGetGegevens(CumulatiefControlTest) " + ex.toString());
        }
        boolean expResult = false;
        boolean result = cm.getGegevens(beginDatum, eindDatum, dm);
        assertEquals(expResult, result);
    }
    /**
     * Test of getGegevens method, of class CumulatiefControl.
     */
    @Test
    public void testGetGegevensFouteEindDatum() {
        System.out.println("getGegevens");
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        Date beginDatum = new Date();
        Date eindDatum = new Date();
        try {
            beginDatum = sf.parse("14-10-2013 10:00");
            eindDatum = sf.parse("01-10-2013 15:00");
        } catch (ParseException ex) {
            System.out.println("Derp: testGetGegevens(CumulatiefControlTest) " + ex.toString());
        }
        boolean expResult = false;
        boolean result = cm.getGegevens(beginDatum, eindDatum, dm);
        assertEquals(expResult, result);
    }
}