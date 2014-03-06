/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ObjectedOrientedPetriNet.places.joinEdge;

import BT.managers.JoinEdgeDrawer;
import BT.models.LineModel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Karel
 */
public class PNJoinEdgeDrawer extends JoinEdgeDrawer{

    public PNJoinEdgeDrawer(LineModel joinEdgeController, Point startPoint, Point endPoint) {
        super(joinEdgeController, startPoint, endPoint);
    }
    
    /**
     * Method for drawing each type of line.
     * @param g 
     */
    public void drawLine(Graphics2D g)
    {
        if (this.joinEdgeController.getSelected())
        {
            g.setColor(this.joinEdgeController.getSelectedColor());
        }
        else
        {
            g.setColor(this.joinEdgeController.getColor());
        }
        
        float dash1[] = {10.0f};
        BasicStroke dashed =
        new BasicStroke(2.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f);
        if (this.joinEdgeController instanceof PNJoinEdgeController)
        {
            PNJoinEdgeController pnJoin = (PNJoinEdgeController) this.joinEdgeController;
            if (pnJoin.getJoinEdgeType() == BT.BT.OOPNLineType.JOIN)
            {
                g.drawLine(this.startPoint.x, this.startPoint.y, this.endPoint.x, this.endPoint.y);
                drawTriangle(g, this.endPoint, this.startPoint, Color.BLACK);
            }
                    
        }
    }
    
}
