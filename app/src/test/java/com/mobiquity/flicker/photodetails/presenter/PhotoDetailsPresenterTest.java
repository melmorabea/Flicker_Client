package com.mobiquity.flicker.photodetails.presenter;

import com.mobiquity.flicker.core.presenter.RxSubscriptionsHelper;
import com.mobiquity.flicker.photodetails.view.PhotoDetailsView;
import com.mobiquity.flicker.photosearch.model.dto.Photo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */
public class PhotoDetailsPresenterTest {

    @Mock
    PhotoDetailsView view;

    private PhotoDetailsPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new PhotoDetailsPresenter(new RxSubscriptionsHelper(new CompositeSubscription(), Schedulers.immediate()));
        presenter.onAttach(view);
    }

    @Test
    public void onViewLoaded() throws Exception {
        // Given initialized presenter as above
        Photo photo = new Photo();
        Mockito.when(view.getArgument()).thenReturn(photo);

        // When
        presenter.onAttach(view);
        presenter.onViewLoaded();

        // Then
        Mockito.verify(view).showPhoto(photo);
    }

    @Test
    public void onViewLoadedWithNullImage() throws Exception {
        // Given initialized presenter as above
        Mockito.when(view.getArgument()).thenReturn(null);

        // When
        presenter.onAttach(view);
        presenter.onViewLoaded();

        // Then
        Mockito.verify(view, Mockito.times(0)).showPhoto(null);
    }

}