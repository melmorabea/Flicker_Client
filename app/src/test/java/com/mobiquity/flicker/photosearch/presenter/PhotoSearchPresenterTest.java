package com.mobiquity.flicker.photosearch.presenter;

import com.mobiquity.flicker.core.presenter.RxSubscriptionsHelper;
import com.mobiquity.flicker.photosearch.model.dto.Photo;
import com.mobiquity.flicker.photosearch.model.dto.PhotoSearchResult;
import com.mobiquity.flicker.photosearch.model.dto.PhotoSearchResult.PhotosModel;
import com.mobiquity.flicker.photosearch.model.repo.PhotosRepository;
import com.mobiquity.flicker.photosearch.view.PhotoSearchView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */
public class PhotoSearchPresenterTest {

    @Mock
    PhotoSearchView view;

    @Mock
    PhotosRepo photosRepo;

    @Mock
    Scheduler scheduler;

    private PhotoSearchPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new PhotoSearchPresenter(new PhotosRepository(photosRepo), new RxSubscriptionsHelper(new CompositeSubscription(), Schedulers.immediate()));
        presenter.onAttach(view);
    }

    @Test
    public void searchPhotos() throws Exception {
        // Given initialized presenter with attached view
        PhotoSearchResult result = getValidPhotos();
        Mockito.when(photosRepo.searchForPhotos(ArgumentMatchers.any(String.class),
                ArgumentMatchers.any(Integer.class))).thenReturn(Observable.just(result));

        // When
        presenter.searchPhotos("any string");

        // Then
        Mockito.verify(view).showLoading();
        Mockito.verify(photosRepo).searchForPhotos(ArgumentMatchers.any(String.class),
                ArgumentMatchers.any(Integer.class));
        Mockito.verify(view).showPhotos(result.getPhotosModel().getPhotos(), false);
    }

    @Test
    public void searchPhotosWithEmptyResult() throws Exception {
        // Given initialized presenter with attached view
        Mockito.when(photosRepo.searchForPhotos(ArgumentMatchers.any(String.class),
                ArgumentMatchers.any(Integer.class))).thenReturn(Observable.just(getEmptyResult()));

        // When
        presenter.searchPhotos("any string");

        // Then
        Mockito.verify(view).showLoading();
        Mockito.verify(photosRepo).searchForPhotos(ArgumentMatchers.any(String.class),
                ArgumentMatchers.any(Integer.class));
        Mockito.verify(view).showError(ArgumentMatchers.anyString());
    }

    @Test
    public void searchPhotosWithNullResult() throws Exception {
        // Given initialized presenter with attached view
        Mockito.when(photosRepo.searchForPhotos(ArgumentMatchers.any(String.class),
                ArgumentMatchers.any(Integer.class))).thenReturn(Observable.just(getNullResult()));

        // When
        presenter.searchPhotos("any string");

        // Then
        Mockito.verify(view).showLoading();
        Mockito.verify(photosRepo).searchForPhotos(ArgumentMatchers.any(String.class),
                ArgumentMatchers.any(Integer.class));
        Mockito.verify(view).showError(ArgumentMatchers.anyString());
    }

    @Test
    public void loadMoreForFirstTime() throws Exception {
        // Given initialized presenter with attached view
        PhotoSearchResult result = getValidPhotos();
        Mockito.when(photosRepo.searchForPhotos(ArgumentMatchers.any(String.class),
                ArgumentMatchers.any(Integer.class))).thenReturn(Observable.just(result));

        // When
        presenter.loadMore();

        // Then
        Mockito.verify(view).hideLoading();
        Mockito.verify(view).hideLoadMoreLoading();
    }

    @Test
    public void loadMore() throws Exception {
        // Given initialized presenter with attached view
        PhotoSearchResult result = getValidPhotos();
        Mockito.when(photosRepo.searchForPhotos(ArgumentMatchers.any(String.class),
                ArgumentMatchers.any(Integer.class))).thenReturn(Observable.just(result));

        // When
        presenter.searchPhotos("any string");
        presenter.loadMore();

        // Then
        Mockito.verify(view).showLoadMoreLoading();
        Mockito.verify(photosRepo, Mockito.times(2)).searchForPhotos(ArgumentMatchers.any(String.class),
                ArgumentMatchers.any(Integer.class));
        Mockito.verify(view).showPhotos(result.getPhotosModel().getPhotos(), true);
    }

    private PhotoSearchResult getNullResult() {
        return null;
    }

    private PhotoSearchResult getEmptyResult() {
        List<Photo> photos = new ArrayList<>();
        PhotosModel photosModel = new PhotosModel();
        photosModel.setPhotos(photos);

        PhotoSearchResult result = new PhotoSearchResult();
        result.setPhotosModel(photosModel);
        return result;
    }

    private PhotoSearchResult getValidPhotos() {
        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo());
        photos.add(new Photo());
        photos.add(new Photo());

        PhotosModel photosModel = new PhotosModel();
        photosModel.setPhotos(photos);

        PhotoSearchResult result = new PhotoSearchResult();
        result.setPhotosModel(photosModel);
        return result;
    }

}