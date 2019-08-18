package comp3350.amate;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.amate.presentation.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void searchByAuthor() {
        onView(withId(R.id.search_view)).perform(typeText("Dante Alighieri"));
        onView(withId(R.id.search_author)).perform(click());
        pressBack();
    }
    @Test
    public void searchByTitle() {
        onView(withId(R.id.search_view)).perform(typeText("The Inferno"));
        onView(withId(R.id.search_title)).perform(click());
        pressBack();
    }

}
