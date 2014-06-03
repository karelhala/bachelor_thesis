/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BT.modules.mainInterface.WindowLayoutControler;
import java.awt.Dimension;

/**
 * Class which describes look and feel of main window. This class extends JFrame and set's basic behavior of window.
 *
 * @author Karel Hala
 */
public class MainWindowModel extends javax.swing.JFrame {

    /**
     * Basic width of window.
     */
    private int width = 1200;

    /**
     * Basic height of window.
     */
    private int height = 750;
    /**
     * Controller which describes functionality of window.
     */
    private final WindowLayoutControler myLayout;
    /**
     * Main menu controller.
     */
    private MyMenuBar myMenu;

    /**
     * It sets programName, width and height and myLayout.
     *
     * @param programName name of program will be set to this.
     * @param width window width.
     * @param height window height.
     * @param myLayout WindowLayoutControler.
     */
    public MainWindowModel(String programName, int width, int height, WindowLayoutControler myLayout) {
        super(programName);
        this.width = width;
        this.height = height;
        this.myLayout = myLayout;
    }

    /**
     * It creates window and sets height and width to basic values.
     *
     * @param programName name of program will be set to this.
     * @param myLayout WindowLayoutControler.
     */
    public MainWindowModel(String programName, WindowLayoutControler myLayout) {
        super(programName);
        this.myLayout = myLayout;
    }

    /**
     * Insert and init each cpomponent of window. Window will not be maximized.
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
