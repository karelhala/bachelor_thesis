/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.managers;

import BT.models.LineModel;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;/**
 *
 * @author Karel Hala
 */
public class JoinEdgeDrawer {
    /**
     * 
     */
    protected Point startPoint;
    
    /**
     * 
     */
    protected Point endPoint;
    
    protected LineModel joinEdgeController;

    public JoinEdgeDrawer(LineModel joinEdgeController, Point startPoint, Point endPoint) 
    {
        this.joinEdgeController = joinEdgeController;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }
    
    /**
     * Method for drawing arrow at end of the line given by 2 points.
     * @param g2D
     * @param A
     * @param B 111111111111111111111111
     */
    protected void drawArrow(Graphics2D g2D, Point A, Point B)
    {
        Graphics2D g = (Graphics2D) g2D.create();
        g.setStroke(new BasicStroke(2));
        double dx = B.x - A.x;
        double dy = B.y - A.y;
        double angle = Math.atan2(dy, dx);
        AffineTransform at = AffineTransform.getTranslateInstance(A.x, A.y);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        g.drawLine(0, 0, 0+5, 0+5);
        g.drawLine(0, 0, 0+5, 0-5);
    }
    
    /**
     * 
     * @param g2D
     * @param A
     * @param B
     * @param name 
     */
    protected void drawString(Graphics2D g2D, Point A, Point B, String name)
    {
        Graphics2D g = (Graphics2D) g2D.create();
        g.setStroke(new BasicStroke(2));
        double dx = B.x - A.x;
        double dy = B.y - A.y;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        if ((angle<=Math.PI && angle>=Math.PI/2) || (angle>=-Math.PI && angle<=-Math.PI/2))
        {
            len = -len*2;
            angle = angle-Math.PI;
        }
        AffineTransform at = AffineTransform.getTranslateInstance(A.x, A.y);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        g.drawString(name, len/3, -5);
    }
    
}
