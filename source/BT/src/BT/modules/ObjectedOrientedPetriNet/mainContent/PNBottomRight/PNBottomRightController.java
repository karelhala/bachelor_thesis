/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ObjectedOrientedPetriNet.mainContent.PNBottomRight;

import BT.managers.CD.Attribute;
import GUI.BasicPetrinetPanel;
import GUI.BottomRightContentModel;
import GUI.PetrinetGuardActionPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Karel Hala
 */
public class PNBottomRightController extends PNBottomRightModel{
    
    /**
     * 
     * @param bottomRightModel
     * @param petrinetPanel
     * @param petrinetGuardAction 
     */
    public PNBottomRightController(BottomRightContentModel bottomRightModel, BasicPetrinetPanel petrinetPanel, PetrinetGuardActionPanel petrinetGuardAction) {
        super(bottomRightModel, petrinetPanel, petrinetGuardAction);
        
    }
    
    /**
     * Load class attributes to combobox.
     * @return this object.
     */
    public PNBottomRightController loadAttributesToComboBox()
    {
        if (this.selectedClass != null)
        {
            for (Attribute oneAttribute : this.selectedClass.loadClassAttributes()) {
                this.basicPetrinetPanel.getClassAttributes().addItem(oneAttribute);
            }
        }
        if (this.selectedClass.loadClassAttributes().size() == 0)
        {
            this.basicPetrinetPanel.getClassAttributes().setEnabled(false);
        }
        return this;
    }
    
    /**
     * Initialize action listeners for top and bottom button.
     * @return this object.
     */
    public PNBottomRightController initializeButtonListeners()
    {
        bottomRightModel.getTopButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Generated method"); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        bottomRightModel.getBottomButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Generated method"); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        this.basicPetrinetPanel.getAddClassVariable().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("asd"); //To change body of generated methods, choose Tools | Templates.
            }
        });
        return this;
    }
}
