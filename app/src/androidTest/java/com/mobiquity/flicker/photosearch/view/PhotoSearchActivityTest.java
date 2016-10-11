package com.mobiquity.flicker.photosearch.view;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.view.KeyEvent;
import android.widget.EditText;

import com.mobiquity.flicker.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */
public class PhotoSearchActivityTest {

    @Rule
    public ActivityTestRule<PhotoSearchActivity> activityActivityTestRule =
            new ActivityTestRule<>(PhotoSearchActivity.class);

    private IdlingResource idlingResource;

    @Before
    public void setUp() {
        idlingResource = activityActivityTestRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(idlingResource);
    }

    @Test
    public void clickSearchIconWithSearchText_ShowsSearchResult() {
        onView(ViewMatchers.withId(R.id.photos_menu_search)).perform(ViewActions.click());

        onView(isAssignableFrom(EditText.class)).perform(typeText("food"), pressKey(KeyEvent.KEYCODE_ENTER));

        onView(ViewMatchers.withId(R.id.photo_search_recycler)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() {
        Espresso.unregisterIdlingResources(idlingResource);
        idlingResource = null;
    }

}