package engine.prototype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import engine.gameobject.GameObject;
import engine.gameobject.Purchasable;


/**
 * Keeps a list of all prototypes and has the ability to return clones
 *
 * @author Tom Puglisi
 *
 */
@Deprecated
public class PrototypeService {
    private Map<String, Purchasable<GameObject>> prototypeMap;

    public PrototypeService (List<Purchasable<GameObject>> prototypes) {
        prototypeMap = new HashMap<String, Purchasable<GameObject>>();
        // prototypes.stream().forEach(prototype -> prototypeMap.put(prototype.getName(),
        // prototype));
    }

    public GameObject getInstance (String name) {
        return prototypeMap.get(name).clone();
    }

    public void addPrototype (Purchasable<GameObject> prototype) {
        // prototypeMap.put(prototype.getName(), prototype);
    }
}
