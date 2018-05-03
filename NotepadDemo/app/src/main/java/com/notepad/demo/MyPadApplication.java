package com.notepad.demo;

import android.app.Application;

/**
 * Created by feiran.zhang on 2018/5/1.
 */

public class MyPadApplication extends Application {
    public static MyPadApplication mApp;

    @Override
    public void onCreate() {
        super.onCreate();

        mApp=this;
    }

}
