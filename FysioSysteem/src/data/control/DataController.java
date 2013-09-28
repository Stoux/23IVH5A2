package data.control;

import data.entity.Folder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Leon Stam
 */
public class DataController {

    /**
     * Data opslag pad
     */
    private String mainFolder;
    private String separator;
    private String type;
    
    /**
     * Maak de datacontroller
     */
    public DataController() {
        separator = System.getProperty("file.separator");
        mainFolder = System.getProperty("user.home") + separator + "FysioSysteem";
        type = ".dat";
        
        //Maak folders
        for(Folder f : Folder.values()) {
            maakFolder(f.name());
        }
        
    }
    
    /**
     * Maak de folder
     * @param pad Naam van de folder
     * @return of het gelukt is
     */
    private boolean maakFolder(String pad) {
        File f = new File(mainFolder + separator + pad); //Maak de file/folder aan
        boolean returnValue;
        if (f.exists()) { //Kijk of de folder bestaat
            returnValue = true;
        } else { //Als dat niet 't geval is maak hem aan
            returnValue = f.mkdirs();
        }
        return returnValue;
    }
    
    /**
     * Sla een object op
     * @param folder Folder waar het word opgeslagen
     * @param bestand Naam van het bestand
     * @param object Het object dat opgeslagen moet worden
     * @return of het opslaan is gelukt
     */
    public boolean saveObject(Folder folder, String bestand, Object object) {
        boolean returnValue;
        try {
            FileOutputStream fos = new FileOutputStream(new File(mainFolder + separator + folder + separator + bestand + type)); //Maak een nieuwe output
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) { //Try-With-Resources | oss word automatisch gesloten als die klaar is
                oos.writeObject(object); //Schrijf object weg
            }
            returnValue = true;
        } catch (IOException e) {
            returnValue = false;
        }
        return returnValue;
    }
    
    /**
     * Laad een opgeslagen object
     * @param folder Folder waar het is opgeslagen
     * @param bestand Naam van het bestand
     * @param objectKlasse De klasse die het object zou moeten zijn
     * @return Het geladen object | Null als het bestand niet is gevonden of niet de goede klasse was
     */
    public Object laadObject(Folder folder, String bestand, Class objectKlasse) {
        Object returnObject;
        try {
            FileInputStream fis = new FileInputStream(new File(mainFolder + separator + folder + separator + bestand + type));
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                returnObject = objectKlasse.cast(ois.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            returnObject = null;
        }
        return returnObject;
    }
    
    
}
