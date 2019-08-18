package comp3350.amate.tests.objects;

import org.junit.Test;

import static org.junit.Assert.*;

import comp3350.amate.objects.Review;

public class ReviewTest {
    @Test
    public void testReview() {
        System.out.println("Starting testReview\n");

        Review review = new Review("The Inferno", "Okay at best", 3);
        assertNotNull(review);
        assertTrue(review.getReBookName().equals("The Inferno"));
        assertTrue(review.getWrittenReview().equals("Okay at best"));
        assertEquals((long) 3, (long) review.getNumStars());

        System.out.println("Finished testReview\n");
    }
}
