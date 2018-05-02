package com.example.rogge;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;

import com.example.rogge.bean.UserInfoBean;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;


/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2015/12/30.
 * @since 1.0.0
 */
public class AppConfiguration implements Serializable {
    public static final String APPCONFIGURATION = "appconfiguration";
    public static final String SAVEAPPCONFIGURATION = "saveappconfiguration";
    public static final String REMEMBERLOGIN = "rememberlogin";
    private static AppConfiguration _instance;
    public static Context mContext;


    //  用户登陆信息
    private UserInfoBean mUserInfoBean;

    public static AppConfiguration getInstance() {
        if (_instance == null) {
            synchronized (AppConfiguration.class) {
                if (_instance == null) {
                    _instance = new AppConfiguration();
                }
            }
        }
        return _instance;
    }

    private AppConfiguration() {
        super();
    }

    /////////初始化配置//////////////////////////////////////

    /**
     * app运行初始，创建app配置
     * @author Rogge
     * @time 2016/8/17 11:04
     */
    public static synchronized void init(Context context) {
        mContext = context;
        // 获取存储的配置对象
        _instance = getAppConfiguration();
        if (_instance == null) {
            // 无存储,则需首次创建配置对象，并存储到sharereference
            resetDefaultConfiguration(mContext);
        }
        _instance.logScreenPX();
    }

    /**
     * 出廠化所有app配置參數
     * @author Rogge
     * @time 2016/8/17 11:03
     */
    public synchronized static void resetDefaultConfiguration(Context context) {
        _instance = createFirstConfigArgs();
    }

    /**
     * 判断是否是第一次安装
     * @author Rogge
     * @time 2016/8/17 11:03
     */
    public static boolean isFirstInstall() {
        try {
            if (getAppConfiguration() == null) {
                return true;
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    /**
     * 此函数爲恢復出廠設置，包括自定义参数对象、首登的inbox更新时间等等
     * @author Rogge
     * @time 2016/8/17 11:03
     */
    private static AppConfiguration createFirstConfigArgs() {
        return new AppConfiguration();
    }

    /**
     * 获取手机屏幕信息：密度、宽、高
     * @author Rogge
     * @time 2016/8/17 11:03
     */
    @TargetApi(Build.VERSION_CODES.FROYO)
    public AppConfiguration logScreenPX() {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        float mDensity = displayMetrics.density;
        int widthPixels = displayMetrics.widthPixels;
        int hightPixels = displayMetrics.heightPixels;
        int uiMode = mContext.getResources().getConfiguration().uiMode;
        return this;
    }

    /**
     * 获取AppConfiguration实例
     * @author Rogge
     * @time 2016/8/17 11:02
     */
    public static AppConfiguration getAppConfiguration() {
        return (AppConfiguration) readObject(SAVEAPPCONFIGURATION);
    }

    /**
     * 保存AppConfiguration实例
     * @author Rogge
     * @time 2016/8/17 11:02
     */
    public void saveAppConfiguration() {
        saveObject(_instance, SAVEAPPCONFIGURATION);
    }

    /**
     * 将对象编码
     * @author Rogge
     * @time 2016/8/17 11:02
     */
    public static void saveObject(Object object, String falg) {
        SharedPreferences preferences = mContext.getSharedPreferences(APPCONFIGURATION,
                mContext.MODE_PRIVATE);
        // 创建字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            // 创建对象输出流，并封装字节流
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            // 将对象写入字节流
            oos.writeObject(object);
            // 将字节流编码成base64的字符窜
            String appConfiguration_Base64 = new String(Base64.encodeBase64(baos
                    .toByteArray()));
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(falg, appConfiguration_Base64);

            editor.commit();
        } catch (IOException e) {
        }
        Log.i("ok", "存储成功");
    }

    /**
     * 解码
     * @author Rogge
     * @time 2016/8/17 11:02
     */
    public static Object readObject(String falg) {
        Object object = null;
        SharedPreferences preferences = mContext.getSharedPreferences(APPCONFIGURATION,
                mContext.MODE_PRIVATE);
        String productBase64 = preferences.getString(falg, "");

        //读取字节
        byte[] base64 = Base64.decodeBase64(productBase64.getBytes());

        //封装到字节流
        ByteArrayInputStream bais = new ByteArrayInputStream(base64);
        try {
            //再次封装
            ObjectInputStream bis = new ObjectInputStream(bais);
            try {
                //读取对象
                object = (Object) bis.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("ok", "解析成功");
        return object;
    }

    /**
     * 获取本机IP
     * @author Rogge
     * @time 2016/8/17 11:02
     */
    public static String getLocalIp(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        if (ipAddress == 0) return null;
        return ((ipAddress & 0xff) + "." + (ipAddress >> 8 & 0xff) + "."
                + (ipAddress >> 16 & 0xff) + "." + (ipAddress >> 24 & 0xff));
    }

    /**
     * 返回当前程序版本名
     * @author Rogge
     * @time 2016/8/17 11:01
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    /**
     * 获取用户登录信息
     * @author Rogge
     * @time 2016/8/17 11:01
     */
    public UserInfoBean getUserInfoBean() {
        if (mUserInfoBean == null) {
            Log.e("UserInfoBean", "UserInfoBean为null");
        }
        return mUserInfoBean;
    }

    /**
     * 保存用户登录信息
     * @author Rogge
     * @time 2016/8/17 11:01
     */
    public AppConfiguration setUserLoginBean(UserInfoBean userInfoBean) {
        mUserInfoBean = userInfoBean;
        return this;
    }


}
