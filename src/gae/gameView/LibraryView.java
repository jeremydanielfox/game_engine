package gae.gameView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import View.ViewUtilities;
import engine.gameobject.Editable;
import gae.backend.TempTower;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;


// import gameobject.Editable;

public class LibraryView {
    // public void editEditableObject(Editable e);

    public static final int THUMBNAIL_SIZE = 20;
    private Pane libraryView = new Pane();
    private Map<String, ObservableList<Editable>> map;
    private Scene scene;

    public LibraryView (Scene sc, Map<String, ObservableList<Editable>> librarymap) {
        scene = sc;
        map = librarymap;
        libraryView.getChildren().add(makeAccordion());
    }

    public Pane initialize () {
        return libraryView;
    }

    private Accordion makeAccordion () {
        Accordion accordion = new Accordion();
        for (String key : map.keySet()) {
            TitledPane title = new TitledPane(key, createList(map.get(key)));
            setTitledPaneClick(title);
        //    title.setContextMenu(createContextMenu());
            accordion.getPanes().add(title);
        }
        return accordion;
    }

    private Node createList (ObservableList<Editable> editables) {
        ListView<Editable> list = new ListView<>();

        // to be changed to editable in real implementation
        List<Editable> towerlist = new ArrayList<>();
        // towerlist.add(new TempTower("yo"));
        // towerlist.add(new TempTower("mama"));

        // list.setItems(FXCollections.observableList(towerlist));
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
        //setListClick(list);
        return list;

    }

    private void setTitledPaneClick (TitledPane pane) {
        pane.setOnMousePressed(e -> {
            if (e.isSecondaryButtonDown()) {
                createContextMenu(e).show(pane, e.getX(), e.getY());
            }
        });
    }

    // private void setListClick (ListView<Editable> list) {
    // list.setOnMouseClicked(e -> {
    // Editable currentItem = list.getSelectionModel().getSelectedItem();
    // ImageView image =
    // new ImageView(new Image(getClass().getResourceAsStream(currentItem.getImage())));
    // Node binder = ViewUtilities.bindCursor(image,
    // scene,
    // ViewUtilities.getMouseLocation(e, image),
    // KeyCode.Q);
    //
    // binder.setOnMouseClicked(ev -> {
    // Circle xx = new Circle();
    // xx.setRadius(50.0f);
    // xx.setCenterX(ev.getSceneX());
    // xx.setCenterY(ev.getSceneY());
    // libraryView.getChildren().add(xx);
    // });
    // libraryView.getChildren().add(binder);
    // });
    //
    // }
    
  
    
    private ContextMenu createContextMenu(MouseEvent me){
        ContextMenu contextmenu=new ContextMenu();
        MenuItem item=new MenuItem("New");
        item.setOnAction(e->{
            TempTower tower = new TempTower();
            ImageView transitionImage =
                    new ImageView(
                                  new Image(getClass().getResourceAsStream(tower
                                          .getImage())));
            Node binder =
                    ViewUtilities.bindCursor(transitionImage,
                                             scene,
                                             ViewUtilities.getMouseLocation(me, transitionImage),
                                             KeyCode.ESCAPE);

            binder.setOnMouseReleased(ev -> {
                ImageView placedTower =
                        new ImageView(
                                      new Image(getClass().getResourceAsStream(tower
                                              .getImage())));
                Group wrapGroup = new Group(placedTower);
                wrapGroup.relocate(ev.getSceneX(), ev.getSceneY());

                Point2D current = ViewUtilities.getMouseLocation(ev, placedTower);

                wrapGroup.relocate(current.getX(), current.getY());

                libraryView.getChildren().add(wrapGroup);
            //    map.get(pane.getText()).add(tower);
            });
            libraryView.getChildren().add(binder);
        });
        contextmenu.getItems().add(item);
        return contextmenu;
    }

    private Node createCellContent (Editable edit) {
        HBox content = new HBox();
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream(edit.getImage())));
        image.setFitHeight(THUMBNAIL_SIZE);
        image.setPreserveRatio(true);
        content.getChildren().addAll(image, new Label(edit.getName()));
        return content;
    }
}
