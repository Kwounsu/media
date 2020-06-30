package com.example.media

import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.ACTION_IMAGE_CAPTURE_SECURE
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_camera.*

class CameraActivity : AppCompatActivity() {
    private val CAMERA_REQUEST = 1888
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        // Take a picture
        button_camera.setOnClickListener {
            val cameraIntent = Intent(ACTION_IMAGE_CAPTURE_SECURE)
            startActivityForResult(cameraIntent, CAMERA_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Change ImageView to the photo took by camera.
        if(resultCode != RESULT_CANCELED && requestCode == CAMERA_REQUEST) {
            val photo = data?.extras!!["data"] as Bitmap
            imageView?.setImageBitmap(photo)
        } else {
            imageView?.setImageResource(R.mipmap.ic_launcher)
        }
    }
}