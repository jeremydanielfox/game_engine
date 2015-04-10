package engine.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import engine.game.Player;
import engine.gameobject.GameObject;
import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundle;
import engine.prototype.Prototype;
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
    private Map<String, Prototype<GameObject>> prototypeMap;
    private Map<String, UpgradeBundle> upgradeMap;
    private final double markup;

    public ShopModel (List<Prototype<GameObject>> prototypes,
                      GameWorld myGameWorld,
                      View myShopView,
                      Player currentPlayer, double markup) {
        this.markup = markup;
        this.currentPlayer = currentPlayer;
        this.myShopView = myShopView;
        prototypeMap = new HashMap<String, Prototype<GameObject>>();
        upgradeMap = new HashMap<String, UpgradeBundle>();
        prototypes.forEach(prototype -> addPrototype(prototype));
    }

    /**
     * Adds a purchasable to the appropriate maps
     * 
     * @param priceTag
     */
    public void addPrototype (Prototype<GameObject> prototype) {
        prototypeMap.put(prototype.getTag().getName(), prototype);
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
    public void purchaseGameObject (String name, double x, double y) {
        myGameWorld.addObject(prototypeMap.get(name).clone());
        currentPlayer.getWallet().withdraw(getPrice(((PriceTag) prototypeMap.get(name).getTag())
                                                   .getValue()));
    }

    /**
     * Applies an upgrade to the given GameObject and subtracts the appropriate amount from the
     * player's wallet.
     * 
     * @param itemGraphic
     */
    public void purchaseUpgrade (String name, GameObject gameObject) {
        currentPlayer.getWallet().withdraw(getPrice(upgradeMap.get(name).getTag().getValue()));
        // gameObject.getWeapon().applyUpgrade()
    }

    public boolean canPurchase (String name) {
        double price;
        if (prototypeMap.containsKey(name)) {
            price = getPrice(((PriceTag) prototypeMap.get(name).getTag()).getValue());
        }
        else {
            price = upgradeMap.get(name).getTag().getValue();
        }
        return currentPlayer.getWallet().getBalance() >= price;
    }
    
    public double getPrice (double value) {
        return value*markup;
    }

}
