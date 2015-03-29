package View;

public class HeadsUpDisplay {

    private Integer myTimeRemaining;
    private Integer myHealth;
    private Integer myScore;
    private Player myPlayer; //player is here to keep track of score to respective player
    
    public HeadsUpDisplay(Player player) {
        myPlayer=player;
    }
    
    private void makeDisplay() {
        //this method will create the pane on the screen that displays time, score,and health for the player.
    }
    
    private void updateTime(int time) {
        myTimeRemaining=time;
    }
    
    private void updateHealth(int health) {
        myHealth=health;
    }
    
    private void updateScore(int score) {
        myScore=score;
    }
}
