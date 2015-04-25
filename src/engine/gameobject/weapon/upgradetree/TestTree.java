package engine.gameobject.weapon.upgradetree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.gameobject.weapon.range.RangeUpgrade;
import engine.gameobject.weapon.upgradetree.upgradebundle.TestBundle;
import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundleSimple;


public class TestTree extends UpgradeTreeSimple {

    public TestTree () {
        List<UpgradeBundleSimple> bundles =
                new ArrayList<>(
                                Arrays.asList(new UpgradeBundleSimple[] {new TestBundle(), new TestBundle(1)}));
        setUpgradeBundles(bundles);
    }

}
