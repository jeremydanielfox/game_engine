package engine.shop;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javafx.event.EventHandler;
import javafx.scene.Node;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.gameobject.Purchasable;
import engine.shop.ShopModelSimple.ItemInfo;
import engine.shop.ShopModelSimple.UpgradeGraphic;
import gameworld.GameWorld;


public interface ShopModel {
    
    
    public void setGameWorld (GameWorld world);
    
    /**
     * Adds an item to the shop's inventory
     *
     * @param prototype
     */
    public void addPurchasable (Purchasable<GameObject> prototype);

    /**
     *
     * @return a list of ItemGraphics corresponding to all objects in the shop's inventory
     */
    public List<ItemGraphic> getItemGraphics ();

    /**
     * Called by the view to determine which ItemGraphics to display
     *
     * @param gameObject
     * @return a list of ItemGraphics to be displayed in the shop
     */
    public List<UpgradeGraphic> getUpgradeGraphics (GameObject gameObject);

    /**
     * Purchases the named GameObject and places it at the given position on the screen
     *
     * @param name
     * @param location
     * @return whether GameObject was purchased or not
     */
    public boolean purchaseGameObject (String name, PointSimple location, EventHandler selected);
    
    public void purchaseUpgrade (String name, Consumer<GameObject> refreshUpgrades);

    /**
     *
     * @param name
     * @return true if the current player has enough currency to purchase the given item
     */
    public boolean canPurchase (String name);

    /**
     *
     * @param name
     * @return a TransitionGameObject corresponding to the given item name
     */
    public RangeDisplay getRangeDisplay (String name);

    /**
     * Provides item information to the view for display
     *
     * @param name
     */
    public Map<ItemInfo, String> getInfo (String name);

    /**
     * Checks if the GameObject associated with the name is placable at the given location
     * 
     * @param name
     * @param location
     * @return true if the object is placable
     */
    public boolean checkPlacement (String name, PointSimple location);

    /**
     * Sells GameObject by removing it from GameWorld and depositing value into Player's account
     * @param obj
     */
    public void sellGameObject (GameObject obj);

    
    public GameObject getObjectFromNode (Node node);
}
