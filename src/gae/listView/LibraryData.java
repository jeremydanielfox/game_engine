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
import gae.editorView.GameObjectInformation;
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
    private ObservableList<GameObjectSimple> gameObjectList = FXCollections.observableArrayList();
    private ObservableList<Type> labelList = FXCollections.observableArrayList();
    private ObservableList<Object> moverList = FXCollections.observableArrayList();

    private LibraryData () {
        setLists();
    }

    public static LibraryData getInstance () {
        return instance;
    }

    private void setLists () {
        editableList.addListener( (ListChangeListener.Change<? extends Authorable> change) -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    Authorable added = change.getAddedSubList().get(0);
                    if (added instanceof Placeable) {
                        Placeable placeable = (Placeable) added;
                        labelList.add(placeable.getLabel());
                        addToExistingGameObjectList(placeable);
                    }
                }
            }
        });
        pathList.addListener( (ListChangeListener.Change<? extends Authorable> change) -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    Authorable added = change.getAddedSubList().get(0);
                    if (added instanceof PathView) {
                        PathView pathView = (PathView) added;
                        moverList.add(getMover(pathView.createPathObjects()));
                    }
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
        if (createdObjectMap.containsKey(klass)) {
            int index = GameObjectInformation.getInstance().getIndex(o);
            if (index >= 0) {
                createdObjectMap.get(klass).set(index, o);
            }
            else {
                createdObjectMap.get(klass).add(o);
            }
        }
        else {
            ObservableList<Object> list = FXCollections.observableArrayList();
            createdObjectMap.put(klass, list);
            createdObjectMap.get(klass).add(o);
        }
    }

    public ObservableList<Object> getObservableList (Class<?> klass) {
        if (!createdObjectMap.containsKey(klass)) {
            if (!klass.getSimpleName().equals("MoverPath")) {
                ObservableList<Object> list = FXCollections.observableArrayList();
                createdObjectMap.put(klass, list);
            }
            else {
                createdObjectMap.put(klass, moverList);
            }
        }
        return createdObjectMap.get(klass);
    }

    public void addEditableToList (Placeable editable) {
        editableList.add(editable);
    }

    public void addGameObjectToList (Object gameObject) {
        GameObjectToEditable editable = new GameObjectToEditable((GameObject) gameObject);
        editableList.add(editable);
        gameObjectList.add((GameObjectSimple) gameObject);
    }

    public void addPathToList (PathView pathView) {
        pathList.add(pathView);
    }

    public ObservableList<GameObjectSimple> getGameObjectList () {
        return gameObjectList;
    }

    public ObservableList<Type> getLabelList () {
        return labelList;
    }

    private void addToExistingGameObjectList (Authorable authorable) {
        Placeable editable = (Placeable) authorable;
        GameObjectSimple object = new GameObjectSimple();
        object.setPoint(editable.getLocation());
        object.setGraphic(new Graphic(editable.getWidth(), editable.getHeight(),
                                      editable.getImagePath()));
        object.setLabel(editable.getLabel());
        object.setShopTag(editable.getShopTag());
        object.setMover(editable.getPath());
        object.setHealth(new HealthSimple(editable.getHealth()));
        // set Collider
        gameObjectList.add(object);
    }

    private MoverPath getMover (List<Path> list) {
        MoverPath mover = new MoverPath();
        PathFixed myPath = new PathFixed();
        for (int i = 0; i < list.size(); i++) {
            // System.out.println("Path " + i + "'s coordinates");
            Path temp = list.get(i);
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
        mover.setPath(myPath);
        return mover;
    }
}
