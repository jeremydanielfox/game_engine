package engine.gameobject.weapon.upgradable.range;


public class RangeSimple implements Range {

//    private double previous = 0;
//    private double current;
    
    private double range;

    public RangeSimple (double range) {
        this.range = range;
    }

    public double getRange () {
        return range;
    }

}
