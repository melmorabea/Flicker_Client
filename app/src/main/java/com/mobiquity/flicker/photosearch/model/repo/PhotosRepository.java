package com.mobiquity.flicker.photosearch.model.repo;

import com.mobiquity.flicker.core.error.ErrorManager;
import com.mobiquity.flicker.core.error.FlickerException;
import com.mobiquity.flicker.photosearch.model.dto.PhotoSearchResult;
import com.mobiquity.flicker.photosearch.presenter.PhotosRepo;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public class PhotosRepository implements PhotosRepo {

    private PhotosRepo cloudPhotosRepo;

    @Inject
    public PhotosRepository(@Named("cloud_repo") PhotosRepo cloudPhotosRepo) {
        this.cloudPhotosRepo = cloudPhotosRepo;
    }

    @Override
    public Observable<PhotoSearchResult> searchForPhotos(String queryString, int page) {
        return ErrorManager.wrap(cloudPhotosRepo.searchForPhotos(queryString, page))
                .flatMap(new Func1<PhotoSearchResult, Observable<PhotoSearchResult>>() {
                    @Override
                    public Observable<PhotoSearchResult> call(PhotoSearchResult photoSearchResult) {
                        if (photoSearchResult == null || photoSearchResult.getPhotosModel() == null
                                || photoSearchResult.getPhotosModel().getPhotos() == null
                                || photoSearchResult.getPhotosModel().getPhotos().isEmpty()) {
                            return Observable.error(new FlickerException(FlickerException.NO_DATA_ERROR,
                                    "No results found for your search."));
                        }
                        return Observable.just(photoSearchResult);
                    }
                });
    }

}
