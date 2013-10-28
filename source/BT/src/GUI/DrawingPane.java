/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BT.managers.CoordinateManager;
import BT.managers.UC.UCPlaceManager;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author Karel
 */
public class DrawingPane{
    private UCPlaceManager UCPlaces;
    private drawing drawPane;

    public DrawingPane(UCPlaceManager UCPlaces)
    {
        drawPane = new drawing();
        this.UCPlaces = UCPlaces;
    }
    
    public class drawing extends JPanel{
        void drawX(Graphics g1, int x1, int y1) {
                Graphics2D g = (Graphics2D) g1.create();
                g.drawLine(x1+5, y1+5, x1-5, y1-5);
                g.drawLine(x1+5, y1-5, x1-5, y1+5);
            }
        
        
        @Override
        protected void paintComponent(Graphics g1) {
            super.paintComponent(g1);
            Graphics2D g = (Graphics2D) g1.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            for (CoordinateManager place: UCPlaces.getPlaces()) {
                drawX(g, place.getX(), place.getY());
            }
        }
    }
    
    public drawing getDrawing()
    {
        return this.drawPane;
    }
    
    public void setPlaces(UCPlaceManager places)
    {
        this.UCPlaces = places;
    }
}
