package engine.gameobject.weapon.upgradable.behavior.damage;


public class DamageUpgrade implements Damage {
    
    private Damage decorated;
    private double increment;

    public DamageUpgrade (double increment, Damage decorated) {
        this.decorated = decorated;
        this.increment = increment;
    }

    @Override
    public double getDamage () {
        return decorated.getDamage() + increment;
    }

}
