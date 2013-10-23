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