package engine.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import engine.game.Player;
import engine.gameobject.GameObject;
import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundle;
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
    private final double markup;
    private Map<ItemGraphic, TransitionGameObject> itemTransitionMap;
    private Map<TransitionGameObject, Tag> transitionPriceTagMap;
    private Map<ItemGraphic, UpgradeBundle> itemUpgradeMap;
    private Map<ItemGraphic, Tag> itemPurchasableMap;

    public ShopModel (List<PriceTagConcrete> priceTags,
                      GameWorld myGameWorld,
                      View myShopView,
                      Player currentPlayer, double markup) {
        this.markup = markup;
        this.currentPlayer = currentPlayer;
        this.myShopView = myShopView;
        itemTransitionMap = new HashMap<ItemGraphic, TransitionGameObject>();
        itemUpgradeMap = new HashMap<ItemGraphic, UpgradeBundle>();
        transitionPriceTagMap = new HashMap<TransitionGameObject, Tag>();
        priceTags.forEach(purchasable -> addPurchasable(purchasable));
    }

    /**
     * Adds a purchasable to the appropriate maps
     * 
     * @param purchasable
     */
    public void addPurchasable (Tag purchasable) {
        TransitionGameObject transitionGameObject = new TransitionGameObject(null);
        ItemGraphic itemGraphic = new ItemGraphic(null, null);
        itemTransitionMap.put(itemGraphic, transitionGameObject);
        transitionPriceTagMap.put(transitionGameObject, purchasable);
        itemPurchasableMap.put(itemGraphic, purchasable);
    }

    public TransitionGameObject getTransitionGameObject (ItemGraphic itemGraphic) {
        return itemTransitionMap.get(itemGraphic);
    }

    public void showUpgradeBundles (GameObject gameObject) {
        /*
         * List<UpgradeBundle> bundles = gameObject.getWeapon().getNextUpgrades();
         * itemUpgradeMap.clear();
         * bundles.forEach(bundle -> itemUpgradeMap.put(new ItemGraphic(null, null), bundle));
         * myShopView.display(itemUpgradeMap.keySet());
         */
    }

    /**
     * Used to purchase items which are placed on the screen (e.g. towers)
     * 
     * @param transitionGameObject
     */
    public boolean purchase (TransitionGameObject transitionGameObject, double x, double y) {
        try {
            myGameWorld.addObject(PrototypeLocator.getService()
                    .getInstance(transitionPriceTagMap.get(transitionGameObject).getName()));
        }
        catch (StructurePlacementException e) {
            return false;
        }
        currentPlayer.getWallet().withdraw(getPrice(transitionPriceTagMap.get(transitionGameObject)));
        return true;
    }

    /**
     * Used to purchase items which are not placed on the screen (upgrades)
     * 
     * @param itemGraphic
     */
    public void purchase (ItemGraphic itemGraphic, GameObject gameObject) {
        currentPlayer.getWallet().withdraw(getPrice(itemUpgradeMap.get(itemGraphic)));
        // gameObject.getWeapon().applyUpgrade()
    }

    public boolean canPurchase (ItemGraphic itemGraphic) {
        double price;
        if (itemPurchasableMap.containsKey(itemGraphic)) {
            price = getPrice(itemPurchasableMap.get(itemGraphic));
        }
        else {
            price = getPrice(itemUpgradeMap.get(itemGraphic));
        }
        return currentPlayer.getWallet().getBalance() > price;
    }
    
    private double getPrice(Tag purchasable) {
        return purchasable.getValue()*markup;
    }
}
