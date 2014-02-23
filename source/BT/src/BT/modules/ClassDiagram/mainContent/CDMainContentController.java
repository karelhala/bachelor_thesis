/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.mainContent;

import BT.interfaces.DrawingClicks;
import BT.managers.ObjectChecker;
import BT.models.CoordinateModel;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.UC.mainContent.UCDrawingPane;
import BT.modules.UC.mainContent.UCJoinEdgeManipulator;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

/**
 *
 * @author Karel Hala
 */
public class CDMainContentController extends CDMainContentModel implements DrawingClicks{

    /**
     * 
     */
    public CDMainContentController()
    {
        super();
    }
    
    /**
     * 
     * @param e
     * @param dragged 
     */
    @Override
    public void drawingMouseDragged(MouseEvent e, CoordinateModel dragged)
    {
        if (dragged != null)
        {
            dragged.setX(e.getX());
            dragged.setY(e.getY());
            this.mainContent.getDrawingPane().getDrawing().repaint();
        }
    }
    
    /**
     * 
     * @param evt 
     */
    @Override
    public void drawingPaneClicked(MouseEvent evt) 
    {
        ObjectChecker objectChecker = new ObjectChecker(this.mainContent.getDrawingPane().getPlaces());
        CoordinateModel coordObject = objectChecker.getObjectUnderMouse(evt.getPoint());
        if (coordObject == null)
        {
            JToggleButton selectedItemButton = this.LeftTopContent.getSelectedButton();
            if (selectedItemButton != null && "ACTOR".equals(selectedItemButton.getName()))
            {
                this.places.setAllObjectDiselected();
                this.places.addObject(new CDClass(evt.getX(), evt.getY()));
            }
            else
            {
                this.places.setAllObjectDiselected();
            }
        }
        else
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
     * @param evt 
     */
    @Override
    public void drawingPanecheckMove(MouseEvent evt) 
    {
        ObjectChecker objectChecker = new ObjectChecker(this.mainContent.getDrawingPane().getPlaces());
        CoordinateModel coordObject = objectChecker.getObjectUnderMouse(evt.getPoint());
        CDDrawingPane UCdrawing = this.mainContent.getDrawingPane();
        if (this.newJoinEdge != null)
        {
            if (this.newJoinEdge.getSecondObject() == null)
            {
                this.newJoinEdge.setEndPoint(evt.getPoint());
            }
            UCdrawing.setNewLine(this.newJoinEdge);
        }
        if (coordObject != null)
        {
            coordObject.setHowerColor();
        }
        UCdrawing.getDrawing().repaint();
    }

    /**
     * 
     * @param clickedObject 
     */
    @Override
    public void setSelectedObject(CoordinateModel clickedObject) 
    {
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
     * @param pressedObject 
     */
    @Override
    public void drawingPaneDoubleCliked(CoordinateModel pressedObject) 
    {
        if (pressedObject != null)
        {
            String name = (String) JOptionPane.showInputDialog("Enter name of the object",pressedObject.getName());
            if (name!= null && !"".equals(name))
                pressedObject.setName(name);
            this.mainContent.getDrawingPane().getDrawing().repaint();
        }
    }
    
    @Override
    public void buttonsChanged() {
    }
    
    public void drawJoinEdge(CoordinateModel clickedObject)
    {
        this.newJoinEdge = CDJoinEdgeManipulator.createJoinEdge(this.newJoinEdge,clickedObject);
        CDJoinEdgeManipulator.changeLineTypeByButton(this.LeftBottomContent.getSelectedButton(),this.newJoinEdge);

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
        CDDrawingPane cdDrawing = this.mainContent.getDrawingPane();
        cdDrawing.setNewLine(newJoinEdge);
    }
}
