package gae.listView;

import gae.backend.TempTower;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.paint.Color;


public class LeftPaneView {

    private String[] gameObjects = { "Tower", "Enemy" };
    private List<PaneList> listOfListObjects;

    public Scene getScene () {
        Group root = new Group();
        root.getChildren().addAll(view(), tempButton());
        return new Scene(root);
    }

    private Node view () {
        listOfListObjects = new ArrayList<>();
        Accordion accordion = new Accordion();
        for (int i = 0; i < gameObjects.length; i++) {
            try {
                Class<?> className = Class.forName("gae.listView." + gameObjects[i] + "PaneList");
                Object instance = className.getConstructor().newInstance();
                listOfListObjects.add((PaneList) instance);
                Method setUpList = className.getMethod("setUpList");
                accordion.getPanes().add((TitledPane) setUpList
                        .invoke(instance));
            }
            catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                    | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return accordion;
    }

    public void addToList (EditableNode node) {
        for (PaneList paneList : listOfListObjects) {
            if (paneList.getType().equals(node.getType())) {
                paneList.addToGenericList(node);
            }
        }
    }

    private Button tempButton () {
        Button temp = new Button("add to List");
        temp.setTranslateX(200);
        EditableNode node = new EditableNode(new TempTower());
        temp.setOnAction(e -> addToList(node));
        return temp;
    }

    private void addTitledPanesWithList (Accordion accordion) {
        // TODO: must be able to dynamically add to Main TitledPane list (Tower/Enemy/Weapon)
        // TODO: want to connect it to the list but could also just add a TitledPane using the
        // addEditableTolist method
        GameObjectList golist = new GameObjectList();
        for (EditableNode node : golist.getListTemp()) {
            TitledPane tp = new TitledPane();
            /*
             * how is Nina getting the object when the TitledPane is clicked? Is it just getting an
             * image
             * NEED: EditableNode to be the content of this TitledPane so that Nina can click on it
             * and obtain the Editable object
             */
            tp.setText(node.getName());
            tp.setContent(new ListView());
            accordion.getPanes().add(tp);
        }
    }
}
