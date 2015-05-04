package engine.gameobject.types;

/**
 * Forms the basic for a dynamic, user-defined typing system. Since the engine is built with only
 * one concrete GameObject, we need a way to tell GameObjects apart. Type is the solution, as the
 * user can set and get
 * a string representing the name. Futhermore, Type supports being built in a tree format, as you
 * can set super types to each Type
 * ( and the concrete implementations of Type could potentially keep track of their subtypes). This
 * allows you to set up systems of
 * Type inheritance where you make sub and super Types for each Type.
 * 
 * @author Jeremy
 *
 */
public interface Type {
    /**
     * Takes in a string that is used as the name for each Type.
     * 
     * @param name: The String name for each Type
     */
    public void setName (String name);

    /**
     * Returns a String that is the Type name
     * 
     * @return The String name for each Type
     */
    public String getName ();

    /**
     * Returns the single super Type associated with each Type.
     * 
     * @return The super Type associated with each Type.
     */
    public Type getSuperType ();

    /**
     * Sets the super Type for any given Type. Will override the super Type if one already exists.
     * 
     * @param parent: the super Type that this Type will "extend" from
     */
    public void setSuperType (Type parent);

    /**
     * Returns a clone of this Type
     * 
     * @return The cloned version of the Type
     */
    public Type clone ();

}
