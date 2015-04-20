package engine.gameobject.labels;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import engine.fieldsetting.Settable;


public abstract class LabelConcrete implements Label {
    private String myName;
    private Label mySuperLabel;
    private Label myBaseLabel;
    private Collection<Label> mySubLabels;

    public LabelConcrete () {
        myBaseLabel = new LabelBase();
        setSuperLabel(myBaseLabel);
    }

    public LabelConcrete (Label superLabel) {
        setSuperLabel(superLabel);
    }

    public Label clone(){
        return null;
    }
    @Override
    public String getName () {
        return myName;
    }

    //@Override
    public boolean hasSubLabel () {
        return mySubLabels.size() > 0;
    }

    //@Override
    public Collection<Label> getSubLabels () {
        return Collections.unmodifiableCollection(mySubLabels);
    }

    //@Override
    public boolean hasSuperLabel () {
        return mySuperLabel != null;
    }

    @Override
    public Label getSuperLabel () {
        return mySuperLabel;
    }

    //@Override
    public void addSubLabel (Label label) {
        mySubLabels.add(label);

    }

    /*
    private void setSuperLabel (Label superLabel) {
        if (mySuperLabel.getSubLabels().contains(this))
           mySuperLabel.removeSubLabel(this);
        mySuperLabel = superLabel;
        superLabel.addSubLabel(this);
    }*/

    //@Override
    public void removeSubLabel (Label label) {
        mySubLabels.remove(label);
    }

    @Settable(primary = true)
    public void setName (String name) {
        myName = name;
    }

    /**
     * Recursively returns all sublabels for any given label
     *//*
    public Collection<Label> getAllSubLabels (Label label) {
        if (label.hasSubLabel() == false)
            return new HashSet<>();
        Set<Label> returnSet = new HashSet<>();
        label.getSubLabels().forEach(sl -> returnSet.addAll(getAllSubLabels(sl)));
        return returnSet;
    }*/

}
