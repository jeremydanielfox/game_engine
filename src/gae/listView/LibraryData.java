package gae.listView;

import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverPath;
import engine.gameobject.PointSimple;
import engine.gameobject.labels.Type;
import engine.pathfinding.PathFixed;
import engine.pathfinding.PathSegmentBezier;
import gae.backend.Placeable;
import gae.gridView.Path;
import gae.gridView.PathView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;


/**
 * Library Data class that stores the list of Editables
 *
 * @author Kei
 *
 */
public class LibraryData {
    private static LibraryData instance = new LibraryData();
    private ObservableList<Authorable> editableList = FXCollections.observableArrayList();
    private ObservableList<Authorable> pathList = FXCollections.observableArrayList();
    private Map<Class<?>, ObservableList<Object>> createdObjectMap = new HashMap<>();
    private ArrayList<GameObjectSimple> gameObjectList = new ArrayList<>();
    private ObservableList<Type> labelList = FXCollections.observableArrayList();

    private LibraryData () {
        setEditableList();
    }

    public static LibraryData getInstance () {
        return instance;
    }

    private void setEditableList () {
        editableList.addListener( (ListChangeListener.Change<? extends Authorable> change) -> {
            while (change.next()) {
                if (change.wasAdded()) { // if an editablenode was added
                    Authorable added = change.getAddedSubList().get(0);
                    if (added instanceof Placeable)
                        labelList.add(((Placeable) added).getLabel());
                }
            }
        });
    }

    public ObservableList<Authorable> getEditableObservableList () {
        return editableList;
    }

    public ObservableList<Authorable> getPathObservableList () {
        return pathList;
    }

    public void addCreatedObjectToList (Class<?> klass, Object o) {
        try {
            createdObjectMap.get(klass).add(o);
        }
        catch (NullPointerException e) {
            System.out.println(klass.getSimpleName() + " NOT available");
        }
    }

    public ObservableList<Object> getObservableList (Class<?> klass) {
        if (!createdObjectMap.containsKey(klass)) {
            ObservableList<Object> list = FXCollections.observableArrayList();
            createdObjectMap.put(klass, list);
        }
        return createdObjectMap.get(klass);
    }

    public void addEditableToList (Placeable editable) {
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
        return gameObjectList;
    }

    public ObservableList<Type> getLabelList () {
        return labelList;
    }

    public List<GameObjectSimple> createGameObjectList () {
        for (Authorable authorable : editableList) {
            Placeable editable = (Placeable) authorable;
            GameObjectSimple object = new GameObjectSimple();
            object.setGraphic(new Graphic(editable.getWidth(), editable.getHeight(),
                                          editable.getImagePath()));
            object.setLabel(editable.getLabel());
            object.setTag(editable.getTag());
            object.setMover(getMover(editable));
            object.setPoint(editable.getLocation());
            object.setHealth(new HealthSimple(editable.getHealth()));
            // WHAT IS TAG/LABEL
            // set Collider
            gameObjectList.add(object);
        }
        return gameObjectList;
    }

    private MoverPath getMover (Placeable editable) {
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
