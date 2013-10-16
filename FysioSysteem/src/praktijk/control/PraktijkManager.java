package praktijk.control;

import java.util.ArrayList;
import praktijk.entity.Praktijk;
import data.control.DataController;
import data.entity.Folder;
import java.util.*;
import praktijk.entity.Therapeut;

/**
 * Manager van {@link praktijk.boundary.PraktijkOverzichtGUI PraktijkOverzichtGUI} en {@link praktijk.boundary.PraktijkWijzigGUI PraktijkWijzigGUI}
 * @author Dennis
 */
public class PraktijkManager {
    private ArrayList<Praktijk> praktijken;
    private ArrayList<Long> kvkNummers;
    private DataController dataController;
    
    /**
     * Constructor<br />
     * Haalt de praktijken op uit het {@link data datasubsysteem}
     * @param dataController de controller voor het lezen en schrijven van objecten
     */
    public PraktijkManager(DataController dataController) {
        this.dataController = dataController;
        praktijken = new ArrayList<>();
        kvkNummers = new ArrayList<>();
        
        //ophalen van objecten uit data subsysteem, cast deze naar praktijken
        for (Object obj : dataController.laadObjectenUitFolder(Folder.Praktijken, Praktijk.class)) {
            praktijken.add((Praktijk) obj);
            kvkNummers.add(((Praktijk) obj).getKvk());
        }
    }
    
    /**
     * Verkrijg alle praktijken.<br />
     * Alvorens het terugsturen worden deze gesorteerd, aangezien de kans groot is dat er wijzigingen hebben plaatsgevonden.
     * @return Een {@link java.util.ArrayList ArrayList} met alle {@link praktijk.entity.Praktijk praktijken}
     */
    public ArrayList<Praktijk> getPraktijken() {
        //sorteer de praktijken op naam
        Collections.sort(praktijken, Praktijk.praktijkNameComparator);
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
        if (succes) {
            praktijken.add(praktijk);
            kvkNummers.add(praktijk.getKvk());
        }
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
     * Verwijder een praktijk, deze wordt ook verwijderd uit het {@link data datasubsysteem}. <br />
     * Daarnaast word het KVK-nummer in de therapeuten bij deze praktijk op 0 gezet.
     * @param index De index die deze prakijk heeft in de {@link java.util.ArrayList ArrayList} en JTable
     * @return succes: is het toevoegen geslaagd?
     */
    public boolean verwijder(int index) {
        long kvk = praktijken.get(index).getKvk();
        TherapeutManager therapeutManager = new TherapeutManager(dataController);
        ArrayList<Therapeut> therapeuten = therapeutManager.getTherapeuten();
        
        for (Therapeut therapeut : therapeuten) {
            if (therapeut.getPraktijkKvk() == kvk) {
                int therapeutIndex = therapeuten.indexOf(therapeut);
                therapeut.setPraktijkKvk(0);
                therapeutManager.wijzig(therapeutIndex, therapeut);
            }
        }
        
        boolean succes = dataController.verwijderBestand(Folder.Praktijken, String.valueOf(kvk));
        if (succes) {
            praktijken.remove(index);
            kvkNummers.remove(index);
        }
        return succes;
    }
    
    /**
     * Bekijkt of er reeds een praktijk bestaat met dit KVK-nummer. Dit nummer dient namelijk uniek te zijn.
     * @param kvk het KVK-nummer, dat zal worden opgezocht
     * @return of het KVK-nummer al bestaat of neit
     */
    public boolean kvkBestaat(long kvk) {
        return kvkNummers.contains(kvk);
    }
    
    /**
     * Doorzoekt de namen van de praktijken op de woorden in de query
     * @param query de woorden waarop gezocht wordt
     * @param isName true wanneer gezocht wordt op naam, false waneer gezocht wordt op plaatsnaam
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
