package com.example.lab1;

import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.espresso.matcher.ViewMatchers.*;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.actionWithAssertions;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.*;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.lab1", appContext.getPackageName());
    }

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    // test name from setting if its display on main activity

    @Test
    public void userApp(){
        onView(withId(R.id.settingsPage)).perform(click());
        onView(withId(R.id.usernameInput)).perform(typeText("maram"), closeSoftKeyboard());
        onView(withId(R.id.usernameSave)).perform(click());
        Espresso.pressBackUnconditionally();
        onView(withId(R.id.userNameTasks)).check(matches(withText("maram Task")));
    }

    @Test
    public void userApp1(){
     onView(withId(R.id.settingsPage)).check(matches(isDisplayed()));
    }


//    @Test
//    public void userApp2(){
//        onView(withId(R.id.TaskListRecycler)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
//        onView(withId(R.id.BodyDetail)).check(matches(withText("TASK1")));
//    }





}