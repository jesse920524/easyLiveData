package me.jessefu.livedata_arch.base;

import me.jessefu.livedata_arch.net.api.HttpManager;

/**
 * Created by icourt on 2018/8/24.
 */

public abstract class BaseModel {
    private static final String TAG = "BaseModel";

    protected HttpManager mHttpManager;

    public BaseModel(){
        mHttpManager = HttpManager.getInstance();
    }


}
