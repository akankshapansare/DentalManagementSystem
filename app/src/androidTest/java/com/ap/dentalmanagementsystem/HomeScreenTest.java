package com.ap.dentalmanagementsystem;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ap.dentalmanagementsystem.ui.activity.HomeScreenActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class HomeScreenTest {



    @Rule
    public ActivityTestRule<HomeScreenActivity> doctorHomeScreenActivityRule =
            new ActivityTestRule<HomeScreenActivity>(HomeScreenActivity.class,
                    true,     // initialTouchMode
                    false);   // launchActivity. False to customize the intent

    @Test
    public void checkdoctorHomeScreenActivity() {
        Intent intent = new Intent();
        intent.putExtra("INTENT_EXTRA_ROLE", "DOCTOR");
        doctorHomeScreenActivityRule.launchActivity(intent);

        onView(withId(R.id.recyler_view_home_screen)).check(matches(isDisplayed()));
    }
}
