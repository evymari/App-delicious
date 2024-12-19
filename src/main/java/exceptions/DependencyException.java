package exceptions;

public class DependencyException extends AppException {
    public DependencyException(Long id) {
        super("Cannot delete Guardian with id " + id + " because they have associated desserts.");
    }
}
