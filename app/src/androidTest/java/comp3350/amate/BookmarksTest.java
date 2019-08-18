package comp3350.amate;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.amate.presentation.MainActivity;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BookmarksTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);
    private String bookToSearch = "Divine comedy";
    private String username_typed = "ben123";
    private String last_first_name = "Ben Lagos";
    private String phoneNum = "2045634708";
    private String addr = "121 roadway Avenue";
    private String email = "ben@myumanitoba.ca";
    private String correct_password = "ben1234";

    @Test
    public void BookMarkTest() {
        onView(withId(R.id.sign_in)).perform(click());
        onView(withId(R.id.buttonSignup)).perform(click());

        //informations needed to create account

        onView(withId(R.id.signupUserID)).perform(typeText(username_typed));
        onView(withId(R.id.signupUserName)).perform(typeText(last_first_name));
        onView(withId(R.id.signupUserNum)).perform(typeText(phoneNum));
        onView(withId(R.id.signupUserAddress)).perform(typeText(addr));
        onView(withId(R.id.signupUserEmail)).perform(typeText(email));
        onView(withId(R.id.signupPassword)).perform(typeText(correct_password));

        onView(withId(R.id.customerMode)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.buttonRegister)).perform(click());

        onView(withId(R.id.signinUserID)).perform(typeText(username_typed));
        onView(withId(R.id.signinPassword)).perform(typeText(correct_password));
        onView(withId(R.id.buttonSignin)).perform(click());
        closeSoftKeyboard();

        onView(withId(R.id.buttonHome)).perform(click());

        // search for book
        onView(withId(R.id.search_view)).perform(typeText(bookToSearch));
        onView(withId(R.id.search_title)).perform(click());
        onView(withId(R.id.bookEntryButton)).perform(click());
        onView(withId(R.id.addBookmarkButton)).perform(click());
        onView(withText("Confirm")).perform(click());
        pressBack();
        pressBack();
        onView(withId(R.id.bookmark)).perform(click());
        pressBack();
        onView(withId(R.id.bookmark)).perform(click());
        onView(withId(R.id.bookmarkEntryButton)).perform(click());
        pressBack();
        onView(withId(R.id.getBestSeller)).perform(click());
        onView(withId(R.id.addBookmarkButton)).perform(click());
        onView(withText("Confirm")).perform(click());
        pressBack();

        onView(withId(R.id.bookmark)).perform(click());


    }
}
