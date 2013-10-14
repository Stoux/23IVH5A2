/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package home.control;

import java.awt.Image;
import javax.swing.JFrame;

/**
 *
 * @author Leon
 */
public class IconManager {
    
    private static Image image;
    
    /**
     * Set de icon van het frame
     * @param frame 
     */
    public static void setIcon(JFrame frame) {
        if (image != null) {
            frame.setIconImage(image);
        }
    }
    
    /**
     * Initializeer de con
     * @param icon nieuwe icon
     */
    public static void intializeerIcon(Image icon) {
        image = icon;
    }
    
}
