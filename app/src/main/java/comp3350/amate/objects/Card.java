package comp3350.amate.objects;

public class Card {
    private final long cardNumber;
    private final String cardHolder;
    private final String expiryDate;
    private final int cvv;
    private final String userID;

    public Card() {
        cardNumber = 0L;
        cardHolder = "";
        expiryDate = "";
        cvv = 0;
        userID = "";
    }

    public Card(long cardNumber, String cardHolder, String expiryDate, int cvv, String userID) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.userID = userID;
    }

    //Accessors
    public long getCardNumber() {
        return cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public int getCVV() {
        return cvv;
    }

    public String getCardUserID() {
        return userID;
    }

    public String toString() {
        return "Visa ending in " + (cardNumber % 10000);
    }
}
