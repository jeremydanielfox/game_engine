package engine.gameobject.weapon.upgradetree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.gameobject.weapon.upgradetree.upgradebundle.TestBundle;
import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundleSimple;


public class TestTree extends UpgradeTreeSimple {

    public TestTree () {
        setUpgradeBundles(Arrays.asList(new UpgradeBundleSimple[] { new TestBundle(0),
                                                                   new TestBundle(1) }));
    }

}
