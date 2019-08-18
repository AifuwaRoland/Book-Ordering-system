package comp3350.amate.business;

public class InvalidSigninException extends Exception {
    public InvalidSigninException(String errorMessage) {
        super(errorMessage);
    }
}