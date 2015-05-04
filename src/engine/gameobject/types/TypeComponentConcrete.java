// This entire file is part of my masterpiece.
// JEREMY FOX
package engine.gameobject.types;
/**
 * First and perhaps only concrete implementation of the TypeComponent. Holds and manages the Type
 * for a GameObject
 * @author Jeremy
 *
 */
public class TypeComponentConcrete implements TypeComponent {
    private Type myType;

    @Override
    public Type getType () {

        return myType;
    }

    @Override
    public void setType (Type type) {
        myType = type;
    }

}
