package gae.listView;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.PointSimple;
import engine.gameobject.labels.Type;
import engine.pathfinding.PathFixed;
import engine.pathfinding.PathSegmentBezier;
import gae.backend.Placeable;
import gae.editor.EditingParser;
import gae.editorView.GameObjectInformation;
import gae.gridView.AuthoringPath;
import engine.pathfinding.Path;
import gae.gridView.PathView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;


/**
 * Library Data class that stores the list of Objects
 *
 */
public class LibraryData {
    private static LibraryData instance = new LibraryData();
    private ObservableList<Authorable> editableList = FXCollections.observableArrayList();
    private ObservableList<Authorable> pathList = FXCollections.observableArrayList();
    private Map<String, ObservableList<Object>> createdObjectMap = new HashMap<>();
    private ObservableList<GameObjectSimple> gameObjectList = FXCollections.observableArrayList();
    private ObservableSet<Type> labelList = FXCollections.observableSet();
    private ObservableList<Object> moverList = FXCollections.observableArrayList();
    private ObservableList<Object> freeWorldList = FXCollections.observableArrayList();
    private boolean free;

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
//                        addToExistingGameObjectList(placeable);
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
                        Path path = getPath(pathView.createPathObjects());
                        GameObjectInformation.getInstance().addInformation(path, "Path", -1);
                        moverList.add(path);
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

    private String getKeyName (Class<?> klass) {
        return EditingParser.getInterfaceClassFromMap(klass);
    }

    public void addCreatedObjectToList (Class<?> klass, Object o) {
        if (createdObjectMap.containsKey(getKeyName(klass))) {
            int index = GameObjectInformation.getInstance().getIndex(o);
            if (index >= 0) {
                createdObjectMap.get(getKeyName(klass)).set(index, o);
            }
            else {
                createdObjectMap.get(getKeyName(klass)).add(o);
            }
        }
        else {
            ObservableList<Object> list = FXCollections.observableArrayList();
            createdObjectMap.put(getKeyName(klass), list);
            createdObjectMap.get(getKeyName(klass)).add(o);
        }
        System.out.println(createdObjectMap);
    }

    public ObservableList<Object> getObservableList (Class<?> klass) {
        if (!createdObjectMap.containsKey(getKeyName(klass))) {
            if (!klass.getSimpleName().equals("PathFixed")) {
                ObservableList<Object> list = FXCollections.observableArrayList();
                createdObjectMap.put(getKeyName(klass), list);
            }
            else if (!free) {
                createdObjectMap.put(getKeyName(klass), moverList);
            }
            else if (free) {
                createdObjectMap.put(getKeyName(klass), freeWorldList);
            }
        }
        return createdObjectMap.get(getKeyName(klass));
    }

    public void addEditableToList (Placeable editable) {
        editableList.add(editable);
    }

    public void addGameObjectToList (Object gameObject) {
        GameObjectToEditable editable = new GameObjectToEditable((GameObjectSimple) gameObject);
        editableList.add(editable);
        gameObjectList.add((GameObjectSimple) gameObject);
    }

    public void addPathToList (PathView pathView) {
        pathList.add(pathView);
    }

    public ObservableList<GameObjectSimple> getGameObjectList () {
        return gameObjectList;
    }

    public ObservableSet<Type> getLabelSet () {
        return labelList;
    }

    private PathFixed getPath (List<AuthoringPath> list) {
        // MoverPath mover = new MoverPath();
        PathFixed myPath = new PathFixed();
        for (int i = 0; i < list.size(); i++) {
            // System.out.println("Path " + i + "'s coordinates");
            AuthoringPath temp = list.get(i);
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
        // mover.setPath(myPath);
        return myPath;
    }

    public void addFreeWorldPath (Path path) {
        freeWorldList.add(path);
        GameObjectInformation.getInstance().addInformation(path, "Free Path", -1);
        free = true;
    }
}
