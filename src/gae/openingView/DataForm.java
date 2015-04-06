package gae.openingView;

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

    public DataForm (UIMediator mediator) {
        myMediator = mediator;
        form = new VBox(30);
        form.setId("dataForm");
        HEADER.setId("welcomeBanner");
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
        inputFields.getChildren().addAll(createSingleField("Game Title", new TextField()),
                                         createSingleField("Author", new TextField()),
                                         createSingleField("Description", new TextArea()),
                                         createSingleField("Instructions", new TextArea()));
        inputFields.setAlignment(Pos.BASELINE_LEFT);
    }

    /**
     * creates one single input field
     * 
     * @param input
     * @param fieldType
     * @return
     */
    private HBox createSingleField (String input, Node fieldType) {
        HBox box = new HBox(10);
        box.setPrefWidth(600);
        Label label = new Label(input);
        label.setMinWidth(LABEL_WIDTH);
        box.getChildren().addAll(label, fieldType);
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
