package engine.shop;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javafx.event.EventHandler;
import javafx.scene.Node;
import engine.fieldsetting.Settable;
import engine.game.Player;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.gameobject.Purchasable;
import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundle;
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
    private Map<String, Purchasable<GameObject>> purchasableMap = new HashMap<>();
    private Map<String, UpgradeBundle> upgradeMap = new HashMap<>();
    private double markup;
    private GameObject currentGameObject;

    public ShopModelSimple () {
        markup = 1;
    }

    public ShopModelSimple (GameWorld world, Player player, double markup) {
        this.markup = markup;
        myGameWorld = world;
        currentPlayer = player;
        purchasableMap = new HashMap<>();
        upgradeMap = new HashMap<>();
    }

    public ShopModelSimple (List<Purchasable<GameObject>> purchasables,
                            GameWorld currentGameWorld,
                            Player currentPlayer, double markup) {
        this.markup = markup;
        this.currentPlayer = currentPlayer;
        purchasableMap = new HashMap<String, Purchasable<GameObject>>();
        upgradeMap = new HashMap<String, UpgradeBundle>();
        purchasables.forEach(purchasable -> addPurchasable(purchasable));
    }

    @Settable
    public void setMarkup (double markup) {
        this.markup = markup;
    }

    @Settable
    public void setGameWorld (GameWorld world) {
        myGameWorld = world;
    }

    @Settable
    public void setPlayer (Player player) {
        currentPlayer = player;
    }

    @Override
    public void addPurchasable (Purchasable<GameObject> purchasable) {
        purchasableMap.put(purchasable.getName(), purchasable);
    }
    
    @Settable
    public void setPurchasables (List<Purchasable<GameObject>> purchasables) {
        purchasables.forEach(prototype -> addPurchasable(prototype));
    }

    @Override
    public List<ItemGraphic> getItemGraphics () {
        List<ItemGraphic> items = new ArrayList<ItemGraphic>();
        purchasableMap.values().forEach(purchasable -> items.add(new ItemGraphic(purchasable.getName(), purchasable.getShopGraphic())));
        return items;
    }

    @Override
    public List<UpgradeGraphic> getUpgradeGraphics (GameObject gameObject) {
        currentGameObject = gameObject;
        List<UpgradeBundle> bundles = gameObject.getWeapon().getNextUpgrades();
        List<UpgradeGraphic> upgradeGraphics = new ArrayList<>();
        upgradeMap.clear();
        bundles.forEach(bundle -> {
            String name = bundle.getName();
            upgradeMap.put(name, bundle);
            ItemGraphic graphic = new ItemGraphic(name, bundle.getShopGraphic());
            graphic.unGlow();
            upgradeGraphics.add(new UpgradeGraphic(graphic, canPurchase(name), bundle.isFinalUpgrade()));
        });
        return upgradeGraphics;
    }

    /**
     * Purchases and item and places it at the selected position on the screen
     *
     * @param name Name of GameObject
     * @param location Location to be placed
     */
    public boolean purchaseGameObject (String name, PointSimple location, EventHandler selected) {
        if (canPurchase(name) && checkPlacement(name, location)) {
            currentPlayer.getWallet().withdraw(getPrice(name));
            try {
                GameObject tower = purchasableMap.get(name).clone(); 
                //GameObject tower = new TestTower(1, 100, 100);
                tower.getGraphic().getNode().setOnMousePressed(selected);
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
    
    @Override
    public void sellGameObject (GameObject obj) {
        currentPlayer.getWallet().deposit(obj.getValue());
        //myGameWorld.
    }

    /**
     * Applies an upgrade to the given GameObject and subtracts the appropriate amount from the
     * player's wallet.
     *
     * @param itemGraphic
     */
    @Override
    public void purchaseUpgrade (String name, Consumer<GameObject> refreshUpgrades) {
        if (canPurchase(name)) {
            currentPlayer.getWallet().withdraw(getPrice(name));
            currentGameObject.getWeapon().applyUpgrades(upgradeMap.get(name));
            refreshUpgrades.accept(currentGameObject);
        }
    }

    @Override
    public boolean canPurchase (String name) {
        return currentPlayer.getWallet().getBalance() >= getPrice(name);
    }

    public double getPrice (String name) {
        return getPurchasable(name).getValue() * markup;
    }

    @Override
    public RangeDisplay getRangeDisplay (String name) {
        return ((GameObject) purchasableMap.get(name)).getRangeDisplay();
    }

    @Override
    public Map<ItemInfo, String> getInfo (String name) {
        Map<ItemInfo, String> info = new EnumMap<ItemInfo, String>(ItemInfo.class);
        info.put(ItemInfo.NAME, name);
        info.put(ItemInfo.DESCRIPTION, getPurchasable(name).getDescription());
        info.put(ItemInfo.PRICE, Double.toString(getPrice(name)));
        return info;
    }

    // TODO: account for the possibility of a "name" not in either map
    private Purchasable<?> getPurchasable (String name) {
        if (purchasableMap.containsKey(name)) {
            return (Purchasable<?>) purchasableMap.get(name);
        }
        else {
            return (Purchasable<?>) upgradeMap.get(name);
        }
    }

    public enum ItemInfo {
        NAME, DESCRIPTION, PRICE
    }

    @Override
    public boolean checkPlacement (String name, PointSimple location) {
        return myGameWorld.isPlaceable(((GameObject) purchasableMap.get(name)).getGraphic().getNode(),
                                       location);
    }
    
    public GameObject getObjectFromNode (Node node) {
        return myGameWorld.getObjectFromNode(node);
    }
    
    
    public class UpgradeGraphic{
        
        private ItemGraphic graphic;
        private boolean canAfford;
        private boolean isFinal;

        private UpgradeGraphic (ItemGraphic graphic, boolean canAfford, boolean isFinal){
            this.graphic = graphic;
            this.canAfford = canAfford;
            this.isFinal = isFinal;
        }
        
        public ItemGraphic getGraphic(){
            return graphic;
        }
        
        public boolean canAfford(){
            return canAfford;
        }
        
        public boolean isFinal(){
            return isFinal;
        }
    }

}
