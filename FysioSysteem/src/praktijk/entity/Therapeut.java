package praktijk.entity;

import java.util.Date;

/**
 * Bevat alle informatie van een fysiotherapeut
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
    private int praktijkKvk;

    /**
     * Maakt een nieuwe fysiotherapeut aan en stelt alle informatie in
     * @param tussenvoegsel tussenvoegsel van de therapeut tussenvoegsel van de therapeut
     * @param achternaam achternaam van de therapeut
     * @param geboortedatum geboortedatum van de therapeut
     * @param geslacht geslacht van de therapeut
     * @param bsn bsn van de therapeut
     * @param postcode postcode van de therapeut
     * @param huisnummer huisnummer van de therapeut
     * @param telefoonnummer telefoonnummer van de therapeut 
     */
    public Therapeut (String naam, String tussenvoegsel, String achternaam, Date geboortedatum, Geslacht geslacht, int bsn, String postcode, String huisnummer, int telefoonnummer, int praktijkKvk) {
        this.naam = naam;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        this.geslacht = geslacht;
        this.bsn = bsn;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.telefoonnummer = telefoonnummer;
        this.praktijkKvk = praktijkKvk;
    }
    
    /**
     * Geeft de voornaam van de therapeut terug
     * @return de voornaam van de therapeut
     */
    public String getNaam() {
        return naam;
    }

    /**
     * Geeft het tussenvoegsel van de therapeut terug
     * @return het tussenvoegsel van de therapeut
     */
    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    /**
     * Geeft de achternaam van de therapeut terug
     * @return de achternaam van de therapeut
     */
    public String getAchternaam() {
        return achternaam;
    }

    /**
     * Geeft de geboortedatum van de therapeut terug
     * @return de geboortedatum van de therapeut
     */
    public Date getGeboortedatum() {
        return geboortedatum;
    }

    /**
     * Geeft het geslacht van de therapeut terug
     * @return het geslacht van de therapeut: mannelijk of vrouwelijk
     */
    public Geslacht getGeslacht() {
        return geslacht;
    }

    /**
     * Geeft de BSN-nummer van de therapeut terug
     * @return BSN-nummer van de therapeut
     */
    public int getBsn() {
        return bsn;
    }

    /**
     * Geeft de postcode van de therapeut terug
     * @return postcode van de therapeut
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Geeft het huisnummer van de therapeut terug
     * @return huisnummer
     */
    public String getHuisnummer() {
        return huisnummer;
    }

    /**
     * Geeft het telefoonnummer van de therapeut terug
     * @return telefoonnummer van de therapeut
     */
    public int getTelnr() {
        return telefoonnummer;
    }
    
    /**
     * Geeft het KVK-nummer van de fysiotherapiepraktijk terug, waar de therapeut werkzaam is
     * @return het KVK-nummer van de praktijk
     */
    public int getPraktijkKvk() {
        return praktijkKvk;
    }

    /**
     * Wijzig de voornaam van de therapeut
     * @param naam De nieuwe voornaam
     */
    public void setNaam(String naam) {
        this.naam = naam;
    }

    /**
     * Wijzig het tussenvoegsel van de therapeut
     * @param tussenvoegsel Het nieuwe tussenvoegsel
     */
    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    /**
     * Wijzig de achternaam van de therapeut
     * @param achternaam De nieuwe achternaam
     */
    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    /**
     * Wijzig de geboortedatum van de therapeut
     * @param geboortedatum De nieuwe geboortedatum
     */
    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    /**
     * Wijzig het geslacht van de therapeut
     * @param geslacht Het nieuwe geslacht
     */
    public void setGeslacht(Geslacht geslacht) {
        this.geslacht = geslacht;
    }

    /**
     * Wijzig de postcode van de therapeut
     * @param postcode De nieuwe postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * Wijzig het huisnummer van de therapeut
     * @param huisnummer Het nieuwe huisnummer
     */
    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    /**
     * Wijzig het telefoonnummer van de therapeut
     * @param telefoonnummer Het nieuwe telefoonnummer
     */
    public void setTelnr(int telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }
    
    /**
     * Wijzig de praktijk waar de therapeut werkzaam is
     * @param praktijkKvk Het nieuwe KVK-nummer van de praktijk
     */
    public void setPraktijkKvk(int praktijkKvk) {
        this.praktijkKvk = praktijkKvk;
    }
    
    /**
     * Geslacht van de therapeut.
     * Kan mannelijk of vrouwelijk zijn.
     */
    public enum Geslacht {
        Mannelijk, Vrouwelijk;
    }
}
