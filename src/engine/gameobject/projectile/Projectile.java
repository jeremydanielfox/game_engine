package engine.gameobject.projectile;

import java.util.HashSet;
import java.util.Set;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.units.Buff;
import engine.gameobject.units.BuffableUnit;


public class Projectile extends GameObjectSimple {
    protected Set<String> collidedID;
    protected Set<Buff> onCollision;
    
    public Projectile () {
        collidedID = new HashSet<String>();
        onCollision = new HashSet<Buff>();
    }

    public void addCollsionBehavior(Buff newBuff){
        removeDuplicateBuff(newBuff);
        onCollision.add(newBuff);
    }
    
    private void removeDuplicateBuff(Buff newBuff){
        for (Buff b: onCollision){
            if (b.getClass().equals(newBuff.getClass())){
                onCollision.remove(b);
            }
        }
    }
    // Many conditions have to be met for an projectile to impart its effects.
    // For example, it must be on the "other side", it must not be another projectile, etc.
    private boolean effectiveCollision (GameObject obstacle) {
        //TODO: Enable the following code by adding "object identification" and "teams"
        /*if (!collidedID.contains(obstacle.getID())){
            if(obstacle.getTeam()!= getTeam())
                return true;
        }*/
        return false;
    }

    private void onCollision (BuffableUnit obstacle) {
        if (effectiveCollision(obstacle)) {
            for (Buff b: onCollision){
                obstacle.addBuff(b);
            }
            changeHealth(-1);
        }
    }
}
