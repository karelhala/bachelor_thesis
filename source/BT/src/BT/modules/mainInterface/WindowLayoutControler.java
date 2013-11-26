/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.mainInterface;

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
import java.awt.event.MouseAdapter;

/**
 *
 * @author Karel Hala
 */
public class WindowLayoutControler {
    private CloseTabbedPane fileTab;
    private MyToolBar toolBar;
    private PlusTab plusTab;
    private MouseAdapter addNewTabListener;
    
    /**
     * 
     * @param toolBar 
     */
    public WindowLayoutControler(MyToolBar toolBar)
    {
        this.toolBar = toolBar;
        fileTab = new CloseTabbedPane();
        plusTab = new PlusTab();
        this.plusTab.AddPlusTab(this.fileTab);
    }
    
    /**
     * 
     * @param addNewTabListener 
     */
    public void setAddNewTabListener(MouseAdapter addNewTabListener)
    {
        this.addNewTabListener = addNewTabListener;
    }
    
    /**
     * 
     */
    public void setMouseClickedOnPlusButton()
    {
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
     */
    public void addNewTab(MainContentModel UCContentModel, MainContentModel UMLContentModel, MainContentModel OOPNContentModel)
    {
        addNewTab(UCContentModel, UMLContentModel, OOPNContentModel, "new file");
    }
    
    /**
     * 
     * @param selectedTab 
     */
    public void removeTab(Component selectedTab)
    {
        removeTab(selectedTab, true);
    }
    
    /**
     * 
     * @param selectedTab
     * @param closedByButton 
     */
    public void removeTab(Component selectedTab, Boolean closedByButton)
    {
        if (closedByButton && this.fileTab.getTabCount()>1)
        {
            this.fileTab.remove(selectedTab);
        }
        else if (!closedByButton)
        {
            this.fileTab.remove(selectedTab);
        }
        if (this.fileTab.getTabCount()>1)
        {
            this.fileTab.setSelectedIndex(this.fileTab.getTabCount() - 2);
        }
        else
        {
            this.fileTab.setSelectedIndex(-1);
        }
    }
    /**
     * 
     * @return 
     */
    public Component getSelectedTab()
    {
        return this.fileTab.getSelectedComponent();
    }
    
    /**
     * 
     * @param UCContentModel
     * @param UMLContentModel
     * @param OOPNContentModel
     * @param name 
     */
    public void addNewTab(MainContentModel UCContentModel, MainContentModel UMLContentModel, MainContentModel OOPNContentModel, String name)
    {
        JTabbedPane typeTab = new JTabbedPane();
        
        typeTab.addTab("UseCase", getWindowLayout(UCContentModel));
        typeTab.addTab("ClassDiagram", getWindowLayout(UMLContentModel));
        typeTab.addTab("OOPetriNets", getWindowLayout(OOPNContentModel));
       
        if (this.fileTab.getTabCount() > 0)
        {
            removeTab(this.fileTab.getComponentAt(this.fileTab.getTabCount()-1), false);
        }
        this.fileTab.addCloseTab(name, typeTab);
        this.plusTab.AddPlusTab(this.fileTab);
        setMouseClickedOnPlusButton();
        this.fileTab.setEnabledAt(this.fileTab.getTabCount()-1, false);
    }
    
    /**
     * 
     * @param mycontent
     * @return 
     */
    private JSplitPane getWindowLayout(MainContentModel mycontent)
    {
        WindowLayoutModel myWindow = new WindowLayoutModel();
        myWindow.initSplitPanes();
        myWindow.setVerticals();
        myWindow.setContentsSplitPanes(mycontent);
        myWindow.setDividerLocation();
        return myWindow.getLeftSplitPane();
    }
    
    /**
     * 
     * @return 
     */
    public JTabbedPane getFileTab()
    {
        return this.fileTab;
    }
}
