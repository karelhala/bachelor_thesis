/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.modules.ClassDiagram.mainContent;

import BT.managers.PlaceManager;
import GUI.BottomLeftContentModel;

/**
 *
 * @author Karel
 */
public class CDBottomLeftController {
    final private BottomLeftContentModel leftContentmodel;
    final private PlaceManager cdPlaces;
    
    public CDBottomLeftController (BottomLeftContentModel leftContentmodel, PlaceManager cdPlaces)
    {
        this.leftContentmodel = leftContentmodel;
        this.cdPlaces = cdPlaces;
    }
}
