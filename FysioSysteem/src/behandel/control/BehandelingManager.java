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
import java.util.HashSet;
import java.util.Map;
import praktijk.control.TherapeutManager;
import praktijk.entity.Therapeut;

/**
 *
 * @author Leon Stam
 */
public class BehandelingManager {
    
    /**
     * Externe managers/controllers
     */
    private DataController dataController;
    private TherapeutManager therapeutManager;
    
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
     * TimeFormats
     */
    private SimpleDateFormat datum;
    private SimpleDateFormat tijd;
    private SimpleDateFormat datumTijd;
    
    /**
     * Constructor van de manager
     * @param dataController de DataController
     */
    public BehandelingManager(DataController dataController, TherapeutManager therapeutManager) {
        this.dataController = dataController;
        this.therapeutManager = therapeutManager;
        
        //Maak de lijsten
        stamGegevens = new HashMap<>();
        behandelingen = new ArrayList<>();
        patienten = new HashMap<>();
        aangepastePatienten = new HashSet<>();
        
        //Maak de DateFormat
        datum = new SimpleDateFormat("dd/MM/yyyy");
        tijd = new SimpleDateFormat("HH:mm");
        datumTijd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
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
     * Get alle behandelcodes van de stamgegevens
     * @return Lijst met alle codes
     */
    public ArrayList<String> getBehandelCodes() {
        return new ArrayList<>(stamGegevens.keySet());        
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
     * Maak een nieuwe behandeling aan
     * @param bsn BurgerServiceNummer van de patient
     * @param behandelingscode de behandelingscode
     * @param fysiotherapeutBSN De BSN van de fysiotherapeut
     * @param begintijd De begin date
     * @param eindtijd De eind date
     * @param status De status
     * @param opmerking Een mogelijke opmerking
     * @return De nieuwe behandeling
     */
    public Behandeling maakBehandeling(int bsn, String behandelingscode, long fysiotherapeutBSN, Date begintijd, Date eindtijd, Status status, String opmerking) {
        int id = 1;
        for (Behandeling behandeling : behandelingen) {
            if (behandeling.getBehandelingsID() > id) id = behandeling.getBehandelingsID();
        }
        Behandeling behandeling = new Behandeling(id, bsn, behandelingscode, fysiotherapeutBSN, begintijd, eindtijd, status, opmerking);
        dataController.saveObject(Folder.Behandelingen, String.valueOf(id), behandeling);
        aangepastePatienten.add(bsn);
        return behandeling;
    }
    
    /**
     * Het updaten van een behandeling
     * @param behandelingsID het id
     * @param behandelingscode de code
     * @param begintijd de begintijd
     * @param eindtijd de eindtijd
     * @param status de status
     * @param opmerking de opmerking
     * @return gelukt
     */
    public boolean updateBehandeling(int behandelingsID, String behandelingscode, long fysiotherapeutbsn, Date begintijd, Date eindtijd, Status status, String opmerking) {
        boolean returnBoolean = false;
        Behandeling behandeling = getBehandeling(behandelingsID);
        if (behandeling != null) {
            behandeling.setBehandelingscode(behandelingscode);
            behandeling.setFysiotherapeutBSN(fysiotherapeutbsn);
            behandeling.setBegintijd(begintijd);
            behandeling.setEindtijd(eindtijd);
            behandeling.setStatus(status);
            behandeling.setOpmerking(opmerking);
            returnBoolean = dataController.saveObject(Folder.Behandelingen, Integer.toString(behandelingsID), behandeling);
            aangepastePatienten.add(behandeling.getBurgerServiceNummer());
        } 
        return returnBoolean;
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
     * Format een date naar dd/MM/yyyy
     * @param date de date
     * @return formatted string
     */
    public String formatDatum(Date date) {
        return datum.format(date);
    }
    
    /**
     * Format een date naar HH:mm
     * @param date de date
     * @return formatted string
     */
    public String formatTijd(Date date) {
        return tijd.format(date);
    }
    
    /**
     * Parse een string (Format: dd/MM/yyyy HH:mm) naar date
     * @param datum de datum string
     * @return de date of null als het geen valide string is
     */
    public Date parseDateString(String datum) {
        Date returnDate;
        try {
            returnDate = datumTijd.parse(datum);
        } catch (ParseException e) {
            returnDate = null; //Geen geldige datum string
        }
        return returnDate;
    }
    
    /**
     * Get de DateFormat: dd/MM/yyyy
     * @return de Format
     */
    public SimpleDateFormat getDatumFormat() {
        return datum;
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
     * Get een arraylist met alle patient nummers
     * @return Lijst met alle BSNummers van alle patienten in strings
     */
    public ArrayList<String> getPatientenBSNLijst() {
        ArrayList<String> patientenLijst = new ArrayList<>();
        for (int patient : patienten.keySet()) {
            patientenLijst.add(String.valueOf(patient));
        }
        return patientenLijst;
    }
    
    /**
     * Get alle therapeuten
     * @return arraylist met alle therapeuten
     */
    public ArrayList<Therapeut> getTherapeuten() {
        return therapeutManager.getTherapeuten();
    }
    
    /**
     * Check of een therapeut bestaat
     * @param bsn Het BurgerServiceNummer van de therapeut
     * @return bestaat
     */
    public boolean isBestaandeTherapeut(long bsn) {
        for (Therapeut therapeut : getTherapeuten()) {
            if (therapeut.getBsn() == bsn) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Check of de fysiotherapeut beschikbaar is voor de aangegeven periode.
     * @param bsn Het burgerservicenummer van de fysiotherapeut
     * @param beginTijd De begin tijd Date
     * @param eindTijd De eind tijd Date
     * @return beschikbaar
     */
    public boolean isFysiotherapeutBeschikbaar(int bsn, Date beginTijd, Date eindTijd) {
        return isFysiotherapeutBeschikbaar(bsn, beginTijd, eindTijd, -1);
    }
    
    /**
     * Check of de fysiotherapeut beschikbaar is voor de aangegeven periode. Negeer de behandeling die word aangepast.
     * @param bsn Het burgerservicenummer van de fysiotherapeut
     * @param beginTijd De begin tijd Date
     * @param eindTijd De eint tijd Date
     * @param behandelingsID Het ID van de behandeling die genegeerd mag worden.
     * @return beschikbaar
     */
    public boolean isFysiotherapeutBeschikbaar(int bsn, Date beginTijd, Date eindTijd, int behandelingsID) {
        for (Behandeling behandeling : getBehandelingen()) { //Check alle behandelingen
            if (behandeling.getFysiotherapeutBSN() == bsn) { //Kijk het BSN van de fysio overeenkomt
                if (behandelingsID != behandeling.getBehandelingsID()) { //Negeer de behandeling met het ID
                    if ((beginTijd.before(behandeling.getBegintijd()) && eindTijd.after(behandeling.getEindtijd())) || (beginTijd.after(behandeling.getBegintijd()) && beginTijd.before(behandeling.getEindtijd())) || (eindTijd.after(behandeling.getBegintijd()) && eindTijd.before(behandeling.getEindtijd()))) {
                        return false; //Niet beschikbaar, return false
                    }
                }
            }
        }
        return true;
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
