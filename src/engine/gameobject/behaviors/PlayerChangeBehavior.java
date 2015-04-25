package engine.gameobject.behaviors;

import java.util.HashSet;
import java.util.Set;
import engine.fieldsetting.Settable;
import engine.game.Player;
import engine.gameobject.GameObject;
import gameworld.ObjectCollection;

@Settable
public class PlayerChangeBehavior implements Behavior{

    private Set<Player> playersToChange;
    private int moneyChange;
    private int healthChange;
    private int pointChange;
    
    public PlayerChangeBehavior(){
        playersToChange = new HashSet<Player>();
        moneyChange = 0;
        healthChange = 0;
        pointChange = 0;
    }

    @Override
    public void execute (ObjectCollection world, GameObject object) {
        for (Player player : playersToChange){
            player.changeHealth(healthChange);
            player.changeScore(pointChange);
            player.getWallet().deposit(moneyChange);
        }
    }
    
    @Settable
    public void setMoney(int money){
        moneyChange = money;
    }
    
    @Settable
    public void setPoint(int point){
        pointChange = point;
    }
    
    @Settable
    public void setHealth(int health){
        healthChange = health;
    }
    
    @Settable
    public void addPlayer(Player player){
        playersToChange.add(player);
    }
    
    @Settable
    public void removePlayer(Player player){
        playersToChange.remove(player);
    }
}
