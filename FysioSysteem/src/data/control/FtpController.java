package data.control;

import data.entity.FtpGegevens;
import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Leon Stam
 */
public class FtpController {
    
    /*
     * De FTP inlog/connectie gegevens
     */
    private FtpGegevens ftpGegevens;
    
    /*
     * De FTP client
     */
    private FTPClient ftpClient;
    
    /*
     * Connectie info
     */
    private boolean verbonden;
    private boolean ingelogd;
    private String huidgeFolder;
    
    private String error;
    
    
    public FtpController(FtpGegevens ftpGegevens) {
        this.ftpGegevens = ftpGegevens;
        verbonden = false;
        ingelogd = false;
        
        ftpClient = new FTPClient();
        verbind();
    }
    
    /**
     * Verbind met de server
     * @return boolean verbinden gelukt
     */
    public boolean verbind() {
        if (!isVerbonden()) connect();
        if (isVerbonden()) { if (!isIngelogd()) login(); }
        if (isIngelogd() && isVerbonden()) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Connect met de FTP server
     */
    private void connect() {
        try {
            ftpClient.connect(ftpGegevens.getHost(), ftpGegevens.getPort()); //Probeer te verbinden
            verbonden = true;
        } catch (FTPException | FTPIllegalReplyException | IOException e) {
            e.printStackTrace();
            error = "Mislukt te verbinden met de FTP server.";
            verbonden = false; //verbonden is mislukt
        }
    }
    
    /**
     * Login op de FTP server
     */
    private void login() {
        try {
            ftpClient.login(ftpGegevens.getLoginNaam(), ftpGegevens.getLoginWachtwoord());
            ingelogd = true;
        } catch (FTPException | FTPIllegalReplyException | IOException e) {
            e.printStackTrace();
            error = "Inloggen op de FTP server is mislukt.";
            verbreekVerbinding();
            ingelogd = false;
        }
    }
    
    /**
     * Verbreek de verbinding met de server
     */
    public void verbreekVerbinding() {
        if (isVerbonden()) {
            ingelogd = false;
            verbonden = false;
            try {
                ftpClient.disconnect(true);
            } catch (FTPException | FTPIllegalReplyException | IOException e) { 
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Synchroniseer (aangepaste) bestanden die op de server staan
     * @param files De huidige bestanden op de computer
     * @return boolean of het gelukt is
     */
    public boolean syncMetServerBestanden(ArrayList<File> files, String saveLocation) {
        if (!isVerbonden() || !isIngelogd()) return false; //Niet verbonden of ingelogd dus kan niet uigevoerd worden
        try {
            if (!changeDirectory("Facturatie")) return false;
            ArrayList<File> aangepasteFiles = new ArrayList<>();
            FTPFile[] ftpFiles = ftpClient.list();
            for (FTPFile file : ftpFiles) {
                String fileName = file.getName();
                boolean fileFound = false;
                for (File f : files) {
                    if (f.getName().equals(fileName)) {
                        if (f.lastModified() < file.getModifiedDate().getTime()) {
                            ftpClient.download(fileName, f);
                        }
                        fileFound = true;
                        break;
                    }
                }
                if (!fileFound) {
                    File saveLoc = new File(saveLocation + fileName);
                    ftpClient.download(fileName, saveLoc);
                }
            }
        } catch (FTPException | FTPIllegalReplyException | IOException | FTPAbortedException | FTPDataTransferException | FTPListParseException e) {
                e.printStackTrace();
            return false;
        }
        return true;
    }
    
    /**
     * Upload file naar de server
     * @param file De file
     * @return boolean gelukt
     */
    public boolean uploadFileNaarSever(File file) {
        if (!isVerbonden() || !isIngelogd()) return false; //Niet verbonden of ingelogd dus kan niet uigevoerd worden
        try {
            if (!changeDirectory("Fysio")) return false;
            ftpClient.upload(file);
            return true;
        } catch (FTPAbortedException | FTPDataTransferException | FTPException | FTPIllegalReplyException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private boolean changeDirectory(String pad) {
        if (!isVerbonden() || !isIngelogd()) return false; //Niet verbonden of ingelogd dus kan niet uigevoerd worden
        try {
            if (!ftpClient.currentDirectory().equals(pad)) {
                try {
                    ftpClient.changeDirectory(pad);
                    return true;
                } catch (FTPException e) {
                    if (e.getCode() == 550) {
                        ftpClient.createDirectory(pad);
                        ftpClient.changeDirectory(pad);
                        return true;
                    } else {
                        throw new FTPException(e.getCode(), e.getMessage());
                    }
                }
            } else {
                return true;
            }
        } catch (FTPException | FTPIllegalReplyException | IOException e) {
            return false;
        }
    }

    /**
     * Check of er een verbinding is met de FTP server
     * @return verbonden
     */
    public boolean isVerbonden() {
        return verbonden;
    }
    
    /**
     * Check of er is ingelogd op de FTP server
     * @return ingelogd
     */
    public boolean isIngelogd() {
        return ingelogd;
    }
    
    /**
     * Geeft mogelijke foutmeldingen terug voor nette errorhandling
     * @return error
     */
    public String getError(){
        return error;
    }
    
}
