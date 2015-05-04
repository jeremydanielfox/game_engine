// This entire file is part of my masterpiece.
// JEREMY FOX
package engine.gameobject.types;

/**
 * Represents the component for handling Type interactions in the GameObject
 * 
 * @author Jeremy
 *
 */
public interface TypeComponent {
    /**
     * Returns the Type associated with the TypeComponent
     */
    public Type getType ();

    /**
     * Sets the Type of the TypeComponent
     */
    public void setType (Type type);
}
