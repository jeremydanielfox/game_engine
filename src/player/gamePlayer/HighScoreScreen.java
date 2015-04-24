package player.gamePlayer;

import engine.game.Player;
import voogasalad.util.highscore.HighScoreController;
import voogasalad.util.highscore.HighScoreException;

public class HighScoreScreen {

    private HighScoreController myScores;
    
    public HighScoreScreen(){
         myScores = HighScoreController.getController();
    }
    
    public void displayHighScores(String game, String sortBy, double width, double height){
        try {
            myScores.displayHighScores(game, sortBy, width, height);
        }
        catch (HighScoreException e) {
            //TODO
            System.out.println("issues storing");
        }
    }
    
    public void storeScore(Player p){
        try {
            myScores.saveInstanceScoreData(p.getName(), (int) p.getScore(), p.getName());
        }
        catch (HighScoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
