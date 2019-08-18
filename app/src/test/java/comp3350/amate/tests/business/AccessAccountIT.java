package comp3350.amate.tests.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import comp3350.amate.business.AccessAccount;
import comp3350.amate.business.InvalidRegisterException;
import comp3350.amate.business.InvalidSigninException;
import comp3350.amate.objects.Account;
import comp3350.amate.persistence.AccountPersistence;
import comp3350.amate.persistence.HSQL.AccountPersistenceHSQLDB;
import comp3350.amate.tests.utils.TestUtils;

import static org.junit.Assert.*;

public class AccessAccountIT {
    private AccessAccount accessAccount;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final AccountPersistence persistence = new AccountPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessAccount = new AccessAccount(persistence);
    }

    @Test
    public void testAccessAccountList() {
        System.out.println("Starting testAccessAccountList\n");

        List<Account> accounts = accessAccount.getAccounts();
        assertNotNull("first sequential account should not be null", accounts);

        System.out.println("Finished testAccessAccountList\n");
    }

    @Test
    public void testAccessAccountSignIn() {
        System.out.println("Starting testAccessAccountSignIn\n");

        Account a = new Account.AccountBuilder("testAcc")
                .buildUserPassword("123")
                .build();
        try {
            a = accessAccount.signIn(a);
        } catch (InvalidSigninException e) {
            fail();
        }
        assertTrue("testAcc".equals(a.getUserID()));

        System.out.println("Finished testAccessAccountSignIn\n");
    }

    @Test
    public void testAccessAccountRegister() {
        System.out.println("Starting testAccessAccountRegister\n");

        Account a = new Account.AccountBuilder("mockTest")
                .buildUserPassword("qqq")
                .build();

        //Register new account
        try {
            accessAccount.register(a);
        } catch (InvalidRegisterException e) {
            fail();
        }

        //Sign in to new account
        try {
            a = accessAccount.signIn(a);
        } catch (InvalidSigninException e) {
            fail();
        }
        assertTrue("mockTest".equals(accessAccount.getSignedInAccount().getUserID()));

        //Register account with same user ID - exception should be caught
        boolean exceptionCaught = false;
        try {
            accessAccount.register(a);
        } catch (InvalidRegisterException e) {
            exceptionCaught = true;
        }
        assertTrue(exceptionCaught);

        System.out.println("Finished testAccessAccountRegister\n");
    }

    @Test
    public void testAccessAccountBookmarking() {
        System.out.println("Starting testAccessAccountBookmarking\n");

        Account a = new Account.AccountBuilder("testAcc")
                .buildUserPassword("123")
                .build();
        try {
            accessAccount.signIn(a);
        } catch (InvalidSigninException e) {
            fail();
        }

        accessAccount.addBookmark("The Inferno"); //database not updated
        Account b = accessAccount.updateUserBookmarks(accessAccount.getSignedInAccount().getBookmarkList());

        List<String> bookmark = b.getBookmarkList();
        assertTrue(bookmark.get(2).equals("The Inferno"));

        System.out.println("Finished testAccessAccountBookmarking\n");
    }

    @Test
    public void testAccessAccountAddOrder() {
        System.out.println("Starting testAccessAccountAddOrder\n");

        accessAccount.addOrderToUser("testAcc", 3);

        Account a = new Account.AccountBuilder("testAcc")
                .buildUserPassword("123")
                .build();
        try {
            a = accessAccount.signIn(a);
        } catch (InvalidSigninException e) {
            fail();
        }

        assertEquals((long) 3, (long) a.getOrderList().get(0));

        System.out.println("Finished testAccessAccountAddOrder\n");
    }

    @After
    public void tearDown() {
        this.tempDB.delete();
    }
}
