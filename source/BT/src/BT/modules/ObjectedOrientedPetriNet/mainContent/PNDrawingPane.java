/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ObjectedOrientedPetriNet.mainContent;

import BT.managers.PlaceManager;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author Karel
 */
public class PNDrawingPane {
    private PlaceManager CDplaces;
    private PNDrawingPane.drawing drawPane;
//    private CDJoinEdgeController newLine;

    public PNDrawingPane()
    {
       this(null);
    }

     
     /**
     * 
     * @param CDplaces
     */
    public PNDrawingPane(PlaceManager PNplaces)
    {
        this.drawPane = new PNDrawingPane.drawing();
        this.CDplaces = CDplaces;
//        this.newLine = null;
    }

//    void setNewLine(CDJoinEdgeController newJoinEdge) {
////        newLine = newJoinEdge;
//    }
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
//            if (newLine != null)
//            {
//                g.setColor(Color.GREEN);
//                newLine.drawJoinEdge(g);   
//            }
            
//            for (LineModel joinEdge: CDplaces.getJoinEdges()) {
//                ((CDJoinEdgeController) joinEdge).drawJoinEdge(g);
//            }
//            
//            for (CoordinateModel actor: CDplaces.getObjects()) {
//                ((CDClass) actor).drawClass(g);
//            }
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
        CDplaces = places;
    }
    
    public PlaceManager getPlaces()
    {
        return CDplaces;
    }
}
