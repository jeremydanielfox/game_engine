package gae.openingView;

/**
 * Represents the Mediator in the Mediator OO design pattern. A concrete object that implements this
 * will act as the controller for all interactions of UIObject classes.
 * 
 * @author Brandon Choi
 *
 */

public interface UIMediator {
    /**
     * initializes all the objects, values, etc. necessary to use the concrete mediator
     */
    abstract void initialize();
    
    /**
     * called to add a UIObject whose interactions the mediator will control
     */
    abstract void addUIObject (UIObject object);
}
