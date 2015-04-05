package engine.game;

import java.util.Observable;
import shop.wallet.Wallet;


/**
 * This class represents a current player of the game, storing player specific information
 * such as lives left/health, score, and currency that can be used in the store.
 * 
 * @author Sierra Smith, Cosette Goldstein
 *
 */
public class Player extends Observable {

    private String myName;
    private PlayerUnit myHealth;
    private PlayerUnit myScore;
    private Wallet myWallet;

    // note: wallet needs to be initialzed with the right player unit before passed in here?
    // alternative: could pass in a string or something to indicate which player unit to assign the
    // wallet
    public Player (String name, PlayerUnit health, PlayerUnit score, Wallet wallet) {
        myHealth = health;
        myScore = score;
        myName = name;
        myWallet = wallet;
    }

    public void setName (String name) {
        myName = name;
    }

    public void changeScore (int toAdd) {
        myScore.changeValue(toAdd);
        setChanged();
        notifyObservers(myScore);
    }

    public void changeHealth (int change) {
        myHealth.changeValue(change);
        setChanged();
        notifyObservers(myHealth);
    }

    public double getScore () {
        return myScore.getValue();
    }

    public double getHealth () {
        return myHealth.getValue();
    }
    
    public Wallet getWallet(){
        return myWallet;
    }
    
    public String getName(){
        return myName;
    }
}
