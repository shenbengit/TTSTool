package com.shencoder.ttstooldemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.shencoder.ttstool.destroyTTS
import com.shencoder.ttstool.initTTS


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTTS(this, true) { code -> println("初始化结果:$code") }

        findViewById<Button>(R.id.btnTTS).setOnClickListener {
            startActivity(Intent(this, JavaActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyTTS()
    }
}