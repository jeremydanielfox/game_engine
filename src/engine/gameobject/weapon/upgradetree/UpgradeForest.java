package engine.gameobject.weapon.upgradetree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import engine.fieldsetting.Settable;
import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundle;


/**
 * A collection of SimpleUpgrade Trees. Essentially the composite pattern used for UpgradeTree.
 *
 * @author Nathan Prabhu
 *
 */
public class UpgradeForest implements UpgradeTree {

    private List<UpgradeTreeSimple> trees;

    public UpgradeForest (UpgradeTreeSimple ... trees) {
        this.trees = new ArrayList<>(Arrays.asList(trees));
    }

    @Settable
    public void setTrees (List<UpgradeTreeSimple> trees) {
        this.trees = trees;
    }

    @Override
    public List<UpgradeBundle> getNextUpgrades () {
        List<UpgradeBundle> result = new ArrayList<UpgradeBundle>();
        trees.forEach(tree -> result.addAll(tree.getNextUpgrades()));
        return result;
    }

    @Override
    public double getValue () {
        return trees.stream().map(UpgradeTree::getValue)
                .reduce( (sum, value) -> sum + value)
                .get();
    }

    @Override
    public void updateCurrent (UpgradeTree ... toUpdate) {
        List<UpgradeTree> updatingTrees = new ArrayList<>(Arrays.asList(toUpdate));
        updatingTrees.forEach(tree -> tree.updateCurrent(new UpgradeTree[0]));
    }

    public UpgradeTree clone () {
        List<UpgradeTreeSimple> treeClones = new ArrayList<>();
        trees.forEach(tree -> treeClones.add((UpgradeTreeSimple) tree.clone()));
        return new UpgradeForest(treeClones.toArray(new UpgradeTreeSimple[trees.size()]));
    }

}
