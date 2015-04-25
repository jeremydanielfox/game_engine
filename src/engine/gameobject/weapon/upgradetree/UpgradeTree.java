package engine.gameobject.weapon.upgradetree;

import java.util.List;
import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundle;


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

    /**
     * Updates the current position in the tree. Will be called upon purchase of an UpgradeBundle.
     * 
     * @param toUpdate current active tree. Should only be 0 or 1 parameters in this method.
     */
    public void updateCurrent (UpgradeTree ... toUpdate);
    
    public UpgradeTree clone();
}
