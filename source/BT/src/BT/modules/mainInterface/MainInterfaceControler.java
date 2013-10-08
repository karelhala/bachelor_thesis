/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.mainInterface;

import BT.BaseControler;
import GUI.MainWindowModel;
import GUI.MyToolBar;

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
        ToolBarContentControler ToolBarContent = new ToolBarContentControler();
        MyToolBar toolBar = new MyToolBar();
        WindowLayoutControler myLayout = new WindowLayoutControler(toolBar);
        
        ToolBarContent.addBasicButtons(myLayout);
        toolBar.setPaneToolbar(ToolBarContent.getToolBarcontent().getToolBarPane());
                
        MainWindowModel mainWindowModel = new MainWindowModel("tOOl", myLayout);
        mainWindowModel.initComponents();
    }

}
