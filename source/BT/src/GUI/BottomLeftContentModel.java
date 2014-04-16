/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Karel
 */
public class BottomLeftContentModel {
    final private JPanel contentPane;
    final private JScrollPane scrollPane;
    final private JPanel mainPane;
    
    public BottomLeftContentModel()
    {
        this.contentPane = new JPanel(new GridLayout(0,1));
        this.scrollPane = new JScrollPane();
        this.mainPane = new JPanel(new BorderLayout());
        initilizeCompponent();
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JPanel getMainPane() {
        return mainPane;
    }
    
    /**
     * Method that will add contentPane in scrollPane and this scrollPane into main pane.
     */
    private void initilizeCompponent()
    {
        this.scrollPane.add(contentPane);
        this.scrollPane.setViewportView(contentPane);
        this.mainPane.add(this.scrollPane, BorderLayout.CENTER);
    }
    
    /**
     * Method that will insert text with button to contentPane
     * @param name
     * @param deletebutton
     */
    public void addObjectsToPane(String name, JButton deletebutton)
    {
        JLabel newLabel = new JLabel(name);
        JPanel newPanel = new JPanel(new BorderLayout());
        newLabel.setToolTipText(name);
        deletebutton.setToolTipText("Delete " + name);
        newPanel.add(newLabel, BorderLayout.CENTER);
        newPanel.add(deletebutton, BorderLayout.LINE_END);
        this.contentPane.add(newPanel);
    }

    public BottomLeftContentModel addClassLabelToPane(JLabel classLabel)
    {
        this.mainPane.add(classLabel, BorderLayout.LINE_START);
        return this;
    }
    
    /**
     * 
     * @param attributeLabel
     * @return 
     */
    public BottomLeftContentModel addAttributesToPane(JLabel attributeLabel)
    {
        this.contentPane.add(attributeLabel);
        return this;
    }
    
    /**
     * Method for clearing content pane.
     * It will remove all components from content pane, repaint it and revalidate it.
     */
    public void destroyContent()
    {
        this.contentPane.removeAll();
        this.contentPane.repaint();
        this.contentPane.revalidate();
    }
}
