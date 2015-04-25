package engine.gameobject.weapon.upgradetree.upgradebundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import engine.fieldsetting.Settable;
import engine.gameobject.weapon.Upgrade;
import engine.gameobject.weapon.UpgradeSet;
import engine.gameobject.weapon.upgradetree.UpgradeTree;
import engine.shop.tag.UpgradeTag;


/**
 * Concrete implementation of BuildableBundle. Each bundle only has at most one child.
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
    private UpgradeTag upgradeTag;
    private double value;

    public UpgradeBundleSimple () {

    }
    
    
    @Override
    public void addChild (Buildable child) {
        next = (UpgradeBundleSimple) child;
    }

    @Settable
    public void setUpgrades (Upgrade ... upgrades) {
        this.upgrades = new ArrayList<>(Arrays.asList(upgrades));
        isFinal = false;
    }

    @Settable
    public void setUpgradeTag (UpgradeTag upgradeTag) {
        this.upgradeTag = upgradeTag;
    }

    @Override
    public void applyUpgrades (UpgradeSet<Upgrade> upgradables) {
        upgrades.forEach(upgrade -> addUpgrade(upgrade, upgradables));
    }

    private void addUpgrade (Upgrade toAdd, UpgradeSet<Upgrade> upgradables) {
        if (upgradables.contains(toAdd)) {
            toAdd.upgrade(upgradables.get(toAdd));
        }
        upgradables.add(toAdd);
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
        return (isFinal) ? null : next;
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
        return upgradeTag;
    }

    @Override
    public double getValue () {
        return getTag().getValue();
    }
    
    @Override
    public BuildableBundle clone () {
        UpgradeBundleSimple clone = new UpgradeBundleSimple();
        List<Upgrade> cloneList = new ArrayList<Upgrade>();
        for (Upgrade u: upgrades){
            cloneList.add(u);
        }
        clone.setUpgrades(cloneList.toArray(new Upgrade[cloneList.size()]));
        clone.setUpgradeTag(upgradeTag.clone());
        return clone;
    }
    

    public List<Upgrade> getUpgrades(){
        return upgrades;
    }

}
