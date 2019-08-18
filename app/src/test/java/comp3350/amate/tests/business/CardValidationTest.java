package comp3350.amate.tests.business;

import junit.framework.TestCase;

import org.junit.Test;

import comp3350.amate.business.CardException;
import comp3350.amate.business.CardValidation;
import comp3350.amate.objects.Card;

public class CardValidationTest extends TestCase {
    private final CardValidation validator;
    private final long correctNum = 1234123412341234L;
    private final int correctCvv = 123;
    private final String correctDate = "11/22";
    private final String correctName = "JOHN SMITH";
    private final String correctUserID = "JSMITH";
    private Card card;

    public CardValidationTest(String arg0) {
        super(arg0);
        validator = new CardValidation();
        card = null;
    }

    private void cardHandler(Card card) {
        try {
            validator.validateCard(card);
        } catch (CardException c) {
            System.out.println("\t" + c);
        }
    }

    @Test(expected = CardException.class)
    public void testEmptyCardNumber() {
        System.out.println("Starting testPaymentProcessor: empty Card Number\n");

        card = new Card(0L, correctName, correctDate, correctCvv, correctUserID);
        cardHandler(card);

        System.out.println("Finished testPaymentProcessor: empty Card Number\n");
    }

    @Test(expected = CardException.class)
    public void testShortCardNumber() {
        System.out.println("Starting testPaymentProcessor: long Card Number\n");

        card = new Card(999999999L, correctName, correctDate, correctCvv, correctUserID);
        cardHandler(card);

        System.out.println("Finished testPaymentProcessor: long Card Number\n");
    }

    @Test(expected = CardException.class)
    public void testEmptyDate() {
        System.out.println("Starting testPaymentProcessor: empty Date\n");

        card = new Card(999999999L, "", correctDate, correctCvv, correctUserID);
        cardHandler(card);

        System.out.println("Finished testPaymentProcessor: empty Date\n");
    }

    @Test(expected = CardException.class)
    public void testIncorrectFormatDate() {
        System.out.println("Starting testPaymentProcessor: incorrect Date format\n");

        String incDate = "012019";
        System.out.println("\t" + incDate + ":");
        card = new Card(correctNum, correctName, incDate, correctCvv, correctUserID);
        cardHandler(card);
        incDate = "08/2019";
        System.out.println("\t" + incDate + ":");
        card = new Card(correctNum, correctName, incDate, correctCvv, correctUserID);
        cardHandler(card);
        incDate = "5/19";
        System.out.println("\t" + incDate + ":");
        card = new Card(correctNum, correctName, incDate, correctCvv, correctUserID);
        cardHandler(card);

        System.out.println("Finished testPaymentProcessor: incorrect Date format\n");
    }

    @Test(expected = CardException.class)
    public void testExpiredDate() {
        System.out.println("Starting testPaymentProcessor: expired Date\n");

        System.out.println("\tSame year, past month:");
        String incDate = "01/19";
        card = new Card(correctNum, correctName, incDate, correctCvv, correctUserID);
        cardHandler(card);
        System.out.println("\tPast year:");
        incDate = "01/16";
        card = new Card(correctNum, correctName, incDate, correctCvv, correctUserID);
        cardHandler(card);

        System.out.println("Finished testPaymentProcessor: expired Date\n");
    }

    @Test(expected = CardException.class)
    public void testSuspiciousDate() {
        System.out.println("Starting testPaymentProcessor: suspicious Date\n");

        String incDate = "01/68";
        card = new Card(correctNum, correctName, incDate, correctCvv, correctUserID);
        cardHandler(card);

        System.out.println("Finished testPaymentProcessor: suspicious Date\n");
    }

    @Test(expected = CardException.class)
    public void testEmptyCvv() {
        System.out.println("Starting testPaymentProcessor: empty CVV\n");

        card = new Card(correctNum, correctName, correctDate, 0, correctUserID);
        cardHandler(card);

        System.out.println("Finished testPaymentProcessor: empty CVV\n");
    }

    @Test(expected = CardException.class)
    public void testShortCvv() {
        System.out.println("Starting testPaymentProcessor: short CVV\n");

        card = new Card(correctNum, correctName, correctDate, 1, correctUserID);
        cardHandler(card);

        System.out.println("Finished testPaymentProcessor: short CVV\n");
    }

    @Test(expected = CardException.class)
    public void testEmptyName() {
        System.out.println("Starting testPaymentProcessor: empty name\n");

        card = new Card(correctNum, "", correctDate, correctCvv, correctUserID);
        cardHandler(card);

        System.out.println("Finished testPaymentProcessor: empty name\n");
    }
}
