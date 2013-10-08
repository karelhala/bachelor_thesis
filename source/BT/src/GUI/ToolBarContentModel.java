/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Karel
 */
public class ToolBarContentModel {
    private JPanel toolBarPane;
    public ToolBarContentModel(){
        this.toolBarPane = new JPanel();
    }
    
    public JPanel getToolBarPane()
    {
        return this.toolBarPane;
    }
    
    public void setToolBarPane(JPanel toolBarPane)
    {
        this.toolBarPane = toolBarPane;
    }
    
    public JButton addNewButton (String name)
    {
        return addNewButton(name, Color.PINK); 
    }
    
    public JButton addNewButton (String name, Color buttonColor)
    {
        JButton jButton1 = new javax.swing.JButton();
        jButton1.setText(name);
        jButton1.setFocusable(false);
        jButton1.setBackground(buttonColor);
        this.toolBarPane.add(jButton1);
        return jButton1;
    }
}
