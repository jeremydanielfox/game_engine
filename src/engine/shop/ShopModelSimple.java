package engine.shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import engine.game.Player;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.gameobject.test.TestTower;
import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundle;
import engine.prototype.Prototype;
import engine.shop.tag.GameObjectTag;
import engine.shop.tag.PriceTag;
import gameworld.GameWorld;
import gameworld.StructurePlacementException;


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

    public ShopModelSimple () {
        markup = 1;
    }

    // For test only
    public ShopModelSimple (GameWorld world, Player player, double markup) {
        // List<Prototype<GameObject>>prototypes =

        this.markup = markup;
        this.myGameWorld = world;
        currentPlayer = player;
        prototypeMap = new HashMap<>();
        upgradeMap = new HashMap<>();
        // prototypes.forEach(prototype -> addPrototype(prototype));
    }

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
                .getShopGraphic(), new TransitionOnClicked(this, prototype
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
     * @param name Name of GameObject
     * @param location Location to be placed
     */
    public boolean purchaseGameObject (String name, PointSimple location) {
        if (canPurchase(name) && checkPlacement(name, location)) {
            currentPlayer.getWallet().withdraw(getPrice(name));
            // myGameWorld.addObject(prototypeMap.get(name).clone());
            try {
                GameObject tower = new TestTower(100, 100, 100);
                //TODO: add listener of some sort to access ShopView?
                myGameWorld.addObject(tower, location);
                return true;
            }
            catch (StructurePlacementException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }
        return false;
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
        currentGameObject.getWeapon().applyUpgrades(upgradeMap.get(name));
        getUpgradeGraphics(currentGameObject);
    }

    public boolean canPurchase (String name) {
        return currentPlayer.getWallet().getBalance() >= getPrice(name);
    }

    public double getPrice (String name) {
        return getPriceTag(name).getValue() * markup;
    }

    @Override
    public RangeDisplay getRangeDisplay (String name) {
        return prototypeMap.get(name).clone().getRangeDisplay();
    }

    @Override
    public Map<ItemInfo, String> getInfo (String name) {
        Map<ItemInfo, String> info = new EnumMap<ItemInfo, String>(ItemInfo.class);
        info.put(ItemInfo.NAME, name);
        info.put(ItemInfo.DESCRIPTION, getPriceTag(name).getDescription());
        info.put(ItemInfo.PRICE, Double.toString(getPrice(name)));
        return info;
    }

    // TODO: account for the possibility of a "name" not in either map
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

    public boolean checkPlacement (String name, PointSimple location) {
        return myGameWorld.isPlaceable(prototypeMap.get(name).getTag().getGraphic().getNode(),
                                      location);
    }

}
