package comp3350.amate.business;

import comp3350.amate.objects.Card;
import comp3350.amate.application.Services;
import comp3350.amate.persistence.CardPersistence;

public class AccessCard {
    private CardPersistence caPersistence;

    public AccessCard() {
        caPersistence = Services.getCardPersistence();
    }

    public AccessCard(final CardPersistence payPers) {
        this();
        this.caPersistence = payPers;
    }

    public Card getCard(String userID) {
        return caPersistence.getCard(userID);
    }

    public Card insertCard(Card card) throws CardException {
        CardValidation pa = new CardValidation();
        pa.validateCard(card);
        if (getCard(card.getCardUserID()) == null)
            return caPersistence.addCreditCard(card);
        else
            return updateCard(card);
    }

    public Card updateCard(Card card) {
        return caPersistence.updateCreditCard(card);
    }


}
