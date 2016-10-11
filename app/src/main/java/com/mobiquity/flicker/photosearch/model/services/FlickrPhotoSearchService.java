package com.mobiquity.flicker.photosearch.model.services;

import com.mobiquity.flicker.photosearch.model.dto.PhotoSearchResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public interface FlickrPhotoSearchService {


    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1")
    Observable<PhotoSearchResult> searchForPhoto(@Query("text") String queryText, @Query("page") int page,
                                                 @Query("api_key") String apiKey);

}
