package engine.gameobject.weapon;

import java.util.List;


/**
 * The path of available UpgradeBundles for a given weapon.
 * 
 * @author Nathan Prabhu
 *
 */
public interface UpgradeTree {

    /**
     * 
     * @return current value of the weapon, which is reflected by the weapon's position in the
     *         UpgradeTree.
     */
    public double getValue ();

    /**
     * 
     * @return list of upgradebundles, to be used in the shop to display what upgrades can currently
     *         be bought
     */
    public List<UpgradeBundle> getNextUpgrades ();
}
