package com.ap.dentalmanagementsystem;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;

import com.ap.dentalmanagementsystem.ui.activity.HomeScreenActivity;
import com.ap.dentalmanagementsystem.ui.activity.LoginActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class AdminAddStaffTest {

    @Rule
    public IntentsTestRule<LoginActivity> intentsTestRule =
            new IntentsTestRule<>(LoginActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void setup() {
        mIdlingResource = intentsTestRule.getActivity().getIdlingResource();
        // To prove that the test fails, omit this call:
        IdlingRegistry.getInstance().register(mIdlingResource);

        onView(withId(R.id.text_email)).perform(typeText("admin@admin.com"), closeSoftKeyboard());
        onView(withId(R.id.text_password)).perform(typeText("admin123"), closeSoftKeyboard());
        onView(withId(R.id.button_login)).perform(click());

        //wait till IdlingResource is available

        intended(hasComponent(HomeScreenActivity.class.getName()));

        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(open()); // Open Drawer

    }

    @Test
    public void checkDoctorAddPatientActivity() {
        onView(withId(R.id.recyler_view_admin_fragment))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText("Add Staff")), click()));

        int num = new Random().nextInt()%500;
        onView(withId(R.id.text_first_name)).perform(typeText("Kelly"+num), closeSoftKeyboard());
        onView(withId(R.id.text_last_name)).perform(typeText("Morgan"+num), closeSoftKeyboard());
        onView(withId(R.id.text_date_of_birth)).perform(typeText("02/18/1990"), closeSoftKeyboard());
        onView(withId(R.id.text_sex)).perform(typeText("Female"), closeSoftKeyboard());
        onView(withId(R.id.text_address)).perform(typeText("2104 Royal Drive, CA 95050"), closeSoftKeyboard());
        onView(withId(R.id.text_speciality)).perform(typeText("Receptionist"), closeSoftKeyboard());
        onView(withId(R.id.number_mobile)).perform(typeText("3457239500"), closeSoftKeyboard());
        onView(withId(R.id.text_email)).perform(typeText("kelly"+num+"@morgan"+num+".com"), closeSoftKeyboard());
        onView(withId(R.id.text_password)).perform(typeText("abc123"), closeSoftKeyboard());
        onView(withId(R.id.button_save)).perform(click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
}
