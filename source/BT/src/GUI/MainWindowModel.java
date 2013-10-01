/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Karel
 */
public class MainWindowModel extends javax.swing.JFrame{
    private int width = 800;
    private int height = 800;
    
    public MainWindowModel(String programName, int width, int height) {
        super(programName);
        this.width = width;
        this.height = height;
    }
    
    public MainWindowModel(String programName) {
        super(programName);
    }
    
    public void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }// </editor-fold>   
}
