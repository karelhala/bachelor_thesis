/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BT.managers.CD;

import BT.BT;

/**
 *
 * @author Karel
 */
public class Attribute {
    private BT.AttributeType visibility;
    private String name;
    private String type;

    public Attribute(BT.AttributeType visibility, String name, String type) {
        this.visibility = visibility;
        this.name = name;
        this.type = type;
    }

    public Attribute(String name, String type) {
        visibility = BT.AttributeType.PRIVATE;
        this.name = name;
        this.type = type;
    }
    
    public void setVisibility(BT.AttributeType visibility) {
        this.visibility = visibility;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public BT.AttributeType getVisibility() {
        return visibility;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
    
    
}
