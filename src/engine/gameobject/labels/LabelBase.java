package engine.gameobject.labels;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;


/**
 * This provides the base label that all other labels have as a super label.
 * If labels were classes in Java, this would be the Object class.
 * @author Jeremy
 *
 */
public class LabelBase extends SimpleLabel {
    /*private static String name = "Base";
    private static Collection<Label> mySubLabels = new HashSet<>();*/

    private static String myName = "Base";
    private static Label superLabel = null;
    
    public LabelBase(){
        super(myName, superLabel);
    }
    
   /* @Override
    public String getLabel () {
        return name;
    }

    
    @Override
    public boolean hasSubLabel () {
        return mySubLabels.size() > 0;
    }

    @Override
    public Collection<Label> getSubLabels () {
        return Collections.unmodifiableCollection(mySubLabels);
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


    @Override
    public void removeSubLabel (Label label) {
        mySubLabels.remove(label);
        
    }*/
    

}
