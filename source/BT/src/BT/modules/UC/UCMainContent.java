/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.UC;

import BT.managers.CoordinateManager;
import BT.managers.UC.UCActor;
import BT.managers.UC.UCPlaceManager;
import BT.managers.UC.UCUseCase;
import GUI.DrawingPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

/**
 *
 * @author Karel
 */
public final class UCMainContent {
    private JPanel mainContentPane;
    private JToggleButton selectedButton;
    private UCPlaceManager places;
    private DrawingPane drawingPane;
    
    public UCMainContent()
    {
        this.places = new UCPlaceManager();
        this.mainContentPane = new JPanel(new BorderLayout());
        this.drawingPane = new DrawingPane(this.places);
        createMainPane();
    }
    
    public JPanel getMainContentPane ()
    {
        return this.mainContentPane;
    }
    
    public void createMainPane()
    {   
        this.drawingPane.getDrawing().setBackground(Color.WHITE);
        UCDrawingListeners alpha = new UCDrawingListeners(this.drawingPane.getDrawing(), this);
        this.drawingPane.getDrawing().addMouseMotionListener(alpha);
        this.drawingPane.getDrawing().addMouseListener(alpha);
        drawingPane.getDrawing().repaint();
        JScrollPane myScrollPane = new JScrollPane(drawingPane.getDrawing());
        
        this.mainContentPane.add(myScrollPane, BorderLayout.CENTER);
    }

    void setSelectedButton(JToggleButton toggleButton) {
        this.selectedButton = toggleButton;
    }
    
    public void mouseDragged(MouseEvent e)
    {
        final CoordinateManager actor = isActorUnderMouse(e.getX(), e.getY());
        if (actor!=null){
            actor.setX(e.getX());
            actor.setY(e.getY());
            this.drawingPane.getDrawing().repaint();
        }
        else
        {
            final CoordinateManager useCase = isUseCaseUnderMouse(e.getX(), e.getY());
            if (useCase!= null)
            {
                useCase.setX(e.getX());
                useCase.setY(e.getY());
                this.drawingPane.getDrawing().repaint();
            }
        }

    }
    
    public void drawingPaneClicked(MouseEvent evt) {
        if (this.selectedButton!=null){
            switch (this.selectedButton.getName()){
                case "actor":
                        UCActor actor = new UCActor();
                        actor.setX(evt.getX());
                        actor.setY(evt.getY());
                        this.places.addActor(actor);
                        this.drawingPane.setPlaces(places);
                        
                        this.drawingPane.getDrawing().repaint();
                        System.out.println(this.selectedButton.getText());
                     break;
                    
               case "useCase":
                        UCUseCase useCase = new UCUseCase();
                        useCase.setX(evt.getX());
                        useCase.setY(evt.getY());
                        this.places.addUseCase(useCase);
                        this.drawingPane.setPlaces(places);
                        
                        this.drawingPane.getDrawing().repaint();
                        System.out.println(this.selectedButton.getText());
                     break;
                   
               case "association":  
                        System.out.println(evt.getX() + " " + evt.getY());
                        System.out.println(this.selectedButton.getText());
                     break;
                   
               case "include":  
                        System.out.println(evt.getX() + " " + evt.getY());
                        System.out.println(this.selectedButton.getText());
                     break;
                   
               case "extend":  
                        System.out.println(evt.getX() + " " + evt.getY());
                        System.out.println(this.selectedButton.getText());
                     break;
            }
        }
    }
    
    public void drawingPanecheckMove(MouseEvent evt) {
        UCActor actor = isActorUnderMouse(evt.getX(), evt.getY());
        if (actor != null)
        {
            actor.setColor(Color.red);
        }
        UCUseCase usecase = isUseCaseUnderMouse(evt.getX(), evt.getY());
        if (usecase != null)
        {
            usecase.setColor(Color.green);
        }
        this.drawingPane.getDrawing().repaint();
    }
    
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
}
