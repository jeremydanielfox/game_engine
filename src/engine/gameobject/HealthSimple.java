package engine.gameobject;

import engine.fieldsetting.Settable;


/**
 * Most traditional implementation of health. Can take damage and die appropriately.
 *
 * @author Kaighn
 *
 */
@Settable
public class HealthSimple implements Health {
    private double myHealth;

    public HealthSimple () {
        myHealth = 0;
    }

    public HealthSimple (double health) {
        myHealth = health;
    }

    @Override
    public boolean isDead () {
        return myHealth <= 0;
    }

    @Override
    public void changeHealth (double amount) {
        myHealth += amount;
    }

    @Settable
    public void setHealth (double health) {
        myHealth = health;
    }

    @Override
    public Health clone () {
        return new HealthSimple(myHealth);
    }
}
