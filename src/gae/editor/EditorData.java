package gae.editor;

import engine.gameobject.GameObject;
import gae.backend.Editable;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

//import usecases.ConcreteTower;

/**
 * This class is for taking in an Editable object and referencing the property
 * file to see which fields can be change by the author.
 * 
 */
public class EditorData {
	private HashMap<String, String[]> fieldMap;
	private GameObject editable;

	public EditorData(GameObject editable) {
		this.editable = editable;
		fieldMap = useResourceBundle();
	}

	private String[] getFields() {
		return fieldMap.get(editable.getClass().getSimpleName());
	}

	public void showFields() {
		Class<?> editableClass = editable.getClass();
		for (String fieldName : getFields()) {
			try {
				Field field = editableClass.getDeclaredField(fieldName);
				field.setAccessible(true);
				Object value = field.get(editable);
				System.out.println("Field is: " + field);
				System.out.println("Value is: " + value);
				// if (!fieldName.equals("Weapon")) {
				// field.set(editable, 10);
				// Object newValue = field.get(editable);
				// System.out.println("New Value is: " + newValue);
				// }
			} catch (NoSuchFieldException | SecurityException
					| IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		/*
		 * I think for this part, the front-end should have the Editable object.
		 * Using the method showFields, the front-end can tell which fields to
		 * show the GUI, and then they can also bind each field to the textfield
		 */
	}

	/**
	 * Key is the type of object, its values are the editable fields
	 */
	private HashMap<String, String[]> useResourceBundle() {
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

//	public static void main(String[] args) {
//		ConcreteTower tower = new ConcreteTower(null, null, null);
//		EditorData data = new EditorData(tower);
//
//	}
}
