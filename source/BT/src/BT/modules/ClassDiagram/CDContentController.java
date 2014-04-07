/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram;

import BT.managers.DiagramPlacesManager;
import BT.modules.ClassDiagram.mainContent.CDMainContentController;
import GUI.BottomLeftContentModel;
import GUI.BottomRightContentModel;
import GUI.ClassDiagramAttributesPanel;
import GUI.MainContentModel;

/**
 *
 * @author Karel Hala
 */
public class CDContentController {

    private final MainContentModel cdContent;

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
        BottomRightContentModel cdBottomRightContent = new BottomRightContentModel();
        ClassDiagramAttributesPanel attributesPanel = new ClassDiagramAttributesPanel();
        BottomLeftContentModel bottomLeftContentModel = new BottomLeftContentModel();
        CDMainContentController cdMain = new CDMainContentController(diagramPlaces, cdBottomRightContent, attributesPanel, bottomLeftContentModel);

        CDLeftTopContent cdLeftTop = new CDLeftTopContent(cdMain);
        cdLeftTop.setListeners();

        CDLeftBottomContent cdLeftBottom = new CDLeftBottomContent(cdMain);
        cdLeftBottom.setListeners();
        
        cdBottomRightContent.setAdditionalContent(attributesPanel.getContentPanel());
        cdBottomRightContent.addAdditionalcontent();
        this.cdContent.setCenterPane(cdMain.getMainContent().getMainContentPane());
        this.cdContent.setLeftTopPane(cdLeftTop.getMainContentPane());
        this.cdContent.setLeftBottomPane(cdLeftBottom.getMainContentPane());
        this.cdContent.setBottomRightPane(cdBottomRightContent.getContentPane());
        this.cdContent.setBottomLeftPane(bottomLeftContentModel.getMainPane());
        cdMain.setLeftBottomContent(cdLeftBottom);
        cdMain.setLeftTopContent(cdLeftTop);
    }
}
