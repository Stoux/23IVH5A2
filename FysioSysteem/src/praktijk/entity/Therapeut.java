package praktijk.entity;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

/**
 * Bevat alle informatie van een fysiotherapeut
 * @author Dennis
 */
public class Therapeut implements Serializable {
    private String naam;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;
    private Geslacht geslacht;
    private long bsn;
    private String postcode;
    private String huisnummer;
    private long telefoonnummer;
    private long praktijkKvk;

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
     * @param praktijkKvk KVK-nummer van de praktijk waar de therapeut werkzaam is
     */
    public Therapeut (String naam, String tussenvoegsel, String achternaam, Date geboortedatum, Geslacht geslacht, long bsn, String postcode, String huisnummer, long telefoonnummer, long praktijkKvk) {
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
     * Comparator voor het sorteren van therapeuten.
     * Er wordt gesorteerd op de naam van de therapeut.
     */
    public static Comparator<Therapeut> therapeutNameComparator = new Comparator<Therapeut>() {
        // TODO: Comparator voor therapeuten: theorie, nog niet getest!
        @Override
        public int compare(Therapeut therapeut1, Therapeut therapeut2) {
            //omzetten naar kleine letters, maakt het sorter niet hoofdlettergevoelig
            String naam1 = therapeut1.getNaam().toLowerCase();
            String naam2 = therapeut2.getNaam().toLowerCase();
            //compare de strings
            return naam1.compareTo(naam2);
        }
    };
    
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
    public long getBsn() {
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
    public long getTelnr() {
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
    public void setTelnr(long telefoonnummer) {
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
        Mannelijk, Vrouwelijk;
    }
}
