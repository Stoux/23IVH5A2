package praktijk.control;

import java.util.ArrayList;
import praktijk.entity.Therapeut;
import data.control.DataController;
import data.entity.Folder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import praktijk.entity.Praktijk;

/**
 * Manager van {@link therapeut.boundary.TherapeutOverzichtGUI TherapeutOverzichtGUI} en {@link therapeut.boundary.TherapeutWijzigGUI TherapeutWijzigGUI}
 * @author Dennis
 */
public class TherapeutManager {
    private ArrayList<Therapeut> therapeuten;
    private ArrayList<Integer> bsnNummers;
    private DataController dataController;
    
    /**
     * Constructor<br />
     * Haalt de therapeuten op uit het {@link data datasubsysteem}
     */
    public TherapeutManager(DataController dataController) {
        this.dataController = dataController;
        therapeuten = new ArrayList<>();
        bsnNummers = new ArrayList<>();
        
        //ophalen van objecten uit data subsysteem, cast deze naar therapeuten
        for (Object obj : dataController.laadObjectenUitFolder(Folder.Therapeuten, Therapeut.class)) {
            therapeuten.add((Therapeut) obj);
            bsnNummers.add(((Therapeut) obj).getBsn());
        }
    }
    
    /**
     * Verkrijg alle therapeuten.<br />
     * Alvorens het terugsturen worden deze gesorteerd, aangezien de kans groot is dat er wijzigingen hebben plaatsgevonden.
     * @return Een {@link java.util.ArrayList ArrayList} met alle {@link therapeut.entity.Therapeut therapeuten}
     */
    public ArrayList<Therapeut> getTherapeuten() {
        //sorteer de therapeuten op naam
        Collections.sort(therapeuten, Therapeut.therapeutNameComparator);
        return therapeuten;
    }
    
    /**
     * Verkrijg een bepaalde therapeut
     * @param index De index die deze therapeut heeft in de {@link java.util.ArrayList ArrayList} en JTable
     * @return De gevraagde therapeut
     */
    public Therapeut getTherapeut(int index) {
        return therapeuten.get(index);
    }
    
    /**
     * Voeg een nieuwe therapeut toe, deze wordt ook opgeslagen d.m.v. het {@link data datasubsysteem}
     * @param therapeut de nieuwe therapeut
     * @return succes: is het toevoegen geslaagd?
     */
    public boolean voegToe(Therapeut therapeut) {
        boolean succes = dataController.saveObject(Folder.Therapeuten, String.valueOf(therapeut.getBsn()), therapeut);
        if (succes) {
            therapeuten.add(therapeut);
            bsnNummers.add(therapeut.getBsn());
        }
        return succes;
    }
    
    /**
     * Wijzig een bestaande therapeut, deze wordt ook gewijzigd in het {@link data datasubsysteem}
     * @param index De index die deze therapeut heeft in de {@link java.util.ArrayList ArrayList} en JTable
     * @param therapeut de nieuwe therapeut
     * @return succes: is het toevoegen geslaagd?
     */
    public boolean wijzig(int index, Therapeut therapeut) {
        boolean succes = dataController.saveObject(Folder.Therapeuten, String.valueOf(therapeut.getBsn()), therapeut);
        if (succes)
            therapeuten.set(index, therapeut);
        return succes;
    }
    
    /**
     * Verwijder een therapeut, deze wordt ook verwijderd uit het {@link data datasubsysteem}
     * @param index De index die deze therapeut heeft in de {@link java.util.ArrayList ArrayList} en JTable
     * @return succes: is het toevoegen geslaagd?
     */
    public boolean verwijder(int index) {
        boolean succes = dataController.verwijderBestand(Folder.Therapeuten, String.valueOf(therapeuten.get(index).getBsn()));
        if (succes) {
            therapeuten.remove(index);
            bsnNummers.remove(index);
        }
        return succes;
    }
    
    /**
     * Bekijkt of er reeds een therapeut bestaat met dit BSN-nummer. Dit nummer dient namelijk uniek te zijn.
     * @param bsn het BSN-nummer, dat zal worden opgezocht
     * @return of het BSN-nummer al bestaat of neit
     */
    public boolean bsnBestaat(int bsn) {
        return bsnNummers.contains(bsn);
    }
    
    /**
     * Doorzoekt de namen van de therapeuten op de woorden in de query
     * @param query de woorden waarop gezocht wordt
     * @return de therapeuten die voldoen aan de zoekopdracht
     */
    public ArrayList<Therapeut> zoekTherapeut(String query) {
        ArrayList<Therapeut> gevondenTherapeuten = new ArrayList<>();
        //splits de query op de spatie (regex)
        List<String> keywords = Arrays.asList(query.toLowerCase().split("\\s+"));
        
        for(Therapeut therapeut : therapeuten) {
            Iterator<String> iterator = keywords.iterator();
            String naam = therapeut.getVolledigeNaam().toLowerCase();
            //boolean wordt false, wanneer niet alle zoekworden terugkomen in de naam
            boolean containsWords = true;
            
            while (iterator.hasNext() && containsWords) {
                if (!naam.contains(iterator.next()))
                    containsWords = false;
            }
            
            if (containsWords)
                gevondenTherapeuten.add(therapeut);
        }
        
        return gevondenTherapeuten;
    }
    
    /**
     * Teruggeven van lijst met KVK-nummers en namen
     * @return KVK-nummers met bijbehorende namen
     */
    public HashMap<Long, String> getPraktijkNamen() {
        PraktijkManager praktijkManager = new PraktijkManager(dataController);
        ArrayList<Praktijk> praktijken = praktijkManager.getPraktijken();
        HashMap<Long, String> namen = new HashMap<>();
        
        for (Praktijk praktijk : praktijken) {
            namen.put(praktijk.getKvk(), praktijk.getNaam());
        }
        
        return namen;
    }
}
