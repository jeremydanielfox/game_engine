package engine.gameobject.weapon.upgradable.range;


public class RangeUpgrade implements Range {
    
    private Range decorated;
    private double increment;

    public RangeUpgrade (double increment, Range decorated) {
        this.decorated = decorated;
        this.increment = increment;
    }

    @Override
    public double getRange () {
        return decorated.getRange() + increment;
    }

}
