package engine.gameobject.interactions;

import engine.gameobject.GameObject;
import engine.gameobject.types.Type;
import gameworld.GameWorld;


/**
 * Represents the basic interface for a table of interactions between Types and GameObjects that
 * have those types.
 * 
 * @author Jeremy
 *
 */
public interface InteractionEngine {

    /**
     * Interacts two GameObjects -- first acts upon second.
     * 
     * @param first The acting object
     * @param second What the first object is acting upon
     */
    public void interact (GameObject first, GameObject second);

    /**
     * Puts two interactions into the interaction engine table, along with the interaction between
     * them.
     * This will be the interaction that the first does to the second.
     * 
     * @param first : Type that is acting in the interaction
     * @param second : Type that is being acted upon in the interaction
     * @param interaction : Interaction that occurs between the two types
     */
    public void put (Type first,
                     Type second,
                     Interaction interaction);

    /**
     * Sets the GameWorld for the interaction engine.
     * 
     * @param world : World used in the interaction engine.
     */
    public void setWorld (GameWorld world);
}
