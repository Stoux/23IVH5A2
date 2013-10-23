/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package behandel.control;

import behandel.entity.BehandelGegevens;
import behandel.entity.Behandeling;
import behandel.entity.Behandeling.Status;
import data.control.DataController;
import data.entity.Folder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
    private DataController dc;
    private DefaultTableModel dm;
    private HashMap<String, BehandelGegevens> stamGegevens;
    private SimpleDateFormat datumTijd;
    
    public BehandelingManagerTest() {
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
        DataController dc = new DataController();
        tm = new TherapeutManager(dc);
        bm = new BehandelingManager(dc, tm);
        bm.getTherapeuten().clear();
        bm.getBehandelingen().clear();
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
        Behandeling get = bm.getBehandeling(maak.getBehandelingsID());
        Assert.assertEquals(maak, get);
    }
    
    /**
     * Test of updateBehandeling method.
     */
    @Test
    public void testUpdateBehandeling() {
        System.out.println("updateBehandeling");
        //Maak behandeling
        Behandeling a = bm.maakBehandeling(123456789, "AB1", 987654321, bm.parseDateString("10/12/2013 14:15"), bm.parseDateString("10/12/2013 15:30"), Behandeling.Status.Voltooid, "Opmerking test");
        //Get alle info uit de behandeling
        int id = a.getBehandelingsID();
        int bsn = a.getBurgerServiceNummer();
        String code = a.getBehandelingscode();
        int fysio = a.getFysiotherapeutBSN();
        Date begin = a.getBegintijd();
        Date eind = a.getEindtijd();
        Status status = a.getStatus();
        String opmerking = a.getOpmerking();
        //Update behandeling
        boolean succes = bm.updateBehandeling(a.getBehandelingsID(), "CD2", 456987123, bm.parseDateString("10/12/2013 09:30"), bm.parseDateString("10/12/2013 10:15"), Behandeling.Status.Ingepland, "Opmerking nummero 2");
        Assert.assertTrue(succes);
        //Compare
        Assert.assertTrue(a.getBehandelingsID() == id);
        Assert.assertTrue(a.getBurgerServiceNummer() == bsn);
        Assert.assertTrue(a.getBehandelingscode() != code);
        Assert.assertTrue(a.getFysiotherapeutBSN() != fysio);
        Assert.assertTrue(!a.getBegintijd().equals(begin));
        Assert.assertTrue(!a.getEindtijd().equals(eind));
        Assert.assertTrue(!a.getStatus().equals(status));
        Assert.assertTrue(!a.getOpmerking().equals(opmerking));
    }
    /**
     * Test of getBehandelingen method
     */
    @Test
    public void testGetBehandelingen() {
        System.out.println("getBehandelingen");
        ArrayList<Behandeling> voor = bm.getBehandelingen();
        Behandeling a = bm.maakBehandeling(123456789, "AB1", 987654321, bm.parseDateString("10/12/2013 14:15"), bm.parseDateString("10/12/2013 15:30"), Behandeling.Status.Voltooid, "Opmerking test");
        voor.add(a);
        ArrayList<Behandeling> na = bm.getBehandelingen();
        Assert.assertTrue("Arrays zijn niet zelfde lengte", voor.size() == na.size());
    }
    
    /**
     * Test of deleteBehandeling method
     */
    @Test
    public void testDeleteBehandeling() {
        System.out.println("deleteBehandeling");
        Behandeling a = bm.maakBehandeling(123456789, "AB1", 987654321, bm.parseDateString("10/12/2013 14:15"), bm.parseDateString("10/12/2013 15:30"), Behandeling.Status.Voltooid, "Opmerking test");
        Behandeling voor = bm.getBehandeling(a.getBehandelingsID());
        bm.deleteBehandeling(a.getBehandelingsID());
        Behandeling na = bm.getBehandeling(a.getBehandelingsID());
        Assert.assertNotSame(voor, na);
    }
    /**
     * Test of isFysiotherapeutBeschikbaar method (positief)
     */
    @Test
    public void testIsFysiotherapeutBeschikbaarPositief() {
        System.out.println("isFysiotherapeutBeschikbaarPositief");
        //Voeg therapeut toe
        tm.voegToe(new Therapeut("Kees", "van", "Voort", null, Therapeut.Geslacht.Mannelijk, 123123123, null, null, null, 1));
        //Voeg nieuwe behandeling aan de therapeut toe
        bm.maakBehandeling(123456789, "AB1", 123123123, bm.parseDateString("10/12/2013 15:30"), bm.parseDateString("10/12/2013 16:30"), Behandeling.Status.Ingepland, "Opmerking test");
        //Controleer of die beschikbaar is
        boolean result = bm.isFysiotherapeutBeschikbaar(123123123, bm.parseDateString("10/12/2013 14:30"), bm.parseDateString("10/12/2013 15:00"));
        Assert.assertEquals(true, result);
    }
    
    /**
     * Test of isFysiotherapeutBeschikbaar method (negatief)
     */
    @Test
    public void testIsFysiotherapeutBeschikbaarNegatief() {
        System.out.println("isFysiotherapeutBeschikbaarNegatief");
        tm.voegToe(new Therapeut("Kees", "van", "Voort", null, Therapeut.Geslacht.Mannelijk, 123123123, null, null, null, 1));
        bm.maakBehandeling(123456789, "AB1", 123123123, bm.parseDateString("10/12/2013 14:15"), bm.parseDateString("10/12/2013 15:30"), Behandeling.Status.Ingepland, "Opmerking test");
        boolean result = bm.isFysiotherapeutBeschikbaar(123123123, bm.parseDateString("10/12/2013 14:30"), bm.parseDateString("10/12/2013 15:00"));
        Assert.assertEquals(false, result);
    }
    
    /*
     * Test of isBestaandeTherapeut method (positief)
     */
    @Test
    public void testIsBestaandeTherapeutPositief() {
        System.out.println("isBestaandeTherapeutPositief");
        tm.voegToe(new Therapeut("Kees", "van", "Voort", null, Therapeut.Geslacht.Mannelijk, 456789123, "5964AB", "103", "010390349", 8964745));
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
    /*
     * Test of getBehandelGegevens method
     */
    @Test
    public void testGetBehandelGegevens() {
        DataController dc = new DataController();
        stamGegevens = new HashMap<>();
        String[][] gegevens = new String[][] {
            new String[]{"AF1", "Algemene fysiotherapie (45 minuten)", "De fysiotherapeut helpt mensen die niet vooruit komen in het dagelijks leven"},
            };
            for (String[] stam : gegevens) { //Laad de stam gegevens
                BehandelGegevens bGegevens = new BehandelGegevens(stam[0], stam[1], stam[2]); //Maak de entity aan
                stamGegevens.put(stam[0], bGegevens); //Stop die in de hashmap
                dc.saveObject(Folder.BehandelingGegevens, stam[0], bGegevens); //Sla hem ook op om later te gebruiken
            }   
        
            BehandelGegevens a = new BehandelGegevens("AF1", "Algemene fysiotherapie (45 minuten)", "De fysiotherapeut helpt mensen die niet vooruit komen in het dagelijks leven");
            BehandelGegevens b = bm.getBehandelGegevens("AF1");

            Assert.assertEquals(a.getBehandelingscode(), b.getBehandelingscode());
            Assert.assertEquals(a.getNaam(), b.getNaam());
            Assert.assertEquals(a.getOmschrijving(), b.getOmschrijving());
    }
    
    @Test
    public void testIsPatientenOpgehaald() {
        boolean result = bm.isPatientenOpgehaald();
        boolean expResult = true;

        Assert.assertEquals(expResult, result);
    }

    @Test
    public void testSynchroniseerBehandelingen() {
        boolean expResult = true; // Alleen testen als er verbinding is met FTP, anders is result false
        boolean result = bm.synchroniseerBehandelingen();

        Assert.assertEquals(expResult, result);
    }

     /*
     * Test of getBehandelCodes method
     */
    @Test
    public void testGetBehandelCodes() {
        ArrayList<String> behandelcodes = new ArrayList<String>(); 
        behandelcodes.add("AF1");
        behandelcodes.add("AF2");
        behandelcodes.add("BF1");
        behandelcodes.add("BF2");
        behandelcodes.add("KF1");
        behandelcodes.add("KF2");
        behandelcodes.add("OF1");
        behandelcodes.add("OF2");
        behandelcodes.add("PF1");
        behandelcodes.add("PF2");
        behandelcodes.add("AR1");
        behandelcodes.add("AR2");
        behandelcodes.add("GF1");
        behandelcodes.add("GF2");
        behandelcodes.add("MF1");
        behandelcodes.add("MF2");
        behandelcodes.add("OR1");
        behandelcodes.add("OR2");
        behandelcodes.add("SF1");
        behandelcodes.add("SF2");
        
        for (String codeOpgehaald : bm.getBehandelCodes()) {
            boolean match = false;
            for (String codeToegevoegd : behandelcodes) {
                if (codeOpgehaald.equals(codeToegevoegd))
                    match = true;
            }
            Assert.assertTrue(match);
        }
    }
    /**
     * Test of formatDatum method
     */
    @Test
    public void testFormatDatum() {
        datumTijd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        try {
            date = datumTijd.parse("20/01/2008 15:00");
        } catch (ParseException ex) {
            System.out.println("Fout testParseDateString() " + ex.toString());  
        }
        String formatDate = bm.formatDatum(date);
        Assert.assertEquals("20/01/2008", formatDate);
    }
    /**
     * Test of formatTijd method
     */
    @Test
    public void testFormatTijd() {
        datumTijd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date time = new Date();
        try {
            time = datumTijd.parse("20/01/2008 15:00");
        } catch (ParseException ex) {
            System.out.println("Fout testParseDateString() " + ex.toString());  
        }
        String formatTime = bm.formatTijd(time);
        Assert.assertEquals("15:00", formatTime);
    }
    /**
     * Test of getDatumFormat method
     */
    @Test
    public void testGetDatumFormat() {
        SimpleDateFormat expResult = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat result = bm.getDatumFormat();
        
        Assert.assertEquals(expResult, result);
    }
}