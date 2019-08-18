package comp3350.amate.persistence;

import java.util.List;

import comp3350.amate.objects.Review;

public interface ReviewPersistence {
    List<Review> getReviewList(String name);

    void addReview(Review review);
}
