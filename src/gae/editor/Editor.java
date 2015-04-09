package gae.editor;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 
 * @author Eric
 *
 */
public abstract class Editor {

    abstract void setDefaults();
    abstract void clearValues();

    public TreeNode getMethodsTree(Class<?> klass, Method m) {
        TreeNode root = new TreeNode(m, "null");
        List<Method> methods = EditingParser.getMethodsWithSetterAnnotation(klass);
        for (Method method : methods) {
            //System.out.println(method.toString());
            Type parameterClass = method.getGenericParameterTypes()[0];
            if (parameterClass.equals(double.class)) {
                System.out.println("double  " + getPropertyName(method.getName()));
                root.addToNodes(new TreeNode(method, "Slider"));
            } else if (parameterClass.equals(String.class)) {
                System.out.println("String  " + getPropertyName(method.getName()));
                root.addToNodes(new TreeNode(method, "Textbox"));
            } else {
                System.out.println(parameterClass.getTypeName() + ":");
                root.addToNodes(getMethodsTree((Class<?>) parameterClass, method));
            }
        }
        return root;
    }

    public static String getPropertyName(String methodName) {
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
