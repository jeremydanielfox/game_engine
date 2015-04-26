package engine.gameobject;

import engine.fieldsetting.Settable;
import engine.titles.Title;


/**
 * Most traditional implementation of health. Can take damage and die appropriately.
 *
 * @author Kaighn
 *
 */
@Settable
public class HealthSimple implements Health, Title {
    private double myHealth;
    private double maxHealth;
    private String myTitle = "";
    private int index;

    public HealthSimple () {
        this(0);
    }

    public HealthSimple (double health) {
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

    public Health clone () {
        return new HealthSimple(maxHealth);
    }

    @Override
    public String getTitle () {
        // TODO Auto-generated method stub
        return myTitle;
    }

    @Settable
    @Override
    public void setTitle (String title) {
        myTitle = title;
    }

    @Override
    public int getIndex () {
        // TODO Auto-generated method stub
        return index;
    }

    @Override
    public void setIndex (int index) {
        // TODO Auto-generated method stub
        this.index = index;
    }
}
