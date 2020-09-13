package com.jarvanmo.demo;

import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.jarvanmo.exoplayerview.extension.MultiQualitySelectorAdapter;
import com.jarvanmo.exoplayerview.media.ExoMediaSource;
import com.jarvanmo.exoplayerview.media.SimpleMediaSource;
import com.jarvanmo.exoplayerview.media.SimpleQuality;
import com.jarvanmo.exoplayerview.ui.ExoVideoPlaybackControlView;
import com.jarvanmo.exoplayerview.ui.ExoVideoView;
import okhttp3.OkHttpClient;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static com.jarvanmo.exoplayerview.orientation.OnOrientationChangedListener.SENSOR_LANDSCAPE;
import static com.jarvanmo.exoplayerview.orientation.OnOrientationChangedListener.SENSOR_PORTRAIT;

import com.jarvanmo.demo.controllers.ChildVideoCtrl;
import com.jarvanmo.demo.models.ChildVideoModel;
import com.jarvanmo.demo.utils.OkhttpService;

public class SimpleVideoViewActivity extends AppCompatActivity {

    private ExoVideoView videoView;
    private String[] urls;
    private int m_Index = 0;
    private  OkHttpClient okHttpClient = new OkHttpClient();
    private ChildVideoCtrl childVideoCtrl = new ChildVideoCtrl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_video_view);
        initVideoView();
        getVideoList();
    }

    private void getVideoList() {
        childVideoCtrl.applyVisitor(this.okHttpClient, new OkhttpService.OnResponseListener() {
            @Override
            public void onSuccess(String result) {
                java.lang.reflect.Type type = new TypeToken<ChildVideoModel>() {}.getType();
                final ChildVideoModel childVideos = new Gson().fromJson(result, type);
                Log.d("exo", "AFD_FSDK_InitialFaceEngine = " + childVideos.getUrls().length);
                (SimpleVideoViewActivity.this).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        urls = childVideos.getUrls();
                        PlayVideoList();
                    }
                });
            }
            @Override
            public void onFailure(IOException error) {
                (SimpleVideoViewActivity.this).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SimpleVideoViewActivity.this, "添加失败，网络异常", Toast.LENGTH_SHORT).show();
                        Log.e("exo", "Get Video List Failed");
                    }
                });
            }
        });
    }

    private void initVideoView() {
        videoView = findViewById(R.id.videoView);
        videoView.setPortrait(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT);
        videoView.setBackListener((view, isPortrait) -> {
            if (isPortrait) {
                finish();
            }
            return false;
        });

        videoView.SetOnPlayerStateChanged((playWhenReady, playbackState)->{
            Log.e("exo", "playbackState" + playbackState);
            if(playbackState == 4)
            {
                onKeyUP();
            }
        });
        // videoView.addListener(new Player.DefaultEventListener() {
        //     @Override
        //     public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        //       if (playWhenReady && playbackState == Player.STATE_READY) {
        //         // media actually playing
        //       } else if (playWhenReady) {
        //         // might be idle (plays after prepare()), 
        //         // buffering (plays when data available) 
        //         // or ended (plays when seek away from end)
        //       } else {
        //         // player paused in any state
        //       }
        //     }
        // });

        videoView.setOrientationListener(orientation -> {
            if (orientation == SENSOR_PORTRAIT) {
                //changeToPortrait();
            } else if (orientation == SENSOR_LANDSCAPE) {
                //changeToLandscape();
            }
        });
    }

    private void PlayVideoList()
    {
        String url = urls[m_Index];
        Log.e("exo", url);

        SimpleMediaSource mediaSource = new SimpleMediaSource(url);
        mediaSource.setDisplayName(url);
        videoView.play(mediaSource, true);
    }

    private void onKeyDwon()
    {
        m_Index--;
        if (m_Index < 0)
        {
            m_Index = urls.length - 1;
        }
        videoView.releasePlayer();
        PlayVideoList();
        //initVideoView();
    }

    private void onKeyUP()
    {
        m_Index++;
        if (!(m_Index < urls.length))
        {
            m_Index = 0;
        }
        videoView.releasePlayer();
        PlayVideoList();
        //initVideoView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT > 23) {
            videoView.resume();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if ((Build.VERSION.SDK_INT <= 23)) {
            videoView.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT <= 23) {
            videoView.pause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Build.VERSION.SDK_INT > 23) {
            videoView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.releasePlayer();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //return videoView.onKeyDown(keyCode, event);
        }

        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            onKeyUP();
        } else if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            onKeyDwon();
        }
        return super.onKeyDown(keyCode, event);
    }
}
