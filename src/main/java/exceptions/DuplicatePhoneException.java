package exceptions;

public class DuplicatePhoneException extends RuntimeException {
    public DuplicatePhoneException(String phone) { super("Phone number " + phone + " already exists");}
}
