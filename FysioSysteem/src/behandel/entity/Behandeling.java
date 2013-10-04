package behandel.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Leon Stam
 */
public class Behandeling implements Serializable {
    
    private int behandelingsID;
    private int bsn;
    private String behandelingscode;
    private String fysiotherapeutcode;
    private Date begintijd;
    private Date eindtijd;
    private Status status;
    private String opmerking;

    /**
     * Constructor voor een behandeling
     * @param behandelingsID het ID
     * @param bsn het Burgerservicenummer
     * @param behandelingscode de behandelingscode
     * @param fysiotherapeutcode de code van de fysiotherapeut
     * @param begintijd de begintijd
     * @param eindtijd de eindtijd
     * @param status de status van de behandeling
     * @param opmerking een eventuele opmerking
     */
    public Behandeling(int behandelingsID, int bsn, String behandelingscode, String fysiotherapeutcode, Date begintijd, Date eindtijd, Status status, String opmerking) {
        this.behandelingsID = behandelingsID;
        this.bsn = bsn;
        this.behandelingscode = behandelingscode;
        this.fysiotherapeutcode = fysiotherapeutcode;
        this.begintijd = begintijd;
        this.eindtijd = eindtijd;
        this.status = status;
        this.opmerking = opmerking;
    }

    /**
     * Get de begintijd
     * @return de tijd
     */
    public Date getBegintijd() {
        return begintijd;
    }

    /**
     * Get het behandelings ID
     * @return het ID
     */
    public int getBehandelingsID() {
        return behandelingsID;
    }

    /**
     * Get de code van de behandeling
     * @return 
     */
    public String getBehandelingscode() {
        return behandelingscode;
    }

    /**
     * Get het burgerservicenummer
     * @return het bsn
     */
    public int getBurgerServiceNummer() {
        return bsn;
    }

    /**
     * Get de eindtijd
     * @return de tijd
     */
    public Date getEindtijd() {
        return eindtijd;
    }

    /**
     * Get de fysiotherapeutcode
     * @return de code van de therapeut
     */
    public String getFysiotherapeutcode() {
        return fysiotherapeutcode;
    }

    /**
     * Get de opmerking
     * @return de opmerking
     */
    public String getOpmerking() {
        return opmerking;
    }

    /**
     * Get de status
     * @return de status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Set de begintijd
     * @param begintijd de tijd
     */
    public void setBegintijd(Date begintijd) {
        this.begintijd = begintijd;
    }

    /**
     * Set de behandelingscode
     * @param behandelingscode de code 
     */
    public void setBehandelingscode(String behandelingscode) {
        this.behandelingscode = behandelingscode;
    }

    /**
     * Set de eindtijd
     * @param eindtijd de tijd 
     */
    public void setEindtijd(Date eindtijd) {
        this.eindtijd = eindtijd;
    }

    /**
     * Set de fysiotherapeutcode
     * @param fysiotherapeutcode de code 
     */
    public void setFysiotherapeutcode(String fysiotherapeutcode) {
        this.fysiotherapeutcode = fysiotherapeutcode;
    }

    /**
     * Set de opmerking
     * @param opmerking de opmerking
     */
    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }

    /**
     * Set de status
     * @param status de status 
     */
    public void setStatus(Status status) {
        this.status = status;
    }
    
    public enum Status {
        Voltooid, Afgezegd, Ingepland;
    }
    
}
