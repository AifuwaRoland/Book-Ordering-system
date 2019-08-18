package comp3350.amate.business;

public class InvalidRegisterException extends Exception {
    public InvalidRegisterException(String errorMessage) {
        super(errorMessage);
    }
}