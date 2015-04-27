package gae.builder;

/**
 * Interface for everything that Builder can potentially build.
 *
 * @author Brandon Choi
 *
 */

public interface BuildObjectData {
    /**
     * fills all the build object's properties from the engine's object
     */
    void fillProperties ();
}
