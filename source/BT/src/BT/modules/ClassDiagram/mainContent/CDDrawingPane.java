/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.mainContent;

import BT.managers.PlaceManager;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ClassDiagram.places.joinEdge.CDJoinEdgeController;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author Karel Hala
 */
public class CDDrawingPane {
    private PlaceManager CDplaces;
    private CDDrawingPane.drawing drawPane;
    private UCJoinEdgeController newLine;

     public CDDrawingPane()
    {
        this(null);
    }
    
    /**
     * 
     * @param CDplaces
     */
    public CDDrawingPane(PlaceManager CDplaces)
    {
        this.drawPane = new CDDrawingPane.drawing();
        this.CDplaces = CDplaces;
        this.newLine = null;
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
            
            for (LineModel joinEdge: CDplaces.getJoinEdges()) {
                
                ((CDJoinEdgeController) joinEdge).drawJoinEdge(g);
            }
            
            for (CoordinateModel actor: CDplaces.getObjects()) {
                ((CDClass) actor).drawClass(g);
            }
//            
//            for (UCUseCase useCase: UCPlaces.getUseCases()) {
//                useCase.drawUseCase(g);
//                
////                drawX(g, useCase.getX(), useCase.getY());
//            }
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
    
    /**
     * 
     * @param places 
     */
    public void setPlaces(PlaceManager places)
    {
        CDplaces = places;
    }

    /**
     * 
     * @param newLine 
     */
    public void setNewLine(UCJoinEdgeController newLine)
    {
        newLine = newLine;
    }
}
