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
public class RecommendationTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);
    private String searchBook="Inferno";
    @Test
    public void recomTest() {
        onView(withId(R.id.search_view)).perform(typeText(searchBook));
        onView(withId(R.id.search_title)).perform(click());
        onView(withId(R.id.bookEntryButton)).perform(click());
        //to get books by the same authors
        onView(withId(R.id.bookRecommendAuthor)).perform(click());
    }


}
