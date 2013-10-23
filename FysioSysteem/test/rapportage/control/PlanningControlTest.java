/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rapportage.control;

import behandel.control.BehandelingManager;
import behandel.entity.Behandeling;
import data.control.DataController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class PlanningControlTest {
    private DefaultTableModel dm;
    private PlanningControl pc;
    
    
    public PlanningControlTest() {
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
                "Bedrijfscode", "TherapeutCode", "Sofinummer", "BehandelCode", "Datum", "Opmerkingen"
            }
        );
        pc = new PlanningControl(dm, bm);
    }
    
    @BeforeClass
    public static void setUpClass() {
        DummyData.setDummyData();
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
     * Test of getGegevens method, of class PlanningControl.
     */
    @Test
    public void testGetGegevens() {
        System.out.println("getGegevens");
        Date beginDatum;
        Date eindDatum;
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        try{
            beginDatum = sf.parse("16-10-2013 10:00");
            eindDatum = sf.parse("16-10-2013 15:00");
            boolean expResult = true;
            boolean result = pc.getGegevens(beginDatum, eindDatum, dm);
            assertEquals(expResult, result);
        } catch(ParseException ex){
            System.out.println("Derp: testGetGegevens(planningControlTest) " + ex.toString());
        }
    }
    
    /**
     * Test of getGegevens method, of class PlanningControl.
     */
    @Test
    public void testGetGegevensFouteBegindatum() {
        System.out.println("getGegevens");
        Date beginDatum;
        Date eindDatum;
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        try{
            beginDatum = sf.parse("24-10-2013 10:00");
            eindDatum = sf.parse("14-10-2013 15:00");
            boolean expResult = false;
            boolean result = pc.getGegevens(beginDatum, eindDatum, dm);
            assertEquals(expResult, result);
        } catch(ParseException ex){
            System.out.println("Derp: testGetGegevens(planningControlTest) " + ex.toString());
        }
    }
    
    /**
     * Test of getGegevens method, of class PlanningControl.
     */
    @Test
    public void testGetGegevensFouteEinddatum() {
        System.out.println("getGegevens");
        Date beginDatum;
        Date eindDatum;
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        try{
            beginDatum = sf.parse("14-10-2013 10:00");
            eindDatum = sf.parse("01-10-2013 15:00");
            boolean expResult = false;
            boolean result = pc.getGegevens(beginDatum, eindDatum, dm);
            assertEquals(expResult, result);
        } catch(ParseException ex){
            System.out.println("Derp: testGetGegevens(planningControlTest) " + ex.toString());
        }
    }

    /**
     * Test of getGegevensVanArrayList method, of class PlanningControl.
     */
    @Test
    public void testGetGegevensVanArrayList() {
        System.out.println("getGegevensVanArrayList");
        Date beginDatum;
        Date eindDatum;
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        try{
            beginDatum = sf.parse("14-10-2013 10:00");
            eindDatum = sf.parse("14-10-2013 15:00");
        
            ArrayList<Behandeling> behandelingen = new ArrayList<>();
            behandelingen.add(new Behandeling(1, 1, "AR1", 352, sf.parse("14-10-2013 11:00"), sf.parse("14-10-2013 12:00"), Behandeling.Status.Ingepland, " "));
            boolean expResult = true;
            boolean result = pc.getGegevensVanArrayList(beginDatum, eindDatum, dm, behandelingen);
            assertEquals(expResult, result);
        } catch(ParseException ex){
            System.out.println("Derp: testGetGegevensVanArrayList(planningControlTest) " + ex.toString());
        }
    }
    
    /**
     * Test of getGegevensVanArrayList method, of class PlanningControl.
     */
    @Test
    public void testGetGegevensVanArrayListFouteBegindatum() {
        System.out.println("getGegevensVanArrayList");
        Date beginDatum;
        Date eindDatum;
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        try{
            beginDatum = sf.parse("24-10-2013 10:00");
            eindDatum = sf.parse("14-10-2013 15:00");
        
            ArrayList<Behandeling> behandelingen = new ArrayList<>();
            behandelingen.add(new Behandeling(1, 1, "AR1", 352, sf.parse("14-10-2013 11:00"), sf.parse("14-10-2013 12:00"), Behandeling.Status.Ingepland, " "));
            boolean expResult = false;
            boolean result = pc.getGegevensVanArrayList(beginDatum, eindDatum, dm, behandelingen);
            assertEquals(expResult, result);
        } catch(ParseException ex){
            System.out.println("Derp: testGetGegevensVanArrayList(planningControlTest) " + ex.toString());
        }
    }
    
    /**
     * Test of getGegevensVanArrayList method, of class PlanningControl.
     */
    @Test
    public void testGetGegevensVanArrayListFouteEinddatum() {
        System.out.println("getGegevensVanArrayList");
        Date beginDatum;
        Date eindDatum;
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        try{
            beginDatum = sf.parse("14-10-2013 10:00");
            eindDatum = sf.parse("01-10-2013 15:00");
        
            ArrayList<Behandeling> behandelingen = new ArrayList<>();
            behandelingen.add(new Behandeling(1, 1, "AR1", 352, sf.parse("14-10-2013 11:00"), sf.parse("14-10-2013 12:00"), Behandeling.Status.Ingepland, " "));
            boolean expResult = false;
            boolean result = pc.getGegevensVanArrayList(beginDatum, eindDatum, dm, behandelingen);
            assertEquals(expResult, result);
        } catch(ParseException ex){
            System.out.println("Derp: testGetGegevensVanArrayList(planningControlTest) " + ex.toString());
        }
    }
}