package praktijk.control;

import java.util.ArrayList;
import praktijk.entity.Praktijk;
import data.control.DataController;
import data.entity.Folder;
import java.util.*;

/**
 * Manager van {@link praktijk.boundary.PraktijkOverzichtGUI PraktijkOverzichtGUI} en {@link praktijk.boundary.PraktijkWijzigGUI PraktijkWijzigGUI}
 * @author Dennis
 */
public class PraktijkManager {
    private ArrayList<Praktijk> praktijken;
    private DataController dataController;
    
    /**
     * Constructor<br />
     * Haalt de praktijken op uit het {@link data datasubsysteem}
     */
    private PraktijkManager() {
        praktijken = new ArrayList<>();
        dataController = new DataController();
        
        //ophalen van objecten uit data subsysteem, cast deze naar praktijken
        for (Object obj : dataController.laadObjectenUitFolder(Folder.Praktijken, Praktijk.class)) {
            praktijken.add((Praktijk) obj);
        }
        
        //sorteer de praktijken op naam
        Collections.sort(praktijken, Praktijk.praktijkNameComparator);
    }
    
    /**
     * Verkrijg alle praktijken
     * @return Een {@link java.util.ArrayList ArrayList} met alle {@link praktijk.entity.Praktijk praktijken}
     */
    public ArrayList<Praktijk> getPraktijken() {
        return praktijken;
    }
    
    /**
     * Verkrijg een bepaalde praktijk
     * @param index De index die deze praktijk heeft in de {@link java.util.ArrayList ArrayList} en JTable
     * @return De gevraagde praktijk
     */
    public Praktijk getPraktijk(int index) {
        return praktijken.get(index);
    }
    
    /**
     * Voeg een nieuwe praktijk toe, deze wordt ook opgeslagen d.m.v. het {@link data datasubsysteem}
     * @param praktijk de nieuwe praktijk
     * @return succes: is het toevoegen geslaagd?
     */
    public boolean voegToe(Praktijk praktijk) {
        boolean succes = dataController.saveObject(Folder.Praktijken, String.valueOf(praktijk.getKvk()), praktijk);
        if (succes)
            praktijken.add(praktijk);
        return succes;
    }
    
    /**
     * Wijzig een bestaande praktijk, deze wordt ook gewijzigd in het {@link data datasubsysteem}
     * @param index De index die deze prakijk heeft in de {@link java.util.ArrayList ArrayList} en JTable
     * @param praktijk de nieuwe praktijk
     * @return succes: is het toevoegen geslaagd?
     */
    public boolean wijzig(int index, Praktijk praktijk) {
        boolean succes = dataController.saveObject(Folder.Praktijken, String.valueOf(praktijk.getKvk()), praktijk);
        if (succes)
            praktijken.set(index, praktijk);
        return succes;
    }
    
    /**
     * Verwijder een praktijk, deze wordt ook verwijderd uit het {@link data datasubsysteem}
     * @param index De index die deze prakijk heeft in de {@link java.util.ArrayList ArrayList} en JTable
     * @return succes: is het toevoegen geslaagd?
     */
    public boolean verwijder(int index) {
        boolean succes = dataController.verwijderBestand(Folder.Praktijken, String.valueOf(praktijken.get(index).getKvk()));
        if (succes)
            praktijken.remove(index);
        return succes;
    }
    
    /**
     * Doorzoekt de namen van de praktijken op de woorden in de query
     * @param query de woorden waarop gezocht wordt
     * @return de praktijken die voldoen aan de zoekopdracht
     */
    public ArrayList<Praktijk> zoekPraktijk(String query, boolean isName) {
        ArrayList<Praktijk> gevondenPraktijken = new ArrayList<>();
        //splits de query op de spatie (regex)
        List<String> keywords = Arrays.asList(query.toLowerCase().split("\\s+"));
        
        for(Praktijk praktijk : praktijken) {
            Iterator<String> iterator = keywords.iterator();
            String naam = (isName ? praktijk.getNaam() : praktijk.getPlaats()).toLowerCase();
            //boolean wordt false, wanneer niet alle zoekworden terugkomen in de naam/plaatsnaam
            boolean containsWords = true;
            
            while (iterator.hasNext() && containsWords) {
                if (!naam.contains(iterator.next()))
                    containsWords = false;
            }
            
            if (containsWords)
                gevondenPraktijken.add(praktijk);
        }
        
        return gevondenPraktijken;
    }
}
