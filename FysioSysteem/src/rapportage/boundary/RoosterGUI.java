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
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
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
    private Date beginWeek;
    private Calendar cal;
    private DateFormat dagMaand;

    /**
     * Creates new form RoosterGUI
     */
    // TODO new RoosterControl(defaulttablemodel van de jtable)
    public RoosterGUI(HomeGUI homeGUI, BehandelingManager bManager) {
        dagMaand = new SimpleDateFormat("dd/MM");
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
        setHeaders("huidige");
        roosterTabel.setModel(control.getModel());
        


    }

    private boolean setDatumWeek() {
        boolean succes = false;
        DateFormat dateFormat = new SimpleDateFormat("u");
        Date huidigeDatum = new Date(System.currentTimeMillis());
        int dagNummer = Integer.parseInt(dateFormat.format(huidigeDatum));

        switch (dagNummer) {
            case 1:
                cal = Calendar.getInstance();
                cal.setTime(huidigeDatum);
                cal.add(Calendar.DATE, -1);
                beginDatum = new Date(cal.getTimeInMillis());
                cal.add(Calendar.DATE, +1);
                beginWeek = new Date(cal.getTimeInMillis());
                cal.setTime(huidigeDatum);
                cal.add(Calendar.DATE, +5);
                eindDatum = new Date(cal.getTimeInMillis());
                succes = true;
                break;
            case 2:
                cal = Calendar.getInstance();
                cal.setTime(huidigeDatum);
                cal.add(Calendar.DATE, -2);
                beginDatum = new Date(cal.getTimeInMillis());
                cal.add(Calendar.DATE, +1);
                beginWeek = new Date(cal.getTimeInMillis());
                cal = Calendar.getInstance();
                cal.setTime(huidigeDatum);
                cal.add(Calendar.DATE, +4);
                eindDatum = new Date(cal.getTimeInMillis());               
                succes = true;
                break;
            case 3:
                cal = Calendar.getInstance();
                cal.setTime(huidigeDatum);
                cal.add(Calendar.DATE, -3);
                beginDatum = new Date(cal.getTimeInMillis());
                cal.add(Calendar.DATE, +1);
                beginWeek = new Date(cal.getTimeInMillis());
                cal = Calendar.getInstance();
                cal.setTime(huidigeDatum);
                cal.add(Calendar.DATE, +3);
                eindDatum = new Date(cal.getTimeInMillis());
                succes = true;
                break;
            case 4:
                cal = Calendar.getInstance();
                cal.setTime(huidigeDatum);
                cal.add(Calendar.DATE, -4);
                beginDatum = new Date(cal.getTimeInMillis());
                cal.add(Calendar.DATE, +1);
                beginWeek = new Date(cal.getTimeInMillis());
                cal = Calendar.getInstance();
                cal.setTime(huidigeDatum);
                cal.add(Calendar.DATE, +2);
                eindDatum = new Date(cal.getTimeInMillis());
                succes = true;
                break;
            case 5:
                cal = Calendar.getInstance();
                cal.setTime(huidigeDatum);
                cal.add(Calendar.DATE, -5);
                beginDatum = new Date(cal.getTimeInMillis());
                cal.add(Calendar.DATE, +1);
                beginWeek = new Date(cal.getTimeInMillis());
                cal.setTime(huidigeDatum);
                cal.add(Calendar.DATE, +1);
                eindDatum = new Date(cal.getTimeInMillis());
                succes = true;
                break;

        }
        return succes;
    }

    private boolean setData(String kant) {
        boolean succes = false;
        cal = Calendar.getInstance();
        if (kant.equals("volgende")) {
            cal.setTime(beginDatum);
            cal.add(Calendar.DATE, +7);
            beginDatum = new Date(cal.getTimeInMillis());
            cal.setTime(eindDatum);
            cal.add(Calendar.DATE, +7);
            eindDatum = new Date(cal.getTimeInMillis());
            succes = true;
        } else if (kant.equals("vorige")) {
            cal.setTime(beginDatum);
            cal.add(Calendar.DATE, -7);
            beginDatum = new Date(cal.getTimeInMillis());
            cal.setTime(eindDatum);
            cal.add(Calendar.DATE, -7);
            eindDatum = new Date(cal.getTimeInMillis());
            succes = true;
        }

        return succes;
    }

    private void setHeaders(String kant) {
        JTableHeader header = roosterTabel.getTableHeader();
        TableColumnModel tcm = header.getColumnModel();
        cal = Calendar.getInstance();
        if (kant.equals("huidige")) {
            cal.setTime(beginWeek);
            Date maandag = new Date(cal.getTimeInMillis());
            tcm.getColumn(0).setHeaderValue("Maandag: " + dagMaand.format(maandag));
            cal.setTime(beginWeek);
            cal.add(Calendar.DATE, +1);
            Date dinsdag = new Date(cal.getTimeInMillis());
            tcm.getColumn(1).setHeaderValue("Dinsdag: " + dagMaand.format(dinsdag));
            cal.setTime(beginWeek);
            cal.add(Calendar.DATE, +2);
            Date woensdag = new Date(cal.getTimeInMillis());
            tcm.getColumn(2).setHeaderValue("Woensdag: " + dagMaand.format(woensdag));
            cal.setTime(beginWeek);
            cal.add(Calendar.DATE, +3);
            Date donderdag = new Date(cal.getTimeInMillis());
            tcm.getColumn(3).setHeaderValue("Donderdag: " + dagMaand.format(donderdag));
            cal.setTime(beginWeek);
            cal.add(Calendar.DATE, +4);
            Date vrijdag = new Date(cal.getTimeInMillis());
            tcm.getColumn(4).setHeaderValue("Vrijdag: " + dagMaand.format(vrijdag));
            header.repaint();
        }
        if (kant.equals("volgende")) {
            cal.setTime(beginWeek);
            cal.add(Calendar.DATE, +7);
            beginWeek = new Date(cal.getTimeInMillis());
            Date maandag = new Date(cal.getTimeInMillis());
            tcm.getColumn(0).setHeaderValue("Maandag: " + dagMaand.format(maandag));
            cal.setTime(beginWeek);
            cal.add(Calendar.DATE, +1);
            Date dinsdag = new Date(cal.getTimeInMillis());
            tcm.getColumn(1).setHeaderValue("Dinsdag: " + dagMaand.format(dinsdag));
            cal.setTime(beginWeek);
            cal.add(Calendar.DATE, +2);
            Date woensdag = new Date(cal.getTimeInMillis());
            tcm.getColumn(2).setHeaderValue("Woensdag: " + dagMaand.format(woensdag));
            cal.setTime(beginWeek);
            cal.add(Calendar.DATE, +3);
            Date donderdag = new Date(cal.getTimeInMillis());
            tcm.getColumn(3).setHeaderValue("Donderdag: " + dagMaand.format(donderdag));
            cal.setTime(beginWeek);
            cal.add(Calendar.DATE, +4);
            Date vrijdag = new Date(cal.getTimeInMillis());
            tcm.getColumn(4).setHeaderValue("Vrijdag: " + dagMaand.format(vrijdag));
            header.repaint();
        }
        if (kant.equals("vorige")) {
            cal.setTime(beginWeek);
            cal.add(Calendar.DATE, -7);
            beginWeek = new Date(cal.getTimeInMillis());
            Date maandag = new Date(cal.getTimeInMillis());
            tcm.getColumn(0).setHeaderValue("Maandag: " + dagMaand.format(maandag));
            cal.setTime(beginWeek);
            cal.add(Calendar.DATE, +1);
            Date dinsdag = new Date(cal.getTimeInMillis());
            tcm.getColumn(1).setHeaderValue("Dinsdag: " + dagMaand.format(dinsdag));
            cal.setTime(beginWeek);
            cal.add(Calendar.DATE, +2);
            Date woensdag = new Date(cal.getTimeInMillis());
            tcm.getColumn(2).setHeaderValue("Woensdag: " + dagMaand.format(woensdag));
            cal.setTime(beginWeek);
            cal.add(Calendar.DATE, +3);
            Date donderdag = new Date(cal.getTimeInMillis());
            tcm.getColumn(3).setHeaderValue("Donderdag: " + dagMaand.format(donderdag));
            cal.setTime(beginWeek);
            cal.add(Calendar.DATE, +4);
            Date vrijdag = new Date(cal.getTimeInMillis());
            tcm.getColumn(4).setHeaderValue("Vrijdag: " + dagMaand.format(vrijdag));
            header.repaint();
        }
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
        terugKnop = new javax.swing.JButton();
        negenTienLabel = new javax.swing.JLabel();
        tienElfLabel = new javax.swing.JLabel();
        elfTwaalfLabel = new javax.swing.JLabel();
        twaalfEenLabel = new javax.swing.JLabel();
        eenTweeLabel = new javax.swing.JLabel();
        tweeDrieLabel = new javax.swing.JLabel();
        drieVierLabel = new javax.swing.JLabel();
        vierVijfLabel = new javax.swing.JLabel();
        menuBalk = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        terugMenuItem = new javax.swing.JMenuItem();
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

        terugKnop.setText("Terug");
        terugKnop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terugKnopActionPerformed(evt);
            }
        });

        negenTienLabel.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        negenTienLabel.setText("09:00-10:00");

        tienElfLabel.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tienElfLabel.setText("10:00-11:00");

        elfTwaalfLabel.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        elfTwaalfLabel.setText("11:00-12:00");

        twaalfEenLabel.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        twaalfEenLabel.setText("12:00-13:00");

        eenTweeLabel.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        eenTweeLabel.setText("13:00-14:00");

        tweeDrieLabel.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tweeDrieLabel.setText("14:00-15:00");

        drieVierLabel.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        drieVierLabel.setText("15:00-16:00");

        vierVijfLabel.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        vierVijfLabel.setText("16:00-17:00");

        javax.swing.GroupLayout roosterPanelLayout = new javax.swing.GroupLayout(roosterPanel);
        roosterPanel.setLayout(roosterPanelLayout);
        roosterPanelLayout.setHorizontalGroup(
            roosterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roosterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roosterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roosterPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(terugKnop))
                    .addGroup(roosterPanelLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(roosterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(vierVijfLabel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(drieVierLabel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(negenTienLabel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tienElfLabel)
                            .addComponent(elfTwaalfLabel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(twaalfEenLabel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eenTweeLabel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tweeDrieLabel, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE))
                    .addGroup(roosterPanelLayout.createSequentialGroup()
                        .addGroup(roosterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roosterPanelLayout.createSequentialGroup()
                                .addGroup(roosterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(fysiotherapeutComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(beschrijvingLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(vorigeWeekKnop, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(volgendeWeekKnop))
                            .addGroup(roosterPanelLayout.createSequentialGroup()
                                .addGroup(roosterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(beschrijvingLabel3)
                                    .addComponent(beschrijvingLabel1))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
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
                        .addGap(18, 18, 18)
                        .addComponent(negenTienLabel)
                        .addGap(34, 34, 34)
                        .addComponent(tienElfLabel)
                        .addGap(36, 36, 36)
                        .addComponent(elfTwaalfLabel)
                        .addGap(37, 37, 37)
                        .addComponent(twaalfEenLabel)
                        .addGap(35, 35, 35)
                        .addComponent(eenTweeLabel)
                        .addGap(36, 36, 36)
                        .addComponent(tweeDrieLabel)
                        .addGap(36, 36, 36)
                        .addComponent(drieVierLabel)
                        .addGap(36, 36, 36)
                        .addComponent(vierVijfLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(terugKnop)
                .addContainerGap())
        );

        jMenu1.setText("File");

        terugMenuItem.setText("Terug");
        terugMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terugMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(terugMenuItem);

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
        if (setData("vorige")) {
            boolean success = control.maakRooster(beginDatum, eindDatum, fysiotherapeutComboBox.getSelectedItem().toString());
            if (success) {
                roosterTabel.setModel(control.getModel());
                setHeaders("vorige");
            } else {
                JOptionPane.showMessageDialog(this, "Vorige week faal!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "DATUM CALCULATIE FAAL!");
        }
    }//GEN-LAST:event_vorigeWeekKnopActionPerformed

    private void volgendeWeekKnopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volgendeWeekKnopActionPerformed
        if (setData("volgende")) {
            boolean success = control.maakRooster(beginDatum, eindDatum, fysiotherapeutComboBox.getSelectedItem().toString());
            if (success) {
                roosterTabel.setModel(control.getModel());
                setHeaders("volgende");
            } else {
                JOptionPane.showMessageDialog(this, "Vorige week faal!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "DATUM CALCULATIE FAAL!");
        }
    }//GEN-LAST:event_volgendeWeekKnopActionPerformed

    private void fysiotherapeutComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fysiotherapeutComboBoxActionPerformed
        String therapeutNaam = fysiotherapeutComboBox.getSelectedItem().toString();
        setDatumWeek();
        control.maakRooster(beginDatum, eindDatum, therapeutNaam);
        roosterTabel.setModel(control.getModel());
    }//GEN-LAST:event_fysiotherapeutComboBoxActionPerformed

    private void terugKnopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terugKnopActionPerformed
        sluitGUI();
    }//GEN-LAST:event_terugKnopActionPerformed

    private void terugMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terugMenuItemActionPerformed
        sluitGUI();
    }//GEN-LAST:event_terugMenuItemActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel beschrijvingLabel1;
    private javax.swing.JLabel beschrijvingLabel2;
    private javax.swing.JLabel beschrijvingLabel3;
    private javax.swing.JLabel drieVierLabel;
    private javax.swing.JLabel eenTweeLabel;
    private javax.swing.JLabel elfTwaalfLabel;
    private javax.swing.JComboBox fysiotherapeutComboBox;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menuBalk;
    private javax.swing.JLabel negenTienLabel;
    private javax.swing.JPanel roosterPanel;
    private javax.swing.JTable roosterTabel;
    private javax.swing.JButton terugKnop;
    private javax.swing.JMenuItem terugMenuItem;
    private javax.swing.JLabel tienElfLabel;
    private javax.swing.JLabel twaalfEenLabel;
    private javax.swing.JLabel tweeDrieLabel;
    private javax.swing.JLabel vierVijfLabel;
    private javax.swing.JButton volgendeWeekKnop;
    private javax.swing.JButton vorigeWeekKnop;
    // End of variables declaration//GEN-END:variables
}
