package com.moneysaving.app;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;


// This class tests the launch of the login page
public class MainActivityTest {

    @Rule
    public ActivityTestRule<activity_login_in> mainActivityTestRule = new ActivityTestRule<activity_login_in>(activity_login_in.class);

    private activity_login_in mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mainActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch () throws Exception {
        View view = mActivity.findViewById(R.id.Password1);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}