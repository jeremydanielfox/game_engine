package gae.editor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.fieldsetting.Settable;

public class EditingParser {
    
    public static List<Method> getMethodsWithSetterAnnotation(final Class<?> type) {
        final List<Method> methods = new ArrayList<Method>();
        Class<?> klass = type;
        final List<Method> allMethods = new ArrayList<Method>(Arrays.asList(klass.getDeclaredMethods()));       
        for (final Method method : allMethods) {
            if (method.isAnnotationPresent(Settable.class)) {
                methods.add(method);
            }
        }
        return methods;
    }

}
