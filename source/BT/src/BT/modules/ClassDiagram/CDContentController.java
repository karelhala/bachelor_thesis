/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram;

import BT.managers.DiagramPlacesManager;
import BT.managers.PlaceManager;
import BT.modules.ClassDiagram.mainContent.CDMainContentController;
import GUI.MainContentModel;

/**
 *
 * @author Karel Hala
 */
public class CDContentController {

    private MainContentModel cdContent;

    /**
     *
     */
    public CDContentController() {
        this.cdContent = new MainContentModel();
    }

    public MainContentModel getCdContent() {
        return cdContent;
    }

    /**
     *
     * @param diagramPlaces
     */
    public void createComponents(DiagramPlacesManager diagramPlaces) {
        CDMainContentController cdMain = new CDMainContentController(diagramPlaces);

        CDLeftTopContent cdLeftTop = new CDLeftTopContent(cdMain);
        cdLeftTop.setListeners();

        CDLeftBottomContent cdLeftBottom = new CDLeftBottomContent(cdMain);
        cdLeftBottom.setListeners();

        this.cdContent.setCenterPane(cdMain.getMainContent().getMainContentPane());
        this.cdContent.setLeftTopPane(cdLeftTop.getMainContentPane());
        this.cdContent.setLeftBottomPane(cdLeftBottom.getMainContentPane());
        cdMain.setLeftBottomContent(cdLeftBottom);
        cdMain.setLeftTopContent(cdLeftTop);
    }
}
