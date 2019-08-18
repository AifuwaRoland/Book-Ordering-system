package comp3350.amate.application;

import comp3350.amate.persistence.AccountPersistence;
import comp3350.amate.persistence.BookPersistence;
import comp3350.amate.persistence.CardPersistence;
import comp3350.amate.persistence.OrderPersistence;
import comp3350.amate.persistence.ReviewPersistence;

import comp3350.amate.persistence.HSQL.AccountPersistenceHSQLDB;
import comp3350.amate.persistence.HSQL.BookPersistenceHSQLDB;
import comp3350.amate.persistence.HSQL.CardPersistenceHSQLDB;
import comp3350.amate.persistence.HSQL.OrderPersistenceHSQLDB;
import comp3350.amate.persistence.HSQL.ReviewPersistenceHSQLDB;

public class Services {
    private static BookPersistence bookPersistence = null;
    private static AccountPersistence accountPersistence = null;
    private static ReviewPersistence reviewPersistence = null;
    private static OrderPersistence orderPersistence = null;
    private static CardPersistence cardPersistence = null;

    public static synchronized BookPersistence getBookPersistence() {
        if (bookPersistence == null) {
            bookPersistence = new BookPersistenceHSQLDB(Main.getDBPathName());
        }
        return bookPersistence;
    }

    public static synchronized ReviewPersistence getReviewPersistence() {
        if (reviewPersistence == null) {
            reviewPersistence = new ReviewPersistenceHSQLDB(Main.getDBPathName());
        }
        return reviewPersistence;
    }

    public static synchronized AccountPersistence getAccountPersistence() {
        if (accountPersistence == null) {
            accountPersistence = new AccountPersistenceHSQLDB(Main.getDBPathName());
        }
        return accountPersistence;
    }

    public static synchronized OrderPersistence getOrderPersistence() {
        if (orderPersistence == null) {
            orderPersistence = new OrderPersistenceHSQLDB(Main.getDBPathName());
        }
        return orderPersistence;
    }

    public static synchronized CardPersistence getCardPersistence() {
        if (cardPersistence == null) {
            cardPersistence = new CardPersistenceHSQLDB(Main.getDBPathName());
        }
        return cardPersistence;
    }
}