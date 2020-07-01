package com.example.media.video

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.media.R


/*
* Video player activity.
 */

class VideoActivity : AppCompatActivity() {
    lateinit var videoview : VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        Log.i("videoView","onCreate")

        videoview = findViewById(R.id.videoView1)

        val controller = MediaController(this)
        controller.setMediaPlayer(videoview)
        videoview.setMediaController(controller)
    }

    private fun initPlayer() {
        val uriPath = "android.resource://" + packageName + "/" + R.raw.movie
        val uri = Uri.parse(uriPath)
        videoview.setVideoURI(uri)
        videoview.start()
    }

    private fun releasePlayer(){
        Log.i("videoView","releasePlayer")
        videoview.stopPlayback()
    }

    override fun onStop() {
        Log.i("videoView","onStop")
        super.onStop()
        releasePlayer()
    }

    override fun onStart() {
        Log.i("videoView","onStart")
        super.onStart()
        initPlayer()
    }

    override fun onPause() {
        Log.i("videoView","onPause")
        super.onPause()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            videoview.pause()
        }
    }
}