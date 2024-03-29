package data.entity;

/**
 *
 * @author Leon Stam
 */
public enum Folder {
    
    Therapeuten("Therapeuten"), Praktijken("Praktijken"), Behandelingen("Behandelingen"), BehandelingGegevens("Behandelingen" + System.getProperty("file.separator") + "StamGegevens"), FTPFysio("FTP" + System.getProperty("file.separator") + "Fysio"), FTPFacturatie("FTP" + System.getProperty("file.separator") + "Facturatie");

    private String pad;
    
    private Folder(String pad) {
        this.pad = pad;
    }
    
    public String getPad() {
        return pad;
    }
    
    
}
