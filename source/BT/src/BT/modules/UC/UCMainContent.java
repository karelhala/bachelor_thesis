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
        JScrollPane myScrollPane = new JScrollPane();
        
        this.drawingPane.getDrawing().setBackground(Color.WHITE);
        this.drawingPane.getDrawing().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                drawingPaneClicked(evt);
            }

        });
        myScrollPane.setViewportView(drawingPane.getDrawing());
        drawingPane.getDrawing().repaint();
        this.mainContentPane.add(myScrollPane, BorderLayout.CENTER);
    }

    void setSelectedButton(JToggleButton toggleButton) {
        this.selectedButton = toggleButton;
    }
    
    private void drawingPaneClicked(MouseEvent evt) {
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
    
    private void drawingPanePressed(MouseEvent evt) {
        throw new UnsupportedOperationException("Not yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
