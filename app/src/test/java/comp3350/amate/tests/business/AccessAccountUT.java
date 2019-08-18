package comp3350.amate.tests.business;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.amate.business.AccessAccount;

import comp3350.amate.business.InvalidRegisterException;
import comp3350.amate.business.InvalidSigninException;
import comp3350.amate.objects.Account;

import comp3350.amate.persistence.AccountPersistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccessAccountUT {
    private AccessAccount accessAccounts;
    private AccountPersistence accountPersistence;


    @Before
    public void setUp() {
        accountPersistence = mock(AccountPersistence.class);
        accessAccounts = new AccessAccount(accountPersistence);
    }

    @Test
    public void getAccounts_TEST() {  //getAccounts
        System.out.println("Starting test AccessAccount.getAccounts\n");

        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account.AccountBuilder("testID123").build());
        when(accountPersistence.getAccountSequential()).thenReturn(accounts);//getAccountSequential

        accounts = accessAccounts.getAccounts();
        verify(accountPersistence).getAccountSequential();
        assertNotNull("first sequential account should not be null", accounts);
        assertTrue(0 == "testID123".compareTo(accounts.get(0).getUserID()));

        System.out.println("Finished test AccessAccount.getAccounts\n");
    }

    @Test
    public void signIn_TEST() throws InvalidSigninException { //signin id = id and pwd = pwd
        System.out.println("Starting test AccessAccount.signin\n");

        final Account account;
        Account testAccount = new Account.AccountBuilder("testID123").buildUserPassword("testPwd").build();
        final List<Account> accounts = new ArrayList<>();
        accounts.add(testAccount);
        when(accountPersistence.getAccountSequential()).thenReturn(accounts);//getAccountSequential

        account = accessAccounts.signIn(testAccount);
        assertNotNull("first sequential account should not be null", account);
        assertTrue("testID123".equals(account.getUserID()));
        assertTrue("testID123".equals(accessAccounts.getSignedInAccount().getUserID()));

        verify(accountPersistence).getAccountSequential();

        System.out.println("Finished test AccessAccount.signin\n");
    }

    @Test
    public void register_TEST() throws InvalidRegisterException { //register
        System.out.println("Starting test AccessAccount.register\n");

        final Account account;
        Account testAccount = new Account.AccountBuilder("testID123").buildUserPassword("testPwd").build();
        final List<Account> accounts = new ArrayList<>();
        when(accountPersistence.getAccountSequential()).thenReturn(accounts);//getAccountSequential

        account = accessAccounts.register(testAccount);
        assertNotNull("first sequential account should not be null", account);
        assertTrue("testID123".equals(account.getUserID()));

        verify(accountPersistence).getAccountSequential();

        System.out.println("Finished test AccessAccount.register\n");
    }

    @Test
    public void insertAccount_TEST() { //insertAccount
        System.out.println("Starting test AccessAccount.insertAccount\n");

        Account account;
        Account testAccount = new Account.AccountBuilder("testID123").build();
        when(accountPersistence.insertAccount(testAccount)).thenReturn(testAccount);//getAccountSequential

        account = accessAccounts.insertAccount(testAccount);
        assertNotNull("first sequential account should not be null", account);
        assertTrue("testID123".equals(account.getUserID()));

        verify(accountPersistence).insertAccount(testAccount);

        System.out.println("Finished test AccessAccount.insertAccount\n");
    }

    @Test
    public void updateUserBookmarks_TEST() throws InvalidSigninException {
        System.out.println("Starting test updateUserBookmarks_TEST\n");

        Account account;
        Account testAccount = new Account.AccountBuilder("testID123").buildUserPassword("testPwd").build();
        final List<Account> accounts = new ArrayList<>();
        accounts.add(testAccount);
        when(accountPersistence.getAccountSequential()).thenReturn(accounts);//getAccountSequential

        account = accessAccounts.signIn(testAccount);

        accessAccounts.updateUserBookmarks(account.getBookmarkList());
        List<String> emptyList = new ArrayList<>();
        verify(accountPersistence).updateUserBookmarks("testID123", emptyList);

        System.out.println("Finished test updateUserBookmarks_TEST\n");
    }

    @Test
    public void addOrderToUser_TEST() {
        System.out.println("Starting test AccessAccount.insertAccount\n");

        accessAccounts.addOrderToUser("uid123", 123);
        verify(accountPersistence).addOrderToUser("uid123", 123);

        System.out.println("Finished test AccessAccount.insertAccount\n");
    }

}
