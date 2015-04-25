package engine.gameobject;



/**
 * All gameobjects have a health. Every game object can affect other objects' health
 * through interaction.
 *
 * @author Kaighn
 */
public interface Health {
    /**
     * Returns if the health is dead (ie, the gameobject with the health is dead).
     * Some healths never die (ie unattackable towers).
     * 
     * @return boolean , true if health is dead.
     */
    public boolean isDead ();

    /**
     * Changes health (presumably representing taking damage, or healing).
     * 
     * @param amount +/- amount by which to affect health
     */
    public void changeHealth (double amount);

    public Health clone ();
}
