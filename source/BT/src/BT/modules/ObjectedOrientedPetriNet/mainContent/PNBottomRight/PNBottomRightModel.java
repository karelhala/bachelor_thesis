/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ObjectedOrientedPetriNet.mainContent.PNBottomRight;

import BT.managers.PlaceManager;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ObjectedOrientedPetriNet.mainContent.PNDrawingPane;
import BT.modules.ObjectedOrientedPetriNet.places.PNTransition;
import GUI.BasicPetrinetPanel;
import GUI.BottomRightContentModel;
import GUI.MethodLabel;
import GUI.PetrinetGuardActionPanel;

/**
 *
 * @author Karel Hala
 */
public class PNBottomRightModel {
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
    protected PNTransition selectedTransition;
    
    /**
     * 
     * @param bottomRightModel
     * @param petrinetPanel
     * @param petrinetGuardAction 
     */
    public PNBottomRightModel(BottomRightContentModel bottomRightModel, BasicPetrinetPanel petrinetPanel, PetrinetGuardActionPanel petrinetGuardAction) {
        this.basicPetrinetPanel = petrinetPanel;
        this.petrinetGuardAction = petrinetGuardAction;
        this.bottomRightModel = bottomRightModel;
    }
    
    /**
     * Getter for selected method of right content Panel.
     * @return 
     */
    public MethodLabel getSelectedMethod() {
        return selectedMethod;
    }

    /**
     * Setter for selected method of right content Panel.
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

    public PNTransition getSelectedTransition() {
        return selectedTransition;
    }

    public void setSelectedTransition(PNTransition selectedTransition) {
        this.selectedTransition = selectedTransition;
    }
}
