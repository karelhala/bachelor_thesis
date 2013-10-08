/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.mainInterface;

import GUI.MainContentModel;
import GUI.MyToolBar;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import GUI.WindowLayoutModel;
import java.awt.Component;

/**
 *
 * @author Karel
 */
public class WindowLayoutControler {
    private JTabbedPane fileTab;
    private MyToolBar toolBar;
    public WindowLayoutControler(MyToolBar toolBar)
    {
        this.toolBar = toolBar;
        fileTab = new JTabbedPane();
    }
    
    
    public void addComponentsToPane(Container pane) {
        if (!(pane.getLayout() instanceof BorderLayout)) {
            pane.add(new JLabel("Container doesn't use BorderLayout!"));
            return;
        }

        
        pane.add(this.toolBar.getToolbar(), BorderLayout.PAGE_START);

        pane.add(this.fileTab, BorderLayout.CENTER);
    }
    public void addNewTab(MainContentModel UCContentModel, MainContentModel UMLContentModel, MainContentModel OOPNContentModel)
    {
        addNewTab(UCContentModel, UMLContentModel, OOPNContentModel, "new file");
    }
    
    public void removeTab(Component selectedTab)
    {
        this.fileTab.remove(selectedTab);
    }
    
    public Component getSelectedTab()
    {
        return this.fileTab.getSelectedComponent();
    }
    
    public void addNewTab(MainContentModel UCContentModel, MainContentModel UMLContentModel, MainContentModel OOPNContentModel, String name)
    {
        JTabbedPane typeTab = new JTabbedPane();
        
        typeTab.addTab("UC", getWindowLayout(UCContentModel));
        typeTab.addTab("UML", getWindowLayout(UMLContentModel));
        typeTab.addTab("OOPN", getWindowLayout(OOPNContentModel));
        
        this.fileTab.addTab(name, typeTab);
    }
    
    private JSplitPane getWindowLayout(MainContentModel mycontent)
    {
        WindowLayoutModel myWindow = new WindowLayoutModel();
        myWindow.initSplitPanes();
        myWindow.setVerticals();
        myWindow.setContentsSplitPanes(mycontent);
        myWindow.setDividerLocation();
        return myWindow.getLeftSplitPane();
    }
}
