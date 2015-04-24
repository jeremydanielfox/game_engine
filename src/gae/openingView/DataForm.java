package gae.openingView;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * DataForm is the left-hand side of the opening view. It holds all the forms the author must fill
 * out as well as the title header and author button
 *
 * @author Brandon Choi
 *
 */

public class DataForm implements UIObject {

    private static final String BUTTON_TEXT = "LET'S GO!";
    private static final int LABEL_WIDTH = 80;
    private static final Text HEADER = new Text("Ready to create a game?");

    private UIMediator myMediator;
    private VBox form, inputFields;
    private TextField title, author, gameType;
    private TextArea description, instructions;
    private SimpleStringProperty typeText;
    private SimpleStringProperty bindValue;

    public DataForm (UIMediator mediator, SimpleStringProperty binded) {
        myMediator = mediator;
        form = new VBox(30);
        form.setId("dataForm");
        HEADER.setId("welcomeBanner");
        typeText = new SimpleStringProperty();
        bindValue = binded;
        typeText.bind(bindValue);
        createFields();
        form.getChildren().addAll(HEADER, inputFields, createButtonBox());
    }

    @Override
    public Node getObject () {
        return form;
    }

    /**
     * creates all the desired fields and places them in a single VBox
     */
    private void createFields () {
        inputFields = new VBox(10);

        title = new TextField();
        author = new TextField();

        /*
         * gameType is not editable so that when the user selects the game from the hover pictures,
         * the string automatically binds
         */
        gameType = new TextField();
        gameType.setEditable(false);
        gameType.textProperty().bind(typeText);

        description = new TextArea();
        instructions = new TextArea();

        inputFields.getChildren().addAll(createSingleField("Game Title", title),
                                         createSingleField("Author", author),
                                         createSingleField("Game Type", gameType),
                                         createSingleField("Description", description),
                                         createSingleField("Instructions", instructions));

        inputFields.setAlignment(Pos.BASELINE_LEFT);
    }

    /**
     * the following get methods are all public methods for the OpeningView to receive and pull the
     * user inputed text
     *
     * @return
     */
    String getTitle () {
        return title.getText();
    }

    String getAuthor () {
        return author.getText();
    }

    String getGameType () {
        return gameType.textProperty().get();
    }

    String getDescription () {
        return description.getText();
    }

    String getInstructions () {
        return instructions.getText();
    }

    /**
     * checks whether all the forms are empty or not, is a package friendly method
     * 
     * @return
     */
    boolean filledFields () {
        if (getTitle().isEmpty() || getAuthor().isEmpty() ||
            getGameType().equals(OpeningView.DEFAULT_TYPE_MSG) ||
            getDescription().isEmpty() || getInstructions().isEmpty()) {
            return false;
        }
        else if (!getTitle().isEmpty() || !getAuthor().isEmpty() ||
                 !getGameType().equals(OpeningView.DEFAULT_TYPE_MSG) ||
                 !getDescription().isEmpty() || !getInstructions().isEmpty()) {
            return true;
        }

        return false;
    }

    /**
     * creates one single input field
     *
     * @param input
     * @param fieldType
     * @return
     */
    private HBox createSingleField (String input, Node field) {
        HBox box = new HBox(10);
        box.setPrefWidth(600);
        Label label = new Label(input);
        label.setMinWidth(LABEL_WIDTH);
        box.getChildren().addAll(label, field);
        return box;
    }

    /**
     * creates button that will take the user from the opening view to the actual authoring
     * environment
     *
     * @return
     */
    private HBox createButtonBox () {
        HBox box = new HBox();
        box.setPrefWidth(600);
        Button b = new Button(BUTTON_TEXT);
        b.setId("authorButton");
        b.setOnMouseClicked(e -> {
            myMediator.handleEvent(this, e);
        });
        box.setAlignment(Pos.BASELINE_RIGHT);
        box.getChildren().add(b);
        return box;
    }
}
