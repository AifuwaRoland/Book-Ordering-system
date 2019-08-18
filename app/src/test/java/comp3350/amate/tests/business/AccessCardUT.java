package comp3350.amate.tests.business;

import org.junit.Before;
import org.junit.Test;

import comp3350.amate.business.AccessCard;
import comp3350.amate.business.CardException;
import comp3350.amate.objects.Card;
import comp3350.amate.persistence.CardPersistence;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class AccessCardUT {
    private AccessCard accessPayInfo;
    CardPersistence cardPersistence;

    @Before
    public void setUp() {
        cardPersistence = mock(CardPersistence.class);
        accessPayInfo = new AccessCard(cardPersistence);
    }

    @Test
    public void getCard_byUID_TEST() {
        System.out.println("Starting test AccessPaymentInfo\n");

        Card card;
        Card test = new Card();
        String userId = "Guest";

        when(cardPersistence.getCard(userId)).thenReturn(test);
        card = accessPayInfo.getCard(userId);
        assertNotNull("No cards in DB, should be null\n", card);
        verify(cardPersistence).getCard(userId);

        System.out.println("\nFinished test AccessPaymentInfo\n");
    }

    @Test
    public void insertCard_TEST() throws CardException {
        System.out.println("Starting insertCard_TEST(\n");

        long temp = new Long(1234567890);
        temp *= 1000000;
        temp += 3456;

        System.out.print(temp);
        Card testCard = new Card(temp, "str", "01/20", 223, "str");
        accessPayInfo.insertCard(testCard);
        verify(cardPersistence).addCreditCard(testCard);

        System.out.println("Finished insertCards_TEST(\n");
    }

    @Test
    public void updateCard_TEST() {
        System.out.println("Starting updateCard_TEST(\n");

        long temp = new Long(1235678901);
        temp *= 100000;
        System.out.print(temp);
        Card testCard = new Card(temp, "str1", "str2", 123, "str3");
        accessPayInfo.updateCard(testCard);
        verify(cardPersistence).updateCreditCard(testCard);

        System.out.println("Finished updateCard_TEST(\n");
    }
}
