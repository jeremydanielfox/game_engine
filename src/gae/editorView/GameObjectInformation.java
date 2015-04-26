package gae.editorView;

import java.util.HashMap;
import java.util.Map;


public class GameObjectInformation {
    private static GameObjectInformation instance = new GameObjectInformation();
    private Map<Object, String> titleMap = new HashMap<>();
    private Map<Object, Integer> indexMap = new HashMap<>();

    private GameObjectInformation () {

    }

    public static GameObjectInformation getInstance () {
        return instance;
    }

    public void addInformation (Object o, String title, int index) {
        titleMap.put(o, title);
        indexMap.put(o, index);
    }

    public String getTitle (Object o) {
        return titleMap.get(o);
    }

    public int getIndex (Object o) {
        return indexMap.get(o);
    }
}
