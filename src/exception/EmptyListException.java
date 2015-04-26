package exception;

public class EmptyListException extends VOOGAException {
    private static final long serialVersionUID = 1L;

    public EmptyListException () {
        super(String.format("List is empty!"));
    }

}

