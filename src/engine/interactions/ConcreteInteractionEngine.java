package engine.interactions;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;


/**
 * The InteractionEngine stores interactions between different GameObjects as
 * BiConsumer Functional Interfaces. The InteractionEngine represents a table of
 * interactions. It accepts two GameObjects, find the appropriate BiConsumer for
 * them, and lets the BiConsumer act upon them, modifying their states.
 * 
 * @author Jeremy
 *
 */
@Settable
public class ConcreteInteractionEngine implements InteractionEngine {
    private Map<String, Map<String, BiConsumer<GameObject, GameObject>>> myTable = new HashMap<>();

    /**
     * This method takes in two GameObjects, locates the correct BiConsumer for
     * their interaction in the table, and lets the BiConsumer act upon these
     * GameObjects.
     * 
     * @param first
     * @param second
     */
    @Override
    public void interact (GameObject first, GameObject second) {
        try {
            BiConsumer<GameObject, GameObject> consumer =
                    myTable.get(
                                first.getTag().getLabel()).get(second.getTag().getLabel());
            consumer.accept(first, second);
        }
        catch (NullPointerException e) {
            System.out.println("Interaction hasn't been defined between "
                               + first.getTag().getLabel() + " and " + second.getTag().getLabel());

        }

    }

    /**
     * This method lets someone define the action that occurs between two
     * GameObjects
     * 
     * @param first
     * @param second
     * @param consumer
     */
    @Override
    public void put (GameObject first, GameObject second, BiConsumer consumer) {
        String firstID = first.getTag().getLabel();
        String secondID = second.getTag().getLabel();
        checkNullMap(firstID);
        checkNullMap(secondID);
        putInMap(firstID, secondID, consumer);
        putInMap(secondID, firstID, consumer);
    }

    /**
     * Checks to see if a given string in the outer HashMap. If it is not, puts the String in the
     * HashMap,
     * with another HashMap as its value
     */
    private void checkNullMap (String str) {
        if (myTable.get(str) == null)
            myTable.put(str,
                        new HashMap<String, BiConsumer<GameObject, GameObject>>());
    }

    /**
     * Gets the HashMap associated with the String "first."
     * Puts the given BiConsumer into that HashMap with the String "second" as the key.
     * 
     * @param first
     * @param second
     * @param consumer
     */
    private void putInMap (String first, String second, BiConsumer consumer) {
        Map temp = myTable.get(first);
        temp.put(second, consumer);
    }

}
