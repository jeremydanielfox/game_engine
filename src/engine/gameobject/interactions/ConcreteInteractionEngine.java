package engine.gameobject.interactions;

import java.util.HashMap;
import java.util.Map;
import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import engine.gameobject.types.Type;
import gameworld.GameWorld;


/**
 * The ConcreteInteractionEngine stores interactions between different Types as
 * Interactions. The InteractionEngine represents a table of
 * interactions. It accepts two GameObjects, finds the appropriate Interaction for
 * them (by using their Types), and lets the Interaction act upon them, modifying their states.
 * 
 * Say you have two different Types called first and second. In the ConcreteInteractionEngine, first
 * acting upon
 * second is different than second acting upon first.
 * 
 * The table in the ConcreteInteractionEngine is represented as a HashMap of HashMaps of
 * Interactions.
 *
 * @author Jeremy
 *
 */
@Settable
public class ConcreteInteractionEngine implements InteractionEngine {
    // sets up the initial, blank, table
    private Map<Type, Map<Type, Interaction>> myTable = new HashMap<>();
    private GameWorld myGameWorld;

    /**
     * Interacts two GameObjects by finding the interaction between their Types.
     * it gives the interaction the GameWorld and then allows it to interact the two GameObjects
     */
    @Override
    public void interact (GameObject first, GameObject second) {
        Type firstLabel = first.getType();
        Type secondLabel = second.getType();
        Interaction interaction = findInteraction(firstLabel, secondLabel);
        interaction.setGameWorld(myGameWorld);
        interaction.accept(first, second);
    }

    /**
     * Find the interaction between two Types. The ConcreteInteraction does this by relying on the
     * tree
     * structure of types it searches for the lowest level interaction defined between the two
     * types, finds
     * it and then uses it to interact the two Types. It does this by moving up the trees for each
     * Type simultaneously,
     * getting the map defined for the actor, and seeing if the target is in it. If not, it checks
     * if the super Type
     * of the target is in the map continuously. If the target has any super type in the map, this
     * is the interaction that will be used.
     * If not. the process repeats again, this time with the super type of the actor. If no
     * interaction is
     * ever found, the process ultimately returns a NoInteraction Interaction.
     * 
     * @param actor : The Type that is acting
     * @param target : The Type that is being acted upon
     * @return
     */
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
        return new NoInteraction();
    }

    /**
     * This method lets someone define the action that occurs between two
     * Types. Specifically, this action occurs from first onto second.
     *
     * @param first : the Type acting
     * @param second : the Type being acted upon
     * @param interaction : the Interaction between the two Types
     */
    @Override
    public void put (Type first, Type second, Interaction interaction) {
        checkNullMap(first);
        putInMap(first, second, interaction);
    }

    /**
     * Checks to see if a given Type in the outer HashMap. If it is not, puts the Type in the
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
     * Gets the HashMap associated with the Type "first."
     * Puts the given BiConsumer into that HashMap with the Type "second" as the key.
     *
     * @param first
     * @param second
     * @param consumer
     */
    private void putInMap (Type first, Type second, Interaction interaction) {
        Map<Type, Interaction> temp = myTable.get(first);
        temp.put(second, interaction);
    }

    /**
     * Sets the world for the ConcreteInteractionEngine
     */
    @Settable
    @Override
    public void setWorld (GameWorld world) {
        myGameWorld = world;
    }

}
