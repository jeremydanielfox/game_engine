package gae.openingView;

import gae.gameView.GameView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DataForm {

    private static final String BUTTON_TEXT = "LET'S GO!";
    private static final int LABEL_WIDTH = 80;
    private static final Text HEADER = new Text("Ready to create a game?");

    private VBox form;
    private VBox inputFields;
    
    private Stage myStage;

    public DataForm (Stage stage) {
        initialize();
        
        myStage = stage;
    }

    public Node getForm () {
        return form;
    }

    private void initialize () {
        form = new VBox(30);
        form.setId("dataForm");
        HEADER.setId("welcomeBanner");
        inputFields = new VBox(10);
        inputFields.getChildren().addAll(createInputField("Game Title", new TextField()),
                                         createInputField("Author", new TextField()),
                                         createInputField("Description", new TextArea()),
                                         createInputField("Instructions", new TextArea()));
        inputFields.setAlignment(Pos.BASELINE_LEFT);
        form.getChildren().addAll(HEADER, inputFields, createButtonBox());
    }

    private HBox createInputField (String input, Node fieldType) {
        HBox box = new HBox(10);
        box.setPrefWidth(600);
        Label label = new Label(input);
        label.setMinWidth(LABEL_WIDTH);
        box.getChildren().addAll(label, fieldType);
        return box;
    }

    private HBox createButtonBox () {
        HBox box = new HBox();
        box.setPrefWidth(600);
        Button b = new Button(BUTTON_TEXT);
        b.setId("authorButton");
        b.setOnMouseClicked(e -> {
            GameView author = new GameView();
            //best way to pass around stage?
            myStage.setScene(author.getScene());
            myStage.show();;
        });
        box.setAlignment(Pos.BASELINE_RIGHT);
        box.getChildren().add(b);
        return box;
    }
}
