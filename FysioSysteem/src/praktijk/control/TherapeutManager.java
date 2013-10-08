package praktijk.control;

import java.util.ArrayList;
import praktijk.entity.Therapeut;
import data.control.DataController;
import data.entity.Folder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Manager van {@link therapeut.boundary.TherapeutOverzichtGUI TherapeutOverzichtGUI} en {@link therapeut.boundary.TherapeutWijzigGUI TherapeutWijzigGUI}
 * @author Dennis
 */
public class TherapeutManager {
    private ArrayList<Therapeut> therapeuten;
    private DataController dataController;
    
    /**
     * Constructor<br />
     * Haalt de therapeuten op uit het {@link data datasubsysteem}
     */
    private TherapeutManager() {
        therapeuten = new ArrayList<>();
        dataController = new DataController();
        
        //ophalen van objecten uit data subsysteem, cast deze naar therapeuten
        for (Object obj : dataController.laadObjectenUitFolder(Folder.Therapeuten, Therapeut.class)) {
            therapeuten.add((Therapeut) obj);
        }
        
        //sorteer de therapeuten op naam
        Collections.sort(therapeuten, Therapeut.therapeutNameComparator);
    }
    
    /**
     * Verkrijg alle therapeuten
     * @return Een {@link java.util.ArrayList ArrayList} met alle {@link therapeut.entity.Therapeut therapeuten}
     */
    public ArrayList<Therapeut> getTherapeuten() {
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
        if (succes)
            therapeuten.add(therapeut);
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
        if (succes)
            therapeuten.remove(index);
        return succes;
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
    
    public boolean checkDatum(String datum) {
        try {
            Date parsed = new SimpleDateFormat("dd-MM-yyyy").parse(datum);
            return true;
        }
        catch (ParseException e) {
            return false;
        }
    }
}
