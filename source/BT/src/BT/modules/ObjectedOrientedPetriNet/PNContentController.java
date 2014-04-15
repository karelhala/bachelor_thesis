/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ObjectedOrientedPetriNet;

import BT.managers.DiagramPlacesManager;
import BT.modules.ObjectedOrientedPetriNet.mainContent.PNMainContentController;
import GUI.BottomLeftContentModel;
import GUI.MainContentModel;

/**
 *
 * @author Karel
 */
public class PNContentController {

    private MainContentModel pnContent;
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
        this.pnMain = new PNMainContentController(diagramPlaces, bottomLeftContentModel);

        PNLeftTopContent cdLeftTop = new PNLeftTopContent(pnMain);
        cdLeftTop.setListeners();

        PNLeftBottomContent pnLeftBottom = new PNLeftBottomContent(pnMain);
        pnLeftBottom.setListeners();

        this.pnContent.setCenterPane(this.pnMain.getMainContent().getMainContentPane());
        this.pnContent.setLeftTopPane(cdLeftTop.getMainContentPane());
        this.pnContent.setLeftBottomPane(pnLeftBottom.getMainContentPane());
        this.pnContent.setBottomLeftPane(bottomLeftContentModel.getMainPane());
        this.pnMain.setLeftBottomContent(pnLeftBottom);
        this.pnMain.setLeftTopContent(cdLeftTop);
    }
}
