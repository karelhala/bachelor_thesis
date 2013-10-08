/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author Karel
 */
public class MyToolBar {
    private javax.swing.JToolBar jToolBar;
    
    public MyToolBar ()
    {
        initializeToolBar();
    }
    
    public JToolBar getToolbar()
    {
        return this.jToolBar;
    }
    
    public void setToolbar(JToolBar jToolBar)
    {
        this.jToolBar = jToolBar;
    }
    
    public void setPaneToolbar(JPanel mypanel)
    {
        this.jToolBar.add(mypanel);
    }

    private void initializeToolBar() {
        this.jToolBar = new JToolBar();
        this.jToolBar.setFloatable(false);
        this.jToolBar.setRollover(true);
    }
}
