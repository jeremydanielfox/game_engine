package engine.prototype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import engine.gameobject.GameObject;

/**
 * Keeps a list of all prototypes and has the ability to return clones
 * 
 * @author Tom Puglisi
 *
 */
public class PrototypeService {
    private Map<String, Prototype<GameObject>> prototypeMap;
    
    public PrototypeService(List<Prototype<GameObject>> prototypes) {
        prototypeMap = new HashMap<String, Prototype<GameObject>>();
        prototypes.stream().forEach(prototype -> prototypeMap.put(prototype.getName(), prototype));
    }
    
    public GameObject getInstance(String name) {
        return prototypeMap.get(name).clone();
    }
    
    public void addPrototype(Prototype<GameObject> prototype) {
        prototypeMap.put(prototype.getName(), prototype);
    }
}
