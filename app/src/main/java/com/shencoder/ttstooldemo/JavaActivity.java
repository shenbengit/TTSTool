package com.shencoder.ttstooldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iflytek.cloud.InitListener;
import com.shencoder.ttstool.TTSManager;
import com.shencoder.ttstool.TTSManagerExtKt;

public class JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        findViewById(R.id.btnTTS).setOnClickListener(v -> TTSManagerExtKt.startSpeaking("啦啦啦德玛西亚"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}