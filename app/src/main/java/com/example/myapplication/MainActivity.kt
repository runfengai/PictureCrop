package com.example.myapplication

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.yalantis.ucrop.UCrop
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

class MainActivity : AppCompatActivity() {

    val path = Environment.getExternalStorageDirectory().absolutePath + File.separator + "a.jpg"
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val root = getCacheDir().getAbsoluteFile()
        if (!root.exists()) {
            root.mkdir()
        }
        val pp = root.absolutePath + File.separator + "a.jpg"
        val qq = root.absolutePath + File.separator + "b.jpg"
        //本地路径dir
        btn.setOnClickListener {
            //
            val file = File("/sdcard/a.jpg")
            val fromFile = Uri.fromFile(file)
            if (file.exists()) {
                val bytes = readLocalFile(file)
                open(bytes)

                val ex = File(path).exists()
                Toast.makeText(this, "$ex", Toast.LENGTH_LONG).show()
            }


        }

        fab.setOnClickListener {
            val fromFile = Uri.fromFile(File(pp))
            val destinationUri = Uri.fromFile(File(qq))
            UCrop.of(fromFile, destinationUri).withAspectRatio(16f, 9f).withMaxResultSize(200, 400)
                .start(this)
        }
    }


    @Throws(IOException::class)
    private fun readLocalFile(f: File): ByteArray {
        val inputStream: InputStream = FileInputStream(f)
        val data = toByteArray(inputStream)
        inputStream.close()
        return data
    }

    @Throws(IOException::class)
    private fun toByteArray(`in`: InputStream): ByteArray {
        val out = ByteArrayOutputStream()
        val buffer = ByteArray(1024 * 4)
        var n = 0
        while (`in`.read(buffer).also { n = it } != -1) {
            out.write(buffer, 0, n)
        }
        return out.toByteArray()
    }


    fun open(b: ByteArray) {
        val f = File(path)
        if (!f.exists()) {
            val p = f.parentFile
            if (!p.exists()) {
                p.mkdirs()
            }

            f.createNewFile()
        }

        val fos = FileOutputStream(f)
        fos.write(b, 0, b.size)
        fos.flush()
        fos.close()

    }


}