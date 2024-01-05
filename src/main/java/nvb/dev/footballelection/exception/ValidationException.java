package nvb.dev.footballelection.exception;

public class ValidationException extends IllegalArgumentException {
    public ValidationException() {
        super();
    }

    public ValidationException(String s) {
        super(s);
    }
}
