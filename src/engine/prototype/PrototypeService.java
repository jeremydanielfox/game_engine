package engine.prototype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Keeps a list of all prototypes and has the ability to return clones
 * 
 * @author Tom Puglisi
 *
 */
public class PrototypeService {
    private Map<String, Prototype> prototypeMap;
    
    public PrototypeService(List<Prototype> prototypes) {
        prototypeMap = new HashMap<String, Prototype>();
        prototypes.stream().forEach(prototype -> prototypeMap.put(prototype.getName(), prototype));
    }
    
    public Object getInstance(String name) {
        return prototypeMap.get(name).clone();
    }
    
    public void addPrototype(Prototype prototype) {
        prototypeMap.put(prototype.getName(), prototype);
    }
}
