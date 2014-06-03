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
 * ClassDiagram content controller. This class creates every part of main content of class diagram part.
 *
 * @author Karel Hala
 */
public class CDContentController {

    /**
     * Model that contains main content data.
     */
    private final MainContentModel cdContent;

    /**
     * Basic constructor
     */
    public CDContentController() {
        this.cdContent = new MainContentModel();
    }

    public MainContentModel getCdContent() {
        return cdContent;
    }

    /**
     * Call this to create every part of class diagram main content. It will cereate BottomRightContent, then it will
     * create attributes pane, and bottom left model. It will create main controller and pass him models and
     * diagramPlaces.
     *
     * @param diagramPlaces stores every part of diagrams.
     */
    public void createComponents(DiagramPlacesManager diagramPlaces) {
        BottomRightContentModel cdBottomRightContent = new BottomRightContentModel();
        cdBottomRightContent.setButtonNames("Delete all inactive", "Reactivate all inactive");
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
