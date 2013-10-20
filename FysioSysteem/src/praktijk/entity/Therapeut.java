package praktijk.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Bevat alle informatie van een fysiotherapeut
 * @author Dennis
 */
public class Therapeut implements Serializable {
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;
    private Geslacht geslacht;
    private int bsn;
    private String postcode;
    private String huisnummer;
    private String telefoonnummer;
    private long praktijkKvk;

    /**
     * Maakt een nieuwe fysiotherapeut aan en stelt alle informatie in
     * @param voornaam voornaam van de therapeut
     * @param tussenvoegsel tussenvoegsel van de therapeut
     * @param achternaam achternaam van de therapeut
     * @param geboortedatum geboortedatum van de therapeut
     * @param geslacht geslacht van de therapeut
     * @param bsn bsn van de therapeut
     * @param postcode postcode van de therapeut
     * @param huisnummer huisnummer van de therapeut
     * @param telefoonnummer telefoonnummer van de therapeut 
     * @param praktijkKvk KVK-nummer van de praktijk waar de therapeut werkzaam is
     */
    public Therapeut (String voornaam, String tussenvoegsel, String achternaam, Date geboortedatum, Geslacht geslacht, int bsn, String postcode, String huisnummer, String telefoonnummer, long praktijkKvk) {
        this.voornaam = voornaam;
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
     * Comparator voor het sorteren van therapeuten.
     * Er wordt gesorteerd op de naam van de therapeut.
     */
    public static Comparator<Therapeut> therapeutNameComparator = new Comparator<Therapeut>() {
        @Override
        public int compare(Therapeut therapeut1, Therapeut therapeut2) {
            //omzetten naar kleine letters, maakt het sorter niet hoofdlettergevoelig
            String naam1 = therapeut1.getVolledigeNaam().toLowerCase();
            String naam2 = therapeut2.getVolledigeNaam().toLowerCase();
            //compare de strings
            return naam1.compareTo(naam2);
        }
    };
    
    /**
     * Geeft de voornaam van de therapeut terug
     * @return de voornaam van de therapeut
     */
    public String getVoornaam() {
        return voornaam;
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
     * Geeft de volledige naam van de therapeut terug
     * @return de naam in het format <i>Achternaam, Voornaam tussenvoegsels</i>
     */
    public String getVolledigeNaam() {
        String naam = achternaam + ", " + voornaam;
        if (!tussenvoegsel.isEmpty())
            naam += " " + tussenvoegsel;
        return naam;
    }

    /**
     * Geeft de geboortedatum van de therapeut terug
     * @return de geboortedatum van de therapeut
     */
    public Date getGeboortedatum() {
        return geboortedatum;
    }
    
    /**
     * Geeft de geboortedatum van de therapeut als string terug
     * @return de geboortedatum van de therapeut als string in het format <i>dd-MM-yyyy</i>
     */
    public String getGeboortedatumFormatted() {
        return new SimpleDateFormat("dd-MM-yyyy").format(geboortedatum);
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
    public String getTelnr() {
        return telefoonnummer;
    }
    
    /**
     * Geeft het KVK-nummer van de fysiotherapiepraktijk terug, waar de therapeut werkzaam is
     * @return het KVK-nummer van de praktijk
     */
    public long getPraktijkKvk() {
        return praktijkKvk;
    }

    /**
     * Wijzig de voornaam van de therapeut
     * @param voornaam De nieuwe voornaam
     */
    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
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
    public void setTelnr(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    /**
     * Wijzig de praktijk waar de therapeut werkzaam is
     * @param praktijkKvk Het nieuwe KVK-nummer van de praktijk
     */
    public void setPraktijkKvk(long praktijkKvk) {
        this.praktijkKvk = praktijkKvk;
    }
    
    /**
     * Geslacht van de therapeut.
     * Kan mannelijk of vrouwelijk zijn.
     */
    public enum Geslacht {
        /**
         * Geslacht is mannelijk
         */
        Mannelijk,
        /**
         * Geslacht is vrouwelijk
         */
        Vrouwelijk;
    }
}
