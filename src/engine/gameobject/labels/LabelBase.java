package engine.gameobject.labels;

import java.util.Collection;
import java.util.HashSet;


/**
 * This provides the base label that all other labels have as a super label.
 * If labels were classes in Java, this would be the Object class.
 * @author Jeremy
 *
 */
public class LabelBase implements Label {
    private static String myName = "Base";
    private static Collection<Label> mySubLabels = new HashSet<>();

    @Override
    public String getLabel () {
        return myName;
    }

    
    @Override
    public boolean hasSubLabel () {
        return mySubLabels.size() > 0;
    }

    @Override
    public Collection<Label> getSubLabels () {
        return mySubLabels;
    }

    @Override
    public boolean hasSuperLabel () {
        return false;
    }

    @Override
    public Label getSuperLabel () {
        return null;
    }

    @Override
    public void addSubLabel (Label label) {
        mySubLabels.add(label);
    }

}
