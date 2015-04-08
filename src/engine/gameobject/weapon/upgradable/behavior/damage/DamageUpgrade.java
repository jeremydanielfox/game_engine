package engine.gameobject.weapon.upgradable.behavior.damage;

import java.util.Optional;
import engine.gameobject.GameObject;
import engine.gameobject.weapon.Upgrade;
import engine.gameobject.weapon.upgradable.Upgradable;


/**
 * Manages a weapon's Damage. It is both an upgrade and an upgradable via the decorator pattern.
 * 
 * 
 * @author Nathan Prabhu
 *
 */
public class DamageUpgrade implements Damage, Upgrade {

    private double increment;
    private Optional<Damage> decorated;

    /**
     * 
     * @param increment The amount to which you upgrade an existing damage, which is held in
     *        decorated
     */
    public DamageUpgrade (double increment) {
        this.increment = increment;
    }

    // returns increment only at base (where decorated would be null)
    @Override
    public double getDamage () {
        return decorated.map(Damage::getDamage).orElse(increment);
    }

    @Override
    public void apply (GameObject target) {
        decorated.ifPresent(damage -> {
            damage.apply(target);
            return;
        });
        // reached base
        target.changeHealth(-getDamage());
    }

    @Override
    public Class<? extends Upgradable> getType () {
        return Damage.class;
    }

    @Override
    public void setDecorated (Upgradable decorated) {
        this.decorated = Optional.of((Damage) decorated);
    }

    @Override
    public void setDefault () {
        this.decorated = Optional.of(new DamageUpgrade(0));
    }
}
