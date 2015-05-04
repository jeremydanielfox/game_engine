package engine.gameobject.types;



/**
 * This provides the base Type that all other Type have as a super Type.
 * If Type were classes in Java, this would be the Object class.
 * 
 * @author Jeremy
 *
 */
public class TypeBase extends SimpleType {
    // Sets the name for the type
    private static String myName = "Base";
    // a TypeBase never has a superType
    private static Type superType = null;

    private static int PRIME_NUMBER = 23;

    /**
     * Returns a new TypeBase with "Base" as the name, and a null superType
     */
    public TypeBase () {
        super(myName, superType);
    }

    /**
     * Overrides the hash code in SimpleType to take into account the fact that a TypeBase does not
     * have a supertype
     */
    @Override
    public int hashCode () {
        return PRIME_NUMBER * myName.hashCode();
    }

}
