package me.jessefu.livedata_arch.biz.model.main;

import java.util.List;


import io.reactivex.Observable;
import me.jessefu.livedata_arch.net.entity.ZhihuNewsEntity;
import me.jessefu.livedata_arch.net.httpResponse.ZhihuLatestNewsResponse;

/**
 * Created by icourt on 2018/8/24.
 */

public interface IMainModel {

    Observable<ZhihuLatestNewsResponse> getLatestNews();
}
