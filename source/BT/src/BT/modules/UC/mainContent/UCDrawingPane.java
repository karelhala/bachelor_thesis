/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.mainContent;

import BT.models.CoordinateModel;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCJoinEdge;
import BT.managers.UC.UCPlaceManager;
import BT.modules.UC.places.UCUseCase;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author Karel Hala
 */
public class UCDrawingPane{
    private UCPlaceManager UCPlaces;
    private drawing drawPane;
    private UCJoinEdge newLine;

    /**
     * 
     * @param UCPlaces 
     */
    public UCDrawingPane(UCPlaceManager UCPlaces)
    {
        drawPane = new drawing();
        this.UCPlaces = UCPlaces;
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
            
            for (UCJoinEdge joinEdge: UCPlaces.getJoinEdges()) {
                joinEdge.drawJoinEdge(g);
            }
            
            for (UCActor actor: UCPlaces.getActors()) {
                actor.drawActor(g);
            }
            
            for (UCUseCase useCase: UCPlaces.getUseCases()) {
                useCase.drawUseCase(g);
                
//                drawX(g, useCase.getX(), useCase.getY());
            }
        }
    }
    
    /**
     * 
     * @return 
     */
    public drawing getDrawing()
    {
        return this.drawPane;
    }
    
    /**
     * 
     */
    public void setButtonsListeners()
    {
        this.drawPane.getActionMap().put("removeObject", new AbstractAction() {
            @Override
                public void actionPerformed(ActionEvent e) {
                UCPlaces.removeAllSelectedItems();
                newLine = null;
                drawPane.repaint();
                }
            }
        );
         InputMap inputMap = this.drawPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
         inputMap.put(KeyStroke.getKeyStroke("DELETE"), "removeObject");
         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK), "closeTab");
    }
    
    /**
     * 
     * @param places 
     */
    public void setPlaces(UCPlaceManager places)
    {
        this.UCPlaces = places;
    }

    /**
     * 
     * @param newLine 
     */
    public void setNewLine(UCJoinEdge newLine)
    {
        this.newLine = newLine;
    }
}
