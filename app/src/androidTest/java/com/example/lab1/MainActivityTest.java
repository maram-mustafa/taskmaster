package com.example.lab1;

import junit.framework.TestCase;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;



import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MainActivityTest extends TestCase {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    public static final String username = "maram";
    public static final String body = "Name";

    @Test
    public void testSettingButton() {
        onView(withId(R.id.settingsPage)).perform(click());
        onView(withId(R.id.usernameInput)).perform(typeText(username));
        closeSoftKeyboard();
        onView(withId(R.id.usernameSave)).perform(click());
        onView(withId(R.id.userNameTasks)).check(matches(withText("username"+ username+ " task")));
    }

    @Test
    public void testGoToDetails() {
        onView(withId(R.id.TaskListRecycler)).perform(actionOnItemAtPosition(0 ,click()));
        onView(withId(R.id.BodyDetail)).check(matches(withText(body)));

    }

}