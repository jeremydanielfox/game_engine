package gae.builder;

import java.util.List;
import engine.game.Player;
import engine.game.PlayerUnit;
import engine.shop.wallet.ConcreteWallet;
import engine.shop.wallet.Wallet;

public class PlayerData implements BuildObjectData {

    private Player myPlayer;
    private PlayerUnit healthUnit, scoreUnit;
    private Wallet myWallet;
    private List<String> myData;

    /**
     * Receives a map of the property names to the the map of the fields to the inputs
     * 
     * @param map
     */
    public PlayerData (List<String> answers) {
        myPlayer = new Player();
        healthUnit = new PlayerUnit();
        scoreUnit = new PlayerUnit();
        myWallet = new ConcreteWallet();
        myData = answers;
    }

    @Override
    public Object getBuiltObject () {
        return myPlayer;
    }

    @Override
    public void fillProperties () {
        healthUnit.setLabel(myData.get(0));
        healthUnit.setStartingValue(Double.parseDouble(myData.get(1)));
        scoreUnit.setLabel(myData.get(2));
        scoreUnit.setStartingValue(Double.parseDouble(myData.get(3)));
        myPlayer.setHealth(healthUnit);
        myPlayer.setScore(scoreUnit);
        myPlayer.setWallet(myWallet);
        myPlayer.setWalletUnit(myData.get(4));
    }
}
