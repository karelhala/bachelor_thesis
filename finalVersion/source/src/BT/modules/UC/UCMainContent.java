/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC;

import BT.managers.PlaceManager;
import BT.models.ContentPaneModel;
import BT.models.CoordinateModel;
import BT.modules.UC.mainContent.UCDrawingPane;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCUseCase;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Class with every part of main content. It creates drawing panel and set area and background color.
 *
 * @author Karel Hala
 */
public final class UCMainContent extends ContentPaneModel {

    /**
     * Prefered area of drawing panel.
     */
    private Dimension area;

    /**
     * Basic constructor. It calls other constructor with no places.
     */
    public UCMainContent() {
        this(null);
    }

    /**
     * Construcotr that sets places which are drawn as argument's places. Creates mainContentPanel, drawingPane and set
     * area to 0 0.
     *
     * @param places these places have all use case object inside.
     */
    public UCMainContent(PlaceManager places) {
        super();
        this.mainContentPane = new JPanel(new BorderLayout());
        this.drawingPane = new UCDrawingPane(places);
        this.area = new Dimension(0, 0);
        createMainPane();
    }

    /**
     * For creating drawing pane and setting it. It sets drawing pane to white background and reapint it and isert it in
     * JscrollPane.
     */
    private void createMainPane() {
        UCDrawingPane ucDrawing = (UCDrawingPane) this.drawingPane;
        ucDrawing.getDrawing().setPreferredSize(this.area);
        ucDrawing.getDrawing().setBackground(Color.WHITE);
        ucDrawing.getDrawing().repaint();
        JScrollPane myScrollPane = new JScrollPane();
        myScrollPane.setViewportView(ucDrawing.getDrawing());
        this.mainContentPane.add(myScrollPane, BorderLayout.CENTER);
    }
}
