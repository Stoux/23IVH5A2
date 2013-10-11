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
    public long zoekTherapeutBSN(String fysiotherapeutNaam) {
        therapeuten = bManager.getTherapeuten();
        long nummer = 0;

        for (Therapeut t : therapeuten) {
            if (t.getVolledigeNaam().equals(fysiotherapeutNaam)) {
                nummer = t.getBsn();
            }
        }
        return nummer;
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
        long therapeutBSN = zoekTherapeutBSN(therapeut);
        DateFormat dagUur = new SimpleDateFormat("k");
        DateFormat dagWeek = new SimpleDateFormat("u");
        DateFormat dagMaand = new SimpleDateFormat("dd/MM");
        String behandelingenNegenUurMaandag = " ";
        String behandelingenNegenUurDinsdag = " ";
        String behandelingenNegenUurWoensdag = " ";
        String behandelingenNegenUurDonderdag = " ";
        String behandelingenNegenUurVrijdag = " ";
        String behandelingenTienUurMaandag = " ";
        String behandelingenTienUurDinsdag = " ";
        String behandelingenTienUurWoensdag = " ";
        String behandelingenTienUurDonderdag = " ";
        String behandelingenTienUurVrijdag = " ";
        String behandelingenElfUurMaandag = " ";
        String behandelingenElfUurDinsdag = " ";
        String behandelingenElfUurWoensdag = " ";
        String behandelingenElfUurDonderdag = " ";
        String behandelingenElfUurVrijdag = " ";
        String behandelingenTwaalfUurMaandag = " ";
        String behandelingenTwaalfUurDinsdag = " ";
        String behandelingenTwaalfUurWoensdag = " ";
        String behandelingenTwaalfUurDonderdag = " ";
        String behandelingenTwaalfUurVrijdag = " ";
        String behandelingenEenUurMaandag = " ";
        String behandelingenEenUurDinsdag = " ";
        String behandelingenEenUurWoensdag = " ";
        String behandelingenEenUurDonderdag = " ";
        String behandelingenEenUurVrijdag = " ";
        String behandelingenTweeUurMaandag = " ";
        String behandelingenTweeUurDinsdag = " ";
        String behandelingenTweeUurWoensdag = " ";
        String behandelingenTweeUurDonderdag = " ";
        String behandelingenTweeUurVrijdag = " ";
        String behandelingenDrieUurMaandag = " ";
        String behandelingenDrieUurDinsdag = " ";
        String behandelingenDrieUurWoensdag = " ";
        String behandelingenDrieUurDonderdag = " ";
        String behandelingenDrieUurVrijdag = " ";
        String behandelingenVierUurMaandag = " ";
        String behandelingenVierUurDinsdag = " ";
        String behandelingenVierUurWoensdag = " ";
        String behandelingenVierUurDonderdag = " ";
        String behandelingenVierUurVrijdag = " ";







        for (Behandeling b : behandelingen) {
            int fysiotherapeutBSN = b.getBurgerServiceNummer();
            if (therapeutBSN == fysiotherapeutBSN) {
                int beginTijdBehandeling = Integer.parseInt(dagUur.format(b.getBegintijd()));
                int dagBehandeling = Integer.parseInt(dagWeek.format(b.getBegintijd()));

                if (beginTijdBehandeling == 9) {


                    if (dagBehandeling == 1) {
                        behandelingenNegenUurMaandag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 2) {
                        behandelingenNegenUurDinsdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 3) {
                        behandelingenNegenUurWoensdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 4) {
                        behandelingenNegenUurDonderdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 5) {
                        behandelingenNegenUurVrijdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                }
                if (beginTijdBehandeling == 10) {


                    if (dagBehandeling == 1) {
                        behandelingenTienUurMaandag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 2) {
                        behandelingenTienUurDinsdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 3) {
                        behandelingenTienUurWoensdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 4) {
                        behandelingenTienUurDonderdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 5) {
                        behandelingenTienUurVrijdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }

                }
                if (beginTijdBehandeling == 11) {


                    if (dagBehandeling == 1) {
                        behandelingenElfUurMaandag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 2) {
                        behandelingenElfUurDinsdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 3) {
                        behandelingenElfUurWoensdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 4) {
                        behandelingenElfUurDonderdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 5) {
                        behandelingenElfUurVrijdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }

                }
                if (beginTijdBehandeling == 12) {


                    if (dagBehandeling == 1) {
                        behandelingenTwaalfUurMaandag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 2) {
                        behandelingenTwaalfUurDinsdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 3) {
                        behandelingenTwaalfUurWoensdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 4) {
                        behandelingenTwaalfUurDonderdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 5) {
                        behandelingenTwaalfUurVrijdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }

                }
                if (beginTijdBehandeling == 13) {


                    if (dagBehandeling == 1) {
                        behandelingenEenUurMaandag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 2) {
                        behandelingenEenUurDinsdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 3) {
                        behandelingenEenUurWoensdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 4) {
                        behandelingenEenUurDonderdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 5) {
                        behandelingenEenUurVrijdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }

                }
                if (beginTijdBehandeling == 14) {


                    if (dagBehandeling == 1) {
                        behandelingenTweeUurMaandag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 2) {
                        behandelingenTweeUurDinsdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 3) {
                        behandelingenTweeUurWoensdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 4) {
                        behandelingenTweeUurDonderdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 5) {
                        behandelingenTweeUurVrijdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }

                }

                if (beginTijdBehandeling == 15) {


                    if (dagBehandeling == 1) {
                        behandelingenDrieUurMaandag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 2) {
                        behandelingenDrieUurDinsdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 3) {
                        behandelingenDrieUurWoensdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 4) {
                        behandelingenDrieUurDonderdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 5) {
                        behandelingenDrieUurVrijdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }

                }
                if (beginTijdBehandeling == 16) {


                    if (dagBehandeling == 1) {
                        behandelingenVierUurMaandag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 2) {
                        behandelingenVierUurDinsdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 3) {
                        behandelingenVierUurWoensdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 4) {
                        behandelingenVierUurDonderdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    if (dagBehandeling == 5) {
                        behandelingenVierUurVrijdag = dagUur.format(b.getBegintijd()) + ":00-" + dagUur.format(b.getEindtijd()) + ":00\n" + bManager.getPatient(b.getBurgerServiceNummer()) + "\n" + b.getBehandelingscode();
                    }
                    
                }
            }


        }
        model.setRowCount(0);
        model.addRow(new Object[]{behandelingenNegenUurMaandag, behandelingenNegenUurDinsdag, behandelingenNegenUurWoensdag, behandelingenNegenUurDonderdag, behandelingenNegenUurVrijdag});
        model.addRow(new Object[]{behandelingenTienUurMaandag, behandelingenTienUurDinsdag, behandelingenTienUurWoensdag, behandelingenTienUurDonderdag, behandelingenTienUurVrijdag});
        model.addRow(new Object[]{behandelingenElfUurMaandag, behandelingenElfUurDinsdag, behandelingenElfUurWoensdag, behandelingenElfUurDonderdag, behandelingenElfUurVrijdag});
        model.addRow(new Object[]{behandelingenTwaalfUurMaandag, behandelingenTwaalfUurDinsdag, behandelingenTwaalfUurWoensdag, behandelingenTwaalfUurDonderdag, behandelingenTwaalfUurVrijdag});
        model.addRow(new Object[]{behandelingenEenUurMaandag, behandelingenEenUurDinsdag, behandelingenEenUurWoensdag, behandelingenEenUurDonderdag, behandelingenEenUurVrijdag});
        model.addRow(new Object[]{behandelingenTweeUurMaandag, behandelingenTweeUurDinsdag, behandelingenTweeUurWoensdag, behandelingenTweeUurDonderdag, behandelingenTweeUurVrijdag});
        model.addRow(new Object[]{behandelingenDrieUurMaandag, behandelingenDrieUurDinsdag, behandelingenDrieUurWoensdag, behandelingenDrieUurDonderdag, behandelingenDrieUurVrijdag});
        model.addRow(new Object[]{behandelingenVierUurMaandag, behandelingenVierUurDinsdag, behandelingenVierUurWoensdag, behandelingenVierUurDonderdag, behandelingenVierUurVrijdag});






        return succes;
    }
}
