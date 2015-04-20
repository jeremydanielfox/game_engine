package engine.gameobject.weapon;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;


/**
 * ClassSet is a collection that contains only one class-type at most, all of which extend from <T>.
 * The motivation behind this class was that Java's Sets can only check for duplication based on
 * hash value, rather than by class-type.
 * 
 * @author Nathan Prabhu
 *
 * @param <T> the type of elements maintained by this set
 */

public class ClassSet<T> implements Set<T> {

    // Dummy value to associate with an Object in the backing Map
    private static final Object PRESENT = new Object();

    // maps className to T
    private Map<String, T> classMap;

    public <T> ClassSet () {
        this.classMap = new HashMap<>();
    }

    public ClassSet (T ... objects) {
        this.classMap = new HashMap<>();
        addAll(objects);
    }

    /**
     * Adds object to set. If object's class already exists, the entry will be replaced.
     * 
     * @param T element to be added to this set
     * @return <tt>true</tt> if this set did not already contain the specified
     *         element
     */
    @Override
    public boolean add (T obj) {
        return classMap.put(obj.getClass().getSimpleName(), obj) == null;
    }

    public void addAll (T ... objects) {
        for (T obj : objects) {
            add(obj);
        }
    }

    /**
     * Removes the specified element from this set if it is present.
     * 
     * @param obj object to be removed from this set, if present
     * @return <tt>true</tt> if the set contained the specified element
     */
    @Override
    public boolean remove (Object obj) {
        String name = obj.getClass().getSimpleName();
        if (classMap.containsKey(name)) {
            classMap.remove(name);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains (Object o) {
        return classMap.keySet().contains(o.getClass().getSimpleName());
    }

    @Override
    public void forEach (Consumer action) {
        classMap.values().forEach(action);
    }

    /**
     * Obtains object of the same class-type in this set if it exists
     * 
     * @param obj object's class-type counterpart to be retrieved from this set, if present
     * @return object if it exists, otherwise null
     */
    public T get (T obj) {
        return classMap.get(obj.getClass().getSimpleName());
    }

    public void clear () {
        classMap.clear();
    }

    @Override
    public int size () {
        return classMap.size();
    }

    @Override
    public boolean isEmpty () {
        return classMap.isEmpty();
    }

    @Override
    public Iterator<T> iterator () {
        return classMap.values().iterator();
    }

    @Override
    public Object[] toArray () {
        return classMap.values().toArray();
    }

    @Override
    public <T> T[] toArray (T[] a) {
        return classMap.values().toArray(a);
    }

    @Override
    public boolean containsAll (Collection<?> c) {
        return classMap.values().containsAll(c);
    }

    @Override
    public boolean addAll (Collection<? extends T> c) {
        Collection<T> copy = new HashSet<T>(classMap.values());
        c.forEach(this::add);
        return classMap.values().containsAll(copy);
    }

    @Override
    public boolean retainAll (Collection<?> c) {
        return classMap.values().retainAll(c);
    }

    @Override
    public boolean removeAll (Collection<?> c) {
        Collection<T> copy = new HashSet<T>(classMap.values());
        c.forEach(this::remove);
        return classMap.values().containsAll(copy);
    }

    @Override
    public String toString () {
        String result = "";
        for (String key : classMap.keySet()) {
            result += String.format("%s: %s \n", key, classMap.get(key));
        }
        return result;
    }

}
