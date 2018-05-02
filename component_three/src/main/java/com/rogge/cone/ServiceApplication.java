package com.rogge.cone;


import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.rogge.AppConfiguration;
import com.rogge.crash.CrashHandler;
import com.rogge.user.UserInfoBean;
import com.rogge.utils.Utils;

public class ServiceApplication extends Application {

    private static ServiceApplication ourInstance = new ServiceApplication();

    public static ServiceApplication getInstance() {
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
            AppConfiguration.init(getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        LeakCanary.install(this); //检测内存泄漏
        Utils.init(this, BuildConfig.VERSION_NAME, true);

        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.setBranchLevel(3);
        userInfoBean.setIsBranchNomal(0);
        userInfoBean.setProjectCode("YF");
        AppConfiguration.getInstance().saveUserInfo(userInfoBean);
    }

    public Context getContext() {
        return this.getApplicationContext();
    }

}
