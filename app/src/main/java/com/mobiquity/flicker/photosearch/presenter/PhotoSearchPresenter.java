package com.mobiquity.flicker.photosearch.presenter;

import com.mobiquity.flicker.core.error.FlickerException;
import com.mobiquity.flicker.core.presenter.BasePresenter;
import com.mobiquity.flicker.core.presenter.RxSubscriptionsHelper;
import com.mobiquity.flicker.photosearch.model.dto.PhotoSearchResult;
import com.mobiquity.flicker.photosearch.view.PhotoSearchView;

import javax.inject.Inject;
import javax.inject.Named;

import rx.SingleSubscriber;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public class PhotoSearchPresenter extends BasePresenter<PhotoSearchView> {

    private PhotosRepo photosRepo;

    private String lastQueryString;
    private PhotoSearchResult lastSearchResult;

    @Inject
    public PhotoSearchPresenter(@Named("repo_impl") PhotosRepo photosRepo, RxSubscriptionsHelper rxSubscriptionsHelper) {
        super(rxSubscriptionsHelper);
        this.photosRepo = photosRepo;
    }

    public void searchPhotos(String queryString) {
        view().showLoading();
        searchPhotos(queryString, 1, false);
    }

    private void searchPhotos(String queryString, int page, final boolean loadMore) {
        lastQueryString = queryString;

        if (queryString == null || queryString.isEmpty()) {
            view().hideLoading();
            view().hideLoadMoreLoading();
            return;
        }

        subscribeToObservable(photosRepo.searchForPhotos(queryString, page), new SingleSubscriber<PhotoSearchResult>() {
            @Override
            public void onSuccess(PhotoSearchResult photoSearchResult) {
                lastSearchResult = photoSearchResult;
                if (!isViewAttached())
                    return;
                view().showPhotos(photoSearchResult.getPhotosModel().getPhotos(), loadMore);
            }

            @Override
            public void onError(Throwable error) {
                if (!isViewAttached())
                    return;
                if (loadMore)
                    view().hideLoadMoreLoading();
                else
                    view().showError(((FlickerException) error).getErrorMessage());
            }
        });
    }

    public void loadMore() {
        view().showLoadMoreLoading();
        searchPhotos(lastQueryString, getPageNumberFromLastResult(lastSearchResult), true);
    }

    private int getPageNumberFromLastResult(PhotoSearchResult lastSearchResult) {
        if (lastSearchResult == null)
            return 1;
        return lastSearchResult.getPhotosModel().getPage() + 1;
    }

}
