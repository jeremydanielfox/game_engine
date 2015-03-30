package engine.interactions;

import gameobject.GameObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * The InteractionEngine stores interactions between different GameObjects as
 * BiConsumer Functional Interfaces. The InteractionEngine represents a table of
 * interactions. It accepts two GameObjects, find the appropriate BiConsumer for
 * them, and lets the BiConsumer act upon them, modifying their states.
 * 
 * @author Jeremy
 *
 */
public class ConcreteInteractionEngine implements InteractionEngine {
	private Map<String, Map<String, BiConsumer<GameObject, GameObject>>> myTable = new HashMap<>();

	/**
	 * This method takes in two GameObjects, locates the correct BiConsumer for
	 * their interaction in the table, and lets the BiConsumer act upon these
	 * GameObjects.
	 * 
	 * @param first
	 * @param second
	 */
	@Override
	public void interact(GameObject first, GameObject second) {
		try {
			BiConsumer<GameObject, GameObject> consumer = myTable.get(
					first.getLabel()).get(second.getLabel());
			consumer.accept(first, second);
		} catch (NullPointerException e) {
			System.out.println("Interaction hasn't been defined between "
					+ first.getLabel() + " and " + second.getLabel());

		}

	}

	/**
	 * This method lets someone define the action that occurs between two
	 * GameObjects
	 * 
	 * @param first
	 * @param second
	 * @param consumer
	 */
	@Override
	public void put(GameObject first, GameObject second, BiConsumer consumer) {
		String firstID = first.getLabel();
		String secondID = second.getLabel();
		checkNullMap(firstID);
		checkNullMap(secondID);
		putInMap(firstID, secondID, consumer);
		putInMap(secondID, firstID, consumer);
	}

	/**
 * 
 */
	private void checkNullMap(String str) {
		if (myTable.get(str) == null)
			myTable.put(str,
					new HashMap<String, BiConsumer<GameObject, GameObject>>());
	}

	private void putInMap(String first, String second, BiConsumer consumer) {
		Map temp = myTable.get(first);
		temp.put(second, consumer);
	}


}
