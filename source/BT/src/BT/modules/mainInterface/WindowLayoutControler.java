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
 *
 * @author Karel Hala
 */
public class WindowLayoutControler {

    /**
     *
     */
    private final CloseTabbedPane fileTab;
    /**
     *
     */
    private final MyToolBar toolBar;
    /**
     *
     */
    private final PlusTab plusTab;
    /**
     *
     */
    private ActionListener addNewTabListener;

    /**
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
     *
     * @param addNewTabListener
     */
    public void setAddNewTabListener(ActionListener addNewTabListener) {
        this.addNewTabListener = addNewTabListener;
    }

    /**
     *
     */
    public void setMouseClickedOnPlusButton() {
        this.plusTab.addMouseClickedListenerToPlus(this.addNewTabListener);
    }

    /**
     *
     * @param pane
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
     *
     * @param UCContentModel
     * @param UMLContentModel
     * @param OOPNContentModel
     * @param diagramPlaces
     */
    public void addNewTab(MainContentModel UCContentModel, MainContentModel UMLContentModel, MainContentModel OOPNContentModel, DiagramPlacesManager diagramPlaces) {
        addNewTab(UCContentModel, UMLContentModel, OOPNContentModel, diagramPlaces.getFileName());
    }

    /**
     *
     * @param selectedTab
     */
    public void removeTab(Component selectedTab) {
        removeTab(selectedTab, true);
    }

    /**
     *
     * @param selectedTab
     * @param closedByButton
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
     *
     * @return
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
