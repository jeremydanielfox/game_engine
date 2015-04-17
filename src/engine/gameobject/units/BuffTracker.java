package engine.gameobject.units;

import java.util.ArrayList;
import java.util.List;

public class BuffTracker{
    private List<Buff> buffList;

    public BuffTracker () {
        super();
        buffList = new ArrayList<>();
    }

    public void addBuff (Buff toAdd) {
        Buff equalBuff = findEqualBuff(toAdd);
        if (equalBuff == null) {
            applyBuff(toAdd);
        }
        else if (toAdd.isStrongerBuff(equalBuff)) {
            removeBuff(equalBuff);
            applyBuff(toAdd);
        }
    }

    public void update () {
        ArrayList<Buff> removeBuffer = new ArrayList<Buff>();
        for (Buff buff : buffList) {
            if (buff.timeLeft() <= 0) {
                removeBuffer.add(buff);
            }
            buff.advanceTime(1, this);
        }
        for (Buff toRemove : removeBuffer) {
            removeBuff(toRemove);
        }
    }

    private void applyBuff (Buff b) {
        b.apply(this);
        buffList.add(b);
    }

    private void removeBuff (Buff b) {
        buffList.remove(b);
        b.unapply(this);
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
