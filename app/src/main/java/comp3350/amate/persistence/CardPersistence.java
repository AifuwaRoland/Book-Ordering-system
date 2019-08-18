package comp3350.amate.persistence;


import comp3350.amate.objects.Card;

public interface CardPersistence {
    Card addCreditCard(Card cards);

    Card updateCreditCard(Card cards);

    Card getCard(String userID);
}