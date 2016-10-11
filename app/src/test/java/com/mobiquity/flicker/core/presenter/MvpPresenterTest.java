package com.mobiquity.flicker.core.presenter;

import com.mobiquity.flicker.core.ui.MvpView;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */
public class MvpPresenterTest<V extends MvpView> {

    @Mock
    V view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void onAttachWithValidView() throws Exception {
        // Given
        MvpPresenter<V> presenter = new MvpPresenter<>();

        // When
        presenter.onAttach(view);

        // Then
        Assert.assertEquals(view, presenter.view());
    }

    @Test(expected = IllegalArgumentException.class)
    public void onAttachWithNullView() throws Exception {
        // Given
        MvpPresenter<V> presenter = new MvpPresenter<>();

        // When
        presenter.onAttach(null);

        // Then exception should be thrown
    }

    @Test
    public void viewWhenAttachedToPresenter() throws Exception {
        // Given
        MvpPresenter<V> presenter = new MvpPresenter<>();

        // When
        presenter.onAttach(view);

        // Then
        Assert.assertEquals(view, presenter.view());
    }

    @Test
    public void viewWithoutAttachedToPresenter() throws Exception {
        // Given
        MvpPresenter<V> presenter = new MvpPresenter<>();

        // When no view is attached to presenter

        // Then
        Assert.assertEquals(null, presenter.view());
    }

    @Test
    public void onDetach() throws Exception {
        // Given
        MvpPresenter<V> presenter = new MvpPresenter<>();

        // When
        presenter.onAttach(view);
        presenter.onDetach();

        // Then
        Assert.assertEquals(null, presenter.view());
    }

    @Test
    public void isViewAttachedWhenViewAttached() throws Exception {
        // Given
        MvpPresenter<V> presenter = new MvpPresenter<>();

        // When
        presenter.onAttach(view);

        // Then
        Assert.assertEquals(true, presenter.isViewAttached());
    }

    @Test
    public void isViewAttachedWhenViewDetached() throws Exception {
        // Given
        MvpPresenter<V> presenter = new MvpPresenter<>();

        // When
        presenter.onAttach(view);
        presenter.onDetach();

        // Then
        Assert.assertEquals(false, presenter.isViewAttached());
    }

}