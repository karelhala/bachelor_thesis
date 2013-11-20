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

/**
*
* @author Karel Hala
*/
public class UCPlaceModel {
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
}
