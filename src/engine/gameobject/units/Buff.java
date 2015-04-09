package engine.gameobject.units;

/**
 * Generally, how a buff should works is apply -> advanceTime (until the end of duration) -> unapply. For example,
 * a burning unbuff will (not do anything) -> (take away health every (advanceTime) time) -> (not do anything)
 * @author Danny
 *
 */
public abstract class Buff {
    private int duration;
    private int timeSinceStart;

    public Buff(int duration){
        this.duration=duration;
        timeSinceStart = 0;
    }
    
    /**
     * Applies initial effect to myUnit
     * @param myUnit
     */
    public abstract void apply (BuffableUnit myUnit);

    /**
     * Unapplies the initial effect
     * @param myUnit
     */
    public abstract void unapply (BuffableUnit myUnit);

    /**
     * What the buff does each timeunit
     * @param timePassed
     * @param myUnit
     */
    public void advanceTime (int timePassed, BuffableUnit myUnit) {
        timeSinceStart = timeSinceStart + timePassed;
        changeOverTime(myUnit);
    }

    protected void changeOverTime(BuffableUnit myUnit){
        
    }
    
    /**
     * Returns time left before buff expires
     * @return
     */
    public int timeLeft () {
        return duration - timeSinceStart;
    }

    /**
     * Comparator of buffs
     * @param otherBuff
     * @return whether it is stronger than otherBuff
     */
    public abstract boolean isStrongerBuff(Buff otherBuff);
}
