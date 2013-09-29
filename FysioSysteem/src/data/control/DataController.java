package data.control;

import data.entity.Folder;
import data.entity.FtpGegevens;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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
     * FTP Controller
     */
    private FtpController ftp;
    
    /**
     * DataController constructor
     */
    public DataController() {
        separator = System.getProperty("file.separator");
        mainFolder = System.getProperty("user.home") + separator + "FysioSysteem" + separator;
        type = ".dat";
        
        //Maak folders
        for(Folder f : Folder.values()) {
            maakFolder(f.getPad());
        }
        
        //Maak de FTP controller aan
        ftp = new FtpController(new FtpGegevens("localhost", 21, "school", "school"));
        
        //Sync met server
        String folder = Folder.FTPFacturatie.getPad();
        ftp.syncMetServerBestanden(getBestandenUitFolder(folder), mainFolder + folder + separator);
        
        //Verbreek verbinding
        ftp.verbreekVerbinding();
    }
    
    /**
     * Afsluit sequence.
     */
    public void sluitAf() {
        if (ftp.isVerbonden()) {
            ftp.verbreekVerbinding();
        }
    }
    
    
    /**
     * Verstuur een object naar de FTP server
     * @param bestand
     * @param object
     * @return 
     */
    public boolean syncObjectMetFTP(String bestand, Object object) {
        if (!ftp.isVerbonden() && !ftp.isIngelogd()) ftp.verbind(); //Verbind als er geen verbinding is
        else if (!ftp.isVerbonden() || !ftp.isIngelogd()) return false;
        
        boolean returnValue = true;
        File file = saveObjectF(mainFolder + Folder.FTPFysio.getPad() + separator + bestand + type, object);
        if (file != null) {
            returnValue = ftp.uploadFileNaarSever(file);
        } else {
            returnValue = false;
        }
        return returnValue;
    }
    
    /**
     * Maak de folder
     * @param pad Naam van de folder
     * @return of het gelukt is
     */
    private boolean maakFolder(String pad) {
        File f = new File(mainFolder + pad); //Maak de file/folder aan
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
        if (saveObjectF(mainFolder + folder.getPad() + separator + bestand + type, object) != null) return true;
        else return false;
    }
    
    /**
     * Sla een object op
     * @param pad het pad van het bestand
     * @param object het op te slaan bestand
     * @return De opgeslagen file. Null als het mislukt is.
     */
    private File saveObjectF(String pad, Object object) {
        File returnValue;
        try {
            File file = new File(pad);
            FileOutputStream fos = new FileOutputStream(file); //Maak een nieuwe output
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) { //Try-With-Resources | oss word automatisch gesloten als die klaar is
                oos.writeObject(object); //Schrijf object weg
            }
            returnValue = file;
        } catch (IOException e) {
            e.printStackTrace();
            returnValue = null;
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
    private Object laadObject(Folder folder, String bestand, Class objectKlasse) {
        Object returnObject;
        try {
            FileInputStream fis = new FileInputStream(new File(mainFolder + folder + separator + bestand + type));
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                returnObject = objectKlasse.cast(ois.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            returnObject = null;
        }
        return returnObject;
    }
    
    
    
    /**
     * Haal alle bestanden op in een bepaalde folder
     * @param folder
     * @return 
     */
    private ArrayList<File> getBestandenUitFolder(String folder) {
        File f = new File(mainFolder + folder);
        
        ArrayList<File> files = new ArrayList<>();
        for (File foundFile : f.listFiles()) {
            if (foundFile.isFile()) {
                files.add(foundFile);
            }
        }
        return files;
    }
    
    
}
