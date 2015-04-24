package gae.editorView;

import gae.openingView.UIObject;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class SimpleEditorView implements UIObject {
    // NAME, TYPE, IMAGE
    private String[] hardCodeForNow = { "Tower", "Enemy" };
    private VBox vbox;

    public SimpleEditorView () {
        vbox = new VBox();
        vbox.getChildren().addAll(getNameBox(), getTypeBox(), getImageBox());
    }

    private HBox getNameBox () {
        HBox hbox = new HBox();
        Label label = new Label("Name");
        TextField field = new TextField();
        hbox.getChildren().addAll(label, field);
        return hbox;
    }

    private HBox getTypeBox () {
        HBox hbox = new HBox();
        final ToggleGroup group = new ToggleGroup();
        group.selectedToggleProperty()
                .addListener( (observable, oldValue, newValue) -> {
                    // set label as newValue
                });
        for (String type : hardCodeForNow) {
            ToggleButton choice = new ToggleButton(type);
            choice.setToggleGroup(group);
            hbox.getChildren().add(choice);
        }
        return hbox;
    }

    private HBox getImageBox () {
        HBox hbox = new HBox();
        FileChooser fileChooser = new FileChooser();
        Label label = new Label("Image");
        Button chooserOpener = new Button("Load image");
        Stage stage = new Stage();
        chooserOpener.setOnMouseClicked(e -> {
            String path = fileChooser.showOpenDialog(stage).getAbsolutePath();
            // set image
            });
        hbox.getChildren().addAll(label, chooserOpener);
        return hbox;
    }

    private HBox getButtonBox () {
        HBox hbox = new HBox();
        
        return hbox;
    }

    @Override
    public Node getObject () {
        // TODO Auto-generated method stub
        return vbox;
    }
}
