/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers;

import BT.models.PlaceModel;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import java.util.Iterator;

/**
 *
 * @author Karel Hala
 */
public class PlaceManager extends PlaceModel {

    public PlaceManager() {
        super();
    }

    /**
     * Method that removes selected object from any array list.
     *
     * @param selectedObject selectedObject object to be deleted
     */
    public void removePlace(CoordinateModel selectedObject) {
        removeJoinEdgesWithObject(selectedObject);
        if (selectedObject.getAssignedObject() != null)
        {
            (selectedObject.getAssignedObject()).setAssignedObject(null);
        }
        this.objects.remove(selectedObject);
    }

    /**
     * Method for removing join edge from array list.
     *
     * @param jointEdge jointEdge object to be removed.
     */
    public void removeJoinEdge(LineModel jointEdge) {
        jointEdge.getFirstObject().removeOutJoin(jointEdge);
        if (jointEdge.getSecondObject() != null) {
            jointEdge.getSecondObject().removeInJoin(jointEdge);
        }
        if (jointEdge.getAssignedObject() != null)
        {
            jointEdge.getAssignedObject().setAssignedObject(null);
        }
        this.joinEdges.remove(jointEdge);
    }

    /**
     * Remove join edges that has object, that will be removed.
     *
     * @param CoordinateModel removedObject object that will be removed.
     */
    private void removeJoinEdgesWithObject(CoordinateModel removedObject) {
        Iterator<LineModel> it = joinEdges.iterator();
        while (it.hasNext()) {
            LineModel joinEdge = it.next();
            if (joinEdge.getFirstObject().equals(removedObject) || joinEdge.getSecondObject().equals(removedObject)) {
                joinEdge.getFirstObject().removeOutJoin(joinEdge);
                if (joinEdge.getSecondObject() != null) {
                    joinEdge.getSecondObject().removeInJoin(joinEdge);
                }
                if (joinEdge.getAssignedObject() != null)
                {
                    joinEdge.getAssignedObject().setAssignedObject(null);
                }
                it.remove();
            }
        }
    }

    /**
     * Method for looping through every array list and removing every object,
     * that is selected.
     */
    public void removeAllSelectedItems() {
        for (Iterator<LineModel> it = joinEdges.iterator(); it.hasNext();) {
            CoordinateModel coorModel = it.next();
            if (coorModel.getSelected()) {
                LineModel selectedLine = (LineModel) coorModel;
                selectedLine.getFirstObject().removeOutJoin(selectedLine);
                if (selectedLine.getSecondObject() != null) {
                    selectedLine.getSecondObject().removeInJoin(selectedLine);
                }
                if (selectedLine.getAssignedObject() != null)
                {
                    selectedLine.getAssignedObject().setAssignedObject(null);
                }
                it.remove();
            }
        }

        for (Iterator<CoordinateModel> it = objects.iterator(); it.hasNext();) {
            CoordinateModel coorModel = it.next();
            if (coorModel.getSelected()) {
                removeJoinEdgesWithObject(coorModel);
                if (coorModel.getAssignedObject() != null)
                {
                    (coorModel.getAssignedObject()).setAssignedObject(null);
                }
                it.remove();
            }
        }
    }

    /**
     * Method that sets all obejct to be not selected.
     */
    public void setAllObjectDiselected() {
        for (CoordinateModel oneModel : this.objects) {
            oneModel.setSelected(false);
        }
        for (CoordinateModel oneJoinEdge : this.joinEdges) {
            oneJoinEdge.setSelected(false);
        }
    }

    /**
     * Method for selecting lines that come out of object.
     *
     * @param place selected place
     */
    public void setSelectedLinesOnObject(CoordinateModel place) {
        for (LineModel oneEdge : this.joinEdges) {
            if (oneEdge.getFirstObject().equals(place)) {
                oneEdge.setSelected(true);
            } else {
                oneEdge.setSelected(false);
            }
        }
    }
    
    public CoordinateModel getSelectedObject()
    {
        for (CoordinateModel oneObject : this.objects) {
            if (oneObject.getSelected())
            {
                return oneObject;
            }
        }
        return null;
    }
}
