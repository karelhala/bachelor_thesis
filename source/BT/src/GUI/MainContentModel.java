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
     * 
     * @param panel 
     */
    public void setLeftTopPane(JPanel panel) {
        this.leftTopPane = panel;
    }

    /**
     * 
     * @param panel 
     */
    public void setLeftBottomPane(JPanel panel) {
        this.leftBottomPane = panel;
    }

    /**
     * 
     * @param panel 
     */
    public void setBottomLeftPane(JPanel panel) {
        this.bottomLeftPane = panel;
    }

    /**
     * 
     * @param panel 
     */
    public void setBottomRightPane(JPanel panel) {
        this.bottomRightPane = panel;
    }

    /**
     * 
     * @param panel 
     */
    public void setCenterPane(JPanel panel) {
        this.centerPane = panel;
    }

    /**
     * 
     * @return 
     */
    public JPanel getLeftTopPane() {
        return this.leftTopPane;
    }

    /**
     * 
     * @return 
     */
    public JPanel getLeftBottomPane() {
        return this.leftBottomPane;
    }

    /**
     * 
     * @return 
     */
    public JPanel getBottomLeftPane() {
        return this.bottomLeftPane;
    }

    /**
     * 
     * @return 
     */
    public JPanel getBottomRightPane() {
        return this.bottomRightPane;
    }

    /**
     * 
     * @return 
     */
    public JPanel getCenterPane() {
        return this.centerPane;
    }

}
