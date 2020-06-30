package com.example.media

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/*
* This activity is Audio Player activity.
* There are three buttons: start play, pause play, stop play.
* Each buttons are being enabled and disabled according to the situation.
* It toasts "Failed Loading" message at the beginning if the file is not loaded.
 */

class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the media player
        try {
            mediaPlayer = MediaPlayer.create(this, R.raw.friendlyday)
            mediaPlayer.prepare()
            mediaPlayer.prepareAsync()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this,"Failed Loading",Toast.LENGTH_SHORT).show()
        }

        // Buttons handling playing audio.
        button_start.setOnClickListener {
            mediaPlayer.start()
            Toast.makeText(this,"Start Audio",Toast.LENGTH_SHORT).show()
            button_pause.isEnabled = true
            button_start.isEnabled = false
            button_stop.isEnabled = true
        }
        button_pause.setOnClickListener {
            mediaPlayer.pause()
            Toast.makeText(this,"Pause Audio",Toast.LENGTH_SHORT).show()
            button_pause.isEnabled = false
            button_start.isEnabled = true
            button_stop.isEnabled = true
        }
        button_stop.setOnClickListener {
            mediaPlayer.stop()
            Toast.makeText(this,"Stop Audio",Toast.LENGTH_SHORT).show()
            button_pause.isEnabled = false
            button_start.isEnabled = true
            button_stop.isEnabled = false
        }
    }
}