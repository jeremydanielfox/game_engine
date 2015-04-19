package gae.gameView;

import gae.openingView.OpeningView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main class used to construct a button for the game player to use
 * 
 * @author Brandon Choi
 *
 */

public class ButtonCreator extends Application{
    
    private static final String DROPDOWN_DEFAULT = "Select action here";
    private static final String DEFAULT_NAME_FIELD = "Enter Button name here";
    
    VBox container;
    TextField nameField;
    ComboBox myLambdas;
    
    public ButtonCreator() {
        container = new VBox();
        nameField = new TextField();
        nameField.setText(DEFAULT_NAME_FIELD);
        nameField.setOnMouseClicked(e -> {
            if (!nameField.getText().equals(DEFAULT_NAME_FIELD)){
                nameField.clear();
            }
        });
        
        myLambdas = new ComboBox();
        myLambdas.setPromptText(DROPDOWN_DEFAULT);
    }

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage arg0) throws Exception {
        Stage stage = new Stage();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();
    } 
}
