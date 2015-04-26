package engine.gameobject.labels;



/**
 * This provides the base label that all other labels have as a super label.
 * If labels were classes in Java, this would be the Object class.
 * 
 * @author Jeremy
 *
 */
public class TypeBase extends SimpleType {
    /*
     * private static String name = "Base";
     * private static Collection<Label> mySubLabels = new HashSet<>();
     */

    private static String myName = "Base";
    private static Type superType = null;

    public TypeBase () {
        super(myName, superType);
    }

    /*
     * @Override
     * public String getLabel () {
     * return name;
     * }
     * 
     * 
     * @Override
     * public boolean hasSubLabel () {
     * return mySubLabels.size() > 0;
     * }
     * 
     * @Override
     * public Collection<Label> getSubLabels () {
     * return Collections.unmodifiableCollection(mySubLabels);
     * }
     * 
     * @Override
     * public boolean hasSuperLabel () {
     * return false;
     * }
     * 
     * @Override
     * public Label getSuperLabel () {
     * return null;
     * }
     * 
     * @Override
     * public void addSubLabel (Label label) {
     * mySubLabels.add(label);
     * }
     * 
     * 
     * @Override
     * public void removeSubLabel (Label label) {
     * mySubLabels.remove(label);
     * 
     * }
     */

}
