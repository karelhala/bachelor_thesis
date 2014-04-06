/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram;

import BT.managers.DiagramPlacesManager;
import BT.modules.ClassDiagram.mainContent.CDMainContentController;
import GUI.BottomRightContentModel;
import GUI.MainContentModel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

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
        CDMainContentController cdMain = new CDMainContentController(diagramPlaces, cdBottomRightContent);

        CDLeftTopContent cdLeftTop = new CDLeftTopContent(cdMain);
        cdLeftTop.setListeners();

        CDLeftBottomContent cdLeftBottom = new CDLeftBottomContent(cdMain);
        cdLeftBottom.setListeners();

        JPanel additional = new JPanel();
        additional.setBackground(Color.yellow);
        cdBottomRightContent.setAdditionalContent(additional);
        cdBottomRightContent.addAdditionalcontent();
        this.cdContent.setCenterPane(cdMain.getMainContent().getMainContentPane());
        this.cdContent.setLeftTopPane(cdLeftTop.getMainContentPane());
        this.cdContent.setLeftBottomPane(cdLeftBottom.getMainContentPane());
        this.cdContent.setBottomRightPane(cdBottomRightContent.getContentPane());
        cdMain.setLeftBottomContent(cdLeftBottom);
        cdMain.setLeftTopContent(cdLeftTop);
    }
}
