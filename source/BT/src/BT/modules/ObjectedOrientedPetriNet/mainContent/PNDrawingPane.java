/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ObjectedOrientedPetriNet.mainContent;

import BT.managers.PlaceManager;
import BT.models.CoordinateModel;
import BT.models.DrawingPaneModel;
import BT.models.LineModel;
import BT.modules.ObjectedOrientedPetriNet.places.PNPlace;
import BT.modules.ObjectedOrientedPetriNet.places.PNTransition;
import BT.modules.ObjectedOrientedPetriNet.places.joinEdge.PNJoinEdgeController;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author Karel
 */
public class PNDrawingPane extends DrawingPaneModel{
    private PNDrawingPane.drawing drawPane;
    
     /**
     * 
     * @param PNplaces
     */
    public PNDrawingPane(PlaceManager PNplaces)
    {
        super (PNplaces);
        this.drawPane = new PNDrawingPane.drawing();
    }
    
     /**
     * 
     */
    public class drawing extends JPanel{
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
                ((PNJoinEdgeController) newLine).drawJoinEdge(g);   
            }
            
            for (LineModel joinEdge: places.getJoinEdges()) {
                ((PNJoinEdgeController) joinEdge).drawJoinEdge(g);
            }

            for (CoordinateModel place: places.getObjects()) {
                if (place instanceof PNPlace)
                {
                    ((PNPlace) place).drawPlace(g);
                }
                else if (place instanceof PNTransition)
                {
                    ((PNTransition) place).drawTransition(g);
                }
            }
        }
    }
    
    /**
     * 
     * @return 
     */
    public PNDrawingPane.drawing getDrawing()
    {
        return drawPane;
    }
}
