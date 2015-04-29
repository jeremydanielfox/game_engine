package gae.shopEditor;

import java.util.ArrayList;
import java.util.List;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.Purchasable;
import engine.shop.ShopModel;
import engine.shop.ShopModelSimple;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import gae.backend.Placeable;
import gae.gameView.CheckList;
import gae.gameView.ShopCheckList;
import gae.listView.Authorable;
import gae.listView.LibraryData;
import gae.openingView.UIObject;


public class ShopEditor implements UIObject {

    private Pane pane = new StackPane();

    private ShopCheckList checklist;
    private ShopModelSimple shop;

    public ShopEditor () {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        Text text = new Text("Select Items for Shop");
        text.getStyleClass().add("large-title");
        vbox.getChildren().addAll(text,
                                  makeObjectChecklist(LibraryData.getInstance()
                                          .getEditableObservableList()),
                                  makeSaveButton());
        pane.getChildren().add(vbox);
        pane.setMaxWidth(Screen.getPrimary().getBounds().getWidth());
        
        shop = new ShopModelSimple();
    }

    @Override
    public Node getObject () {
        return pane;
    }

    public ShopModel getShop () {
        return shop;
    }

    private ScrollPane makeObjectChecklist (ObservableList<Authorable> list) {
        ScrollPane pane = new ScrollPane();
        checklist = new ShopCheckList(list);
        pane.setContent(checklist.getCheckList());
        pane.setFitToWidth(true);
        return pane;
    }

    private Button makeSaveButton () {
        Button button = new Button("Save Shop");
        button.setOnAction(e -> {
            shop = makeShop();
        });
        return button;
    }

    private ShopModelSimple makeShop () {
        List<Purchasable<GameObject>> prototypes = new ArrayList<>();
        checklist.getSelectedPlaceables().stream().forEach(e -> {
            prototypes.add((Purchasable<GameObject>) e.getGameObject());
        });
        shop.setPurchasables(prototypes);
        return shop;
    }
}
