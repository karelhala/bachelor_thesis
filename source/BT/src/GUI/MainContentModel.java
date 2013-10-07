/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JPanel;

/**
 * Class for handeling with main content in window
 * @author Karel
 */
public class MainContentModel {
    /**
     * Left Top part of window
     */
    private JPanel leftTopPane;
    
    /**
     * Left bottom part of window
     */
    private JPanel leftBottomPane;
    
    /**
     * Bottom left part of window
     */
    private JPanel bottomLeftPane;
    
    /**
     * Bottom right part of window
     */
    private JPanel bottomRightPane;
    
    /**
     * Main content
     */
    private JPanel centerPane;
    
    /**
     * constructor
     * it creates new panes for each part of window
     */
    public MainContentModel()
    {
        this.leftTopPane = new JPanel();
        this.leftBottomPane = new JPanel();
        this.bottomLeftPane = new JPanel();
        this.bottomRightPane = new JPanel();
        this.centerPane = new JPanel();
    }
    
    public void setLeftTopPane (JPanel panel)
    {
        this.leftTopPane = panel;
    }
    
    public void setLeftBottomPane (JPanel panel)
    {
        this.leftBottomPane = panel;
    }
    
    public void setBottomLeftPane (JPanel panel)
    {
        this.bottomLeftPane = panel;
    }
    
    public void setBottomRightPane (JPanel panel)
    {
        this.bottomRightPane = panel;
    }
    
    public void setCenterPane (JPanel panel)
    {
        this.centerPane = panel;
    }
    
    public JPanel getLeftTopPane()
    {
        return this.leftTopPane;
    }
    
    public JPanel getLeftBottomPane()
    {
        return this.leftBottomPane;
    }
    
    public JPanel getBottomLeftPane()
    {
        return this.bottomLeftPane;
    }
    
    public JPanel getBottomRightPane()
    {
        return this.bottomRightPane;
    }
    
    public JPanel getCenterPane()
    {
        return this.centerPane;
    }
    
}
