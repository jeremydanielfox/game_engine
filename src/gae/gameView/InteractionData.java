package gae.gameView;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.gameobject.labels.Type;
import engine.interactions.BuffImparter;
import engine.interactions.CollisionEngine;
import engine.interactions.Interaction;
import engine.interactions.InteractionEngine;
import engine.interactions.NoInteraction;
import engine.interactions.RangeEngine;
import engine.interactions.ShootAt;

/**
 * Class that acts as the data holder of all interactions authored. This will hold the interactions
 * that will be exported out to the engine.
 *
 * @author Brandon Choi
 *
 */

public class InteractionData {

    /*
     * Values for the map of user options to interaction classes in the engine
     */
    private static final List<String> MAP_KEYS = Arrays.asList("Collide", "Shoot",
                                                               "Do not collide", "Do not shoot");
    private static final List<Class<? extends Interaction>> MAP_VALUES = Arrays
            .asList(BuffImparter.class, ShootAt.class, NoInteraction.class, NoInteraction.class);

    private List<InteractionEngine> myInteractionEngines;
    private InteractionEngine myCollisions;
    private InteractionEngine myShoots;
    private Map<String, Class> interactionMap;

    public InteractionData () {
        myInteractionEngines = new ArrayList<>();
        myCollisions = new CollisionEngine();
        myShoots = new RangeEngine();
        myInteractionEngines.addAll(Arrays.asList(myCollisions, myShoots));
        interactionMap = new HashMap<>();
        fillMap();
    }

    /**
     * returns both interaction engines with respective interactions set in each of them
     *
     * @return
     */
    public List<InteractionEngine> getEngines () {
        return myInteractionEngines;
    }

    /**
     * next two methods returns individual engines if needed
     * 
     * @return
     */
    public InteractionEngine getCollisionEngine () {
        return myCollisions;
    }

    public InteractionEngine getRangeEngine () {
        return myShoots;
    }

    /**
     * returns map of strings that are mapped to the type of interaction in the engine
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Map<String, Class> getInteractionMap () {
        return interactionMap;
    }

    /**
     * fills the map with the correct corresponding values between strings and interactions
     */
    private void fillMap () {
        MAP_KEYS.forEach(e -> {
            interactionMap.put(e, MAP_VALUES.get(MAP_KEYS.indexOf(e)));
        });
    }

    /**
     * adds interactions based on one list of objects that interact in a certain way with another
     * list of objects
     *
     * @param one
     * @param i
     * @param two
     */
    public void addInteraction (List<Type> one, Interaction i, List<Type> two) {
        if (i instanceof BuffImparter) {
            massPut(one, i, two, myCollisions);
        }
        else if (i instanceof ShootAt) {
            massPut(one, i, two, myShoots);
        }
        else {
            massPut(one, new NoInteraction(), two, myCollisions);
            massPut(one, new NoInteraction(), two, myShoots);
        }
    }

    /**
     * places all the interactions between list one and two by iterating through each of them and
     * setting all iterations
     *
     * @param one
     * @param i
     * @param two
     * @param engine
     */
    private void massPut (List<Type> one, Interaction i, List<Type> two, InteractionEngine engine) {
        one.forEach(e -> {
            two.forEach(f -> {
                engine.put(e, f, i);
            });
        });
    }
}
