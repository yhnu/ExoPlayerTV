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

import java.util.ArrayList;
import java.util.List;

import static com.jarvanmo.exoplayerview.orientation.OnOrientationChangedListener.SENSOR_LANDSCAPE;
import static com.jarvanmo.exoplayerview.orientation.OnOrientationChangedListener.SENSOR_PORTRAIT;

public class SimpleVideoViewActivity extends AppCompatActivity {

    private ExoVideoView videoView;

    private final String[] urls = new String[]{
            "http://iptv.pdsu.edu.cn/hls/zjhd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/hunanhd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/jshd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv5hd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/dfhd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/ahhd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv8hd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv1hd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv2hd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv3hd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv4hd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv5hd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv5phd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv6hd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv7hd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv8hd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv9hd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv10hd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv12hd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv14hd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cgtnhd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/chchd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/btv1hd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/btv2hd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/btv4hd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/btv9hd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/btv11hd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/hunanhd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/zjhd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/jshd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/dfhd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/ahhd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/hljhd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/lnhd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/szhd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/gdhd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/tjhd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/hbhd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/sdhd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cqhd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/docuchina.m3u8",
    "http://iptv.pdsu.edu.cn/hls/schd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/gedocu.m3u8",
    "http://iptv.pdsu.edu.cn/hls/dnhd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/hebhd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/jxhd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cetv1hd.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv1.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv2.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv3.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv4.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv5.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv6.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv7.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv8.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv9.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv10.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv11.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv12.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv13.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv14.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv15.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cctv16.m3u8",
    "http://iptv.pdsu.edu.cn/hls/dyjctv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/fyjctv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/fyyytv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/fyzqtv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/gfjstv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/hjjctv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/sjdltv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/btv1.m3u8",
    "http://iptv.pdsu.edu.cn/hls/btv2.m3u8",
    "http://iptv.pdsu.edu.cn/hls/btv3.m3u8",
    "http://iptv.pdsu.edu.cn/hls/btv4.m3u8",
    "http://iptv.pdsu.edu.cn/hls/btv5.m3u8",
    "http://iptv.pdsu.edu.cn/hls/btv7.m3u8",
    "http://iptv.pdsu.edu.cn/hls/btv8.m3u8",
    "http://iptv.pdsu.edu.cn/hls/btv9.m3u8",
    "http://iptv.pdsu.edu.cn/hls/btv10.m3u8",
    "http://iptv.pdsu.edu.cn/hls/zjtv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/hunantv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/jstv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/dftv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/sztv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/ahtv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/hntv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/sxtv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/jltv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/gdtv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/sdtv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/hbtv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/gxtv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/hebtv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/xztv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/nmtv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/qhtv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/sctv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/tjtv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/sxrtv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/lntv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/xmtv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/xjtv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/hljtv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/yntv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/jxtv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/dntv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/gztv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/nxtv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/gstv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cqtv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/bttv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/lytv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cetv1.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cetv3.m3u8",
    "http://iptv.pdsu.edu.cn/hls/cetv4.m3u8",
    "http://iptv.pdsu.edu.cn/hls/chcatv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/chctv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/jykttv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/sdetv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/fhzw.m3u8",
    "http://iptv.pdsu.edu.cn/hls/fhzx.m3u8",
    "http://iptv.pdsu.edu.cn/hls/fhdy.m3u8",
    "http://iptv.pdsu.edu.cn/hls/discovery.m3u8",
    "http://iptv.pdsu.edu.cn/hls/natlgeo.m3u8",
    "http://iptv.pdsu.edu.cn/hls/startv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/channelv.m3u8",
    "http://iptv.pdsu.edu.cn/hls/starsports.m3u8",
    };
    private int m_Index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_video_view);
        initVideoView();
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

        videoView.setOrientationListener(orientation -> {
            if (orientation == SENSOR_PORTRAIT) {
                //changeToPortrait();
            } else if (orientation == SENSOR_LANDSCAPE) {
                //changeToLandscape();
            }
        });

//        videoView.setGestureEnabled(false);
//
//
//        SimpleMediaSource mediaSource = new SimpleMediaSource("http://flv2.bn.netease.com/videolib3/1604/28/fVobI0704/SD/fVobI0704-mobile.mp4");
//        mediaSource.setDisplayName("Apple HLS");

        //SimpleMediaSource mediaSource = new SimpleMediaSource("http://vfx.mtime.cn/Video/2019/03/12/mp4/190312083533415853.mp4");
        SimpleMediaSource mediaSource = new SimpleMediaSource(urls[m_Index]);
        mediaSource.setDisplayName(urls[m_Index]);
        //demo only,not real multi quality, urls are the same actually
//        List<ExoMediaSource.Quality> qualities = new ArrayList<>();
//        ExoMediaSource.Quality quality;
//        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.YELLOW);
//        SpannableString spannableString = new SpannableString("1080p");
//        spannableString.setSpan(colorSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        quality = new SimpleQuality(spannableString, mediaSource.uri());
//        qualities.add(quality);
//
//        spannableString = new SpannableString("720p");
//        colorSpan = new ForegroundColorSpan(Color.LTGRAY);
//        spannableString.setSpan(colorSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        quality = new SimpleQuality(spannableString, mediaSource.uri());
//        qualities.add(quality);
//
//        mediaSource.setQualities(qualities);
//        videoView.changeWidgetVisibility(R.id.exo_player_controller_back,View.INVISIBLE);
//        videoView.setMultiQualitySelectorNavigator(new MultiQualitySelectorAdapter.MultiQualitySelectorNavigator() {
//            @Override
//            public boolean onQualitySelected(ExoMediaSource.Quality quality) {
//                quality.setUri(Uri.parse("https://media.w3.org/2010/05/sintel/trailer.mp4"));
//                return false;
//            }
//        });
        videoView.play(mediaSource, true);

    }

    private void onKeyUP()
    {
        m_Index++;
        if (!(m_Index < urls.length))
        {
            m_Index = 0;
        }
        videoView.releasePlayer();
        initVideoView();
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
            return videoView.onKeyDown(keyCode, event);
        }

        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            onKeyUP();
        }
        return super.onKeyDown(keyCode, event);
    }
}
