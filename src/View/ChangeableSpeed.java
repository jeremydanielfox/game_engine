package View;

/**
 * This interface is meant to be implemented by an object that has a changeable speed and allows
 * the speed to be toggled.
 *
 * @author Sierra
 * @author Cosette
 *
 */
public interface ChangeableSpeed {

    /**
     * Toggles the speed.
     */
    public void toggleRate ();

    /**
     * Checks if the speed of the object can be further increased.
     * 
     * @return
     */
    public boolean canIncSpeed ();

    /**
     * Checks if the speed of the object can be further decreased.
     * 
     * @return
     */
    public boolean canDecSpeed ();

}
