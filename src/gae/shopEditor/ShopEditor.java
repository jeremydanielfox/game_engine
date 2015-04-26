package gae.shopEditor;

import java.util.ArrayList;
import java.util.List;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimple;
import engine.prototype.Prototype;
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

    public ShopEditor () {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        Text text = new Text("Select Items for Shop");
        text.setFont(Font.font("Verdana", 20));
        vbox.getChildren().addAll(text,
                                  makeObjectChecklist(LibraryData.getInstance()
                                          .getEditableObservableList()));
//        vbox.getChildren().add(tempbutton());
        pane.getChildren().add(vbox);
        pane.setMaxWidth(Screen.getPrimary().getBounds().getWidth());
    }

    @Override
    public Node getObject () {
        return pane;
    }

    private ScrollPane makeObjectChecklist (ObservableList<Authorable> list) {
        ScrollPane pane = new ScrollPane();
        checklist = new ShopCheckList(list);
        pane.setContent(checklist.getCheckList());
        pane.setFitToWidth(true);
        return pane;
    }

    // temporary
//    private Button tempbutton () {
//        Button button = new Button("Print selected");
//
//        button.setOnAction(e -> {
//            for (Placeable obj : (checklist.getSelectedPlaceables()) {
//                System.out.println(obj.getTag().getName());
//            }
//        });
//        return button;
//    }
//    
    
    public ShopModel makeShop(){
        ShopModelSimple shop=new ShopModelSimple();
        List<Prototype<GameObject>> prototypes=new ArrayList<>();
        checklist.getSelectedPlaceables().stream().forEach(e->{
            prototypes.add((Prototype<GameObject>) e.getGameObject());
        });
        shop.setPrototypes(prototypes);
        return shop;
    }
    

}
