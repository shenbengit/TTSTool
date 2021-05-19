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
### 需要使用的用户权限
> AndroidManifest.xml中已添加，危险权限可能要自行处理。
```xml
<!--连接网络权限，用于执行云端语音能力 -->
<uses-permission android:name="android.permission.INTERNET"/>
<!--获取手机录音机使用权限，听写、识别、语义理解需要用到此权限 -->
<uses-permission android:name="android.permission.RECORD_AUDIO"/>
<!--读取网络信息状态 -->
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<!--获取当前wifi状态 -->
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
<!--允许程序改变网络连接状态 -->
<uses-permission android:name="android.permission.CHANGE_NETWORK_STATE"/>
<!--读取手机信息权限 -->
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
<!--读取联系人权限，上传联系人需要用到此权限 -->
<uses-permission android:name="android.permission.READ_CONTACTS"/>
<!--外存储写权限，构建语法需要用到此权限 -->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<!--外存储读权限，构建语法需要用到此权限 -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<!--配置权限，用来记录应用配置信息 -->
<uses-permission android:name="android.permission.WRITE_SETTINGS"/>
<!--手机定位信息，用来为语义等功能提供定位，提供更精准的服务-->
<!--定位信息是敏感信息，可通过Setting.setLocationEnable(false)关闭定位请求 -->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
<!--如需使用人脸识别，还要添加：摄相头权限，拍照需要用到 -->
<uses-permission android:name="android.permission.CAMERA" />
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

## proguard-rules.pro
>  已添加混淆规则，一般情况不需要自行添加
```xml
-keep class com.iflytek.**{*;}
-keepattributes Signature
```

# [License](https://github.com/shenbengit/TTSTool/blob/master/LICENSE)
