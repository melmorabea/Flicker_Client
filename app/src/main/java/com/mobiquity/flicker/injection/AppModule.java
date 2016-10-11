package com.mobiquity.flicker.injection;

import com.mobiquity.flicker.injection.network.NetworkModule;
import com.mobiquity.flicker.photosearch.model.repo.CloudPhotosRepository;
import com.mobiquity.flicker.photosearch.model.repo.PhotosRepository;
import com.mobiquity.flicker.photosearch.model.services.FlickrPhotoSearchService;
import com.mobiquity.flicker.photosearch.presenter.PhotosRepo;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

@Module(includes = {NetworkModule.class})
public class AppModule {

    @Provides
    public FlickrPhotoSearchService provideFlickrPhotoSearchService(Retrofit retrofit) {
        return retrofit.create(FlickrPhotoSearchService.class);
    }

    @Provides @Named("repo_impl")
    public PhotosRepo providePhotoRepoImplementation(@Named("cloud_repo") PhotosRepo cloudRepo) {
        return new PhotosRepository(cloudRepo);
    }

    @Provides @Named("cloud_repo")
    public PhotosRepo providePhotoRepoCloudImplementation(FlickrPhotoSearchService service) {
        return new CloudPhotosRepository(service);
    }

    @Provides
    public CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }

    @Provides @Named("main_scheduler")
    public Scheduler provideMainScheduler() {
        return AndroidSchedulers.mainThread();
    }

}
