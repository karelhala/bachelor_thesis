/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ObjectedOrientedPetriNet;

import BT.modules.ObjectedOrientedPetriNet.mainContent.PNMainContentController;
import GUI.MainContentModel;

/**
 *
 * @author Karel
 */
public class PNContentController {

    private MainContentModel pnContent;

    /**
     *
     */
    public PNContentController() {
        this.pnContent = new MainContentModel();
    }

    public MainContentModel getPnContent() {
        return pnContent;
    }

    /**
     *
     */
    public void createComponents() {
        PNMainContentController pnMain = new PNMainContentController();

        PNLeftTopContent cdLeftTop = new PNLeftTopContent(pnMain);
        cdLeftTop.setListeners();

        PNLeftBottomContent pnLeftBottom = new PNLeftBottomContent(pnMain);
        pnLeftBottom.setListeners();

        this.pnContent.setCenterPane(pnMain.getMainContent().getMainContentPane());
        this.pnContent.setLeftTopPane(cdLeftTop.getMainContentPane());
        this.pnContent.setLeftBottomPane(pnLeftBottom.getMainContentPane());
        pnMain.setLeftBottomContent(pnLeftBottom);
        pnMain.setLeftTopContent(cdLeftTop);
    }
}
