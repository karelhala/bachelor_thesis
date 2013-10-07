/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.mainInterface;

import GUI.MainContentModel;
import GUI.MyToolBar;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import GUI.WindowLayoutModel;

/**
 *
 * @author Karel
 */
public class WindowLayoutControler {
    private MainContentModel mycontent;
    private JTabbedPane fileTab;
    
    public WindowLayoutControler()
    {
        fileTab = new JTabbedPane();
    }
    
    public void setContent (MainContentModel content)
    {
        this.mycontent = content;
    }
    
    public void addComponentsToPane(Container pane) {
        if (!(pane.getLayout() instanceof BorderLayout)) {
            pane.add(new JLabel("Container doesn't use BorderLayout!"));
            return;
        }

        MyToolBar toolbar = new MyToolBar();
        pane.add(toolbar.getToolbar(), BorderLayout.PAGE_START);
        
        addNewTab();

        pane.add(this.fileTab, BorderLayout.CENTER);
    }
    
    public void addNewTab()
    {
        JTabbedPane typeTab = new JTabbedPane();
        
        typeTab.addTab("UC", getWindowLayout());
        typeTab.addTab("UML", getWindowLayout());
        typeTab.addTab("OOPN", getWindowLayout());
        
        this.fileTab.addTab("new file", typeTab);
    }
    
    private JSplitPane getWindowLayout()
    {
        WindowLayoutModel myWindow = new WindowLayoutModel();
        myWindow.initSplitPanes();
        myWindow.setVerticals();
        myWindow.setContentsSplitPanes(this.mycontent);
        myWindow.setDividerLocation();
        return myWindow.getLeftSplitPane();
//        JSplitPane leftSplitPane = new JSplitPane();
//        JSplitPane rightSplitPane = new JSplitPane();
//        JSplitPane leftContentSplitPane = new JSplitPane();
//        JSplitPane rightContentSplitPane = new JSplitPane();
//        
//        rightSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
//        leftContentSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
//        
//        leftContentSplitPane.setLeftComponent(this.mycontent.getLeftTopPane());
//        leftContentSplitPane.setRightComponent(this.mycontent.getLeftBottomPane());
//        
//        rightContentSplitPane.setLeftComponent(this.mycontent.getBottomLeftPane());
//        rightContentSplitPane.setRightComponent(this.mycontent.getBottomRightPane());
//        
//        rightSplitPane.setLeftComponent(this.mycontent.getCenterPane());
//        rightSplitPane.setRightComponent(rightContentSplitPane);
//        
//        leftSplitPane.setLeftComponent(leftContentSplitPane);
//        leftSplitPane.setRightComponent(rightSplitPane);
//        
//        
//        leftSplitPane.setDividerLocation(200);
//        rightSplitPane.setDividerLocation(450);
//        leftContentSplitPane.setDividerLocation(300);
//        rightContentSplitPane.setDividerLocation(400);
    }
}
