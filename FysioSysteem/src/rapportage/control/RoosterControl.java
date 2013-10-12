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
/**
 *
 * @author Rogier Welten
 */
public class RoosterControl {

    private ArrayList<Behandeling> behandelingen;
    private BehandelingManager bManager;
    private DateFormat dagUur;
    private DateFormat dagWeek;
    private DefaultTableModel model;   
    private String[] maandag;
    private String[] dinsdag;
    private String[] woensdag;
    private String[] donderdag;
    private String[] vrijdag;

    /**
     * Constructor voor RoosterControl
     *
     * @param model Het huidige model van de JTable in RoosterGUI
     * @param bManager de BehandelingManager
     */
    public RoosterControl(DefaultTableModel model, BehandelingManager bManager) {
        this.model = model;
        this.bManager = bManager;

        //Maak de DateFormats
        dagUur = new SimpleDateFormat("k"); // Uur op de dag (bijvoorbeeld 9)
        dagWeek = new SimpleDateFormat("u"); // Dag in de week (Maandag = 1 en Zondag = 7)
    }

    /**
     * Methode die het TableModel terug geeft, waarmee de GUI de JTable opnieuw kan vullen.
     *
     * @return het meest recente TableModel.
     */
    public DefaultTableModel getModel() {
        return model;
    }

    /**
     *  Methode die de string uit de JComboBox splitst, zodat het BurgerServiceNummer overblijft
     * 
     * @param comboBoxString String van de JComboBox (BSN | Achternaam, Voornaam Tussenvoegsel)
     */
    public int zoekTherapeutBSN(String comboBoxString) {
        return Integer.parseInt(comboBoxString.split(" | ")[0]); //Splitsen van de String, zodat alleen het BurgerServiceNummer overblijft
    }

    /**
     * Methode die het rooster van een specifieke fysiotherapeut maakt, binnen de begin- en eindDatum
     *
     * @param beginDatum Datum van het begin van de werkweek
     * @param eindDatum Datum van het einde van de werkweek
     * @param comboBoxString BSN en naam van de comboBoxString waarvan het rooster gemaakt wordt
     * @return succes boolean
     */
    public boolean maakRooster(Date beginDatum, Date eindDatum, String comboBoxString) {
        boolean succes = false;
        behandelingen = bManager.getBehandelingen(); // ArrayList ophalen met Behandelingen uit de BehandelManager 
        int therapeutBSN = zoekTherapeutBSN(comboBoxString); // BurgerServiceNummer van de fysiotherapeut halen uit de String 
        
        //Maak de String[]'s elke keer dat rooster opnieuw gemaakt wordt
        maandag = new String[8];
        dinsdag = new String[8];
        woensdag = new String[8];
        donderdag = new String[8];
        vrijdag = new String[8];
        if (!behandelingen.isEmpty()) { // Als de ArrayList met behandelingen niet leeg is, wordt onderstaande uitgevoerd
            succes = true;
            for (Behandeling b : behandelingen) { // Voor elke Behandeling in de ArrayList wordt onderstaande uitgevoerd
                int fysiotherapeutBSN = b.getFysiotherapeutBSN(); // Ophalen van het BSN van de fysiotherapeut uit de behandeling
                if (therapeutBSN == fysiotherapeutBSN) { //BSN wordt vergeleken, als dit gelijk is, wordt onderstaande uitgevoerd
                    if (!b.getBegintijd().before(beginDatum) && !b.getEindtijd().after(eindDatum)) { //controle of begintijd en eindtijd van de behandeling binnen de week vallen die getoond wordt
                        int uurBehandeling = Integer.parseInt(dagUur.format(b.getBegintijd())); //Het uur waarin de behandeling plaatsvindt
                        int dagBehandeling = Integer.parseInt(dagWeek.format(b.getBegintijd())); // De dagnummer in de week waarop de behandeling plaatsvindt

                        switch (dagBehandeling) { //Switch voor de dag in de week waarop de behandeling plaatsvindt
                            case 1: vulUurRooster(maandag, b, uurBehandeling);    break; // als het dagnummer 1 is, wordt de methode VulUurRooster uitgevoerd
                            case 2: vulUurRooster(dinsdag, b, uurBehandeling);    break; // als het dagnummer 2 is, wordt de methode VulUurRooster uitgevoerd
                            case 3: vulUurRooster(woensdag, b, uurBehandeling);   break; // als het dagnummer 3 is, wordt de methode VulUurRooster uitgevoerd
                            case 4: vulUurRooster(donderdag, b, uurBehandeling);  break; // als het dagnummer 4 is, wordt de methode VulUurRooster uitgevoerd
                            case 5: vulUurRooster(vrijdag, b, uurBehandeling);    break; // als het dagnummer 5 is, wordt de methode VulUurRooster uitgevoerd
                        }
                    }
                }
            }
            model.setRowCount(0); //legen van het TableModel
            for (int i = 0; i < 8; i++) { //8 keer de loop doorlopen
                model.addRow(new Object[]{maandag[i], dinsdag[i], woensdag[i], donderdag[i], vrijdag[i]}); //rij toevoegen aan het TableModel
            }
        }
        return succes;
    }

    /**
     * Methode die de behandeling in het juiste uur van de dag zet
     *
     * @param dag De String[] van de meegegeven dag
     * @param b De behandeling
     * @param uur Het uur waarin de behandeling plaatsvindt
     */
    private void vulUurRooster(String[] dag, Behandeling b, int uur) {
        switch (uur) {
            case 9:  dag[0] = getBehandelingString(b); break; // als het uur 9  is, wordt de methode getBehandelingString uitgevoerd
            case 10: dag[1] = getBehandelingString(b); break; // als het uur 10 is, wordt de methode getBehandelingString uitgevoerd
            case 11: dag[2] = getBehandelingString(b); break; // als het uur 11 is, wordt de methode getBehandelingString uitgevoerd
            case 12: dag[3] = getBehandelingString(b); break; // als het uur 12 is, wordt de methode getBehandelingString uitgevoerd
            case 13: dag[4] = getBehandelingString(b); break; // als het uur 13 is, wordt de methode getBehandelingString uitgevoerd
            case 14: dag[5] = getBehandelingString(b); break; // als het uur 14 is, wordt de methode getBehandelingString uitgevoerd
            case 15: dag[6] = getBehandelingString(b); break; // als het uur 15 is, wordt de methode getBehandelingString uitgevoerd
            case 16: dag[7] = getBehandelingString(b); break; // als het uur 16 is, wordt de methode getBehandelingString uitgevoerd

        }
    }

    /**
     * Methode die de behandeling string op (naam + behandelcode) haalt uit de behandeling
     *
     * @param b De behandeling
     * @return de string van de behandeling (naam patiënt + behandelcode)
     */
    private String getBehandelingString(Behandeling b) {
        return bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode(); // teruggeven van de String (naam patiënt + behandelcode)
    }
}
