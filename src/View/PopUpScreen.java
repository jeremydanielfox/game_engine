package View;

/**
 * This class creates a simple pop up screen with a caption and a button.
 *
 * @authors Cosette Goldstein, Sierra Smith
 *
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class PopUpScreen {

    // consider adding lambda expression into constructor to pass to the button set on action
    public void makeScreen (String caption, String buttonCaption) {
        Stage newStage = new Stage();
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid);
        newStage.setHeight(400); // needs to be proportionate to full game screen
        newStage.setWidth(400); // needs to be proportionate to full game screen
        Button button = new Button(buttonCaption);
        button.setOnAction(e -> newStage.close());
        Text text = new Text(caption);
        grid.add(text, 3, 4);
        grid.add(button, 5, 5);
        grid.setAlignment(Pos.CENTER);
        newStage.setScene(scene);
        newStage.showAndWait();
    }
}
