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
 * Class for holding bottom right content of window. It has 2 bottons and one additional content. These 2 buttons are
 * topButton and topButton, additional content is stored via Jpanel additionalcontent.
 *
 * @author Karel Hala
 */
public class BottomRightContentModel {

    /**
     * Pane shich holds every content of this class.
     */
    final private JPanel contentPane;

    /**
     * Bottom button, used in useCase and class diagram for reactivating.
     */
    final private JButton bottomButton;

    /**
     * Top buttton, used in useCase and class diagram for deleting inactive.
     */
    final private JButton topButton;

    /**
     * Additional content panel.
     */
    private JPanel additionalContent;

    /**
     * Basic constructor. It creates new contentPane, bottomButton and topButton. It inserts each content in content
     * pane.
     */
    public BottomRightContentModel() {
        this.contentPane = new JPanel(new BorderLayout());
        this.bottomButton = new JButton();
        this.topButton = new JButton();
        insertBasicbuttons();
    }

    /**
     * Method for inserting reactivate all and delete all buttons to content pane.
     */
    private void insertBasicbuttons() {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        this.topButton.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(this.topButton);
        this.bottomButton.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(this.bottomButton);
        this.contentPane.add(buttonPanel, BorderLayout.LINE_END);
    }

    /**
     * Add additional content to content pane.
     */
    public void addAdditionalcontent() {
        this.contentPane.add(this.additionalContent, BorderLayout.CENTER);
    }

    /**
     * Set addition content Jpanel and add it to content pane.
     *
     * @param additionalcontent
     */
    public void addAdditionalcontent(JPanel additionalcontent) {
        this.additionalContent = additionalcontent;
        addAdditionalcontent();
    }

    /**
     * Retuns panel which holds each content of this class.
     *
     * @return contentPane as JPanel.
     */
    public JPanel getContentPane() {
        return contentPane;
    }

    /**
     * Returns button which is located at bottom.
     *
     * @return bottomButton as JButton.
     */
    public JButton getBottomButton() {
        return bottomButton;
    }

    /**
     * Returns button which is located at top.
     *
     * @return topButton as JButton.
     */
    public JButton getTopButton() {
        return topButton;
    }

    /**
     * Set adition content pane. You might want to call addAditionalContent as well.
     *
     * @param additionalContent additionalContent as JPanel.
     */
    public void setAdditionalContent(JPanel additionalContent) {
        this.additionalContent = additionalContent;
    }

    /**
     * Returns additional content, which holds other settings.
     *
     * @return additionalContent as JPanel.
     */
    public JPanel getAdditionalContent() {
        return additionalContent;
    }

    /**
     * Set topButton and bottomButton texts.
     *
     * @param topButtonText string that will be visible in top button.
     * @param bottomButtonText string that will be visible in bottom button.
     */
    public void setButtonNames(String topButtonText, String bottomButtonText) {
        this.topButton.setText(topButtonText);
        this.bottomButton.setText(bottomButtonText);
    }

    /**
     * Set visible of all items to isShown argument.
     *
     * @param isShown Boolean true or false if this panel should be shown.
     * @return this object.
     */
    public BottomRightContentModel showAllItems(Boolean isShown) {
        this.topButton.setVisible(isShown);
        this.bottomButton.setVisible(isShown);
        this.additionalContent.setVisible(isShown);
        return this;
    }

    /**
     * Show or hide additional content.
     *
     * @param isShown if additional content should be hidden or shown.
     * @return this obejct.
     */
    public BottomRightContentModel showAdditionalContent(Boolean isShown) {
        this.additionalContent.setVisible(isShown);
        return this;
    }

    /**
     * Hide bottom and top button of bottom right content pane.
     *
     * @return this object.
     */
    public BottomRightContentModel hideButtons() {
        this.bottomButton.setVisible(false);
        this.topButton.setVisible(false);
        return this;
    }

    /**
     * Show top and bottom button of bottom right content pane.
     *
     * @return this object.
     */
    public BottomRightContentModel showButtons() {
        this.bottomButton.setVisible(true);
        this.topButton.setVisible(true);
        return this;
    }

    /**
     * Replace additional content pane with additionalContentPane
     *
     * @param additionalContentPane
     */
    public void replaceAdditionalContent(JPanel additionalContentPane) {
        this.contentPane.remove(additionalContent);
        this.contentPane.add(additionalContentPane);
        this.additionalContent = additionalContentPane;
        this.contentPane.validate();
        this.contentPane.repaint();
    }
}
