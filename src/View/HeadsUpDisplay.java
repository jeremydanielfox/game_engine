package View;

import engine.game.Player;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class HeadsUpDisplay implements Observer {

    // note: this should change with screen size, fix it later
    private static final double TEXT_SPACING = 10;

    private Integer myTimeRemaining;
    private Text myScoreDisplay;
    private Text myHealthDisplay;
    private Text myCurrencyDisplay;
    private Player myPlayer; // player is here to keep track of score to respective player

    public HeadsUpDisplay (Player player) {
        myPlayer = player;
        myPlayer.addObserver(this);
        myHealthDisplay = new Text(myPlayer.getHealth() + "");
        myScoreDisplay = new Text(myPlayer.getScore() + "");
        myCurrencyDisplay = new Text(myPlayer.getCurrency() + "");
    }

    // note: consider putting this in constructor and making a getDisplay method
    // note: add a resource file parameter to this method to set the text displays
    public VBox makeDisplay () {
        // this method will create the pane on the screen that displays time, score,and health for
        // the player.
        VBox display = new VBox(TEXT_SPACING);
        // make these set from resource file
//        Text score = new Text("Score: ");
//        Text currency = new Text("Currency: ");
//        Text health = new Text("Health: ");
        // display.getChildren().addAll(score, myScoreDisplay, currency, myCurrencyDisplay, health,
        // myHealthDisplay);
        display.getChildren().addAll(generatePairedBox(new Text("Score: "), myScoreDisplay),
                                     generatePairedBox(new Text("Currency: "), myCurrencyDisplay),
                                     generatePairedBox(new Text("Health: "), myHealthDisplay));
        return display;
    }

    private HBox generatePairedBox (Node a, Node b) {
        HBox toReturn = new HBox(TEXT_SPACING);
        toReturn.getChildren().addAll(a, b);
        return toReturn;
    }

    private void updateTime (int time) {
        myTimeRemaining = time;
    }

    private void updateHealth (int health) {
        myHealthDisplay.setText("" + health);
    }

    private void updateScore (int score) {
        myScoreDisplay.setText("" + score);
    }

    private void updateCurrency (int currency) {
        myCurrencyDisplay.setText("" + currency);
    }

    // note to self - check what arg is
    @Override
    public void update (Observable o, Object arg) {
        // check that o equals myPlayer, if so update appropriate fields
        if (myPlayer.equals(o)) {
            updateScore(myPlayer.getScore());
            updateHealth(myPlayer.getHealth());
            updateCurrency(myPlayer.getCurrency());
        }
    }
}
