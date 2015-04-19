package engine.interactions;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import engine.gameobject.labels.Label;
import engine.gameobject.test.TowerLabel;
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
    private Map<Label, Map<Label, Interaction>> myTable = new HashMap<>();
    private GameWorld myGameWorld;

    @Override
    public void interact (GameObject first, GameObject second) {
        Label firstLabel = first.getLabel();
        Label secondLabel = second.getLabel();
        Interaction interaction = findInteraction(firstLabel, secondLabel);
        if (interaction!= null){
            interaction.setGameWorld(myGameWorld);
            interaction.accept(first, second);
        }
    }
    
    //TODO: This code is too complex... break it down/find a way to make it simpler
    private Interaction findInteraction(Label actor, Label target){
        Map<Label, Interaction> actorMap;
        Label actorSuper = actor;
        Label targetSuper = target;
        while (actorSuper.getSuperLabel() != null){
            if(!myTable.containsKey(actorSuper)){
                actorSuper = actorSuper.getSuperLabel();
                continue;
            }
            actorMap = myTable.get(actorSuper);
            while(targetSuper.getSuperLabel() != null){
                if(!actorMap.containsKey(targetSuper)){
                    targetSuper = targetSuper.getSuperLabel();
                    continue;
                }
                return actorMap.get(targetSuper);
            }
            targetSuper = target;
            actorSuper = actorSuper.getSuperLabel();
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
    public void put (Label first, Label second, Interaction interaction) {
        checkNullMap(first);
        putInMap(first, second, interaction);
    }

    /**
     * Checks to see if a given string in the outer HashMap. If it is not, puts the String in the
     * HashMap,
     * with another HashMap as its value
     */
    private void checkNullMap (Label label) {
        if (myTable.get(label) == null)
            myTable.put(label,
                        new HashMap<Label, Interaction>());
    }

    /**
     * Gets the HashMap associated with the String "first."
     * Puts the given BiConsumer into that HashMap with the String "second" as the key.
     * 
     * @param first
     * @param second
     * @param consumer
     */
    private void putInMap (Label first, Label second, Interaction interaction) {
        Map temp = myTable.get(first);
        temp.put(second, interaction);
    }

    @Override
    public void setWorld (GameWorld world) {
        myGameWorld=world;
    }

}
