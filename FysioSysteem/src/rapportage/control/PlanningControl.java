/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rapportage.control;

import behandel.control.BehandelingManager;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jan
 */
public class PlanningControl {
    private DefaultTableModel model;
    
    /**
     * Constructor voor PlanningControl, waar alle logica in zit.
     * @param model Het huidige model van de jTable in PlanningGUI
     */
    public PlanningControl(DefaultTableModel model, BehandelingManager manager){
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
     * Print functie. Geeft een PDF bestand met de inhoud van de jTable in PlanningGUI
     * @param planningModel Huidige model van de jTable
     */
    public void print(DefaultTableModel planningModel){
        throw new UnsupportedOperationException();
    }
    
    /**
     * Haal gegevens op. Deze functie bevat een link naar subsysteem 2: Behandelingen.
     * @param beginDatum Vroegste datum van de gegevens die opgehaald moeten worden
     * @param eindDatum Laatste datum van de gegevens die opgehaald moeten worden
     * @param planningModel Huidige model van de jTable
     * @return successboolean.
     */
    public boolean getGegevens(Date beginDatum, Date eindDatum, DefaultTableModel planningModel){
        throw new UnsupportedOperationException();
    }
    
    /**
     * Sorteer functionaliteit
     * @param onderwerp Het onderwerp waarop gesorteerd wordt
     * @param planningModel Het huidige model
     * @return successboolean.
     */
    public boolean sorteer(String onderwerp, DefaultTableModel planningModel){
        throw new UnsupportedOperationException();
    }
    
    /**
     * Zoek functie voor een bepaalde periode
     * @param beginDatumbeginDatum van de zoekperiode
     * @param eindDatum eindDatum van de zoekperiode
     * @param planningModel het huidige model
     * @return successboolean
     */
    public boolean zoekDatum(Date beginDatum, Date eindDatum, DefaultTableModel planningModel) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Zoek functie voor een bepaalde periode
     * @param sofiNummer het sofinummer van een patient
     * @param planningModel Het huidige model
     * @return successboolean
     */
    public boolean zoekSofinummer(int sofiNummer, DefaultTableModel planningModel) {
        throw new UnsupportedOperationException();
    }
    
}
