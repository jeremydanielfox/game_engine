package gae.builder;

import javafx.scene.Node;


/**
 * Interface for all builders such as the button builder or terrain builder to implement.
 * Differs from an Editor because Builders will build things that are NOT GameObjects.
 *
 * @author Brandon Choi
 *
 */

public interface Builder {

    /**
     * returns the outermost Node in order to grab the Builder after it has been created. This Node
     * can then be added to the UI for the user to work with.
     *
     * @return
     */
    Node getBuilder ();

    /**
     * resets all values to default & clears the fields
     */
    void restart ();
}
