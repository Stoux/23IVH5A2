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
        ArrayList<Behandeling> gevonden = new ArrayList<>();
        ArrayList<Integer> count = new ArrayList<>();
        Integer one = 1;
        for (Behandeling b : behandelingen) {
            if (!b.getBegintijd().before(beginDatum) && !b.getEindtijd().after(eindDatum)) {
                boolean found = false;
                for (Behandeling bh : gevonden) {
                    if (bh.getBehandelingscode().equals(b.getBehandelingscode()) && bh.getFysiotherapeutBSN() == b.getFysiotherapeutBSN()) {
                        int index = gevonden.indexOf(bh);
                        Integer newCount = count.get(index) + 1;
                        count.set(index, newCount);
                        found = true;
                    }
                }
                if (found == false) {
                    gevonden.add(b);
                    count.add(one);
                } else {
                    found = false;
                }
            }
        }
        for (Behandeling b : gevonden) {
            int index = gevonden.indexOf(b);
            ArrayList <Therapeut> therapeuten = manager.getTherapeuten();
            Therapeut therapeut = null;
            for(Therapeut th: therapeuten){
                if(th.getBsn() == b.getFysiotherapeutBSN()){
                    therapeut = th;
                    break;
                }
            }
            SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
            cumulatiefModel.addRow(new Object[]{therapeut.getPraktijkKvk(), b.getFysiotherapeutBSN(), b.getBehandelingscode(), count.get(index), sf.format(b.getBegintijd()), b.getOpmerking()});
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
