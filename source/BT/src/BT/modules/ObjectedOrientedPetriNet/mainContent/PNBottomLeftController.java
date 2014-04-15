/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ObjectedOrientedPetriNet.mainContent;

import BT.managers.CD.Attribute;
import BT.modules.ClassDiagram.places.CDClass;
import GUI.BottomLeftContentModel;
import javax.swing.JLabel;

/**
 *
 * @author Karel Hala
 */
public class PNBottomLeftController {
    
    private BottomLeftContentModel bottomLeftModel;
    public PNBottomLeftController(BottomLeftContentModel bottomLeftModel)
    {
        this.bottomLeftModel = bottomLeftModel;
    }
    
    public void repaintBottomLeftContent(CDClass selectedClass)
    {
        this.bottomLeftModel.destroyContent();
        this.bottomLeftModel.addAttributesToPane(new JLabel(selectedClass.getName()));
        for (Attribute attr : selectedClass.getVariables()) {
            this.bottomLeftModel.addAttributesToPane(new JLabel(attr.getName()));
        }
        
        for (Attribute attr : selectedClass.getMethods()) {
            this.bottomLeftModel.addAttributesToPane(new JLabel(attr.getName()));
        }
    }
}