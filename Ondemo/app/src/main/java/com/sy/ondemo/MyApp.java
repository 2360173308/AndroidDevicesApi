package com.sy.ondemo;

import android.app.Application;
import android.content.Context;

/**
 * 创建时间： 2017/11/14 0014.
 * 编写人：Tina
 * 邮箱：1208156801@qq.com
 * 功能描述：
 */

public class MyApp extends Application{
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }


}
