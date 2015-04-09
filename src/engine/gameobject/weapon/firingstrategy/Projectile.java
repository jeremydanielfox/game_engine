package engine.gameobject.weapon.firingstrategy;

import java.util.HashSet;
import java.util.Set;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.MoverDirection;
import engine.gameobject.PointSimple;
import engine.gameobject.units.Buff;
import engine.gameobject.units.Buffable;
import engine.gameobject.units.BuffableUnit;


public class Projectile extends GameObjectSimple implements Buffer {
    protected Set<String> collidedID;
    protected Set<Buff> onCollision;
    protected Explosion onDeath;
    
    public Projectile () {
        collidedID = new HashSet<String>();
        onCollision = new HashSet<Buff>();
    }

    public void addCollsionBehavior(Buff newBuff){
        removeDuplicateBuff(newBuff);
        onCollision.add(newBuff);
    }
    
    public void collide(){
        
    }
    
    public void addOnDeath(Buff newBuff){
        onDeath.addBuff(newBuff);;
    }
    
    @Override
    public void impartBuffs (Buffable target) {
        // TODO Auto-generated method stub
        
    }
    
    private void removeDuplicateBuff(Buff newBuff){
        for (Buff b: onCollision){
            if (b.getClass().equals(newBuff.getClass())){
                onCollision.remove(b);
            }
        }
    }
    
    //TODO: We really want to impart a new mover to this projectile
    public Projectile clone(PointSimple location, double angle){
        Projectile clone = (Projectile) super.clone();
        return clone;
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
