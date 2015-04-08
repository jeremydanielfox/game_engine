package engine.prototype;


public class PrototypeLocator {
    private static PrototypeService myPrototypeService;

    public static PrototypeService getService () {
        return myPrototypeService;
    }

    public static void provide (PrototypeService prototypeService) {
        myPrototypeService = prototypeService;
    }
}
