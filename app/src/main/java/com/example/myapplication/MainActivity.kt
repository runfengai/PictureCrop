package com.example.myapplication

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.yalantis.ucrop.UCrop
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //本地路径dir
        val fromFile = Uri.fromFile(File("/sdcard/a.jpg"))
        val destinationUri = Uri.fromFile(File("/sdcard/b.jpg"))
        fab.setOnClickListener {
            UCrop.of(fromFile, destinationUri).withAspectRatio(16f, 9f).withMaxResultSize(200, 400)
                .start(this)
        }
    }

}