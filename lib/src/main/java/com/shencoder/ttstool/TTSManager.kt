package com.shencoder.ttstool

import android.content.Context
import android.os.Bundle
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

        /**
         * 男声
         */
        private const val VOICE_MALE = "xiaofeng"

        /**
         * 女声
         */
        private const val VOICE_FEMALE = "xiaoyan"

        @JvmStatic
        fun getInstance() = SingleHolder.instance
    }

    private lateinit var mSpeechSynthesizer: SpeechSynthesizer

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
     * @param context
     * @param isFemaleVoice 是否是女声，true:女声，false:男声
     * @param listener 初始化结果回调，成功：[ErrorCode.SUCCESS]
     */
    @JvmOverloads
    fun init(
        context: Context,
        isFemaleVoice: Boolean = true,
        listener: InitListener? = null
    ) {
        Setting.setLogLevel(Setting.LOG_LEVEL.none)
        SpeechUtility.createUtility(
            context,
            "${SpeechConstant.APPID}=59e80f31,${SpeechConstant.ENGINE_MODE}=${SpeechConstant.MODE_AUTO}"
        )
        mSpeechSynthesizer = SpeechSynthesizer.createSynthesizer(context) { code ->
            listener?.onInit(code)
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
            setParameter(SpeechConstant.VOICE_NAME, if (isFemaleVoice) VOICE_FEMALE else VOICE_MALE)
            setParameter(ResourceUtil.TTS_RES_PATH, getResPath(context, isFemaleVoice))
        }
    }

    /**
     * 可连续播放
     */
    fun startSpeaking(text: String) {
        mSpeechSynthesizer.startSpeaking(text, mSynthesizerListener)
    }

    fun stopSpeaking() {
        mSpeechSynthesizer.stopSpeaking()
    }

    fun isSpeaking() = mSpeechSynthesizer.isSpeaking


    fun destroy() {
        stopSpeaking()
        mSpeechSynthesizer.destroy()
    }

    fun setSpeechStatusListener(listener: SpeechStatusListener?) {
        mSpeechStatusListener = listener
    }

    /**
     * 发音文件资源
     */
    private fun getResPath(context: Context, isFemaleVoice: Boolean): String {
        val builder = StringBuilder()
        builder.append(
            ResourceUtil.generateResourcePath(
                context,
                ResourceUtil.RESOURCE_TYPE.assets,
                "tts/common.jet"
            )
        )//合成通用资源
            .append(";")
            .append(
                ResourceUtil.generateResourcePath(
                    context,
                    ResourceUtil.RESOURCE_TYPE.assets,
                    "tts/${if (isFemaleVoice) VOICE_FEMALE else VOICE_MALE}.jet"
                )
            )//发音人资源
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