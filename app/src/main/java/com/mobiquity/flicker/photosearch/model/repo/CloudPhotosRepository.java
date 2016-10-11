package com.mobiquity.flicker.photosearch.model.repo;

import com.mobiquity.flicker.BuildConfig;
import com.mobiquity.flicker.photosearch.model.dto.PhotoSearchResult;
import com.mobiquity.flicker.photosearch.model.services.FlickrPhotoSearchService;
import com.mobiquity.flicker.photosearch.presenter.PhotosRepo;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public class CloudPhotosRepository implements PhotosRepo {

    private FlickrPhotoSearchService service;

    @Inject
    public CloudPhotosRepository(FlickrPhotoSearchService service) {
        this.service = service;
    }

    @Override
    public Observable<PhotoSearchResult> searchForPhotos(String queryString, int page) {
        return service.searchForPhoto(queryString, page, BuildConfig.flickr_api_key);
    }

}
