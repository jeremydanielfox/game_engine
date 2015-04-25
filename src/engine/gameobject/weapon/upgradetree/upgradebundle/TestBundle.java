package engine.gameobject.weapon.upgradetree.upgradebundle;

import engine.gameobject.weapon.Upgrade;
import engine.gameobject.weapon.range.RangeUpgrade;
import engine.shop.tag.UpgradeTagSimple;


public class TestBundle extends UpgradeBundleSimple {

    public TestBundle () {
        this(0);
    }

    public TestBundle (int i) {
        if (i == 0) {
            setUpgrades(new Upgrade[] { new RangeUpgrade(30) });
            setUpgradeTag(new UpgradeTagSimple());
        }
        if (i == 1) {
            setUpgrades(new Upgrade[] { new RangeUpgrade(30) });
            setUpgradeTag(new UpgradeTagSimple());
        }
    }
}
