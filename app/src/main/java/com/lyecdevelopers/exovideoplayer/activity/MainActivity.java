package com.lyecdevelopers.exovideoplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.lyecdevelopers.exovideoplayer.R;
import com.lyecdevelopers.exovideoplayer.adapter.VideoPlayerRecyclerAdapter;
import com.lyecdevelopers.exovideoplayer.custom.VerticalSpacingItemDecorator;
import com.lyecdevelopers.exovideoplayer.models.MediaObject;
import com.lyecdevelopers.exovideoplayer.utils.RecyclerTouchListener;
import com.lyecdevelopers.exovideoplayer.utils.Resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private VideoPlayerRecyclerAdapter mVideoPlayerRecyclerAdapter;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_videos);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);
        setAdapter();

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                MediaObject object = getMediaObjects().get(position);

                // intent 
                Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                intent.putExtra(PlayerActivity.VIDEO_TYPE, PlayerActivity.SIMPLE_VIDEO);
                intent.putExtra(PlayerActivity.VIDEO_URI, object.mediaUrl);
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    public List<MediaObject> getMediaObjects() {
        return new ArrayList<>(Arrays.asList(Resources.MEDIA_OBJECTS));
    }

    public void setAdapter() {
        if (mVideoPlayerRecyclerAdapter == null) {
            mVideoPlayerRecyclerAdapter = new VideoPlayerRecyclerAdapter(getMediaObjects(), initGlide());
            mRecyclerView.setAdapter(mVideoPlayerRecyclerAdapter);
        } else if (mRecyclerView.getAdapter() == null) {
            mRecyclerView.setAdapter(mVideoPlayerRecyclerAdapter);
        } else {
            mVideoPlayerRecyclerAdapter.notifyDataSetChanged();
        }
    }

    private RequestManager initGlide() {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.white_background)
                .error(R.drawable.white_background);

        return Glide.with(this)
                .setDefaultRequestOptions(options);
    }
}