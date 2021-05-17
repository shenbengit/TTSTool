package com.shencoder.ttstool

import android.content.Context

/**
 * TTSManager 扩展方法
 *
 * @author  ShenBen
 * @date    2021/05/17 15:56
 * @email   714081644@qq.com
 */

/**
 * 必须在主进程初始化
 */
fun initTTS(context: Context, appId: String) {
    TTSManager.getInstance().init(context, appId)
}

fun startSpeaking(text: String) {
    TTSManager.getInstance().startSpeaking(text)
}

fun stopSpeaking() {
    TTSManager.getInstance().stopSpeaking()
}

fun isSpeaking() = TTSManager.getInstance().isSpeaking()

/**
 * 销毁
 */
fun destroyTTS() {
    TTSManager.getInstance().destroy()
}



