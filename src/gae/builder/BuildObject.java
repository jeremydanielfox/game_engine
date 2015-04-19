package gae.builder;

import javafx.scene.Node;

/**
 * Interface for everything that Builder can potentially build.
 * 
 * @author Brandon Choi
 *
 */

public interface BuildObject {
    
    /**
     * retrieves the built object
     * @return
     */
    Node getBuildObject();
}
