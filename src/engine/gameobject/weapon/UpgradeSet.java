package engine.gameobject.weapon;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
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

    // maps className to T
    private Map<UpgradeType, T> upgradeMap;
    
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
            return upgradeMap.containsKey(type);
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
        return upgradeMap.put(new UpgradeType(e), e) == null;
    }

    @Override
    public boolean remove (Object o) {
        if (o instanceof Upgrade) {
            UpgradeType type = new UpgradeType((Upgrade) o);
            if (upgradeMap.containsKey(type)) {
                upgradeMap.remove(type);
                return true;
            }
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
    
    private Set<UpgradeType> makeUpgradeTypes (Collection<?> c) {
        return c.stream().filter(o -> (o instanceof Upgrade))
                .map(o -> new UpgradeType((Upgrade) o)).collect(Collectors.toSet());
    }

    @Override
    public void clear () {
        upgradeMap.clear();
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
            buffType = (upg instanceof Buff) ? ((Buff) upg).getType() : BuffType.COLLISION;
        }

        @Override
        public boolean equals (Object obj) {
            if (!(obj instanceof Upgrade)) { return false; }
            Upgrade upg = (Upgrade) obj;
            if (upg instanceof Buff) {
                Buff buff = (Buff) upg;
                return equalsUpgrade(buff) && buffType.equals(buff.getType());
            }
            return equalsUpgrade(upg);
        }

        private boolean equalsUpgrade (Upgrade upg) {
            return upgrade.getClass().getName().equals(upg.getClass().getName());
        }
    }

}
