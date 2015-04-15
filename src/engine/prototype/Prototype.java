package engine.prototype;

import engine.shop.tag.GameObjectTag;

/**
 * 
 * @author Tom Puglisi
 *
 */
public interface Prototype<E> {
    public E clone();
    public GameObjectTag getTag ();
    public double getRange ();
    
}
