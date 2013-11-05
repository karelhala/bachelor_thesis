/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers;

/**
 *
 * @author Karel Hala
 */
public class CoordinateManager {
    protected int x;
    protected int y;
    protected String name;
    
    public int getX()
    {
        return this.x;
    }
    
    public void setX(int X)
    {
        this.x = X;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    public void setY(int Y)
    {
        this.y = Y;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
    }
}
