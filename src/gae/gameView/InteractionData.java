package gae.gameView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import engine.gameobject.GameObject;
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

    private List<InteractionEngine> myInteractionEngines;
    private InteractionEngine myCollisions;
    private InteractionEngine myShoots;

    public InteractionData () {
        myInteractionEngines = new ArrayList<>();
        myCollisions = new CollisionEngine();
        myShoots = new RangeEngine();
        myInteractionEngines.addAll(Arrays.asList(myCollisions, myShoots));
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
     * adds interactions based on one list of objects that interact in a certain way with another
     * list of objects
     * 
     * @param one
     * @param i
     * @param two
     */
    public void addInteraction (List<GameObject> one, Interaction i, List<GameObject> two) {
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
    private void massPut (List<GameObject> one,
                          Interaction i,
                          List<GameObject> two,
                          InteractionEngine engine) {
        one.forEach(e -> {
            two.forEach(f -> {
                engine.put(e.getLabel(), f.getLabel(), i);
            });
        });
    }
}
