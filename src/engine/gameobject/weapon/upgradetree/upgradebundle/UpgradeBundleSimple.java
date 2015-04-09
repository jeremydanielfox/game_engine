package engine.gameobject.weapon.upgradetree.upgradebundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import engine.gameobject.Graphic;
import engine.gameobject.weapon.Upgrade;
import engine.gameobject.weapon.upgradable.Upgradable;
import engine.gameobject.weapon.upgradetree.UpgradeTree;


/**
 * Concrete implementation of BuildableBundle
 * 
 * @author Nathan Prabhu
 *
 */
public class UpgradeBundleSimple implements BuildableBundle {

    private List<Upgrade> upgrades;
    private UpgradeBundleSimple next;
    private boolean isFinal;
    private UpgradeTree parent;

    public UpgradeBundleSimple (Upgrade ... upgrades) {
        this.upgrades = new ArrayList<>(Arrays.asList(upgrades));
        isFinal = false;
    }

    @Override
    public void applyUpgrades (Map<Class<? extends Upgradable>, Upgradable> upgradables) {
        upgrades.forEach(upgrade -> {
            Class<? extends Upgradable> upgradeType = upgrade.getType();

            // TODO: add Optional to clean this up?
            if (upgradables.keySet().contains(upgradeType)) {
                // upgrade existing upgradable
                upgrade.setDecorated(upgradables.get(upgradeType));
            }
                else {
                    // create new upgradable
                    upgrade.setDefault();
                }
                // put new upgradable in the map
                upgradables.put(upgradeType, upgradeType.cast(upgrade));
                // TODO: must add listener to Behavior list to update
            });
    }

    @Override
    public void addChild (Buildable child) {
        next = (UpgradeBundleSimple) child;
    }

    @Override
    public boolean isFinalUpgrade () {
        return isFinal;
    }

    @Override
    public void markFinalUpgrade () {
        isFinal = true;
    }

    @Override
    public BuildableBundle getNext () {
        // if isFinal, shop will disallow further purchase and change graphics
        return (isFinal) ? this : next;
    }

    @Override 
    public UpgradeTree getParent () {
        return parent;
    }
    
    @Override
    public void setParent (UpgradeTree tree) {
        parent = tree;
    }
    
    @Override
    public String getName () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getPrice () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getValue () {
        return 0;
    }

    @Override
    public Graphic getGraphic () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDescription () {
        // TODO Auto-generated method stub
        return null;
    }

}
