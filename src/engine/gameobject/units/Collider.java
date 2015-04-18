package engine.gameobject.units;

import java.util.HashSet;
import java.util.Set;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.gameobject.weapon.firingstrategy.Explosion;
import gae.listView.DeepCopy;
import gameworld.ObjectCollection;

public class Collider {
    private Set<String> collidedID;
    private Set<Buff> onCollision;
    private Explosion onDeath;
    
    public Collider () {
        collidedID = new HashSet<String>();
        onCollision = new HashSet<Buff>();
        onDeath = new Explosion();
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
        onCollision(target);
        //TODO: Keep track of things that have been collided using something like
        //collidedID.add(target.getName());
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
    
    /**
     * We expect this to now be done in the collision engine
     * @param obstacle
     * @return
     */
    private boolean effectiveCollision (GameObject obstacle) {
        //TODO: Enable the following code by adding "object identification" and "teams"
        /*if (!collidedID.contains(obstacle.getID())){
            if(obstacle.getTeam()!= getTeam())
                return true;
        }*/
        return true;
    }

    /**
     * What to do on collision
     * @InteractionEngine we should handle this in the interaction engine
     * @param target
     */
    private void onCollision (GameObject target) {
        //This check may no longer have to be done.
        if (effectiveCollision(target)) {
            for (Buff b: onCollision){
                ((Buffable) target).addBuff(b);
            }
        }
    }
    
    /**
     * Returns the deep clone, but must test to see if behavior is correct
     */
    public Collider clone(){
        return (Collider) DeepCopy.copy(this);
    }

}
