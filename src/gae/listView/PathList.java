package gae.listView;

import gae.gridView.PathView;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


public class PathList {
    private PathView pathView;

    public PathList (PathView pathView) {
        this.pathView = pathView;
    }

    public TitledPane getTitledPane (ObservableList paths, String text) {
        TitledPane pane = new TitledPane();
        pane.setText(text);
        pane.setTextFill(Color.RED);

        final ListView listView = new ListView(paths);
        listView.setPrefSize(200, 250);
        listView.setEditable(true);

        listView.setItems(paths);
        listView.setOnMouseClicked(e -> {
            setOldPath((PathView) listView.getSelectionModel().getSelectedItem());
        });

        listView.setMaxWidth(300);
        pane.setContent(listView);
        return pane;
    }

    private void setOldPath (PathView view) {
        pathView.resetScreen();
        pathView = view;
        pathView.remakePath();
    }
}
