package com.lyecdevelopers.exovideoplayer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.lyecdevelopers.exovideoplayer.R;
import com.lyecdevelopers.exovideoplayer.models.MediaObject;

import java.util.List;


public class VideoPlayerRecyclerAdapter extends RecyclerView.Adapter<VideoPlayerRecyclerAdapter.VideoPlayerViewHolder> {

    private final List<MediaObject> mMediaObjects;
    private final RequestManager mRequestManager;

    public VideoPlayerRecyclerAdapter(List<MediaObject> mediaObjects, RequestManager requestManager) {
        mMediaObjects = mediaObjects;
        mRequestManager = requestManager;
    }

    @NonNull
    @Override
    public VideoPlayerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new VideoPlayerViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_video_list_item, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull VideoPlayerViewHolder viewHolder, int i) {
        ((VideoPlayerViewHolder) viewHolder).onBind(mMediaObjects.get(i), mRequestManager);
    }

    @Override
    public int getItemCount() {
        return mMediaObjects == null ? 0 : mMediaObjects.size();
    }

    public class VideoPlayerViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout media_container;
        private final View parent;
        protected ImageView thumbnail;
        protected ProgressBar progressBar;
        protected RequestManager requestManager;
        private TextView title;

        public VideoPlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView;
            media_container = itemView.findViewById(R.id.media_container);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            //title = itemView.findViewById(R.id.title);
            //progressBar = itemView.findViewById(R.id.progressBar);

        }

        public void onBind(MediaObject mediaObject, RequestManager requestManager) {
            this.requestManager = requestManager;
            parent.setTag(this);
            //title.setText(mediaObject.title);
            this.requestManager
                    .load(mediaObject.thumbnail)
                    .into(thumbnail);
        }
    }
}
