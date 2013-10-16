/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package praktijk.entity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit Test voor alle methodes van de klasse Test
 * @author Dennis
 */
public class PraktijkTest {
    private Praktijk praktijk;
    
    public PraktijkTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        praktijk = new Praktijk("Het Krakebeentje", "Breda", "1234AB", "1a", 12345678, "NL123456789101112131415", "0761234567", "0781234567");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetNaam() {
        assertEquals("Het Krakebeentje", praktijk.getNaam());
    }

    @Test
    public void testGetPlaats() {
        assertEquals("Breda", praktijk.getPlaats());
    }

    @Test
    public void testGetPostcode() {
        assertEquals("1234AB", praktijk.getPostcode());
    }

    @Test
    public void testGetHuisnummer() {
        assertEquals("1a", praktijk.getHuisnummer());
    }

    @Test
    public void testGetKvk() {
        assertEquals(12345678, praktijk.getKvk());
    }

    @Test
    public void testGetIban() {
        assertEquals("NL123456789101112131415", praktijk.getIban());
    }

    @Test
    public void testGetTelnr() {
        assertEquals("0761234567", praktijk.getTelnr());
    }

    @Test
    public void testGetFaxnr() {
        assertEquals("0781234567", praktijk.getFaxnr());
    }

    @Test
    public void testSetNaam() {
        praktijk.setNaam("De Krakebeentje");
        assertEquals("De Krakebeentje", praktijk.getNaam());
    }

    @Test
    public void testSetPlaats() {
        praktijk.setPlaats("Oosterhout");
        assertEquals("Oosterhout", praktijk.getPlaats());
    }

    @Test
    public void testSetPostcode() {
        praktijk.setPostcode("5678CD");
        assertEquals("5678CD", praktijk.getPostcode());
    }

    @Test
    public void testSetHuisnummer() {
        praktijk.setHuisnummer("1207");
        assertEquals("1207", praktijk.getHuisnummer());
    }

    @Test
    public void testSetIban() {
        praktijk.setIban("UK20212223242526272829");
        assertEquals("UK20212223242526272829", praktijk.getIban());
    }

    @Test
    public void testSetTelnr() {
        praktijk.setTelnr("08001111");
        assertEquals("08001111", praktijk.getTelnr());
    }

    @Test
    public void testSetFaxnr() {
        praktijk.setFaxnr("09091234");
        assertEquals("09091234", praktijk.getFaxnr());
    }
}