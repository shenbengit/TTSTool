# TTSTool
基于科大讯飞离线语音SDK封装快速实现TTS
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

```
