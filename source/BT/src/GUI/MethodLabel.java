/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import BT.managers.CD.Attribute;
import BT.managers.PlaceManager;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author Karel
 */
public class MethodLabel extends JLabel{
    private final PlaceManager petriNet;
    
    public MethodLabel()
    {
        petriNet = new PlaceManager();
    }

    public PlaceManager getPetriNet() {
        return petriNet;
    }
}
