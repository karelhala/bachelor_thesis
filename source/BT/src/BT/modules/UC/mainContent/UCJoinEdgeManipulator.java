/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.mainContent;

import BT.BT;
import BT.models.CoordinateModel;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import BT.modules.UC.places.UCUseCase;
import javax.swing.JToggleButton;

/**
 * Class for manipulating with join edges between each usecase object.
 *
 * @author Karel Hala
 */
public class UCJoinEdgeManipulator {

    /**
     * Change type of join edge by button type.
     *
     * @param selectedButton selected button, by this the line will change.
     * @param joinEdge line to be changed.
     */
    public static void changeLineTypeByButton(JToggleButton selectedButton, UCJoinEdgeController joinEdge) {
        switch (selectedButton.getName()) {
            case "ASSOCIATION":
                joinEdge.setJoinEdgeType(BT.UCLineType.ASSOCIATION);
                break;

            case "INCLUDE":
                joinEdge.setJoinEdgeType(BT.UCLineType.INCLUDE);
                checkObjects(joinEdge);
                break;

            case "EXTENDS":
                joinEdge.setJoinEdgeType(BT.UCLineType.EXTENDS);
                checkObjects(joinEdge);
                break;
            case "GENERALIZATION":
                joinEdge.setJoinEdgeType(BT.UCLineType.GENERALIZATION);
                break;
        }
        setLineTypeBySecondObject(joinEdge);
    }

    /**
     * Method for creating join edge. It will create jin edge if null is passed, insert to first place if no object is
     * there or insert at second place. It will call method for checking if objects are correct.
     *
     * @param joinEdge UCJoinEdgeController to be created and eddited.
     * @param clickedObject CoordinateModel oject that will be inseted on first object or second object place.
     * @return changed UCJoinEdgeController.
     */
    public static UCJoinEdgeController createJoinEdge(UCJoinEdgeController joinEdge, CoordinateModel clickedObject) {
        if (joinEdge == null) {
            joinEdge = new UCJoinEdgeController();
        }
        if (joinEdge.getFirstObject() == null) {
            joinEdge.setFirstObject(clickedObject);
        } else if (joinEdge.getSecondObject() == null) {
            if (clickedObject instanceof UCActor && joinEdge.getFirstObject() instanceof UCUseCase) {
                joinEdge.setSecondObject(joinEdge.getFirstObject());
                joinEdge.setFirstObject(clickedObject);
                if (joinEdge.getBreakPoints() != null && !joinEdge.getBreakPoints().isEmpty()) {
                    joinEdge.getBreakPoints().swapWholeArrayList();
                }
            } else {
                joinEdge.setSecondObject(clickedObject);
            }
        }
        return joinEdge;
    }

    /**
     * Method for setting type of line based on second object. This is used for checking of association join.s
     * 
     * @param joinEdge UCJoinEdgeController to be inspected.
     */
    public static void setLineTypeBySecondObject(UCJoinEdgeController joinEdge) {
        if (joinEdge.getFirstObject() instanceof UCActor && joinEdge.getSecondObject() instanceof UCUseCase) {
            joinEdge.setJoinEdgeType(BT.UCLineType.ASSOCIATION);
        }
        if (joinEdge.getJoinEdgeType() == BT.UCLineType.ASSOCIATION) {
            if (joinEdge.getSecondObject() != null && joinEdge.getFirstObject().getClass().equals(joinEdge.getSecondObject().getClass())) {
                joinEdge.setSecondObject(null);
            }
        }
    }

    /**
     * method for checking if first and second object can be joined together.
     * 
     * @param joinEdge UCJoinEdgeController to be inspected.
     */
    public static void checkObjects(UCJoinEdgeController joinEdge) {
        if (joinEdge.getFirstObject() != null && joinEdge.getFirstObject() instanceof UCActor) {
            joinEdge.setFirstObject(null);
        }
        if (joinEdge.getSecondObject() != null && joinEdge.getSecondObject() instanceof UCActor) {
            joinEdge.setSecondObject(null);
        }
    }
}
