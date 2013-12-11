/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package BT.models;

import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import java.util.ArrayList;

/**
*
* @author Karel Hala
*/
public class PlaceModel {
    /**
    * Arraylist that contains all actors in UC
    * @var ArrayList<UCActor>
    */
    protected ArrayList<CoordinateModel> objects = new ArrayList<>();
    
    /**
    * Arraylist that contains all join edges in UC
    * @var ArrayList<LineModel>
    */
    protected ArrayList<LineModel> joinEdges = new ArrayList<>();

    /**
    * Method for add new join edge to array list.
    * @param UCJoinEdgeController joinEdge object to be added
    */
    private void addJoinEdge(LineModel joinEdge)
    {
        if (!lineExists(joinEdge))
        {
            this.joinEdges.add(joinEdge);
        }
    }
    
    /**
    * Method for returning all join edges in array list.
    * @return ArrayList<LineModel>
    */
    public ArrayList<LineModel> getJoinEdges()
    {
        return this.joinEdges;
    }
    
    /**
    * Method for returning all actors in array list.
    * @return ArrayList<CoordinateModel>
    */
    public ArrayList<CoordinateModel> getObjects()
    {
        return this.objects;
    }
    
    /**
    * Method for adding new place to array list.
    * @param CoordinateModel place object to be added
    */
    private void addPlace(CoordinateModel place)
    {
        this.objects.add(place);
    }
    
    /**
     * 
     * @param coordModel 
     */
    public void addObject (CoordinateModel coordModel)
    {
        if (coordModel instanceof UCJoinEdgeController)
        {
            addJoinEdge((UCJoinEdgeController)coordModel);
        }
        else
        {
            addPlace(coordModel);
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
    public Boolean lineExists(LineModel newLine)
    {
        for (LineModel oneEdge : this.joinEdges)
        {
            if (oneEdge.getSecondObject().equals(newLine.getSecondObject()) || oneEdge.getSecondObject().equals(newLine.getFirstObject()))
            {
                if (oneEdge.getFirstObject().equals(newLine.getSecondObject()) || oneEdge.getFirstObject().equals(newLine.getFirstObject()))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
