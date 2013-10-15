/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rapportage.control;

import behandel.control.BehandelingManager;
import behandel.entity.Behandeling;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import praktijk.entity.Therapeut;

/**
 *
 * @author Jan
 */
public class CumulatiefControl {

    private DefaultTableModel model;
    private BehandelingManager manager;

    /**
     * Constructor voor CumulatiefControl, waar alle logica in zit.
     *
     * @param model Het huidige model van de jTable in CumulatiefGUI
     * @param manager De behandelmanager, waarmee de data opgehaald wordt
     */
    public CumulatiefControl(DefaultTableModel model, BehandelingManager manager) {
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
     * Zoekfunctie. Zoekt binnen een bepaalde periode.
     *
     * @param beginDatum beginDatum van de zoekperiode
     * @param eindDatum eindDatum van de zoekperiode
     * @param cumulatiefModel Huidige model van de jTable
     * @return successboolean.
     */
    public boolean zoek(Date beginDatum, Date eindDatum, DefaultTableModel cumulatiefModel) {
        boolean success = getGegevens(beginDatum, eindDatum, cumulatiefModel);
        return success;
    }

    /**
     * Haal gegevens op. Deze functie bevat een link naar subsysteem 2:
     * Behandelingen.
     *
     * @param beginDatum Vroegste datum van de gegevens die opgehaald moeten
     * worden
     * @param eindDatum Laatste datum van de gegevens die opgehaald moeten
     * worden
     * @param cumulatiefModel Huidige model van de jTable
     * @return successboolean.
     */
    public boolean getGegevens(Date beginDatum, Date eindDatum, DefaultTableModel cumulatiefModel) {
        boolean success = false;
        cumulatiefModel.setRowCount(0);
        
        ArrayList<Behandeling> behandelingen = manager.getBehandelingen();
        ArrayList <Therapeut> therapeuten = manager.getTherapeuten();
        
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        
        HashMap<Integer, HashMap<String, Integer>> gevondenTherapeuten = new HashMap<>();
        
        for (Behandeling b : behandelingen) {
            if (!b.getBegintijd().before(beginDatum) && !b.getEindtijd().after(eindDatum)) {
                int bsn = b.getFysiotherapeutBSN();
                HashMap<String, Integer> th = gevondenTherapeuten.get(bsn);
                if (th == null) { //Zit nog niet in de hashmap
                    HashMap<String, Integer> behandeling = new HashMap<>();
                    behandeling.put(b.getBehandelingscode(), 1);
                    gevondenTherapeuten.put(bsn,behandeling);
                } else { //Zit in de hashmap
                    Integer aantal = th.get(b.getBehandelingscode());
                    if (aantal != null) { //Code is al toegevoegd
                        aantal++;
                        th.put(b.getBehandelingscode(), aantal);
                    } else { //Nog niet toegevoegd
                        th.put(b.getBehandelingscode(), 1);
                    }
                }
            }
        }
        for (Map.Entry<Integer, HashMap<String, Integer>> entry : gevondenTherapeuten.entrySet()) {
            Therapeut t = null;
            for (Therapeut therapeut : therapeuten) {
                if (therapeut.getBsn() == entry.getKey()) {
                    t = therapeut;
                    break;
                }
            }
            for (Map.Entry<String, Integer> therapeutEntry : entry.getValue().entrySet()) {
                cumulatiefModel.addRow(new Object[]{t.getPraktijkKvk(), entry.getKey(), therapeutEntry.getKey(), therapeutEntry.getValue()});
            }
            
            
        }
        if (cumulatiefModel.getRowCount() > 0) {
            success = true;
        }
        if (success == true) {
            model = cumulatiefModel;
        }
        return success;
    }
}
