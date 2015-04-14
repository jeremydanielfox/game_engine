package engine.gameobject.labels;

import java.util.Collection;

public class LabelConcrete implements Label {
    private String myName;
    private Label mySuperLabel;
    private Collection<Label> mySubLabels;
    
    @Override
    public String getLabel () {
        return myName;
    }

    @Override
    public boolean hasSubLabel () {
        return mySubLabels.size()>0;
    }

    @Override
    public Collection<Label> getSubLabels () {
        return mySubLabels;
    }

    @Override
    public boolean hasSuperLabel () {
        return mySuperLabel!=null;
    }

    @Override
    public Label getSuperLabel () {
        return mySuperLabel;
    }

    @Override
    public void addSubLabel (Label label) {
         mySubLabels.add(label);
        
    }

}
