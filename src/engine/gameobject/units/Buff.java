package engine.gameobject.units;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.effect.ColorAdjust;
import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import engine.gameobject.weapon.Upgrade;
import engine.observable.Observer;


/**
 * Generally, how a buff should works is apply -> advanceTime (until the end of duration) ->
 * unapply. For example,
 * a burning unbuff will (not do anything) -> (take away health every (advanceTime) time) -> (not do
 * anything)
 *
 * @author Danny
 *
 */
public abstract class Buff implements Upgrade {
    private int duration;
    private int timeSinceStart;
    private UpgradeType type;
    private List<Observer> observers = new ArrayList<>();

    public Buff (double duration) {
        this.duration = (int)(60 * duration);
        timeSinceStart = 0;
        type = UpgradeType.COLLISION; // could be overridden
    }

    /**
     * Applies initial effect to myUnit
     *
     * @param myUnit
     */
    public abstract void apply (GameObject myUnit);

    /**
     * Unapplies the initial effect
     *
     * @param myUnit
     */
    public abstract void unapply (GameObject myUnit);

    /**
     * What the buff does each timeunit
     *
     * @param timePassed
     * @param myUnit
     */
    public void advanceTime (int timePassed, GameObject myUnit) {       
        timeSinceStart = timeSinceStart + timePassed;
        changeOverTime(myUnit);
    }

    protected void changeOverTime (GameObject myUnit) {

    }

    protected int getDuration () {
        return duration;
    }

    /**
     * Returns time left before buff expires
     *
     * @return
     */
    public double timeLeft () {
        return getDuration() - timeSinceStart;
    }

    /**
     * Simple implementation of a graphic effect.
     * 
     * @param myUnit
     * @param hue
     * @param saturation
     * @param brightness
     * @param contrast
     */
    protected void adjustEffect (GameObject myObject,
                                 double hue,
                                 double saturation,
                                 double brightness,
                                 double contrast) {
        ColorAdjust initialEffect = new ColorAdjust(0, 0, 0, 0);
        if (myObject.getGraphic().getNode().getEffect() != null) {
            initialEffect = (ColorAdjust) myObject.getGraphic().getNode().getEffect();
        }
        myObject.getGraphic().getNode()
        .setEffect(new ColorAdjust(initialEffect.getHue() + hue,
                                   initialEffect.getSaturation() + saturation,
                                   initialEffect.getBrightness() + brightness,
                                   initialEffect.getContrast() + contrast));
    }

    /**
     * Comparator of buffs
     *
     * @param otherBuff
     * @return whether it is stronger than otherBuff
     */
    public abstract boolean isStrongerBuff (Buff otherBuff);

    /**
     * Reproduces buff. Must be defined in each buff made
     */
    
    public abstract Buff clone ();
    
    @Override
    public void addObserver (Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver (Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers () {
        observers.forEach(obs -> obs.update());
    }
    
    @Settable
    public void setType (UpgradeType type) {
        this.type = type;
    }
    
    public UpgradeType getType () {
        return type;
    }

}
