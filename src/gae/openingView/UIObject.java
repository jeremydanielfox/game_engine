package gae.openingView;

import javafx.scene.Node;

/**
 * This represents the mediated object in the Mediator OO design pattern. Each object that
 * implements this will be a UI object that interacts with the user and other UI components.
 * 
 * 
 * @author Brandon Choi
 *
 */

public interface UIObject {

    /**
     * initializes any objects, values, and can call helper methods to do so
     */
    abstract void initialize ();

    /**
     * returns the most general container to whatever class is calling it in order to display the
     * UIObject
     * 
     * @return Node
     */
    Node getObject ();
}
