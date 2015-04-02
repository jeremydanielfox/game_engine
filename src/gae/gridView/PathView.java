package gae.gridView;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


public class PathView {
    private Group root;
    private Scene myScene;
    private ArrayList<Anchor> anchorList;
    private ArrayList<PathSet> pathSetList;
    private boolean selected;

    public PathView (StackPane stack, Scene scene) {
        this.myScene = scene;
        anchorList = new ArrayList<>();
        pathSetList = new ArrayList<>();
        root = new Group();
        root.setManaged(false);
        stack.getChildren().add(root);
    }

    public void makeBezierCurve () {
        PathSet set = new PathSet(root, anchorList, myScene);
        root.getChildren().add(set);

        set.setOnMouseEntered(e -> {
            set.changeColor(Color.YELLOW);
            selected = true;
            myScene.setOnKeyPressed(f -> {
                if (f.getCode().equals(KeyCode.BACK_SPACE)) {
                    root.getChildren().remove(set);
                    pathSetList.remove(set);
                }
            });
        });

        set.setOnMouseReleased(e -> {
            set.changeColor(Color.FORESTGREEN);
        });

        pathSetList.add(set);
    }

    public List<Path> createPathObjects () {
        /*
         * TODO: Make sure the use is able to delete specific ones and send the paths in order
         */
        List<Path> pathList = new ArrayList<>();
        for (PathSet set : pathSetList) {
            pathList.add(set.getPathObject());
        }
        return pathList;
    }
}
