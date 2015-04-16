package engine.gameobject.weapon.upgradetree.upgradebundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.fieldsetting.Settable;
import engine.gameobject.weapon.ClassSet;
import engine.gameobject.weapon.Upgrade;
import engine.gameobject.weapon.upgradetree.UpgradeTree;
import engine.shop.tag.UpgradeTag;


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
    private UpgradeTag myUpgradeTag;

    public UpgradeBundleSimple () {

    }

    @Settable
    void setUpgrades (Upgrade ... upgrades) {
        this.upgrades = new ArrayList<>(Arrays.asList(upgrades));
        isFinal = false;
    }

    @Override
    public void applyUpgrades (ClassSet<Upgrade> upgradables) {
        upgrades.forEach(upgrade -> addUpgrade(upgrade, upgradables));
    }

    private void addUpgrade (Upgrade toAdd, ClassSet<Upgrade> upgradables) {
        if (upgradables.contains(toAdd)) {
            toAdd.upgrade(upgradables.get(toAdd));
        }
        upgradables.add(toAdd);
        //TODO: add listeners to buffs?
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
    public UpgradeTag getTag () {
        return myUpgradeTag;
    }

    @Override
    public double getValue () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Settable
    public void setUpgradeTag (UpgradeTag upgradeTag) {
        myUpgradeTag = upgradeTag;
    }

}
