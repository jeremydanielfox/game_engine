package engine.gameobject.labels;

import engine.fieldsetting.Settable;


@Settable
public class SimpleType implements Type {
    private static final int[] PRIME_NUMBERS = { 7, 97 };
    private Type superType;
    private String myName;

    public SimpleType () {
        superType = new TypeBase();
        myName = " ";
    }

    public SimpleType (String name) {
        myName = name;
        superType = new TypeBase();
    }

    public SimpleType (String name, Type parent) {
        myName = name;
        superType = parent;
    }

    @Override
    @Settable
    public void setName (String name) {
        myName = name;
    }

    @Override
    public String getName () {
        return myName;
    }

    @Override
    @Settable
    public void setSuperType (Type parent) {
        superType = parent;
    }

    @Override
    public Type getSuperType () {
        return superType;
    }

    @Override
    public Type clone () {
        return new SimpleType(myName, superType);
    }

    @Override
    public int hashCode () {
        return PRIME_NUMBERS[0] * myName.hashCode() + PRIME_NUMBERS[1] * superType.hashCode();
    }

    @Override
    public boolean equals (Object other) {
        return other != null && hashCode() == other.hashCode();
    }
}
