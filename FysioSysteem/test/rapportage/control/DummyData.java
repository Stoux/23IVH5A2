package rapportage.control;

import behandel.entity.Behandeling;
import data.control.DataController;
import data.entity.Folder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import praktijk.entity.Therapeut;

/**
 *
 * @author Dennis
 */
public class DummyData {
    /**
     * Set de Dummy Data, benodigt voor de testen in het subsysteem rapportage
     */
    public static void setDummyData() {
        DataController dC = new DataController();
        SimpleDateFormat sF = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        try {
            Therapeut therapeut = new Therapeut("Rogier", "", "Welten", sF.parse("23/11/1993 12:00"), Therapeut.Geslacht.Mannelijk, 352, "HI", "LOL", "NEE", 123456789);
            dC.saveObject(Folder.Therapeuten, String.valueOf(therapeut.getBsn()), therapeut);
            
            HashMap<Integer, String> hP = new HashMap<>();
            hP.put(441, "Bert van der Ven");
            dC.saveObject(Folder.FTPFacturatie, "klanten", hP);
            
            Behandeling behandeling = new Behandeling(200000000, 441, "AR1", 352, sF.parse("16/10/2013 14:00"), sF.parse("16/10/2013 15:00"), Behandeling.Status.Voltooid, null);
            dC.saveObject(Folder.Behandelingen, String.valueOf(behandeling.getBehandelingsID()), behandeling);
        }
        catch (ParseException e) {}
    }
}
