package praktijk.boundary;

import home.control.IconManager;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import praktijk.control.TherapeutManager;
import praktijk.entity.Therapeut;
import praktijk.entity.Therapeut.Geslacht;

/**
 *
 * @author Leon
 */
public class TherapeutWijzigGUI extends javax.swing.JFrame {
    private TherapeutManager manager;
    private TherapeutOverzichtGUI overzichtGUI;
    private int index;
    private Therapeut wijzigTherapeut;
    private boolean isNieuw;
    private HashMap<Long, String> praktijken;
    private boolean allesGeldig;

    /**
     * Creates new form TherapeutWijzigGUI
     * @param overzicht het therapeutoverzicht dat vernieuwd dient te worden
     * @param manager de manager voor het lezen en schrijven van objecten
     */
    public TherapeutWijzigGUI(TherapeutOverzichtGUI overzicht, TherapeutManager manager) {
        initComponents();
        this.manager = manager;
        this.overzichtGUI = overzicht;
        this.isNieuw = true;
        IconManager.setIcon(this);
        
        this.setTitle("Therapeut toevoegen");
        geboortedatumDatePicker.setFormats(new SimpleDateFormat("dd-MM-yyyy"));
        geboortedatumDatePicker.setDate(new Date());
        vulComboBox();
    }
    
    /**
     *
     * @param overzicht het therapeutoverzicht dat vernieuwd dient te worden
     * @param manager de manager voor het lezen en schrijven van objecten
     * @param index index van de te wijzigen therapeut
     */
    public TherapeutWijzigGUI(TherapeutOverzichtGUI overzicht, TherapeutManager manager, int index) {
        initComponents();
        this.manager = manager;
        this.overzichtGUI = overzicht;
        this.index = index;
        this.isNieuw = false;
        this.wijzigTherapeut = manager.getTherapeut(index);
        IconManager.setIcon(this);
        
        bsnTextField.setEnabled(false);
        this.setTitle("Therapeut wijzigen");
        geboortedatumDatePicker.setFormats(new SimpleDateFormat("dd-MM-yyyy"));
        vulComboBox();
        vulVelden();
        
    }
    
    /**
     * Vult de combobox met de namen van alle praktijken
     */
    private void vulComboBox() {
        praktijken = manager.getPraktijkNamen();
        
        for(Entry<Long, String> entry : praktijken.entrySet()) {
            praktijkComboBox.addItem(entry.getValue());
        }
        
        AutoCompleteDecorator.decorate(praktijkComboBox);
    }
    
    /**
     * Vult alle velden in met geselecteerde therapeut data
     */
    private void vulVelden() {
        voornaamTextField.setText(wijzigTherapeut.getVoornaam());
        tussenvoegselTextField.setText(wijzigTherapeut.getTussenvoegsel());
        achternaamTextField.setText(wijzigTherapeut.getAchternaam());
        geboortedatumDatePicker.setDate(wijzigTherapeut.getGeboortedatum());
        geslachtComboBox.setSelectedItem(wijzigTherapeut.getGeslacht());
        bsnTextField.setText(String.valueOf(wijzigTherapeut.getBsn()));
        postcodeTextField.setText(wijzigTherapeut.getPostcode());
        huisnrTextField.setText(wijzigTherapeut.getHuisnummer());
        telefoonnummerTextField.setText(String.valueOf(wijzigTherapeut.getTelnr()));
        
        if (praktijken.containsKey(wijzigTherapeut.getPraktijkKvk()))
            praktijkComboBox.setSelectedItem(praktijken.get(wijzigTherapeut.getPraktijkKvk()));
        else
            praktijkComboBox.setSelectedIndex(-1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        voornaamTextField = new javax.swing.JTextField();
        voornaamLabel = new javax.swing.JLabel();
        bsnLabel = new javax.swing.JLabel();
        postcodeLabel = new javax.swing.JLabel();
        huisnrLabel = new javax.swing.JLabel();
        telefoonnrLabel = new javax.swing.JLabel();
        praktijkKvkLabel = new javax.swing.JLabel();
        bsnTextField = new javax.swing.JTextField();
        postcodeTextField = new javax.swing.JTextField();
        huisnrTextField = new javax.swing.JTextField();
        annulerenButton = new javax.swing.JButton();
        opslaanButton = new javax.swing.JButton();
        achternaamTextField = new javax.swing.JTextField();
        achternaamLabel = new javax.swing.JLabel();
        tussenvoegselTextField = new javax.swing.JTextField();
        tussenvoegselLabel = new javax.swing.JLabel();
        geboortedatumLabel = new javax.swing.JLabel();
        geslachtComboBox = new javax.swing.JComboBox();
        geslachtLabel = new javax.swing.JLabel();
        geboortedatumDatePicker = new org.jdesktop.swingx.JXDatePicker();
        telefoonnummerTextField = new javax.swing.JTextField();
        praktijkComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        voornaamLabel.setText("Voornaam");

        bsnLabel.setText("BSN");

        postcodeLabel.setText("Postcode");

        huisnrLabel.setText("Huisnummer");

        telefoonnrLabel.setText("Telefoonnummer");

        praktijkKvkLabel.setText("Praktijk");

        annulerenButton.setText("Annuleren");
        annulerenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerenButtonActionPerformed(evt);
            }
        });

        opslaanButton.setText("Opslaan");
        opslaanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opslaanButtonActionPerformed(evt);
            }
        });

        achternaamLabel.setText("Achternaam");

        tussenvoegselLabel.setText("Tussenvoegsel");

        geboortedatumLabel.setText("Geboortedatum");

        geslachtComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mannelijk", "Vrouwelijk" }));

        geslachtLabel.setText("Geslacht");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(voornaamLabel)
                    .addComponent(bsnLabel)
                    .addComponent(huisnrLabel)
                    .addComponent(telefoonnrLabel)
                    .addComponent(postcodeLabel)
                    .addComponent(achternaamLabel)
                    .addComponent(tussenvoegselLabel)
                    .addComponent(geboortedatumLabel)
                    .addComponent(geslachtLabel)
                    .addComponent(praktijkKvkLabel))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(huisnrTextField)
                    .addComponent(voornaamTextField)
                    .addComponent(bsnTextField)
                    .addComponent(postcodeTextField)
                    .addComponent(achternaamTextField)
                    .addComponent(tussenvoegselTextField)
                    .addComponent(geslachtComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(geboortedatumDatePicker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(opslaanButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(annulerenButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(telefoonnummerTextField)
                    .addComponent(praktijkComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(voornaamTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(voornaamLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tussenvoegselTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tussenvoegselLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(achternaamTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(achternaamLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(geboortedatumDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(geboortedatumLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(geslachtComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(geslachtLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bsnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bsnLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(postcodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(postcodeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(huisnrTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(huisnrLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telefoonnummerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefoonnrLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(praktijkComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(praktijkKvkLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opslaanButton)
                    .addComponent(annulerenButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Sluit het venster
     * @param evt 
     */
    private void annulerenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerenButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_annulerenButtonActionPerformed

    /**
     * Maakt een nieuwe therapeut aan of wijzigt de gegevens van een bestaande therapeut
     * @param evt 
     */
    private void opslaanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opslaanButtonActionPerformed
        String voornaam = voornaamTextField.getText();
        String achternaam = achternaamTextField.getText();
        String bsnStr = bsnTextField.getText();
        String postcode = postcodeTextField.getText();
        String huisnr = huisnrTextField.getText();
        String telnr = telefoonnummerTextField.getText();
        int praktijkIndex = praktijkComboBox.getSelectedIndex();
        
        allesGeldig = true;
        
        checkGeldig(!voornaam.isEmpty(), voornaamLabel);
        checkGeldig(!achternaam.isEmpty(), achternaamLabel);
        checkGeldig(bsnStr.matches("\\d{8,9}"), bsnLabel);
        checkGeldig(postcode.matches("\\d{4}[a-zA-Z]{2}"), postcodeLabel);
        checkGeldig(huisnr.matches("\\d{1,}[a-zA-Z]?"), huisnrLabel);
        checkGeldig(telnr.matches("\\d{10}"), telefoonnrLabel);
        checkGeldig(praktijkIndex != -1, praktijkKvkLabel);
        
        if (allesGeldig) {
            String tussenvoegsel = tussenvoegselTextField.getText();
            Date geboortedatum = geboortedatumDatePicker.getDate();
            int bsn = Integer.parseInt(bsnStr);
            long praktijkKvk = (long) praktijken.keySet().toArray()[praktijkIndex];
            Geslacht geslacht = Geslacht.Mannelijk;
            if (geslachtComboBox.getSelectedIndex() == 1) {
                geslacht = Geslacht.Vrouwelijk;
            }
            
            if (isNieuw && manager.bsnBestaat(bsn)) {
                JOptionPane.showMessageDialog(this, "Er bestaat reeds een therapeut met ditzelfde BSN-nummer.\nHet BSN-nummer dient uniek te zijn, pas eventueel de andere praktijk aan.", "Fout", JOptionPane.ERROR_MESSAGE);
                checkGeldig(false, bsnLabel);
                return;
            }

            Therapeut therapeut = new Therapeut(voornaam, tussenvoegsel, achternaam, geboortedatum, geslacht, bsn, postcode, huisnr, telnr, praktijkKvk);
            boolean succes;
            
            if(isNieuw) {
                succes = manager.voegToe(therapeut);
            }
            else {
                succes = manager.wijzig(index, therapeut);
            }

            if (succes) {
                overzichtGUI.vernieuwOverzicht();
                this.dispose();
            }
            else {
                JOptionPane.showMessageDialog(this, "Er is een fout opgetreden bij het opslaan van de therapeut.", "Fout", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Controleer of alle velden (correct) ingevuld zijn.\nFoutief ingevulde velden zijn rood weergegeven.", "Fout", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_opslaanButtonActionPerformed

    private void checkGeldig(boolean geldig, JLabel label) {
        if (geldig) {
            label.setForeground(Color.BLACK);
        }
        else {
            label.setForeground(Color.RED);
            allesGeldig = false;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel achternaamLabel;
    private javax.swing.JTextField achternaamTextField;
    private javax.swing.JButton annulerenButton;
    private javax.swing.JLabel bsnLabel;
    private javax.swing.JTextField bsnTextField;
    private org.jdesktop.swingx.JXDatePicker geboortedatumDatePicker;
    private javax.swing.JLabel geboortedatumLabel;
    private javax.swing.JComboBox geslachtComboBox;
    private javax.swing.JLabel geslachtLabel;
    private javax.swing.JLabel huisnrLabel;
    private javax.swing.JTextField huisnrTextField;
    private javax.swing.JButton opslaanButton;
    private javax.swing.JLabel postcodeLabel;
    private javax.swing.JTextField postcodeTextField;
    private javax.swing.JComboBox praktijkComboBox;
    private javax.swing.JLabel praktijkKvkLabel;
    private javax.swing.JLabel telefoonnrLabel;
    private javax.swing.JTextField telefoonnummerTextField;
    private javax.swing.JLabel tussenvoegselLabel;
    private javax.swing.JTextField tussenvoegselTextField;
    private javax.swing.JLabel voornaamLabel;
    private javax.swing.JTextField voornaamTextField;
    // End of variables declaration//GEN-END:variables
}
