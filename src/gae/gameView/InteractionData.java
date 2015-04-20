package gae.gameView;

import java.util.List;

import engine.interactions.Interaction;

/**
 * Class that acts as the data holder of all interactions authored. This will hold the interactions
 * that will be exported out to the engine.
 * 
 * @author Brandon Choi
 *
 */

public class InteractionData {
    
    public List<Interaction> myInteractions;
 
    public InteractionData(){
        
    }
    
    /**
     * Called by the InteractionTable to add a new Interaction authored
     */
    public void addInteraction(){
        
    }
}
