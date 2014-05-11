/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers;

import BT.models.LineModel;
import BT.models.MyArrayList;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 * Class that can draw triangles and diamonds at the end of lines. This class is
 * basic class for drawing additional components, such as traingles or strings
 * at precise location.
 *
 * @author Karel Hala
 */
public class JoinEdgeDrawer {

    /**
     * Start point of line.
     */
    protected Point startPoint;
    /**
     * End point of line.
     */
    protected Point endPoint;
    /**
     * Controller for detectiong color of line.
     */
    protected LineModel joinEdgeController;
    /**
     * Point that describes where is start of text.
     */
    protected Point textStartPoint;
    /**
     * Point that describes where is end of text.
     */
    protected Point textEndPoint;

    /**
     * Basic constructor for drawing join edges.
     */
    public JoinEdgeDrawer(LineModel joinEdgeController, Point startPoint, Point endPoint) {
        this.joinEdgeController = joinEdgeController;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    /**
     * Method for drawing arrow at end of the line given by 2 points.
     *
     * @param g2D graphics that will have drawn this line.
     * @param A Start point.
     * @param B End point.
     */
    protected void drawArrow(Graphics2D g2D, Point A, Point B) {
        Graphics2D g = (Graphics2D) g2D.create();
        g.setStroke(new BasicStroke(2));
        double dx = B.x - A.x;
        double dy = B.y - A.y;
        double angle = Math.atan2(dy, dx);
        AffineTransform at = AffineTransform.getTranslateInstance(A.x, A.y);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        g.drawLine(0, 0, 0 + 5, 0 + 5);
        g.drawLine(0, 0, 0 + 5, 0 - 5);
    }

    /**
     * Method for drawing traiangle at end of the line given by 2 points.
     *
     * @param g2D graphics that will have drawn this triangle.
     * @param A Start point.
     * @param B End point.
     * @param fillColor color for insides of this triangle.
     */
    protected void drawTriangle(Graphics2D g2D, Point A, Point B, Color fillColor) {
        Graphics2D g = (Graphics2D) g2D.create();
        g.setStroke(new BasicStroke(2));
        double dx = B.x - A.x;
        double dy = B.y - A.y;
        double angle = Math.atan2(dy, dx);
        AffineTransform at = AffineTransform.getTranslateInstance(A.x, A.y);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        g.drawLine(0, 0, 0 + 7, 0 + 7);
        g.drawLine(0, 0, 0 + 7, 0 - 7);
        g.drawLine(0 + 7, 0 + 7, 0 + 7, 0 - 7);

        g.setColor(fillColor);
        int xpoints[] = {0, 0 + 7, 0 + 7};
        int ypoints[] = {0, 0 + 7, 0 - 7};
        int npoints = 3;

        g.fillPolygon(xpoints, ypoints, npoints);
    }

    /**
     * Method for drawing diamond at end of the line given by 2 points.
     *
     * @param g2D graphics that will have drawn this diamond.
     * @param A Start point.
     * @param B End point.
     * @param fillColor color for insides of this diamond.
     */
    protected void drawDiamond(Graphics2D g2D, Point A, Point B, Color fillColor) {
        Graphics2D g = (Graphics2D) g2D.create();
        g.setStroke(new BasicStroke(2));
        double dx = B.x - A.x;
        double dy = B.y - A.y;
        double angle = Math.atan2(dy, dx);
        AffineTransform at = AffineTransform.getTranslateInstance(A.x, A.y);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        g.drawLine(0, 0, 0 + 7, 0 + 7);
        g.drawLine(0, 0, 0 + 7, 0 - 7);
        g.drawLine(0 + 7, 0 + 7, 14, 0);
        g.drawLine(0 + 7, 0 - 7, 14, 0);

        g.setColor(fillColor);
        int xpoints[] = {0, 0 + 7, 0 + 7};
        int ypoints[] = {0, 0 + 7, 0 - 7};
        int npoints = 3;

        g.fillPolygon(xpoints, ypoints, npoints);

        int xpoints2[] = {0 + 7, 0 + 7, 14};
        int ypoints2[] = {0 + 7, 0 - 7, 0};
        int npoints2 = 3;

        g.fillPolygon(xpoints2, ypoints2, npoints2);
    }

    /**
     * Method for drawing string at precise coordinates.
     *
     * @param g2D drawing panel.
     * @param name String to be srawn.
     */
    protected void drawString(Graphics2D g2D, String name) {
        Graphics2D g = (Graphics2D) g2D.create();
        g.setStroke(new BasicStroke(2));
        double dx = this.textEndPoint.x - this.textStartPoint.x;
        double dy = this.textEndPoint.y - this.textStartPoint.y;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);
        if ((angle <= Math.PI && angle >= Math.PI / 2) || (angle >= -Math.PI && angle <= -Math.PI / 2)) {
            len = -len * 2;
            angle = angle - Math.PI;
        }
        AffineTransform at = AffineTransform.getTranslateInstance(this.textStartPoint.x, this.textStartPoint.y);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        g.drawString(name, len / 3, -5);
    }

    /**
     * Method for drawing line, that has break points inside.
     *
     * @param g drawing panel.
     * @param startPoint start point of this line.
     * @param endPoint end point of this line.
     * @param breakPoints MyArrayList<Point> points that describe break points.
     */
    protected void drawbreakedLine(Graphics2D g, Point startPoint, Point endPoint, MyArrayList<Point> breakPoints) {
        Color lineColor = g.getColor();
        for (Point point : breakPoints) {
            g.drawLine(startPoint.x, startPoint.y, point.x, point.y);
            startPoint = point;
            g.setColor(Color.BLACK);
            g.drawOval(point.x - 2, point.y - 2, 5, 5);
            g.setColor(lineColor);
        }
        if (breakPoints != null && !breakPoints.isEmpty()) {
            g.drawLine(breakPoints.getLast().x, breakPoints.getLast().y, endPoint.x, endPoint.y);
        } else {
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        }
    }

    protected void setBasicColors(Graphics2D g) {
        setBasicColors(g, true);
    }

    /**
     * For setting basic colors of lines.
     *
     * @param g drawing panel.
     * @param searchForAssignedObject should be considered assigned object in
     * cause.
     */
    protected void setBasicColors(Graphics2D g, Boolean searchForAssignedObject) {
        if (this.joinEdgeController.getSelected()) {
            g.setColor(this.joinEdgeController.getSelectedColor());
        } else {
            if (this.joinEdgeController.getAssignedObject() == null && searchForAssignedObject == true) {
                g.setColor(this.joinEdgeController.getNoParentLine());
            } else {
                g.setColor(this.joinEdgeController.getColor());
            }
        }
    }

    /**
     * Method for setting start and end point of texts.
     *
     * @param joins this argument holds join model.
     */
    protected void setStartEndPointsText(LineModel joins) {
        this.textStartPoint = (joins.getBreakPoints() != null && !joins.getBreakPoints().isEmpty()) ? joins.getBreakPoints().getLeftMiddle() : this.startPoint;
        this.textEndPoint = (joins.getBreakPoints() != null && !joins.getBreakPoints().isEmpty() && joins.getBreakPoints().size() > 1) ? joins.getBreakPoints().getRightMiddle() : this.endPoint;
    }
}
