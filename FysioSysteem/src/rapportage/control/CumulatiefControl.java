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

public class CumulatiefControl {
    private DefaultTableModel model;
    
    /**
     * Constructor voor CumulatiefControl, waar alle logica in zit.
     * @param model Het huidige model van de jTable in CumulatiefGUI
     */
    public CumulatiefControl(DefaultTableModel model){
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
     * Zoekfunctie. Zoekt binnen een bepaalde periode.
     * @param beginDatum beginDatum van de zoekperiode
     * @param eindDatum eindDatum van de zoekperiode
     * @param cumulatiefModel Huidige model van de jTable
     * @return successboolean.
     */
    public boolean zoek(Date beginDatum, Date eindDatum, DefaultTableModel cumulatiefModel) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Print functie. Geeft een PDF bestand met de inhoud van de jTable in CumulatiefGUI
     * @param cumulatiefModel Huidige model van de jTable
     */
    public void print(DefaultTableModel cumulatiefModel){
        throw new UnsupportedOperationException();
    }
    
    /**
     * Haal gegevens op. Deze functie bevat een link naar subsysteem 2: Behandelingen.
     * @param beginDatum Vroegste datum van de gegevens die opgehaald moeten worden
     * @param eindDatum Laatste datum van de gegevens die opgehaald moeten worden
     * @param cumulatiefModel Huidige model van de jTable
     * @return successboolean.
     */
    public boolean getGegevens(Date beginDatum, Date eindDatum, DefaultTableModel cumulatiefModel){
        throw new UnsupportedOperationException();
    }
    
    /**
     * Sorteer functionaliteit
     * @param onderwerp Het onderwerp waarop gesorteerd wordt
     * @param cumulatiefModel Het huidige model
     * @return successboolean.
     */
    public boolean sorteer(String onderwerp, DefaultTableModel cumulatiefModel){
        throw new UnsupportedOperationException();
    }
    
}
