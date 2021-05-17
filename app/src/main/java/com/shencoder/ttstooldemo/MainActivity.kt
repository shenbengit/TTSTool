package com.shencoder.ttstooldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.shencoder.ttstool.destroyTTS
import com.shencoder.ttstool.initTTS
import com.shencoder.ttstool.startSpeaking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTTS(this, "97939e0c")
        findViewById<Button>(R.id.btnTTS).setOnClickListener {
            startSpeaking("播报测试")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyTTS()
    }
}