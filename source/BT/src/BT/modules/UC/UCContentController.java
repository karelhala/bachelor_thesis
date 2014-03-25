/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC;

import BT.managers.DiagramPlacesManager;
import BT.managers.PlaceManager;
import BT.modules.UC.mainContent.UCMainContentController;
import GUI.MainContentModel;

/**
 *
 * @author Karel Hala
 */
public class UCContentController {

    private MainContentModel UCContent;

    /**
     *
     */
    public UCContentController() {
        this.UCContent = new MainContentModel();
    }

    /**
     *
     * @return
     */
    public MainContentModel getUCContent() {
        return this.UCContent;
    }

    /**
     *
     * @param diagramPlaces
     */
    public void createComponents(DiagramPlacesManager diagramPlaces) {
        UCMainContentController UCmain = new UCMainContentController(diagramPlaces);

        UCLeftTopContent UCLeftTop = new UCLeftTopContent(UCmain);
        UCLeftTop.setListeners();

        UCLeftBottomContent UCLeftBottom = new UCLeftBottomContent(UCmain);
        UCLeftBottom.setListeners();

        this.UCContent.setCenterPane(UCmain.getMainContent().getMainContentPane());
        this.UCContent.setLeftTopPane(UCLeftTop.getMainContentPane());
        this.UCContent.setLeftBottomPane(UCLeftBottom.getMainContentPane());
        UCmain.setLeftBottomContent(UCLeftBottom);
        UCmain.setLeftTopContent(UCLeftTop);
    }
}
