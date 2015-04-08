package engine.prototype;

/**
 * Follows the service locator pattern. Keeps a reference to the PrototypeService and returns it to
 * whoever asks for it.
 * 
 * @author Tom Puglisi
 *
 */
public class PrototypeLocator {
    private static PrototypeService myPrototypeService;

    public static PrototypeService getService () {
        return myPrototypeService;
    }

    public static void provide (PrototypeService prototypeService) {
        myPrototypeService = prototypeService;
    }

}
