package engine.gameobject.weapon.upgradetree;

import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundle;


public interface SingleTree extends UpgradeTree {

    /**
     * 
     * @return next available Upgrade to be purchased (if not final). Will be called by the shop to
     *         display what can be bought.
     */
    public UpgradeBundle getNextUpgrade ();

    /**
     * Updates the current position in the tree. Will be called upon purchase of an UpgradeBundle.
     */
    public void updateCurrent ();

}
