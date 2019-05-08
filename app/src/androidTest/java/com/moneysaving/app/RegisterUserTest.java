package com.moneysaving.app;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;


// This tests if register button click
public class RegisterUserTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(),null, false);

    @Before
    public void setUp() throws Exception {

        mActivity = mainActivityTestRule.getActivity();
    }


    @Test
    public void testLaunchOnLoginBtnClick () {
        assertNotNull(mActivity.findViewById(R.id.SignUp));

        onView(withId(R.id.SignUp)).perform(click());

        Activity logInActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);

        assertNotNull(logInActivity);

        logInActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}
