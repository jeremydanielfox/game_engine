package gae.shopEditor;

import java.util.ArrayList;
import java.util.List;
import engine.gameobject.GameObject;
import engine.gameobject.test.TestEnemy;
import engine.gameobject.test.TestTower;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import gae.backend.Placeable;
import gae.gameView.CheckList;
import gae.gameView.ShopCheckList;
import gae.listView.Authorable;
import gae.listView.LibraryData;
import gae.openingView.UIObject;

public class ShopEditor implements UIObject{

    private Pane pane=new StackPane();
    private CheckList checklist;
    public ShopEditor(){
        VBox vbox=new VBox();
        Text text=new Text("Select Items for Shop");
        vbox.getChildren().addAll(text, makeObjectChecklist( LibraryData.getInstance().getEditableObservableList()));
        pane.getChildren().addAll(vbox, tempbutton());
    }
    @Override
    public Node getObject () {
        return pane;
    }
    
    private ScrollPane makeObjectChecklist(ObservableList<Authorable> list){
        ScrollPane pane=new ScrollPane();
        checklist=new ShopCheckList(list);
        pane.setContent(checklist.getCheckList());
        return pane;
    }
    
    //temporary
    private Button tempbutton(){
        Button button=new Button("Print selected");
     
        button.setOnAction(e->{
            for(Placeable obj: ((ShopCheckList) checklist).getSelectedPlaceables()){
                System.out.println(obj.getTag().getName());
            }
        });
        return button;
    }
    
}
