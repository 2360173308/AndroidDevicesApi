####在AndroidStudio中打成jar包使用其中的方法

在AndroidStudio中将项目打包成jar包的方法：
在build.gradle（Module:app）中，修改两项：    

     //apply plugin: 'com.android.application'
     apply plugin: 'com.android.library'
     android {
    compileSdkVersion 26
    buildToolsVersion "26.0.0"
    defaultConfig {
      //  applicationId "com.sy.ondemo"
        minSdkVersion 19
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
    }


如上所示：         
注释掉 apply plugin: 'com.android.application'    
添加  apply plugin: 'com.android.library'       
注释掉 applicationId "com.sy.ondemo"      
执行rebuild project,就会在app\build\intermediates\bundles\debug下生成classes.jar文件，这个文件就可以提供给其他项目使用
