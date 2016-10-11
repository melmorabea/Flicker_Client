package com.mobiquity.flicker.photosearch.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.mobiquity.flicker.R;
import com.mobiquity.flicker.core.ui.activity.BaseActivity;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public class PhotoSearchActivity extends BaseActivity {

    private CountingIdlingResource idlingResource = new CountingIdlingResource(PhotoSearchActivity.class.getName());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_search);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_photos_menu, menu);

        final MenuItem myActionMenuItem = menu.findItem(R.id.photos_menu_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                notifyFragmentWithSearch(query);
                if(!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    private void notifyFragmentWithSearch(String query) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.photo_search_fragment_layout_id);
        if (fragment != null && fragment instanceof PhotoSearchFragment) {
            ((PhotoSearchFragment) fragment).onSearchTriggered(query);
        }
    }

    public CountingIdlingResource getIdlingResource() {
        return idlingResource;
    }

}
