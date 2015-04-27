package engine.gameobject.labels;

import engine.fieldsetting.Settable;


@Settable
public class SimpleType implements Type{

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
    public int hashCode(){
        return 7 * myName.hashCode() + 97 * superType.hashCode();
    }
    
    // TODO: equals method needs work... possibly should go all the way down to basetype
//    @Override
//    public boolean equals (Object other) {
//        return hashCode() == other.hashCode();
//    }
}
