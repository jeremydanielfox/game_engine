// This entire file is part of my masterpiece.
// JEREMY FOX
package engine.gameobject.healths;

import engine.fieldsetting.Settable;


public interface HealthComponent {

    public boolean isDead ();

    public void changeHealth (double amount);

    /**
     * Note that this takes in a new health object.
     * Should only be used when setting to a new health, not for damage.
     * 
     * @param health
     */

    @Settable
    public void setHealth (Health health);

    public Health getHealth ();

}
