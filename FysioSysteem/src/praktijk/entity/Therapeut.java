package praktijk.entity;

import java.util.Date;

/**
 *
 * @author Dennis
 */
public class Therapeut {
    private String naam;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;
    private Geslacht geslacht;
    private int bsn;
    private String postcode;
    private String huisnummer;
    private int telefoonnummer;

    public String getNaam() {
        return naam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public Geslacht getGeslacht() {
        return geslacht;
    }

    public int getBsn() {
        return bsn;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public int getTelnr() {
        return telefoonnummer;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public void setGeslacht(Geslacht geslacht) {
        this.geslacht = geslacht;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public void setTelnr(int telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }
    
    public enum Geslacht {
        Mannelijk, Vrouwelijk;
    }
}