package comp3350.amate.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.amate.tests.business.AccessAccountIT;
import comp3350.amate.tests.business.AccessBooksIT;
import comp3350.amate.tests.business.AccessCardIT;
import comp3350.amate.tests.business.AccessOrdersIT;
import comp3350.amate.tests.business.AccessReviewIT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessAccountIT.class,
        AccessBooksIT.class,
        AccessCardIT.class,
        AccessOrdersIT.class,
        AccessReviewIT.class,
})
public class AllIntegrationTests {
}
