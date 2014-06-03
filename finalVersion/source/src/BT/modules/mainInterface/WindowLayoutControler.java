/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.mainInterface;

import BT.managers.DiagramPlacesManager;
import GUI.MainContentModel;
import GUI.CloseTabbedPane;
import GUI.MyToolBar;
import GUI.PlusTab;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import GUI.WindowLayoutModel;
import java.awt.Component;
import java.awt.event.ActionListener;

/**
 * Class that holds and maintain window layout of whole application.
 *
 * @author Karel Hala
 */
public class WindowLayoutControler {

    /**
     * CloseTabbedPane with opened files.
     */
    private final CloseTabbedPane fileTab;
    /**
     * Toolbar at top of application.
     */
    private final MyToolBar toolBar;
    /**
     * Plus tab at end of opened files.
     */
    private final PlusTab plusTab;
    /**
     * Listener for ading new tab.
     */
    private ActionListener addNewTabListener;

    /**
     * Basic constructor. It sets toolBar, ToolBarContentControler. It creates fileTab and plusTab.
     *
     * @param toolBar
     * @param toolBarContent
     */
    public WindowLayoutControler(MyToolBar toolBar, ToolBarContentControler toolBarContent) {
        this.toolBar = toolBar;
        fileTab = new CloseTabbedPane(toolBarContent);
        plusTab = new PlusTab();
        this.plusTab.addPlusTab(this.fileTab);
    }

    /**
     * Listener to add new file button.
     *
     * @param addNewTabListener addNewTabListener will be set to ActionListener .
     */
    public void setAddNewTabListener(ActionListener addNewTabListener) {
        this.addNewTabListener = addNewTabListener;
    }

    /**
     * Sets mouse clicked on plus action.
     */
    public void setMouseClickedOnPlusButton() {
        this.plusTab.addMouseClickedListenerToPlus(this.addNewTabListener);
    }

    /**
     * Method for adding new button to toolBar.
     *
     * @param pane Container with buttons and components.
     */
    public void addComponentsToPane(Container pane) {
        if (!(pane.getLayout() instanceof BorderLayout)) {
            pane.add(new JLabel("Container doesn't use BorderLayout!"));
            return;
        }

        pane.add(this.toolBar.getToolbar(), BorderLayout.PAGE_START);

        pane.add(this.fileTab, BorderLayout.CENTER);
    }

    /**
     * Method for inserting new tab to file tabs.
     *
     * @param UCContentModel MainContentModel of use case diagram.
     * @param UMLContentModel MainContentModel of class diagram.
     * @param OOPNContentModel MainContentModel of petriNets.
     * @param diagramPlaces DiagramPlacesManager each object and it's lines.
     */
    public void addNewTab(MainContentModel UCContentModel, MainContentModel UMLContentModel, MainContentModel OOPNContentModel, DiagramPlacesManager diagramPlaces) {
        addNewTab(UCContentModel, UMLContentModel, OOPNContentModel, diagramPlaces.getFileName());
    }

    /**
     * Method for removing tab, closed by button.
     *
     * @param selectedTab selected tab as Component.
     */
    public void removeTab(Component selectedTab) {
        removeTab(selectedTab, true);
    }

    /**
     * Remove selected tab.
     *
     * @param selectedTab tab to be removed.
     * @param closedByButton whether or not it was closed by botton or by shortcut.
     */
    public void removeTab(Component selectedTab, Boolean closedByButton) {
        if (closedByButton && this.fileTab.getTabCount() > 1) {
            this.fileTab.remove(selectedTab);
        } else if (!closedByButton) {
            this.fileTab.remove(selectedTab);
        }
        if (this.fileTab.getTabCount() > 1) {
            this.fileTab.setSelectedIndex(this.fileTab.getTabCount() - 2);
        } else {
            this.fileTab.setSelectedIndex(-1);
        }
    }

    /**
     * Returns selected tab.
     *
     * @return selected tab as Component.
     */
    public Component getSelectedTab() {
        return this.fileTab.getSelectedComponent();
    }

    /**
     * Add new tab to file tab. Create tab for useCase, classDiagram and OOPetriNets. Add these elemnts to close tab and
     * create plus tab panel.
     *
     * @param UCContentModel MainContentModel of useCase.
     * @param UMLContentModel MainContentModel of classDiagram.
     * @param OOPNContentModel MainContentModel of objected oriented pteri nets.
     * @param name file tab name.
     */
    public void addNewTab(MainContentModel UCContentModel, MainContentModel UMLContentModel, MainContentModel OOPNContentModel, String name) {
        JTabbedPane typeTab = new JTabbedPane();

        typeTab.addTab("UseCase", getWindowLayout(UCContentModel));

        typeTab.addTab("ClassDiagram", getWindowLayout(UMLContentModel));

        typeTab.addTab("OOPetriNets", getWindowLayout(OOPNContentModel));

        if (this.fileTab.getTabCount() > 0) {
            removeTab(this.fileTab.getComponentAt(this.fileTab.getTabCount() - 1), false);
        }
        this.fileTab.addCloseTab(name, typeTab);
        this.plusTab.addPlusTab(this.fileTab);
        setMouseClickedOnPlusButton();
        this.fileTab.setEnabledAt(this.fileTab.getTabCount() - 1, false);
    }

    /**
     * Creates myWindow and sets split panes and eventuly return left split pane.
     *
     * @param mycontent MainContentModel content with each object and elemtn in it.
     * @return myWindow.getLeftSplitPane() which holds left and right content.
     */
    private JSplitPane getWindowLayout(MainContentModel mycontent) {
        WindowLayoutModel myWindow = new WindowLayoutModel();
        myWindow.initSplitPanes();
        myWindow.setVerticals();
        myWindow.setContentsSplitPanes(mycontent);
        myWindow.setDividerLocation();
        return myWindow.getLeftSplitPane();
    }

    /**
     * Return file tab pane.
     *
     * @return fileTab as JTabbedPane.
     */
    public JTabbedPane getFileTab() {
        return this.fileTab;
    }
}
