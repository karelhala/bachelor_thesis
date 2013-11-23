/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers.UC;

import BT.modules.UC.places.UCPlaceModel;
import BT.modules.UC.places.UCUseCase;
import BT.modules.UC.places.UCJoinEdge;
import BT.modules.UC.places.UCActor;
import BT.models.CoordinateModel;
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
        if (selectedObject instanceof UCActor)
        {
            actors.remove(selectedObject);
        }
        else if (selectedObject instanceof UCUseCase)
        {
            UseCases.remove(selectedObject);
        }
    }
    
    /**
     * Method for removing join edge from array list.
     * @param UCJoinEdge jointEdge object to be removed.
     */
    public void removeJointEdge(UCJoinEdge jointEdge)
    {
        this.joinEdges.remove(jointEdge);
    }
    
    /**
     * Remove join edges that has object, that will be removed.
     * @param CoordinateModel removedObject object that will be removed.
     */
    private void removeJoinEdgesWithObject(CoordinateModel removedObject)
    {
        Iterator<UCJoinEdge> it = joinEdges.iterator();
        while (it.hasNext()) {
          UCJoinEdge joinEdge = it.next();
          if (joinEdge.getfirstObject().equals(removedObject) || joinEdge.getSecondObject().equals(removedObject)) {
            it.remove();
          }
        }
    }
    
    /**
     * Method for looping through every array list and removing every object, that is selected.
     */
    public void removeAllSelectedItems()
    {
        for (Iterator<UCJoinEdge> it = joinEdges.iterator(); it.hasNext();)
        {
            CoordinateModel coorModel = it.next();
            if (coorModel.getSelected())
            {
                it.remove();
            }
        }
        
        for (Iterator<UCActor> it = actors.iterator(); it.hasNext();)
        {
            CoordinateModel coorModel = it.next();
            if (coorModel.getSelected())
            {
                removeJoinEdgesWithObject(coorModel);
                it.remove();
            }
        }
        
        for (Iterator<UCUseCase> it = UseCases.iterator(); it.hasNext();)
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
        for (CoordinateModel oneModel : this.actors)
        {
            oneModel.setSelected(false);
        }
        
        for (CoordinateModel oneModel : this.UseCases)
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
        for (UCJoinEdge oneEdge : this.joinEdges)
        {
            if (oneEdge.getfirstObject().equals(place))
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
