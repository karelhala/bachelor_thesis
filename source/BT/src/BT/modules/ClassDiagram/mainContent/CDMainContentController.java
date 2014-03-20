/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram.mainContent;

import BT.BT.ClassType;
import BT.interfaces.DrawingClicks;
import BT.managers.CD.Attribute;
import BT.managers.ObjectChecker;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import BT.modules.ClassDiagram.places.CDClass;
import BT.modules.ClassDiagram.places.joinEdge.CDJoinEdgeController;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
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
        CDDrawingPane cdDrawing = (CDDrawingPane) this.mainContent.getDrawingPane();
        if (dragged instanceof CDClass)
        {
            if (this.newJoinEdge != null)
            {
                this.newJoinEdge = null;
                cdDrawing.setNewLine(null);
            }
            dragged.setX(e.getX());
            dragged.setY(e.getY());
        }
        else if (dragged != null)
        {
            CDJoinEdgeController draggedJoinEdge = (CDJoinEdgeController) dragged;
            if (!draggedJoinEdge.isInRange(e.getX(), e.getY()) && this.newJoinEdge == null)
            {
                removeLineFromArrayListAndSetNewLine(draggedJoinEdge);
                drawingPanecheckMove(e);
            }
        }
        cdDrawing.getDrawing().repaint();
    }
    
    /**
     * 
     * @param evt 
     */
    @Override
    public void drawingPaneClicked(MouseEvent evt) 
    {
        JToggleButton selectedItemButton = this.LeftTopContent.getSelectedButton();
        if (selectedItemButton != null && "CLASS".equals(selectedItemButton.getName()))
        {
            this.places.setAllObjectDiselected();
            CDClass newClass = new CDClass(evt.getX(), evt.getY());
            newClass.addNewVariable(new Attribute(BT.BT.AttributeType.PUBLIC,"variable1", "String"));
            newClass.addNewVariable(new Attribute(BT.BT.AttributeType.PROTECTED,"variable1", "String"));
            newClass.addNewMethod(new Attribute("MethodOne()", "void"));
            this.places.addObject(newClass);
        }
        else
        {
            this.places.setAllObjectDiselected();
            deleteNewLine();
        }
        ((CDDrawingPane)this.mainContent.getDrawingPane()).getDrawing().repaint();
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
        CDDrawingPane UCdrawing = (CDDrawingPane) this.mainContent.getDrawingPane();
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
        
        if (this.LeftBottomContent.getSelectedButton()!=null || this.newJoinEdge != null)
        {
            clickedOnObject(clickedObject);
        }
        ((CDDrawingPane)this.mainContent.getDrawingPane()).getDrawing().repaint();
    }
    
    /**
     * 
     * @param pressedObject 
     */
    @Override
    public void drawingPaneDoubleCliked(CoordinateModel pressedObject) 
    {
        JTextField nameInput = new JTextField();
        JRadioButton actor = new JRadioButton("actor");
        JRadioButton activity = new JRadioButton("activity");
        ButtonGroup classTypeGroup = new ButtonGroup();
        classTypeGroup.add(activity);
        classTypeGroup.add(actor);
        JPanel dialogPanel = new JPanel(new BorderLayout());
        dialogPanel.add(nameInput, BorderLayout.PAGE_START);
        dialogPanel.add(actor, BorderLayout.LINE_START);
        dialogPanel.add(activity, BorderLayout.CENTER);
        if (pressedObject != null)
        {
            ClassType typeOfClass = ((CDClass)pressedObject).getTypeOfClass();
            nameInput.setText(pressedObject.getName());
            ((JRadioButton)((typeOfClass == ClassType.ACTIVITY)?activity:actor)).setSelected(true);
            int result = JOptionPane.showConfirmDialog(null, dialogPanel, 
               "Please Enter name of class and select type", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION)
            {
                pressedObject.setName(nameInput.getText());
                ((CDClass)pressedObject).setTypeOfClass((actor.isSelected() == true)?ClassType.ACTOR:ClassType.ACTIVITY);
            }
        }
    }
    
    /**
     * 
     */
    @Override
    public void buttonsChanged() {
        JToggleButton selectedJoinEdgeButton = this.LeftBottomContent.getSelectedButton();
        CDDrawingPane cdDrawing = (CDDrawingPane) this.mainContent.getDrawingPane();
        if (selectedJoinEdgeButton == null)
        {
            this.newJoinEdge = null;
            cdDrawing.setNewLine(null);
        }
        else if (this.newJoinEdge != null)
        {
            CDJoinEdgeManipulator.changeLineTypeByButton(this.LeftBottomContent.getSelectedButton(), (CDJoinEdgeController) this.newJoinEdge);
        }
        cdDrawing.getDrawing().repaint();
    }
    
    /**
     * 
     * @param clickedObject 
     */
    public void drawJoinEdge(CoordinateModel clickedObject)
    {
        this.newJoinEdge = CDJoinEdgeManipulator.createJoinEdge((CDJoinEdgeController) this.newJoinEdge,clickedObject);
        if (this.LeftBottomContent.getSelectedButton() != null)
        {
            CDJoinEdgeManipulator.changeLineTypeByButton(this.LeftBottomContent.getSelectedButton(), (CDJoinEdgeController) this.newJoinEdge);
        }

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
        CDDrawingPane cdDrawing = (CDDrawingPane) this.mainContent.getDrawingPane();
        cdDrawing.setNewLine(newJoinEdge);
    }

    /**
     * 
     * @param clickedObject 
     */
    private void clickedOnObject(CoordinateModel clickedObject) {
        if (clickedObject == null || clickedObject instanceof LineModel)
        {
            this.newJoinEdge = null;
            this.mainContent.getDrawingPane().setNewLine(null);
        }
        else
        {
            if (this.newJoinEdge!=null && this.newJoinEdge.getFirstObject().equals(clickedObject))
            {
                this.newJoinEdge = null;
            }
            drawJoinEdge(clickedObject);
        }
    }
}
