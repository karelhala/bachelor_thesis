/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ObjectedOrientedPetriNet;

import BT.managers.DiagramPlacesManager;
import BT.modules.ObjectedOrientedPetriNet.mainContent.PNMainContentController;
import BT.modules.ObjectedOrientedPetriNet.mainContent.PNMainContentInitializer;
import GUI.BasicPetrinetPanel;
import GUI.BottomLeftContentModel;
import GUI.BottomRightContentModel;
import GUI.MainContentModel;
import GUI.PetrinetGuardActionPanel;
import GUI.PetrinetPlacePanel;

/**
 * Content controller for petriNets.
 * It creates panels and objects of petri net part.
 * 
 * @author Karel Hala
 */
public class PNContentController {

    /**
     * Main content is stored in this variable.
     */
    private final MainContentModel pnContent;
    /**
     * Main controller to Petri Net.
     */
    private PNMainContentController pnMain;

    /**
     * Basic constructor, that creates new mainContent model.
     */
    public PNContentController() {
        this.pnContent = new MainContentModel();
    }

    /**
     * Getter for main content model. In here is stored each part of main panels.
     * @return MainContentModel with panels where is stored controls.
     */
    public MainContentModel getPnContent() {
        return pnContent;
    }

    /**
     * Getter for main content controller of petri nets.
     * @return PNMainContentController which controlls whole petri net drqawing panel.
     */
    public PNMainContentController getPnMain() {
        return pnMain;
    }

    /**
     * Create every component of petriNet. In here every component is created and stored in pnContent.
     * @param diagramPlaces every object on drawing panels is stored in this.
     */
    public void createComponents(DiagramPlacesManager diagramPlaces) {
        BottomLeftContentModel bottomLeftContentModel = new BottomLeftContentModel();
        BottomRightContentModel bottomRightTransition = new BottomRightContentModel();
        PetrinetGuardActionPanel guardActionPanel = new PetrinetGuardActionPanel();
        PetrinetPlacePanel petrinetPlace = new PetrinetPlacePanel();
        BasicPetrinetPanel basicPetrinetPanel = new BasicPetrinetPanel();
        bottomRightTransition.setButtonNames("Change guard", "Change action");
        bottomRightTransition.addAdditionalcontent(basicPetrinetPanel.getContentPane());
        bottomRightTransition.hideButtons();

        this.pnMain = new PNMainContentController(diagramPlaces);

        PNLeftTopContent cdLeftTop = new PNLeftTopContent(pnMain);
        cdLeftTop.setListeners();

        PNLeftBottomContent pnLeftBottom = new PNLeftBottomContent(pnMain);
        pnLeftBottom.setListeners();

        this.pnContent.setCenterPane(this.pnMain.getMainContent().getMainContentPane());
        this.pnContent.setLeftTopPane(cdLeftTop.getMainContentPane());
        this.pnContent.setLeftBottomPane(pnLeftBottom.getMainContentPane());
        this.pnContent.setBottomLeftPane(bottomLeftContentModel.getMainPane());
        this.pnContent.setBottomRightPane(bottomRightTransition.getContentPane());
        this.pnMain.setLeftBottomContent(pnLeftBottom);
        this.pnMain.setLeftTopContent(cdLeftTop);
        this.pnMain.setBottomLeftContentModel(bottomLeftContentModel).initializeBottomLeftController();
        ((PNMainContentInitializer) this.pnMain.setBottomRightModel(bottomRightTransition)).initializeRightController(basicPetrinetPanel, guardActionPanel, petrinetPlace);
    }
}
