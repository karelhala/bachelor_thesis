/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        
        MyMenuBar myMenu = new MyMenuBar();
        this.setJMenuBar(myMenu.getMainMenu());
        
        addComponentsToPane(this.getContentPane());
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    private static void addComponentsToPane(Container pane) {
        if (!(pane.getLayout() instanceof BorderLayout)) {
            pane.add(new JLabel("Container doesn't use BorderLayout!"));
            return;
        }

        MyToolBar toolbar = new MyToolBar();
        pane.add(toolbar.getToolbar(), BorderLayout.PAGE_START);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 100));
        pane.add(panel, BorderLayout.CENTER);
    }
}
