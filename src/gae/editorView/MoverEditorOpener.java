package gae.editorView;

import gae.gridView.PathView;
import gae.listView.LibraryData;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MoverEditorOpener extends EditorOpener {
    private static final String TITLE = "Path Editor";
    private LibraryData libraryData;
    private VBox optionBox;
    private boolean first = true;
    private List<ComboBox<PathView>> createdDropDownList;

    @Override
    public void initialize () {
        // TODO Auto-generated method stub
        libraryData = LibraryData.getInstance();
        createdDropDownList = new ArrayList<>();
        Stage s = new Stage();
        Scene scene = new Scene(setVBoxPopup());
        s.setWidth(300);
        s.setHeight(500);
        s.setScene(scene);
        s.setTitle(TITLE);
        s.show();
    }

    private VBox setVBoxPopup () {
        VBox mainBox = new VBox();
        mainBox.setSpacing(10);
        optionBox = new VBox();
        optionBox.getChildren().add(createHBox());
        Button save = new Button("save");
        save.setOnAction(e -> save());
        mainBox.getChildren().addAll(optionBox, save);
        return mainBox;
    }

    private void save () {
        createdDropDownList.forEach( (dropDown) -> {
            PathView pathView = dropDown.getSelectionModel().getSelectedItem();
            System.out.println("We have selected : " + pathView.getID());
            pathView.createPathObjects();
        });
    }

    private HBox createHBox () {
        HBox hbox = new HBox();
        ComboBox<PathView> dropDown = new ComboBox<>(libraryData.getPathObservableList());
        createdDropDownList.add(dropDown);

        dropDown.setButtonCell(new ListCell<PathView>() {
            @Override
            protected void updateItem (PathView pathView, boolean bln) {
                super.updateItem(pathView, bln);
                if (bln) {
                    setText(null);
                    setGraphic(null);
                }
                else if (pathView != null) {
                    HBox content = new HBox();
                    content.getChildren().add(new Label("Path " + pathView.getID()));
                    setGraphic(content);
                }
            }

        });
        dropDown.setCellFactory( (myList) -> {
            return new ListCell<PathView>() {
                @Override
                protected void updateItem (PathView pathView, boolean bln) {
                    super.updateItem(pathView, bln);
                    if (bln) {
                        setText(null);
                        setGraphic(null);
                    }
                    else if (pathView != null) {
                        HBox content = new HBox();
                        content.getChildren().add(new Label("Path " + pathView.getID()));
                        setGraphic(content);
                    }
                }
            };
        });
        if (first) {
            Button plus = new Button("+");
            plus.setOnAction(e -> addMoreOptions());
            hbox.getChildren().addAll(dropDown, plus);
            first = false;
        }
        else {
            hbox.getChildren().addAll(dropDown);
        }
        return hbox;
    }

    private void addMoreOptions () {
        optionBox.getChildren().add(createHBox());
    }

    // private void deleteOption() {
    // optionBox.getChildren()
    // }
}
