/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.models;

import BT.BaseBTModel;

/**
 *
 * @author Karel
 */
public class UMLModel extends BaseBTModel{
    private String whoAmI;
    
    /**
     * constructor, who am I will be set to "I am UML string"
     */
    public UMLModel()
    {
        this.whoAmI = "I am UML string";
    }
    
    /**
     * Function for setting who am I
     * @param whoAmI string for variable who am I
     */
    public void setWhoAmI(String whoAmI)
    {
        this.whoAmI = whoAmI;
    }
    
    /**
     * Function for getting who am I
     * @return string of who am I variables
     */
    public String getWhoAmI()
    {
        return this.whoAmI;
    }
}
