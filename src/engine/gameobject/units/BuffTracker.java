package engine.gameobject.units;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import engine.gameobject.GameObject;


public class BuffTracker {
    private List<Buff> buffList;
    private Map<Class<? extends Buff>, UpgradeType> immunityList;
    
    public BuffTracker () {
        super();
        buffList = new ArrayList<>();
        immunityList = new HashMap<>();
    }

    /**
     * Makes it so the buff tracker does not process a certain type of buff
     * @param immunity The type of buff you want this object to be immune to
     * @param buffType NULL if any buff type
     */
    public void addImmunity(Class<? extends Buff> immunity, UpgradeType buffType){
        immunityList.put(immunity, buffType);
    }
    
    public void receiveBuff (Buff toAdd, GameObject myObject) {
        if (isImmuneTo(toAdd)){
            return;
        }
        Buff equalBuff = findEqualBuff(toAdd);
        if (equalBuff == null) {
            applyBuff(toAdd, myObject);
        }
        else if (toAdd.isStrongerBuff(equalBuff)) {
            removeBuff(equalBuff, myObject);
            applyBuff(toAdd, myObject);
        }
    }

    /**
     * Advances time of buffs in myObject by increment 1
     * @param myObject
     */
    public void update (GameObject myObject) {
        ArrayList<Buff> removeBuffer = new ArrayList<Buff>();
        for (Buff buff : buffList) {
            if (buff.timeLeft() <= 0) {
                removeBuffer.add(buff);
            }
            buff.advanceTime(1, myObject);
        }
        for (Buff toRemove : removeBuffer) {
            removeBuff(toRemove, myObject);
        }
    }

    private void applyBuff (Buff b, GameObject myObject) {
        b.apply(myObject);
        buffList.add(b);
    }

    private void removeBuff (Buff b, GameObject myObject) {
        buffList.remove(b);
        b.unapply(myObject);
    }

    private Buff findEqualBuff (Buff toAdd) {
        for (Buff b : buffList) {
            if (toAdd.getClass().equals(b.getClass())) {
                return b;
            }
        }
        return null;
    }
    
    private boolean isImmuneTo(Buff newBuff){
        for (Class<? extends Buff> buff : immunityList.keySet()){
            if (buff.isAssignableFrom(newBuff.getClass())){
                UpgradeType immuneType = immunityList.get(buff);
                return immuneType.equals(UpgradeType.NULL) || 
                        immuneType.equals(newBuff.getType());
                }
            }        
        return false;
    }

}
