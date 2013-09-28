package data.control;

import data.entity.FtpGegevens;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import java.io.IOException;

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
        ftpClient = new FTPClient();
        verbind();
        if (!verbonden) return; //Mislukt te verbinden.
        login();
        if (!ingelogd) return; //Mislukt in te loggen.
        
        
    }
    
    /**
     * Verbind met de FTP server
     */
    private void verbind() {
        try {
            ftpClient.connect(ftpGegevens.getHost(), ftpGegevens.getPort()); //Probeer te verbinden
            verbonden = true;
        } catch (FTPException | FTPIllegalReplyException | IOException e) {
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
            error = "Inloggen op de FTP server is mislukt.";
            ingelogd = false;
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
    
    
}
