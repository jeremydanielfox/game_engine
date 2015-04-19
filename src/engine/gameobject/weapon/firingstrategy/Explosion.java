package engine.gameobject.weapon.firingstrategy;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimpleTest;
import engine.gameobject.PointSimple;
import engine.gameobject.units.Buff;
import engine.gameobject.units.BuffTracker;
import gameworld.ObjectCollection;


public class Explosion {

    private double radius;
    private Set<Buff> effects;

    public Explosion (int radius, Collection<Buff> effects) {
        effects = new HashSet<Buff>(effects);
        this.radius = radius;
    }

    public Explosion () {
        radius = 0;
        effects = new HashSet<Buff>();
    }

    /**
     * Creates explosion in world at the specified location
     * @param world
     * @param location
     */
    public void explode (ObjectCollection world, PointSimple location) {
        HashSet<GameObject> objectsInRange = new HashSet<>(world.objectsInRange(radius, location));
        for (GameObject target : objectsInRange) {
            if (checkTeam(target)) {
                applyBuffs(target);
            }
        }
    }
    
    /**
     * Adds newBuff as an explosion behavior
     * @param newBuff
     */
    public void addBuff(Buff newBuff){
        effects.add(newBuff);
    }

    /**
     * Sets the explosion radius
     * @param radius
     */
    public void setRadius(double radius){
        this.radius = radius;
    }
    
    private void applyBuffs (GameObject target) {
        for (Buff b : effects) {
            target.addBuff(b.clone());
        }
    }

    private boolean checkTeam (GameObject target) {
        // TODO: Check if target is on the other team...
        if (target instanceof GameObjectSimpleTest)
            return true;
        return false;
    }
}
