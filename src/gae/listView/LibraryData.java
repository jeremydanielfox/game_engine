package gae.listView;

import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverPath;
import engine.gameobject.PointSimple;
import engine.pathfinding.PathFixed;
import engine.pathfinding.PathSegmentBezier;
import gae.backend.Editable;
import gae.gridView.Path;
import gae.gridView.PathView;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Library Data class that stores the list of Editables
 *
 * @author Kei
 *
 */
public class LibraryData {
    private static LibraryData instance = new LibraryData();

    private LibraryData () {
    }

    public static LibraryData getInstance () {
        return instance;
    }

    private ObservableList<Editable> editableList = FXCollections.observableArrayList();
    private ObservableList<PathView> pathList = FXCollections.observableArrayList();

    public ObservableList<Editable> getEditableObservableList () {
        return editableList;
    }

    public ObservableList<PathView> getPathObservableList () {
        return pathList;
    }

    public void addEditableToList (Editable editable) {
        editableList.add(editable);
    }
    
    public void addGameObjectToList (Object gameObject) {
        GameObjectToEditable editable = new GameObjectToEditable((GameObject) gameObject);
        editableList.add(editable);
    }

    public void addPathToList (PathView pathView) {
        pathList.add(pathView);
    }

    public List<GameObjectSimple> getGameObjectList () {
        List<GameObjectSimple> gameObjectList = new ArrayList<>();
        for (Editable editable : editableList) {
            GameObjectSimple object = new GameObjectSimple();
            object.setGraphic(new Graphic(editable.getWidth(), editable.getHeight(),
                                          editable.getImagePath()));
            // object.setLabel(editableNode.getLabel());
            // object.setTag(editableNode.getTag());
            object.setMover(getMover(editable));
            object.setPoint(editable.getLocation());
            object.setHealth(new HealthSimple(editable.getHealth()));
            // WHAT IS TAG/LABEL
            // set Collider
        }
        return gameObjectList;
    }

    private MoverPath getMover (Editable editable) {
        List<List<Path>> allPaths = editable.getPath();
        MoverPath mover = new MoverPath();
        PathFixed myPath = new PathFixed();
        for (List<Path> lists : allPaths) {

            for (int i = 0; i < lists.size(); i++) {
                // System.out.println("Path " + i + "'s coordinates");
                Path temp = lists.get(i);
                temp.printInfo();
                // System.out.println();
                PathSegmentBezier tempBez = new PathSegmentBezier();
                List<PointSimple> points = new ArrayList<>();
                points.add(temp.getStart());
                points.add(temp.getControlOne());
                points.add(temp.getControlTwo());
                points.add(temp.getEnd());
                tempBez.setPoints(points);
                myPath.addPathSegment(tempBez);
            }
        }
        mover.setPath(myPath);
        return mover;
    }
}
