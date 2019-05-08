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

public class HomePageLaunchTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(HomePageActivity.class.getName(),null, false);

    @Before
    public void setUp() throws Exception {

        mActivity = mainActivityTestRule.getActivity();
    }


    @Test
    public void testLaunchOnLoginBtnClick () {
        assertNotNull(mActivity.findViewById(R.id.login));

        onView(withId(R.id.login)).perform(click());

        Activity logInActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);

        assertNotNull(logInActivity);

        logInActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}