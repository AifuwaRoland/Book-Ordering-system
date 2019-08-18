package comp3350.amate.tests.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;


import comp3350.amate.business.AccessReview;
import comp3350.amate.objects.Review;
import comp3350.amate.persistence.ReviewPersistence;
import comp3350.amate.persistence.HSQL.ReviewPersistenceHSQLDB;
import comp3350.amate.tests.utils.TestUtils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;

public class AccessReviewIT {
    private AccessReview accessReview;
    private File tempDB;

    @Before
    public void setUP() throws IOException {
        tempDB = TestUtils.copyDB();
        ReviewPersistence persistence = new ReviewPersistenceHSQLDB(tempDB.getAbsolutePath().replace(".script", ""));
        accessReview = new AccessReview(persistence);
    }

    @Test
    public void testReviewList() {
        System.out.println("Starting testReviewList\n");
        List<Review> testList = accessReview.getReviews("The Inferno");
        assertNotNull("List should exist", testList);
        System.out.println("Finished testAccessBooksList\n");
    }

    @Test
    public void testAddReview() {
        System.out.println("Starting testAddReview\n");

        boolean testException = false;
        try {
            accessReview.addReviewDB(new Review("The Inferno", "Good", 3));
        } catch (Exception e) {
            testException = true;
        }
        assertFalse(testException);

        System.out.println("Finished testReview\n");
    }

    @After
    public void tearDown() {
        this.tempDB.delete();
    }
}
