/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package BT.modules.UC.places;

import BT.models.CoordinateModel;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import java.util.ArrayList;

/**
*
* @author Karel Hala
*/
public class UCPlaceModel {
    /**
    * Arraylist that contains all actors in UC
    * @var ArrayList<UCActor>
    */
    protected ArrayList<UCActor> actors = new ArrayList<>();
    
    /**
    * Arraylist that all contains use cases in UC
    * @var ArrayList<UCUseCase>
    */
    protected ArrayList<UCUseCase> UseCases = new ArrayList<>();
    
    /**
    * Arraylist that contains all join edges in UC
    * @var ArrayList<UCJoinEdge>
    */
    protected ArrayList<UCJoinEdgeController> joinEdges = new ArrayList<>();

    /**
    * Method for add new join edge to array list.
    * @param UCJoinEdgeController joinEdge object to be added
    */
    private void addJoinEdge(UCJoinEdgeController joinEdge)
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
    public ArrayList<UCJoinEdgeController> getJoinEdges()
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
    private void addActor(UCActor place)
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
    private void addUseCase(UCUseCase place)
    {
        this.UseCases.add(place);
    }
    
    /**
     * 
     * @param coordModel 
     */
    public void addObject (CoordinateModel coordModel)
    {
        if (coordModel instanceof UCActor)
        {
            addActor((UCActor)coordModel);
        }
        else if (coordModel instanceof UCUseCase)
        {
            addUseCase((UCUseCase)coordModel);
        }
        else if (coordModel instanceof UCJoinEdgeController)
        {
            addJoinEdge((UCJoinEdgeController)coordModel);
        }
    }
    
    /**
     * Method that checks if line allrady exists, either in way first object --> second object or
     * second object --> first object.
     * Resolving issue with multiple lines connected to same objects.
     * Also checks if line isn't same
     * @param UCJoinEdgeController newLine
     * @return true when line exist
     * @return false when line doesn't exist
     */
    public Boolean lineExists(UCJoinEdgeController newLine)
    {
        for (UCJoinEdgeController oneEdge : this.joinEdges)
        {
            if (oneEdge.getSecondObject().equals(newLine.getSecondObject()) || oneEdge.getSecondObject().equals(newLine.getfirstObject()))
            {
                if (oneEdge.getfirstObject().equals(newLine.getSecondObject()) || oneEdge.getfirstObject().equals(newLine.getfirstObject()))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
