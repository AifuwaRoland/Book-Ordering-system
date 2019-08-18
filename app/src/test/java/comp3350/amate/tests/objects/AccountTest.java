package comp3350.amate.tests.objects;

import org.junit.Before;
import org.junit.Test;

import comp3350.amate.objects.Account;
import comp3350.amate.objects.Book;
import comp3350.amate.objects.Order;

import static org.junit.Assert.*;

public class AccountTest {
    private Account account;

    @Before
    public void setUp() {
        account = new Account.AccountBuilder("adams123")
                .buildUserEmail("adams123@myumanitoba.ca")
                .buildUserAddr("testme1234")
                .buildUserNum("123456")
                .buildUserPassword("123")
                .build();
    }

    @Test
    public void testAccountConstruction() {
        System.out.println("Starting testAccountConstruction\n");

        assertNotNull(account);
        assertTrue(account.getUserID().equals("adams123"));
        assertTrue(account.getUserAddr().equals("testme1234"));
        assertTrue(account.getUserNum().equals("123456"));
        assertTrue(account.getUserEmail().equals("adams123@myumanitoba.ca"));
        assertTrue(account.getUserPassword().equals("123"));
        assertNotNull(account.getBookmarkList());
        assertNotNull(account.getOrderList());

        System.out.println("Finished testAccountConstruction\n");
    }

    @Test
    public void testAccountAddOrder() {
        System.out.println("Starting testAccountAddOrder\n");

        account.addOrder(1234);  //orderID = 1234
        assertEquals((long) 1, (long) account.getOrderList().size());

        System.out.println("Finished testAccountAddOrder\n");
    }

    @Test
    public void testAccountBookmarks() {
        System.out.println("Starting testAccountBookmarks\n");

        account.addBookToBookmark("The Inferno");
        assertEquals((long) 1, (long) account.getBookmarkList().size());
        account.removeBookFromBookmark("The Inferno");
        assertEquals((long) 0, (long) account.getBookmarkList().size());

        System.out.println("Finished testAccountBookmarks\n");
    }
}
