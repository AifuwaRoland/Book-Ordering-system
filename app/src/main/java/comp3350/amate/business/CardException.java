package comp3350.amate.business;

public class CardException extends Exception {
    private final String message;

    public CardException(String m) {
        message = m;
    }

    public String toString() {
        return message;
    }
}
