package com.rogge.cone;


import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.rogge.cone.http.ServiceUtils;
import com.rogge.crash.CrashHandler;
import com.rogge.utils.Utils;

public class ComOneApplication extends Application {

    private static ComOneApplication ourInstance = new ComOneApplication();

    public static ComOneApplication getInstance() {
        return ourInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(getApplicationContext()));
        Utils.init(this);
        ServiceUtils.init(getInstance());

        ARouter.openLog();     // 打印日志
        ARouter.openDebug();
        ARouter.init(this);
    }

    public Context getContext() {
        return this.getApplicationContext();
    }

}
