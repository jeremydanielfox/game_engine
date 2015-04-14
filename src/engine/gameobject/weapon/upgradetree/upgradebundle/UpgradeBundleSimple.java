package engine.gameobject.weapon.upgradetree.upgradebundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import engine.fieldsetting.Settable;
import engine.gameobject.Graphic;
import engine.gameobject.weapon.Upgrade;
import engine.gameobject.weapon.upgradetree.UpgradeTree;
import engine.shop.tag.PriceTag;


/**
 * Concrete implementation of BuildableBundle
 * 
 * @author Nathan Prabhu
 *
 */
@Settable
public class UpgradeBundleSimple implements BuildableBundle {

    private List<Upgrade> upgrades;
    private UpgradeBundleSimple next;
    private boolean isFinal;
    private UpgradeTree parent;
    private PriceTag myUpgradeTag;

    public UpgradeBundleSimple () {

    }

    @Settable
    void setUpgrades (Upgrade ... upgrades) {
        this.upgrades = new ArrayList<>(Arrays.asList(upgrades));
        isFinal = false;
    }

    @Override
    public void applyUpgrades (Map<Class<? extends Upgrade>, Upgrade> upgradables) {
        upgrades.forEach(upgrade -> {
            Class<? extends Upgrade> upgradeType = upgrade.getType();

            // TODO: add Optional to clean this up?
            if (upgradables.keySet().contains(upgradeType)) {
                // upgrade existing upgrade
                upgrade.setDecorated(upgradables.get(upgradeType));
            }
            // create new upgrade
            else {
                upgrade.setDefault();
            }
            // either way, put new upgradable in the map
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
    public PriceTag getTag () {
        return myUpgradeTag;
    }

    @Override
    public double getValue () {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Settable
    public void setUpgradeTag (PriceTag upgradeTag) {
        myUpgradeTag = upgradeTag;
    }

}
