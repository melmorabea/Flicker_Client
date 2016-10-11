package com.mobiquity.flicker.injection;

import com.mobiquity.flicker.injection.network.NetworkModule;
import com.mobiquity.flicker.photodetails.presenter.PhotoDetailsPresenter;
import com.mobiquity.flicker.photosearch.presenter.PhotoSearchPresenter;

import dagger.Component;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    PhotoSearchPresenter photoSearchPresenter();

    PhotoDetailsPresenter photoDetailsPresenter();

}
