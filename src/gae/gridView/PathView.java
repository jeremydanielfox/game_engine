package gae.gridView;

import gae.listView.ContainerWrapper;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


public class PathView {
    private Group root;
    private Scene myScene;
    private ArrayList<Anchor> anchorList;
    private ArrayList<PathSet> pathSetList;
    private int index;
    private StackPane myStack;
    private static ContainerWrapper container;
    private int myID;


    public PathView (StackPane stack, Scene scene) {
        this.myScene = scene;
        myStack = stack;
        anchorList = new ArrayList<>();
        pathSetList = new ArrayList<>();
        root = new Group();
        root.setManaged(false);
        stack.getChildren().add(root);
        StackPane.setAlignment(root, Pos.CENTER);
    }

    public void setContainerArea (ContainerWrapper container) {
        PathView.container = container;
    }

    public void makeBezierCurve () {
        PathSet set = new PathSet(anchorList, myStack, index, container);
        index++;
        root.getChildren().add(set);
        set.setOnMouseEntered(e -> {
            set.changeColor(Color.YELLOW);
            myScene.setOnKeyPressed(f -> {
                if (f.getCode().equals(KeyCode.BACK_SPACE)) {
                    root.getChildren().remove(set);
                    pathSetList.remove(set);
                    setIndices();
                }
            });
        });

        set.setOnMouseExited(e -> {
            set.changeColor(Color.FORESTGREEN);
        });

        System.out.println("I'm adding onto : " + this);
        pathSetList.add(set);
    }

    private void setIndices () {
        for (int i = 0; i < pathSetList.size(); i++) {
            pathSetList.get(i).changeIndex(i);
        }
        index = pathSetList.size();
    }

    public void remakePath () {
        myStack.getChildren().add(root);
    }

    public void resetScreen () {
        myStack.getChildren().remove(root);
    }

    public List<Path> createPathObjects () {
        /*
         * OR, We can store the PathView somewhere and re-visualize that
         */
        List<Path> pathList = new ArrayList<>();
        for (PathSet set : pathSetList) {
            pathList.add(set.getPathObject());
        }

        resetScreen();
        return pathList;
    }

    public void setID (int id) {
        myID=id;
    }
    
    public int getID () {
        return myID;
    }
}
