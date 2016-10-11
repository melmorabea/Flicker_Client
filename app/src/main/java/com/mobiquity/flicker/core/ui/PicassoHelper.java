package com.mobiquity.flicker.core.ui;

import android.content.Context;
import android.widget.ImageView;

import com.mobiquity.flicker.BuildConfig;
import com.mobiquity.flicker.R;
import com.squareup.picasso.Picasso;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public class PicassoHelper {

    private PicassoHelper() {}

    public static void loadImage(Context context, String url, ImageView view) {
        Picasso picasso = Picasso.with(context);
        if (BuildConfig.DEBUG) {
            picasso.setIndicatorsEnabled(true);
            picasso.setLoggingEnabled(true);
        }
        picasso.load(url)
                .placeholder(R.drawable.icon_place_holder)
                .error(R.drawable.ic_info_outline_48px)
                .into(view);
    }

}
