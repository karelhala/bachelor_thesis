/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Karel
 */
public class BottomRightContentModel {
    final private JPanel contentPane;
    final private JButton reactivateAllButton;
    final private JButton deleteAllNonValidButton;
    private JPanel additionalContent;
    
    public BottomRightContentModel()
    {
        this.contentPane = new JPanel(new BorderLayout());
        this.reactivateAllButton = new JButton("Reactivate all inactive");
        this.deleteAllNonValidButton = new JButton("Delete all inactive");
        insertBasicbuttons();
    }

    /**
     * Method for inserting reactivate all and delete all buttons to content pane.
     */
    private void insertBasicbuttons()
    {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        this.deleteAllNonValidButton.setPreferredSize(new Dimension(200, 50));
	buttonPanel.add(this.deleteAllNonValidButton);
        this.reactivateAllButton.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(this.reactivateAllButton);
	this.contentPane.add(buttonPanel, BorderLayout.LINE_START);
    }
    
    /**
     * 
     */
    public void addAdditionalcontent()
    {
        this.contentPane.add(this.additionalContent,BorderLayout.CENTER);
    }
    
    public JPanel getContentPane() {
        return contentPane;
    }

    public JButton getReactivateAllButton() {
        return reactivateAllButton;
    }

    public JButton getDeleteAllNonValidButton() {
        return deleteAllNonValidButton;
    }

    public void setAdditionalContent(JPanel additionalContent) {
        this.additionalContent = additionalContent;
    }

    public JPanel getAdditionalContent() {
        return additionalContent;
    }
}
