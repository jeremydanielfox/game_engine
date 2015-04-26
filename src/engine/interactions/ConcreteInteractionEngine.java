package engine.interactions;

import java.util.HashMap;
import java.util.Map;
import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import engine.gameobject.labels.Type;
import gameworld.GameWorld;


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
    private Map<Type, Map<Type, Interaction>> myTable = new HashMap<>();
    private GameWorld myGameWorld;

    @Override
    public void interact (GameObject first, GameObject second) {
        Type firstLabel = first.getLabel();
        Type secondLabel = second.getLabel();
        Interaction interaction = findInteraction(firstLabel, secondLabel);
        if (interaction != null) {
            interaction.setGameWorld(myGameWorld);
            interaction.accept(first, second);
        }
    }

    // TODO: This code is too complex... break it down/find a way to make it simpler
    private Interaction findInteraction (Type actor, Type target) {
        Map<Type, Interaction> actorMap;
        Type actorSuper = actor;
        Type targetSuper = target;
        while (actorSuper.getSuperType() != null) {
            if (!myTable.containsKey(actorSuper)) {
                actorSuper = actorSuper.getSuperType();
                continue;
            }
            actorMap = myTable.get(actorSuper);
            while (targetSuper.getSuperType() != null) {
                if (!actorMap.containsKey(targetSuper)) {
                    targetSuper = targetSuper.getSuperType();
                    continue;
                }
                return actorMap.get(targetSuper);
            }
            targetSuper = target;
            actorSuper = actorSuper.getSuperType();
        }
        return null;
    }

    /**
     * This method lets someone define the action that occurs between two
     * GameObjects. Specifically, this action occurs from first onto second.
     *
     * @param first
     * @param second
     * @param consumer
     */
    @Override
    public void put (Type first, Type second, Interaction interaction) {
        checkNullMap(first);
        putInMap(first, second, interaction);
    }

    /**
     * Checks to see if a given string in the outer HashMap. If it is not, puts the String in the
     * HashMap,
     * with another HashMap as its value
     */
    private void checkNullMap (Type label) {
        if (myTable.get(label) == null) {
            myTable.put(label,
                        new HashMap<Type, Interaction>());
        }
    }

    /**
     * Gets the HashMap associated with the String "first."
     * Puts the given BiConsumer into that HashMap with the String "second" as the key.
     *
     * @param first
     * @param second
     * @param consumer
     */
    private void putInMap (Type first, Type second, Interaction interaction) {
        Map temp = myTable.get(first);
        temp.put(second, interaction);
    }

    @Override
    public void setWorld (GameWorld world) {
        myGameWorld = world;
    }

}
