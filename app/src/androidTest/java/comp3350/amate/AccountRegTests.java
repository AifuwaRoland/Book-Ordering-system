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
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AccountRegTests {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);
    private String username_typed = "mark12";
    private String last_first_name = "Mark john";
    private String phoneNum = "2045569708";
    private String addr = "121 markham Avenue";
    private String email = "mark@myumanitoba.ca";
    private String correct_password = "mark123";

    @Test
    public void createAccount() {

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


        //verify the account was created

        loginSuccess();


    }

    public void loginSuccess() {
        onView(withId(R.id.signinUserID)).perform(typeText(username_typed));
        onView(withId(R.id.signinPassword)).perform(typeText(correct_password));
        onView(withId(R.id.buttonSignin)).perform(click());
        closeSoftKeyboard();
    }

}
