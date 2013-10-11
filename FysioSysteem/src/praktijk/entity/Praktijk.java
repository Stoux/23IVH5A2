package praktijk.entity;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Bevat alle informatie van een fysiotherapiepraktijk
 * @author Dennis
 */
public class Praktijk implements Serializable {
    private String naam;
    private String plaats;
    private String postcode;
    private String huisnummer;
    private long kvkNummer;
    private String iban;
    private String telefoonnummer;
    private String faxnummer;
    
    /**
     * Maakt een nieuwe fysiotherapiepraktijk aan en stelt alle informatie in
     * @param naam naam van de praktijk naam van de praktijk
     * @param plaats plaats van de praktijk
     * @param postcode postcode van de praktijk
     * @param huisnummer huisnummer van de praktijk
     * @param kvkNummer kvkNummer van de praktijk
     * @param iban iban van de praktijk
     * @param telefoonnummer telefoonnummer van de praktijk
     * @param faxnummer faxnummer van de praktijk 
     */
    public Praktijk(String naam, String plaats, String postcode, String huisnummer, long kvkNummer, String iban, String telefoonnummer, String faxnummer) {
        this.naam = naam;
        this.plaats = plaats;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.kvkNummer = kvkNummer;
        this.iban = iban;
        this.telefoonnummer = telefoonnummer;
        this.faxnummer = faxnummer;
    }
    
    /**
     * Comparator voor het sorteren van praktijken.
     * Er wordt gesorteerd op de naam van de praktijk.
     */
    public static Comparator<Praktijk> praktijkNameComparator = new Comparator<Praktijk>() {
        @Override
        public int compare(Praktijk praktijk1, Praktijk praktijk2) {
            //omzetten naar kleine letters, maakt het sorter niet hoofdlettergevoelig
            String naam1 = praktijk1.getNaam().toLowerCase();
            String naam2 = praktijk2.getNaam().toLowerCase();
            //compare de strings
            return naam1.compareTo(naam2);
        }
    };
    
    /**
     * Geeft de naam van de praktijk terug
     * @return de naam van de praktijk
     */
    public String getNaam() {
        return naam;
    }

    /**
     * Geeft de plaatsnaam van de praktijk terug
     * @return de plaats waarin de praktijk gevestigd is
     */
    public String getPlaats() {
        return plaats;
    }

    /**
     * Geeft de postcode van de praktijk terug
     * @return de postcode van de vestigingsplaats
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Geeft het huisnummer van de praktijk terug
     * @return het huisnummer van de vestigingsplaats
     */
    public String getHuisnummer() {
        return huisnummer;
    }

    /**
     * Geeft het KVK-nummer van de praktijk terug
     * @return het KVK-nummer
     */
    public long getKvk() {
        return kvkNummer;
    }

    /**
     * Geeft het IBAN-nummer (rekeningnummer) van de praktijk terug
     * @return het IBAN-nummer
     */
    public String getIban() {
        return iban;
    }

    /**
     * Geeft het telefoonnummer van de praktijk terug
     * @return het telefoonnummer waarop de praktijk bereikbaar is
     */
    public String getTelnr() {
        return telefoonnummer;
    }

    /**
     * Geeft het faxnummer van de praktijk terug
     * @return het faxnummer waarop de praktijk bereikbaar is
     */
    public String getFaxnr() {
        return faxnummer;
    }

    /**
     * Wijzig de naam van de praktijk
     * @param naam De nieuwe naam
     */
    public void setNaam(String naam) {
        this.naam = naam;
    }

    /**
     * Wijzig de plaatsnaam van de praktijk
     * @param plaats De nieuwe plaatsnaam
     */
    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    /**
     * Wijzig de postcode van de praktijk
     * @param postcode De nieuwe postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * Wijzig het huisnummer van de praktijk
     * @param huisnummer Het nieuwe huisnummer
     */
    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    /**
     * Wijzig het IBAN-nummer van de praktijk
     * @param iban Het nieuwe IBAN-nummer
     */
    public void setIban(String iban) {
        this.iban = iban;
    }

    /**
     * Wijzig het telefoonnummer van de praktijk
     * @param telefoonnummer Het nieuwe telefoonnummer
     */
    public void setTelnr(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    /**
     * Wijzig het faxnummer van de praktijk
     * @param faxnummer 
     */
    public void setFaxnr(String faxnummer) {
        this.faxnummer = faxnummer;
    }
}
