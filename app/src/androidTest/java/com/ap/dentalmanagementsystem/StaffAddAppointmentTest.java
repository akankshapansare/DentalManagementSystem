package com.ap.dentalmanagementsystem;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.ap.dentalmanagementsystem.ui.activity.HomeScreenActivity;
import com.ap.dentalmanagementsystem.ui.activity.LoginActivity;

import org.hamcrest.Matchers;
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
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class StaffAddAppointmentTest {

    @Rule
    public IntentsTestRule<LoginActivity> intentsTestRule =
            new IntentsTestRule<>(LoginActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void setup() {
        mIdlingResource = intentsTestRule.getActivity().getIdlingResource();
        // To prove that the test fails, omit this call:
        IdlingRegistry.getInstance().register(mIdlingResource);

        onView(withId(R.id.text_email)).perform(typeText("staff@staff.com"), closeSoftKeyboard());
        onView(withId(R.id.text_password)).perform(typeText("staff123"), closeSoftKeyboard());
        onView(withId(R.id.button_login)).perform(click());

        //wait till IdlingResource is available

        intended(hasComponent(HomeScreenActivity.class.getName()));

        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(open()); // Open Drawer

    }

    @Test
    public void checkStaffAddAppointmentActivity() {
        onView(withId(R.id.recyler_view_staff_fragment))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText("Add Appointment")), click()));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int num = new Random().nextInt()%500;
        onView(withId(R.id.text_name_patient)).perform(typeText("Martin"+num+" Corner"+num), closeSoftKeyboard());
        onView(withId(R.id.number_mobile_patient)).perform(typeText("8467139500"), closeSoftKeyboard());
        onView(withId(R.id.text_name_doctor)).perform(typeText("Karl198 Clark198"), closeSoftKeyboard());
        onView(withId(R.id.text_appointment_description)).perform(typeText("General check-up"), closeSoftKeyboard());
        setDate(R.id.text_appointment_start_date, 2017, 9, 20);
        setTime(R.id.text_appointment_start_time, 10, 0);
        setTime(R.id.text_appointment_end_time, 10, 30);
        onView(withId(R.id.button_save)).perform(click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void setDate(int datePickerLaunchViewId, int year, int monthOfYear, int dayOfMonth) {
        onView(withId(datePickerLaunchViewId)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, monthOfYear, dayOfMonth));
        onView(withId(android.R.id.button1)).perform(click());
    }

    private static void setTime(int timePickerLaunchViewId, int hours, int minutes) {
        onView(withId(timePickerLaunchViewId)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hours, minutes));
        onView(withId(android.R.id.button1)).perform(click());
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
}
