/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers.UC;

import BT.modules.UC.places.UCUseCase;
import BT.modules.UC.places.UCJoinEdge;
import BT.modules.UC.places.UCActor;
import BT.models.CoordinateModel;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Karel Hala
 */
public class UCPlaceManager {
    /**
     * Arraylist that contains all actors in UC
     * @var ArrayList<UCActor> 
     */
    private ArrayList<UCActor> actors = new ArrayList<>();
    
    /**
     * Arraylist that all contains use cases in UC
     * @var ArrayList<UCUseCase> 
     */
    private ArrayList<UCUseCase> UseCases = new ArrayList<>();
    
    /**
     * Arraylist that contains all join edges in UC
     * @var ArrayList<UCJoinEdge> 
     */
    private ArrayList<UCJoinEdge> joinEdges = new ArrayList<>();

    /**
     * Method for add new join edge to array list.
     * @param UCJoinEdge joinEdge object to be added
     */
    public void addJoinEdge(UCJoinEdge joinEdge)
    {
        if (!lineExists(joinEdge))
        {
            this.joinEdges.add(joinEdge);
        }
    }
    
    /**
     * Method for returning all join edges in array list.
     * @return ArrayList<UCJoinEdge>
     */
    public ArrayList<UCJoinEdge> getJoinEdges() 
    {
        return this.joinEdges;
    }
    
    /**
     * Method for returning all actors in array list.
     * @return ArrayList<UCActor>
     */
    public ArrayList<UCActor> getActors()
    {
        return this.actors;
    }
    
    /**
     * Method for adding new place to array list.
     * @param UCActor place object to be added
     */
    public void addActor(UCActor place) 
    {
        this.actors.add(place);
    }
    
    /**
     * Method that returns all use cases as array list.
     * @return ArrayList<UCUseCase>
     */
    public ArrayList<UCUseCase> getUseCases()
    {
        return this.UseCases;
    }
    
    /**
     * Method for adding new use case to array list.
     * @param UCUseCase place object to be added
     */
    public void addUseCase(UCUseCase place) 
    {
        this.UseCases.add(place);
    }

    /**
     * Method that removes selected object from any array list.
     * @param CoordinateModel selectedObject object to be deleted
     */
    public void removePlace(CoordinateModel selectedObject) {
        removeJoinEdgesWithObject(selectedObject);
        if (selectedObject instanceof UCActor)
        {
            this.actors.remove(selectedObject);
        }
        else if (selectedObject instanceof UCUseCase)
        {
            this.UseCases.remove(selectedObject);
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
    
    /**
     * Method that checks if line allrady exists. Either in way first object --> second object or
     * second object --> first object.
     * Resolving issue with multiple lines connected to same objects.
     * Also checks if line isn't same
     * @param UCJoinEdge newLine
     * @return true when line exist
     * @return false when line doesn't exist
     */
    public Boolean lineExists(UCJoinEdge newLine)
    {
        for (UCJoinEdge oneEdge : this.joinEdges)
        {
            if (oneEdge.getSecondObject().equals(newLine.getSecondObject()) || oneEdge.getSecondObject().equals(newLine.getfirstObject()))
            {
                if (oneEdge.getfirstObject().equals(newLine.getSecondObject()) || oneEdge.getfirstObject().equals(newLine.getfirstObject()))
                {
                    if (oneEdge.getJoinEdgeType() == newLine.getJoinEdgeType())
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
