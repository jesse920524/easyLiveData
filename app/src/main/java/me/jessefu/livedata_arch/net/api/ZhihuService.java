package me.jessefu.livedata_arch.net.api;

import io.reactivex.Observable;
import me.jessefu.livedata_arch.net.httpResponse.ArticleDetailsResponse;
import me.jessefu.livedata_arch.net.httpResponse.ZhihuLatestNewsResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by icourt on 2018/8/24.
 */

public interface ZhihuService {

    @GET("api/4/news/latest")
    Observable<ZhihuLatestNewsResponse> getLatestNews();

    @GET("api/4/news/{id}")
    Observable<ArticleDetailsResponse> getArticleDetail(@Path("id") String id);
}
