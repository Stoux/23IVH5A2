/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package praktijk.control;

import data.control.DataController;
import java.util.ArrayList;
import java.util.Date;
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
public class PraktijkManagerTest {
    private static DataController dataController;
    private PraktijkManager manager;
    private Praktijk testPraktijk;
    
    public PraktijkManagerTest() {
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
        manager = new PraktijkManager(dataController);
        testPraktijk = new Praktijk("Het Krakebeentje", "Breda", "1234AB", "1a", 12345678, "NL123456789101112131415", "0761234567", "0781234567");
        for (int i = (manager.getPraktijken().size() - 1); i >= 0; i--) {
            manager.verwijder(i);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testVoegToeEnGetPraktijken() {
        assertTrue(manager.getPraktijken().isEmpty());
        assertTrue(manager.voegToe(testPraktijk));
        ArrayList<Praktijk> praktijken = manager.getPraktijken();
        assertEquals(1, praktijken.size());
        
        boolean gevonden = false;
        for (Praktijk p : praktijken) {
            //kvk nummer is uniek
            if (p.getKvk() == 12345678) {
                gevonden = true;
                break;
            }
        }
        assertTrue(gevonden);
    }
    
    @Test
    public void testGetPraktijk() {
        assertTrue(manager.voegToe(testPraktijk));
        assertEquals(12345678, manager.getPraktijk(0).getKvk());
    }

    @Test
    public void testWijzig() {
        assertTrue(manager.voegToe(testPraktijk));
        testPraktijk.setNaam("Nieuwe naam van praktijk");
        assertTrue(manager.wijzig(0, testPraktijk));
        assertEquals("Nieuwe naam van praktijk", manager.getPraktijk(0).getNaam());
    }

    @Test
    public void testVerwijder() {
        assertTrue(manager.voegToe(testPraktijk));
        
        //tijdelijke therapeuten
        TherapeutManager therapeutManager = new TherapeutManager(dataController);
        
        for (int i = (therapeutManager.getTherapeuten().size() - 1); i >= 0; i--) {
            therapeutManager.verwijder(i);
        }
        
        Therapeut testTherapeut = new Therapeut("Henkie", "", "Stamp", new Date(), Therapeut.Geslacht.Mannelijk, 234567890, "1234AB", "1a", "07612345678", 12345678);
        assertTrue(therapeutManager.voegToe(testTherapeut));
        testTherapeut = new Therapeut("Jan-Pieter", "de", "Klerk", new Date(), Therapeut.Geslacht.Mannelijk, 345678901, "1234AB", "1a", "07612345678", 87654321);
        assertTrue(therapeutManager.voegToe(testTherapeut));
        testTherapeut = new Therapeut("Josef", "", "Raaymakers", new Date(), Therapeut.Geslacht.Mannelijk, 456789012, "1234AB", "1a", "07612345678", 12345678);
        assertTrue(therapeutManager.voegToe(testTherapeut));
        
        assertEquals(1, manager.getPraktijken().size());
        assertTrue(manager.verwijder(0));
        assertTrue(manager.getPraktijken().isEmpty());

        //opnieuw ophalen van gegevens
        therapeutManager = new TherapeutManager(dataController);
        //kijkt of de KVK-nummers bij therapeuten inderdaad op 0 staan
        assertEquals(0, therapeutManager.getTherapeut(0).getPraktijkKvk());
        assertEquals(87654321, therapeutManager.getTherapeut(1).getPraktijkKvk());
        assertEquals(0, therapeutManager.getTherapeut(2).getPraktijkKvk());
    }

    @Test
    public void testKvkBestaat() {
        assertFalse(manager.kvkBestaat(12345678));
        assertTrue(manager.voegToe(testPraktijk));
        assertTrue(manager.kvkBestaat(12345678));
    }

    @Test
    public void testZoekPraktijkNaam() {
        assertTrue(manager.voegToe(testPraktijk));
        
        testPraktijk = new Praktijk("Prakkie", "Oosterhout", "1234AB", "1a", 23456789, "NL123456789101112131415", "0761234567", "0781234567");
        assertTrue(manager.voegToe(testPraktijk));
        
        testPraktijk = new Praktijk("Gezochte Praktijk 1", "Oosterhout", "1234AB", "1a", 34567890, "NL123456789101112131415", "0761234567", "0781234567");
        assertTrue(manager.voegToe(testPraktijk));
        
        testPraktijk = new Praktijk("Gezochte Praktijk twee", "Geertruidenberg", "1234AB", "1a", 45678901, "NL123456789101112131415", "0761234567", "0781234567");
        assertTrue(manager.voegToe(testPraktijk));
        
        ArrayList<Praktijk> praktijken = manager.zoekPraktijk("zocht praktijk", true);
        assertEquals(2, praktijken.size());
        assertEquals(34567890, praktijken.get(0).getKvk());
        assertEquals(45678901, praktijken.get(1).getKvk());
    }
    
    @Test
    public void testZoekPraktijkPlaats() {
        assertTrue(manager.voegToe(testPraktijk));
        
        testPraktijk = new Praktijk("Prakkie", "Oosterhout", "1234AB", "1a", 23456789, "NL123456789101112131415", "0761234567", "0781234567");
        assertTrue(manager.voegToe(testPraktijk));
        
        testPraktijk = new Praktijk("Gezochte Praktijk 1", "Oosterhout", "1234AB", "1a", 34567890, "NL123456789101112131415", "0761234567", "0781234567");
        assertTrue(manager.voegToe(testPraktijk));
        
        testPraktijk = new Praktijk("Gezochte Praktijk twee", "Geertruidenberg", "1234AB", "1a", 45678901, "NL123456789101112131415", "0761234567", "0781234567");
        assertTrue(manager.voegToe(testPraktijk));
        
        ArrayList<Praktijk> praktijken = manager.zoekPraktijk("Oosterhout", false);
        assertEquals(2, praktijken.size());
        assertEquals(23456789, praktijken.get(0).getKvk());
        assertEquals(34567890, praktijken.get(1).getKvk());
    }
}