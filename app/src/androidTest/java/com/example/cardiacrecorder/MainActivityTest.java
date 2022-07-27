package com.example.cardiacrecorder;
import static android.service.autofill.Validators.not;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

import android.os.SystemClock;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
//import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);
    //private Date PickerActions;
    @Test
    public void projectTest(){
        SystemClock.sleep(3000);
        Espresso.onView(withId(R.id.add)).perform(click());
        Espresso.onView(withId(R.id.record)).check(matches(isDisplayed()));
        int year=2000;
        int month=10;
        int day=23;
        int hour = 10;
        int minutes = 59;
        Espresso.onView(withId(R.id.datePickerButton)).perform(click());
        Espresso.onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, month, day));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.timePickerButton)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour, minutes));
        onView(withId(android.R.id.button1)).perform(click());
        Espresso.onView(withId(R.id.systolic)).perform(ViewActions.typeText("135"));
        Espresso.onView(withId(R.id.diastolic)).perform(ViewActions.typeText("80"));
        Espresso.onView(withId(R.id.heartrate)).perform(ViewActions.typeText("80"));
        Espresso.pressBack();
        Espresso. onView(withId(R.id.comment)).perform(ViewActions.typeText("Health is good"));
        Espresso.pressBack();
        Espresso.onView(withId(R.id.update)).perform(click());
    }

}