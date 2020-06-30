package com.example.media

import android.widget.MediaController
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_video.*

/*
* Video player activity.
* The video file is not exist currently.
* Could not test it yet.
 */

class VideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        //Creating MediaController
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView);

        //specify the location of media file
        val uri: Uri = Uri.parse(
            Environment.getExternalStorageDirectory().path.toString() + "/media/1.mp4"
        )

        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

    }


}