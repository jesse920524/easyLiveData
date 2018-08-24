package me.jessefu.livedata_arch.base;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * Created by icourt on 2018/8/24.
 */

public class BaseApp extends Application {
    private static final String TAG = "BaseApp";

    private static Context mContext;

    public static Context getContext(){
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

}
