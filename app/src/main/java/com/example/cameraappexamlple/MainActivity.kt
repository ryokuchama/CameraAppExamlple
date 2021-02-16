package com.example.cameraappexamlple

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
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
import java.lang.Exception
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    var correct: ImageView? = null
    var comparative: ImageView? = null

    var mode: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        correct= findViewById(R.id.src)
        comparative = findViewById(R.id.target)
        val correctButton: Button = findViewById(R.id.correctImageButton)
        val comparativeButton: Button = findViewById(R.id.comparativeImageButton)
        val find: Button = findViewById(R.id.findDifferences)

        correct.setImageResource(R.drawable.noimage)
        comparative.setImageResource(R.drawable.noimage)

        correctButton.setOnClickListener{
            val intent: Intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            mode = 0

            startActivityForResult(intent, READ_REQUEST_CODE)
        }

        comparativeButton.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            mode = 1

            startActivityForResult(intent, READ_REQUEST_CODE)
        }

        find.setOnClickListener {

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            READ_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && requestCode == READ_REQUEST_CODE) {

                    when {
                        mode == 0 -> correct
                    }
                }
            }
        }
    }

    // クラス内に生成されるシングルトン
    // シングルトン -> 状態を持たないこと、抽象クラスまたはインターフェースを実装していること
    companion object {
        const val READ_REQUEST_CODE: Int = 2
    }
}