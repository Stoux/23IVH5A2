/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package home.control;

import behandel.control.BehandelingManager;
import data.control.DataController;
import home.boundary.StartGUI;
import static java.lang.Thread.sleep;
import praktijk.control.PraktijkManager;
import praktijk.control.TherapeutManager;

/**
 *
 * @author Leon
 */
public class StartThread extends Thread {

    private StartGUI startScherm;
    
    /**
     * The DataController
     */
    private DataController dataController;
    
    /**
     * Therapeut/Praktijk managers
     */
    private TherapeutManager therapeutManager;
    private PraktijkManager praktijkManager;
    
    /**
     * Behandeling controllers
     */
    private BehandelingManager behandelingManager;

    public StartThread(StartGUI ss) {
        startScherm = ss;
    }
    
    @Override
    public void run() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            this.interrupt();
        }
        startScherm.setStatus(1);
        dataController = new DataController();
        startScherm.setStatus(2);
        therapeutManager = new TherapeutManager(dataController);
        startScherm.setStatus(3);
        praktijkManager = new PraktijkManager(dataController);        
        startScherm.setStatus(4);
        behandelingManager = new BehandelingManager(dataController, therapeutManager);
        startScherm.setStatus(5);
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            this.interrupt();
        }
        startScherm.openHomeScherm(therapeutManager, praktijkManager, behandelingManager);
    }

}
