package exception;

/**
 * An exception for input is not a number is found
 * 
 *
 */
public class NonNumberException extends VOOGAException {
    private static final long serialVersionUID = 1L;

    public NonNumberException () {
        super(String.format("Please Enter a valid integer!"));

    }

}
