/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.mainInterface;

import GUI.MainWindowModel;
import GUI.MyMenuBar;
import GUI.MyToolBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

/**
 *
 * @author Karel Hala
 */
public class MainInterfaceControler {

    String programName;

    /**
     *
     * @param programName
     */
    public MainInterfaceControler(String programName) {
        this.programName = programName;
    }

    /**
     *
     */
    public void runTheMainWindow() {
        ToolBarContentControler ToolBarContent = new ToolBarContentControler();
        MyToolBar toolBar = new MyToolBar();
        WindowLayoutControler myLayout = new WindowLayoutControler(toolBar);

        toolBar.setPaneToolbar(ToolBarContent.getToolBarcontent().getToolBarPane());
        MainWindowModel mainWindowModel = new MainWindowModel("tOOl", myLayout);
        mainWindowModel.initComponents();
        ToolBarContent.setBasicListeners(myLayout);
        ToolBarContent.addBasicButtons();
        ActionListener addNewFileMouseclicked = ToolBarContent.getNewFileAction();
        myLayout.setAddNewTabListener(addNewFileMouseclicked);
        myLayout.setMouseClickedOnPlusButton();
	mainWindowModel.getMyMenu().addActionListenerToNewFileItem(ToolBarContent.getNewFileAction());
	mainWindowModel.getMyMenu().addActionListenerToCloseFileItem(ToolBarContent.getCloseFileAction());
    }

}
