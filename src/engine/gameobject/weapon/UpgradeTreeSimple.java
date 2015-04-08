package engine.gameobject.weapon;

import java.util.List;


public class UpgradeTreeSimple implements UpgradeTree {
    
    private double value;

    @Override
    public List<UpgradeBundle> getNextUpgrades () {
        // returns only one UpgradeBundle ... the next available UpgradeBundle
        return null;
    }

    @Override
    public double getValue () {
        double result = value;
        // should only return one UpgradeBundle
        for (UpgradeBundle upgrade: getNextUpgrades()){
            result+=upgrade.getValue();
        }
        return result;
    }

}
