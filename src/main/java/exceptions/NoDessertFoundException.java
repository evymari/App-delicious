package exceptions;

public class NoDessertFoundException extends RuntimeException {
  public NoDessertFoundException(Long guardianId) {
    super("No pets found for dessert with ID: " + guardianId);
  }
}
