package engine.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import View.Displayable;
import engine.fieldsetting.Settable;
import engine.shop.wallet.ConcreteWallet;
import engine.shop.wallet.Wallet;


/**
 * This class represents a current player of the game, storing player specific information
 * such as lives left/health, score, and currency that can be used in the store.
 *
 * @author Sierra Smith
 * @author Cosette Goldstein
 * @author Brandon Choi
 *
 */
@Settable
public class Player extends Observable implements Observer {

    private static final String DEFAULT_NAME = "Player 1";

    private String myName;
    private PlayerUnit myHealth;
    private PlayerUnit myScore;
    private Wallet myWallet;

    public Player () {
        PlayerUnit defaultScore = new PlayerUnit();
        intialize(DEFAULT_NAME, new PlayerUnit(), defaultScore, new ConcreteWallet(defaultScore));
    }

    public Player (String name, PlayerUnit health, PlayerUnit score, Wallet wallet) {
        intialize(name, health, score, wallet);
    }
    
    private void intialize(String name, PlayerUnit health, PlayerUnit score, Wallet wallet){
        myHealth = health;
        myScore = score;
        myName = name;
        myWallet = wallet;
        myHealth.addObserver(this);
        myScore.addObserver(this);
    }

    public void setName (String name) {
        myName = name;
    }

    /**
     * Adds the specifies amount to the player's score unit.
     *
     * @param toAdd
     */
    public void changeScore (int toAdd) {
        myScore.changeValue(toAdd);
        setChanged();
        notifyObservers(myScore);
        System.out.println("Score is now: " + myScore);
    }

    /**
     * Adds the specified amount to the player's health unit.
     *
     * @param change
     */
    public void changeHealth (int change) {
        myHealth.changeValue(change);
        setChanged();
        notifyObservers(myHealth);
        System.out.println("Score is now: " + myHealth);
    }

    public double getScore () {
        return myScore.getValue();
    }

    public double getHealth () {
        return myHealth.getValue();
    }

    public Wallet getWallet () {
        return myWallet;
    }

    public String getName () {
        return myName;
    }

    /**
     * Returns a list of items from the player that implement the displayable interface.
     *
     * @return
     */
    public List<Displayable> getDisplayables () {
        List<Displayable> toReturn = new ArrayList<>();
        toReturn.add(myHealth);
        toReturn.add(myScore);
        return toReturn;
    }

    @Settable
    public void setHealth (PlayerUnit health) {
        myHealth = health;
        myHealth.addObserver(this);
    }

    @Settable
    public void setScore (PlayerUnit score) {
        myScore = score;
        myScore.addObserver(this);
    }

    public void setWallet (Wallet wallet) {
        myWallet = wallet;
    }
    
    @Settable
    public void setWalletUnit (String unitLabel) {
        if(myHealth.getLabel().equals(unitLabel))
            myWallet = new ConcreteWallet(myHealth);
        else
            myWallet = new ConcreteWallet(myScore);
    }

    @Override
    public void update (Observable o, Object arg) {
        setChanged();
        notifyObservers(myScore);
    }
}
