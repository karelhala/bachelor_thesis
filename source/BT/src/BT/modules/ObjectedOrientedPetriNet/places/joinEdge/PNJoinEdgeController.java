/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ObjectedOrientedPetriNet.places.joinEdge;

import BT.BT;
import BT.BT.OOPNLineType;
import BT.managers.PointsCalculator;
import BT.models.LineModel;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Karel
 */
public class PNJoinEdgeController extends LineModel {

    private BT.OOPNLineType joinEdgeType;

    public void setJoinEdgeType(OOPNLineType joinEdgeType) {
        this.joinEdgeType = joinEdgeType;
    }

    public OOPNLineType getJoinEdgeType() {
        return joinEdgeType;
    }

    /**
     *
     */
    public PNJoinEdgeController() {
        super();
        this.joinEdgeType = OOPNLineType.JOIN;
    }

    /**
     *
     * @param g
     */
    public void drawJoinEdge(Graphics2D g) {
        PointsCalculator pointsCaluclator = new PointsCalculator(this.firstObject, this.secondObject, getStartPoint(), getEndPoint(), this.breakPoints);

        PNJoinEdgeDrawer lineDrawer;
        lineDrawer = new PNJoinEdgeDrawer(this, pointsCaluclator.getStartPoint(), pointsCaluclator.getEndPoint());
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
