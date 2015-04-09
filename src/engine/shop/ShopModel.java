package engine.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import engine.game.Player;
import engine.gameobject.GameObject;
import engine.gameobject.weapon.UpgradeBundle;
import engine.grid.StructurePlacementException;
import engine.prototype.PrototypeLocator;
import gameworld.GameWorld;


/**
 * Manages the maps containing the purchasable prototypes
 * 
 * @author Tom Puglisi
 *
 */
public class ShopModel {
    private View myShopView;
    private Player currentPlayer;
    private GameWorld myGameWorld;
    private Map<ItemGraphic, TransitionTower> itemTowerMap;
    private Map<TransitionTower, Purchasable> towerPurchasableMap;
    private Map<ItemGraphic, UpgradeBundle> itemUpgradeMap;
    private Map<ItemGraphic, Purchasable> itemPurchasableMap;

    public ShopModel (List<Purchasable> purchasables,
                      GameWorld myGameWorld,
                      View myShopView,
                      Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        this.myShopView = myShopView;
        itemTowerMap = new HashMap<ItemGraphic, TransitionTower>();
        itemUpgradeMap = new HashMap<ItemGraphic, UpgradeBundle>();
        towerPurchasableMap = new HashMap<TransitionTower, Purchasable>();
        purchasables.forEach(purchasable -> addPurchasable(purchasable));
    }

    public void addPurchasable (Purchasable purchasable) {
        TransitionTower transitionTower = new TransitionTower(null);
        ItemGraphic itemGraphic = new ItemGraphic(null, null);
        itemTowerMap.put(itemGraphic, transitionTower);
        towerPurchasableMap.put(transitionTower, purchasable);
        itemPurchasableMap.put(itemGraphic, purchasable);
    }

    public TransitionTower getTransitionTower (ItemGraphic itemGraphic) {
        return itemTowerMap.get(itemGraphic);
    }

    public void showUpgradeBundles (GameObject gameObject) {
        /*List<UpgradeBundle> bundles = gameObject.getWeapon().getNextUpgrades();
        itemUpgradeMap.clear();
        bundles.forEach(bundle -> itemUpgradeMap.put(new ItemGraphic(null, null), bundle));
        myShopView.display(itemUpgradeMap.keySet());*/
    }

    /**
     * Used to purchase items which are placed on the screen (e.g. towers)
     * 
     * @param transitionTower
     */
    public boolean purchase (TransitionTower transitionTower, double x, double y) {
        try {
            myGameWorld.addObject(PrototypeLocator.getService()
                    .getInstance(towerPurchasableMap.get(transitionTower).getName()));
        }
        catch (StructurePlacementException e) {
            return false;
        }
        currentPlayer.getWallet().withdraw(towerPurchasableMap.get(transitionTower).getPrice());
        return true;
    }

    /**
     * Used to purchase items which are not placed on the screen (upgrades)
     * 
     * @param itemGraphic
     */
    public void purchase (ItemGraphic itemGraphic, GameObject gameObject) {
        currentPlayer.getWallet().withdraw(itemUpgradeMap.get(itemGraphic).getPrice());
        //gameObject.getWeapon().applyUpgrade()
    }
    
    public boolean canPurchase(ItemGraphic itemGraphic) {
        double price;
        if(itemPurchasableMap.containsKey(itemGraphic)) {
            price = itemPurchasableMap.get(itemGraphic).getPrice();
        } else {
            price = itemUpgradeMap.get(itemGraphic).getPrice();
        }
        return currentPlayer.getWallet().getBalance() > price;
    }
}
