package engine.gameobject.units;

import java.util.HashSet;
import java.util.Set;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.gameobject.weapon.firingstrategy.Explosion;
import gae.listView.DeepCopy;
import gameworld.ObjectCollection;

public class Collider {
    private Set<GameObject> collidedID;
    private Set<Buff> onCollision;
    private Explosion onDeath;
    
    public Collider () {
        collidedID = new HashSet<GameObject>();
        onCollision = new HashSet<Buff>();
        onDeath = new Explosion();
    }

    public Collider (Set<Buff> onCollision, Explosion onDeath) {
        collidedID = new HashSet<GameObject>();
        this.onCollision = onCollision;
        this.onDeath = onDeath;
    }
    public void addCollisionBehavior(Buff newBuff){
        removeDuplicateBuff(newBuff);
        onCollision.add(newBuff);
    }
 
    public void setExplosionRadius(double radius){
        onDeath.setRadius(radius);
    }
    
    public void addExplosionBuff(Buff newBuff){
        onDeath.addBuff(newBuff);;
    }
    
    public void collide (GameObject target) {
        if (!collidedID.contains(target)){
            collidedID.add(target);
            onCollision(target);
        }
    }
    
    public void explode (ObjectCollection world, PointSimple location){
        onDeath.explode(world, location);
    }
    
    private void removeDuplicateBuff(Buff newBuff){
        for (Buff b: onCollision){
            if (b.getClass().equals(newBuff.getClass())){
                onCollision.remove(b);
            }
        }
    }
    
    private void onCollision (GameObject target) {
        for (Buff b : onCollision) {
            target.addBuff(b);
        }
    }
    
    public Collider clone(){
        return new Collider(new HashSet<>(onCollision), onDeath.clone());
    }

}
