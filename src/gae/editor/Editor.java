package gae.editor;

import java.lang.reflect.Method;
import java.util.List;
import engine.gameobject.GameObjectSimple;
import javafx.scene.Node;

public abstract class Editor {
    
    abstract Node getEditor();
    
    abstract void setDefaults();
    
    public void getGameObjectMethods() {
        List<Method> methods = EditingParser.getMethodsWithSetterAnnotation(GameObjectSimple.class);
        for (Method method : methods) {
            String propertyName = getPropertyNameFromMethodName(method.getName());
            System.out.println(propertyName);
        }
    }
    
    private String getPropertyNameFromMethodName(String methodName) {
        String propertyName = methodName.substring(3, methodName.length());
        char[] chars = propertyName.toCharArray();
        int editCount = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] >= 65 && chars[i] <= 90) {
                String temp1 = propertyName.substring(0, i + editCount);
                String temp2 = propertyName.substring(i + editCount, propertyName.length());
                propertyName = String.format("%s %s", temp1, temp2);
                editCount++;
            }
        }
        
        return propertyName;
    }
}
