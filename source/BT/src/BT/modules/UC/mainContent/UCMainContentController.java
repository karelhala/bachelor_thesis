/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.mainContent;

import BT.BT.UCLineType;
import BT.models.CoordinateModel;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCJoinEdge.UCJoinEdgeController;
import BT.managers.UC.UCPlaceManager;
import BT.modules.UC.UCLeftBottomContent;
import BT.modules.UC.UCLeftTopContent;
import BT.modules.UC.places.UCUseCase;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import BT.modules.UC.UCMainContent;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author Karel Hala
 */
public class UCMainContentController extends UCMainContentModel{

    
    /**
     * 
     */
    public UCMainContentController()
    {
        super();
    }
    
    /**
     * 
     */
    public void buttonsChanged()
    {
        JToggleButton selectedJoinEdgeButton = this.LeftBottomContent.getSelectedButton();
        UCDrawingPane UCdrawing = this.mainContent.getDrawingPane();
        if (selectedJoinEdgeButton == null)
        {
            this.newJoinEdge = null;
            UCdrawing.setNewLine(null);
        }
        else if (this.newJoinEdge != null)
        {
            changeLineType(selectedJoinEdgeButton, this.newJoinEdge);
        }
        UCdrawing.getDrawing().repaint();
    }
    
    /**
     * 
     * @param e
     * @param dragged 
     */
    public void mouseDragged(MouseEvent e, CoordinateModel dragged)
    {
        UCDrawingPane UCdrawing = this.mainContent.getDrawingPane();
        if (dragged instanceof UCActor || dragged instanceof UCUseCase)
        {
            if (this.newJoinEdge != null)
            {
                this.newJoinEdge = null;
                UCdrawing.setNewLine(null);
            }
            dragged.setX(e.getX());
            dragged.setY(e.getY());
        }
        else if (dragged instanceof UCJoinEdgeController)
        {
            UCJoinEdgeController draggedJoinEdge = (UCJoinEdgeController) dragged;
            if (!draggedJoinEdge.isInRange(e.getX(), e.getY()))
            {
                if (this.LeftBottomContent.getSelectedButton() == null)
                {
                    this.LeftBottomContent.getButtonWithName(draggedJoinEdge.getJoinEdgeType().name()).setSelected(true);
                }
                removeLineFromArrayListAndSetNewLine(draggedJoinEdge);
                drawingPanecheckMove(e);
            }
        }
        UCdrawing.getDrawing().repaint();
    }
    
    /**
     * 
     * @param evt 
     */
    public void drawingPaneClicked(MouseEvent evt) {
        JToggleButton selectedItemButton = this.LeftTopContent.getSelectedButton();
        if (selectedItemButton!=null){
            UCDrawingPane UCdrawing = this.mainContent.getDrawingPane();
            switch (selectedItemButton.getName()){
                case "ACTOR":
                        UCActor actor = new UCActor(evt.getX(), evt.getY());
                        this.places.addObject(actor);
                     break;
                    
               case "USECASE":
                        UCUseCase useCase = new UCUseCase(evt.getX(), evt.getY());
                        this.places.addObject(useCase);
                     break;
                }
            UCdrawing.getDrawing().repaint();
        }
    }
    
        /**
     * 
     * @param selectedJoinEdgeButton 
     */
    private void changeLineType(JToggleButton selectedJoinEdgeButton, UCJoinEdgeController joinEdge)
    {
        switch (selectedJoinEdgeButton.getName())
        {
           case "ASSOCIATION":
                    joinEdge.setJoinEdgeType(UCLineType.ASSOCIATION);
                 break;

           case "USES":  
                    joinEdge.setJoinEdgeType(UCLineType.USES);
                 break;

           case "EXTENDS":  
                    joinEdge.setJoinEdgeType(UCLineType.EXTENDS);
                 break;
        }
    }
    
    /**
     * 
     * @param clickedObject 
     */
    public void drawJoinEdge(CoordinateModel clickedObject)
    {
        UCDrawingPane UCdrawing = this.mainContent.getDrawingPane();
        JToggleButton selectedJoinEdgeButton = this.LeftBottomContent.getSelectedButton();
        if (this.newJoinEdge == null)
        {
            this.newJoinEdge = new UCJoinEdgeController();
            UCdrawing.setNewLine(newJoinEdge);
        }
        createJoinEdge(clickedObject);
        changeLineType(selectedJoinEdgeButton, newJoinEdge);

        if (this.newJoinEdge.getfirstObject() != null && this.newJoinEdge.getSecondObject() != null)
        {
            if (this.newJoinEdge.getfirstObject().equals(clickedObject))
            {
                this.newJoinEdge.setSelected(true);
            }
            else
            {
                this.newJoinEdge.setSelected(false);
            }
            this.places.addObject(this.newJoinEdge);
            UCdrawing.setNewLine(null);
            this.newJoinEdge = null;
        }
    }
    
    
    /**
     * 
     * @param clickedObject 
     */
    public void clickedOnObject(CoordinateModel clickedObject) {
        if (clickedObject == null || clickedObject instanceof UCJoinEdgeController)
        {
            this.newJoinEdge = null;
            this.mainContent.getDrawingPane().setNewLine(null);
        }
        else
        {
            if (this.newJoinEdge!=null && this.newJoinEdge.getfirstObject().equals(clickedObject))
            {
                this.newJoinEdge = null;
            }
            drawJoinEdge(clickedObject);
        }
    }
    
    /**
     * 
     * @param clickedObject 
     */
    public void createJoinEdge(CoordinateModel clickedObject)
    {        
        if (this.newJoinEdge.getfirstObject() == null)
        {
            this.newJoinEdge.setFirstObject(clickedObject);
        }
        else if (this.newJoinEdge.getSecondObject() == null)
        {
            this.newJoinEdge.setSecondObject(clickedObject);
        }       
        
    }
    
    /**
     * 
     * @param evt 
     */
    public void drawingPanecheckMove(MouseEvent evt) {
        UCDrawingPane UCdrawing = this.mainContent.getDrawingPane();
        if (this.newJoinEdge != null)
        {
            if (this.newJoinEdge.getSecondObject() == null)
            {
                this.newJoinEdge.setMouseCoordinates(evt.getX(), evt.getY());
            }
            UCdrawing.setNewLine(this.newJoinEdge);
        }
        UCObjectChecker objectUnderMouse = new UCObjectChecker(places);
        CoordinateModel coordModel = objectUnderMouse.getObjectUnderMouse(evt.getPoint());
        if (coordModel != null)
        {
            coordModel.setHowerColor();
        }
        
        UCdrawing.getDrawing().repaint();
    }
    
    /**
     * 
     * @param pressedObject 
     */
    public void objectDoubleClicked(CoordinateModel pressedObject) {
        String name = (String) JOptionPane.showInputDialog("Enter name of the object",pressedObject.getName());
        if (name!= null && !"".equals(name))
            pressedObject.setName(name);
        this.mainContent.getDrawingPane().getDrawing().repaint();
    }

    /**
     * 
     * @param clickedObject 
     */
    public void setSelectedObject(CoordinateModel clickedObject) {
        this.places.setAllObjectDiselected();
        places.setSelectedLinesOnObject(clickedObject);
        if (clickedObject!=null)
        {
            clickedObject.setSelected(true);
        }

        if (this.LeftBottomContent.getSelectedButton()!=null)
        {
            clickedOnObject(clickedObject);
        }
        this.mainContent.getDrawingPane().getDrawing().repaint();
    }
    
    /**
     * 
     */
    public void deselectAllObjectsAndRepaint()
    {
        this.places.setAllObjectDiselected();
        this.mainContent.getDrawingPane().getDrawing().repaint();
    }

    /**
     * 
     * @param joinEdge 
     */
    public void removeLineFromArrayListAndSetNewLine(UCJoinEdgeController joinEdge) {
        this.newJoinEdge = new UCJoinEdgeController();
        this.newJoinEdge.setFirstObject(joinEdge.getfirstObject());
        this.places.removeJoinEdge(joinEdge);
    }
}
