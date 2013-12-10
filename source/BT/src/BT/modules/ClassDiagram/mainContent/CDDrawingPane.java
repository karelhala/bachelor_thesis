/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.mainContent;

import BT.managers.CD.CDPlaceManager;
import BT.managers.UC.UCPlaceManager;
import BT.modules.UC.mainContent.UCDrawingPane;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import BT.modules.UC.places.UCUseCase;
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
    private CDPlaceManager CDplaces;
    private CDDrawingPane.drawing drawPane;
    private UCJoinEdgeController newLine;

     public CDDrawingPane()
    {
        this(null);
    }
    
    /**
     * 
     * @param UCPlaces 
     */
    public CDDrawingPane(CDPlaceManager CDplaces)
    {
        this.drawPane = new CDDrawingPane.drawing();
        this.CDplaces = CDplaces;
        this.newLine = null;
    }
    
    /**
     * 
     */
    public class drawing extends JPanel{
        //TODO: remove this
        void drawX(Graphics2D g1, int x1, int y1) {
                g1.drawLine(x1+5, y1+5, x1-5, y1-5);
                g1.drawLine(x1+5, y1-5, x1-5, y1+5);
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
                newLine.drawJoinEdge(g);
                
            }
            
//            for (UCJoinEdgeController joinEdge: UCPlaces.getJoinEdges()) {
//                joinEdge.drawJoinEdge(g);
//            }
//            
//            for (UCActor actor: UCPlaces.getActors()) {
//                actor.drawActor(g);
//            }
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
        return this.drawPane;
    }
    
    /**
     * 
     * @param places 
     */
    public void setPlaces(CDPlaceManager places)
    {
        this.CDplaces = places;
    }

    /**
     * 
     * @param newLine 
     */
    public void setNewLine(UCJoinEdgeController newLine)
    {
        this.newLine = newLine;
    }
}
