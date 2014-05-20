/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 * Class for ToolBar, which holds each button to do different action.
 * 
 * @author Karel Hala
 */
public class MyToolBar {

    /**
     * Toolbar that will hold each button.
     */
    private javax.swing.JToolBar jToolBar;

    /**
     * Basic constructor, it initialize toolbar.
     */
    public MyToolBar() {
        initializeToolBar();
    }

    /**
     * Returns toolbar component.
     * @return main toolBar as JToolBar.
     */
    public JToolBar getToolbar() {
        return this.jToolBar;
    }

    /**
     * Set tootlbar component.
     * @param jToolBar main toolbar to be set to JToolBar.
     */
    public void setToolbar(JToolBar jToolBar) {
        this.jToolBar = jToolBar;
    }

    /**
     * Pane of toolbar to be set to JPanel.
     * @param mypanel which holds each button in correct way.
     */
    public void setPaneToolbar(JPanel mypanel) {
        this.jToolBar.add(mypanel);
    }

    /**
     * Create toolbar and position it correctly.
     */
    private void initializeToolBar() {
        this.jToolBar = new JToolBar();
        this.jToolBar.setFloatable(false);
        this.jToolBar.setRollover(true);
    }
}
