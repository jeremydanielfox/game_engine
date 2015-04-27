package engine.gameobject.weapon.upgradetree;

import java.util.ArrayList;
import java.util.List;
import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundle;

public class NullTree implements UpgradeTree {

    @Override
    public double getValue () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<UpgradeBundle> getNextUpgrades () {
        return new ArrayList<UpgradeBundle>();
    }

    @Override
    public void updateCurrent (UpgradeTree ... toUpdate) {
        // TODO Auto-generated method stub

    }

    @Override
    public UpgradeTree clone () {
        // TODO Auto-generated method stub
        return new NullTree();
    }

}
