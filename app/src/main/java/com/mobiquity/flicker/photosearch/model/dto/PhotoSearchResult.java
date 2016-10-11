package com.mobiquity.flicker.photosearch.model.dto;

import android.support.annotation.VisibleForTesting;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public class PhotoSearchResult {

    public static class PhotosModel {
        private int page;
        private int pagesCount;
        @SerializedName("perpage")
        private int perPage;
        private int total;
        @SerializedName("photo")
        private List<Photo> photos;

        public int getPage() {
            return page;
        }

        @VisibleForTesting
        public void setPhotos(List<Photo> photos) {
            this.photos = photos;
        }

        public List<Photo> getPhotos() {
            return photos;
        }
    }

    @SerializedName("photos")
    private PhotosModel photosModel;
    private String stat;

    @VisibleForTesting
    public void setPhotosModel(PhotosModel photosModel) {
        this.photosModel = photosModel;
    }

    public PhotosModel getPhotosModel() {
        return photosModel;
    }

    public boolean isSuccess() {
        return "ok".equals(stat);
    }

}
