/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rapportage.control;

import behandel.control.BehandelingManager;
import behandel.entity.Behandeling;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jan
 */
public class PlanningControl {

    private DefaultTableModel model;
    private BehandelingManager manager;

    /**
     * Constructor voor PlanningControl, waar alle logica in zit.
     *
     * @param model Het huidige model van de jTable in PlanningGUI
     * @param manager De behandelmanager, waarmee de data opgehaald wordt
     */
    public PlanningControl(DefaultTableModel model, BehandelingManager manager) {
        this.model = model;
        this.manager = manager;
    }

    /**
     * Geeft het TableModel terug, waarmee de GUI de jTable opnieuw kan vullen.
     *
     * @return het meest recente TableModel.
     */
    public DefaultTableModel getModel() {
        return model;
    }

    /**
     * Print functie. Geeft een PDF bestand met de inhoud van de jTable in
     * PlanningGUI
     *
     * @param planningModel Huidige model van de jTable
     */
    public void print(DefaultTableModel planningModel) {
        throw new UnsupportedOperationException();
    }

    /**
     * Haal gegevens op. Deze functie bevat een link naar subsysteem 2:
     * Behandelingen.
     *
     * @param beginDatum Vroegste datum van de gegevens die opgehaald moeten
     * worden
     * @param eindDatum Laatste datum van de gegevens die opgehaald moeten
     * worden
     * @param planningModel Huidige model van de jTable
     * @return successboolean.
     */
    public boolean getGegevens(Date beginDatum, Date eindDatum, DefaultTableModel planningModel) {
        ArrayList<Behandeling> behandelingen = manager.getBehandelingen();
        boolean success = false;
        planningModel.setRowCount(0);
        for (Behandeling b : behandelingen) {
            if (!b.getBegintijd().before(beginDatum) && !b.getEindtijd().after(eindDatum)) {
                planningModel.addRow(new Object[]{"x", b.getFysiotherapeutBSN(), b.getBurgerServiceNummer(), b.getBehandelingscode(), b.getBegintijd(), b.getOpmerking()});
            }
        }
        if (planningModel.getRowCount() > 0) {
            success = true;
            model = planningModel;
        }
        return success;
    }
    
    public boolean getGegevensVanArrayList(Date beginDatum, Date eindDatum, DefaultTableModel planningModel, ArrayList<Behandeling> behandelingen) {
        boolean success = false;
        planningModel.setRowCount(0);
        for (Behandeling b : behandelingen) {
            if (!b.getBegintijd().before(beginDatum) && !b.getEindtijd().after(eindDatum)) {
                planningModel.addRow(new Object[]{"x", b.getFysiotherapeutBSN(), b.getBurgerServiceNummer(), b.getBehandelingscode(), b.getBegintijd(), b.getOpmerking()});
            }
        }
        if (planningModel.getRowCount() > 0) {
            success = true;
            model = planningModel;
        }
        return success;
    }

    /**
     * Zoek functie voor een bepaalde periode
     *
     * @param beginDatumbeginDatum van de zoekperiode
     * @param eindDatum eindDatum van de zoekperiode
     * @param planningModel het huidige model
     * @return successboolean
     */
    public boolean zoekDatum(Date beginDatum, Date eindDatum, DefaultTableModel planningModel) {
        return getGegevens(beginDatum, eindDatum, planningModel);
    }

    /**
     * Zoek functie voor een bepaalde periode
     *
     * @param sofiNummer het sofinummer van een patient
     * @param planningModel Het huidige model
     * @return successboolean
     */
    public boolean zoekSofinummer(int sofiNummer, DefaultTableModel planningModel) {
        ArrayList<Behandeling> behandelingen = manager.getBehandelingen();
        ArrayList<Behandeling> gesorteerd = new ArrayList<>();
        for(Behandeling b:behandelingen){
            if(b.getBurgerServiceNummer() == sofiNummer){
                gesorteerd.add(b);
            }
        }
        if(gesorteerd.isEmpty()){
            return false;
        } else {
            return getGegevensVanArrayList(new Date(new Date().getTime() - 604800000), new Date(new Date().getTime() + 604800000), planningModel, gesorteerd);
        }
    }
}
