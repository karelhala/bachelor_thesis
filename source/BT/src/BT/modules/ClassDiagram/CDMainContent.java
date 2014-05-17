/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram;

import BT.managers.PlaceManager;
import BT.models.ContentPaneModel;
import BT.models.CoordinateModel;
import BT.modules.ClassDiagram.mainContent.CDDrawingPane;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCUseCase;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Class that creates new drawing pane for class diagram with given places.
 *
 * @author Karel Hala
 */
public final class CDMainContent extends ContentPaneModel {

    /**
     * Basic area of drawing pane.
     */
    private final Dimension area;

    /**
     * Basic constructor. It will create drawing pane, insert places in it and createMainPane.
     *
     * @param places
     */
    public CDMainContent(PlaceManager places) {
        super();
        this.mainContentPane = new JPanel(new BorderLayout());
        this.drawingPane = new CDDrawingPane(places);
        this.area = new Dimension(0, 0);
        createMainPane();
    }

    /**
     * This will set prefered size, set background color, and insert drawing pane in scrollPane.
     */
    private void createMainPane() {
        CDDrawingPane cdDrawingPane = (CDDrawingPane) this.drawingPane;
        cdDrawingPane.getDrawing().setPreferredSize(this.area);
        cdDrawingPane.getDrawing().setBackground(Color.WHITE);
        cdDrawingPane.getDrawing().repaint();
        JScrollPane myScrollPane = new JScrollPane(cdDrawingPane.getDrawing());
        this.mainContentPane.add(myScrollPane, BorderLayout.CENTER);
    }
}
