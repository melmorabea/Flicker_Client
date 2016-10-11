package com.mobiquity.flicker.photosearch.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobiquity.flicker.R;
import com.mobiquity.flicker.core.ui.activity.BaseActivity;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public class PhotoSearchActivity extends BaseActivity {

    TextView title;
    EditText searchEditText;
    ImageView searchIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_search);

//        setUpToolbar(savedInstanceState == null ? null : savedInstanceState.getString("query"));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.search_photos_menu, menu);

        final MenuItem myActionMenuItem = menu.findItem( R.id.photos_menu_search);
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

    private void setUpToolbar(String query) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));

        title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        searchEditText = (EditText) toolbar.findViewById(R.id.toolbar_search_edit_text);
        searchIcon = (ImageView) toolbar.findViewById(R.id.toolbar_search_icon);

        searchIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchEditText.getVisibility() == View.GONE) {
                    title.setVisibility(View.GONE);
                    searchEditText.setVisibility(View.VISIBLE);
                } else {
                    notifyFragmentWithSearch(searchEditText.getText().toString());
                }
            }
        });

        if (!TextUtils.isEmpty(query)) {
            title.setVisibility(View.GONE);
            searchEditText.setVisibility(View.VISIBLE);
            searchEditText.setText(query);
        }
    }

    private void notifyFragmentWithSearch(String query) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.photo_search_fragment_layout_id);
        if (fragment != null && fragment instanceof PhotoSearchFragment) {
            ((PhotoSearchFragment) fragment).onSearchTriggered(query);
        }
    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString("query", searchEditText.getText().toString());
//    }
}
