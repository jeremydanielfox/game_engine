package exception;

/**
 * An exception for when the object placed is out of bounds
 */
public class ObjectOutOfBoundsException extends VOOGAException {
    private static final long serialVersionUID = 1L;

    public ObjectOutOfBoundsException () {
        super(String.format("Object is out of bounds!"));
    }

}
