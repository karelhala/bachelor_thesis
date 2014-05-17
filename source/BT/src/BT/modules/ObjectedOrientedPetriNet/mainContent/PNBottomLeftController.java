/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ObjectedOrientedPetriNet.mainContent;

import BT.managers.CD.Attribute;
import BT.managers.CD.Method;
import BT.modules.ClassDiagram.places.CDClass;
import GUI.BottomLeftContentModel;
import GUI.MethodLabel;
import java.util.ArrayList;

/**
 *
 * @author Karel Hala
 */
public class PNBottomLeftController {

    /**
     *
     */
    private final BottomLeftContentModel bottomLeftModel;

    /**
     *
     */
    private MethodLabel drawnClass;

    /**
     *
     */
    private final ArrayList<MethodLabel> methodLabels;

    /**
     *
     * @param bottomLeftModel
     */
    public PNBottomLeftController(BottomLeftContentModel bottomLeftModel) {
        this.bottomLeftModel = bottomLeftModel;
        this.methodLabels = new ArrayList<>();
    }

    public MethodLabel getDrawnClass() {
        return drawnClass;
    }

    public ArrayList<MethodLabel> getMethodLabels() {
        return methodLabels;
    }

    /**
     * Method for repainting and setting listeners to class labels and method labels. This method will also destroy old
     * bottom left content and insert new items in it.
     *
     * @param selectedClass
     */
    public void repaintBottomLeftContent(CDClass selectedClass) {
        this.bottomLeftModel.destroyContent();
        this.drawnClass = new MethodLabel(selectedClass.getName(), selectedClass);
        this.bottomLeftModel.addClassLabelToPane(this.drawnClass);

        for (Attribute attr : selectedClass.getMethods()) {
            Method classMethod = (Method) attr;
            MethodLabel methodLabel = new MethodLabel(classMethod.getName(), classMethod);
            this.bottomLeftModel.addAttributesToPane(methodLabel);
            this.methodLabels.add(methodLabel);
        }
    }

    /**
     * Method that will clear all methods.
     */
    public void removeAllmethods() {
        if (this.methodLabels != null && !this.methodLabels.isEmpty()) {
            this.methodLabels.clear();
        }
    }

    /**
     * Get selected method label.
     *
     * @return either drawn class or one os method labels.
     */
    public MethodLabel getSelectedmethodLabel() {
        if (this.drawnClass != null) {
            if (this.drawnClass.isSelected()) {
                return drawnClass;
            }
        }

        for (MethodLabel methodLabel : methodLabels) {
            if (methodLabel.isSelected()) {
                return methodLabel;
            }
        }
        return null;
    }

    /**
     * Method for setting all method labels to false and repaint their color.
     */
    public void setAllMethodsNotSelectedAndRepaintAllMethods()
    {
        for (MethodLabel methodLabel : methodLabels) {
            methodLabel.setSelected(false);
            methodLabel.setBasicColor();
        }
    }
    /**
     * 
     * @param drawnClass 
     */
    public void setDrawnClass(MethodLabel drawnClass) {
        this.drawnClass = drawnClass;
    }
}
