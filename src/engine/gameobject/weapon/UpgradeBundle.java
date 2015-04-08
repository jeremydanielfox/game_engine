package engine.gameobject.weapon;

import java.util.Map;
import engine.gameobject.weapon.upgradable.Upgradable;
import engine.shop.Purchasable;


/**
 * A predetermined method of upgrading a weapon's Upgradables. UpgradeBundles will be purchased at
 * the shop, and so they extend Purchasable. A weapon accesses its appropriate UpgradeBundles
 * through its UpgradeTree.
 * 
 * @author Nathan Prabhu
 *
 */
public interface UpgradeBundle extends Purchasable {

    /**
     * Transfers the upgrades to upgradables and replaces them in the map. If the current type of
     * upgradable doesn't exist, it is added as a new entry to the map.
     * 
     * @param upgradables
     */
    public void applyUpgrades (Map<Class<? extends Upgradable>, Upgradable> upgradables);
    
    /**
     * Flags whether or not this upgrade has a child in the UpgradeTree.
     * @return
     */
    public boolean isFinalUpgrade();

}
