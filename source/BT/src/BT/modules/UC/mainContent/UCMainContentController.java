/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.mainContent;

import BT.BT.LineType;
import BT.models.CoordinateModel;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCJoinEdge;
import BT.managers.UC.UCPlaceManager;
import BT.modules.UC.UCLeftBottomContent;
import BT.modules.UC.places.UCUseCase;
import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import BT.modules.UC.UCMainContent;

/**
 *
 * @author Karel Hala
 */
public class UCMainContentController {
    private UCMainContent mainContent;
    private JToggleButton selectedItemButton;
    private JToggleButton selectedJoinEdgeButton;
    private UCPlaceManager places;
    private UCJoinEdge newJoinEdge;
    private UCLeftBottomContent buttonPane;
    
    /**
     * 
     */
    public UCMainContentController()
    {
        this.places = new UCPlaceManager();
        this.mainContent = new UCMainContent(places);
    }
    
    /**
     * 
     * @param buttonPane 
     */
    public void setButtonPane(UCLeftBottomContent buttonPane)
    {
        this.buttonPane = buttonPane;
    }
    
    /**
     * 
     * @return 
     */
    public UCMainContent getMainContent ()
    {
        return this.mainContent;
    }
    
    /**
     * 
     */
    public void createMainPane()
    {   
        UCDrawingPane UCdrawing = this.mainContent.getDrawingPane();
        UCDrawingListeners alpha = new UCDrawingListeners(UCdrawing.getDrawing(), this);
        UCdrawing.getDrawing().addMouseMotionListener(alpha);
        UCdrawing.getDrawing().addMouseListener(alpha);
    }

    /**
     * 
     * @param toggleButton 
     */
    public void setSelectedItemButton(JToggleButton toggleButton) {
        this.selectedItemButton = toggleButton;
    }
    
    /**
     * 
     * @param selectedButton 
     */
    public void setSelectedJoinEdgeButton(JToggleButton selectedButton) {
        this.selectedJoinEdgeButton = selectedButton;
        if (selectedButton == null)
        {
            UCDrawingPane UCdrawing = this.mainContent.getDrawingPane();
            this.newJoinEdge = null;
            UCdrawing.setNewLine(null);
            newJoinEdge = null;
            UCdrawing.getDrawing().repaint();
        }
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
        else if (dragged instanceof UCJoinEdge)
        {
            UCJoinEdge draggedJoinEdge = (UCJoinEdge) dragged;
            if (!draggedJoinEdge.isInRange(e.getX(), e.getY()))
            {
                if (this.selectedJoinEdgeButton == null)
                {
                    this.buttonPane.getButtonWithName(LineType.ASSOCIATION.name()).setSelected(true);
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
        if (this.selectedItemButton!=null){
            UCDrawingPane UCdrawing = this.mainContent.getDrawingPane();
            switch (this.selectedItemButton.getName()){
                case "actor":
                        UCActor actor = new UCActor();
                        actor.setX(evt.getX());
                        actor.setY(evt.getY());
                        this.places.addActor(actor);
                        UCdrawing.setPlaces(places);
                        
                        UCdrawing.getDrawing().repaint();
                        System.out.println(this.selectedItemButton.getText());
                     break;
                    
               case "useCase":
                        UCUseCase useCase = new UCUseCase();
                        useCase.setX(evt.getX());
                        useCase.setY(evt.getY());
                        this.places.addUseCase(useCase);
                        UCdrawing.setPlaces(places);
                        
                        UCdrawing.getDrawing().repaint();
                        System.out.println(this.selectedItemButton.getText());
                     break;
                }
        }
    }
    
    /**
     * 
     * @param clickedObject 
     */
    public void drawJoinEdge(CoordinateModel clickedObject)
    {
        UCDrawingPane UCdrawing = this.mainContent.getDrawingPane();
        if (this.newJoinEdge == null)
        {
            this.newJoinEdge = new UCJoinEdge();
            UCdrawing.setNewLine(newJoinEdge);
        }
        createJoinEdge(clickedObject);
        switch (this.selectedJoinEdgeButton.getName())
        {
           case "association":
                    System.out.println(this.selectedJoinEdgeButton.getText());
                 break;

           case "uses":  
                    System.out.println(this.selectedJoinEdgeButton.getText());
                 break;

           case "extend":  
                    System.out.println(this.selectedJoinEdgeButton.getText());
                 break;
        }

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
            places.addJoinEdge(this.newJoinEdge);
            UCdrawing.setNewLine(null);
            this.newJoinEdge = null;
        }
    }
    
    /**
     * 
     * @param clickedObject 
     */
    public void clickedOnObject(CoordinateModel clickedObject) {
        if (clickedObject == null || clickedObject instanceof UCJoinEdge)
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
        int x = evt.getX();
        int y = evt.getY();
        if (this.newJoinEdge != null)
        {
            if (this.newJoinEdge.getSecondObject() == null)
            {
                this.newJoinEdge.setMouseCoordinates(x, y);
            }
            UCdrawing.setNewLine(this.newJoinEdge);
        }
        UCActor actor = isActorUnderMouse(x, y);
        if (actor != null)
        {
            actor.setColor(Color.red);
        }
        UCUseCase usecase = isUseCaseUnderMouse(x, y);
        if (usecase != null)
        {
            usecase.setColor(Color.green);
        }
        
        UCJoinEdge joinEdge = isJoinEdgeUnderMouse(x, y);
        if (joinEdge != null && actor == null && usecase == null)
        {
            joinEdge.setColor(Color.ORANGE);
        }
        UCdrawing.getDrawing().repaint();
    }
    
    /**
     * 
     * @param x
     * @param y
     * @return 
     */
    public UCActor isActorUnderMouse(int x, int y)
    {
        for (UCActor actor : places.getActors())
        {
            if (actor.isActor(x,y))
            {
                return actor;
            }
            else
            {
                actor.setBasicColor();
            }
        }
        return null;
    }

    /**
     * 
     * @param x
     * @param y
     * @return 
     */
    public UCJoinEdge isJoinEdgeUnderMouse(int x, int y)
    {
        for (UCJoinEdge joinEdge : places.getJoinEdges())
        {
            if (joinEdge.isInRange(x,y))
            {
                return joinEdge;
            }
            else
            {
                joinEdge.setBasicColor();
            }
        }
        return null;
    }
            
    /**
     * 
     * @param x
     * @param y
     * @return 
     */
    public UCUseCase isUseCaseUnderMouse(int x, int y) {
       for (UCUseCase useCase : places.getUseCases())
        {
            if (useCase.isUseCase(x,y))
            {
                return useCase;
            }
            else
            {
                useCase.setBasicColor();
            }
        }
        return null;
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

        if (this.selectedJoinEdgeButton!=null)
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
    public void removeLineFromArrayListAndSetNewLine(UCJoinEdge joinEdge) {
        this.newJoinEdge = new UCJoinEdge();
        this.newJoinEdge.setFirstObject(joinEdge.getfirstObject());
        this.places.removeJointEdge(joinEdge);
    }
}
