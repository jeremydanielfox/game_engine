package engine.gameobject.units;

import java.util.ArrayList;
import java.util.List;
import engine.gameobject.GameObject;

public class BuffTracker{
    private List<Buff> buffList;

    public BuffTracker () {
        super();
        buffList = new ArrayList<>();
    }

    public void receiveBuff (Buff toAdd, GameObject myObject) {
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

}
