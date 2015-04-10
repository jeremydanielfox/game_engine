package engine.gameobject.weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import engine.gameobject.GameObject;
import engine.gameobject.weapon.upgradable.behavior.Behavior;
import engine.gameobject.PointSimple;
import engine.gameobject.units.Buff;
import engine.gameobject.units.Buffable;
import engine.gameobject.weapon.firingstrategy.Buffer;
import engine.gameobject.weapon.firingstrategy.FiringStrategy;
import engine.gameobject.weapon.firingstrategy.Projectile;
import engine.gameobject.weapon.upgradable.FiringRate;
import engine.gameobject.weapon.upgradable.Upgradable;
import engine.gameobject.weapon.upgradetree.UpgradeTree;
import gameworld.GameWorld;
import gameworld.ObjectCollection;


/**
 * Tool of attack for a GameObject. It has inherent range and a firing rate.
 * The weapon contains all behaviors that will be applied to a GameObject target.
 * 
 * @author Nathan Prabhu and Danny Oh
 *
 */
public class BasicWeapon implements Weapon{
    protected int timeSinceFire;
    protected double myRange;
    protected FiringRate myFiringRate;
    protected Buffer myProjectile;
    protected FiringStrategy myFiringStrategy;
    Map<Class<? extends Upgradable>, Upgradable> upgradables;
    protected UpgradeTree tree;

    /* (non-Javadoc)
     * @see engine.gameobject.weapon.Weaopn#fire(gameworld.GameWorld, engine.gameobject.PointSimple)
     */
    @Override
    public void fire (ObjectCollection world, PointSimple location) {
        if (canFire()) {
            List<GameObject> targets = (List<GameObject>) world.objectsInRange(myRange, location);
            List<Buffable> buffables =
                    targets.stream().filter(p -> p.getClass().isAssignableFrom(Buffable.class))
                            .map(p -> (Buffable) p)
                            .collect(Collectors.toList());
            if (!buffables.isEmpty()) {
                Buffable target = chooseTarget(buffables);
                myFiringStrategy.execute(world, target, location, myProjectile);
                timeSinceFire = 0;
                return;
            }
        }
        timeSinceFire++;
    }

    /* (non-Javadoc)
     * @see engine.gameobject.weapon.Weaopn#addBuff(engine.gameobject.units.Buff)
     */
    @Override
    public void addBuff (Buff newBuff) {
        myProjectile.addCollsionBehavior(newBuff);
    }

    /* (non-Javadoc)
     * @see engine.gameobject.weapon.Weaopn#getValue()
     */
    @Override
    public double getValue () {
        return tree.getValue();
    };

    /* (non-Javadoc)
     * @see engine.gameobject.weapon.Weaopn#setProjectile(engine.gameobject.weapon.firingstrategy.Projectile)
     */
    @Override
    public void setProjectile (Projectile projectile) {
        myProjectile = projectile;
    }

    /* (non-Javadoc)
     * @see engine.gameobject.weapon.Weaopn#getRange()
     */
    @Override
    public double getRange (){
        return myRange;
    }

    /* (non-Javadoc)
     * @see engine.gameobject.weapon.Weaopn#getFiringRate()
     */
    @Override
    public double getFiringRate(){
        return myFiringRate.getRate();
    }
    
    /*Utility that we may need in the future
    private void fireAtEnemyInRange (GameWorld world, PointSimple center) {
        ArrayList<GameObject> candidates =
                (ArrayList<GameObject>) world.objectsInRange(myRange, center);
        // TODO: In bloons, we choose from the candidates using first, last, strong, weak. We could
        // do something here as well using polymorphism. For now, we just choose a random one.
        if (!candidates.isEmpty()){
            myFiringStrategy.execute(world, candidates, center, myProjectile);
            timeSinceFire = 0;
        }
    }*/

    // TODO: In bloons, we choose from the candidates using first, last, strong, weak. We could
    // do something here as well using polymorphism. For now, we just choose a random one.
    private Buffable chooseTarget(List<Buffable> targets){
        return targets.get(0);
    }
    
    // TODO: Get the math correct here
    private double firingRateToSeconds () {
        return 60.0 / myFiringRate.getRate();
    }

    private boolean canFire () {
        return timeSinceFire > firingRateToSeconds();
    }

}
