/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.mainContent;

import BT.managers.PlaceManager;
import BT.models.CoordinateModel;
import BT.models.DrawingPaneModel;
import BT.models.LineModel;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ClassDiagram.places.joinEdge.CDJoinEdgeController;
import BT.modules.UC.mainContent.UCDrawingPane;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author Karel Hala
 */
public class CDDrawingPane extends DrawingPaneModel{
    private drawing drawPane;
    /**
     * 
     * @param CDplaces
     */
    public CDDrawingPane(PlaceManager CDplaces)
    {
        super(CDplaces);
        this.drawPane = new drawing();
    }

    
    /**
     * 
     */
    public class drawing extends JPanel{
        
        public drawing()
        {
            super();
        }
        /**
         * 
         * @param g1 
         */
        @Override
        protected void paintComponent(Graphics g1) {
            super.paintComponent(g1);
            Graphics2D g = (Graphics2D) g1.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (newLine != null)
            {
                g.setColor(Color.GREEN);
                ((CDJoinEdgeController)newLine).drawJoinEdge(g);   
            }
            
            for (LineModel joinEdge: places.getJoinEdges()) {
                ((CDJoinEdgeController) joinEdge).drawJoinEdge(g);
            }
            
            for (CoordinateModel actor: places.getObjects()) {
                ((CDClass) actor).drawClass(g);
            }
        }
    }
    
    /**
     * 
     * @return 
     */
    public CDDrawingPane.drawing getDrawing()
    {
        return drawPane;
    }
}
