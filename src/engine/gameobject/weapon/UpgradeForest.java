package engine.gameobject.weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A collection of SimpleUpgrade Trees. Essentially the composite pattern used for UpgradeTree. 
 * @author Nathan Prabhu
 *
 */
public class UpgradeForest implements UpgradeTree {

    private List<UpgradeTreeSimple> trees;
    
    public UpgradeForest (UpgradeTreeSimple... trees){
        this.trees = new ArrayList<>(Arrays.asList(trees));
    }

    @Override
    public List<UpgradeBundle> getNextUpgrades () {
        List<UpgradeBundle> result = new ArrayList<UpgradeBundle>();
        trees.forEach(tree -> result.addAll(tree.getNextUpgrades()));
        return result;
    }

    @Override
    public double getValue () {
        double result = 0;
        for (UpgradeTreeSimple tree: trees){
            result+=tree.getValue();
        }
        return result;
    }

}
