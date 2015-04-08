package engine.gameobject.units;

import java.util.List;
import engine.gameobject.BasicMover;
import engine.gameobject.GameObjectSimple;


public class BuffableUnit extends GameObjectSimple {
    private List<Buff> buffList;

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
    
    /********
     * advanceBuffs would need to be on the list of things updated every frame/unit of time.
     ********/
    private void advanceBuffs () {
        for (Buff buff : buffList) {
            if (buff.timeLeft() <= 0) {
                removeBuff(buff);
            }
            buff.advanceTime(1, this);
        }
    }

    private void applyBuff(Buff b){
        b.apply(this);
        buffList.add(b);
    }
    
    private void removeBuff(Buff b){
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
