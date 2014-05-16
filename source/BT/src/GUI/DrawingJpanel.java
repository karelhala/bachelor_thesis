/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import BT.managers.RecalculatePaneSize;
import BT.models.CoordinateModel;
import BT.models.LineModel;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Karel Hala
 */
public class DrawingJpanel extends JPanel{
    /**
    * Method for recaltulating and resizing drawing pane based on objects in it.
    * @param newObject
    */
    protected void recalculateSize(CoordinateModel newObject) {
        Dimension calculatedSize = RecalculatePaneSize.recalculateSizeofPaneOnObject(newObject, this.getSize());
        this.setPreferredSize(calculatedSize);
        this.setSize(calculatedSize);
        this.revalidate();
    }
    
    protected void recalculateSizeForLines(LineModel drawnLine)
    {
        Dimension calculatedSize = RecalculatePaneSize.recalculateSizeOfPaneByLine(drawnLine, this.getSize());
        this.setPreferredSize(calculatedSize);
        this.setSize(calculatedSize);
        this.revalidate();
    }
}
