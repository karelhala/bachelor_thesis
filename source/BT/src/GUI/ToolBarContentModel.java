/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Class that creates toolBar content.
 *
 * @author Karel Hala
 */
public class ToolBarContentModel {

    /**
     * Main panel holding toolBar data.
     */
    private JPanel toolBarPane;

    /**
     * Constructor that creates new JPanel for toolNar.
     */
    public ToolBarContentModel() {
        this.toolBarPane = new JPanel();
    }

    /**
     * Get toolbar pane which stores each button.
     * 
     * @return JPanel with every button of toolbar.
     */
    public JPanel getToolBarPane() {
        return this.toolBarPane;
    }
    
    /**
     * Set toolbar pane which stores each button.
     * @param toolBarPane JPanel with every button of toolbar.
     */
    public void setToolBarPane(JPanel toolBarPane) {
        this.toolBarPane = toolBarPane;
    }

    /**
     * Method for creating new Button with basic color to toolBar.
     *
     * @param name
     * @param iconName
     * @return
     */
    public JButton addNewButton(String name, String iconName) {
        return addNewButton(name, iconName, Color.GRAY);
    }

    /**
     * Add button with name and nothign else.
     * 
     * @param name name of button
     * @return created JButton.
     */
    public JButton addNewButton(String name) {
        return addNewButton(name, null);
    }

    /**
     * Method for initializing and creating new button.
     *
     * @param name new name of button.
     * @param iconName name of icon, including extension.
     * @param buttonColor color of new button.
     * @return new button created and initialized.
     */
    public JButton addNewButton(String name, String iconName, Color buttonColor) {
        JButton jButton1 = new javax.swing.JButton();
        ClassLoader cl = this.getClass().getClassLoader();
        if (cl.getResource("resources/" + iconName) != null) {
            ImageIcon icon = new ImageIcon(cl.getResource("resources/" + iconName));
            jButton1.setIcon(icon);
            jButton1.setText(name);
        } else {
            jButton1.setText(name);
        }
        jButton1.setToolTipText(name);
        jButton1.setFocusable(false);
        jButton1.setBackground(buttonColor);
        this.toolBarPane.add(jButton1);
        return jButton1;
    }
}
