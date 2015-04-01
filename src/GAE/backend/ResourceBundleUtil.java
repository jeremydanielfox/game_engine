package GAE.backend;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;


public class ResourceBundleUtil {
    public static HashMap<String, String[]> useResourceBundle () {
        ResourceBundle resources = ResourceBundle
                .getBundle("Example_Data/Reflection_Data");
        Enumeration<String> paramKeys = resources.getKeys();
        HashMap<String, String[]> newMap = new HashMap<>();

        while (paramKeys.hasMoreElements()) {
            String Key = paramKeys.nextElement();
            newMap.put(Key, resources.getString(Key).split(",\\s+"));
        }
        return newMap;
    }
}
