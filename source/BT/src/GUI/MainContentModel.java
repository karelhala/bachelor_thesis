/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JPanel;

/**
 * Class for handeling with main content in window.
 *
 * @author Karel Hala
 */
public class MainContentModel {

    /**
     * Left Top part of window.
     */
    private JPanel leftTopPane;

    /**
     * Left bottom part of window.
     */
    private JPanel leftBottomPane;

    /**
     * Bottom left part of window.
     */
    private JPanel bottomLeftPane;

    /**
     * Bottom right part of window.
     */
    private JPanel bottomRightPane;

    /**
     * Main content.
     */
    private JPanel centerPane;

    /**
     * Constructor it creates new panes for each part of window.
     */
    public MainContentModel() {
        this.leftTopPane = new JPanel();
        this.leftBottomPane = new JPanel();
        this.bottomLeftPane = new JPanel();
        this.bottomRightPane = new JPanel();
        this.centerPane = new JPanel();
    }
    /**
     * Method for setting left top pane.
     * @param panel leftTopPane as JPanel.
     */
    public void setLeftTopPane(JPanel panel) {
        this.leftTopPane = panel;
    }

    /**
     * Method for setting left bottom pane.
     * @param panel leftBottomPane will be set as JPanel.
     */
    public void setLeftBottomPane(JPanel panel) {
        this.leftBottomPane = panel;
    }

    /**
     * Method for setting bottom left pane.
     * @param panel  bottomLeftPane will be set as JPanel.
     */
    public void setBottomLeftPane(JPanel panel) {
        this.bottomLeftPane = panel;
    }

    /**
     * Method for setting bottom right pane.
     * @param panel bottomRightPane will be set as JPanel.
     */
    public void setBottomRightPane(JPanel panel) {
        this.bottomRightPane = panel;
    }

    /**
     * Method for setting center pane, also known as ain pane.
     * @param panel centerPane will be set as JPanel.
     */
    public void setCenterPane(JPanel panel) {
        this.centerPane = panel;
    }

    /**
     * Method for returning left top pane.
     * @return leftTopPane as JPanel.
     */
    public JPanel getLeftTopPane() {
        return this.leftTopPane;
    }

    /**
     * Method for returning left bottom pane.
     * @return leftBottomPane as JPanel.
     */
    public JPanel getLeftBottomPane() {
        return this.leftBottomPane;
    }

    /**
     * Method for returning bottom left pane.
     * @return bottomLeftPane as JPanel.
     */
    public JPanel getBottomLeftPane() {
        return this.bottomLeftPane;
    }

    /**
     * Method for returning bottom right pane.
     * @return bottomRightPane as JPanel.
     */
    public JPanel getBottomRightPane() {
        return this.bottomRightPane;
    }

    /**
     * Method for returning center pane, also known as main pane.
     * @return centerPane as JPanel.
     */
    public JPanel getCenterPane() {
        return this.centerPane;
    }

}
