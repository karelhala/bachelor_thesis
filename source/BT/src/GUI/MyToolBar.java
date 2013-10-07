/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JButton;
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

    private void initializeToolBar() {
        this.jToolBar = new JToolBar();
        this.jToolBar.setFloatable(false);
        this.jToolBar.setRollover(true);
        
        JButton jButton1 = new javax.swing.JButton();
        jButton1.setText("New file");
        jButton1.setFocusable(false);
        this.jToolBar.add(jButton1);
    }
}
