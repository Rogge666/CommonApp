package com.example.rogge;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.euler.andfix.patch.PatchManager;
import com.example.rogge.api.ServiceUtils;
import com.rogge.crash.CrashHandler;
import com.rogge.utils.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyApplication extends Application {

    private static MyApplication ourInstance = new MyApplication();
    public PatchManager patchManager;

    public static MyApplication getInstance() {
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
        /*
         * 初始化AppConfiguration设置
		 */
        try {
            AppConfiguration.init(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Utils.init(this);
        ServiceUtils.init(this);
        //初始化依赖的ServiceUtils
        if (BuildConfig.isPlugin){
            try {
                Class serverUtilsClass1 = Class.forName("com.rogge.cone.http.ServiceUtils");
                Method method1 = serverUtilsClass1.getMethod("init",Application.class);
                method1.invoke(null,this);

                Class serverUtilsClass2 = Class.forName("com.rogge.ctwo.http.ServiceUtils");
                Method method2 = serverUtilsClass2.getMethod("init",Application.class);
                method2.invoke(null,this);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        // TODO by Rogge : 2016/10/24 加入热跟新

        initAndFix();
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();
        ARouter.init(this);
    }

    private void initAndFix() {
        patchManager = new PatchManager(getContext());
        patchManager.init(AppConfiguration.getAppVersionName(getContext()));//current version
        patchManager.loadPatch();

//        try {
//            String patchFileString = Environment.getExternalStorageDirectory().getAbsolutePath() + "/wu.apatch";
//            MyApplication.getInstance().patchManager.addPatch(patchFileString);
//            Toast.Short(getContext(),"加载补丁成功");
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.Short(getContext(),"加载补丁失败");
//        }
    }

    public Context getContext() {
        return this.getApplicationContext();
    }

}
