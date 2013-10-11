/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rapportage.control;

import behandel.control.BehandelingManager;
import behandel.entity.Behandeling;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import praktijk.entity.Therapeut;

/**
 *
 * @author Jan
 */
public class RoosterControl {

    private DefaultTableModel model;
    private BehandelingManager bManager;
    private ArrayList<Behandeling> behandelingen;
    private ArrayList<Therapeut> therapeuten;

    /**
     * Constructor voor RoosterControl, waar alle logica in zit.
     *
     * @param model Het huidige model van de jTable in RoosterGUI
     */
    public RoosterControl(DefaultTableModel model, BehandelingManager bManager) {
        this.model = model;
        this.bManager = bManager;
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
     * Zoekfunctie therapeut
     *
     * @param therapeutNaam naam van de therapeut waarop gezocht wordt
     * @return successboolean
     */
    public int zoekTherapeutBSN(String comboBoxString) {
        return Integer.parseInt(comboBoxString.split(" | ")[0]);
    }

    /**
     * Functie om het rooster van een specifieke therapeut te maken
     *
     * @param beginDatum Vroegste datum van het rooster
     * @param eindDatum Laatste datum van het rooster
     * @param therapeut naam van de therapeut waarvan het rooster gemaakt wordt
     * @return successboolean
     */
    public boolean maakRooster(Date beginDatum, Date eindDatum, String therapeut) {
        boolean succes = false;
        behandelingen = bManager.getBehandelingen();
        int therapeutBSN = zoekTherapeutBSN(therapeut);
        DateFormat dagUur = new SimpleDateFormat("k");
        DateFormat dagWeek = new SimpleDateFormat("u");
        DateFormat dagMaand = new SimpleDateFormat("dd/MM");
        String[] maandag = new String[8];
        String[] dinsdag = new String[8];
        String[] woensdag = new String[8];
        String[] donderdag = new String[8];
        String[] vrijdag = new String[8];
        if (!behandelingen.isEmpty()) {
            succes = true;
            for (Behandeling b : behandelingen) {
                int fysiotherapeutBSN = b.getFysiotherapeutBSN();
                if (therapeutBSN == fysiotherapeutBSN) {
                    if (!b.getBegintijd().before(beginDatum) && !b.getEindtijd().after(eindDatum)) {
                        int beginTijdBehandeling = Integer.parseInt(dagUur.format(b.getBegintijd()));
                        int dagBehandeling = Integer.parseInt(dagWeek.format(b.getBegintijd()));

                        switch (dagBehandeling) {
                            case 1: checkUur(maandag, b, beginTijdBehandeling); break;
                            case 2: checkUur(dinsdag, b, beginTijdBehandeling); break;
                            case 3: checkUur(woensdag, b, beginTijdBehandeling); break;
                            case 4: checkUur(donderdag, b, beginTijdBehandeling); break;
                            case 5: checkUur(vrijdag, b, beginTijdBehandeling); break;
                        }
                    }
                }
            }
            model.setRowCount(0);
            for (int i = 0; i < 8; i++) {
                model.addRow(new Object[]{maandag[i], dinsdag[i], woensdag[i], donderdag[i], vrijdag[i]});
            }
        }
        return succes;
    }

    /**
     * Vul het goede uur van een dag
     * @param dag De dag array
     * @param b De behandeling
     * @param uur Het uur
     */
    private void checkUur(String[] dag, Behandeling b, int uur) {
        switch (uur) {
            case 9: dag[0] = getBehandelingString(b); break;
            case 10: dag[1] = getBehandelingString(b); break;
            case 11: dag[2] = getBehandelingString(b); break;
            case 12: dag[3] = getBehandelingString(b); break;
            case 13: dag[4] = getBehandelingString(b); break;
            case 14: dag[5] = getBehandelingString(b); break;
            case 15: dag[6] = getBehandelingString(b); break;
            case 16: dag[7] = getBehandelingString(b); break;

        }
    }
    
    /**
     * Haal de behandeling string op (Naam + Behandelcode)
     * @param b De behandeling
     * @return de string
     */
    private String getBehandelingString(Behandeling b) {
        return bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
    }
}
