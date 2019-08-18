package comp3350.amate;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.amate.presentation.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ReviewTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);
    private String bookToReview = "Inferno";

    @Test
    public void searchBook() {

        onView(withId(R.id.search_view)).perform(typeText(bookToReview));
        onView(withId(R.id.search_title)).perform(click());

        onView(withId(R.id.bookEntryButton)).perform(click());

        //now, make review on the book
        reviewBook();


    }

    public void reviewBook(){
        String comment="   good book!";
        onView(withId(R.id.reviewButton)).perform(click());
        onView(withId(R.id.reviewText)).perform(typeText(comment));
        onView(withId(R.id.rateThree)).perform(click());
        onView(withId(R.id.submitReview)).perform(click());


    }
}

