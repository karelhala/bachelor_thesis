/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.mainContent;

import BT.modules.ClassDiagram.CDLeftBottomContent;
import BT.modules.ClassDiagram.CDLeftTopContent;
import BT.modules.ClassDiagram.CDMainContent;

/**
 *
 * @author Karel Hala
 */
class CDMainContentModel {
    protected CDMainContent mainContent;
//    protected UCPlaceManager places;
//    protected UCJoinEdgeController newJoinEdge;
    protected CDLeftBottomContent LeftBottomContent;
    protected CDLeftTopContent LeftTopContent;

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
    
    
}
