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
        if (!behandelingen.isEmpty()) {
            succes = true;
            for (Behandeling b : behandelingen) {
                int fysiotherapeutBSN = b.getFysiotherapeutBSN();
                if (therapeutBSN == fysiotherapeutBSN && !b.getBegintijd().before(beginDatum) && !b.getEindtijd().after(eindDatum)) {
                    int beginTijdBehandeling = Integer.parseInt(dagUur.format(b.getBegintijd()));
                    int dagBehandeling = Integer.parseInt(dagWeek.format(b.getBegintijd()));
                    if (beginTijdBehandeling == 9) {
                        if (dagBehandeling == 1) {
                            behandelingenNegenUurMaandag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 2) {
                            behandelingenNegenUurDinsdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 3) {
                            behandelingenNegenUurWoensdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 4) {
                            behandelingenNegenUurDonderdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 5) {
                            behandelingenNegenUurVrijdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                    }
                    if (beginTijdBehandeling == 10) {
                        if (dagBehandeling == 1) {
                            behandelingenTienUurMaandag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 2) {
                            behandelingenTienUurDinsdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 3) {
                            behandelingenTienUurWoensdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 4) {
                            behandelingenTienUurDonderdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 5) {
                            behandelingenTienUurVrijdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                    }
                    if (beginTijdBehandeling == 11) {
                        if (dagBehandeling == 1) {
                            behandelingenElfUurMaandag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 2) {
                            behandelingenElfUurDinsdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 3) {
                            behandelingenElfUurWoensdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 4) {
                            behandelingenElfUurDonderdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 5) {
                            behandelingenElfUurVrijdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                    }
                    if (beginTijdBehandeling == 12) {
                        if (dagBehandeling == 1) {
                            behandelingenTwaalfUurMaandag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 2) {
                            behandelingenTwaalfUurDinsdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 3) {
                            behandelingenTwaalfUurWoensdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 4) {
                            behandelingenTwaalfUurDonderdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 5) {
                            behandelingenTwaalfUurVrijdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                    }
                    if (beginTijdBehandeling == 13) {
                        if (dagBehandeling == 1) {
                            behandelingenEenUurMaandag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 2) {
                            behandelingenEenUurDinsdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 3) {
                            behandelingenEenUurWoensdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 4) {
                            behandelingenEenUurDonderdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 5) {
                            behandelingenEenUurVrijdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                    }
                    if (beginTijdBehandeling == 14) {
                        if (dagBehandeling == 1) {
                            behandelingenTweeUurMaandag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 2) {
                            behandelingenTweeUurDinsdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 3) {
                            behandelingenTweeUurWoensdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 4) {
                            behandelingenTweeUurDonderdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 5) {
                            behandelingenTweeUurVrijdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                    }
                    if (beginTijdBehandeling == 15) {
                        if (dagBehandeling == 1) {
                            behandelingenDrieUurMaandag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 2) {
                            behandelingenDrieUurDinsdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 3) {
                            behandelingenDrieUurWoensdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 4) {
                            behandelingenDrieUurDonderdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 5) {
                            behandelingenDrieUurVrijdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                    }
                    if (beginTijdBehandeling == 16) {
                        if (dagBehandeling == 1) {
                            behandelingenVierUurMaandag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 2) {
                            behandelingenVierUurDinsdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 3) {
                            behandelingenVierUurWoensdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 4) {
                            behandelingenVierUurDonderdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
                        }
                        if (dagBehandeling == 5) {
                            behandelingenVierUurVrijdag = bManager.getPatient(b.getBurgerServiceNummer()) + " " + b.getBehandelingscode();
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
        }
        return succes;
    }
}
