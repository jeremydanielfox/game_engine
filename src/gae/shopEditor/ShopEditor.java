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
import javafx.util.Callback;
import gae.gameView.CheckList;
import gae.openingView.UIObject;

public class ShopEditor implements UIObject{

    private Pane pane=new StackPane();
    private CheckList checklist;
    public ShopEditor(){
        VBox vbox=new VBox();
        
        //for testing
        List<GameObject> list=new ArrayList<>();
        TestTower test=new TestTower(0,0,0);
        TestEnemy tester=new TestEnemy();
        list.add(test);
        list.add(tester);
        list.add(test.clone());
        list.add(tester.clone());
        //System.out.println(test.getTag().getName());
        ObservableList<GameObject> oblist= FXCollections.observableArrayList(new ArrayList<>());
        ListView<GameObject> listview=new ListView<GameObject>(oblist);
        
        vbox.getChildren().add(makeObjectChecklist(list));
        pane.getChildren().addAll(vbox, tempbutton());
    }
    @Override
    public Node getObject () {
        
        return pane;
    }
    
    private ScrollPane makeObjectChecklist(List<GameObject> list){
        ScrollPane pane=new ScrollPane();
        checklist=new CheckList(list);
        pane.setContent(checklist.getCheckList());
        return pane;
    }
    
    //temporary
    private Button tempbutton(){
        Button button=new Button("Print selected");
        button.setOnAction(e->{
            for(GameObject obj: checklist.getSelected()){
                System.out.println(obj.getTag().getName());
            }
        });
        return button;
    }
    
}
