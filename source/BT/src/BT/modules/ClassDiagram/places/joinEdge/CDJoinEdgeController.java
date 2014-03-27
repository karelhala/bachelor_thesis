/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.places.joinEdge;

import BT.BT.CDLineType;
import BT.managers.PointsCalculator;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Karel Hala
 */
public class CDJoinEdgeController extends LineModel {

    private CDLineType joinEdgeType;
    
    public CDJoinEdgeController(CoordinateModel firstObject, CoordinateModel secondObject)
    {
        super();
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }

    public void setJoinEdgeType(CDLineType joinEdgeType) {
        this.joinEdgeType = joinEdgeType;
    }

    public CDLineType getJoinEdgeType() {
        return joinEdgeType;
    }

    /**
     *
     */
    public CDJoinEdgeController() {
        super();
        this.joinEdgeType = CDLineType.ASSOCIATION;
    }

    /**
     *
     * @param g
     */
    public void drawJoinEdge(Graphics2D g) {
        PointsCalculator pointsCaluclator = new PointsCalculator(this.firstObject, this.secondObject, getStartPoint(), getEndPoint(), this.breakPoints);

        CDJoinEdgeDrawer lineDrawer;
        lineDrawer = new CDJoinEdgeDrawer(this, pointsCaluclator.getStartPoint(), pointsCaluclator.getEndPoint());
        Point startPoint = pointsCaluclator.getStartPoint();
        Point endPoint = pointsCaluclator.getEndPoint();
        if (startPoint != null && endPoint != null) {
            g.setStroke(new BasicStroke(2));
            lineDrawer.drawLine(g);
            setStartCoordinates(startPoint);

            setEndPoint(endPoint);
        }
    }
}
