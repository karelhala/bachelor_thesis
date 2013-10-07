/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BT.modules.mainInterface.WindowLayoutControler;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Karel
 */
public class MainWindowModel extends javax.swing.JFrame{
    private int width = 1200;
    private int height = 750;
    private MainContentModel mycontent;
    
    public MainWindowModel(String programName, int width, int height) {
        super(programName);
        this.width = width;
        this.height = height;
    }
    
    public MainWindowModel(String programName) {
        super(programName);
    }
    
    public void setContent (MainContentModel content)
    {
        this.mycontent = content;
    }
    
    public void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(this.width, this.height));
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        MyMenuBar myMenu = new MyMenuBar();
        this.setJMenuBar(myMenu.getMainMenu());
        
        WindowLayoutControler myWindowLayoutControler = new WindowLayoutControler();
        myWindowLayoutControler.setContent(this.mycontent);
        myWindowLayoutControler.addComponentsToPane(this.getContentPane());
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
