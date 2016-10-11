package com.mobiquity.flicker.photosearch.view;

import com.mobiquity.flicker.core.ui.MvpView;
import com.mobiquity.flicker.photosearch.model.dto.Photo;

import java.util.List;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public interface PhotoSearchView extends MvpView {


    void showPhotos(List<Photo> photos, boolean loadMore);

    void showLoading();

    void hideLoading();

    void showLoadMoreLoading();

    void hideLoadMoreLoading();

    void noDataFound();

    void showError(String error);

}
