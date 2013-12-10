/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.mainContent;

import BT.managers.CD.CDPlaceManager;
import BT.managers.UC.UCPlaceManager;
import BT.modules.ClassDiagram.CDLeftBottomContent;
import BT.modules.ClassDiagram.CDLeftTopContent;
import BT.modules.ClassDiagram.CDMainContent;
import BT.modules.UC.UCMainContent;
import BT.modules.UC.mainContent.UCDrawingListeners;
import BT.modules.UC.mainContent.UCDrawingPane;
import BT.modules.UC.mainContent.UCMainContentController;

/**
 *
 * @author Karel Hala
 */
class CDMainContentModel {
    protected CDMainContent mainContent;
    protected CDLeftBottomContent LeftBottomContent;
    protected CDPlaceManager places;
    protected CDLeftTopContent LeftTopContent;

    public CDMainContentModel()
    {
        this.places = new CDPlaceManager();
        this.mainContent = new CDMainContent(places);
        createMainPane();
    }
    
    public CDMainContent getMainContent() {
        return mainContent;
    }

    public CDLeftBottomContent getLeftBottomContent() {
        return LeftBottomContent;
    }

    public CDLeftTopContent getLeftTopContent() {
        return LeftTopContent;
    }

    public void setMainContent(CDMainContent mainContent) {
        this.mainContent = mainContent;
    }

    public void setLeftBottomContent(CDLeftBottomContent LeftBottomContent) {
        this.LeftBottomContent = LeftBottomContent;
    }

    public void setLeftTopContent(CDLeftTopContent LeftTopContent) {
        this.LeftTopContent = LeftTopContent;
    }
    
    private void createMainPane()
    {   
        CDDrawingPane UCdrawing = this.mainContent.getDrawingPane();
//        UCDrawingListeners alpha = new UCDrawingListeners((UCMainContentController) this);
//        UCdrawing.getDrawing().addMouseMotionListener(alpha);
//        UCdrawing.getDrawing().addMouseListener(alpha);
//        setButtonsListeners();
    }
}
