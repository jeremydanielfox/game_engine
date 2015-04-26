package engine.gameobject.weapon.upgradetree.upgradebundle;

import engine.gameobject.Graphic;
import engine.gameobject.weapon.Upgrade;
import engine.gameobject.weapon.range.RangeUpgrade;
import engine.shop.ShopTagSimple;


public class TestBundle extends UpgradeBundleSimple {

    public TestBundle (int i) {
        ShopTagSimple shopTag = new ShopTagSimple();
        setShopTag(shopTag);
        if (i == 0) {
            setUpgrades(new Upgrade[] { new RangeUpgrade(30) });
            shopTag.setName("Range Upgrade 1");
        }
        if (i == 1) {
            setUpgrades(new Upgrade[] { new RangeUpgrade(30) });
            shopTag.setName("Range Upgrade 2");
        }
        shopTag.setDescription("Test description");
        shopTag.setShopGraphic(new Graphic(30, 30, "Bloons_DartMonkey.png"));
        setValue(15);
    }
}
