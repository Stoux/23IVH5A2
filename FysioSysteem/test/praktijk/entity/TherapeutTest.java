/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package praktijk.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
public class TherapeutTest {
    private Therapeut therapeut;
    private Date geboortedatum;
    
    public TherapeutTest() {
        try {
            geboortedatum = new SimpleDateFormat("dd-MM-yyyy").parse("15-06-1989");
        } catch (ParseException ex) {}
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        therapeut = new Therapeut("Peter", "van der", "Josef", geboortedatum, Therapeut.Geslacht.Mannelijk, 123123123, "1234AB", "1a", "0612345678", 12345678);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetVoornaam() {
         assertEquals("Peter", therapeut.getVoornaam());
    }

    @Test
    public void testGetTussenvoegsel() {
        assertEquals("van der", therapeut.getTussenvoegsel());
    }

    @Test
    public void testGetAchternaam() {
        assertEquals("Josef", therapeut.getAchternaam());
    }

    @Test
    public void testGetVolledigeNaam() {
        assertEquals("Josef, Peter van der", therapeut.getVolledigeNaam());
    }

    @Test
    public void testGetGeboortedatum() {
        assertEquals(geboortedatum, therapeut.getGeboortedatum());
    }

    @Test
    public void testGetGeboortedatumFormatted() {
        assertEquals("15-06-1989", therapeut.getGeboortedatumFormatted());
    }

    @Test
    public void testGetGeslacht() {
        assertEquals(Therapeut.Geslacht.Mannelijk, therapeut.getGeslacht());
    }

    @Test
    public void testGetBsn() {
        assertEquals(123123123, therapeut.getBsn());
    }

    @Test
    public void testGetPostcode() {
        assertEquals("1234AB", therapeut.getPostcode());
    }

    @Test
    public void testGetHuisnummer() {
        assertEquals("1a", therapeut.getHuisnummer());
    }

    @Test
    public void testGetTelnr() {
        assertEquals("0612345678", therapeut.getTelnr());
    }

    @Test
    public void testGetPraktijkKvk() {
        assertEquals(12345678, therapeut.getPraktijkKvk());
    }

    @Test
    public void testSetVoorNaam() {
        therapeut.setVoornaam("Henk");
        assertEquals("Henk", therapeut.getVoornaam());
    }

    @Test
    public void testSetTussenvoegsel() {
        therapeut.setTussenvoegsel("");
        assertEquals("", therapeut.getTussenvoegsel());
    }

    @Test
    public void testSetAchternaam() {
        therapeut.setAchternaam("Avans");
        assertEquals("Avans", therapeut.getAchternaam());
    }

    @Test
    public void testSetGeboortedatum() {
        try {
            therapeut.setGeboortedatum(new SimpleDateFormat("dd-MM-yyyy").parse("14-07-1988"));
            assertEquals(new SimpleDateFormat("dd-MM-yyyy").parse("14-07-1988"), therapeut.getGeboortedatum());
        } catch (ParseException ex) {}
    }

    @Test
    public void testSetGeslacht() {
        therapeut.setGeslacht(Therapeut.Geslacht.Vrouwelijk);
        assertEquals(Therapeut.Geslacht.Vrouwelijk, therapeut.getGeslacht());
    }

    @Test
    public void testSetPostcode() {
        therapeut.setPostcode("9876ZY");
        assertEquals("9876ZY", therapeut.getPostcode());
    }

    @Test
    public void testSetHuisnummer() {
        therapeut.setHuisnummer("12");
        assertEquals("12", therapeut.getHuisnummer());
    }

    @Test
    public void testSetTelnr() {
        therapeut.setTelnr("0687654321");
        assertEquals("0687654321", therapeut.getTelnr());
    }

    @Test
    public void testSetPraktijkKvk() {
        therapeut.setPraktijkKvk(87654321);
        assertEquals(87654321, therapeut.getPraktijkKvk());
    }
}