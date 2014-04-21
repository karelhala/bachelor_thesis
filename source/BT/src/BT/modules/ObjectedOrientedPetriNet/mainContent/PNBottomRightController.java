/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ObjectedOrientedPetriNet.mainContent;

import GUI.BasicPetrinetPanel;
import GUI.BottomRightContentModel;
import GUI.MethodLabel;
import GUI.PetrinetGuardActionPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Karel Hala
 */
public class PNBottomRightController {
    
    /**
     * 
     */
    private final BottomRightContentModel bottomRightModel;
    
    /**
     * 
     */
    private MethodLabel selectedMethod;
    
    /**
     * 
     */
    private BasicPetrinetPanel basicPetrinetPanel;
    
    /**
     * 
     */
    private PetrinetGuardActionPanel petrinetGuardAction;
    
    /**
     * 
     * @param bottomRightModel 
     */
    public PNBottomRightController(BottomRightContentModel bottomRightModel)
    {
        this.bottomRightModel = bottomRightModel;
    }

    PNBottomRightController(BottomRightContentModel bottomRightModel, BasicPetrinetPanel petrinetPanel, PetrinetGuardActionPanel petrinetGuardAction) {
        this.basicPetrinetPanel = petrinetPanel;
        this.petrinetGuardAction = petrinetGuardAction;
        this.bottomRightModel = bottomRightModel;
    }

    /**
     * Getter for selected method of right content Panel.
     * @return 
     */
    public MethodLabel getSelectedMethod() {
        return selectedMethod;
    }

    /**
     * Setter for selected method of right content Panel.
     * @param selectedMethod
     * @return this object.
     */
    public PNBottomRightController setSelectedMethod(MethodLabel selectedMethod) {
        this.selectedMethod = selectedMethod;
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
        return this;
    }

    public BasicPetrinetPanel getBasicPetrinetPanel() {
        return basicPetrinetPanel;
    }

    public PetrinetGuardActionPanel getPetrinetGuardAction() {
        return petrinetGuardAction;
    }
}
