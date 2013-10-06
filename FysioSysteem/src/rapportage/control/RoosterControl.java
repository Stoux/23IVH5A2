/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rapportage.control;

import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jan
 */
public class RoosterControl {
    private DefaultTableModel model;
    
    /**
     * Constructor voor RoosterControl, waar alle logica in zit.
     * @param model Het huidige model van de jTable in RoosterGUI
     */
    public RoosterControl(DefaultTableModel model){
        this.model = model;
    }
    
    /**
     * Geeft het TableModel terug, waarmee de GUI de jTable opnieuw kan vullen.
     * @return het meest recente TableModel.
     */
    public DefaultTableModel getModel(){
        return model;
    }
    
    /**
     * Zoekfunctie therapeut
     * @param therapeutNaam naam van de therapeut waarop gezocht wordt
     * @return successboolean
     */
    public boolean zoekTherapeut(String therapeutNaam){
        throw new UnsupportedOperationException();
    }
    
    /**
     * Functie om het rooster van een specifieke therapeut te maken
     * @param beginDatum Vroegste datum van het rooster
     * @param eindDatum Laatste datum van het rooster
     * @param therapeut naam van de therapeut waarvan het rooster gemaakt wordt
     * @return successboolean
     */
    public boolean maakRooster(Date beginDatum, Date eindDatum, String therapeut){
        throw new UnsupportedOperationException();
    }
    
}
