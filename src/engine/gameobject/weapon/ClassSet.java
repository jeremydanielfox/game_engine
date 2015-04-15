package engine.gameobject.weapon;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;


public class ClassSet<T> implements Set<T> {

    // Dummy value to associate with an Object in the backing Map
    private static final Object PRESENT = new Object();
    
    Map<String, T> classMap; // className to Object

    public <T> ClassSet () {
        this.classMap = new HashMap<>();

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
        return classMap.put(obj.getClass().getName(), obj) == null;
    }

    public void addAll (T ... objects) {
        for (T obj : objects) {
            add(obj);
        }
    }

    @Override
    public boolean remove (Object obj) {
        return classMap.remove(obj.getClass().getName())==PRESENT;
    }

    @Override
    public boolean contains (Object o) {
        return classMap.values().contains(o);
    }

    public void forEach (Consumer action) {
        classMap.values().forEach(action);
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

}
