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
import GUI.BasicPetrinetPanel;
import GUI.BottomRightContentModel;
import GUI.MethodLabel;
import GUI.PetrinetGuardActionPanel;
import GUI.PetrinetPlacePanel;

/**
 * Class which models bottom right panel. This panel has few settings. Either adding new place from variable, adding
 * constant to place and eddit transition.
 *
 * @author Karel Hala
 */
abstract public class PNBottomRightModel {

    /**
     * right content model with 2 buttons and additional pane.
     */
    protected BottomRightContentModel bottomRightModel;

    /**
     * MethodLabel that is being eddited.
     */
    protected MethodLabel selectedMethod;

    /**
     * Panel for inserting new places from class variables or method's arguments.
     */
    protected BasicPetrinetPanel basicPetrinetPanel;

    /**
     * Panel with guard and action panel.
     */
    protected PetrinetGuardActionPanel petrinetGuardAction;

    /**
     * Panel with setting constant of place.
     */
    protected PetrinetPlacePanel petrinetPlace;

    /**
     * Objects of eddited petriNet.
     */
    protected PlaceManager petrinetPlaces;

    /**
     * Drawing pane which draws petriNet objects.
     */
    protected PNDrawingPane petrinetDrawingPane;

    /**
     * Selected class for this petriNet.
     */
    protected CDClass selectedClass;

    /**
     * Object of petriNet that is selected.
     */
    protected CoordinateModel selectedObject;

    /**
     * Manager for all classes from class diagram of opened file.
     */
    protected PlaceManager classManager;

    /**
     * Basic constructor. It sets basicPetrinetPanel, petrinetGuardAction, bottomRightModel, petrinetPlace based on
     * arguments.
     *
     * @param bottomRightModel for setting BottomRightContentModel.
     * @param petrinetPanel for setting BasicPetrinetPanel.
     * @param petrinetGuardAction for setting PetrinetGuardActionPanel.
     * @param petrinetPlace for setting PetrinetPlacePanel.
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
     * @return selectedMethod as MethodLabel.
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
     * Returns panel with button for adding new places from variables or arguments.
     *
     * @return BasicPetrinetPanel.
     */
    public BasicPetrinetPanel getBasicPetrinetPanel() {
        return basicPetrinetPanel;
    }

    /**
     * Returns panel with guard and action panel.
     *
     * @return PetrinetGuardActionPanel.
     */
    public PetrinetGuardActionPanel getPetrinetGuardAction() {
        return petrinetGuardAction;
    }

    /**
     * BottomRightModel has top and bottom button with additional content.
     * 
     * @return bottomRightModel used for setting action and guard in this petriNet.
     */
    public BottomRightContentModel getBottomRightModel() {
        return bottomRightModel;
    }

    /**
     * BottomRightModel has top and bottom button with additional content.
     * 
     * @param bottomRightModel used for setting action and guard in this petriNet.
     */
    public void setBottomRightModel(BottomRightContentModel bottomRightModel) {
        this.bottomRightModel = bottomRightModel;
    }

    /**
     * Returns places that are drawn on petriNet drawing panel.
     * 
     * @return these places are drawn and maintained.
     */
    public PlaceManager getPetrinetPlaces() {
        return petrinetPlaces;
    }

    /**
     * Set places that are drawn on petriNet drawing panel.
     * 
     * @param petrinetPlaces these places will be drawn and maintained.
     */
    public void setPetrinetPlaces(PlaceManager petrinetPlaces) {
        this.petrinetPlaces = petrinetPlaces;
    }

    /**
     * Returns drawing panel for petriNet. This drawing panel draws each object of petriNet.
     *
     * @return PNDrawingPane which will draw each object of petriNet.
     */
    public PNDrawingPane getPetrinetDrawingPane() {
        return petrinetDrawingPane;
    }

    /**
     * Set drawing panel for petriNet. This drawing panel draws each object of petriNet.
     *
     * @param petrinetDrawingPane PNDrawingPane which will draw each object of petriNet.
     */
    public void setPetrinetDrawingPane(PNDrawingPane petrinetDrawingPane) {
        this.petrinetDrawingPane = petrinetDrawingPane;
    }

    /**
     * Returns class that is selected for this petriNet.
     *
     * @return CDClass which is selected for this petriNet.
     */
    public CDClass getSelectedClass() {
        return selectedClass;
    }

    /**
     * Set class that is selected for this petriNet.
     *
     * @param selectedClass CDClass which is selected for this petriNet.
     */
    public void setSelectedClass(CDClass selectedClass) {
        this.selectedClass = selectedClass;
    }

    /**
     * Returns selected object which is used later.
     *
     * @return CoordinateModel, often clicked object.
     */
    public CoordinateModel getSelectedObject() {
        return selectedObject;
    }

    /**
     * Sets selected object which is used later.
     *
     * @param selectedObject CoordinateModel, often clicked object.
     */
    public void setSelectedObject(CoordinateModel selectedObject) {
        this.selectedObject = selectedObject;
    }

    /**
     * Returns manager that holds each class from class diagram.
     *
     * @return PlaceManager of classes from class diagram.
     */
    public PlaceManager getClassManager() {
        return classManager;
    }

    /**
     * Set manager that holds each class from class diagram.
     *
     * @param classManager PlaceManager of classes from class diagram.
     */
    public void setClassManager(PlaceManager classManager) {
        this.classManager = classManager;
    }

    /**
     * Get panel with text to set constant of place.
     *
     * @return PetrinetPlacePanel.
     */
    public PetrinetPlacePanel getPetrinetPlace() {
        return petrinetPlace;
    }

    /**
     * Sets panel with text to set constant of place.
     *
     * @param petrinetPlace PetrinetPlacePanel
     */
    public void setPetrinetPlace(PetrinetPlacePanel petrinetPlace) {
        this.petrinetPlace = petrinetPlace;
    }
}
