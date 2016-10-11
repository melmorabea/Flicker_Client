package com.mobiquity.flicker.photosearch.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobiquity.flicker.R;
import com.mobiquity.flicker.core.ui.PicassoHelper;
import com.mobiquity.flicker.core.ui.adapter.RecyclerViewClickListener;
import com.mobiquity.flicker.photosearch.model.dto.Photo;
import com.mobiquity.flicker.photosearch.view.PhotoSearchAdapter.PhotoSearchViewHolder;

import java.util.List;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public class PhotoSearchAdapter extends RecyclerView.Adapter<PhotoSearchViewHolder> {

    private List<Photo> photos;
    private RecyclerViewClickListener<Photo> listener;

    public PhotoSearchAdapter(List<Photo> photos, RecyclerViewClickListener<Photo> listener) {
        this.photos = photos;
        this.listener = listener;
    }

    @Override
    public PhotoSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_search_list_item, parent, false);
        return new PhotoSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoSearchViewHolder holder, int position) {
        holder.bind(photos.get(position));
    }

    @Override
    public int getItemCount() {
        return photos == null ? 0 : photos.size();
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        listener = null;
    }

    public void setListener(RecyclerViewClickListener<Photo> listener) {
        this.listener = listener;
    }

    public void addItems(List<Photo> newPhotos) {
        int startPos = photos.size();
        photos.addAll(newPhotos);
        notifyItemRangeInserted(startPos, newPhotos.size());
    }

    class PhotoSearchViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView img;

        PhotoSearchViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.photo_search_list_item_title);
            img = (ImageView) itemView.findViewById(R.id.photo_search_list_item_img);
        }

        void bind(final Photo photo) {
            title.setText(photo.getTitle());
            PicassoHelper.loadImage(itemView.getContext(), photo.getUrl(), img);
            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClicked(v, photo);
                }
            });
        }

    }

}
