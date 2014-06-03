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
import BT.models.MyArrayList;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Class for constrolling petriNet joins. It set's start and end point of petriNet join. It manages variables arround
 * line.
 *
 * @author Karel Hala
 */
public class PNJoinEdgeController extends LineModel {

    /**
     * Join edge type of petriNet join.
     */
    private BT.OOPNLineType joinEdgeType;

    /**
     * Selected variables to be shown.
     */
    private final MyArrayList<String> selectedVariables;

    /**
     * Additional string variable.
     */
    private String additionalVariable;

    /**
     * Basic constructor. It calls parent's (LineModel) constructor.
     * Sets joinEdge type to JOIN and create selectedVariables as MyArrayList.
     */
    public PNJoinEdgeController() {
        super();
        this.joinEdgeType = OOPNLineType.JOIN;
        this.selectedVariables = new MyArrayList<>();
    }

    /**
     * Set type of petriNet join edge.
     * 
     * @param joinEdgeType OOPNLineType (now only JOIN).
     */
    public void setJoinEdgeType(OOPNLineType joinEdgeType) {
        this.joinEdgeType = joinEdgeType;
    }

    /**
     * Get type of petriNet join edge.
     * 
     * @return OOPNLineType (now only JOIN).
     */
    public OOPNLineType getJoinEdgeType() {
        return joinEdgeType;
    }

    /**
     * Get variables that are selected to be shown near join edge. Retun them as MyArrayList of strings.
     * 
     * @return MyArrayList<String> selected variables.
     */
    public MyArrayList<String> getSelectedVariables() {
        return selectedVariables;
    }

    /**
     * Add unique variable to join edge.
     *
     * @param newVariable this new variable will be added to selectedVariables.
     */
    public void addVariable(String newVariable) {
        this.selectedVariables.addUnique(newVariable);
    }

    /**
     * Return additional variable as String.
     * 
     * @return additional variable is shown near join edge.
     */
    public String getAdditionalVariable() {
        return additionalVariable;
    }

    /**
     * Set additional variable as String.
     * @param additionalVariable additional variable is shown near join edge.
     */
    public void setAdditionalVariable(String additionalVariable) {
        this.additionalVariable = additionalVariable;
    }

    /**
     * For fetching selected variables as String joined by ","
     *
     * @return all variables joined as string, comma is separator to these variables.
     */
    public String getVariablesAsString() {
        String returnedString = "";
        for (String oneVariable : this.selectedVariables) {
            returnedString += oneVariable;
            if (!this.selectedVariables.isLast(oneVariable)) {
                returnedString += ", ";
            }
        }
        if (this.additionalVariable != null && !this.additionalVariable.equals("")) {
            if (!this.selectedVariables.isEmpty()) {
                returnedString += ", ";
            }
            returnedString += this.additionalVariable;
        }
        return returnedString;
    }

    /**
     * For drawing this join edge.
     * First calculate start and end point of this join edge. Then call drawing function.
     * 
     * @param g Graphics2D that will draw this join edge.
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
