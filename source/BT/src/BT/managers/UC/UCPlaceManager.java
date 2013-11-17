/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers.UC;

import BT.managers.CoordinateManager;
import java.awt.Color;
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

    
    public void addJoinEdge(UCJoinEdge joinEdge)
    {
        if (!lineExists(joinEdge))
        {
            this.joinEdges.add(joinEdge);
        }
    }
    
    public ArrayList<UCJoinEdge> getJoinEdges() 
    {
        return this.joinEdges;
    }
    
    public ArrayList<UCActor> getActors()
    {
        return this.actors;
    }
    
    public void addActor(UCActor place) 
    {
        this.actors.add(place);
    }
    
    public ArrayList<UCUseCase> getUseCases()
    {
        return this.UseCases;
    }
    
    public void addUseCase(UCUseCase place) 
    {
        this.UseCases.add(place);
    }

    public void removePlace(CoordinateManager selectedObject) {
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
    
    public void removeJointEdge(UCJoinEdge jointEdge)
    {
        this.joinEdges.remove(jointEdge);
    }
    
    public void removeJoinEdgesWithObject(CoordinateManager removedObject)
    {
        Iterator<UCJoinEdge> it = joinEdges.iterator();
        while (it.hasNext()) {
          UCJoinEdge joinEdge = it.next();
          if (joinEdge.getfirstObject().equals(removedObject) || joinEdge.getSecondObject().equals(removedObject)) {
            it.remove();
          }
        }
    }
    
    public UCJoinEdge setSelectedLinesOnObject(CoordinateManager place)
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
