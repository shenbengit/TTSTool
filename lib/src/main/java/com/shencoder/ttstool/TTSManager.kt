package com.shencoder.ttstool

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.iflytek.cloud.*
import com.iflytek.cloud.util.ResourceUtil
import java.lang.StringBuilder

/**
 * TTS工具
 *
 * @author  ShenBen
 * @date    2021/05/17 15:44
 * @email   714081644@qq.com
 */
class TTSManager private constructor() {

    private object SingleHolder {
        val instance = TTSManager()
    }

    companion object {
        private const val TAG = "TTSTool"
        fun getInstance() = SingleHolder.instance
    }

    private var mSpeechSynthesizer: SpeechSynthesizer? = null
    private var isInitSuccess = false
    private var mSpeechStatusListener: SpeechStatusListener? = null

    private val mSynthesizerListener = object : SynthesizerListener {
        override fun onBufferProgress(p0: Int, p1: Int, p2: Int, p3: String?) {

        }

        override fun onSpeakBegin() {
            mSpeechStatusListener?.onBegin()
        }

        override fun onSpeakProgress(p0: Int, p1: Int, p2: Int) {

        }

        override fun onEvent(p0: Int, p1: Int, p2: Int, p3: Bundle?) {

        }

        override fun onSpeakPaused() {
            mSpeechStatusListener?.onPaused()
        }

        override fun onSpeakResumed() {
            mSpeechStatusListener?.onResumed()
        }

        override fun onCompleted(p0: SpeechError?) {
            mSpeechStatusListener?.onCompleted(p0)
        }

    }

    /**
     * 必须在主进程初始化
     */
    fun init(context: Context, appId: String) {
        Setting.setLogLevel(Setting.LOG_LEVEL.normal)
        SpeechUtility.createUtility(
            context,
            "${SpeechConstant.APPID}=${appId},${SpeechConstant.ENGINE_MODE}=${SpeechConstant.MODE_AUTO}"
        )
        mSpeechSynthesizer = SpeechSynthesizer.createSynthesizer(context) { code ->
            isInitSuccess = code == ErrorCode.SUCCESS
            if (isInitSuccess.not()) {
                Log.w(TAG, "科大讯飞语音合成初始化识别，code: $code")
            }
        }.apply {
            setParameter(
                SpeechConstant.ENGINE_TYPE,
                SpeechConstant.TYPE_LOCAL
            )
            setParameter(SpeechConstant.SPEED, "50")
            setParameter(SpeechConstant.VOLUME, "100")
            setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true")
            setParameter(SpeechConstant.STREAM_TYPE, "3")
            // 设置发音人
            setParameter(SpeechConstant.VOICE_NAME, "xiaoyan")
            setParameter(ResourceUtil.TTS_RES_PATH, getResPath(context))
        }
    }

    /**
     * 可连续播放
     */
    fun startSpeaking(text: String) {
        mSpeechSynthesizer?.startSpeaking(text, mSynthesizerListener)
    }

    fun stopSpeaking() {
        mSpeechSynthesizer?.stopSpeaking()
    }

    fun isSpeaking() = mSpeechSynthesizer?.isSpeaking ?: false

    fun destroy() {
        stopSpeaking()
        mSpeechSynthesizer?.destroy()
    }

    fun setSpeechStatusListener(listener: SpeechStatusListener) {
        mSpeechStatusListener = listener
    }

    /**
     * 发音文件资源
     */
    private fun getResPath(context: Context): String {
        val builder = StringBuilder()
        // 合成通用资源
        builder.append(
            ResourceUtil.generateResourcePath(
                context,
                ResourceUtil.RESOURCE_TYPE.assets,
                "tts/common.jet"
            )
        )
        builder.append(";")
        // 发音人资源
        builder.append(
            ResourceUtil.generateResourcePath(
                context,
                ResourceUtil.RESOURCE_TYPE.assets,
                "tts/xiaoyan.jet"
            )
        )
        return builder.toString()
    }

    /**
     * 语音合成状态回调
     */
    interface SpeechStatusListener {
        /**
         * 语音合成开始
         */
        fun onBegin()

        fun onPaused() {

        }

        fun onResumed() {

        }

        /**
         * 语音合成结束
         * @param error 为null时，则为成功，不为null，则是失败
         */
        fun onCompleted(error: SpeechError?)
    }
}