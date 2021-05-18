# TTSTool
基于科大讯飞离线语音SDK封装快速实现TTS，支持设置男女声
## 引入

### 将JitPack存储库添加到您的项目中(项目根目录下build.gradle文件)
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
### 添加依赖
[![](https://jitpack.io/v/shenbengit/TTSTool.svg)](https://jitpack.io/#shenbengit/TTSTool)
```gradle
dependencies {
    //必选(armeabi-v7a|arm64-v8a)至少添加一个
    implementation 'com.github.shenbengit.TTSTool:lib:Tag'
    //可选，支持armeabi-v7
    implementation 'com.github.shenbengit.TTSTool:lib-v7a:Tag'
    //可选，支持arm64-v8a
    implementation 'com.github.shenbengit.TTSTool:lib-v8a:Tag'
}
```
## 使用事例

```kotlin
/**
  * 必须在主进程初始化
  * @param context
  * @param appId 
  * @param isFemaleVoice 是否是女声，true:女声，false:男声
  */
TTSManager.getInstance().init(context, "appId", true)

/**
  * 开始合成
  */
TTSManager.getInstance().startSpeaking(text)

/**
  * 停止合成
  */
TTSManager.getInstance().stopSpeaking(text)

/**
  * 是否正在合成
  */
TTSManager.getInstance().isSpeaking()

/**
  * 销毁
  */
TTSManager.getInstance().destroy()

```

如果您使用Kotlin，同样可以快捷调用

```kotlin
/**
  * 必须在主进程初始化
  * @param context
  * @param appId 
  * @param isFemaleVoice 是否是女声，true:女声，false:男声
  */
initTTS(context, "appId", true)

/**
  * 开始合成
  */
startSpeaking(text)

/**
  * 停止合成
  */
stopSpeaking(text)

/**
  * 是否正在合成
  */
isSpeaking()

/**
  * 销毁
  */
destroyTTS()
```

# [License](https://github.com/shenbengit/TTSTool/blob/master/LICENSE)
