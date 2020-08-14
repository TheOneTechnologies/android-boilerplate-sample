package com.theonetech.android.presentation.view.activity;

import com.theonetech.android.R;

import org.hamcrest.core.AllOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<RegisterActivity> activityTestRule = new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void testClearView() {
        onView(withId(R.id.edt_name)).perform(clearText());
        onView(withId(R.id.edt_email)).perform(clearText());
        onView(withId(R.id.edt_password)).perform(clearText());
        onView(withId(R.id.edt_confirm_password)).perform(clearText());
        onView(withId(R.id.btn_register)).perform(click());
        onView(withId(R.id.btn_login)).perform(click());
    }

    //Check view is visible
    @Test
    public void view_IsVisible() {
        onView(withId(R.id.edt_name)).check(matches(isDisplayed()));
        onView(withId(R.id.edt_name)).check(matches(isDisplayed()));
        onView(withId(R.id.edt_email)).check(matches(isDisplayed()));
        onView(withId(R.id.edt_password)).check(matches(isDisplayed()));
        onView(withId(R.id.edt_confirm_password)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_register)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_login)).check(matches(isDisplayed()));
    }

    //Using dummy data do Registration
    @Test
    public void signUpTest() {
        onView(withId(R.id.edt_name)).perform(typeText("Student"), closeSoftKeyboard());
        onView(withId(R.id.edt_email)).perform(typeText("Student@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.edt_password)).perform(typeText("Password12345"), closeSoftKeyboard());
        onView(withId(R.id.edt_confirm_password)).perform(typeText("Password12345"), closeSoftKeyboard());
        onView(AllOf.allOf(withId(R.id.btn_register), withText("SignUp"))).perform(click());
        assertTrue(activityTestRule.getActivity().isFinishing());
    }

}
