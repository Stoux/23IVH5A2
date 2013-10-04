package behandel.control;

import behandel.entity.BehandelGegevens;
import behandel.entity.Behandeling;
import data.control.DataController;
import data.entity.Folder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import praktijk.entity.Therapeut;

/**
 *
 * @author Leon Stam
 */
public class BehandelingManager {
    
    /**
     * De DataController
     */
    private DataController dataController;
    
    /**
     * Behandel gegevens
     * stamGegevens - HashMap met alle stamgegevens. Key: behandelingscode | value: de gegevens
     * behandelingen - ArrayList met alle behandelingen
     */
    private HashMap<String, BehandelGegevens> stamGegevens;
    private ArrayList<Behandeling> behandelingen;
    
    /**
     * Patient gegevens
     * patienten - Hashmap met alle patienten. Key: Sofinummer | Value: Patientnaam
     * aangepastePatienten - HashSet met de burgerservicenummers van patienten van wie er een behandeling is aangepast/toegevoegd/verwijderd
     * patientenOpgehaald - Boolean of het systeem succesvol de patienten heeft opgehaald
     */
    private HashMap<Integer, String> patienten;
    private HashSet<Integer> aangepastePatienten;
    private boolean patientenOpgehaald;
    
    /**
     * Format van de datum
     * tijdFormat - Tijd + Datum
     */
    private SimpleDateFormat tijdFormat;
    
    /**
     * Constructor van de manager
     * @param dataController de DataController
     */
    public BehandelingManager(DataController dataController) {
        this.dataController = dataController;
        
        //Maak de lijsten
        stamGegevens = new HashMap<>();
        behandelingen = new ArrayList<>();
        patienten = new HashMap<>();
        aangepastePatienten = new HashSet<>();
        
        //Maak de DateFormat
        // TODO: Geef de DateFormat een patroon mee
        tijdFormat = new SimpleDateFormat(null);
        
        //Haal de opgeslagen gegevens op
        haalPatientenHashMapOp();
        haalOpgeslagenStamGegevensOp();
        haalOpgeslagenBehandelingenOp();
        
        //Als de gegevens succesvol zijn opgehaald check dan de integriteit
        if (patientenOpgehaald) checkDataIntegriteit();
    }
    
    /**
     * Haal de opgeslagen patienten op (van het andere systeem)
     */
    private void haalPatientenHashMapOp() {
        HashMap object = (HashMap) dataController.laadObject(Folder.FTPFacturatie, "klanten", HashMap.class); //Laad het object
        if (object == null) { //Als die null is, is die nog niet opgehaald/bestaat die niet
            patienten = new HashMap<>(); //Maak een nieuwe lege hashmap
            patientenOpgehaald = false;
        } else {
            patienten = object; //Sla de map op
            patientenOpgehaald = true;
        }
    }
    
    
    /**
     * Laad alle stamgegevens in die opgeslagen zijn
     */
    private void haalOpgeslagenStamGegevensOp() {
        ArrayList<Object> opgeslagenObjecten = dataController.laadObjectenUitFolder(Folder.BehandelingGegevens, BehandelGegevens.class); //Haal alle opgeslagen stamgegevens op
        for (Object opgeslagenObject : opgeslagenObjecten) {
            BehandelGegevens gegevens = (BehandelGegevens) opgeslagenObject;
            stamGegevens.put(gegevens.getBehandelingscode(), gegevens); //Sla de gegevens op in de HashMap
        }
    }
    
    /**
     * Get de BehandelGegevens aan de hand van de code
     * @param behandelingcode de code
     * @return De BehandelGegevens of null als die niet bestaat
     */
    public BehandelGegevens getBehandelGegevens(String behandelingcode) {
        return stamGegevens.get(behandelingcode);
    }
    
    
    /**
     * Laad alle behandelingen in die opgeslagen zijn
     */
    private void haalOpgeslagenBehandelingenOp() {
        ArrayList<Object> opgeslagenObjecten = dataController.laadObjectenUitFolder(Folder.Behandelingen, Behandeling.class); //Haal alle opgeslagen behandelingen op
        for (Object opgeslagenObject : opgeslagenObjecten) {
            behandelingen.add((Behandeling) opgeslagenObject); //Voeg de behandeling toe aan de ArrayList
        }
    }
    
    /**
     * Get een behandeling aan de hand van het behandelingsID
     * @param behandelingID het ID
     * @return De behandeling of null als die niet bestaat
     */
    public Behandeling getBehandeling(int behandelingsID) {
        Behandeling returnBehandeling = null;
        for (Behandeling behandeling : behandelingen) {
            if (behandeling.getBehandelingsID() == behandelingsID) { //Als de behandelingsids overeen komen
                returnBehandeling = behandeling;
                break; //Break de for loop
            }
        }
        return returnBehandeling;
    }
    
    /**
     * Verwijder een behandeling aan de hand van het behandelingsID
     * @param behandelingsID het ID
     * @return of het verwijderen is geslaagd
     */
    public boolean deleteBehandeling(int behandelingsID) {
        boolean returnBoolean = false;
        Behandeling behandeling = getBehandeling(behandelingsID); //Haal de behandeling op
        if (behandeling != null) { //Kijk of die behandeling wel bestaat
            behandelingen.remove(behandeling); //Verwijder de behandeling uit de array
            returnBoolean = dataController.verwijderBestand(Folder.Behandelingen, String.valueOf(behandelingsID)); //Verwijder de behandeling van de harde schijf
            aangepastePatienten.add(behandeling.getBurgerServiceNummer()); //Voeg het BSN van de patient toe aan de arraylist met aangepaste patienten
        }
        return returnBoolean;
    }
    
    /**
     * Verwijder een behandeling uit de array & van het systeem
     * @param behandeling de behandeling
     * @return gelukt
     */
    private boolean deleteBehandeling(Behandeling behandeling) {
        behandelingen.remove(behandeling); //Verwijder de behandeling uit de array
        boolean returnBool = dataController.verwijderBestand(Folder.Behandelingen, String.valueOf(behandeling.getBehandelingsID())); //Verwijder de behandeling van de harde schijf
        aangepastePatienten.add(behandeling.getBurgerServiceNummer()); //Voeg het BSN van de patient toe aan de arraylist met aangepaste patienten
        return returnBool;
    }
    
    /**
     * Geeft een ArrayList met alle behandelingen
     * @return de behandelingen
     */
    public ArrayList<Behandeling> getBehandelingen() {
        return behandelingen;
    }
    
    /**
     * Synchroniseer de behandelingen met de server
     * @return gelukt
     */
    private boolean synchroniseerBehandelingen() {
        HashMap<Integer, HashMap<Long, String>> patientenHashMaps = new HashMap<>(); //Hashmap met alle nieuwe hashmaps. Key: BSN | Value: De HashMap met behandelingen
        for (Integer bsn : aangepastePatienten) {
            patientenHashMaps.put(bsn, new HashMap<Long, String>()); //Maak een nieuwe HashMap aan voor de persoon
        }        
        for (Behandeling behandeling : behandelingen) { //Loop alle behandelingen door
            int bsn = behandeling.getBurgerServiceNummer();
            if (aangepastePatienten.contains(bsn)) { //Als de patient is aangepast
                patientenHashMaps.get(bsn).put(behandeling.getBegintijd().getTime(), behandeling.getBehandelingscode()); //Voeg de behandeling toe aan die persoon's hashmap
            }
        }
        boolean returnBool = true;
        for (Map.Entry<Integer, HashMap<Long, String>> entry : patientenHashMaps.entrySet()) { //Loop alle entrys af
            if (!dataController.syncObjectMetFTP(String.valueOf(entry.getKey()), entry.getValue())) { //Sync de entry met de FTP server
                returnBool = false; //Als die mislukt om te syncen zet de returnBool naar false
            }
        }
        return returnBool;
    }
    
    
    /**
     * Check of het een valide datum is
     * @param datum de datum
     * @return de date
     */
    private Date checkDatum(String datum) {
        Date returnDate;
        try {
            returnDate = tijdFormat.parse(datum); //Parse de datum
        } catch (ParseException e) {
            returnDate = null; //Geen geldige datum string
        }
        return returnDate;
    }
    
    /**
     * Haal een patient op
     * @param bsn het burgerservicenummer
     * @return de naam van de patient of null als die niet bestaat
     */
    public String getPatient(int bsn) {
        return patienten.get(bsn);
    }
    
    /**
     * Get alle therapeuten
     * @return arraylist met alle therapeuten
     */
    public ArrayList<Therapeut> getTherapeuten() {
        // TODO vul in nadat de manager van praktijk systeem dit ondersteund.
        return null;
    }
    
    /**
     * Check of de fysiotherapeut is beschikbaar voor de aangegeven periode
     * @param bsn Het burgerservicenummer van de fysiotherapeut
     * @param beginTijd De begin tijd Date
     * @param eindTijd De eind tijd Date
     * @return beschikbaar
     */
    private boolean isFysiotherapeutBeschikbaar(int bsn, Date beginTijd, Date eindTijd) {
        // TODO: Maak wanneer de praktijkmanager beschikbaar is
        return false;
    }

    /**
     * Checked of de patienten zijn opgehaald
     * @return opgehaald
     */
    public boolean isPatientenOpgehaald() {
        return patientenOpgehaald;
    }
    
    
    /**
     * Check of de opgeslagen data nog altijd overeenkomt met de ontvangen gegevens
     */
    private void checkDataIntegriteit() {
        for (Behandeling behandeling : behandelingen) {
            if (!patienten.containsKey(behandeling.getBurgerServiceNummer())) { //Als de patient van de behandeling niet meer bestaat/in het systeem staat
                deleteBehandeling(behandeling); //Verwijder de behandeling
            }
        }
    }
    
}
