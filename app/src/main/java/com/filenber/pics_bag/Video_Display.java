package com.filenber.pics_bag;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.filenber.pics_bag.R;
import com.filenber.pics_bag.YoutubeConfig;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class Video_Display extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

  private static final int RECOVERY_REQUEST = 1;
  private YouTubePlayerView youTubeView;
  Intent intent = getIntent();
  String videoId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display__display);

    youTubeView = (YouTubePlayerView) findViewById(R.id.video_image);
    youTubeView.initialize(YoutubeConfig.GET_API(), this);
    videoId =getIntent().getStringExtra("videoId").toString();
    //Toast.makeText(this,  Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
    if (!wasRestored) {
      player.setFullscreen(true);
      // player.cueVideo("PLxZHs0Jed3jQwV9NAqm8qhkbYbNRKvrYo");
      player.cueVideo(videoId);

    }
  }

  @Override
  public void onInitializationFailure(Provider provider, YouTubeInitializationResult errorReason) {
    if (errorReason.isUserRecoverableError()) {
      errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
    } else {
      String error =  errorReason.toString();
      Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == RECOVERY_REQUEST) {
      // Retry initialization if user performed a recovery action
      getYouTubePlayerProvider().initialize(YoutubeConfig.GET_API(), this);
    }
  }

  protected Provider getYouTubePlayerProvider() {
    return youTubeView;
  }
}
