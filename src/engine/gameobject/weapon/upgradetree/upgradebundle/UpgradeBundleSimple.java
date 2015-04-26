package engine.gameobject.weapon.upgradetree.upgradebundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.fieldsetting.Settable;
import engine.gameobject.Graphic;
import engine.gameobject.weapon.Upgrade;
import engine.gameobject.weapon.UpgradeSet;
import engine.gameobject.weapon.upgradetree.UpgradeTree;
import engine.shop.ShopTag;
import engine.shop.ShopTagSimple;


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
    private double myValue;

    private ShopTag myShopTag;

    public UpgradeBundleSimple () {
        myShopTag = new ShopTagSimple();
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
        if (next == null){
            markFinalUpgrade();
            return null;
        }
        return next;
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
    public BuildableBundle clone () {
        UpgradeBundleSimple clone = new UpgradeBundleSimple();
        List<Upgrade> cloneList = new ArrayList<Upgrade>();
        for (Upgrade u: upgrades){
            cloneList.add(u);
        }
        clone.setUpgrades(cloneList.toArray(new Upgrade[cloneList.size()]));
        clone.setValue(myValue);
        clone.setShopTag(myShopTag.clone());
        return clone;
    }
    
    @Settable
    public void setValue (double value) {
        myValue = value;
    }

    /*
     * Purchasable methods to follow
     */

    @Override
    public String getName () {
        return myShopTag.getName();
    }

    @Override
    public String getDescription () {
        return myShopTag.getDescription();
    }

    @Override
    public Graphic getShopGraphic () {
        return myShopTag.getShopGraphic();
    }

    @Override
    public double getValue () {
        return myValue;
    }
    
    @Settable
    public void setShopTag (ShopTag shopTag) {
        myShopTag = shopTag;
    }
}
