/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import BT.models.CoordinateModel;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Karel Hala
 */
public class DrawingJpanel extends JPanel{
        /**
        * Method ofr recaltulating and resizing drawing pane based on objects in it.
        * @param newObject
        */
       protected void recalculateSize(CoordinateModel newObject) {
           Dimension area = this.getSize();
           if (newObject.getX()<0)
           {
               newObject.setX(0);
           }
           if (newObject.getY()<0)
           {
               newObject.setY(0);
           }
           if (newObject.getX()>area.width)
           {
               area.width = newObject.getX();
               this.setPreferredSize(area);
           }
           if (newObject.getY()>area.height)
           {
               area.height = newObject.getY();
               this.setPreferredSize(area);
           }
           this.revalidate();
       }
}
