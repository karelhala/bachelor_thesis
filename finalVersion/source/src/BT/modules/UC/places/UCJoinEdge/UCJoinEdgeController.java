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
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Objects;

/**
 * Class for calculating start and end point of join edge between ueCase objects and setting type of line.
 *
 * @author Karel Hala
 */
public class UCJoinEdgeController extends LineModel {

    /**
     * Type of join edge that is being created.
     */
    private UCLineType joinEdgeType;

    /**
     * Basic constructor. It sets first object and second object.
     *
     * @param firstObject first object to be set in LineModel.
     * @param secondObject sectond object to be set in LineModel.
     */
    public UCJoinEdgeController(CoordinateModel firstObject, CoordinateModel secondObject) {
        super();
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }

    /**
     * Method for setting join edge type of useCase join.
     *
     * @param joinEdgeType UCLineType join edge type.
     */
    public void setJoinEdgeType(UCLineType joinEdgeType) {
        this.joinEdgeType = joinEdgeType;
    }

    /**
     * basic constructor which creates basic join edge without any object assigned. Basic join edge type is set to
     * ASSOCIATION.
     */
    public UCJoinEdgeController() {
        super();
        this.joinEdgeType = UCLineType.ASSOCIATION;
    }

    /**
     * Get type of eddited join edge.
     *
     * @return UCLineType of join.
     */
    public UCLineType getJoinEdgeType() {
        return this.joinEdgeType;
    }

    /**
     * Draw join edge. First check start and end points. Then draw break points. Then draw actual join edge using
     * joinEdge drawer.
     *
     * @param g Graphics2D to be used to drawing.
     */
    public void drawJoinEdge(Graphics2D g) {
        if (this.firstObject != null) {
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
}
