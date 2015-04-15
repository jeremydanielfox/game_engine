package engine.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import engine.game.Player;
import engine.gameobject.GameObject;
import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundle;
import engine.prototype.Prototype;
import engine.shop.tag.GameObjectTag;
import engine.shop.tag.PriceTag;
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
    private GameObject currentGameObject;

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

    @Override
    public List<ItemGraphic> getItemGraphics () {
        List<ItemGraphic> items = new ArrayList<ItemGraphic>();
        prototypeMap.values().forEach(prototype -> items.add(new ItemGraphic(prototype.getTag()
                .getName(), ((PriceTag) prototype.getTag())
                .getShopGraphic(), new DragOnClicked(this, prototype
                .getTag().getName()))));
        return items;
    }

    public List<ItemGraphic> getUpgradeGraphics (GameObject gameObject) {
        currentGameObject = gameObject;
        List<UpgradeBundle> bundles = gameObject.getWeapon().getNextUpgrades();
        List<ItemGraphic> upgradeGraphics = new ArrayList<ItemGraphic>();
        upgradeMap.clear();
        bundles.forEach(bundle -> {
            upgradeMap.put(bundle.getTag().getName(), bundle);
            upgradeGraphics.add(new ItemGraphic(bundle.getTag().getName(), bundle.getTag()
                    .getShopGraphic(), new BuyOnClicked(this, bundle.getTag().getName())));
        });
        return upgradeGraphics;
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
    @Override
    public void purchaseUpgrade (String name) {
        currentPlayer.getWallet().withdraw(getPrice(name));
        //currentGameObject.getWeapon().applyUpgrade(upgradeMap.get(name));
    }

    public boolean canPurchase (String name) {
        return currentPlayer.getWallet().getBalance() >= getPrice(name);
    }

    public double getPrice (String name) {
        return getPriceTag(name).getValue() * markup;
    }

    @Override
    public TransitionGameObject getTransitionGameObject (String name) {
        Prototype<GameObject> prototype = prototypeMap.get(name);
        return new TransitionGameObject(prototype.getTag().getName(),
                                        ((GameObjectTag) prototype.getTag()).getGraphic(),
                                        prototype.getRange());
    }

    @Override
    public Map<ItemInfo, String> getInfo (String name) {
        Map<ItemInfo, String> info = new HashMap<ItemInfo, String>();
        info.put(ItemInfo.NAME, name);
        info.put(ItemInfo.DESCRIPTION, getPriceTag(name).getDescription());
        info.put(ItemInfo.PRICE, Double.toString(getPrice(name)));
        return info;
    }

    private PriceTag getPriceTag (String name) {
        if (prototypeMap.containsKey(name)) {
            return (PriceTag) prototypeMap.get(name).getTag();
        }
        else {
            return upgradeMap.get(name).getTag();
        }
    }

    public enum ItemInfo {
        NAME, DESCRIPTION, PRICE
    }

}
