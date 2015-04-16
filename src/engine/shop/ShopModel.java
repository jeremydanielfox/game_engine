package engine.shop;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;
import engine.gameobject.GameObject;
import engine.prototype.Prototype;
import engine.shop.ShopModelSimple.ItemInfo;


public interface ShopModel {
    /**
     * Adds an item to the shop's inventory
     * 
     * @param prototype
     */
    public void addPrototype (Prototype<GameObject> prototype);

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
    public List<ItemGraphic> getUpgradeGraphics (GameObject gameObject);

    /**
     * Purchases the named GameObject and places it at the given position on the screen
     * 
     * @param name
     * @param x
     * @param y
     */
    public void purchaseGameObject (String name, double x, double y);
    
    public void purchaseUpgrade (String name);

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
    public TransitionGameObject getTransitionGameObject (String name);

    /**
     * Provides item information to the view for display
     * 
     * @param name
     */
    public Map<ItemInfo, String> getInfo (String name);
    
    /**
     * Checks if the GameObject associated with the name is placable at the given location
     * @param name
     * @param location
     * @return true if the object is placable
     */
    public boolean checkPlacement (String name, Point2D location);
    
}
