/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers;

import BT.BT;
import BT.models.PlaceModel;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCUseCase;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
     * Method that deletes all lines and objects, that has no assigned object in use case.
     */
    public void deleteAllUnassignedObjects()
    {
        for (Iterator<LineModel> it = joinEdges.iterator(); it.hasNext();) {
            CoordinateModel coorModel = it.next();
            if (coorModel.getAssignedObject() == null) {
                LineModel selectedLine = (LineModel) coorModel;
                selectedLine.getFirstObject().removeOutJoin(selectedLine);
                if (selectedLine.getSecondObject() != null) {
                    selectedLine.getSecondObject().removeInJoin(selectedLine);
                }
                it.remove();
            }
        }
        
        for (Iterator<CoordinateModel> it = objects.iterator(); it.hasNext();) {
            CoordinateModel coorModel = it.next();
            if (coorModel.getAssignedObject() == null) {
                removeJoinEdgesWithObject(coorModel);
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
    
    /**
     * Method for fetching all use case actors.
     * @return ArrayList<CoordinateModel> of actors
     */
    public ArrayList<CoordinateModel> getActorsFromUseCase()
    {
        ArrayList<CoordinateModel> useCasemodels = new ArrayList<>();
        for (CoordinateModel oneObject : this.getObjects()) {
            if (oneObject instanceof UCActor)
            {
                useCasemodels.add(oneObject);
            }
        }
        return useCasemodels;
    }
    
    
    /**
     * Method for fetching all use case useCases.
     * @return ArrayList<CoordinateModel> of useCases
     */
    public ArrayList<CoordinateModel> getUsecasesFromUseCase()
    {
        ArrayList<CoordinateModel> useCasemodels = new ArrayList<>();
        for (CoordinateModel oneObject : this.getObjects()) {
            if (oneObject instanceof UCUseCase)
            {
                useCasemodels.add(oneObject);
            }
        }
        return useCasemodels;
    }
    
    /**
     * Method for fetching all activities from class diagram.
     * @return ArrayList<CoordinateModel> of actors
     */
    public ArrayList<CoordinateModel> getActivitiesFromClassDiagram()
    {
        ArrayList<CoordinateModel> classModels = new ArrayList<>();
        for (CoordinateModel oneObject : this.getObjects()) {
            if (oneObject instanceof CDClass && ((CDClass)oneObject).getTypeOfClass() == BT.ClassType.ACTIVITY)
            {
                classModels.add(oneObject);
            }
        }
        return classModels;
    }
    
    
    /**
     * Method for fetching all actors from class diagram.
     * @return ArrayList<CoordinateModel> of useCases
     */
    public ArrayList<CoordinateModel> getActorsFromClassDiagram()
    {
        ArrayList<CoordinateModel> classModels = new ArrayList<>();
        for (CoordinateModel oneObject : this.getObjects()) {
            if (oneObject instanceof CDClass && ((CDClass)oneObject).getTypeOfClass() == BT.ClassType.ACTOR)
            {
                classModels.add(oneObject);
            }
        }
        return classModels;
    }
    
    /**
     * Method that creates option pane, that lets you select which object should be connected.
     * @param modelsArray
     * @return int based on selected item
     */
    public int createOptionPaneWithSelectBox(String[] modelsArray)
    {
        JPanel dialogPanel = new JPanel(new BorderLayout());
        JComboBox comboBoxObjects = new JComboBox(modelsArray);
        dialogPanel.add(comboBoxObjects);
        int result = JOptionPane.showConfirmDialog(null, dialogPanel,
                    "Please Select object you want to reactivate from", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION)
        {   
            if ((JOptionPane.showConfirmDialog(null, "Are you sure you want to reactivate this object with selected object?",
                    "Please confirm", JOptionPane.YES_NO_OPTION)) == JOptionPane.OK_OPTION)
            {
                return comboBoxObjects.getSelectedIndex();
            }
            
        }
        return -1;
    }
}
