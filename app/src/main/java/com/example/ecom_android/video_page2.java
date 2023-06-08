package com.example.ecom_android;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.IOException;

public class video_page2 extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_page);

        VideoView videoView = (VideoView) findViewById(R.id.videoView1);

        //Creating MediaController
        MediaController mediaController = new MediaController(this);
        //mediaController.setAnchorView(videoView);


        MediaPlayer mp = new MediaPlayer();

        try
        {
            mp.setDataSource(Environment.getExternalStorageDirectory().getPath() + "/Movies/1.mp4");
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            mp.prepare();
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        mp.start();

        //specify the location of media file
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/Movies/1.mp4");

        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.video_page, menu);
        return true;
    }*/
}
