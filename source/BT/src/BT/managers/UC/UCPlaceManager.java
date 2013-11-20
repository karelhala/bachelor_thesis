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
    private ArrayList<UCActor> actors = new ArrayList<>();
    private ArrayList<UCUseCase> UseCases = new ArrayList<>();
    private ArrayList<UCJoinEdge> joinEdges = new ArrayList<>();

    /**
     * 
     * @param joinEdge 
     */
    public void addJoinEdge(UCJoinEdge joinEdge)
    {
        if (!lineExists(joinEdge))
        {
            this.joinEdges.add(joinEdge);
        }
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<UCJoinEdge> getJoinEdges() 
    {
        return this.joinEdges;
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<UCActor> getActors()
    {
        return this.actors;
    }
    
    /**
     * 
     * @param place 
     */
    public void addActor(UCActor place) 
    {
        this.actors.add(place);
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<UCUseCase> getUseCases()
    {
        return this.UseCases;
    }
    
    /**
     * 
     * @param place 
     */
    public void addUseCase(UCUseCase place) 
    {
        this.UseCases.add(place);
    }

    /**
     * 
     * @param selectedObject 
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
     * 
     * @param jointEdge 
     */
    public void removeJointEdge(UCJoinEdge jointEdge)
    {
        this.joinEdges.remove(jointEdge);
    }
    
    /**
     * 
     * @param removedObject 
     */
    public void removeJoinEdgesWithObject(CoordinateModel removedObject)
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
     * 
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
     * 
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
     * 
     * @param place
     * @return 
     */
    public UCJoinEdge setSelectedLinesOnObject(CoordinateModel place)
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
        return null;
    }
    
    /**
     * 
     * @param newLine
     * @return 
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
