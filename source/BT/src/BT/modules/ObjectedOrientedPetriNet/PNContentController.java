/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ObjectedOrientedPetriNet;

import BT.managers.DiagramPlacesManager;
import BT.modules.ObjectedOrientedPetriNet.mainContent.PNDrawingPane;
import BT.modules.ObjectedOrientedPetriNet.mainContent.PNMainContentController;
import BT.modules.ObjectedOrientedPetriNet.mainContent.PNMainContentInitializer;
import GUI.BasicPetrinetPanel;
import GUI.BottomLeftContentModel;
import GUI.BottomRightContentModel;
import GUI.MainContentModel;
import GUI.PetrinetGuardActionPanel;
import GUI.PetrinetPlacePanel;

/**
 *
 * @author Karel
 */
public class PNContentController {

    private final MainContentModel pnContent;
    private PNMainContentController pnMain;

    /**
     *
     */
    public PNContentController() {
        this.pnContent = new MainContentModel();
    }

    public MainContentModel getPnContent() {
        return pnContent;
    }

    public PNMainContentController getPnMain() {
        return pnMain;
    }

    /**
     *
     * @param diagramPlaces
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
        ((PNMainContentInitializer)this.pnMain.setBottomRightModel(bottomRightTransition)).initializeRightController(basicPetrinetPanel, guardActionPanel, petrinetPlace);
    }
}
