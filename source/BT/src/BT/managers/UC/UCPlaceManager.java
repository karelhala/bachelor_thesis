/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers.UC;

import BT.modules.UC.places.UCPlaceModel;
import BT.modules.UC.places.UCUseCase;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import BT.modules.UC.places.UCActor;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import java.util.Iterator;

/**
 *
 * @author Karel Hala
 */
public class UCPlaceManager extends UCPlaceModel {
    public UCPlaceManager()
    {
        super();
    }
    
    /**
     * Method that removes selected object from any array list.
     * @param CoordinateModel selectedObject object to be deleted
     */
    public void removePlace(CoordinateModel selectedObject) {
        removeJoinEdgesWithObject(selectedObject);
        this.objects.remove(selectedObject);
    }
    
    /**
     * Method for removing join edge from array list.
     * @param UCJoinEdgeController jointEdge object to be removed.
     */
    public void removeJoinEdge(UCJoinEdgeController jointEdge)
    {
        this.joinEdges.remove(jointEdge);
    }
    
    /**
     * Remove join edges that has object, that will be removed.
     * @param CoordinateModel removedObject object that will be removed.
     */
    private void removeJoinEdgesWithObject(CoordinateModel removedObject)
    {
        Iterator<LineModel> it = joinEdges.iterator();
        while (it.hasNext()) {
          LineModel joinEdge = it.next();
          if (joinEdge.getFirstObject().equals(removedObject) || joinEdge.getSecondObject().equals(removedObject)) {
            it.remove();
          }
        }
    }
    
    /**
     * Method for looping through every array list and removing every object, that is selected.
     */
    public void removeAllSelectedItems()
    {
        for (Iterator<LineModel> it = joinEdges.iterator(); it.hasNext();)
        {
            CoordinateModel coorModel = it.next();
            if (coorModel.getSelected())
            {
                it.remove();
            }
        }
        
        for (Iterator<CoordinateModel> it = objects.iterator(); it.hasNext();)
        {
            CoordinateModel coorModel = it.next();
            if (coorModel.getSelected())
            {
                removeJoinEdgesWithObject(coorModel);
                it.remove();
            }
        }
    }
    
    /**
     * Method that sets all obejct to be not selected.
     */
    public void setAllObjectDiselected()
    {
        for (CoordinateModel oneModel : this.objects)
        {
            oneModel.setSelected(false);
        }
    }
    
    /**
     * Method for selecting lines that come out of object.
     * @param CoordinateModel place selected place
     */
    public void setSelectedLinesOnObject(CoordinateModel place)
    {
        for (LineModel oneEdge : this.joinEdges)
        {
            if (oneEdge.getFirstObject().equals(place))
            {
                oneEdge.setSelected(true);
            }
            else
            {
                oneEdge.setSelected(false);
            }
        }
    }
}
