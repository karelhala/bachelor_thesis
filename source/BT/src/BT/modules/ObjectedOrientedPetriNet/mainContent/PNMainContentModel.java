/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ObjectedOrientedPetriNet.mainContent;

import BT.modules.ObjectedOrientedPetriNet.mainContent.PNBottomRight.PNBottomRightController;
import BT.managers.DiagramPlacesManager;
import BT.managers.MainContentController;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ObjectedOrientedPetriNet.PNLeftBottomContent;
import BT.modules.ObjectedOrientedPetriNet.PNLeftTopContent;
import GUI.BottomLeftContentModel;
import GUI.BottomRightContentModel;
import GUI.MethodLabel;

/**
 *
 * @author Karel Hala
 */
abstract public class PNMainContentModel extends MainContentController {

    /**
     *
     */
    protected PNLeftBottomContent LeftBottomContent;

    /**
     *
     */
    protected PNLeftTopContent LeftTopContent;

    /**
     *
     */
    protected BottomLeftContentModel bottomLeftContentModel;

    /**
     *
     */
    protected CDClass selectedClass;

    /**
     *
     */
    protected BottomRightContentModel bottomRightModel;

    /**
     *
     */
    protected PNBottomLeftController bottomLeftController;

    /**
     *
     */
    protected PNBottomRightController bottomRightController;

    /**
     *
     * @param diagramPlaces
     */
    public PNMainContentModel(DiagramPlacesManager diagramPlaces) {
        this.diagramPlaces = diagramPlaces;
        this.mainContent = new BT.modules.ObjectedOrientedPetriNet.PNMainContent(this.places);
        this.bottomLeftController = new PNBottomLeftController(this.bottomLeftContentModel);
    }

    public PNMainContentModel setBottomLeftContentModel(BottomLeftContentModel bottomLeftContentModel) {
        this.bottomLeftContentModel = bottomLeftContentModel;
        return this;
    }

    /**
     *
     * @param bottomRightModel
     * @return
     */
    public PNMainContentModel setBottomRightModel(BottomRightContentModel bottomRightModel) {
        this.bottomRightModel = bottomRightModel;
        return this;
    }

    /**
     *
     * @param mainContent
     * @return
     */
    public PNMainContentModel setMainContent(BT.modules.ObjectedOrientedPetriNet.PNMainContent mainContent) {
        this.mainContent = mainContent;
        return this;
    }

    /**
     *
     * @param LeftBottomContent
     * @return
     */
    public PNMainContentModel setLeftBottomContent(PNLeftBottomContent LeftBottomContent) {
        this.LeftBottomContent = LeftBottomContent;
        return this;
    }

    /**
     *
     * @param LeftTopContent
     * @return
     */
    public PNMainContentModel setLeftTopContent(PNLeftTopContent LeftTopContent) {
        this.LeftTopContent = LeftTopContent;
        return this;
    }

    /**
     *
     * @param selectedClass
     * @return
     */
    public PNMainContentModel setSelectedClass(CDClass selectedClass) {
        this.selectedClass = selectedClass;
        return this;
    }

    /**
     *
     * @return
     */
    public PNLeftBottomContent getLeftBottomContent() {
        return LeftBottomContent;
    }

    /**
     *
     * @return
     */
    public PNLeftTopContent getLeftTopContent() {
        return LeftTopContent;
    }

    /**
     *
     * @return
     */
    public CDClass getSelectedClass() {
        return selectedClass;
    }

    /**
     * Intilize bottom left content controller.
     *
     * @return this.
     */
    public PNMainContentModel initializeBottomLeftController() {
        this.bottomLeftController = new PNBottomLeftController(bottomLeftContentModel);
        if (this.selectedClass != null) {
            this.bottomLeftController.setDrawnClass(new MethodLabel(this.selectedClass.getName(), this.selectedClass));
        }
        return this;
    }
}
