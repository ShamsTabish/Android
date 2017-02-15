package com.senses.market.videodemo;

import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class VideoList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        //final VideoView videoPlayer=(VideoView)findViewById(R.id.video);
        final WebView webV = (WebView) findViewById(R.id.webVideo);
        //videoPlayer.setVideoURI(Uri.parse("android.resource://com.senses.market.videodemo/"+R.raw.sample));
        //videoPlayer.requestFocus();
        //videoPlayer.start();
        webV.getSettings().setJavaScriptEnabled(true);
        //webV.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        webV.getSettings().setAllowContentAccess(true);
        webV.getSettings().setAllowFileAccessFromFileURLs(true);
        webV.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webV.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        webV.getSettings().en
//        webV.getSettings().setAllowFileAccess(true);
        webV.getSettings().setAllowFileAccess(true);

        String url ="file:///android_asset/Algebra.swf";
//        String url ="android.resource://com.senses.market.videodemo/"+R.raw.algebra;

        //webV.loadData(loadHTMLFile(), "text/html", "UTF8");
        //webV.loadData(loadHTMLFile(), "application/x-shockwave-flash", "binary");
//        webV.loadUrl(Uri.parse(url).toString());
        webV.loadUrl(url);
    }

    private String loadHTMLFile() {
        AssetManager manager = getAssets();
        //webV.loadUrl(manager.);
        String contents = "Sorry, Could not Load the Animation..<br/> :(";
        try {
            InputStream reader = manager.open(/*"test.html"*/"Algebra.swf");
            byte[] data = new byte[reader.available()];
            reader.read(data);
            contents = new String(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contents;
    }
}
