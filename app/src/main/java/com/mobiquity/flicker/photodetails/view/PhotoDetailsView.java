package com.mobiquity.flicker.photodetails.view;

import com.mobiquity.flicker.core.ui.MvpView;
import com.mobiquity.flicker.photosearch.model.dto.Photo;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public interface PhotoDetailsView extends MvpView {

    Photo getArgument();

    void showPhoto(Photo photo);

}
