package behandel.entity;

import java.io.Serializable;

/**
 *
 * @author Leon Stam
 */
public class BehandelGegevens implements Serializable {

    private String behandelingscode;
    private String naam;
    private String omschrijving;
    
    /**
     * Maak nieuwe behandelingsgegevens aan.
     * @param behandelingscode De behandelcode
     * @param naam De naam van de behandeling
     * @param omschrijving De omschrijving van de behandeling
     */
    public BehandelGegevens(String behandelingscode, String naam, String omschrijving) {
        this.behandelingscode = behandelingscode;
        this.naam = naam;
        this.omschrijving = omschrijving;
    }

    /**
     * Get de behandelingscode
     * @return de behandelingscode
     */
    public String getBehandelingscode() {
        return behandelingscode;
    }
    
    /**
     * Get de naam
     * @return de naam
     */
    public String getNaam() {
        return naam;
    }

    /**
     * Get de omschrijving
     * @return de omschrijving
     */
    public String getOmschrijving() {
        return omschrijving;
    }
    
    
    
}
