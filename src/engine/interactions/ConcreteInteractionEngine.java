package engine.interactions;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import engine.gameobject.labels.Label;


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
    private Map<Label, Map<Label, BiConsumer<GameObject, GameObject>>> myTable = new HashMap<>();

    @Override
    public void interact (GameObject first, GameObject second) {
        Label firstLabel = first.getLabel();
        Label secondLabel = second.getLabel();
        Map<Label, BiConsumer<GameObject, GameObject>> chosenMap;
        BiConsumer<GameObject, GameObject> interaction = null;
        while (firstLabel != null && !myTable.containsKey(firstLabel)) {
            chosenMap = myTable.get(firstLabel);
            while (secondLabel != null && !chosenMap.containsKey(secondLabel)) {
                interaction = chosenMap.get(secondLabel);
                secondLabel = secondLabel.getSuperLabel();
            }
            firstLabel = firstLabel.getSuperLabel();
        }
        interaction.accept(first, second);
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
    public void put (Label first, Label second, BiConsumer consumer) {
        checkNullMap(first);
        checkNullMap(second);
        putInMap(first, second, consumer);
        putInMap(second, first, consumer);
    }

    /**
     * Checks to see if a given string in the outer HashMap. If it is not, puts the String in the
     * HashMap,
     * with another HashMap as its value
     */
    private void checkNullMap (Label label) {
        if (myTable.get(label) == null)
            myTable.put(label,
                        new HashMap<Label, BiConsumer<GameObject, GameObject>>());
    }

    /**
     * Gets the HashMap associated with the String "first."
     * Puts the given BiConsumer into that HashMap with the String "second" as the key.
     * 
     * @param first
     * @param second
     * @param consumer
     */
    private void putInMap (Label first, Label second, BiConsumer consumer) {
        Map temp = myTable.get(first);
        temp.put(second, consumer);
    }
    

}
