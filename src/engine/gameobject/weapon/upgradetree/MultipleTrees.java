package engine.gameobject.weapon.upgradetree;

import java.util.List;
import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundle;


public interface MultipleTrees extends UpgradeTree {

    /**
     *
     * @return next available upgrades to be purchased (if not final). Will be called by the shop to
     *         display what can be bought.
     */
    @Override
    public List<UpgradeBundle> getNextUpgrades ();

    /**
     *
     * @param toUpdate tree that will be updated
     */
    public void updateCurrent (SingleTree toUpdate);

}
