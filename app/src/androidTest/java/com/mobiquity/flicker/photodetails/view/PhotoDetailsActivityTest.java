package com.mobiquity.flicker.photodetails.view;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.mobiquity.flicker.R;
import com.mobiquity.flicker.photosearch.model.dto.Photo;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */
public class PhotoDetailsActivityTest {

    @Rule
    public ActivityTestRule<PhotoDetailsActivity> activityActivityTestRule =
            new ActivityTestRule<>(PhotoDetailsActivity.class, true, false);

    @Test
    public void testWhenOpeningDetailsScreenWithValidObject() {
        // Given
        String title = "momo";
        Photo photo = new Photo();
        photo.setTitle(title);
        Intent intent = new Intent();
        intent.putExtra("photo", photo);

        // When
        activityActivityTestRule.launchActivity(intent);

        // Then
        Espresso.onView(ViewMatchers.withId(R.id.photo_details_title))
                .check(ViewAssertions.matches(ViewMatchers.withText(title)));
    }

    @Test
    public void testWhenOpeningDetailsScreenWithNullObject() {

        // When
        activityActivityTestRule.launchActivity(new Intent());

        // Then
        Espresso.onView(ViewMatchers.withId(R.id.photo_details_title))
                .check(ViewAssertions.matches(ViewMatchers.withText(Matchers.isEmptyOrNullString())));
    }

}