/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rapportage.boundary;

import behandel.control.BehandelingManager;
import home.boundary.HomeGUI;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import rapportage.control.CumulatiefControl;

/**
 *
 * @author Jan
 */
public class CumulatiefGUI extends javax.swing.JFrame {

    private HomeGUI homeGUI;
    private CumulatiefControl control;

    /**
     * Creates new form CumulatiefGUI
     */
    public CumulatiefGUI(HomeGUI homeGUI, BehandelingManager manager) {
        this.homeGUI = homeGUI;
        initComponents();
        control = new CumulatiefControl((DefaultTableModel) overzichtTabel.getModel(), manager);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        
        startdatumTextField.setFormats(manager.getDatumFormat());
        einddatumTextField.setFormats(manager.getDatumFormat());
        startdatumTextField.setDate(new Date());
        einddatumTextField.setDate(new Date());
        
        Date startdatum = new Date(startdatumTextField.getDate().getTime() - 86399999);
        Date einddatum = new Date(einddatumTextField.getDate().getTime() + 86399999);
        control.getGegevens(startdatum, einddatum, (DefaultTableModel) overzichtTabel.getModel());
        
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

        inhoudPanel = new javax.swing.JPanel();
        terugButton = new javax.swing.JButton();
        zoekButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        overzichtTabel = new org.jdesktop.swingx.JXTable();
        startdatumTextField = new org.jdesktop.swingx.JXDatePicker();
        einddatumTextField = new org.jdesktop.swingx.JXDatePicker();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        terugMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        terugButton.setText("Terug");
        terugButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terugButtonActionPerformed(evt);
            }
        });

        zoekButton.setText("Zoeken");
        zoekButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoekButtonActionPerformed(evt);
            }
        });

        overzichtTabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Bedrijfscode", "Therapeutcode", "Behandelingscode", "Aantal Behandelingen"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(overzichtTabel);
        overzichtTabel.getColumnModel().getColumn(0).setResizable(false);
        overzichtTabel.getColumnModel().getColumn(1).setResizable(false);
        overzichtTabel.getColumnModel().getColumn(2).setResizable(false);
        overzichtTabel.getColumnModel().getColumn(3).setResizable(false);

        javax.swing.GroupLayout inhoudPanelLayout = new javax.swing.GroupLayout(inhoudPanel);
        inhoudPanel.setLayout(inhoudPanelLayout);
        inhoudPanelLayout.setHorizontalGroup(
            inhoudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inhoudPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inhoudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(startdatumTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(einddatumTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(zoekButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(terugButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        inhoudPanelLayout.setVerticalGroup(
            inhoudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inhoudPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inhoudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                    .addGroup(inhoudPanelLayout.createSequentialGroup()
                        .addComponent(startdatumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(einddatumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(inhoudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(inhoudPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(terugButton))
                            .addGroup(inhoudPanelLayout.createSequentialGroup()
                                .addComponent(zoekButton)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        fileMenu.setText("File");

        terugMenuItem.setLabel("Terug");
        terugMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terugMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(terugMenuItem);

        jMenuBar1.add(fileMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inhoudPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inhoudPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void zoekButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoekButtonActionPerformed
        Date startdatum = new Date(startdatumTextField.getDate().getTime() - 86399999);
        Date einddatum = new Date(einddatumTextField.getDate().getTime() + 86399999);
        boolean b = control.zoek(startdatum, einddatum, (DefaultTableModel) overzichtTabel.getModel());
        if (b == true) {
            overzichtTabel.setModel(control.getModel());
        } else {
            JOptionPane.showMessageDialog(this, "Zoeken op datum is niet geslaagd.");
        }
    }//GEN-LAST:event_zoekButtonActionPerformed

    private void terugButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terugButtonActionPerformed
        sluitGUI();
    }//GEN-LAST:event_terugButtonActionPerformed

    private void terugMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terugMenuItemActionPerformed
        sluitGUI();
    }//GEN-LAST:event_terugMenuItemActionPerformed

    private void sluitGUI() {
        homeGUI.maakZichtbaar();
        dispose();
    }
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CumulatiefGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new CumulatiefGUI().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker einddatumTextField;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPanel inhoudPanel;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXTable overzichtTabel;
    private org.jdesktop.swingx.JXDatePicker startdatumTextField;
    private javax.swing.JButton terugButton;
    private javax.swing.JMenuItem terugMenuItem;
    private javax.swing.JButton zoekButton;
    // End of variables declaration//GEN-END:variables
}
