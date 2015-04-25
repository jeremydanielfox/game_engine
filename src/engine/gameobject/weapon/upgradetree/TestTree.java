package engine.gameobject.weapon.upgradetree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.gameobject.weapon.range.RangeUpgrade;
import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundleSimple;


public class TestTree extends UpgradeTreeSimple {

    public TestTree () {
        List<UpgradeBundleSimple> bundles =
                new ArrayList<>(
                                Arrays.asList(new UpgradeBundleSimple[] {
                                                                         new UpgradeBundleSimple(),
                                                                         new UpgradeBundleSimple() }));
        bundles.get(0).setUpgrades(new RangeUpgrade(45));
        bundles.get(0).setValue(15);
        bundles.get(1).setUpgrades(new RangeUpgrade(60));
        bundles.get(1).setValue(30);
        setUpgradeBundles(bundles);
    }

}
