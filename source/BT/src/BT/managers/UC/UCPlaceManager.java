/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BT.managers.UC;

import BT.managers.CoordinateManager;
import java.util.ArrayList;

/**
 *
 * @author Karel
 */
public class UCPlaceManager {
    private ArrayList<UCActor> actors = new ArrayList<>();
    private ArrayList<UCUseCase> UseCases = new ArrayList<>();
    
    public ArrayList<UCActor> getActors()
    {
        return this.actors;
    }
    
    public void addActor(UCActor place) 
    {
        this.actors.add(place);
    }
    
    public ArrayList<UCUseCase> getUseCases()
    {
        return this.UseCases;
    }
    
    public void addUseCase(UCUseCase place) 
    {
        this.UseCases.add(place);
    }
}
