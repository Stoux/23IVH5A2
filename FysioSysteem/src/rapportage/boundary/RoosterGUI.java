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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import praktijk.entity.Therapeut;
import rapportage.control.RoosterControl;
import home.control.IconManager;

/**
 *
 * @author Rogier Welten
 */
public class RoosterGUI extends javax.swing.JFrame {

    private ArrayList<Therapeut> therapeuten;
    private Calendar kalender;
    private Date beginDatum;
    private Date eindDatum;
    private Date beginWeek;
    private DateFormat dagMaand;
    private DateFormat dagWeek;
    private HomeGUI homeGUI;
    private RoosterControl control;

    /**
     * Constructor voor RoosterGUI
     * @param homeGUI HomeGUI
     * @param bManager BehandelingManager
     */
    public RoosterGUI(HomeGUI homeGUI, BehandelingManager bManager) {
        IconManager.setIcon(this);
        //Maak de DateFormats
        dagMaand = new SimpleDateFormat("dd/MM"); // dag/maand (bijvoorbeeld 12-10)
        dagWeek = new SimpleDateFormat("u"); // Dag in de week (Maandag = 1 en Zondag = 7)
        this.homeGUI = homeGUI;
        initComponents();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { //Als het scherm gesloten wordt, roep de methode aan sluitGUI()
                sluitGUI();
            }
        });
        control = new RoosterControl((DefaultTableModel) roosterTabel.getModel(), bManager); //Aanmaken van de RoosterControl
        
        roosterTabel.setShowGrid(true); // lijnen in de roosterTabel laten zien
        this.setExtendedState(this.MAXIMIZED_BOTH); // scherm schermvullend maken

        therapeuten = bManager.getTherapeuten(); //ophalen van de ArrayList met therapeuten
        
        if(!therapeuten.isEmpty()){
            for (Therapeut therapeut : therapeuten) {// Voor elke Therapeut in de ArrayList wordt onderstaande uitgevoerd
                fysiotherapeutComboBox.addItem(therapeut.getBsn() + " | " + therapeut.getVolledigeNaam()); // toevoegen van de String aan de fysiotherapeutComboBox (BSN | naam therapeut)
            }
            boolean check = setDatumWeek(); //controle of setDatumWeek() succesvol is uitgevoerd
            if (check) { // Als setDatumWeek() succesvol is uitgevoerd wordt onderstaande uitgevoerd
                boolean succes = control.maakRooster(beginDatum, eindDatum, fysiotherapeutComboBox.getSelectedItem().toString()); //controle of maakRooster() succes vol is uitgevoerd
                if (succes) { // als maakRooster() succesvol is uitgevoerd wordt onderstaande uitgevoerd
                    roosterTabel.setModel(control.getModel()); //het TableModel ophalen uit de control en toewijzen aan de roosterTabal
                    setHeaders("huidige"); //Headers van de tabel aanpassen naar de data van deze week
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Er zijn geen fysiotherapeuten gevonden", "Fout", JOptionPane.ERROR_MESSAGE, null);
        }
        AutoCompleteDecorator.decorate(fysiotherapeutComboBox); // toevoegen dat de fysiotherapeutComboBox automatisch het BSN aanvult

    }
    /**
     * Methode die de data van de huidige week maakt, waarbij setDatumweek (Date, int, int, int) wordt aangeroepen
     * 
     * @return succes boolean
     */
    private boolean setDatumWeek() {
        boolean succes = false;
        
        Date huidigeDatum = new Date(); //Maken van de huidige datum
        int dagNummer = Integer.parseInt(dagWeek.format(huidigeDatum)); // Het dagnummer in de week van de huidige datum
        switch (dagNummer) { //Switch voor de dag in de week waarop de data gebaseerd zijn
            case 1: setDatumWeek(huidigeDatum, -1, +1, +5); succes = true; break; //Als het dagnummer 1 is, wordt de methode setDatumWeek uitgevoerd
            case 2: setDatumWeek(huidigeDatum, -2, +1, +4); succes = true; break; //Als het dagnummer 2 is, wordt de methode setDatumWeek uitgevoerd
            case 3: setDatumWeek(huidigeDatum, -3, +1, +3); succes = true; break; //Als het dagnummer 3 is, wordt de methode setDatumWeek uitgevoerd
            case 4: setDatumWeek(huidigeDatum, -4, +1, +2); succes = true; break; //Als het dagnummer 4 is, wordt de methode setDatumWeek uitgevoerd
            case 5: setDatumWeek(huidigeDatum, -5, +1, +1); succes = true; break; //Als het dagnummer 5 is, wordt de methode setDatumWeek uitgevoerd
            case 6: setDatumWeek(huidigeDatum, -6, +1, 0);  succes = true; break; //Als het dagnummer 6 is, wordt de methode setDatumWeek uitgevoerd
            case 7: setDatumWeek(huidigeDatum, -7, +1, -1); succes = true; break; //Als het dagnummer 7 is, wordt de methode setDatumWeek uitgevoerd
        }
        return succes;
    }

    /**
     * Methode die de data van de huidige week maakt, waarbij beginDatum, beginWeek en eindDatum een waarde krijgen
     * 
     * @param huidigeDatum De huidige datum
     * @param dagBegin aantal dagen wat van de huidigeDatum afgehaald moet worden, zodat beginDatum op zondag komt te staan
     * @param dagWeek aantal dagen wat bij de datum opgeteld wordt, zodat beginWeek op maandag komt te staan
     * @param dagEind aantal dagen wat bij de huidigeDatum opgeteld moet worden, zodat eindDatum op zaterdag komt te staan
     */
    private void setDatumWeek(Date huidigeDatum, int dagBegin, int dagWeek, int dagEind) {
        kalender = Calendar.getInstance(); // Ophalen van de kalender met de huidige tijdzone
        kalender.setTime(huidigeDatum); // De huidige datum van de kalender op huidigeDatum zetten
        kalender.add(Calendar.DATE, dagBegin); //De huidige datum van de kalender optellen met x aantal dagen
        beginDatum = new Date(kalender.getTimeInMillis()); // beginDatum maken op basis van de huidige tijd op de kalender
        kalender.add(Calendar.DATE, dagWeek); //De huidige datum van de kalender optellen met x aantal dagen
        beginWeek = new Date(kalender.getTimeInMillis()); // dagWeek maken als eerste dag van de week (maandag)
        kalender.setTime(huidigeDatum); //De huidgie datum van de kalender op huidigeDatum zetten
        kalender.add(Calendar.DATE, dagEind); //De huidige datum van de kalender optellen met x aantal dagen
        eindDatum = new Date(kalender.getTimeInMillis()); // eindDatum maken op basis van de huidige tijd op de kalender
    }
    /**
     * Methode die bepaalt welk getal aan de methode setData(int) meegegeven wordt
     * 
     * @param kant de kant welke het rooster op moet (vorige week of volgende week)
     * @return succes boolean
     */
    private boolean setData(String kant) {
        boolean succes = false;
        switch (kant) {
            case "volgende": setData(+7); succes = true; break; // als kant volgende is, wordt de methode setData(+7) uitgevoerd
            case "vorige":   setData(-7); succes = true; break; // als kant vorige is, wordt de methode setData(-7) uitgevoerd
        }
        return succes;
    }

    /**
     * Methode die de data van de week maakt, waarbij begindatum en eindDatum een nieuwe waarde krijgen
     * 
     * @param addDagen aantal dagen wat bij de data opgeteld moet worden
     */
    private void setData(int addDagen) {
        kalender = Calendar.getInstance(); // Ophalen van de kalender met de huidige tijdzone
        kalender.setTime(beginDatum); // De huidige datum van de kalender op beginDatum zetten
        kalender.add(Calendar.DATE, addDagen); //De huidige datum van de kalender optellen/aftrekken met x aantal dagen
        beginDatum = new Date(kalender.getTimeInMillis()); // beginDatum maken op basis van de huidige datum op de kalender
        kalender.setTime(eindDatum); // De huidige datum van de kalender op eindDatum zetten
        kalender.add(Calendar.DATE, addDagen); //De huidige datum van de kalender optellen/aftrekken met x aantal dagen
        eindDatum = new Date(kalender.getTimeInMillis()); // eindDatum maken op basis van de huidige datum op de kalender
    }

    /**
     * Methode die bepaald welk getal aan de methode setHeaders(int) meegegeven wordt
     * 
     * @param kant de kant welke het rooster op moet (huidige week, vorige week of volgende week)
     */
    private void setHeaders(String kant) {
        switch (kant) {
            case "huidige":  setHeaders(0);  break; // als kant huidige is, wordt de methode setHeaders(0) uitgevoerd
            case "vorige":   setHeaders(-7); break; // als kant vorige is, wordt de methode setHeaders(-7) uitgevoerd
            case "volgende": setHeaders(+7); break; // als kant volgende is, wordt de methode setHeaders(+7) uitgevoerd
        }
    }

    /**
     * Methode die de data van week maakt die in de headers geplaatst worden
     * 
     * @param addDagen aantal dagen wat bij de data opgeteld moet worden of afgetrokken
     */
    private void setHeaders(int addDagen) {
        JTableHeader header = roosterTabel.getTableHeader(); //Ophalen van de Header van de roosterTabel
        TableColumnModel tcm = header.getColumnModel(); // ophalen van het ColumModel van de header
        kalender = Calendar.getInstance();// Ophalen van de kalender met de huidige tijdzone
        kalender.setTime(beginWeek); //De huidige datum van de kalender op beginWeek zetten
        kalender.add(Calendar.DATE, addDagen); //De huidige datum van de kalender optellen/aftrekken met een x aantal dagen
        beginWeek = new Date(kalender.getTimeInMillis()); // nieuwe beginWeek maken op basis van de huidige datum op de kalender
        Date maandag = new Date(kalender.getTimeInMillis()); // maandag maken op basis van de huidige datum op de kalender
        tcm.getColumn(0).setHeaderValue("Maandag: " + dagMaand.format(maandag)); // plaatsen van de String (Maandag: dag/maand) in de table header
        kalender.setTime(beginWeek); // de huidige datum van de kalender op de nieuwe beginWeek zetten
        kalender.add(Calendar.DATE, +1);// de huidige datum van de kalender optellen met 1 dag
        Date dinsdag = new Date(kalender.getTimeInMillis()); // dinsdag maken op basis van de huidige datum op de kalender
        tcm.getColumn(1).setHeaderValue("Dinsdag: " + dagMaand.format(dinsdag)); // plaatsen van de String (Dinsdag: dag/maand) in de table header
        kalender.setTime(beginWeek); // de huidige datum van de kalender op de nieuwe beginWeek zetten
        kalender.add(Calendar.DATE, +2); // de huidige datum van de kalender optellen met 2 dagen
        Date woensdag = new Date(kalender.getTimeInMillis()); // woensdag maken op basis van de huidige datum op de kalender 
        tcm.getColumn(2).setHeaderValue("Woensdag: " + dagMaand.format(woensdag)); // plaatsen van de String (Woensdag: dag/maand) in de table header
        kalender.setTime(beginWeek); // de huidige datum van de kalender op de nieuwe beginWeek zetten
        kalender.add(Calendar.DATE, +3); // de huidige datum van de kalender optellen met 3 dagen
        Date donderdag = new Date(kalender.getTimeInMillis()); // donderdag maken op basis van de huidige datum op de kalender
        tcm.getColumn(3).setHeaderValue("Donderdag: " + dagMaand.format(donderdag)); // plaatsen van de String (Donderdag: dag/maand) in de table header
        kalender.setTime(beginWeek); // de huidige datum van de kalender op de nieuwe beginWeek zetten
        kalender.add(Calendar.DATE, +4); // de huidige datum van de kalender optellen met 4 dagen
        Date vrijdag = new Date(kalender.getTimeInMillis()); // // vrijdag maken op basis van de huidige datum op de kalender
        tcm.getColumn(4).setHeaderValue("Vrijdag: " + dagMaand.format(vrijdag)); // plaatsen van de String (Vrijdag: dag/maand) in de table header
        header.repaint(); // verversen van de header, waarbij de nieuwe header getoond wordt
    }

    /**
     *Methode die zorgt dat de homeGUI zichtbaar wordt en het huidige scherm afgesloten wordt
     */
    private void sluitGUI() {
        homeGUI.maakZichtbaar(); // zichtbaar maken van de homeGUI
        dispose(); // sluiten van het huidige scherm
    }
    /**
     * Methode die zorgt dat de juiste methoden aangeroepen worden als er een actie uitgevoerd wordt
     * 
     * @param kant de kant welke het rooster op moet (vorige week of volgende week)
     * @param foutMelding1 foutmelding die gegeven moet worden als er geen rooster gemaakt kan worden
     * @param foutMelding2 foutmelding die gegeven moet worden als de data van de week niet bepaald kunnen worden
     */
    private void actieUitgevoerdKnop(String kant, String foutMelding1, String foutMelding2){
        if (setData(kant)) { // Als setData(String) succesvol is uitgevoerd, wordt onderstaande uitgevoerd
                boolean success = control.maakRooster(beginDatum, eindDatum, fysiotherapeutComboBox.getSelectedItem().toString()); //controle of maakRooster() succesvol is uitgevoerd
                if (success) { // als maakRooster() succesvol is uitgevoerd wordt onderstaande uitgevoerd
                    roosterTabel.setModel(control.getModel()); //het TableModel ophalen uit de control en toewijzen aan de roosterTabal
                    
                } else { //Als maakRooster() niet succesvol is uitgevoerd wordt een foutmelding getoond
                    JOptionPane.showMessageDialog(this, foutMelding1, "Melding", JOptionPane.INFORMATION_MESSAGE, null);
                  }setHeaders(kant); //Headers van de tabel aanpassen naar de data van deze week
            } else {//Als setData(String) niet succesvol is uitgevoerd wordt een foutmelding getoond
                JOptionPane.showMessageDialog(this, foutMelding2, "Fout", JOptionPane.ERROR_MESSAGE, null);
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
        setTitle("Rooster");

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
                .addGap(239, 239, 239)
                .addGroup(roosterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(vierVijfLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(drieVierLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(negenTienLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tienElfLabel)
                    .addComponent(elfTwaalfLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(twaalfEenLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eenTweeLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tweeDrieLabel, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roosterPanelLayout.createSequentialGroup()
                .addGap(1026, 1026, 1026)
                .addComponent(terugKnop))
            .addGroup(roosterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roosterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roosterPanelLayout.createSequentialGroup()
                        .addComponent(beschrijvingLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(vorigeWeekKnop, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(volgendeWeekKnop))
                    .addComponent(beschrijvingLabel3)
                    .addComponent(beschrijvingLabel1)
                    .addComponent(fysiotherapeutComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
        String therapeut = fysiotherapeutComboBox.getSelectedItem().toString();
        actieUitgevoerdKnop("vorige", "Fysiotherapeut " +  therapeut.split(" | ")[2] + " " + therapeut.split(" | ")[3]  + " had vorige week geen behandelingen" , "Data van de week kunnen niet worden bepaald");
    }//GEN-LAST:event_vorigeWeekKnopActionPerformed

    private void volgendeWeekKnopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volgendeWeekKnopActionPerformed
        String therapeut = fysiotherapeutComboBox.getSelectedItem().toString();
        actieUitgevoerdKnop("volgende", "Fysiotherapeut " +  therapeut.split(" | ")[2] + " " + therapeut.split(" | ")[3]  + " heeft volgende week geen behandelingen" , "Data van de week kunnen niet worden bepaald");
    }//GEN-LAST:event_volgendeWeekKnopActionPerformed

    private void fysiotherapeutComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fysiotherapeutComboBoxActionPerformed
        if (setDatumWeek()) { // Als setDatumWeek() succesvol is uitgevoerd, wordt onderstaande uitgevoerd
            boolean success = control.maakRooster(beginDatum, eindDatum, fysiotherapeutComboBox.getSelectedItem().toString()); //controle of maakRooster() succesvol is uitgevoerd
            if (success) { // als maakRooster() succesvol is uitgevoerd wordt onderstaande uitgevoerd
                roosterTabel.setModel(control.getModel()); //het TableModel ophalen uit de control en toewijzen aan de roosterTabal
                setHeaders("huidige"); //Headers van de tabel aanpassen naar de data van deze week
            } else { //Als maakRooster() niet succesvol is uitgevoerd wordt een foutmelding getoond
                JOptionPane.showMessageDialog(this, "Er kan geen rooster gemaakt worden voor de huidige week", "Fout", JOptionPane.ERROR_MESSAGE, null);
              }
        } else {//Als setData(String) niet succesvol is uitgevoerd wordt een foutmelding getoond
            JOptionPane.showMessageDialog(this, "Data van de week kunnen niet worden bepaald", "Fout", JOptionPane.ERROR_MESSAGE, null);
        }
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
