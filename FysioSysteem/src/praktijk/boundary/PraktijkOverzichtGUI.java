/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package praktijk.boundary;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import praktijk.control.PraktijkManager;
import praktijk.entity.Praktijk;

/**
 *
 * @author Leon
 */
public class PraktijkOverzichtGUI extends javax.swing.JFrame {
    PraktijkManager manager;

    /**
     * Creates new form PraktijkOverzichtGUI
     */
    public PraktijkOverzichtGUI() {
        initComponents();
        manager = new PraktijkManager();
        
//        ArrayList<Praktijk> lijst = new ArrayList<>();
//        lijst.add(new Praktijk("Test1", "Test", "Test", "Test", 123456789, "Test", 123456789, 123456789));
//        lijst.add(new Praktijk("Test2", "Test", "Test", "Test", 123456789, "Test", 123456789, 123456789));
//        lijst.add(new Praktijk("Test3", "Test", "Test", "Test", 123456789, "Test", 123456789, 123456789));
//        lijst.add(new Praktijk("Test4", "Test", "Test", "Test", 123456789, "Test", 123456789, 123456789));
        
//        for(Praktijk p : lijst) {
        for(Praktijk p : manager.getPraktijken()) {
            DefaultTableModel model = new DefaultTableModel();
            model.addRow(new Object[] { p.getNaam(), p.getPlaats(), p.getPostcode(), p.getHuisnummer(), p.getIban() });
            praktijkenTable.setModel(model);
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        zoekTextField = new javax.swing.JTextField();
        zoekComboBox = new javax.swing.JComboBox();
        zoekButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        praktijkenTable = new javax.swing.JTable();
        terugButton = new javax.swing.JButton();
        verwijderButton = new javax.swing.JButton();
        bewerkButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        zoekTextField.setText("zoekwoord");
        zoekTextField.setName(""); // NOI18N

        zoekComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Naam", "Plaatsnaam", "Sofinummer" }));

        zoekButton.setText("Zoeken");

        praktijkenTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Naam", "Plaatsnaam", "Straatnaam", "Huisnummer", "Rekeningnummer"
            }
        ));
        jScrollPane1.setViewportView(praktijkenTable);

        terugButton.setText("Terug");

        verwijderButton.setText("Verwijder");

        bewerkButton.setText("Bewerk");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(zoekTextField)
                    .addComponent(zoekComboBox, 0, 125, Short.MAX_VALUE)
                    .addComponent(zoekButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(terugButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(verwijderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addComponent(bewerkButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bewerkButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(verwijderButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(terugButton))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(zoekTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(zoekComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(zoekButton))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(PraktijkOverzichtGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PraktijkOverzichtGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PraktijkOverzichtGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PraktijkOverzichtGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PraktijkOverzichtGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bewerkButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable praktijkenTable;
    private javax.swing.JButton terugButton;
    private javax.swing.JButton verwijderButton;
    private javax.swing.JButton zoekButton;
    private javax.swing.JComboBox zoekComboBox;
    private javax.swing.JTextField zoekTextField;
    // End of variables declaration//GEN-END:variables
}
