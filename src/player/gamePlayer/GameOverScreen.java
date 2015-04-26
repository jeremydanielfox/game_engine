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
 * @author goldstein
 *
 */
public class GameOverScreen implements GameScene {

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

    private void makeScene () {
        GridPane grid = new GridPane();
        myScene=new Scene(grid);
        VBox vbox=new VBox(45);
        Button btn1=formatButton("Play Again",1.25);
        btn1.setOnAction(e->myStage.setScene(myPreviousScene));
        Button btn2=formatButton("Select New Game",1.25);
        btn2.setOnAction(e->myStage.setScene(myGameSelectScene));
        Button btn3=formatButton("View High Scores",1.25);
        btn3.setOnAction(e->displayScores());
        
        Text headerText=formatText("GAME OVER",6.5);
        myResultsText=formatText(getResultsText(),3.5);
        vbox.getChildren().addAll(headerText,myResultsText,btn1,btn2,btn3);
        vbox.setAlignment(Pos.CENTER);
        grid.add(vbox, 0, 0);
        grid.setAlignment(Pos.CENTER);
        myStage.setScene(myScene);
    }

    private Button formatButton(String text,double scaleFactor) {
        Button btn=new Button(text);
        btn.setTextFill(Color.RED);
        btn.setScaleX(scaleFactor);
        btn.setScaleY(scaleFactor);
        return btn;
    }
    
    private Text formatText(String exclamation, double scaleFactor) {
        Text text=new Text(exclamation);
        text.setScaleX(scaleFactor);
        text.setScaleY(scaleFactor);
        return text;
    }

    @Override
    public Scene getScene () {
        // TODO Auto-generated method stub
        return myScene;
    }
    
    public void setScene () {
        // TODO Auto-generated method stub
        myStage.setScene(myScene);
    }

    public void displayScores () {
        HighScoreController scores = HighScoreController.getController();
        try {
            scores.displayHighScores(myGame.getGameName(), "Score:", 500, 500);
        }
        catch (HighScoreException e) {
            // TODO Auto-generated catch block
            System.out.println("Issue with displaying high scores.");
            e.printStackTrace();
        }
    }
    
    public void setResultsText(String text) {
        myResultsText.setText(text);
    }
    
    private String getResultsText(){
        //myResultsText,formatText()
        return myResultsText.toString();
    }
}
