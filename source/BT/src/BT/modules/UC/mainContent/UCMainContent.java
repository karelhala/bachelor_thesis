/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC.mainContent;

import BT.BT;
import BT.BT.LineType;
import BT.models.CoordinateModel;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCJoinEdge;
import BT.managers.UC.UCPlaceManager;
import BT.modules.UC.UCLeftBottomContent;
import BT.modules.UC.places.UCUseCase;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

/**
 *
 * @author Karel Hala
 */
public final class UCMainContent {
    private JPanel mainContentPane;
    private JToggleButton selectedItemButton;
    private JToggleButton selectedJoinEdgeButton;
    private UCPlaceManager places;
    private UCDrawingPane drawingPane;
    private UCJoinEdge newJoinEdge;
    private UCLeftBottomContent buttonPane;
    
    /**
     * 
     */
    public UCMainContent()
    {
        this.places = new UCPlaceManager();
        this.mainContentPane = new JPanel(new BorderLayout());
        this.drawingPane = new UCDrawingPane(this.places);
        createMainPane();
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
    public JPanel getMainContentPane ()
    {
        return this.mainContentPane;
    }
    
    /**
     * 
     */
    public void createMainPane()
    {   
        this.drawingPane.getDrawing().setBackground(Color.WHITE);
        UCDrawingListeners alpha = new UCDrawingListeners(this.drawingPane.getDrawing(), this);
        this.drawingPane.getDrawing().addMouseMotionListener(alpha);
        this.drawingPane.getDrawing().addMouseListener(alpha);
        drawingPane.getDrawing().repaint();
        drawingPane.setButtonsListeners();
        JScrollPane myScrollPane = new JScrollPane(drawingPane.getDrawing());
        
        this.mainContentPane.add(myScrollPane, BorderLayout.CENTER);
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
            this.newJoinEdge = null;
            this.drawingPane.setNewLine(null);
            newJoinEdge = null;
            this.drawingPane.getDrawing().repaint();
        }
    }
    
    /**
     * 
     * @param e
     * @param dragged 
     */
    public void mouseDragged(MouseEvent e, CoordinateModel dragged)
    {
        if (dragged instanceof UCActor || dragged instanceof UCUseCase)
        {
            if (this.newJoinEdge != null)
            {
                this.newJoinEdge = null;
                this.drawingPane.setNewLine(null);
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
        this.drawingPane.getDrawing().repaint();
    }
    
    /**
     * 
     * @param evt 
     */
    public void drawingPaneClicked(MouseEvent evt) {
        if (this.selectedItemButton!=null){
            switch (this.selectedItemButton.getName()){
                case "actor":
                        UCActor actor = new UCActor();
                        actor.setX(evt.getX());
                        actor.setY(evt.getY());
                        this.places.addActor(actor);
                        this.drawingPane.setPlaces(places);
//                        objectClicked(actor);
                        
                        this.drawingPane.getDrawing().repaint();
                        System.out.println(this.selectedItemButton.getText());
                     break;
                    
               case "useCase":
                        UCUseCase useCase = new UCUseCase();
                        useCase.setX(evt.getX());
                        useCase.setY(evt.getY());
                        this.places.addUseCase(useCase);
                        this.drawingPane.setPlaces(places);
//                        objectClicked(useCase);
                        
                        this.drawingPane.getDrawing().repaint();
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
        if (this.newJoinEdge == null)
        {
            this.newJoinEdge = new UCJoinEdge();
            this.drawingPane.setNewLine(newJoinEdge);
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
            this.drawingPane.setNewLine(null);
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
            this.drawingPane.setNewLine(null);
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
        int x = evt.getX();
        int y = evt.getY();
        if (this.newJoinEdge != null)
        {
            if (this.newJoinEdge.getSecondObject() == null)
            {
                this.newJoinEdge.setMouseCoordinates(x, y);
            }
            this.drawingPane.setNewLine(this.newJoinEdge);
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
        this.drawingPane.getDrawing().repaint();
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
        this.drawingPane.getDrawing().repaint();
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
        this.drawingPane.getDrawing().repaint();
    }
    
    /**
     * 
     */
    public void deselectAllObjectsAndRepaint()
    {
        this.places.setAllObjectDiselected();
        this.drawingPane.getDrawing().repaint();
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
