package gae.gameView;

import gae.editor.SimpleEditor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class GenericObjectsPane {

    private StackPane baseNode;

    public GenericObjectsPane () {
        initialize();
    }

    private void initialize () {
        ObservableList<String> data = FXCollections.observableArrayList();

        ListView<String> listView = new ListView<String>(data);
        listView.setPrefSize(200, 250);
        listView.setEditable(true);

        data.addAll("Tower", "Enemy", "Obstacle"); // TODO: Don't hard code these values

        listView.setItems(data);
        listView.setOnMouseClicked(e -> listClicked(e, listView));

        baseNode = new StackPane();
        baseNode.getChildren().add(listView);
    }

    private void listClicked (MouseEvent click, ListView<String> list) {
        if (click.getClickCount() == 2) {
            newCustomObject(list.getSelectionModel().getSelectedItem());
        }
    }

    private void newCustomObject (String type) {
        SimpleEditor editor = new SimpleEditor();
        Scene editorScene = new Scene(new Pane(editor.getObject()));
        Stage editorStage = new Stage();
        editorStage.setScene(editorScene);
        editorStage.show();
    }

    public Node getBaseNode () {
        return baseNode;
    }
}
