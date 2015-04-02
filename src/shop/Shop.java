package shop;

import gameobject.GameObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import shop.upgrade.Upgrade;


public class Shop {
    private Map<ItemGraphic, Purchasable> purchasableMap;
    private Map<Purchasable, ItemGraphic> itemGraphicMap;

    public Shop (List<Purchasable> purchasables) {
        populateMaps(purchasables);
    }

    /**
     * 
     * @param purchasables list of purchasable items to be added to the bidirectional map
     */
    private void populateMaps (List<Purchasable> purchasables) {
        purchasableMap = new HashMap<ItemGraphic, Purchasable>();
        itemGraphicMap = new HashMap<Purchasable, ItemGraphic>();
        purchasables.stream().forEach(purchasable -> purchasableMap
                .put(new ItemGraphic("tempIcon", "tempTower"),
                     purchasable));
        purchasableMap.keySet().stream()
                .forEach(itemGraphic -> purchasables.stream().forEach(purchasable -> itemGraphicMap
                        .put(purchasable, itemGraphic)));
    }

    public List<ItemGraphic> getItemGraphics (GameObject gameObject) {
        List<ItemGraphic> items = new ArrayList<>();
        itemGraphicMap.keySet().stream().forEach(purchasable -> addPurchasable(gameObject, items, purchasable));
        return items;
    }
    
    private void addPurchasable (GameObject gameObject, List<ItemGraphic> items, Purchasable purchasable) {
        if (gameObject.canPurchase(purchasable)) {
            items.add(itemGraphicMap.get(purchasable));
        }
    }

    public Object getPrototypeClone(ItemGraphic itemGraphic) {
        return purchasableMap.get(itemGraphic).clone();
    }
    
    public ItemGraphic getItemGraphic(Purchasable purchasable) {
        return itemGraphicMap.get(purchasable);
    }
}
