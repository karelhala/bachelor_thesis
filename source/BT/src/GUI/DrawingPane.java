/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BT.managers.CoordinateManager;
import BT.managers.UC.UCActor;
import BT.managers.UC.UCJoinEdge;
import BT.managers.UC.UCPlaceManager;
import BT.managers.UC.UCUseCase;
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
public class DrawingPane{
    private UCPlaceManager UCPlaces;
    private drawing drawPane;
    private CoordinateManager selectedObject;
    private UCJoinEdge newLine;

    public DrawingPane(UCPlaceManager UCPlaces)
    {
        drawPane = new drawing();
        this.UCPlaces = UCPlaces;
    }
    
    public class drawing extends JPanel{
        void drawX(Graphics2D g1, int x1, int y1) {
                g1.drawLine(x1+5, y1+5, x1-5, y1-5);
                g1.drawLine(x1+5, y1-5, x1-5, y1+5);
            }
        
        
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
                if (joinEdge.getSelected())
                {
                    g.setColor(joinEdge.getSelectedColor());
                }
                else
                {
                    g.setColor(joinEdge.getColor());
                }
                joinEdge.drawJoinEdge(g);
            }
            
            for (UCActor actor: UCPlaces.getActors()) {
                Color objectColor;
                if (actor.equals(selectedObject))
                {
                    objectColor = actor.getSelectedColor();
                    actor.drawSelectedActor(g, objectColor);
                }
                else
                {
                    objectColor = actor.getColor();
                    actor.drawActor(g, objectColor);
                }
            }
            
            for (UCUseCase useCase: UCPlaces.getUseCases()) {
                Color objectColor;
                if (!useCase.equals(selectedObject))
                {
                    objectColor = useCase.getColor();
                    useCase.drawUseCase(g, objectColor);
                }
                else
                {
                    objectColor = useCase.getSelectedColor();
                    useCase.drawSelectedUseCase(g, objectColor);
                }
//                drawX(g, useCase.getX(), useCase.getY());
            }
        }
    }
    
    public drawing getDrawing()
    {
        return this.drawPane;
    }
    
    public void setButtonsListeners()
    {
        this.drawPane.getActionMap().put("removeObject", new AbstractAction() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    if(selectedObject!=null)
                    {
                        newLine = null;
                        UCPlaces.removePlace(selectedObject);
                        drawPane.repaint();
                    }
                }
            }
        );
         InputMap inputMap = this.drawPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
         inputMap.put(KeyStroke.getKeyStroke("DELETE"), "removeObject");
         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK), "closeTab");
    }
    
    public void setPlaces(UCPlaceManager places)
    {
        this.UCPlaces = places;
    }
    
    public void setSelectedObject(CoordinateManager selected)
    {
        this.selectedObject = selected;
    }
    
    public void setNewLine(UCJoinEdge newLine)
    {
        this.newLine = newLine;
    }
}
