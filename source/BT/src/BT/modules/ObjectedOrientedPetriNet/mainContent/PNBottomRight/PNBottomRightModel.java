/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ObjectedOrientedPetriNet.mainContent.PNBottomRight;

import BT.managers.PlaceManager;
import BT.models.CoordinateModel;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ObjectedOrientedPetriNet.mainContent.PNDrawingPane;
import BT.modules.ObjectedOrientedPetriNet.places.PNTransition;
import GUI.BasicPetrinetPanel;
import GUI.BottomRightContentModel;
import GUI.MethodLabel;
import GUI.PetrinetGuardActionPanel;
import GUI.PetrinetPlacePanel;

/**
 *
 * @author Karel Hala
 */
abstract public class PNBottomRightModel {

    /**
     *
     */
    protected BottomRightContentModel bottomRightModel;

    /**
     *
     */
    protected MethodLabel selectedMethod;

    /**
     *
     */
    protected BasicPetrinetPanel basicPetrinetPanel;

    /**
     *
     */
    protected PetrinetGuardActionPanel petrinetGuardAction;

    /**
     *
     */
    protected PetrinetPlacePanel petrinetPlace;

    /**
     *
     */
    protected PlaceManager petrinetPlaces;

    /**
     *
     */
    protected PNDrawingPane petrinetDrawingPane;

    /**
     *
     */
    protected CDClass selectedClass;

    /**
     *
     */
    protected CoordinateModel selectedObject;

    /**
     * Manager for all classes for open file.
     */
    protected PlaceManager classManager;

    /**
     *
     * @param bottomRightModel
     * @param petrinetPanel
     * @param petrinetGuardAction
     * @param petrinetPlace
     */
    public PNBottomRightModel(BottomRightContentModel bottomRightModel, BasicPetrinetPanel petrinetPanel, PetrinetGuardActionPanel petrinetGuardAction, PetrinetPlacePanel petrinetPlace) {
        this.basicPetrinetPanel = petrinetPanel;
        this.petrinetGuardAction = petrinetGuardAction;
        this.bottomRightModel = bottomRightModel;
        this.petrinetPlace = petrinetPlace;
    }

    /**
     * Getter for selected method of right content Panel.
     *
     * @return
     */
    public MethodLabel getSelectedMethod() {
        return selectedMethod;
    }

    /**
     * Setter for selected method of right content Panel.
     *
     * @param selectedMethod
     */
    public void setSelectedMethod(MethodLabel selectedMethod) {
        this.selectedMethod = selectedMethod;
    }

    /**
     *
     * @return
     */
    public BasicPetrinetPanel getBasicPetrinetPanel() {
        return basicPetrinetPanel;
    }

    /**
     *
     * @return
     */
    public PetrinetGuardActionPanel getPetrinetGuardAction() {
        return petrinetGuardAction;
    }

    public BottomRightContentModel getBottomRightModel() {
        return bottomRightModel;
    }

    public void setBottomRightModel(BottomRightContentModel bottomRightModel) {
        this.bottomRightModel = bottomRightModel;
    }

    public PlaceManager getPetrinetPlaces() {
        return petrinetPlaces;
    }

    public void setPetrinetPlaces(PlaceManager petrinetPlaces) {
        this.petrinetPlaces = petrinetPlaces;
    }

    public PNDrawingPane getPetrinetDrawingPane() {
        return petrinetDrawingPane;
    }

    public void setPetrinetDrawingPane(PNDrawingPane petrinetDrawingPane) {
        this.petrinetDrawingPane = petrinetDrawingPane;
    }

    public CDClass getSelectedClass() {
        return selectedClass;
    }

    public void setSelectedClass(CDClass selectedClass) {
        this.selectedClass = selectedClass;
    }

    public CoordinateModel getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(CoordinateModel selectedObject) {
        this.selectedObject = selectedObject;
    }

    public PlaceManager getClassManager() {
        return classManager;
    }

    public void setClassManager(PlaceManager classManager) {
        this.classManager = classManager;
    }

    public PetrinetPlacePanel getPetrinetPlace() {
        return petrinetPlace;
    }

    public void setPetrinetPlace(PetrinetPlacePanel petrinetPlace) {
        this.petrinetPlace = petrinetPlace;
    }
}
