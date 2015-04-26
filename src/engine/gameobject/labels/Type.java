package engine.gameobject.labels;

public interface Type {

    public void setName (String name);

    public String getName ();

    public Type getSuperType ();

    public void setSuperType (Type parent);

    public Type clone ();
    /*
     * public String getLabel();
     * 
     * public boolean hasSubLabel();
     * 
     * public Collection<Label> getSubLabels();
     * 
     * public boolean hasSuperLabel();
     * 
     * public Label getSuperLabel();
     * 
     * public void addSubLabel(Label label);
     * 
     * public void removeSubLabel(Label label);
     */

}
