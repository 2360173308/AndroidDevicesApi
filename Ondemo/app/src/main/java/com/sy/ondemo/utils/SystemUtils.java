package com.sy.ondemo.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * 创建时间： 2017/11/14 0014.
 * 编写人：Tina
 * 邮箱：1208156801@qq.com
 * 功能描述：通过反射修改系统参数
 */

public class SystemUtils {
    /**
     * 旋转屏幕
     */
    public static void sethwrotation(String rotate){
        try {
            Class<?> threadClazz = Class.forName("android.os.SystemProperties");
            Method method = threadClazz.getMethod("set", String.class, String.class);
            Log.d("---12", "canWeSetSystemProperty: "+method.toString());
            method.invoke(null, "persist.sys.hwrotation", rotate);
//            Log.d("---", "result = " + method.invoke(null, "persist.sys.hwrotation", "0"));
        } catch (Exception e){
            Log.wtf("----", e);
        }

    }

    /*获取系统屏幕亮度*/
    private static int BrightValue ;

    public static int getScreenBrightness(Context activity){
        int nowBrightnessValue =0;
        ContentResolver resolver = activity.getContentResolver();
        try {
            nowBrightnessValue = android.provider.Settings.System.getInt(
                    resolver, Settings.System.SCREEN_BRIGHTNESS);

        }catch (Exception e){
            e.printStackTrace();

        }
        BrightValue = nowBrightnessValue;

        return  nowBrightnessValue;
    }


    /*设置系统亮度*/

    public static void setBrightness(Context activity,int briNumba){
//         int  briNumb = (int)Math.rint((255/100)*briNumba);
        int  briNumb = briNumba;
        int   nowBrightnessValuea = 0;
        ContentResolver resolver = activity.getContentResolver();
        Uri uri = android.provider.Settings.System
                .getUriFor("screen_brightness");
        int nowScreenBri = getScreenBrightness(activity);
//        nowScreenBri = nowScreenBri <= 225 ? nowScreenBri + briNumb : briNumb;
//        System.out.println("nowScreenBri==" + nowScreenBri);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(activity)) {
                Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + activity.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(intent);
            } else {
                //有了权限，具体的动作
//                Settings.System.putInt(resolver, "screen_brightness",
//                        briNumb);
                android.provider.Settings.System.putInt(resolver, "screen_brightness",
                        briNumb);
            }
        }


        android.provider.Settings.System.putInt(resolver, "screen_brightness",
                briNumb);
        resolver.notifyChange(uri, null);
        try {
            nowBrightnessValuea = Settings.System.getInt(
                    resolver, Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        BrightValue = nowBrightnessValuea;



    }


    public void setScrennManualMode(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        try {
            int mode = Settings.System.getInt(contentResolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE);
            if (mode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }


}
