package engine.gameobject.weapon.upgradetree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.fieldsetting.Settable;
import engine.gameobject.weapon.upgradetree.upgradebundle.BuildableBundle;
import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundle;


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
    
    public UpgradeTreeSimple() {
    }
    
    @Settable
    public void setUpgradeBundles(List<BuildableBundle> nodes){
        buildTree(nodes);
    }
    
    private void buildTree(List<BuildableBundle> nodes) {
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
        last.markFinalUpgrade();
    }

    @Override
    public List<UpgradeBundle> getNextUpgrades (){
       // will only return one upgrade
       return new ArrayList<UpgradeBundle>(Arrays.asList(current.getNext()));
    }

    @Override
    public double getValue () {
        return traverseActives(first);
    }
    
    private double traverseActives (BuildableBundle active) {
        if (active.equals(current)){
            return active.getValue();
        }
        return active.getValue() + traverseActives(active.getNext());
    }

    public void updateCurrent (UpgradeTree... toUpdate) {
        // ignores parameter
        current = current.getNext();
    }

}
