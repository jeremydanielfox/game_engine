package engine.gameobject.labels;

public interface Type {

    public void setName (String name);

    public String getName ();

    public Type getSuperType ();

    public void setSuperType (Type parent);

    public Type clone ();

}
