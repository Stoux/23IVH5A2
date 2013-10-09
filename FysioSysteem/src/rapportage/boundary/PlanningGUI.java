/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rapportage.boundary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import rapportage.control.PlanningControl;

/**
 *
 * @author Jan
 */
public class PlanningGUI extends javax.swing.JFrame {
    private PlanningControl control;
    /**
     * Creates new form PlanningGUI
     */
    
    // TODO new PlanningControl(defaulttablemodel van de jtable)
    public PlanningGUI() {
        initComponents();
        control = new PlanningControl((DefaultTableModel) overzichtTabel.getModel());
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
        startdatumTextField = new javax.swing.JTextField();
        einddatumTextField = new javax.swing.JTextField();
        datumButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        overzichtTabel = new javax.swing.JTable();
        sofinummerTextField = new javax.swing.JTextField();
        sofinummerButton = new javax.swing.JButton();
        terugButton = new javax.swing.JButton();
        printButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        terugMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        startdatumTextField.setText("Startdatum");

        einddatumTextField.setText("Einddatum");

        datumButton.setText("Zoek op datum");
        datumButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datumButtonActionPerformed(evt);
            }
        });

        overzichtTabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Bedrijf", "TherapeutCode", "Sofinummer", "BehandelCode", "Aantal Behandelingen", "Datum", "Opmerkingen"
            }
        ));
        overzichtTabel.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(overzichtTabel);

        sofinummerTextField.setText("Sofinummer");

        sofinummerButton.setText("Zoek op Sofinummer");
        sofinummerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sofinummerButtonActionPerformed(evt);
            }
        });

        terugButton.setText("Terug");

        printButton.setText("Printen");

        javax.swing.GroupLayout inhoudPanelLayout = new javax.swing.GroupLayout(inhoudPanel);
        inhoudPanel.setLayout(inhoudPanelLayout);
        inhoudPanelLayout.setHorizontalGroup(
            inhoudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inhoudPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inhoudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(startdatumTextField)
                    .addComponent(einddatumTextField)
                    .addComponent(datumButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sofinummerTextField)
                    .addComponent(sofinummerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inhoudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(printButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(terugButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        inhoudPanelLayout.setVerticalGroup(
            inhoudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inhoudPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inhoudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(inhoudPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(printButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(terugButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, inhoudPanelLayout.createSequentialGroup()
                        .addComponent(startdatumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(einddatumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datumButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sofinummerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sofinummerButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        fileMenu.setText("File");

        terugMenuItem.setLabel("Terug");
        fileMenu.add(terugMenuItem);

        jMenuBar1.add(fileMenu);

        helpMenu.setText("Help");
        jMenuBar1.add(helpMenu);

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

    private void datumButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datumButtonActionPerformed
        boolean b = control.zoekDatum(parseDate(startdatumTextField.getText()), parseDate(einddatumTextField.getText()), (DefaultTableModel) overzichtTabel.getModel());
        if(b == true){
            overzichtTabel.setModel(control.getModel());
        } else {
            JOptionPane.showMessageDialog(this, "Zoeken op datum is niet geslaagd.");
        }
    }//GEN-LAST:event_datumButtonActionPerformed

    private void sofinummerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sofinummerButtonActionPerformed
        boolean b = control.zoekSofinummer(Integer.parseInt(sofinummerTextField.getText()), (DefaultTableModel) overzichtTabel.getModel());
        if(b == true){
            overzichtTabel.setModel(control.getModel());
        } else {
            JOptionPane.showMessageDialog(this, "Zoeken op datum is niet geslaagd.");
        }
    }//GEN-LAST:event_sofinummerButtonActionPerformed

    private Date parseDate(String s){
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return df.parse(s);
        } catch (ParseException ex) {
            System.out.println("Failed parsing date: " + ex.toString());
            return null;
        }
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlanningGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PlanningGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton datumButton;
    private javax.swing.JTextField einddatumTextField;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JPanel inhoudPanel;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable overzichtTabel;
    private javax.swing.JButton printButton;
    private javax.swing.JButton sofinummerButton;
    private javax.swing.JTextField sofinummerTextField;
    private javax.swing.JTextField startdatumTextField;
    private javax.swing.JButton terugButton;
    private javax.swing.JMenuItem terugMenuItem;
    // End of variables declaration//GEN-END:variables
}