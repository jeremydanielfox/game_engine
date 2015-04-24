package gae.editorView;

import java.util.ArrayList;
import java.util.List;
import gae.editorView.TestEngine;
import xml.DataManager;
import engine.gameobject.PointSimple;
import engine.pathfinding.PathFixed;
import engine.pathfinding.PathSegmentBezier;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import gae.gridView.PathView;
import gae.listView.LibraryData;
import gae.gridView.Path;


public class MoverEditorOpener extends EditorOpener {
    private LibraryData libraryData;
    private VBox optionBox;
    private boolean first = true;
    private List<ComboBox<PathView>> createdDropDownList;

    public void initialize () {
        // TODO Auto-generated method stub
        libraryData = LibraryData.getInstance();
        createdDropDownList = new ArrayList<>();
        super.initialize();
    }

    private void save () {
        createdDropDownList.forEach( (dropDown) -> {
            PathView pathView = dropDown.getSelectionModel().getSelectedItem();
            System.out.println("We have selected : " + pathView.getID());
            List<Path> path = pathView.createPathObjects();
            // we need to add it to an Editable, which will then be converted to GameObjectSimple
            // later

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
            Button view = new Button("View currently selected animation");
            view.setOnAction(e -> setEngineDemo(dropDown.getSelectionModel().getSelectedItem()));

            hbox.getChildren().addAll(dropDown, plus, view);
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

    private void setEngineDemo (PathView selected) {
        if (selected != null) {
            selected.createPathObjects();
            List<Path> list = selected.createPathObjects();
            PathFixed myPath = new PathFixed();
            for (int i = 0; i < list.size(); i++) {
                // System.out.println("Path " + i + "'s coordinates");
                Path temp = list.get(i);
                temp.printInfo();
                // System.out.println();
                PathSegmentBezier tempBez = new PathSegmentBezier();
                List<PointSimple> points = new ArrayList<>();
                points.add(temp.getStart());
                points.add(temp.getControlOne());
                points.add(temp.getControlTwo());
                points.add(temp.getEnd());
                tempBez.setPoints(points);
                myPath.addPathSegment(tempBez);
            }
            DataManager.writeToXML(myPath, "src/gae/listView/Test.xml");
            TestEngine test = new TestEngine();
            try {
                test.start(new Stage());
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // private void deleteOption() {
    // optionBox.getChildren()
    // }

    @Override
    public Parent setUpParent () {
        VBox mainBox = new VBox();
        mainBox.setSpacing(10);
        optionBox = new VBox();
        optionBox.getChildren().add(createHBox());
        Button save = new Button("save");
        save.setOnAction(e -> save());
        mainBox.getChildren().addAll(optionBox, save);
        return mainBox;
    }

    @Override
    public String getTitle () {
        // TODO Auto-generated method stub
        return "Mover Editor";
    }
}
