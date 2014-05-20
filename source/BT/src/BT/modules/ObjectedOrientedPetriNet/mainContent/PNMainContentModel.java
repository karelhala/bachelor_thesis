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
import BT.modules.ObjectedOrientedPetriNet.PNMainContent;
import GUI.BottomLeftContentModel;
import GUI.BottomRightContentModel;
import GUI.MethodLabel;

/**
 * Model for setting and getting main content of petriNet.
 *
 * @author Karel Hala
 */
abstract public class PNMainContentModel extends MainContentController {

    /**
     * Left bottom content holds buttons for setting join edge. In petriNet is this just one line.
     */
    protected PNLeftBottomContent LeftBottomContent;

    /**
     * Left top content holds buttons for setting which object should be added to pane.
     */
    protected PNLeftTopContent LeftTopContent;

    /**
     * Content model of bottom left has class and it's methods.
     */
    protected BottomLeftContentModel bottomLeftContentModel;

    /**
     * Selected class for this petriNet.
     */
    protected CDClass selectedClass;

    /**
     * Content model of bottom right has buttons and panes based on clicked object.
     */
    protected BottomRightContentModel bottomRightModel;

    /**
     * Controller for showing and handeling of selecting class or it's methods.
     */
    protected PNBottomLeftController bottomLeftController;

    /**
     * Controller for selecting what pane should be visible and what action should be done.
     */
    protected PNBottomRightController bottomRightController;

    /**
     * Basic constructor. It sets diagramPlaces. It creates mainContent and bottomLeftController.
     *
     * @param diagramPlaces
     */
    public PNMainContentModel(DiagramPlacesManager diagramPlaces) {
        this.diagramPlaces = diagramPlaces;
        this.mainContent = new BT.modules.ObjectedOrientedPetriNet.PNMainContent(this.places);
        this.bottomLeftController = new PNBottomLeftController(this.bottomLeftContentModel);
    }

    /**
     * Method for setting bottom left content model, which holds class and method labels.
     *
     * @param bottomLeftContentModel BottomLeftContentModel
     * @return PNMainContentModel for further use.
     */
    public PNMainContentModel setBottomLeftContentModel(BottomLeftContentModel bottomLeftContentModel) {
        this.bottomLeftContentModel = bottomLeftContentModel;
        return this;
    }

    /**
     * Method for setting bottom right content model.
     *
     * @param bottomRightModel BottomRightContentModel with buttons and panels for further settings.
     * @return PNMainContentModel for further use.
     */
    public PNMainContentModel setBottomRightModel(BottomRightContentModel bottomRightModel) {
        this.bottomRightModel = bottomRightModel;
        return this;
    }

    /**
     * Method for setting main content of petriNet.
     *
     * @param mainContent PNMainContent.
     * @return PNMainContentModel for further use.
     */
    public PNMainContentModel setMainContent(PNMainContent mainContent) {
        this.mainContent = mainContent;
        return this;
    }

    /**
     * Set left bottom content with button to join objects on drawing panel of petri nets.
     *
     * @param LeftBottomContent PNLeftBottomContent.
     * @return PNMainContentModel for further use.
     */
    public PNMainContentModel setLeftBottomContent(PNLeftBottomContent LeftBottomContent) {
        this.LeftBottomContent = LeftBottomContent;
        return this;
    }

    /**
     * Set left top content with buttons to specify whot object should be added on drawing panel of petri nets.
     *
     * @param LeftTopContent PNLeftTopContent with PNPlace and PNTransition buttons.
     * @return PNMainContentModel for further use.
     */
    public PNMainContentModel setLeftTopContent(PNLeftTopContent LeftTopContent) {
        this.LeftTopContent = LeftTopContent;
        return this;
    }

    /**
     * Set which class is selected for this petriNet.
     *
     * @param selectedClass CDClass as selected class.
     * @return PNMainContentModel for further use.
     */
    public PNMainContentModel setSelectedClass(CDClass selectedClass) {
        this.selectedClass = selectedClass;
        return this;
    }

    /**
     * Returns left bottom content. Panel with one button join.
     *
     * @return LeftBottomContent as PNLeftBottomContent.
     */
    public PNLeftBottomContent getLeftBottomContent() {
        return LeftBottomContent;
    }

    /**
     * Returns left bottom content. Panel with buttons to specify what object should be added.
     *
     * @return LeftTopContent as PNLeftTopContent.
     */
    public PNLeftTopContent getLeftTopContent() {
        return LeftTopContent;
    }

    /**
     * Returns selected class for which is this petriNet created.
     *
     * @return selectedClass as CDClass.
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
