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

import static android.support.test.espresso.Espresso.onData;
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
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.StringStartsWith.startsWith;

@RunWith(AndroidJUnit4.class)
public class DoctorAddPrescriptionTest {

    @Rule
    public IntentsTestRule<LoginActivity> intentsTestRule =
            new IntentsTestRule<>(LoginActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void setup() {
        mIdlingResource = intentsTestRule.getActivity().getIdlingResource();
        // To prove that the test fails, omit this call:
        IdlingRegistry.getInstance().register(mIdlingResource);

        onView(withId(R.id.text_email)).perform(typeText("dms@dms.com"), closeSoftKeyboard());
        onView(withId(R.id.text_password)).perform(typeText("dms123"), closeSoftKeyboard());
        onView(withId(R.id.button_login)).perform(click());

        //wait till IdlingResource is available

        intended(hasComponent(HomeScreenActivity.class.getName()));

        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(open()); // Open Drawer

    }

    @Test
    public void checkDoctorAddPrescriptionActivity() {
        onView(withId(R.id.recyler_view_doctor_fragment))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText("Add Prescription")), click()));
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.recycler_view_search_patient))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText("Aditya Garud")), click()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.number_prescription)).perform(typeText("P1048"), closeSoftKeyboard());
        onView(withId(R.id.text_drug_name)).perform(typeText("acetaminophen "), closeSoftKeyboard());
        onView(withId(R.id.number_quntity)).perform(typeText("6"), closeSoftKeyboard());
        onView(withId(R.id.number_frequency_in_day)).perform(typeText("2"), closeSoftKeyboard());
        onView(withId(R.id.text_more_information)).perform(typeText("Take pills after meal"), closeSoftKeyboard());
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
