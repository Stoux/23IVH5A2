/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package praktijk.control;

import data.control.DataController;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import praktijk.entity.Praktijk;
import praktijk.entity.Therapeut;

/**
 *
 * @author Dennis
 */
public class TherapeutManagerTest {
    private static DataController dataController;
    private TherapeutManager manager;
    private Therapeut testTherapeut;
    
    public TherapeutManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        dataController = new DataController();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        manager = new TherapeutManager(dataController);
        testTherapeut = new Therapeut("Pieter", "de", "Bont", new Date(), Therapeut.Geslacht.Mannelijk, 123456789, "1234AB", "1a", "07612345678", 12345678);
        for (int i = (manager.getTherapeuten().size() - 1); i >= 0; i--) {
            manager.verwijder(i);
        }
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetTherapeuten() {
    }

    @Test
    public void testVoegToeEnGetTherapeuten() {
        assertTrue(manager.getTherapeuten().isEmpty());
        assertTrue(manager.voegToe(testTherapeut));
        ArrayList<Therapeut> therapeuten = manager.getTherapeuten();
        assertEquals(1, therapeuten.size());
        
        boolean gevonden = false;
        for (Therapeut p : therapeuten) {
            //BSN is uniek
            if (p.getBsn()== 123456789) {
                gevonden = true;
                break;
            }
        }
        assertTrue(gevonden);
    }
    
    @Test
    public void testGetTherapeut() {
        assertTrue(manager.voegToe(testTherapeut));
        assertEquals(123456789, manager.getTherapeut(0).getBsn());
    }

    @Test
    public void testWijzig() {
        assertTrue(manager.voegToe(testTherapeut));
        testTherapeut.setVoornaam("Dennis");
        assertTrue(manager.wijzig(0, testTherapeut));
        assertEquals("Dennis", manager.getTherapeut(0).getVoornaam());
    }

    @Test
    public void testVerwijder() {
        assertTrue(manager.voegToe(testTherapeut));
        assertEquals(1, manager.getTherapeuten().size());
        assertTrue(manager.verwijder(0));
        assertTrue(manager.getTherapeuten().isEmpty());
    }

    @Test
    public void testBsnBestaat() {
        assertFalse(manager.bsnBestaat(123456789));
        assertTrue(manager.voegToe(testTherapeut));
        assertTrue(manager.bsnBestaat(123456789));
    }

    @Test
    public void testZoekTherapeut() {
        assertTrue(manager.voegToe(testTherapeut));
        
        testTherapeut = new Therapeut("Henkie", "", "Stamp", new Date(), Therapeut.Geslacht.Mannelijk, 234567890, "1234AB", "1a", "07612345678", 12345678);
        assertTrue(manager.voegToe(testTherapeut));
        
        testTherapeut = new Therapeut("Jan-Pieter", "de", "Klerk", new Date(), Therapeut.Geslacht.Mannelijk, 345678901, "1234AB", "1a", "07612345678", 12345678);
        assertTrue(manager.voegToe(testTherapeut));
        
        testTherapeut = new Therapeut("Josef", "", "Raaymakers", new Date(), Therapeut.Geslacht.Mannelijk, 456789012, "1234AB", "1a", "07612345678", 12345678);
        assertTrue(manager.voegToe(testTherapeut));
        
        ArrayList<Therapeut> therapeuten = manager.zoekTherapeut("Pieter");
        assertEquals(2, therapeuten.size());
        assertEquals(123456789, therapeuten.get(0).getBsn());
        assertEquals(345678901, therapeuten.get(1).getBsn());
    }

    @Test
    public void testGetPraktijkNamen() {
        PraktijkManager praktijkManager = new PraktijkManager(dataController);
        assertTrue(praktijkManager.getPraktijken().isEmpty());
        
        Praktijk testPraktijk = new Praktijk("Prakkie", "Oosterhout", "1234AB", "1a", 23456789, "NL123456789101112131415", "0761234567", "0781234567");
        assertTrue(praktijkManager.voegToe(testPraktijk));
        testPraktijk = new Praktijk("Praktijk 1", "Oosterhout", "1234AB", "1a", 34567890, "NL123456789101112131415", "0761234567", "0781234567");
        assertTrue(praktijkManager.voegToe(testPraktijk));
        
        HashMap<Long, String> praktijkNamen = manager.getPraktijkNamen();
        
        assertTrue(praktijkNamen.containsKey(23456789l));
        assertEquals("Prakkie", praktijkNamen.get(23456789l));
        assertTrue(praktijkNamen.containsKey(34567890l));
        assertEquals("Praktijk 1", praktijkNamen.get(34567890l));
    }
}