package praktijk.entity;

/**
 *
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

    public String getNaam() {
        return naam;
    }

    public String getPlaats() {
        return plaats;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public int getKvk() {
        return kvkNummer;
    }

    public String getIban() {
        return iban;
    }

    public int getTelnr() {
        return telefoonnummer;
    }

    public int getFaxnr() {
        return faxnummer;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setTelnr(int telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    public void setFaxnr(int faxnummer) {
        this.faxnummer = faxnummer;
    }
}