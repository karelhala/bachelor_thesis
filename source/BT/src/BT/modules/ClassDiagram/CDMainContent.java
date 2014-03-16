/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.modules.ClassDiagram;

import BT.managers.PlaceManager;
import BT.models.ContentPaneModel;
import BT.models.CoordinateModel;
import BT.modules.ClassDiagram.mainContent.CDDrawingPane;
import BT.modules.UC.places.UCActor;
import BT.modules.UC.places.UCUseCase;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Karel Hala
 */
public final class CDMainContent extends ContentPaneModel{
    
    private Dimension area;
    
    /**
     * 
     */
    public CDMainContent(PlaceManager places)
    {
        super();
        this.mainContentPane = new JPanel(new BorderLayout());
        this.drawingPane = new CDDrawingPane(places);
        this.area = new Dimension(0,0);
        createMainPane();
    }

    /**
     * 
     */
    private void createMainPane() {
        CDDrawingPane cdDrawingPane = (CDDrawingPane) this.drawingPane;
        cdDrawingPane.getDrawing().setPreferredSize(this.area);
        cdDrawingPane.getDrawing().setBackground(Color.WHITE);
        cdDrawingPane.getDrawing().repaint();
        JScrollPane myScrollPane = new JScrollPane(cdDrawingPane.getDrawing());
        this.mainContentPane.add(myScrollPane, BorderLayout.CENTER);
    }
    
    /**
     * 
     * @param newObject 
     */
    public void recalculateSize(CoordinateModel newObject)
    {
        Boolean changed = false;
        WidthHeight objectWidthAndHeight = getObjectWidthAndheight(newObject);
        if (objectWidthAndHeight != null)
            {
            int this_width = (newObject.getX() + objectWidthAndHeight.width);
            if (this_width > this.area.width) {
                this.area.width = this_width; 
                changed=true;
            }

            int this_height = (newObject.getY() + objectWidthAndHeight.height);
            if (this_height > this.area.height) {
                this.area.height = this_height; 
                changed=true;
            }
            if (changed) {
                //Update client's preferred size because
                //the area taken up by the graphics has
                //gotten larger or smaller (if cleared).
                ((CDDrawingPane)this.drawingPane).getDrawing().setPreferredSize(area);

                //Let the scroll pane know to update itself
                //and its scrollbars.
                ((CDDrawingPane)this.drawingPane).getDrawing().revalidate();
            }
        }
        ((CDDrawingPane)this.drawingPane).getDrawing().repaint();
    }
    
       /**
     * 
     * @param object
     * @return 
     */
    private WidthHeight getObjectWidthAndheight(CoordinateModel object)
    {
        int objectWidth;
        int objectHeight;
        if (object instanceof UCActor)
        {
            UCActor actor = (UCActor) object;
            objectWidth = actor.getMaxWidth();
            objectHeight = actor.getMaxHeight();
        }
        else if (object instanceof UCUseCase)
        {
            objectWidth = object.getWidth();
            objectHeight = object.getHeight();
        }
        else
        {
            return null;
        }
        return new WidthHeight(objectWidth, objectHeight);
    }
    
    /**
     * 
     */
    private class WidthHeight{
        public int width;
        public int height;
        
        public WidthHeight()
        {
            
        }
        
        public WidthHeight(int width, int height)
        {
            this.width = width;
            this.height = height;
        }
    }
}
