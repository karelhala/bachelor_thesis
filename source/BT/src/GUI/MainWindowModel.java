/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BT.modules.mainInterface.WindowLayoutControler;
import java.awt.Dimension;

/**
 *
 * @author Karel Hala
 */
public class MainWindowModel extends javax.swing.JFrame {

    private int width = 1200;
    private int height = 750;
    private WindowLayoutControler myLayout;
    private MyMenuBar myMenu;

    public MainWindowModel(String programName, int width, int height, WindowLayoutControler myLayout) {
        super(programName);
        this.width = width;
        this.height = height;
        this.myLayout = myLayout;
    }

    public MainWindowModel(String programName, WindowLayoutControler myLayout) {
        super(programName);
        this.myLayout = myLayout;
    }

    /**
     *
     */
    public void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(this.width, this.height));
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.myMenu = new MyMenuBar();
        this.setJMenuBar(this.myMenu.getMainMenu());

        this.myLayout.addComponentsToPane(this.getContentPane());

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public MyMenuBar getMyMenu() {
        return myMenu;
    }
}
