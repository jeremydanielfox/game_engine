package engine.gameobject.weapon.upgradable;

public class FiringRate implements Upgradable {
    private double rate;
    
    public FiringRate() {
        rate = 0;
    }
    
    public FiringRate(double rate){
        this.rate = rate;
    }

    public double getRate () {
       return rate;
    }

}
