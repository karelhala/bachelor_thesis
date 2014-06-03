/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC;

import BT.managers.DiagramPlacesManager;
import BT.modules.UC.mainContent.UCMainContentController;
import GUI.BottomRightContentModel;
import GUI.MainContentModel;

/**
 * Content controller for useCases. It creates panels and objects of use case diagram.
 *
 * @author Karel Hala
 */
public class UCContentController {

    /**
     * Main content model for use case.
     */
    private final MainContentModel UCContent;

    /**
     * Basic constructor. It creates main content model for use case diagram.
     */
    public UCContentController() {
        this.UCContent = new MainContentModel();
    }

    /**
     * Returns main content model of use case diagram.
     *
     * @return MainContentModel with each part of window panel.
     */
    public MainContentModel getUCContent() {
        return this.UCContent;
    }

    /**
     * Creates components of use case diagram. Creates various panels and insert corrct panels in them.
     * 
     * @param diagramPlaces use case objects that are drawn on drawing panel.
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
