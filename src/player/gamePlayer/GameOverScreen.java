package player.gamePlayer;

import engine.game.Game;
import voogasalad.util.highscore.HighScoreController;
import voogasalad.util.highscore.HighScoreException;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * 
 * @author Cosette Goldstein
 *
 */
public class GameOverScreen implements GameScene {

    private static final double BUTTON_RESIZE_SCALE_FACTOR=1.25;
    private static final double HEADER_TEXT_RESIZE_SCALE_FACTOR=6.5;
    private static final double RESULTS_TEXT_RESIZE_SCALE_FACTOR=3.5;
    private static final Integer[] HIGH_SCORE_DISPLAY_DIMENSIONS={500,500};
    
    private Stage myStage;
    private Scene myPreviousScene;
    private Scene myScene;
    private Scene myGameSelectScene;
    private Game myGame;
    private Text myResultsText;
    
    public GameOverScreen(Stage stage, Scene previousScene,Scene gameSelectionScene,Game game) {
        myStage=stage;
        myPreviousScene=previousScene;
        myGameSelectScene=gameSelectionScene;
        myGame=game;
        myResultsText=new Text("");
        makeScene();
    }

    /**
     * Adds elements (buttons and texts, specifically) to gridpane of a scene to add to stage.
     */
    private void makeScene () {
        GridPane grid = new GridPane();
        myScene=new Scene(grid);
        VBox vbox=new VBox(45);
        Button btn1=formatButton("Play Again",BUTTON_RESIZE_SCALE_FACTOR);
        btn1.setOnAction(e->myStage.setScene(myPreviousScene));
        Button btn2=formatButton("Select New Game",BUTTON_RESIZE_SCALE_FACTOR);
        btn2.setOnAction(e->myStage.setScene(myGameSelectScene));
        Button btn3=formatButton("View High Scores",BUTTON_RESIZE_SCALE_FACTOR);
        btn3.setOnAction(e->displayScores());
        
        Text headerText=formatText("GAME OVER",HEADER_TEXT_RESIZE_SCALE_FACTOR);
        myResultsText=formatText(getResultsText(),RESULTS_TEXT_RESIZE_SCALE_FACTOR);
        vbox.getChildren().addAll(headerText,myResultsText,btn1,btn2,btn3);
        vbox.setAlignment(Pos.CENTER);
        grid.add(vbox, 0, 0);
        grid.setAlignment(Pos.CENTER);
        myStage.setScene(myScene);
    }

    /**
     * Create and makes specific style changes to buttons including changing size and text color.
     * @param text String to be on button
     * @param scaleFactor amount for button size to be scaled by
     * @return button to be added to Scene in makeScene
     */
    private Button formatButton(String text,double scaleFactor) {
        Button btn=new Button(text);
        btn.setTextFill(Color.RED);
        btn.setScaleX(scaleFactor);
        btn.setScaleY(scaleFactor);
        return btn;
    }
    /**
     * Create and make specific style changes to Texts including changing size.
     * @param exclamation String to be displayed as a Text
     * @param scaleFactor amount for Text size to be scaled by
     * @return Text to be added to Scene in makeScene
     */
    private Text formatText(String exclamation, double scaleFactor) {
        Text text=new Text(exclamation);
        text.setScaleX(scaleFactor);
        text.setScaleY(scaleFactor);
        return text;
    }

    /**
     * Returns the Scene to be displayed in the stage.
     */
    @Override
    public Scene getScene () {
        return myScene;
    }
    /**
     * Method to be called from View class to set the new scene. 
     */
    public void setScene () {
        myStage.setScene(myScene);
    }

    /**
     * HighScoreController instance created to display the list of high scores for the specific game.
     * @throws HighScoreException 
     */
    public void displayScores (){
        HighScoreController scores = HighScoreController.getController();
        try {
            scores.displayHighScores(myGame.getGameName(), "Score:", HIGH_SCORE_DISPLAY_DIMENSIONS[0], HIGH_SCORE_DISPLAY_DIMENSIONS[1]);
        }
        catch (HighScoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * Called by View to set specific text depending on if the game is won or lost.
     * @param text String declaring game won or lost
     */
    public void setResultsText(String text) {
        myResultsText.setText(text);
    }
    
    /**
     * Returns the String version of the Text.
     * @return string text
     */
    private String getResultsText(){
        return myResultsText.toString();
    }
}
