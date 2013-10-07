/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.mainInterface;

import BT.BaseControler;
import GUI.MainContentModel;
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
        MainContentModel myContent = new MainContentModel();
        
        MainWindowModel mainWindowModel = new MainWindowModel("tOOl");
        mainWindowModel.setContent(myContent);
        mainWindowModel.initComponents();
    }
    

}
