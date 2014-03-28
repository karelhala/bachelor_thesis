/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.places.UCJoinEdge;

import BT.managers.PointsCalculator;
import BT.BT.UCLineType;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Objects;

/**
 *
 * @author Karel Hala
 */
public class UCJoinEdgeController extends LineModel {

    private UCLineType joinEdgeType;

    public UCJoinEdgeController(CoordinateModel firstObject, CoordinateModel secondObject)
    {
        super();
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }
    
    public void setJoinEdgeType(UCLineType joinEdgeType) {
        this.joinEdgeType = joinEdgeType;
    }

    /**
     *
     */
    public UCJoinEdgeController() {
        super();
        this.joinEdgeType = UCLineType.ASSOCIATION;
    }

    /**
     *
     * @return
     */
    public UCLineType getJoinEdgeType() {
        return this.joinEdgeType;
    }

    /**
     *
     * @param g
     */
    public void drawJoinEdge(Graphics2D g) {
        if (this.firstObject != null)
        {
            PointsCalculator pointsCaluclator = new PointsCalculator(this.firstObject, this.secondObject, getStartPoint(), getEndPoint(), this.breakPoints);

            Point startPoint = pointsCaluclator.getStartPoint();
            Point endPoint = pointsCaluclator.getEndPoint();
            UCJoinEdgeDrawer lineDrawer = new UCJoinEdgeDrawer(this, startPoint, endPoint);
            if (startPoint != null && endPoint != null) {
                g.setStroke(new BasicStroke(2));
                lineDrawer.drawLine(g);
                setStartCoordinates(startPoint);
                setEndPoint(endPoint);
            }
        }
    }

    /**
     *
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof UCJoinEdgeController) {
            UCJoinEdgeController object = (UCJoinEdgeController) other;
            if (this.hashCode() == object.hashCode()) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.firstObject);
        hash = 67 * hash + Objects.hashCode(this.secondObject);
        hash = 67 * hash + Objects.hashCode(this.joinEdgeType);
        return hash;
    }
}
