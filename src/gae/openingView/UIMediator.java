package gae.openingView;

import javafx.event.ActionEvent;

/**
 * Represents the Mediator in the Mediator OO design pattern. A concrete object that implements this
 * will act as the controller for all interactions of UIObject classes.
 * 
 * @author Brandon Choi
 *
 */

public interface UIMediator {
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
    void handleEvent (UIObject usedObject, ActionEvent action);
}
