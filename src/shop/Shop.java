package shop;

import gameobject.GameObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import shop.upgrade.Upgrade;


public class Shop {
    private Map<ItemGraphic, Purchasable> purchasableMap;

    public Shop (List<Purchasable> purchasables) {
        populatePurchasableMap(purchasables);
    }

    private void populatePurchasableMap (List<Purchasable> purchasables) {
        purchasableMap = new HashMap<ItemGraphic, Purchasable>();
        purchasables.stream().forEach(purchasable -> purchasableMap
                                              .put(new ItemGraphic("tempIcon", "tempTower"),
                                                   purchasable));
    }

    public List<Purchasable> getPurchasables (GameObject gameObject) {
        return null;
    }

}
