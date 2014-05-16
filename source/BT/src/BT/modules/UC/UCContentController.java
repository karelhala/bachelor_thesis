/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC;

import BT.managers.DiagramPlacesManager;
import BT.modules.UC.mainContent.UCDrawingPane;
import BT.modules.UC.mainContent.UCMainContentController;
import GUI.BottomRightContentModel;
import GUI.MainContentModel;

/**
 *
 * @author Karel Hala
 */
public class UCContentController {

    private final MainContentModel UCContent;

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
        BottomRightContentModel ucBottomRightContent = new BottomRightContentModel();
        ucBottomRightContent.setButtonNames("Delete all inactive", "Reactivate all inactive");
        UCMainContentController UCmain = new UCMainContentController(diagramPlaces, ucBottomRightContent);

        UCLeftTopContent UCLeftTop = new UCLeftTopContent(UCmain);
        UCLeftTop.setListeners();

        UCLeftBottomContent UCLeftBottom = new UCLeftBottomContent(UCmain);
        UCLeftBottom.setListeners();

        this.UCContent.setCenterPane(UCmain.getMainContent().getMainContentPane());
        this.UCContent.setLeftTopPane(UCLeftTop.getMainContentPane());
        this.UCContent.setLeftBottomPane(UCLeftBottom.getMainContentPane());
        this.UCContent.setBottomRightPane(ucBottomRightContent.getContentPane());
        UCmain.setLeftBottomContent(UCLeftBottom);
        UCmain.setLeftTopContent(UCLeftTop);
    }
}
