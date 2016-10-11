package com.mobiquity.flicker.photosearch.presenter;

import com.mobiquity.flicker.photosearch.model.dto.PhotoSearchResult;

import rx.Observable;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public interface PhotosRepo {

    Observable<PhotoSearchResult> searchForPhotos(String queryString, int page);

}
