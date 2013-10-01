/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.mainInterface;

import BT.BaseControler;
import GUI.MainWindowModel;

/**
 *
 * @author Karel
 */
public class MainInterfaceControler extends BaseControler{

    String programName;
    public MainInterfaceControler (String programName)
    {
        super();
        this.programName = programName;
    }
    
    public void runTheMainWindow()
    {
    java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindowModel mainWindowModel = new MainWindowModel("tOOl");
                mainWindowModel.initComponents();
            }
        });
    }
    

}
