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
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Rogier
 */
public class RoosterControlTest {

    private RoosterControl rC;
    private DefaultTableModel model;
    private SimpleDateFormat sF;

    public RoosterControlTest() {

        DataController dC = new DataController();
        TherapeutManager tM = new TherapeutManager(dC);
        BehandelingManager bM = new BehandelingManager(dC, tM);
        model = new DefaultTableModel(
                new Object[][]{
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null}
        },
                new String[]{
            "Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag"
        });
        rC = new RoosterControl(model, bM);
        sF = new SimpleDateFormat("dd/MM/yyyy HH:mm");

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
     * Test of zoekTherapeutBSN method, of class RoosterControl.
     */
    @Test
    public void testZoekTherapeutBSN() {
        System.out.println("zoekTherapeutBSN");
        String comboBoxString = "352 | Rogier Welten";
        int expResult = 352;
        int result = rC.zoekTherapeutBSN(comboBoxString);
        assertEquals(expResult, result);
    }

    /**
     * Test of maakRooster method, of class RoosterControl.
     */
    @Test
    public void testMaakRoosterCorrect() {
        try {
            System.out.println("maakRoosterCorrect");
            Date beginDatum = sF.parse("14/10/2013 7:00");
            Date eindDatum = sF.parse("18/10/2013 20:00");
            String comboBoxString = "352 | Rogier Welten";
            boolean expResult = true;
            boolean result = rC.maakRooster(beginDatum, eindDatum, comboBoxString);
            assertEquals(expResult, result);
        } catch (ParseException ex) {
            System.out.println("Fout testMaakRooster " + ex.toString());
        }

    }

    /**
     * Test of maakRooster method, of class RoosterControl.
     */
    @Test
    public void testMaakRoosterFoutFysioBSN() {
        try {
            System.out.println("maakRoosterFoutFysioBSN");
            Date beginDatum = sF.parse("14/10/2013 7:00");
            Date eindDatum = sF.parse("18/10/2013 20:00");
            String comboBoxString = "112 | Rogier Welten";
            boolean expResult = false;
            boolean result = rC.maakRooster(beginDatum, eindDatum, comboBoxString);
            assertEquals(expResult, result);
        } catch (ParseException ex) {
            System.out.println("Fout testMaakRooster " + ex.toString());
        }

    }
    /**
     * Test of maakRooster method, of class RoosterControl.
     */
    @Test
    public void testMaakRoosterFouteWeek() {
        try {
            System.out.println("maakRoosterFoutFysioBSN");
            Date beginDatum = sF.parse("21/10/2013 7:00");
            Date eindDatum = sF.parse("25/10/2013 20:00");
            String comboBoxString = "352 | Rogier Welten";
            boolean expResult = false;
            boolean result = rC.maakRooster(beginDatum, eindDatum, comboBoxString);
            assertEquals(expResult, result);
        } catch (ParseException ex) {
            System.out.println("Fout testMaakRooster " + ex.toString());
        }

    }
}