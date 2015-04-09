package engine.gameobject.weapon.firingstrategy;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.gameobject.units.Buff;
import engine.gameobject.units.BuffableUnit;
import gameworld.GameWorld;


public class Explosion {

    private int radius;
    private Set<Buff> effects;

    public Explosion (int radius, Collection<Buff> effects) {
        effects = new HashSet<Buff>(effects);
        this.radius = radius;
    }

    public void explode (GameWorld world, PointSimple location) {
        HashSet<GameObject> objectsInRange = (HashSet) world.objectsInRange(radius, location);
        for (GameObject target : objectsInRange) {
            if (checkTeam(target)) {
                applyBuffs((BuffableUnit) target);
            }
        }
    }

    private void applyBuffs (BuffableUnit target) {
        for (Buff b : effects) {
            target.addBuff(b);
        }
    }

    private boolean checkTeam (GameObject target) {
        // TODO: Check if target is on the other team...
        if (target instanceof BuffableUnit)
            return true;
        return false;
    }
}
