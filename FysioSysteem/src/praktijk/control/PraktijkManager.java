package praktijk.control;

import java.util.ArrayList;
import praktijk.entity.Praktijk;
import data.control.DataController;
import data.entity.Folder;

/**
 * Manager van {@link praktijk.boundary.PraktijkOverzichtGUI PraktijkOverzichtGUI} en {@link praktijk.boundary.PraktijkWijzigGUI PraktijkWijzigGUI}
 * @author Dennis
 */
public class PraktijkManager {
    private ArrayList<Praktijk> praktijken;
    private DataController dataController;
    
    /**
     * Initialiseert de ArrayList en folders
     */
    private PraktijkManager() {
        praktijken = new ArrayList<>();
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
     * @param index De index die deze prakijk heeft in de {@link java.util.ArrayList ArrayList} en JTable
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
        praktijken.add(praktijk);
        // TODO: Opslaan van nieuwe praktijk
        return dataController.saveObject(Folder.Praktijken, String.valueOf(praktijk.getKvk()), praktijk);
    }
    
    /**
     * Wijzig een bestaande praktijk, deze wordt ook gewijzigd in het {@link data datasubsysteem}
     * @param index De index die deze prakijk heeft in de {@link java.util.ArrayList ArrayList} en JTable
     * @param praktijk de nieuwe praktijk
     * @return succes: is het toevoegen geslaagd?
     */
    public boolean wijzig(int index, Praktijk praktijk) {
        praktijken.set(index, praktijk);
        
        //TODO: Opslaan van gewijzigde praktijk
        return dataController.saveObject(Folder.Praktijken, String.valueOf(praktijk.getKvk()), praktijk);
    }
    
    public boolean verwijder() {
        // TODO: verwijderen van praktijk
        throw new UnsupportedOperationException("Nog niet geïmplementeerd");
    }
    
    public void toonPraktijk(String naam) {
        // TODO: Zoeken op praktijk
        throw new UnsupportedOperationException("Nog niet geïmplementeerd");
    }
}
