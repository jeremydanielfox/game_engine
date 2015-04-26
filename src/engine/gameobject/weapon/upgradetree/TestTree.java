package engine.gameobject.weapon.upgradetree;

import java.util.Arrays;
import engine.gameobject.weapon.upgradetree.upgradebundle.TestBundle;
import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundleSimple;


public class TestTree extends UpgradeTreeSimple {

    public TestTree () {
        this(0);
    }
    
    public TestTree(int i) {
        if (i==0){
            setUpgradeBundles(Arrays.asList(new UpgradeBundleSimple[] { new TestBundle(0),
                                                                        new TestBundle(1) }));
        }
        if (i==1){
            setUpgradeBundles(Arrays.asList(new UpgradeBundleSimple[] { new TestBundle(2),
                                                                        new TestBundle(0) }));
        }
    }

}
