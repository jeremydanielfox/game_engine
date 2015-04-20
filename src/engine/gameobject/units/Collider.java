package engine.gameobject.units;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.gameobject.weapon.firingstrategy.Explosion;
import gae.listView.DeepCopy;
import gameworld.ObjectCollection;

public class Collider {
    private Set<String> collidedID;
    private Set<Buff> collisionBuffs;
    private Explosion onDeath;
    
    public Collider () {
        collidedID = new HashSet<String>();
        collisionBuffs = new HashSet<Buff>();
        onDeath = new Explosion();
    }

    public void addCollisionBehavior(Buff newBuff){
        removeDuplicateBuff(newBuff);
        collisionBuffs.add(newBuff);
    }
 
    public void setExplosionRadius(double radius){
        onDeath.setRadius(radius);
    }
    
    public void addExplosionBuff(Buff newBuff){
        onDeath.addBuff(newBuff);;
    }
    
    public void collide (GameObject target) {
        onCollision(target);
        //TODO: Keep track of things that have been collided using something like
        //collidedID.add(target.getName());
    }
    
    public void explode (ObjectCollection world, PointSimple location){
        onDeath.explode(world, location);
    }
    
    private void removeDuplicateBuff(Buff newBuff){
        for (Buff b: collisionBuffs){
            if (b.getClass().equals(newBuff.getClass())){
                collisionBuffs.remove(b);
            }
        }
    }
    
    private void onCollision (GameObject target) {
        for (Buff b : collisionBuffs) {
            target.receiveBuff(b);
        }
    }
    
    
    /**
     * Returns the deep clone, but must test to see if behavior is correct
     */
    public Collider clone(){
        return (Collider) DeepCopy.copy(this);
    }
    
    public Set<Buff> getCollisionBuffs () {
        return Collections.unmodifiableSet(collisionBuffs);
    }
    
    public Set<Buff> getExplosionBuffs () {
        return onDeath.getBuffSet();
    }

}
