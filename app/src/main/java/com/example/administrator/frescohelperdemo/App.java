package com.example.administrator.frescohelperdemo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by android_ls on 2017/6/12.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /** 一定要添加, 不然有可能產生 android.view.InflateException: Binary XML file line #7: Error inflating class com.facebook.drawee.view.SimpleDraweeView... 的錯誤 */
        Fresco.initialize(this);
    }

}
