package engine.gameobject.units;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.gameobject.weapon.firingstrategy.Explosion;
import gameworld.ObjectCollection;


public class Collider {

    private Set<GameObject> collidedID;
    private Set<Buff> collisionBuffs;
    private Explosion onDeath;

    @Settable
    public Collider () {
        collidedID = new HashSet<GameObject>();
        collisionBuffs = new HashSet<Buff>();
        onDeath = new Explosion();
    }

    public Collider (Set<Buff> onCollision, Explosion onDeath) {
        collidedID = new HashSet<GameObject>();
        collisionBuffs = onCollision;
        this.onDeath = onDeath;
    }

    @Settable
    public void setCollisionList(Collection<Buff> buffs){
        Set<Buff> buffList = new HashSet<>();
        buffList.addAll(buffs);
        System.out.println("added collision list");
    }
    
    @Settable
    public void setExplosionList(Collection<Buff> buffs){
        onDeath.clearBuffs();
        buffs.forEach(b -> onDeath.addBuff(b));
        System.out.println("added explosion list");
    }
    
    public void addCollisionBehavior (Buff newBuff) {
        removeDuplicateBuff(newBuff);
        collisionBuffs.add(newBuff);
    }

    @Settable
    public void setExplosionRadius (double radius) {
        onDeath.setRadius(radius);
    }

    public void addExplosionBuff (Buff newBuff) {
        onDeath.addBuff(newBuff);
    }

    public void collide (GameObject target) {
        if (!collidedID.contains(target)) {
            collidedID.add(target);
            onCollision(target);
        }
    }

    public void explode (ObjectCollection world, PointSimple location) {
        onDeath.explode(world, location);
    }

    private void removeDuplicateBuff (Buff newBuff) {
        for (Buff b : collisionBuffs) {
            if (b.getClass().equals(newBuff.getClass())) {
                collisionBuffs.remove(b);
            }
        }
    }

    private void onCollision (GameObject target) {
        for (Buff b : collisionBuffs) {
            target.receiveBuff(b.clone());
        }
    }

    @Override
    public Collider clone () {
        return new Collider(new HashSet<>(collisionBuffs), onDeath.clone());
    }

    public Set<Buff> getCollisionBuffs () {
        return Collections.unmodifiableSet(collisionBuffs);
    }

    public Set<Buff> getExplosionBuffs () {
        return onDeath.getBuffSet();
    }

}
