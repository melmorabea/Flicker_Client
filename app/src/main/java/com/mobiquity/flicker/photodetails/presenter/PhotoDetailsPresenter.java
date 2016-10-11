package com.mobiquity.flicker.photodetails.presenter;

import com.mobiquity.flicker.core.presenter.BasePresenter;
import com.mobiquity.flicker.core.presenter.RxSubscriptionsHelper;
import com.mobiquity.flicker.photodetails.view.PhotoDetailsView;
import com.mobiquity.flicker.photosearch.model.dto.Photo;

import javax.inject.Inject;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public class PhotoDetailsPresenter extends BasePresenter<PhotoDetailsView> {

    @Inject
    public PhotoDetailsPresenter(RxSubscriptionsHelper rxSubscriptionsHelper) {
        super(rxSubscriptionsHelper);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();
        Photo photo = view().getArgument();
        if (photo != null)
            view().showPhoto(photo);
    }

}
