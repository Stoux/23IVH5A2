package rapportage.control;

import behandel.entity.BehandelGegevens;
import behandel.entity.Behandeling;
import data.control.DataController;
import data.entity.Folder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import praktijk.entity.Therapeut;

/**
 *
 * @author Jan
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({rapportage.control.PlanningControlTest.class, rapportage.control.CumulatiefControlTest.class, rapportage.control.RoosterControlTest.class})
public class ControlSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
        //Maak managers
        DataController dC = new DataController();

        HashMap<Integer, String> hP = new HashMap<>();
        ArrayList<Therapeut> tP = new ArrayList<>();
        ArrayList<BehandelGegevens> gg = new ArrayList<>();
        SimpleDateFormat sF = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        ArrayList<Behandeling> bb = new ArrayList<>();

        try {
            tP.add(new Therapeut("Rogier", "", "Welten", sF.parse("23/11/1993 12:00"), Therapeut.Geslacht.Mannelijk, 352, "HI", "LOL", "NEE", 123456789));
            hP.put(441, "Bert van der Ven");
            bb.add(new Behandeling(200000000, 441, "AR1", 352, sF.parse("16/10/2013 14:00"), sF.parse("16/10/2013 15:00"), Behandeling.Status.Voltooid, null));
        }
        catch (ParseException e) {}

        System.out.println("klant wegschrijven");
        dC.saveObject(Folder.FTPFacturatie, "klanten", hP);

        for (Therapeut t : tP) {
            System.out.println("Therapeut wegschrijven");
            dC.saveObject(Folder.Therapeuten, String.valueOf(t.getBsn()), t);
        }
        for (Behandeling b : bb) {
            System.out.println("behandeling wegschrijven");
            dC.saveObject(Folder.Behandelingen, String.valueOf(b.getBehandelingsID()), b);
        }
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}