package gae.gridView;

import java.util.ArrayList;
import java.util.List;
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

    public PathView (StackPane stack, Scene scene) {
        this.myScene = scene;
        myStack = stack;
        anchorList = new ArrayList<>();
        pathSetList = new ArrayList<>();
        root = new Group();
        root.setManaged(false);
        stack.getChildren().add(root);
    }

    public void makeBezierCurve () {
        PathSet set = new PathSet(anchorList, myStack, index);
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

    public List<Path> createPathObjects () {
        /*
         * TODO: I want to be able to keep the group with the paths so I'll probably delet ethe root
         * from the stack and store the root somewhere
         * 
         * OR, We can store the PathView somewhere and re-visualize that
         */
        List<Path> pathList = new ArrayList<>();
        for (PathSet set : pathSetList) {
            pathList.add(set.getPathObject());
        }
        myStack.getChildren().remove(root);
        return pathList;
    }
}
