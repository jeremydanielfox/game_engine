package gae.openingView;

import java.util.EventObject;

import javafx.scene.Scene;

/**
 * Represents the Mediator in the Mediator OO design pattern. A concrete object that implements this
 * will act as the controller for all interactions of UIObject classes.
 * 
 * @author Brandon Choi
 *
 */

public interface UIMediator {
    
    /**
     * returns the scene of the mediator
     */
    Scene getScene();
    
    /**
     * called to add a UIObject whose interactions the mediator will control
     */
    void addUIObject (UIObject object);

    /**
     * called by UIObject to handle a specific event based on whatever action event a UIObject
     * receives
     * 
     * @param usedObject
     * @param action
     */
    void handleEvent (UIObject usedObject, EventObject action);
}
