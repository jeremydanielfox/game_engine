package engine.gameobject.units;

import javafx.scene.effect.ColorAdjust;
import engine.gameobject.GameObject;
import engine.gameobject.weapon.Upgrade;


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

    public Buff (int duration) {
        this.duration = duration;
        timeSinceStart = 0;
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
    public int timeLeft () {
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
        .setEffect(new ColorAdjust(initialEffect.getHue() + brightness,
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
    @Override
    public abstract Buff clone ();

    public abstract BuffType getBuffType ();
}
