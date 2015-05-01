package engine.gameobject.labels;

import engine.fieldsetting.Settable;


/**
 * 
 * @author Jeremy
 *
 *         Simple implementation of Type that allows for the tree structure of inheritance to exist.
 *         Overrides hashcode and equals methods to allow for any types with the same string name
 *         that inherit from types
 *         with the string name that inherit from types with the string name... to be equal.
 *         This happened because in order to give something a certain type, you have to create a new
 *         version
 *         of that type. They can't be constants because they are created by the user.
 *         By default, has TypeBase as the super type.
 */
@Settable
public class SimpleType implements Type {
    private static final int[] PRIME_NUMBERS = { 7, 97 };
    private Type superType;
    private String myName;

    /**
     * Creates a new, blank Type with a Type Base as the super type. Ideally, if using this
     * constructor,
     * one would then set the type.
     */
    public SimpleType () {
        superType = new TypeBase();
        myName = " ";
    }

    /**
     * Creates a new SimpleType with the given string as the name. Sets TypeBase as the super Type.
     * 
     * @param name: String that will be set to the Type string
     */
    public SimpleType (String name) {
        myName = name;
        superType = new TypeBase();
    }

    /**
     * Creates a new Type with the given string as the name, and the given Type as the super Type
     * 
     * @param name: String that will be the name for the Type
     * @param parent: Type that will be the super Type
     */
    public SimpleType (String name, Type parent) {
        myName = name;
        superType = parent;
    }

    /**
     * Sets the name for the Type
     */
    @Override
    @Settable
    public void setName (String name) {
        myName = name;
    }

    /**
     * Returns the String-valued name for the Type
     */
    @Override
    public String getName () {
        return myName;
    }

    /**
     * Sets the super Type
     */
    @Override
    @Settable
    public void setSuperType (Type parent) {
        superType = parent;
    }

    /**
     * Returns the super Type as a Type
     */
    @Override
    public Type getSuperType () {
        return superType;
    }

    /**
     * returns a cloned version of this Type
     */
    @Override
    public Type clone () {
        return new SimpleType(myName, superType);
    }

    /**
     * Overrides the hashCode for SimpleType to be incorporate the String of the type and the String
     * of the super Type. This has to happen because two Types should be equal on the basis of two
     * conditions:
     * their strings are the same, and they extend from the same Type. This hashCode takes care of
     * both of those things
     */
    @Override
    public int hashCode () {
        return PRIME_NUMBERS[0] * myName.hashCode() + PRIME_NUMBERS[1] * superType.hashCode();
    }

    /**
     * Implements the equals method using the overwritten hashCode.s
     */
    @Override
    public boolean equals (Object other) {
        return other != null && hashCode() == other.hashCode();
    }
}
