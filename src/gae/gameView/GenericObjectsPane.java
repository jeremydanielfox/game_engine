package gae.gameView;

import engine.gameobject.GameObjectSimple;
import gae.editor.PopUpEditor;
import gae.openingView.UIObject;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GenericObjectsPane implements UIObject {

    private VBox baseNode;
    private Consumer<Object> function;

    public GenericObjectsPane (Consumer<Object> consumer) {
        function = consumer;
        initialize();
    }

    private void initialize () {
        baseNode = new VBox();
        ObservableList<String> data = FXCollections.observableArrayList();

        ListView<String> listView = new ListView<String>(data);
        listView.setPrefSize(200, 250);
        listView.setEditable(true);

        data.addAll("Tower", "Enemy", "Obstacle");

        listView.setItems(data);
        listView.setOnMouseClicked(e -> cellClicked(e, listView.getSelectionModel()
                .getSelectedItem()));

        baseNode.getChildren().addAll(new Text("Generic Objects"), listView);
        baseNode.setPadding(new Insets(5));
        baseNode.setSpacing(5);
    }

    private void cellClicked (MouseEvent e, String selected) {
        if (e.getClickCount() == 2) {
            newCustomObject(GameObjectSimple.class, selected, function);
        }
    }

    public static void newCustomObject (Class<?> klass, String title, Consumer<Object> consumer) {
        Stage editorStage = new Stage();
        Consumer<Object> closeConsumer = e -> {
            consumer.accept(e);
            editorStage.close();
        };
        PopUpEditor editor = new PopUpEditor(klass, title, closeConsumer);
        ScrollPane scroll = new ScrollPane();
        scroll.setPrefSize(300, 500);
        scroll.setContent(editor.getObject());
        Scene editorScene = new Scene(scroll);
        editorStage.setScene(editorScene);
        editorStage.show();
    }

    @Override
    public Node getObject () {
        return baseNode;
    }

}
