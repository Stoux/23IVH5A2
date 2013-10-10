/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package behandel.boundary;

import behandel.control.BehandelingManager;
import behandel.entity.Behandeling;
import behandel.entity.Behandeling.Status;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.prompt.PromptSupport;
import praktijk.entity.Therapeut;

/**
 *
 * @author Nigel
 */
public class BehandelingGUI extends javax.swing.JFrame {

    /**
     * Creates new form ToevoegenGUI
     */
    private BehandelingManager manager;
    private OverzichtGUI overzichtGUI;
    private Behandeling behandeling;
    
    /**
     * Maak een BehandelingGUI aan, toevoegen variant.
     * @param manager De manager
     * @param gui De main GUI
     */
    public BehandelingGUI(BehandelingManager manager, OverzichtGUI gui) {
        this.manager = manager;
        this.overzichtGUI = gui;
        initComponents();
        vulComboBoxen();
        patientBSNField.setEditable(true);
    }
    
    /**
     * Maak een BehandelingGUI aan, aanpassen variant.
     * @param manager De manager
     * @param gui De GUI
     * @param behandelingsID Het ID van de behandeling
     */
    public BehandelingGUI(BehandelingManager manager, OverzichtGUI gui, int behandelingsID) {
        this.manager = manager;
        this.overzichtGUI = gui;
        initComponents();
        vulComboBoxen();
        
        behandeling = manager.getBehandeling(behandelingsID);
        patientBSNField.setText(String.valueOf(behandeling.getBurgerServiceNummer()));
        therapeutField.setText(String.valueOf(behandeling.getFysiotherapeutBSN()));
        datumPicker.setDate(behandeling.getBegintijd());
        beginTijdField.setText(manager.formatTijd(behandeling.getBegintijd()));
        eindTijdField.setText(manager.formatTijd(behandeling.getEindtijd()));
        
        behandelingCodeBox.setSelectedItem(behandeling.getBehandelingscode());
        therapeutField.setText(String.valueOf(behandeling.getFysiotherapeutBSN()));
        
        statusComboBox.setSelectedItem(behandeling.getStatus());
        opmerkingTextArea.setText(behandeling.getOpmerking());
    }
    
    /**
     * Vul de comboboxen met strings & voeg auto complete functionaliteiten toe
     */
    private void vulComboBoxen() {
        for (Behandeling.Status status : Behandeling.Status.values()) { //Voeg de statusen toe
            statusComboBox.addItem(status.toString());
        }
        
        for (String behandelingcode : manager.getBehandelCodes()) { //Voeg alle behandelcodes toe
            behandelingCodeBox.addItem(behandelingcode);
        }
        
        ArrayList<String> therapeutnummers = new ArrayList<>(); //Ga alle therapeutBSN's toe
        for (Therapeut therapeut : manager.getTherapeuten()) {
            therapeutnummers.add(String.valueOf(therapeut.getBsn()));
        }
        
        //Set the format
        datumPicker.setFormats(manager.getDatumFormat());
        datumPicker.setDate(new Date());
        
        //Voeg de AutoComplete functionaliteit toe
        AutoCompleteDecorator.decorate(therapeutField, therapeutnummers, true);
        AutoCompleteDecorator.decorate(patientBSNField, manager.getPatientenBSNLijst(), true);
        AutoCompleteDecorator.decorate(behandelingCodeBox);
        
        //Implement voorbeelden
        PromptSupport.setPrompt("VB: 12:45", beginTijdField);
        PromptSupport.setPrompt("VB: 14:15", eindTijdField);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        OpslaanButton = new javax.swing.JButton();
        AnnulerenButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        bsnLabel = new javax.swing.JLabel();
        patientBSNField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        beginTijdLabel = new javax.swing.JLabel();
        datumPicker = new org.jdesktop.swingx.JXDatePicker();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        bsnLabel2 = new javax.swing.JLabel();
        bsnLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        beginTijdField = new javax.swing.JFormattedTextField();
        eindTijdField = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        behandelingscodeLabel = new javax.swing.JLabel();
        behandelingCodeBox = new javax.swing.JComboBox();
        fysiotherapeutLabel = new javax.swing.JLabel();
        eindTijdLabel1 = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox();
        eindTijdLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        opmerkingTextArea = new javax.swing.JTextArea();
        therapeutField = new javax.swing.JTextField();

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField2.setText("jTextField1");

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField5.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        OpslaanButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        OpslaanButton.setText("Opslaan");
        OpslaanButton.setMaximumSize(new java.awt.Dimension(111, 31));
        OpslaanButton.setMinimumSize(new java.awt.Dimension(111, 31));
        OpslaanButton.setPreferredSize(new java.awt.Dimension(111, 31));
        OpslaanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpslaanButtonActionPerformed(evt);
            }
        });

        AnnulerenButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AnnulerenButton.setText("Annuleren");
        AnnulerenButton.setPreferredSize(new java.awt.Dimension(111, 31));
        AnnulerenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnulerenButtonActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 0)));

        bsnLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bsnLabel.setText("Patient BSN");

        patientBSNField.setEditable(false);
        patientBSNField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bsnLabel)
                .addGap(18, 18, 18)
                .addComponent(patientBSNField)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bsnLabel)
                    .addComponent(patientBSNField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 0)));

        beginTijdLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        beginTijdLabel.setText("Datum");

        bsnLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bsnLabel2.setText("Begin tijd");

        bsnLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bsnLabel3.setText("Eind tijd");

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        beginTijdField.setToolTipText("");
        beginTijdField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        beginTijdField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        eindTijdField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(bsnLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beginTijdField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bsnLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eindTijdField, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bsnLabel2)
                    .addComponent(bsnLabel3)
                    .addComponent(beginTijdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eindTijdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(jSeparator3)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(beginTijdLabel)
                        .addGap(18, 18, 18)
                        .addComponent(datumPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(beginTijdLabel)
                    .addComponent(datumPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 0)));

        behandelingscodeLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        behandelingscodeLabel.setText("Behandelingscode");

        behandelingCodeBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        fysiotherapeutLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fysiotherapeutLabel.setText("Fysiotherapeut");

        eindTijdLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        eindTijdLabel1.setText("Status");

        statusComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        eindTijdLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        eindTijdLabel2.setText("Opmerking");

        opmerkingTextArea.setColumns(20);
        opmerkingTextArea.setRows(5);
        jScrollPane1.setViewportView(opmerkingTextArea);

        therapeutField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(behandelingscodeLabel)
                    .addComponent(eindTijdLabel1)
                    .addComponent(eindTijdLabel2)
                    .addComponent(fysiotherapeutLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(behandelingCodeBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(statusComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(therapeutField))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(behandelingscodeLabel)
                    .addComponent(behandelingCodeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fysiotherapeutLabel)
                    .addComponent(therapeutField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eindTijdLabel1)
                    .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eindTijdLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(OpslaanButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AnnulerenButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AnnulerenButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OpslaanButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AnnulerenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnulerenButtonActionPerformed
        updateOverzicht(false);
    }//GEN-LAST:event_AnnulerenButtonActionPerformed

    private void OpslaanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpslaanButtonActionPerformed
        String code = String.valueOf(behandelingCodeBox.getSelectedItem());
        
        try {
            datumPicker.getDate().getTime(); //Check of er een datum is ingevuld
        } catch (NullPointerException e) {
            geefError("Er is geen datum ingevuld."); return;
        }
        if (new Date().after(datumPicker.getDate())) { //Check of de ingevulde datum niet eerder dan vandaag is
            geefError("De ingevulde datum ligt in het verleden."); return;
        }
        String datum = manager.formatDatum(datumPicker.getDate()); //Format de datum
        String tijd = checkTijdString(beginTijdField.getText());
        if (tijd == null) {
            geefError("De begin tijd is geen valide tijd."); return;
        } else beginTijdField.setText(tijd);
        Date begintijd = manager.parseDateString(datum + " " + beginTijdField.getText()); //Parse de begintijd
        if (begintijd == null) { //Begin tijd is niet valide
            geefError("De ingevulde begin tijd is niet geldig."); return;
        }
        tijd = checkTijdString(eindTijdField.getText());
        if (tijd == null) {
            geefError("De begin tijd is geen valide tijd."); return;
        } else eindTijdField.setText(tijd);
        Date eindtijd = manager.parseDateString(datum + " " + eindTijdField.getText());
        if (eindtijd == null) { //Eind tijd is niet valide
            geefError("De ingevulde eind tijd is niet geldig."); return;
        }
        if (begintijd.after(eindtijd)) { //Eind tijd is voor de eind tijd
            geefError("De eind tijd ligt voor de begin tijd."); return;
        }
        
        int fysioBSNlengte = therapeutField.getText().length();
        
        if (fysioBSNlengte != 9 && fysioBSNlengte != 8) { //Check of de BSN lengte niet 8 of 9 is
            geefError("BSN van de fysio is niet de correcte lengte."); return;
        } 
        long fysioBSN;
        try {
            fysioBSN = Long.parseLong(therapeutField.getText()); //Probeer de string om te zetten naar een long
        } catch (NumberFormatException e) { //Geen valide nummer
            geefError("BSN van de fysio is geen valide nummer."); return;
        }
        if (!manager.isBestaandeTherapeut(fysioBSN)) { //Fysio met dit BSN bestaat niet
            geefError("Er is geen therapeut bekend met dit BSN."); return;
        }
        Status status = Status.valueOf(statusComboBox.getSelectedItem().toString());
        String opmerking = opmerkingTextArea.getText();
        
        if (behandeling == null) {
            int patientBSNlengte = patientBSNField.getText().length();
            if (patientBSNlengte != 8 && patientBSNlengte != 9) { //Check de lengte van het BSN
                geefError("BSN van de patient is niet de correcte lengte."); return;
            }
            int bsn;
            try {
                bsn = Integer.parseInt(patientBSNField.getText()); //Probeer de text te parsen naar int
            } catch (NumberFormatException e) {
                geefError("Het ingevulde BSN van de patient is niet geldig."); return;
            }
            if (manager.getPatient(bsn) == null) { //Kijk of de patient bestaat
                geefError("Er is geen patient bekend met dit BSN."); return;
            }
            manager.maakBehandeling(bsn, code, fysioBSN, begintijd, eindtijd, status, opmerking); //Maak de behandeling
            updateOverzicht(true);
        } else {
            int id = behandeling.getBehandelingsID();            
            boolean succes = manager.updateBehandeling(id, code, fysioBSN, begintijd, eindtijd, status, opmerking); //Update behandeling
            if (!succes) {
                geefError("Het aanpassen van de behandeling is mislukt! Probeer opnieuw?");
            } else {
                updateOverzicht(true);
            }
        }
    }//GEN-LAST:event_OpslaanButtonActionPerformed

    /**
     * Update het overzicht GUI en dispose deze
     */
    private void updateOverzicht(boolean aangepast) {
        if (aangepast) overzichtGUI.updateTabel();
        overzichtGUI.setVisible(true);
        dispose();
    }
    
    private String checkTijdString(String tijd) {
        if (tijd.length() == 4) {
            tijd = tijd.substring(0, 2) + ":" + tijd.substring(2);
        }
        try {
            int uren = Integer.parseInt(tijd.substring(0,2));
            if (uren < 0 || uren > 23) throw new NumberFormatException();
            int minuten = Integer.parseInt(tijd.substring(3));
            if (minuten < 0 || minuten > 59) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            return null;
        }
        return tijd;
    }
    
    /**
     * Laat een JOptionPane error zien
     * @param error de error
     * @param titel de titel
     */
    private void geefError(String error) {
        JOptionPane.showMessageDialog(this, error, "Fout", JOptionPane.ERROR_MESSAGE, null);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BehandelingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BehandelingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BehandelingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BehandelingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new BehandelingGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AnnulerenButton;
    private javax.swing.JButton OpslaanButton;
    private javax.swing.JFormattedTextField beginTijdField;
    private javax.swing.JLabel beginTijdLabel;
    private javax.swing.JComboBox behandelingCodeBox;
    private javax.swing.JLabel behandelingscodeLabel;
    private javax.swing.JLabel bsnLabel;
    private javax.swing.JLabel bsnLabel2;
    private javax.swing.JLabel bsnLabel3;
    private org.jdesktop.swingx.JXDatePicker datumPicker;
    private javax.swing.JFormattedTextField eindTijdField;
    private javax.swing.JLabel eindTijdLabel1;
    private javax.swing.JLabel eindTijdLabel2;
    private javax.swing.JLabel fysiotherapeutLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextArea opmerkingTextArea;
    private javax.swing.JTextField patientBSNField;
    private javax.swing.JComboBox statusComboBox;
    private javax.swing.JTextField therapeutField;
    // End of variables declaration//GEN-END:variables
}
