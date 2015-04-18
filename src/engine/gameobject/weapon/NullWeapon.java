package engine.gameobject.weapon;

import java.util.List;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.gameobject.units.Buff;
import engine.gameobject.weapon.firingstrategy.FiringStrategy;
import engine.gameobject.weapon.firingstrategy.Projectile;
import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundle;
import gameworld.ObjectCollection;
//TODO: There must be a better way to do this than just have empty methods everywhere.
public class NullWeapon implements Weapon{
    
    
    @Override
    public void fire (ObjectCollection world, GameObject target, PointSimple location) {
        //Do nothing
    }

    @Override
    public void addBuff (Buff newBuff) {
        //Do nothing
    }

    @Override
    public double getValue () {
        return 0;
    }

    @Override
    public void setProjectile (GameObject projectile) {
        //Do Nothing
    }

    @Override
    public double getRange () {
        return 0;
    }

    @Override
    public double getFiringRate () {
        return 0;
    }

    @Override
    public List<UpgradeBundle> getNextUpgrades () {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void applyUpgrades (UpgradeBundle bundle){
        
    }

    @Override
    public void setRange (double range) {
        //Do Nothing
    }

    @Override
    public void setFiringRate (double firingRate) {
        //Do Nothing
    }

    @Override
    public void advanceTime () {
        //Do nothing
    }

    @Override
    public void setFiringStrategy (FiringStrategy newStrategy) {
        // Do nothing
    }
}
