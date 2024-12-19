package exceptions;

public class NoRegistersFoundException extends RuntimeException {
    public NoRegistersFoundException() {

        super("No registers found");
    }
}