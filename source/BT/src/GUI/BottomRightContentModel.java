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
    final private JButton bottomButton;
    final private JButton topButton;
    private JPanel additionalContent;
    
    public BottomRightContentModel()
    {
        this.contentPane = new JPanel(new BorderLayout());
        this.bottomButton = new JButton();
        this.topButton = new JButton();
        insertBasicbuttons();
    }

    /**
     * Method for inserting reactivate all and delete all buttons to content pane.
     */
    private void insertBasicbuttons()
    {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        this.topButton.setPreferredSize(new Dimension(200, 50));
	buttonPanel.add(this.topButton);
        this.bottomButton.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(this.bottomButton);
	this.contentPane.add(buttonPanel, BorderLayout.LINE_END);
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

    public JButton getBottomButton() {
        return bottomButton;
    }

    public JButton getTopButton() {
        return topButton;
    }

    public void setAdditionalContent(JPanel additionalContent) {
        this.additionalContent = additionalContent;
    }

    public JPanel getAdditionalContent() {
        return additionalContent;
    }
    
    public void setButtonNames(String topButtonText, String bottomButtonText)
    {
        this.topButton.setText(topButtonText);
        this.bottomButton.setText(bottomButtonText);
    }
}
