package engine.gameobject.labels;

import java.util.Collection;
import java.util.HashSet;


/**
 * This provides the base label that all other labels have as a super label.
 * If labels were classes in Java, this would be the Object class.
 * 
 * @author Jeremy
 *
 */
public class TypeBase extends SimpleType {

    private static String myName = "Base";
    private static Type superType = null;

    public TypeBase () {
        super(myName, superType);
    }

    @Override
    public int hashCode(){
        return 23 * myName.hashCode();
    }

}
