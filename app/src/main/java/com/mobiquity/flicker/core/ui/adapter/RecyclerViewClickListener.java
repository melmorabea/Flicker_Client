package com.mobiquity.flicker.core.ui.adapter;

import android.view.View;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public interface RecyclerViewClickListener<M> {

    void onItemClicked(View view, M model);

}
