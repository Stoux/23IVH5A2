/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rapportage.boundary;

import behandel.control.BehandelingManager;
import home.boundary.HomeGUI;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import praktijk.entity.Therapeut;
import rapportage.control.RoosterControl;

/**
 *
 * @author Jan
 */
public class RoosterGUI extends javax.swing.JFrame {

    private HomeGUI homeGUI;
    
    private RoosterControl control;
    private BehandelingManager bManager;
    private ArrayList<Therapeut> therapeuten;
    private Date beginDatum;
    private Date eindDatum;
    private Calendar cal;

    /**
     * Creates new form RoosterGUI
     */
    // TODO new RoosterControl(defaulttablemodel van de jtable)
    public RoosterGUI(HomeGUI homeGUI, BehandelingManager bManager) {
        this.homeGUI = homeGUI;
        initComponents();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sluitGUI();
            }
        });
        this.bManager = bManager;
        control = new RoosterControl((DefaultTableModel) roosterTabel.getModel(), bManager);
        roosterTabel.setShowGrid(true);
        this.setExtendedState(this.MAXIMIZED_BOTH);

        therapeuten = bManager.getTherapeuten();

        for (int i = 0; i < therapeuten.size(); i++) {
            fysiotherapeutComboBox.addItem(therapeuten.get(i).getVolledigeNaam());
        }
        setDatumWeek();
        control.maakRooster(beginDatum, eindDatum, fysiotherapeutComboBox.getSelectedItem().toString());
        roosterTabel.setModel(control.getModel());
        roosterTabel.repaint();
    }

    private boolean setDatumWeek() {
        boolean succes = false;
        DateFormat dateFormat = new SimpleDateFormat("u");
        Date huidigeDatum = new Date(System.currentTimeMillis());
        int dagNummer = Integer.parseInt(dateFormat.format(huidigeDatum));
      
        switch (dagNummer) {
            case 1:
                beginDatum = huidigeDatum;
                cal = Calendar.getInstance();
                cal.setTime(huidigeDatum);
                cal.add(Calendar.DATE, +4);
                eindDatum = new Date(cal.getTimeInMillis());
                succes = true;
                break;
            case 2:
                cal = Calendar.getInstance();
                cal.setTime(huidigeDatum);
                cal.add(Calendar.DATE, -1);
                beginDatum = new Date(cal.getTimeInMillis());
                cal = Calendar.getInstance();
                cal.setTime(huidigeDatum);
                cal.add(Calendar.DATE, +3);
                eindDatum = new Date(cal.getTimeInMillis());
                succes = true;
                break;
            case 3:
                cal = Calendar.getInstance();
                cal.setTime(huidigeDatum);
                cal.add(Calendar.DATE, -2);
                beginDatum = new Date(cal.getTimeInMillis());
                cal = Calendar.getInstance();
                cal.setTime(huidigeDatum);
                cal.add(Calendar.DATE, +2);
                eindDatum = new Date(cal.getTimeInMillis());
                succes = true;
                break;
            case 4:
                cal = Calendar.getInstance();
                cal.setTime(huidigeDatum);
                cal.add(Calendar.DATE, -3);
                beginDatum = new Date(cal.getTimeInMillis());
                cal = Calendar.getInstance();
                cal.setTime(huidigeDatum);
                cal.add(Calendar.DATE, +1);
                eindDatum = new Date(cal.getTimeInMillis());
                succes = true;
                break;
            case 5:
                cal = Calendar.getInstance();
                cal.setTime(huidigeDatum);
                cal.add(Calendar.DATE, -4);
                beginDatum = new Date(cal.getTimeInMillis());
                eindDatum = huidigeDatum;
                succes = true;
                break;

        }
        return succes;
    }
    
    private void sluitGUI() {
        homeGUI.maakZichtbaar();
        dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roosterPanel = new javax.swing.JPanel();
        beschrijvingLabel1 = new javax.swing.JLabel();
        beschrijvingLabel2 = new javax.swing.JLabel();
        beschrijvingLabel3 = new javax.swing.JLabel();
        fysiotherapeutComboBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        roosterTabel = new javax.swing.JTable();
        vorigeWeekKnop = new javax.swing.JButton();
        volgendeWeekKnop = new javax.swing.JButton();
        menuBalk = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        beschrijvingLabel1.setText("Selecteer de fysiotherapeut");

        beschrijvingLabel2.setText("waarvan je het rooster wilt");

        beschrijvingLabel3.setText("bekijken:");

        fysiotherapeutComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fysiotherapeutComboBoxActionPerformed(evt);
            }
        });

        roosterTabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        roosterTabel.setToolTipText("");
        roosterTabel.setRowHeight(61);
        roosterTabel.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(roosterTabel);
        roosterTabel.getColumnModel().getColumn(0).setResizable(false);
        roosterTabel.getColumnModel().getColumn(1).setResizable(false);
        roosterTabel.getColumnModel().getColumn(2).setResizable(false);
        roosterTabel.getColumnModel().getColumn(3).setResizable(false);
        roosterTabel.getColumnModel().getColumn(4).setResizable(false);

        vorigeWeekKnop.setText("<- Vorige Week");
        vorigeWeekKnop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vorigeWeekKnopActionPerformed(evt);
            }
        });

        volgendeWeekKnop.setText("Volgende Week ->");
        volgendeWeekKnop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volgendeWeekKnopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roosterPanelLayout = new javax.swing.GroupLayout(roosterPanel);
        roosterPanel.setLayout(roosterPanelLayout);
        roosterPanelLayout.setHorizontalGroup(
            roosterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roosterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roosterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roosterPanelLayout.createSequentialGroup()
                        .addGroup(roosterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(fysiotherapeutComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(beschrijvingLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(roosterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roosterPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roosterPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(vorigeWeekKnop, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(volgendeWeekKnop)
                                .addContainerGap())))
                    .addGroup(roosterPanelLayout.createSequentialGroup()
                        .addGroup(roosterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(beschrijvingLabel3)
                            .addComponent(beschrijvingLabel1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        roosterPanelLayout.setVerticalGroup(
            roosterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roosterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(beschrijvingLabel1)
                .addGap(1, 1, 1)
                .addGroup(roosterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(beschrijvingLabel2)
                    .addComponent(vorigeWeekKnop)
                    .addComponent(volgendeWeekKnop))
                .addGap(0, 0, 0)
                .addComponent(beschrijvingLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roosterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roosterPanelLayout.createSequentialGroup()
                        .addComponent(fysiotherapeutComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE))
                .addContainerGap())
        );

        jMenu1.setText("File");
        menuBalk.add(jMenu1);

        jMenu2.setText("Help");
        menuBalk.add(jMenu2);

        setJMenuBar(menuBalk);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roosterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roosterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void vorigeWeekKnopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vorigeWeekKnopActionPerformed
//        Calendar currentDate = Calendar.getInstance();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM");
//        String huidigeDatum = formatter.format(currentDate.getTime());
//        System.out.println("De huidige datum is: " + huidigeDatum);
//
//
//        String therapeutNaam = fysiotherapeutComboBox.getSelectedItem().toString();
//        boolean controle = control.zoekTherapeut(therapeutNaam);
//
//        if (controle = true) {
//            DefaultTableModel model = control.getModel();
//            roosterTabel.setModel(model);
//        } else {
//            //popup alert
//            JOptionPane.showInternalMessageDialog(
//                    this.getContentPane(),
//                    "Er zijn geen behandelingen voor " + therapeutNaam + "gevonden.",
//                    "Foutmelding",
//                    JOptionPane.ERROR_MESSAGE);
//        }
    }//GEN-LAST:event_vorigeWeekKnopActionPerformed

    private void volgendeWeekKnopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volgendeWeekKnopActionPerformed
//        String therapeutNaam = fysiotherapeutComboBox.getSelectedItem().toString();
//        boolean controle = control.zoekTherapeut(therapeutNaam);
//
//        if (controle = true) {
//            DefaultTableModel model = control.getModel();
//            roosterTabel.setModel(model);
//        } else {
//            //popup alert
//            JOptionPane.showInternalMessageDialog(
//                    this.getContentPane(),
//                    "Er zijn geen behandelingen voor " + therapeutNaam + "gevonden.",
//                    "Foutmelding",
//                    JOptionPane.ERROR_MESSAGE);
//        }
    }//GEN-LAST:event_volgendeWeekKnopActionPerformed

    private void fysiotherapeutComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fysiotherapeutComboBoxActionPerformed
        String therapeutNaam = fysiotherapeutComboBox.getSelectedItem().toString();
        setDatumWeek();
        control.maakRooster(beginDatum, eindDatum, therapeutNaam);
        roosterTabel.setModel(control.getModel());
        roosterTabel.repaint();
    }//GEN-LAST:event_fysiotherapeutComboBoxActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel beschrijvingLabel1;
    private javax.swing.JLabel beschrijvingLabel2;
    private javax.swing.JLabel beschrijvingLabel3;
    private javax.swing.JComboBox fysiotherapeutComboBox;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menuBalk;
    private javax.swing.JPanel roosterPanel;
    private javax.swing.JTable roosterTabel;
    private javax.swing.JButton volgendeWeekKnop;
    private javax.swing.JButton vorigeWeekKnop;
    // End of variables declaration//GEN-END:variables
}
