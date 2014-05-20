/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.mainInterface;

import BT.managers.DiagramPlacesManager;
import GUI.MainWindowModel;
import GUI.MyToolBar;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.io.StreamException;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Class that controlls main interface. It creates listeners for each button and menu item.
 *
 * @author Karel Hala
 */
public class MainInterfaceControler {

    /**
     * Toolbar with each button to manage this tool.
     */
    final private ToolBarContentControler ToolBarContent;
    /**
     * Toolbar model.
     */
    final private MyToolBar toolBar;
    /**
     * For controlling layout and feel of window.
     */
    final private WindowLayoutControler myLayout;
    /**
     * for setting basic size of window.
     */
    final private MainWindowModel mainWindowModel;
    /**
     * Manager for opening and reading help files.
     */
    final private HelpManager helpManager;

    /**
     * Basic constructor. It creates new ToolBarContentControler, MyToolBar, WindowLayoutControler, MainWindowModel and
     * help manager.
     *
     * @param programName
     */
    public MainInterfaceControler(String programName) {
        this.ToolBarContent = new ToolBarContentControler();
        this.toolBar = new MyToolBar();
        this.myLayout = new WindowLayoutControler(this.toolBar, this.ToolBarContent);
        this.mainWindowModel = new MainWindowModel(programName, this.myLayout);
        this.helpManager = new HelpManager();
    }

    /**
     * Create main window and set listeners and feel of window.
     */
    public void runTheMainWindow() {
        createListeners();
        this.toolBar.setPaneToolbar(this.ToolBarContent.getToolBarcontent().getToolBarPane());
        this.mainWindowModel.initComponents();
        this.ToolBarContent.setBasicListeners(this.myLayout).addBasicButtons();
        this.myLayout.setAddNewTabListener(this.ToolBarContent.getNewFileAction());
        this.myLayout.setMouseClickedOnPlusButton();
        this.helpManager.loadFiles().setListenerToComboBox();
        setMenuListeners();
    }

    /**
     * Method for creating basic listeners in this program, for maintaining file operations.
     */
    private void createListeners() {
        final MainInterfaceListeners mainInterfaceListeners = new MainInterfaceListeners(ToolBarContent);

        this.ToolBarContent.setNewFileAction(new ActionListener() {
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

        this.ToolBarContent.setExitAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                for (Frame oneFrame : Frame.getFrames()) {
                    oneFrame.dispose();
                }
            }
        });

        this.ToolBarContent.setSaveAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String fileName = mainInterfaceListeners.clickedOnSave(myLayout.getFileTab().getSelectedIndex());
                if (fileName != null && myLayout.getFileTab().getSelectedIndex() != -1) {
                    Component tabComponent = myLayout.getFileTab().getTabComponentAt(myLayout.getFileTab().getSelectedIndex());
                    if (tabComponent instanceof JPanel) {
                        JPanel tabPanel = (JPanel) tabComponent;
                        if (tabPanel.getComponent(0) instanceof JLabel) {
                            ((JLabel) tabPanel.getComponent(0)).setText(fileName);
                        }
                    }
                }
            }
        });

        this.ToolBarContent.setOpenFileAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    DiagramPlacesManager diagramsFile = mainInterfaceListeners.clickedOnOpen();
                    Boolean fileAllredyOpened = false;
                    for (DiagramPlacesManager oneDiagramPlace : ToolBarContent.getDiagramPlaces()) {
                        if (diagramsFile != null && oneDiagramPlace.getAbsolutePath() != null && oneDiagramPlace.getAbsolutePath().equals(diagramsFile.getAbsolutePath())) {
                            myLayout.getFileTab().setSelectedIndex(oneDiagramPlace.getDiagramNumber());
                            fileAllredyOpened = true;
                        }
                    }
                    if (diagramsFile != null && !fileAllredyOpened) {
                        ToolBarContent.openedFile(diagramsFile, myLayout);
                    }
                } catch (StreamException | ConversionException fatalError) {
                    JOptionPane.showMessageDialog(null, "Fatal error when trying to open file.");
                }
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
                if (ToolBarContent.getDiagramById(myLayout.getFileTab().getSelectedIndex()) != null) {
                    DiagramPlacesManager workingDiagramPlaces = ToolBarContent.getDiagramById(myLayout.getFileTab().getSelectedIndex());
                    File originalPath = workingDiagramPlaces.getAbsolutePath();
                    workingDiagramPlaces.setAbsolutePath(null);
                    String fileName = mainInterfaceListeners.clickedOnSave(myLayout.getFileTab().getSelectedIndex());

                    if (fileName != null) {
                        try {
                            DiagramPlacesManager openedFile = mainInterfaceListeners.getDiagramsFromXML(workingDiagramPlaces.getAbsolutePath());
                            if (openedFile != null) {
                                ToolBarContent.openedFile(openedFile, myLayout);
                            }
                        } catch (StreamException | ConversionException fatalError) {
                            JOptionPane.showMessageDialog(null, "Fatal error when trying to open file.");
                        }
                    }
                    workingDiagramPlaces.setAbsolutePath(originalPath);
                } else {
                    JOptionPane.showMessageDialog(null, "No file to save.");
                }
            }
        });
        this.ToolBarContent.setHelpAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int selectedTab = 0;
                if (myLayout.getSelectedTab() instanceof JTabbedPane) {
                    selectedTab = ((JTabbedPane) myLayout.getSelectedTab()).getSelectedIndex() + 1;
                }
                helpManager.showDialog(selectedTab);
            }
        });
    }

    /**
     * Method that sets listeners to menu.
     */
    private void setMenuListeners() {
        this.mainWindowModel.getMyMenu().addActionListenerToNewFileItem(ToolBarContent.getNewFileAction());
        this.mainWindowModel.getMyMenu().addActionListenerToCloseFileItem(ToolBarContent.getCloseFileAction());
        this.mainWindowModel.getMyMenu().addActionListenerToSave(ToolBarContent.getSaveAction());
        this.mainWindowModel.getMyMenu().addActionListenerToOpenNewFileItem(ToolBarContent.getOpenFileAction());
        this.mainWindowModel.getMyMenu().addActionListenerToExportEps(ToolBarContent.getExportEpsAction());
        this.mainWindowModel.getMyMenu().addActionListenerToExportPdf(ToolBarContent.getExportPdfAction());
        this.mainWindowModel.getMyMenu().addActionListenerToExportXml(ToolBarContent.getExportXmlAction());
        this.mainWindowModel.getMyMenu().addActionListenerToSaveAs(ToolBarContent.getSaveAsAction());
        this.mainWindowModel.getMyMenu().addActionListenerToExit(ToolBarContent.getExitAction());
        this.mainWindowModel.getMyMenu().addActionListenerToCurrentHelp(ToolBarContent.getHelpAction());
        this.mainWindowModel.getMyMenu().addActionListenerToUseCaseHelp(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                helpManager.showDialog(1);
            }
        });

        this.mainWindowModel.getMyMenu().addActionListenerToClassDiagramHelp(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                helpManager.showDialog(2);
            }
        });

        this.mainWindowModel.getMyMenu().addActionListenerToOOPNHelp(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                helpManager.showDialog(3);
            }
        });

        this.mainWindowModel.getMyMenu().addActionListenerToApplicationHelp(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                helpManager.showDialog(0);
            }
        });
    }

}
