package comp3350.amate.tests;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.amate.tests.business.AccessAccountUT;
import comp3350.amate.tests.business.AccessBooksUT;
import comp3350.amate.tests.business.AccessCardUT;
import comp3350.amate.tests.business.AccessOrdersUT;
import comp3350.amate.tests.business.AccessReviewUT;
import comp3350.amate.tests.business.AccessShoppingCartUT;
import comp3350.amate.tests.business.CardValidationTest;
import comp3350.amate.tests.objects.AccountTest;
import comp3350.amate.tests.objects.BookTest;
import comp3350.amate.tests.objects.OrderTest;
import comp3350.amate.tests.objects.ShoppingCartTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        //Objects
        BookTest.class,
        OrderTest.class,
        ShoppingCartTest.class,
        AccountTest.class,
        CardValidationTest.class,

        //Business
        AccessAccountUT.class,
        AccessBooksUT.class,
        AccessCardUT.class,
        AccessOrdersUT.class,
        AccessReviewUT.class,
        AccessShoppingCartUT.class,

})

public class AllUnitTests {
}
