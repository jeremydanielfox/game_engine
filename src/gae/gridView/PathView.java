package gae.gridView;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


/**
 * A class that holds lists of previously created PathSets as well as Anchors. The other classes
 * interact with this class to cause changes in the path segments visualized on the screen.
 * 
 * @author Kei
 *
 */
public class PathView {
    private Group root;
    private Scene myScene;
    private ArrayList<Anchor> anchorList;
    private ArrayList<PathSet> pathSetList;
    private int index;
    private StackPane myStack;
    private static ContainerWrapper container;
    private int myID;
    private ObjectProperty<Integer> addPath;
    private ObjectProperty<String> addPathInstructions;

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

    /**
     * Done to have the path coordinates be relative to the grid
     * 
     * @param container
     */
    public void setContainerArea (ContainerWrapper container) {
        PathView.container = container;
    }

    public ObjectProperty<Integer> addPathProperty () {
        return addPath;
    }

    public ObjectProperty<String> addPathInstructionsProperty () {
        return addPathInstructions;
    }

    /**
     * Creates a Bezier curve by creating a PathSet. Also gives it the ability to delete if selected
     */
    public void makeBezierCurve () {
        PathSet set = new PathSet(anchorList, myStack, index, container);
        addPath = set.addPathProperty();
        addPathInstructions = set.addPathInstructionsProperty();
        index++;
        root.getChildren().add(set);
        set.setOnMouseEntered(e -> {
            set.changeColor(Color.YELLOW);
            set.makeVisible(true);
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
            set.makeVisible(false);
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

    /**
     * Called when user needs the objects back on the screen.
     */
    public void remakePath (StackPane stack) {
        stack.getChildren().add(root);
    }

    /**
     * Called when user needs the objects off the screen.
     */
    public void resetScreen (StackPane stack) {
        stack.getChildren().remove(root);
    }

    /**
     * Creates a list of Path objects (a Path object represents a path segment). Essentially
     * represents one pathway.
     * 
     * @return
     */
    public List<Path> createPathObjects () {
        List<Path> pathList = new ArrayList<>();
        for (PathSet set : pathSetList) {
            pathList.add(set.getPathObject());
        }

        resetScreen(myStack);
        return pathList;
    }

    public void setID (int id) {
        myID = id;
    }

    public int getID () {
        return myID;
    }
}
