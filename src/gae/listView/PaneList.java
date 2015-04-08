package gae.listView;

import engine.gameobject.Editable;
import gae.backend.TempTower;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import View.ViewUtilities;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;


public abstract class PaneList {
    public static final int THUMBNAIL_SIZE = 20;
    private Pane libraryView = new Pane();
    private Map<String, ObservableList<Editable>> map;
    private Scene scene;

    protected TitledPane getTitledPane (String text) {
        TitledPane pane = new TitledPane();
        pane.setText(text);
        pane.setTextFill(Color.RED);
        return pane;
    }

    protected ObservableList<TitledPane> setAccordion (TitledPane pane) {
        Accordion accordion = new Accordion();
        pane.setContent(accordion);
        return accordion.getPanes();
    }

    public abstract TitledPane initialize (Group root, Node scene);

    public abstract void addToGenericList (EditableNode node);

    public abstract String getType ();

    protected Node createList (ObservableList<Editable> editables) {
        ListView<Editable> list = new ListView<>();

        list.setItems(editables);
        list.setCellFactory(new Callback<ListView<Editable>, ListCell<Editable>>() {
            @Override
            public ListCell<Editable> call (ListView<Editable> p) {
                ListCell<Editable> cell = new ListCell<Editable>() {
                    @Override
                    protected void updateItem (Editable edit, boolean bln) {
                        super.updateItem(edit, bln);
                        if (edit != null) {
                            setGraphic(createCellContent(edit));
                        }
                    }
                };
                return cell;
            }
        });
        return list;
    }

    protected TitledPane setTitledPaneClick (EditableNode node, Group root, Node pane) {
        TitledPane newPane = new TitledPane();
        newPane.setText(node.getName());
        newPane.setContent(createList(node.getChildrenList()));
        newPane.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                ImageView transitionImage = node.getImageView();
                Node binder =
                        ViewUtilities.bindCursor(transitionImage,
                                                 pane,
                                                 ViewUtilities.getMouseLocation(e, transitionImage),
                                                 KeyCode.ESCAPE);

                binder.setOnMousePressed(ev -> {
                    ImageView placedTower = node.getImageView();
                    Group wrapGroup = new Group(placedTower);

                    wrapGroup.relocate(ev.getSceneX(), ev.getSceneY());

                    Point2D current = ViewUtilities.getMouseLocation(e, placedTower, pane);
                    wrapGroup.relocate(current.getX(), current.getY());
                    root.getChildren().add(wrapGroup);

                });
                root.getChildren().add(binder);
                node.getChildrenList().add(new TempTower());
            }
        });
        return newPane;
    }

    protected Node createCellContent (Editable edit) {
        HBox content = new HBox();
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream(edit.getImage())));
        image.setFitHeight(THUMBNAIL_SIZE);
        image.setPreserveRatio(true);
        content.getChildren().addAll(image, new Label(edit.getName()));
        return content;
    }
}
