package me.jessefu.livedata_arch.biz.model.articleDetail;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessefu.livedata_arch.base.BaseModel;
import me.jessefu.livedata_arch.net.httpResponse.ArticleDetailsResponse;

/**
 * Created by icourt on 2018/8/24.
 */

public class ArticleDetailsModel extends BaseModel implements IArticleDetailsModel{
    private static final String TAG = "ArticleDetailsModel";

    @Override
    public Observable<ArticleDetailsResponse> getArticleDetail(String id) {
        return mHttpManager.getArticleDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
