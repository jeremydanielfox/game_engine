package exception;

public class FieldAlreadyExistingException extends VOOGAException {
    private static final long serialVersionUID = 1L;

    public FieldAlreadyExistingException () {
        super(String.format("Field already exists! Delete previous first"));
    }

}

