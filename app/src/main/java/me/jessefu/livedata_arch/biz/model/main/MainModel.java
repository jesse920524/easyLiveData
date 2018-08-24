package me.jessefu.livedata_arch.biz.model.main;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessefu.livedata_arch.base.BaseModel;
import me.jessefu.livedata_arch.net.httpResponse.ZhihuLatestNewsResponse;

/**
 * Created by icourt on 2018/8/24.
 */

public class MainModel extends BaseModel implements IMainModel{

    @Override
    public Observable<ZhihuLatestNewsResponse> getLatestNews() {
        return mHttpManager.getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
