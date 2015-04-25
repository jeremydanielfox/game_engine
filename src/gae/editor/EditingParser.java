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

    public static List<Method> getMethodsWithAnnotation (final Class<?> type, Class annotation) {
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

}
