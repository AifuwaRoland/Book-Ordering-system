package comp3350.amate.business;

import java.util.*;

import comp3350.amate.application.Services;
import comp3350.amate.objects.Review;
import comp3350.amate.persistence.ReviewPersistence;

public class AccessReview {
    private ReviewPersistence reviewAccess;

    public AccessReview() {
        reviewAccess = Services.getReviewPersistence();
    }

    public AccessReview(ReviewPersistence persistence) {
        this();
        reviewAccess = persistence;
    }

    public List<Review> getReviews(String name) {
        return reviewAccess.getReviewList(name);
    }

    public void addReviewDB(Review name) {
        reviewAccess.addReview(name);
    }
}


