/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.models;

import javax.swing.JPanel;

/**
 * Parent class, for ContentPane
 *
 * @author Karel Hala
 */
public class ContentPaneModel {

    /**
     * Model that holds drawing pane
     */
    protected DrawingPaneModel drawingPane;
    /**
     * Jpanel for storing every component of top left content
     */
    protected JPanel mainContentPane;

    /**
     * Method, that returns main pane has content of left top pane
     *
     * @return JPanel left top pane of application
     */
    public JPanel getMainContentPane() {
        return this.mainContentPane;
    }

    /**
     * Method for returning drawingPaneModel.
     *
     * @return drawingPane as DrawingPaneModel.
     */
    public DrawingPaneModel getDrawingPane() {
        return drawingPane;
    }

    /**
     * Method, that returns main pane has content of left top pane
     *
     * @param mainContentPane
     */
    public void setMainContentPane(JPanel mainContentPane) {
        this.mainContentPane = mainContentPane;
    }

    /**
     * Method for setting drawingPaneModel.
     *
     * @param drawingPane
     */
    public void setDrawingPane(DrawingPaneModel drawingPane) {
        this.drawingPane = drawingPane;
    }
}
