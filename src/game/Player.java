package game;

import java.util.Observable;

public class Player extends Observable {

    private int myScore;
    private int myHealth;
    private int myCurrency;
    private String myName;
    
    public Player(String name){
        myName = name;
    }
    
    public Player(String name, int startHealth, int startCurrency, int score){
        myScore = score;
        myHealth = startHealth;
        myCurrency = startCurrency;
        myName = name;
    }
    
    public void setName(String name){
        myName = name;
    }
    
    public void increaseScore(int toAdd){
        myScore += toAdd;
        setChanged();
        notifyObservers();
    }
    
    public void changeHealth(int change){
        myHealth += change;
        setChanged();
        notifyObservers();
    }
    
    public void changeCurrency(int cash){
        myCurrency += cash;
        setChanged();
        notifyObservers();
    }
    
    public int getScore(){
        return myScore;
    }
    
    public int getHealth(){
        return myHealth;
    }
    
    public int getCurrency(){
        return myCurrency;
    }
    
}
