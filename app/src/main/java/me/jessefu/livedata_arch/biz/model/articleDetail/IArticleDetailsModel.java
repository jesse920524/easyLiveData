package me.jessefu.livedata_arch.biz.model.articleDetail;

import io.reactivex.Observable;
import me.jessefu.livedata_arch.net.httpResponse.ArticleDetailsResponse;

/**
 * Created by icourt on 2018/8/24.
 */

public interface IArticleDetailsModel {

    Observable<ArticleDetailsResponse> getArticleDetail(String id);
}
