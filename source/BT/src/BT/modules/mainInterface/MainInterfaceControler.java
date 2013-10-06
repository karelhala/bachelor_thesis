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
        this.programName = programName;
    }
    
    public void runTheMainWindow()
    {
        MainWindowModel mainWindowModel = new MainWindowModel("tOOl");
        mainWindowModel.initComponents();
    }
    

}
