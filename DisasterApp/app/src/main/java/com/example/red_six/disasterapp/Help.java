package com.example.red_six.disasterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeInitializationResult;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Fred on 10/09/2016.
 */
public class Help extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    public static final String API_KEY = "AIzaSyBfZg-JjeimylfejhXWiWD3caDx-B6Th84";
    public static final String VIDEO_ID = "XZmGGAbHqa0";

    public final static String EXTRA_MESSAGE = "com.example.red_six.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        YouTubePlayer.OnInitializedListener onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        //INITIALIZING YOUTUBE PLAYER VIEW
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_help_player);
        youTubePlayerView.initialize(API_KEY,this);

    }

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    public void onGoSeverity(View view) {
        CharSequence text = "Go to Severity!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(Help.this, text, duration);
        toast.show();

        Intent intent = new Intent(this, Severity.class);
        String message = "message from Help";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    /* open up the "popup" to add another address*/
    public void onEnterAddress (View view) {
        Intent intent = new Intent(this, EnterAddress.class);
        String message = "message from Help";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    public void onGoHome (View view) {
        Intent intent = new Intent(this, home.class);
        String message = "message from Help";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);

        if(!b){
            youTubePlayer.cueVideo(VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this,"Failed to initialize", Toast.LENGTH_SHORT).show();
    }
}
