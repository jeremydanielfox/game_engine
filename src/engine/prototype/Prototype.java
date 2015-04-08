package engine.prototype;

/**
 * 
 * @author Tom Puglisi
 *
 */
public interface Prototype<E> {
    public E clone();
    public String getName();
}
