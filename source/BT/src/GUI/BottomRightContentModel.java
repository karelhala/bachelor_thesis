/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
    final private GridBagConstraints c;
    
    public BottomRightContentModel()
    {
        this.contentPane = new JPanel(new GridBagLayout());
        this.reactivateAllButton = new JButton("Reactivate all inactive");
        this.deleteAllNonValidButton = new JButton("Delete all inactive");
        this.c = new GridBagConstraints();
        insertBasicbuttons();
    }

    /**
     * Method for inserting reactivate all and delete all buttons to content pane.
     */
    private void insertBasicbuttons()
    {
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 0;
        this.deleteAllNonValidButton.setPreferredSize(new Dimension(200, 50));
	this.contentPane.add(this.deleteAllNonValidButton, c);
        
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 1;
        this.reactivateAllButton.setPreferredSize(new Dimension(200, 50));
	this.contentPane.add(this.reactivateAllButton, c);
    }
    
    /**
     * 
     */
    public void addAdditionalcontent()
    {
        c.fill = GridBagConstraints.BOTH;
	c.weightx = 0.5;
	c.gridx = 0;
	c.gridy = 0;
        c.gridheight = GridBagConstraints.REMAINDER;
        c.gridwidth = GridBagConstraints.REMAINDER;
        this.contentPane.add(this.additionalContent, c);
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
