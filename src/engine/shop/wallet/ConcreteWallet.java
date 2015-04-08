package engine.shop.wallet;

import engine.game.PlayerUnit;

public class ConcreteWallet extends Wallet {

    PlayerUnit myUnit;
    
    public ConcreteWallet() {
        myUnit = new PlayerUnit();
    }
    
    public ConcreteWallet(PlayerUnit unit){
        myUnit = unit;
    }
    
    @Override
    public double getBalance () {
        return myUnit.getValue();
    }

    @Override
    public void deposit (double amount) {
        myUnit.changeValue(amount);
    }

    @Override
    public void withdraw (double amount) {
        myUnit.changeValue(amount*(-1));
    }

}
