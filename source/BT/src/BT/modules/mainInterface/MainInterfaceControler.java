/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.mainInterface;

import GUI.MainWindowModel;
import GUI.MyToolBar;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Karel Hala
 */
public class MainInterfaceControler {
    final private String programName;
    final private ToolBarContentControler ToolBarContent;
    final private MyToolBar toolBar;
    final private WindowLayoutControler myLayout;
    final private MainWindowModel mainWindowModel;

    /**
     *
     * @param programName
     */
    public MainInterfaceControler(String programName) {
        this.programName = programName;
        this.ToolBarContent = new ToolBarContentControler();
        this.toolBar = new MyToolBar();
        this.myLayout = new WindowLayoutControler(this.toolBar, this.ToolBarContent);
        this.mainWindowModel = new MainWindowModel(programName, this.myLayout);
    }

    /**
     *
     */
    public void runTheMainWindow() {
        createListeners();
        this.toolBar.setPaneToolbar(this.ToolBarContent.getToolBarcontent().getToolBarPane());
        this.mainWindowModel.initComponents();
        this.ToolBarContent.setBasicListeners(this.myLayout).addBasicButtons();
        this.myLayout.setAddNewTabListener(this.ToolBarContent.getNewFileAction());
        this.myLayout.setMouseClickedOnPlusButton();        
        setMenuListeners();
    }
    
    /**
     * Method for creating basic listeners in this program, for maintaining file operations.
     */
    private void createListeners()
    {
        final MainInterfaceListeners mainInterfaceListeners = new MainInterfaceListeners(ToolBarContent);
        
        this.ToolBarContent.setNewFileAction( new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		ToolBarContent.NewFileButtonMouseClicked(myLayout);
	    }
	});
        this.ToolBarContent.setCloseFileAction(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		ToolBarContent.CloseButtonMouseClicked(myLayout);
	    }
	});
        
        this.ToolBarContent.setSaveAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String fileName = mainInterfaceListeners.clickedOnSave(myLayout.getFileTab().getSelectedIndex());
                Component tabComponent = myLayout.getFileTab().getTabComponentAt(myLayout.getFileTab().getSelectedIndex());
                if (tabComponent instanceof JPanel)
                {
                    JPanel tabPanel = (JPanel) tabComponent;
                    if (tabPanel.getComponent(0) instanceof JLabel)
                    {
                        ((JLabel)tabPanel.getComponent(0)).setText(fileName);
                    }
                }
            }
        });
        
        this.ToolBarContent.setOpenFileAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mainInterfaceListeners.clickedOnOpen();
            }
        });
        
        this.ToolBarContent.setExportEpsAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mainInterfaceListeners.clickedOnExportToEPS(myLayout.getFileTab().getSelectedIndex());
            }
        });
        
        this.ToolBarContent.setExportPdfAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mainInterfaceListeners.clickedOnExportToPDF(myLayout.getFileTab().getSelectedIndex());
            }
        });
        
        this.ToolBarContent.setExportXmlAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mainInterfaceListeners.clickedOnExportToXML(myLayout.getFileTab().getSelectedIndex());
            }
        });
        
        this.ToolBarContent.setSaveAsAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mainInterfaceListeners.clickedOnSaveAs(myLayout.getFileTab().getSelectedIndex());
            }
        });
    }
    
    /**
     * Method that sets listeners to menu.
     */
    private void setMenuListeners()
    {
        this.mainWindowModel.getMyMenu().addActionListenerToNewFileItem(ToolBarContent.getNewFileAction());
	this.mainWindowModel.getMyMenu().addActionListenerToCloseFileItem(ToolBarContent.getCloseFileAction());
        this.mainWindowModel.getMyMenu().addActionListenerToSave(ToolBarContent.getSaveAction());
        this.mainWindowModel.getMyMenu().addActionListenerToOpenNewFileItem(ToolBarContent.getOpenFileAction());
        this.mainWindowModel.getMyMenu().addActionListenerToExportEps(ToolBarContent.getExportEpsAction());
        this.mainWindowModel.getMyMenu().addActionListenerToExportPdf(ToolBarContent.getExportPdfAction());
        this.mainWindowModel.getMyMenu().addActionListenerToExportXml(ToolBarContent.getExportXmlAction());
        this.mainWindowModel.getMyMenu().addActionListenerToSaveAs(ToolBarContent.getSaveAsAction());
        this.mainWindowModel.getMyMenu().addActionListenerToExit(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                for (Frame oneFrame : Frame.getFrames()) {
                    oneFrame.dispose();
                }
            }
        });
    }

}
