package gae.editorView;

import gae.gridView.NumberTextField;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class HealthEditorOpener extends EditorOpener {

    public void initialize () {
        // TODO Auto-generated method stub
        super.initialize();
    }

    @Override
    public Parent setUpParent () {
        VBox vbox = new VBox();
        Label label = new Label("Set Health");
        TextField setHeight = new NumberTextField();
        Button add = new Button("Set");
        add.setOnAction(e -> {
            try {
                int value = Integer.parseInt(setHeight.getText());
            }
            catch (NumberFormatException exception) {

            }
        });
        vbox.getChildren().addAll(label, setHeight, add);
        return vbox;
    }

    @Override
    public String getTitle () {
        return "Health Editor";
    }
}
