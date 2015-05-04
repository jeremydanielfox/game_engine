// This entire file is part of my masterpiece.
// JEREMY FOX
package engine.gameobject.healths;

/**
 * Concrete implementation of the Health Component
 * 
 * @author Jeremy
 *
 */
public class HealthComponentConcrete implements HealthComponent {
    private Health myHealth;

    /**
     * Tells you if the health component is dead
     */
    @Override
    public boolean isDead () {
        return myHealth.isDead();
    }

    /**
     * changes the health by an amount
     */
    @Override
    public void changeHealth (double amount) {
        myHealth.changeHealth(amount);
    }

    /**
     * Sets the health to a new health
     */
    @Override
    public void setHealth (Health health) {
        myHealth = health;
    }

    /**
     * Returns the health
     */
    @Override
    public Health getHealth () {
        return myHealth;
    }

}
