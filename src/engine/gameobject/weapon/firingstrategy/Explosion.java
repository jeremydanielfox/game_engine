package engine.gameobject.weapon.firingstrategy;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.PointSimple;
import engine.gameobject.test.GameObjectSimpleTest;
import engine.gameobject.units.Buff;
import gameworld.ObjectCollection;


public class Explosion {

    private double radius;
    private Set<Buff> explosBuffs;

    public Explosion () {
        radius = 0;
        explosBuffs = new HashSet<Buff>();
    }

    public Explosion (double radius, Collection<Buff> buffSet) {
        explosBuffs = new HashSet<Buff>(buffSet);
        this.radius = radius;
    }

    /**
     * Creates explosion in world at the specified location
     * 
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
     * Clears buff from the explosion
     */
    public void clearBuffs(){
        explosBuffs.clear();
    }
    /**
     * Adds newBuff as an explosion behavior
     * 
     * @param newBuff
     */
    public void addBuff (Buff newBuff) {
        explosBuffs.add(newBuff);
    }

    /**
     * Sets the explosion radius
     * 
     * @param radius
     */
    public void setRadius (double radius) {
        this.radius = radius;
    }

    private void applyBuffs (GameObject target) {
        for (Buff b : explosBuffs) {
            target.receiveBuff(b.clone());
        }
    }

    private boolean checkTeam (GameObject target) {
        // TODO: Check if target is on the other team...
        if (target instanceof GameObjectSimple) {
            return true;
        }
        return false;
    }

    @Override
    public Explosion clone () {
        return new Explosion(radius, explosBuffs);
    }

    public Set<Buff> getBuffSet () {
        return Collections.unmodifiableSet(explosBuffs);
    }
}
