package praktijk.entity;

/**
 * Bevat alle informatie van een fysiotherapiepraktijk
 * @author Dennis
 */
public class Praktijk {
    private String naam;
    private String plaats;
    private String postcode;
    private String huisnummer;
    private int kvkNummer;
    private String iban;
    private int telefoonnummer;
    private int faxnummer;
    
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
    public Praktijk(String naam, String plaats, String postcode, String huisnummer, int kvkNummer, String iban, int telefoonnummer, int faxnummer) {
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
    public int getKvk() {
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
    public int getTelnr() {
        return telefoonnummer;
    }

    /**
     * Geeft het faxnummer van de praktijk terug
     * @return het faxnummer waarop de praktijk bereikbaar is
     */
    public int getFaxnr() {
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
    public void setTelnr(int telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    /**
     * Wijzig het faxnummer van de praktijk
     * @param faxnummer 
     */
    public void setFaxnr(int faxnummer) {
        this.faxnummer = faxnummer;
    }
}
