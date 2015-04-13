package engine.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import engine.game.Player;
import engine.gameobject.GameObject;
import engine.gameobject.Graphical;
import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundle;
import engine.prototype.Prototype;
import gameworld.GameWorld;


/**
 * Manages the maps containing the purchasable prototypes. Assumes all given Prototypes contain a
 * PriceTag, and are therefore available for purchase.
 * 
 * @author Tom Puglisi
 *
 */
public class ShopModelSimple implements ShopModel {
    private Player currentPlayer;
    private GameWorld myGameWorld;
    private Map<String, Prototype<GameObject>> prototypeMap;
    private Map<String, UpgradeBundle> upgradeMap;
    private final double markup;

    public ShopModelSimple (List<Prototype<GameObject>> prototypes,
                            GameWorld currentGameWorld,
                            Player currentPlayer, double markup) {
        this.markup = markup;
        this.currentPlayer = currentPlayer;
        prototypeMap = new HashMap<String, Prototype<GameObject>>();
        upgradeMap = new HashMap<String, UpgradeBundle>();
        prototypes.forEach(prototype -> addPrototype(prototype));
    }

    public void addPrototype (Prototype<GameObject> prototype) {
        prototypeMap.put(prototype.getTag().getName(), prototype);
    }

    public List<ItemGraphic> getUpgradeGraphics (GameObject gameObject) {
        /*
         * List<UpgradeBundle> bundles = gameObject.getWeapon().getNextUpgrades();
         * List<ItemGraphic> upgradeGraphics = new ArrayList<ItemGraphic>();
         * upgradeMap.clear();
         * bundles.forEach(bundle -> {
         * upgradeMap.put(bundle.getTag().getName(), bundle);
         * upgradeGraphics.add(new ItemGraphic(bundle.getTag().getName(),
         * bundle.getTag().getShopGraphic()));
         * });
         */
        return null;

    }

    /**
     * Purchases and item and places it at the selected position on the screen
     * 
     * @param transitionGameObject
     */
    public void purchaseGameObject (String name, double x, double y) {
        myGameWorld.addObject(prototypeMap.get(name).clone());
        currentPlayer.getWallet().withdraw(getPrice(name));
    }

    /**
     * Applies an upgrade to the given GameObject and subtracts the appropriate amount from the
     * player's wallet.
     * 
     * @param itemGraphic
     */
    public void purchaseUpgrade (String name, GameObject gameObject) {
        currentPlayer.getWallet().withdraw(getPrice(name));
        // gameObject.getWeapon().applyUpgrade()
    }

    public boolean canPurchase (String name) {
        return currentPlayer.getWallet().getBalance() >= getPrice(name);
    }

    public double getPrice (String name) {
        double value;
        if (prototypeMap.containsKey(name)) {
            value = ((PriceTag) prototypeMap.get(name).getTag()).getValue();
        }
        else {
            value = upgradeMap.get(name).getTag().getValue();
        }
        return value * markup;
    }

    @Override
    public TransitionGameObject getTransitionGameObject (String name) {
        Prototype<GameObject> prototype = prototypeMap.get(name);
        return new TransitionGameObject(prototype.getTag().getName(), ((PriceTag) prototype.getTag()).getGraphic(), prototype.getRange());
    }

    @Override
    public List<ItemGraphic> getItemGraphics () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<ItemInfo, String> getInfo (String name) {
        //public enu
        return null;    
    }
    
    public enum ItemInfo {
        NAME, DESCRIPTION, PRICE
    }

}
