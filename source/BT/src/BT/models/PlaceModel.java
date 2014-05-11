/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.models;

import BT.managers.RecalculatePaneSize;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 * Class that hold variables and methods of each places. It stores paneSize,
 * objects and object's lines.
 *
 * @author Karel Hala
 */
public class PlaceModel {

    /**
     * Size of printed pane as dimension.
     */
    protected Dimension paneSize;
    /**
     * Arraylist that contains all actors in UC
     *
     * @var ArrayList<UCActor>
     */
    protected ArrayList<CoordinateModel> objects = new ArrayList<>();
    /**
     * Arraylist that contains all join edges in UC
     *
     * @var MyArrayList<LineModel>
     */
    protected MyArrayList<LineModel> joinEdges = new MyArrayList<>();

    /**
     * Method for add new join edge to array list.
     *
     * @param UCJoinEdgeController joinEdge object to be added
     */
    private void addJoinEdge(LineModel joinEdge) {
        if (!lineExists(joinEdge)) {
            if (joinEdge.getFirstObject() != null && joinEdge.getSecondObject() != null) {
                joinEdge.getFirstObject().addOutJoins(joinEdge);
                joinEdge.getSecondObject().addInJoin(joinEdge);
                this.joinEdges.addUnique(joinEdge);
            }
        }
    }

    /**
     * Get pane size of printed pane.
     *
     * @return Dimension of Pane.
     */
    public Dimension getPaneSize() {
        return paneSize;
    }

    /**
     * Set pane size for printing.
     *
     * @param paneSize Dimension of Pane.
     */
    public void setPaneSize(Dimension paneSize) {
        this.paneSize = paneSize;
    }

    /**
     * Method for returning all join edges in array list.
     *
     * @return ArrayList<LineModel>
     */
    public ArrayList<LineModel> getJoinEdges() {
        return this.joinEdges;
    }

    /**
     * Method for returning all objects in array list.
     *
     * @return ArrayList<CoordinateModel>
     */
    public ArrayList<CoordinateModel> getObjects() {
        return this.objects;
    }

    /**
     * Method for adding new place to array list.
     *
     * @param CoordinateModel place object to be added
     */
    private void addPlace(CoordinateModel place) {
        this.objects.add(place);
    }

    /**
     * Method for inserting new object to either join edges or to places.
     *
     * @param coordModel
     */
    public void addObject(CoordinateModel coordModel) {
        if (coordModel instanceof LineModel) {
            addJoinEdge((LineModel) coordModel);
        } else {
            addPlace(coordModel);
        }
    }

    /**
     * Method that checks if line allrady exists, either in way first object -->
     * second object or second object --> first object. Resolving issue with
     * multiple lines connected to same objects. Also checks if line isn't same
     *
     * @param newLine
     * @return true when line exist
     */
    public Boolean lineExists(LineModel newLine) {
        for (LineModel oneEdge : this.joinEdges) {
            if (oneEdge.getSecondObject().equals(newLine.getSecondObject()) || oneEdge.getSecondObject().equals(newLine.getFirstObject())) {
                if (oneEdge.getFirstObject().equals(newLine.getSecondObject()) || oneEdge.getFirstObject().equals(newLine.getFirstObject())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method for fetching existing class.
     *
     * @param newLine check if this line doeasn't exists.
     * @return line that allready exists or null.
     */
    public LineModel getExistingLine(LineModel newLine) {
        if (lineExists(newLine)) {
            for (LineModel oneEdge : this.joinEdges) {
                if (oneEdge.getSecondObject().equals(newLine.getSecondObject()) || oneEdge.getSecondObject().equals(newLine.getFirstObject())) {
                    if (oneEdge.getFirstObject().equals(newLine.getSecondObject()) || oneEdge.getFirstObject().equals(newLine.getFirstObject())) {
                        return oneEdge;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Method used for calculating pane size based on each object. This is
     * usefull for calculating when exporting to EPS.
     */
    public void calculateDimension() {
        for (CoordinateModel coordinateModel : objects) {
            paneSize = RecalculatePaneSize.recalculateSizeofPaneOnObject(coordinateModel, paneSize);
        }
    }
}
