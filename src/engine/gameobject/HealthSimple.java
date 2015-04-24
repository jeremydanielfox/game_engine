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
    private double maxHealth;
    
    public HealthSimple(){
        this(0);
    }
    
    public HealthSimple(double health){
        maxHealth = health;
        myHealth = maxHealth;
    }
    
    @Override
    public boolean isDead () {
        return myHealth <= 0;
    }

    @Override
    public void changeHealth (double amount) {
        if (myHealth + amount > maxHealth) {
            myHealth = maxHealth;
        }
        else {
            myHealth += amount;
        }
    }

    @Settable
    public void setHealth (double health) {
        maxHealth = health;
        myHealth = maxHealth;
    }

    public Health clone(){
        return new HealthSimple(maxHealth);
    }
}
