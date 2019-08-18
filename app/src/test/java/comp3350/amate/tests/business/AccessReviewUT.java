package comp3350.amate.tests.business;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.amate.business.AccessReview;
import comp3350.amate.objects.Review;
import comp3350.amate.persistence.ReviewPersistence;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccessReviewUT {
    private ReviewPersistence reviewPersistence;
    private AccessReview accessReview;

    @Before
    public void setUP() {
        reviewPersistence = mock(ReviewPersistence.class);
        accessReview = new AccessReview(reviewPersistence);
    }

    @Test
    public void getReviews_TEST() {
        System.out.println("Starting test getReviews()\n");

        List<Review> testList = new ArrayList<>();
        Review testReview = new Review("The Inferno", "This book bad.", 3);
        testList.add(testReview);
        when(reviewPersistence.getReviewList("The Inferno")).thenReturn(testList);

        List<Review> output = accessReview.getReviews("The Inferno");
        verify(reviewPersistence).getReviewList("The Inferno");
        assertNotNull(output);
        assertTrue("The review message should match", 3 == output.get(0).getNumStars());
        assertTrue("The review message should match", 0 == ("This book bad.").compareTo(output.get(0).getWrittenReview()));

        System.out.println("Finished test getReviews()\n");


    }


    @Test
    public void addReviewsDB_TEST() {
        System.out.println("Starting test addReviewsDB()\n");
        Review testReview = new Review("The Inferno", "This book bad.", 3);
        accessReview.addReviewDB(testReview);
        verify(reviewPersistence).addReview(testReview);

        System.out.println("Finished test getReviews()\n");


    }
}
