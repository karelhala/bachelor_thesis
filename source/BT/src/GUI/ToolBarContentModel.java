/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Class that creates toolBar content
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

    public JPanel getToolBarPane() {
        return this.toolBarPane;
    }

    public void setToolBarPane(JPanel toolBarPane) {
        this.toolBarPane = toolBarPane;
    }

    /**
     * Method for creating new Button with basic color to toolBar.
     * @param name
     * @return
     */
    public JButton addNewButton(String name) {
        return addNewButton(name, Color.PINK);
    }

    /**
     * Method for initializing and creating new button
     * @param name new name of button
     * @param buttonColor color of new button.
     * @return new button created and initialized.
     */
    public JButton addNewButton(String name, Color buttonColor) {
        JButton jButton1 = new javax.swing.JButton();
        jButton1.setText(name);
        jButton1.setFocusable(false);
        jButton1.setBackground(buttonColor);
        this.toolBarPane.add(jButton1);
        return jButton1;
    }
}
