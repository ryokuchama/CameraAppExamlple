package com.example.cameraappexamlple

import android.content.Context
import android.content.Intent
import android.graphics.ImageFormat
import android.graphics.SurfaceTexture
import android.hardware.camera2.CameraManager
import android.media.ImageReader
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.SurfaceHolder.Callback
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val correct: ImageView = findViewById(R.id.src)
        val comparative: ImageView = findViewById(R.id.target)
        val correctButton: Button = findViewById(R.id.correctImageButton)
        val comparativeButton: Button = findViewById(R.id.comparativeImageButton)
        val find: Button = findViewById(R.id.findDifferences)

        correct.setImageResource(R.drawable.noimage)
        comparative.setImageResource(R.drawable.noimage)

        correctButton.setOnClickListener{
            val intent: Intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"
            }

            startActivityForResult(intent, 42)
        }

//        companion object {
//            private const val READ_REQUEST_CODE: Int = 42
//        }

        comparativeButton.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"
            }

            startActivityForResult(intent, 42)
        }

        find.setOnClickListener {

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}