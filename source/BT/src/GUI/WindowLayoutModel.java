/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JSplitPane;

/**
 *
 * @author Karel
 */
public class WindowLayoutModel {
    private JSplitPane leftSplitPane;
    private JSplitPane rightSplitPane;
    private JSplitPane leftContentSplitPane;
    private JSplitPane rightContentSplitPane;
        
    public void initSplitPanes()
    {
        this.leftSplitPane = new JSplitPane();
        this.rightSplitPane = new JSplitPane();
        this.leftContentSplitPane = new JSplitPane();
        this.rightContentSplitPane = new JSplitPane();
    }
    
    public void setVerticals ()
    {
        this.rightSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        this.leftContentSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
    }
    
    public void setContentsSplitPanes(MainContentModel mycontent)
    {
        this.leftContentSplitPane.setLeftComponent(mycontent.getLeftTopPane());
        this.leftContentSplitPane.setRightComponent(mycontent.getLeftBottomPane());
        
        this.rightContentSplitPane.setLeftComponent(mycontent.getBottomLeftPane());
        this.rightContentSplitPane.setRightComponent(mycontent.getBottomRightPane());
        
        this.rightSplitPane.setLeftComponent(mycontent.getCenterPane());
        this.rightSplitPane.setRightComponent(this.rightContentSplitPane);
        
        this.leftSplitPane.setLeftComponent(this.leftContentSplitPane);
        this.leftSplitPane.setRightComponent(this.rightSplitPane);
    }
    
    public void setDividerLocation ()
    {
        this.leftSplitPane.setDividerLocation(200);
        this.rightSplitPane.setDividerLocation(450);
        this.leftContentSplitPane.setDividerLocation(300);
        this.rightContentSplitPane.setDividerLocation(400);
    }
    
    public JSplitPane getLeftSplitPane()
    {
        return this.leftSplitPane;
    }
}
