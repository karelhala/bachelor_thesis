/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ObjectedOrientedPetriNet.mainContent;

import BT.interfaces.DrawingClicks;
import BT.managers.ObjectChecker;
import BT.models.CoordinateModel;
import BT.modules.ObjectedOrientedPetriNet.places.PNPlace;
import BT.modules.ObjectedOrientedPetriNet.places.PNTransition;
import BT.modules.ObjectedOrientedPetriNet.places.joinEdge.PNJoinEdgeController;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

/**
 *
 * @author Karel
 */
public class PNMainContentController extends PNMainContentModel implements DrawingClicks{

    /**
     * 
     */
    public PNMainContentController()
    {
        super();
    }
    
    /**
     * 
     * @param evt 
     */
    @Override
    public void drawingPanecheckMove(MouseEvent evt) {
        ObjectChecker objectChecker = new ObjectChecker(this.mainContent.getDrawingPane().getPlaces());
        CoordinateModel coordObject = objectChecker.getObjectUnderMouse(evt.getPoint());
        PNDrawingPane PNdrawing = this.mainContent.getDrawingPane();
        if (this.newJoinEdge != null)
        {
            if (this.newJoinEdge.getSecondObject() == null)
            {
                this.newJoinEdge.setEndPoint(evt.getPoint());
            }
            PNdrawing.setNewLine(this.newJoinEdge);
        }
        if (coordObject != null)
        {
            coordObject.setHowerColor();
        }
        PNdrawing.getDrawing().repaint();
    }

    /**
     * 
     * @param pressedObject 
     */
    @Override
    public void drawingPaneDoubleCliked(CoordinateModel pressedObject) {
        if (pressedObject != null)
        {
            String name = (String) JOptionPane.showInputDialog("Enter name of the object",pressedObject.getName());
            if (name!= null && !"".equals(name))
                pressedObject.setName(name);
            this.mainContent.getDrawingPane().getDrawing().repaint();
        }
    }

    /**
     * 
     * @param evt 
     */
    @Override
    public void drawingPaneClicked(MouseEvent evt) {
        ObjectChecker objectChecker = new ObjectChecker(this.mainContent.getDrawingPane().getPlaces());
        CoordinateModel coordObject = objectChecker.getObjectUnderMouse(evt.getPoint());
        if (coordObject == null)
        {
            JToggleButton selectedItemButton = this.LeftTopContent.getSelectedButton();
            if (selectedItemButton != null && "PLACE".equals(selectedItemButton.getName()))
            {
                this.places.setAllObjectDiselected();
                PNPlace newPlace = new PNPlace(evt.getX(), evt.getY());
                this.places.addObject(newPlace);
            }
            else if (selectedItemButton != null && "TRANSITION".equals(selectedItemButton.getName()))
            {
                this.places.setAllObjectDiselected();
                PNTransition newTrasition = new PNTransition(evt.getX(), evt.getY());
                this.places.addObject(newTrasition);
            }
            else
            {
                this.places.setAllObjectDiselected();
                deleteNewLine();
            }
        }
        else if (coordObject instanceof PNPlace || coordObject instanceof PNTransition)
        {
            coordObject.setSelected(Boolean.TRUE);
            if (this.LeftBottomContent.getSelectedButton() != null)
            {
                drawJoinEdge(coordObject);
            }
        }
        this.mainContent.getDrawingPane().getDrawing().repaint();
    }

    /**
     * 
     * @param e
     * @param dragged 
     */
    @Override
    public void drawingMouseDragged(MouseEvent e, CoordinateModel dragged) {
        if (dragged instanceof PNPlace || dragged instanceof PNTransition)
        {
            dragged.setX(e.getX());
            dragged.setY(e.getY());
        }
        else if (dragged != null)
        {
            PNJoinEdgeController draggedJoinEdge = (PNJoinEdgeController) dragged;
            if (!draggedJoinEdge.isInRange(e.getX(), e.getY()))
            {
                removeLineFromArrayListAndSetNewLine(draggedJoinEdge);
                drawingPanecheckMove(e);
            }
        }
        this.mainContent.getDrawingPane().getDrawing().repaint();
    }

    /**
     * 
     * @param clickedObject 
     */
    @Override
    public void setSelectedObject(CoordinateModel clickedObject) {
        this.places.setAllObjectDiselected();
        places.setSelectedLinesOnObject(clickedObject);
        if (clickedObject!=null)
        {
            clickedObject.setSelected(true);
        }
        this.mainContent.getDrawingPane().getDrawing().repaint();
    }

    /**
     * 
     */
    @Override
    public void buttonsChanged() {
        System.out.println("Generated method"); //To change body of generated methods, choose Tools | Templates.
    }
    
        /**
     * 
     * @param clickedObject 
     */
    public void drawJoinEdge(CoordinateModel clickedObject)
    {
        this.newJoinEdge = createJoinEdge(this.newJoinEdge,clickedObject);

        if (this.newJoinEdge.getFirstObject()!= null && this.newJoinEdge.getSecondObject() != null)
        {
            if (this.newJoinEdge.getFirstObject().equals(clickedObject))
            {
                this.newJoinEdge.setSelected(true);
            }
            else
            {
                this.newJoinEdge.setSelected(false);
            }
            this.places.addObject(this.newJoinEdge);
            this.newJoinEdge = null;
        }
        PNDrawingPane cdDrawing = this.mainContent.getDrawingPane();
        cdDrawing.setNewLine(newJoinEdge);
    }
    
    /**
     * 
     * @param joinEdge
     * @param clickedObject 
     * @return  
     */
    private PNJoinEdgeController createJoinEdge(PNJoinEdgeController joinEdge, CoordinateModel clickedObject)
    {   
        if (joinEdge == null)
        {
            joinEdge = new PNJoinEdgeController();
        }
        if (joinEdge.getFirstObject() == null)
        {
            joinEdge.setFirstObject(clickedObject);
        }
        else if (joinEdge.getSecondObject() == null && !joinEdge.getFirstObject().getClass().equals(clickedObject.getClass()))
        {
            joinEdge.setSecondObject(clickedObject);
        }       
        return joinEdge;
    }
    
    
}
