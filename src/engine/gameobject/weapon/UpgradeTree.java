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

    public List<UpgradeBundle> getNextUpgrades ();
}
