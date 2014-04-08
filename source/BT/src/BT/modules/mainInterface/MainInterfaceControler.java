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

        MouseAdapter addNewFileMouseclicked = ToolBarContent.getNewFileMouseClicked();
        myLayout.setAddNewTabListener(addNewFileMouseclicked);
        myLayout.setMouseClickedOnPlusButton();

        toolBar.setPaneToolbar(ToolBarContent.getToolBarcontent().getToolBarPane());

        MainWindowModel mainWindowModel = new MainWindowModel("tOOl", myLayout);
        mainWindowModel.initComponents();
	mainWindowModel.getMyMenu().addActionListenerToNewFileItem(ToolBarContent.getNewFileAction());
	mainWindowModel.getMyMenu().addActionListenerToCloseFileItem(ToolBarContent.getCloseFileAction());
        createButtonListeners(mainWindowModel.getMyMenu(), ToolBarContent);
        ToolBarContent.addBasicButtons(myLayout);
    }
    
    private void createButtonListeners(MyMenuBar menu, final ToolBarContentControler toolbarContent)
    {
        ActionListener saveAction = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                toolbarContent.setFileName("asdf");
                saveFile();
                System.out.println(toolbarContent.getFileName());
            }
        };
        toolbarContent.setSaveAction(saveAction);
        menu.addActionListenerToSave(saveAction);
    }
    
    private void saveFile()
    {
        
    }

}
