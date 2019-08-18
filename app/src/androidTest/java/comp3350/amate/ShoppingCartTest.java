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
public class ShoppingCartTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);
    private String bookToSearch = "Divine comedy";
    private String username_typed = "Angel123";
    private String last_first_name = "Angel Mercedes";
    private String phoneNum = "2045569708";
    private String addr = "121 pembina Avenue";
    private String email = "mark@myumanitoba.ca";
    private String correct_password = "mark123";
    //payment info for user
    private String Card_Num="4222222222222444";
    private String Holder_Name=last_first_name;
    private String expir="05/20";
    private String cvv ="103";
    @Test
    public void CartTest() {
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
        //now we add book to cart

        onView(withId(R.id.purchaseButton)).perform(click());

        onView(withId(R.id.btn_placeorder)).perform(click());
        //enter payment info:

        onView(withId(R.id.editText)).perform(typeText(Card_Num));
        onView(withId(R.id.editText3)).perform(typeText(Holder_Name));
        onView(withId(R.id.editText4)).perform(typeText(expir));
        onView(withId(R.id.editText5)).perform(typeText(cvv));
        onView(withId(R.id.button6)).perform(click());



    }
}
