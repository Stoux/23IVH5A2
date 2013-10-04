package data.entity;

import java.io.Serializable;

/**
 *
 * @author Leon Stam
 */
public class FtpGegevens implements Serializable {
    
    private String host;
    private int port;
    private String loginNaam;
    private String loginWachtwoord;

    /**
     * Constructor van FtpGegevens
     * @param host De host naam
     * @param port De verbindings port
     * @param loginNaam De login naam
     * @param loginWachtwoord Het login wachtwoord
     */
    public FtpGegevens(String host, int port, String loginNaam, String loginWachtwoord) {
        this.host = host;
        this.port = port;
        this.loginNaam = loginNaam;
        this.loginWachtwoord = loginWachtwoord;
    }

    /**
     * Get de host naam
     * @return de host
     */
    public String getHost() {
        return host;
    }

    /**
     * Get de login naam
     * @return de login naam
     */
    public String getLoginNaam() {
        return loginNaam;
    }

    /**
     * Get het login wachtwoord
     * @return het wachtwoord
     */
    public String getLoginWachtwoord() {
        return loginWachtwoord;
    }

    /**
     * Get de verbindings port
     * @return de port 
     */
    public int getPort() {
        return port;
    }

    /**
     * Set de host naam
     * @param host de host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Set de login naam
     * @param loginNaam de login naam
     */
    public void setLoginNaam(String loginNaam) {
        this.loginNaam = loginNaam;
    }

    /**
     * Set het login wachtwoord
     * @param loginWachtwoord het login wachtwoord
     */
    public void setLoginWachtwoord(String loginWachtwoord) {
        this.loginWachtwoord = loginWachtwoord;
    }

    /**
     * Set de verbindings port
     * @param port de port
     */
    public void setPort(int port) {
        this.port = port;
    }
    
    
    
    
    
    
}
