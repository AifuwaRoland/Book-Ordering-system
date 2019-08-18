package comp3350.amate.tests.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import comp3350.amate.business.AccessOrders;
import comp3350.amate.objects.Order;
import comp3350.amate.objects.ShoppingCart;
import comp3350.amate.persistence.HSQL.OrderPersistenceHSQLDB;
import comp3350.amate.persistence.OrderPersistence;
import comp3350.amate.tests.utils.TestUtils;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class AccessOrdersIT {
    private AccessOrders ao;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        tempDB = TestUtils.copyDB();
        OrderPersistence op = new OrderPersistenceHSQLDB(tempDB.getAbsolutePath().replace(".script", ""));
        ao = new AccessOrders(op);
    }

    @Test
    public void testAccessOrdersSetUp() {
        System.out.println("Starting testAccessOrdersSetUp\n");

        assertNotNull("Orders should exist", ao);

        System.out.println("Finished testAccessOrdersSetUp\n");
    }

    @Test
    public void testAccessOrdersAddAndFindOrder() {
        System.out.println("Starting testAccessOrdersAddAndFindOrder\n");

        ShoppingCart cart = new ShoppingCart();
        ao.addOrder(cart);
        Order test = ao.findOrder(3);
        assertNotNull(test);
        assertEquals((long) 3, test.getOrderID());
        cart = test.getCart();
        assertEquals((long) 0, cart.getTotalItems());

        System.out.println("Finished testAccessOrdersAddAndFindOrder\n");
    }

    @After
    public void tearDown() {
        tempDB.delete();
    }
}
