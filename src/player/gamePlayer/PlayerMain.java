package player.gamePlayer;

import gae.gameView.Main;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Responsible for starting the Player
 * 
 * @author Brandon Choi
 *
 */
public class PlayerMain extends Application {

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage arg0) throws Exception {
        Stage myStage = new Stage();
        myStage.setWidth(Main.SCREEN_WIDTH);
        myStage.setHeight(Main.SCREEN_HEIGHT);
        PlayerOpener opener = new PlayerOpener(myStage);
        myStage.setScene(opener.getScene());
        myStage.show();

    } 
}
