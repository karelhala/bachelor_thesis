/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author Karel Hala
 */
public class MyToolBar {

    /**
     * 
     */
    private javax.swing.JToolBar jToolBar;

    /**
     * 
     */
    public MyToolBar() {
        initializeToolBar();
    }

    /**
     * 
     * @return 
     */
    public JToolBar getToolbar() {
        return this.jToolBar;
    }

    /**
     * 
     * @param jToolBar 
     */
    public void setToolbar(JToolBar jToolBar) {
        this.jToolBar = jToolBar;
    }

    /**
     * 
     * @param mypanel 
     */
    public void setPaneToolbar(JPanel mypanel) {
        this.jToolBar.add(mypanel);
    }

    /**
     * 
     */
    private void initializeToolBar() {
        this.jToolBar = new JToolBar();
        this.jToolBar.setFloatable(false);
        this.jToolBar.setRollover(true);
    }
}
