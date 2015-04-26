package engine.gameobject.weapon;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import engine.gameobject.units.UpgradeType;


/**
 * UpgradeSet is a collection that contains only one upgrade-type at most, all of which extend from
 * <T>. The motivation behind this class was that Java's Sets can only check for duplication based
 * on
 * hash value; we want to check uniqueness based on UpgradeType.
 *
 * @author Nathan Prabhu
 *
 * @param <T> the type of elements maintained by this set
 */

public class UpgradeSet<T extends Upgrade> implements ObservableSet<T> {

    // maps UpgradeType (String) to T
    private HashMap<String, T> upgradeMap;
    private ObservableSet<T> obsSet;

    public UpgradeSet () {
        this.upgradeMap = new HashMap<>();
        obsSet = FXCollections.observableSet(upgradeMap.values().stream()
                .collect(Collectors.toSet()));
    }

    /**
     * Obtains object of the same UpgradeType in this set if it exists
     *
     * @param obj object's UpgradeType counterpart to be retrieved from this set, if present
     * @return object if it exists, otherwise null
     */
    public T get (T obj) {
        return upgradeMap.get(new UpgradeWrapper(obj).toString());
    }

    @Override
    public int size () {
        return upgradeMap.size();
    }

    @Override
    public boolean isEmpty () {
        return upgradeMap.isEmpty();
    }

    @Override
    public boolean contains (Object o) {
        if (o instanceof Upgrade) {
            UpgradeWrapper type = new UpgradeWrapper((Upgrade) o);
            return upgradeMap.containsKey(type.toString()) || upgradeMap.containsValue(o);
        }
        return false;
    }

    @Override
    public Iterator<T> iterator () {
        return upgradeMap.values().iterator();
    }

    @Override
    public Object[] toArray () {
        return upgradeMap.values().toArray();
    }

    @Override
    public <T> T[] toArray (T[] a) {
        return upgradeMap.values().toArray(a);
    }

    /**
     * Adds object to set. If object's UpgradeType already exists, the entry will be replaced.
     *
     * @param T element to be added to this set
     * @return <tt>true</tt> if this set did not already contain the specified
     *         element
     */
    @Override
    public boolean add (T e) {
        Upgrade old = upgradeMap.put(new UpgradeWrapper(e).toString(), e);
        if (old != null) {
            old.notifyObservers();
        }
        return old == null;
    }

    @Override
    public boolean remove (Object o) {
        if (o instanceof Upgrade) {
            UpgradeWrapper type = new UpgradeWrapper((Upgrade) o);
            upgradeMap.remove(upgradeMap.get(type));
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll (Collection<?> c) {
        return upgradeMap.values().containsAll(c);
    }

    @Override
    public boolean addAll (Collection<? extends T> c) {
        Collection<T> copy = new HashSet<T>(upgradeMap.values());
        c.forEach(this::add);
        return upgradeMap.values().containsAll(copy);
    }

    @Override
    public boolean retainAll (Collection<?> c) {
        return upgradeMap.keySet().retainAll(makeUpgradeTypes(c));
    }

    @Override
    public boolean removeAll (Collection<?> c) {
        return upgradeMap.keySet().removeAll(makeUpgradeTypes(c));
    }

    private Set<String> makeUpgradeTypes (Collection<?> c) {
        return c.stream().filter(o -> (o instanceof Upgrade))
                .map(o -> new UpgradeWrapper((Upgrade) o))
                .map(UpgradeWrapper::toString)
                .collect(Collectors.toSet());
    }

    @Override
    public void clear () {
        upgradeMap.clear();
    }

    @Override
    public String toString () {
        String result = "";
        for (String key : upgradeMap.keySet()) {
            result += String.format("%s: %s \n", key, upgradeMap.get(key));
        }
        return result;
    }

    /**
     * Defines upgrade uniqueness, according to Class name and UpgradeTypere
     *
     * @author Nathan Prabhu
     *
     */
    private class UpgradeWrapper {
        private Upgrade upgrade;
        private UpgradeType type;

        private UpgradeWrapper (Upgrade upg) {
            this.upgrade = upg;
            type = upg.getType();
        }

        @Override
        public boolean equals (Object obj) {
            if (!(obj instanceof UpgradeSet.UpgradeWrapper)) { return false; }
            UpgradeWrapper arg = (UpgradeSet.UpgradeWrapper) obj;
            boolean bool1 = getUpgClass(upgrade).equals(getUpgClass(arg.getUpgrade()));
            boolean bool2 = type.equals(arg.getType());
            return bool1 && bool2;
        }

        private String getUpgClass (Upgrade upg) {
            return upg.getClass().getSimpleName();
        }

        public Upgrade getUpgrade () {
            return upgrade;
        }

        public UpgradeType getType () {
            return type;
        }

        @Override
        public String toString () {
            String buffTypeString = (type != null) ? type.toString() : "null";
            return String.format("[Upgrade: %s, BuffType: %s]", upgrade.getClass().getSimpleName(),
                                 buffTypeString);
        }
    }

    @Override
    public void addListener (InvalidationListener listener) {
        obsSet.addListener(listener);

    }

    @Override
    public void removeListener (InvalidationListener listener) {
        obsSet.removeListener(listener);
    }

    @Override
    public void addListener (SetChangeListener<? super T> listener) {
        obsSet.addListener(listener);
    }

    @Override
    public void removeListener (SetChangeListener<? super T> listener) {
        obsSet.removeListener(listener);
    }

}
