package engine.gameobject.weapon.upgradetree;

import java.util.Arrays;


public class TestForest extends UpgradeForest {

    public TestForest () {
        setTrees(Arrays.asList(new TestTree[] { new TestTree(0), new TestTree(1) }));
    }

}
