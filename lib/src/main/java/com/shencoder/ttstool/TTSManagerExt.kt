package com.shencoder.ttstool

import android.content.Context
import com.iflytek.cloud.ErrorCode
import com.iflytek.cloud.InitListener

/**
 * TTSManager 扩展方法
 *
 * @author  ShenBen
 * @date    2021/05/17 15:56
 * @email   714081644@qq.com
 */

/**
 * 必须在主进程初始化
 *
 * @param context
 * @param isFemaleVoice 是否是女声，true:女声，false:男声
 * @param listener 初始化结果回调，成功：[ErrorCode.SUCCESS]
 */
@JvmOverloads
fun initTTS(
    context: Context,
    isFemaleVoice: Boolean = true,
    listener: InitListener? = null
) {
    TTSManager.getInstance().init(context, isFemaleVoice, listener)
}

fun setSpeechStatusListener(listener: TTSManager.SpeechStatusListener?) {
    TTSManager.getInstance().setSpeechStatusListener(listener)
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



