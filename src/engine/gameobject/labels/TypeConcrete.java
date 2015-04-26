package engine.gameobject.labels;

import java.util.Collection;
import java.util.Collections;
import engine.fieldsetting.Settable;


public abstract class TypeConcrete implements Type {
    private String myName;
    private Type mySuperLabel;
    private Type myBaseLabel;
    private Collection<Type> mySubLabels;

    public TypeConcrete () {
        myBaseLabel = new TypeBase();
        setSuperType(myBaseLabel);
    }

    public TypeConcrete (Type superLabel) {
        setSuperType(superLabel);
    }

    @Override
    public Type clone () {
        return null;
    }

    @Override
    public String getName () {
        return myName;
    }

    // @Override
    public boolean hasSubLabel () {
        return mySubLabels.size() > 0;
    }

    // @Override
    public Collection<Type> getSubLabels () {
        return Collections.unmodifiableCollection(mySubLabels);
    }

    // @Override
    public boolean hasSuperLabel () {
        return mySuperLabel != null;
    }

    @Override
    public Type getSuperType () {
        return mySuperLabel;
    }

    // @Override
    public void addSubLabel (Type label) {
        mySubLabels.add(label);

    }

    /*
     * private void setSuperLabel (Label superLabel) {
     * if (mySuperLabel.getSubLabels().contains(this))
     * mySuperLabel.removeSubLabel(this);
     * mySuperLabel = superLabel;
     * superLabel.addSubLabel(this);
     * }
     */

    // @Override
    public void removeSubLabel (Type label) {
        mySubLabels.remove(label);
    }

    @Override
    @Settable(primary = true)
    public void setName (String name) {
        myName = name;
    }

    /**
     * Recursively returns all sublabels for any given label
     */
    /*
     * public Collection<Label> getAllSubLabels (Label label) {
     * if (label.hasSubLabel() == false)
     * return new HashSet<>();
     * Set<Label> returnSet = new HashSet<>();
     * label.getSubLabels().forEach(sl -> returnSet.addAll(getAllSubLabels(sl)));
     * return returnSet;
     * }
     */

}
