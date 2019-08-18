package comp3350.amate.tests.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import comp3350.amate.business.AccessCard;
import comp3350.amate.business.CardException;
import comp3350.amate.objects.Card;
import comp3350.amate.persistence.CardPersistence;
import comp3350.amate.persistence.HSQL.CardPersistenceHSQLDB;
import comp3350.amate.tests.utils.TestUtils;

import static org.junit.Assert.*;

public class AccessCardIT {
    private AccessCard accessCard;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final CardPersistence persistence = new CardPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessCard = new AccessCard(persistence);
    }

    @Test
    public void testAccessCardGetCard() {
        System.out.println("Starting testAccessCardGetCard\n");

        Card card = accessCard.getCard("testAcc");
        assertTrue("testAcc".equals(card.getCardUserID()));
        assertEquals(card.getCardNumber(), 1122334455667788L);
        assertTrue("DAN".equals(card.getCardHolder()));
        assertTrue("12/20".equals(card.getExpiryDate()));
        assertEquals(123L, (long) card.getCVV());

        System.out.println("Finished testAccessCardGetCard\n");
    }

    @Test
    public void testAccessCardInsertCard() {
        System.out.println("Starting testAccessCardInsertCard\n");

        Card card = new Card(8877665544332211L, "TEST", "07/20", 777, "sysTest");
        try {
            card = accessCard.insertCard(card);
        } catch (CardException e) {
            System.out.println(e.getMessage());
            fail();
        }
        assertTrue("sysTest".equals(card.getCardUserID()));
        assertEquals(card.getCardNumber(), 8877665544332211L);
        assertTrue("TEST".equals(card.getCardHolder()));
        assertTrue("07/20".equals(card.getExpiryDate()));
        assertEquals(777, (long) card.getCVV());

        System.out.println("Finished testAccessCardInsertCard\n");
    }

    @Test
    public void testAccessCardUpdateCard() {
        System.out.println("Starting testAccessCardUpdateCard\n");

        Card oldCard = accessCard.getCard("testAcc");
        accessCard.updateCard(new Card(8877665544332211L, "TEST", "07/20", 777, "testAcc"));
        Card newCard = accessCard.getCard("testAcc");

        assertTrue(oldCard.getCardUserID().equals(newCard.getCardUserID()));
        assertNotEquals(oldCard.getCardNumber(), newCard.getCardNumber());
        assertFalse(oldCard.getCardHolder().equals(newCard.getCardHolder()));
        assertFalse(oldCard.getExpiryDate().equals(newCard.getExpiryDate()));
        assertNotEquals(oldCard.getCVV(), newCard.getCVV());

        System.out.println("Finished testAccessCardUpdateCard\n");
    }

    @After
    public void tearDown() {
        this.tempDB.delete();
    }
}
