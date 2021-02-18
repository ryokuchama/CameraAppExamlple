package com.example.cameraappexamlple

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
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
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.lang.Exception
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    // lateinit -> delay initialization until onCreate is called
    lateinit var correct: ImageView
    lateinit var comparative: ImageView

    var mode: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        correct = findViewById(R.id.src)
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
                        mode == 0 -> correct.setImageURI(data?.data)
                        mode == 1 -> comparative.setImageURI(data?.data)
                    }
                }
            }
        }
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
                this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when(requestCode) {
            1000 -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(applicationContext, "Request is not permitted", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(applicationContext, "Request permitted", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    // クラス内に生成されるシングルトン
    // シングルトン -> 状態を持たないこと、抽象クラスまたはインターフェースを実装していること
    companion object {
        const val READ_REQUEST_CODE: Int = 2
    }
}