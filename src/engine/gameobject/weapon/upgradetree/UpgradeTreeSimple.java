package engine.gameobject.weapon.upgradetree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.fieldsetting.Settable;
import engine.gameobject.weapon.BasicWeapon;
import engine.gameobject.weapon.upgradetree.upgradebundle.BuildableBundle;
import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundle;
import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundleSimple;


/**
 * UpgradeTreeSimple uses a linked list of UpgradeBundles. There is no branching.
 *
 * @author Nathan Prabhu
 *
 */

@Settable
public class UpgradeTreeSimple implements UpgradeTree {

    private BuildableBundle first;
    private BuildableBundle current;
    private BuildableBundle last;

    public UpgradeTreeSimple () {
    }

    @Settable
    public void setUpgradeBundles (List<? extends BuildableBundle> nodes) {
        buildTree(nodes);
    }

    private void buildTree (List<? extends BuildableBundle> nodes) {
        first = current = nodes.get(0);
        BuildableBundle last = first;
        // take care of first node's parent
        last.setParent(this);

        // iterate from second node onward
        for (BuildableBundle node : nodes.subList(1, nodes.size())) {
            node.setParent(this);

            last.addChild(node);
            last = node;
        }
        this.last = last;
    }

    @Override
    public List<UpgradeBundle> getNextUpgrades () {
        return (current == null) ? Arrays.asList(markFinal()) : Arrays.asList(current);
    }
    
    private BuildableBundle markFinal(){
        last.markFinalUpgrade();
        return last;
    }

    @Override
    public double getValue () {
        return traverseActives(first);
    }

    private double traverseActives (BuildableBundle active) {
        if (active.equals(current)) { return active.getValue(); }
        return active.getValue() + traverseActives(active.getNext());
    }

    @Override
    public void updateCurrent (UpgradeTree ... toUpdate) {
        current = current.isFinalUpgrade() ? current : current.getNext();
    }

    public UpgradeTree clone () {
        UpgradeTreeSimple clone = new UpgradeTreeSimple();
        List<BuildableBundle> clonedList = new ArrayList<>();
        BuildableBundle cloneCurrent = first;
        while (cloneCurrent != null) {
            clonedList.add(cloneCurrent.clone());
            cloneCurrent = cloneCurrent.getNext();
        }
        clone.setUpgradeBundles(clonedList);
        return clone;
    }

}
