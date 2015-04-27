package gae.editor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import engine.fieldsetting.Settable;


public class EditingParser {
    private static final String DEFAULT_PROPERTY_FILE = "engine.fieldsetting.implementing_classes";
    private static final String USER_FRIENDLY_FILE = "engine.fieldsetting.user_friendly_names";

    public static List<Method> getMethodsWithAnnotation (final Class<?> type, Class<?> annotation) {
        final List<Method> methods = new ArrayList<Method>();
        Class<?> klass = type;
        final List<Method> allMethods =
                new ArrayList<Method>(Arrays.asList(klass.getDeclaredMethods()));
        for (final Method method : allMethods) {
            if (method.isAnnotationPresent(Settable.class)) {
                methods.add(method);
            }
        }
        return methods;
    }

    /**
     * This method parses a properties file and outputs a map with the String key and an array list
     * of what it equals
     *
     * @param propertyFilePath a String representing the path to the properties file
     * @return the map of the key to its values
     */
    public static Map<String, ArrayList<String>> getInterfaceClasses (String propertyFilePath) {
        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        ResourceBundle rb = ResourceBundle.getBundle(propertyFilePath);
        Enumeration<String> enumerator = rb.getKeys();

        while (enumerator.hasMoreElements()) {
            String key = enumerator.nextElement();
            if (!key.equals("//")) {
                String value = rb.getString(key);
                String[] split = value.split(",");
                ArrayList<String> values = new ArrayList<String>();
                for (String s : split) {
                    values.add(s.trim());
                }
                map.put(key, values);
            }
        }
        return map;
    }
    
    public static String getUserFriendlyName(String originalName) {
        Map<String, String> map = new HashMap<String, String>();
        ResourceBundle rb = ResourceBundle.getBundle(USER_FRIENDLY_FILE);
        Enumeration<String> enumerator = rb.getKeys();
        
        while (enumerator.hasMoreElements()) {
            String key = enumerator.nextElement();
            map.put(key, rb.getString(key));
        }
        
        String ret = map.get(originalName);
        if (ret != null) return ret;
        else return originalName;
    }
    

    public static String getInterfaceClassFromMap (Class<?> klass) {
        Map<String, ArrayList<String>> map = getInterfaceClasses(DEFAULT_PROPERTY_FILE);
        for (Map.Entry<String,ArrayList<String>> entry: map.entrySet()) {
                ArrayList<String> list = entry.getValue();
                for (String concrete: list) {
                    if (concrete.equals(klass.getName())) {
                        return entry.getKey();
                    }
            }
        }
        return klass.getName();
    }

    /**
     * Checks to see if the input klass is an interface by looking in the properties map.
     *
     * @param klass the class to check.
     * @return the conrete class from the map or if the input klass is not in the map, the input
     */
    public static Class<?> getConcreteClassFromMap (Class<?> klass) {
        Map<String, ArrayList<String>> map = getInterfaceClasses(DEFAULT_PROPERTY_FILE);
        if (map.containsKey(klass.getName())) {
            try {
                String newName = map.get(klass.getName()).get(0);
                return Class.forName(newName);
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return klass;
    }

    public static Object getInstanceFromName (String name) {
        Class<?> c = null;
        Object component = null;
        try {
            c = Class.forName(name);
            component = c.newInstance();
        }
        catch (ClassNotFoundException e) {
            // e.printStackTrace();
        }
        catch (IllegalAccessException iae) {
            // iae.printStackTrace();
        }
        catch (InstantiationException ie) {
             ie.printStackTrace();
        }

        return component;
    }

}
