package engine.gameobject.weapon;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import engine.gameobject.units.Buff;
import engine.gameobject.units.BuffType;


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

public class UpgradeSet<T extends Upgrade> implements Set<T> {

    // maps UpgradeType (String) to T
    private HashMap<String, T> upgradeMap;

    public <T> UpgradeSet () {
        this.upgradeMap = new HashMap<>();
    }

    public UpgradeSet (T ... objects) {
        this.upgradeMap = new HashMap<>();
        addAll(Arrays.asList(objects));
    }

    /**
     * Obtains object of the same UpgradeType in this set if it exists
     * 
     * @param obj object's class-type counterpart to be retrieved from this set, if present
     * @return object if it exists, otherwise null
     */
    public T get (T obj) {
        return upgradeMap.get(new UpgradeType(obj));
    }

    public void addListener (SetChangeListener<T> listener) {
        ObservableSet<T> obs =
                FXCollections.observableSet(upgradeMap.values().stream()
                        .collect(Collectors.toSet()));
        obs.addListener(listener);
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
            UpgradeType type = new UpgradeType((Upgrade) o);
            return upgradeMap.containsKey(type.toString());
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
        UpgradeType toAdd = new UpgradeType(e);
        return upgradeMap.put(toAdd.toString(), e) == null;
    }

    @Override
    public boolean remove (Object o) {
        if (o instanceof Upgrade) {
            UpgradeType type = new UpgradeType((Upgrade) o);
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
                .map(o -> new UpgradeType((Upgrade) o))
                .map(UpgradeType::toString)
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
     * Defines upgrade uniqueness. For non-buffs, uniqueness is defined by class name. For buffs,
     * they must additionally be unique according to BuffType.
     * 
     * @author Nathan Prabhu
     *
     */
    private class UpgradeType {
        private Upgrade upgrade;
        private BuffType buffType;

        private UpgradeType (Upgrade upg) {
            this.upgrade = upg;
            buffType = (upg instanceof Buff) ? ((Buff) upg).getBuffType() : BuffType.NULL;
        }

        @Override
        public boolean equals (Object obj) {
            if (!(obj instanceof UpgradeSet.UpgradeType)) { return false; }
            UpgradeType type = (UpgradeSet.UpgradeType) obj;
            boolean bool1 = getUpgClass(upgrade).equals(getUpgClass(type.getUpgrade()));
            boolean bool2 = buffType.equals(type.getBuffType());
            return bool1 && bool2;
        }

        private String getUpgClass (Upgrade upg) {
            return upg.getClass().getSimpleName();
        }

        public Upgrade getUpgrade () {
            return upgrade;
        }

        public BuffType getBuffType () {
            return buffType;
        }

        @Override
        public String toString () {
            String buffTypeString = (buffType != null) ? buffType.toString() : "null";
            return String.format("[Upgrade: %s, BuffType: %s]", upgrade.getClass().getSimpleName(),
                                 buffTypeString);
        }
    }

}
