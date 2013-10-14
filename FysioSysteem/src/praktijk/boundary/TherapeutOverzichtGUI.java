package praktijk.boundary;

import home.boundary.HomeGUI;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.prompt.PromptSupport;
import praktijk.control.TherapeutManager;
import praktijk.entity.Therapeut;

/**
 *
 * @author Leon
 */
public class TherapeutOverzichtGUI extends javax.swing.JFrame {
    private HomeGUI homeGUI;    
    private TherapeutManager manager;
    private DefaultTableModel tableModel;
    private HashMap<Long, String> praktijken;

    /**
     * Creates new form TherapeutOverzichtGUI
     */
    public TherapeutOverzichtGUI(HomeGUI homeGUI, TherapeutManager therapeutManager) {       
        this.homeGUI = homeGUI;
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        
        manager = therapeutManager;
        praktijken = manager.getPraktijkNamen();
        tableModel = (DefaultTableModel) therapeutenTable.getModel();
        
        PromptSupport.setPrompt("Zoekterm...", zoekTextField);
        
        vernieuwOverzicht();     
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sluitGUI();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        therapeutenTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        zoekTextField = new javax.swing.JTextField();
        zoekComboBox = new javax.swing.JComboBox();
        zoekButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        toevoegenMenuItem = new javax.swing.JMenuItem();
        bewerkenMenuItem = new javax.swing.JMenuItem();
        verwijderMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        terugMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Therapeuten overzicht");

        therapeutenTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Naam", "Geboortedatum", "Postcode", "Huisnummer", "Praktijk"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(therapeutenTable);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Zoeken"));

        zoekTextField.setName(""); // NOI18N

        zoekComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Naam" }));

        zoekButton.setText("Zoeken");
        zoekButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoekButtonActionPerformed(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(zoekTextField)
                    .addComponent(zoekComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 156, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(zoekButton, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(zoekTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(zoekComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zoekButton)
                    .addComponent(resetButton))
                .addContainerGap())
        );

        jMenu1.setText("Bestand");

        toevoegenMenuItem.setText("Toevoegen");
        toevoegenMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toevoegenMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(toevoegenMenuItem);

        bewerkenMenuItem.setText("Wijzigen");
        bewerkenMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bewerkenMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(bewerkenMenuItem);

        verwijderMenuItem.setText("Verwijderen");
        verwijderMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verwijderMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(verwijderMenuItem);
        jMenu1.add(jSeparator1);

        terugMenuItem.setText("Terug");
        terugMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terugMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(terugMenuItem);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Tabel met therapeuten wordt leeggemaakt en gevuld met gevonden therapeuten
     * @param evt 
     */
    private void zoekButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoekButtonActionPerformed
        String query = zoekTextField.getText();
        vulTabel(manager.zoekTherapeut(query));
    }//GEN-LAST:event_zoekButtonActionPerformed

    /**
     * Er wordt een nieuw venster geopend waar therapeuten aangemaakt kunnen worden
     * @param evt 
     */
    private void toevoegenMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toevoegenMenuItemActionPerformed
        TherapeutWijzigGUI therapeutWijzigGUI = new TherapeutWijzigGUI(this, manager);
        therapeutWijzigGUI.setLocationRelativeTo(this);
        therapeutWijzigGUI.setVisible(true);
    }//GEN-LAST:event_toevoegenMenuItemActionPerformed

    /**
     * Er wordt een nieuw venster geopend waar de in de lijst geselecteerde
     * therapeut aangepast kan worden
     * @param evt 
     */
    private void bewerkenMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bewerkenMenuItemActionPerformed
        int index = therapeutenTable.getSelectedRow();
        
        if(index != -1) {
            TherapeutWijzigGUI therapeutWijzigGUI = new TherapeutWijzigGUI(this, manager, index);
            therapeutWijzigGUI.setLocationRelativeTo(this);
            therapeutWijzigGUI.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Er is geen therapeut geselecteerd.", "Error", JOptionPane.ERROR_MESSAGE);
        }        
    }//GEN-LAST:event_bewerkenMenuItemActionPerformed

    /**
     * Er wordt gevraagd of de in de lijst geselecteerde therapeut verwijderd
     * moet worden
     * @param evt 
     */
    private void verwijderMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verwijderMenuItemActionPerformed
        int index = therapeutenTable.getSelectedRow();
        
        if(index != -1) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Weet u zeker dat u deze therapeut wilt verwijderen?", "Verwijderen", JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION)
            {
                if(manager.verwijder(index))
                    vernieuwOverzicht();
                else
                    JOptionPane.showMessageDialog(this, "Er is een fout opgetreden bij het verwijderen.", "Fout", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Er is geen therapeut geselecteerd.", "Fout", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_verwijderMenuItemActionPerformed

    /**
     * Sluit het venster en ga terug naar het hoofdmenu
     * @param evt 
     */
    private void terugMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terugMenuItemActionPerformed
        sluitGUI();
    }//GEN-LAST:event_terugMenuItemActionPerformed

     /**
     * Het zoekveld wordt leeggemaakt en alle therapeuten worden getoond
     * @param evt 
     */
    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        zoekTextField.setText(null);
        vernieuwOverzicht();
    }//GEN-LAST:event_resetButtonActionPerformed
     
    /**
     * Venster wordt afgesloten en home menu wordt getoond
     */
    private void sluitGUI() {
        homeGUI.setVisible(true);
        homeGUI.setLocationRelativeTo(null);
        dispose();
    }
    
    /**
     * Leegt de tabel en vult deze vervolgens met therapeuten
     * @param therapeuten de therapeuten waarmee de tabel gevuld moet worden
     */
    private void vulTabel(ArrayList<Therapeut> therapeuten) {
        //leeg de tabel
        tableModel.setRowCount(0);
        
        for(Therapeut t : therapeuten) {
            String praktijknaam = (t.getPraktijkKvk() == 0) ? "" : praktijken.get(t.getPraktijkKvk());
            tableModel.addRow(new Object[] { t.getVolledigeNaam(), t.getGeboortedatumFormatted(), t.getPostcode(), t.getHuisnummer(), praktijknaam });
        }
    }
    
    /**
     * Vraagt de lijst met therapeuten op en zorgt dat de tabel gevuld wordt.
     */
    public void vernieuwOverzicht() {
        vulTabel(manager.getTherapeuten());
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem bewerkenMenuItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JButton resetButton;
    private javax.swing.JMenuItem terugMenuItem;
    private javax.swing.JTable therapeutenTable;
    private javax.swing.JMenuItem toevoegenMenuItem;
    private javax.swing.JMenuItem verwijderMenuItem;
    private javax.swing.JButton zoekButton;
    private javax.swing.JComboBox zoekComboBox;
    private javax.swing.JTextField zoekTextField;
    // End of variables declaration//GEN-END:variables
}
