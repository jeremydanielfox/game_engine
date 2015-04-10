package exception;

/**
 * An exception for input is not a number is found
 * 
 *
 */
public class ObjectOutOfBoundsException extends VOOGAException {
    private static final long serialVersionUID = 1L;

    public ObjectOutOfBoundsException () {
        super(String.format("Object is out of bounds!"));
    }

}
