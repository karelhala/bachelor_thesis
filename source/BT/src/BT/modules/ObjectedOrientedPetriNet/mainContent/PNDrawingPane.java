/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ObjectedOrientedPetriNet.mainContent;

import BT.managers.PlaceManager;
import BT.models.CoordinateModel;
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
public class PNDrawingPane {
    private PlaceManager PNplaces;
    private PNDrawingPane.drawing drawPane;
    private PNJoinEdgeController newLine;

    public PNDrawingPane()
    {
       this(null);
    }

     
     /**
     * 
     * @param PNplaces
     */
    public PNDrawingPane(PlaceManager PNplaces)
    {
        this.drawPane = new PNDrawingPane.drawing();
        this.PNplaces = PNplaces;
        this.newLine = null;
    }

    void setNewLine(PNJoinEdgeController newJoinEdge) {
        newLine = newJoinEdge;
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
                newLine.drawJoinEdge(g);   
            }
            
            for (LineModel joinEdge: PNplaces.getJoinEdges()) {
                ((PNJoinEdgeController) joinEdge).drawJoinEdge(g);
            }

            for (CoordinateModel place: PNplaces.getObjects()) {
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
    
    /**
     * 
     * @param places 
     */
    public void setPlaces(PlaceManager places)
    {
        PNplaces = places;
    }
    
    public PlaceManager getPlaces()
    {
        return PNplaces;
    }
}
