/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JSplitPane;

/**
 * Class that sets main window
 * @author Karel Hala
 */
public class WindowLayoutModel {

    /**
     * Split pane that holds buttons on left and main content.
     */
    private JSplitPane leftSplitPane;
    
    /**
     * Split pane that holds main content and bottom panes.
     */
    private JSplitPane rightSplitPane;
    
    /**
     * Split pane that holds left top and left bottom buttons.
     */
    private JSplitPane leftContentSplitPane;
    
    /**
     * Split pane that holds bottom panes.
     */
    private JSplitPane rightContentSplitPane;

    /**
     * Constructor that initialize split panes.
     */
    public void initSplitPanes() {
        this.leftSplitPane = new JSplitPane();
        this.rightSplitPane = new JSplitPane();
        this.leftContentSplitPane = new JSplitPane();
        this.rightContentSplitPane = new JSplitPane();
    }

    /**
     * Set button split pane and main split pane to have splits vertical.
     */
    public void setVerticals() {
        this.rightSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        this.leftContentSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
    }

    /**
     * Method for setting panes in desired split panes.
     * Left split pane = left buttons | main content and bottom panes
     * Right split pane = main content | bottom panes
     * Left content split pane = left top pane (buttons) | right bottom pane (buttons)
     * Right content split pane = left bottom pane | right bottom pane
     * @param mycontent model that holds each and every pane.
     */
    public void setContentsSplitPanes(MainContentModel mycontent) {
        this.leftContentSplitPane.setLeftComponent(mycontent.getLeftTopPane());
        this.leftContentSplitPane.setRightComponent(mycontent.getLeftBottomPane());

        this.rightContentSplitPane.setLeftComponent(mycontent.getBottomLeftPane());
        this.rightContentSplitPane.setRightComponent(mycontent.getBottomRightPane());

        this.rightSplitPane.setLeftComponent(mycontent.getCenterPane());
        this.rightSplitPane.setRightComponent(this.rightContentSplitPane);

        this.leftSplitPane.setLeftComponent(this.leftContentSplitPane);
        this.leftSplitPane.setRightComponent(this.rightSplitPane);
    }

    /**
     * Method for setting basic size of divider locations.
     */
    public void setDividerLocation() {
        this.leftSplitPane.setDividerLocation(200);
        this.rightSplitPane.setDividerLocation(450);
        this.leftContentSplitPane.setDividerLocation(300);
        this.rightContentSplitPane.setDividerLocation(400);
    }

    /**
     * Method for fetching left split pane that holds every window pane.
     * @return JSplitPane
     */
    public JSplitPane getLeftSplitPane() {
        return this.leftSplitPane;
    }

}
