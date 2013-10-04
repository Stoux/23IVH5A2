package praktijk.control;

import java.util.ArrayList;
import praktijk.entity.Therapeut;
import data.control.DataController;
import data.entity.Folder;
import java.util.Collections;

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
        
        //sorteer de praktijken op naam
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
        therapeuten.add(therapeut);
        // TODO: Opslaan van nieuwe therapeut
        return dataController.saveObject(Folder.Therapeuten, String.valueOf(therapeut.getBsn()), therapeut);
    }
    
    /**
     * Wijzig een bestaande therapeut, deze wordt ook gewijzigd in het {@link data datasubsysteem}
     * @param index De index die deze therapeut heeft in de {@link java.util.ArrayList ArrayList} en JTable
     * @param therapeut de nieuwe therapeut
     * @return succes: is het toevoegen geslaagd?
     */
    public boolean wijzig(int index, Therapeut therapeut) {
        therapeuten.set(index, therapeut);
        
        //TODO: Opslaan van gewijzigde therapeut
        return dataController.saveObject(Folder.Therapeuten, String.valueOf(therapeut.getBsn()), therapeut);
    }
    
    public boolean verwijder() {
        // TODO: verwijderen van therapeut
        throw new UnsupportedOperationException("Nog niet geïmplementeerd");
    }
    
    public void toonTherapeut(String naam) {
        // TODO: Zoeken op therapeut
        throw new UnsupportedOperationException("Nog niet geïmplementeerd");
    }
}
