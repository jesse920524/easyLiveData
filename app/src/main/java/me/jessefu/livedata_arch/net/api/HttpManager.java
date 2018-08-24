package me.jessefu.livedata_arch.net.api;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessefu.livedata_arch.net.httpResponse.ArticleDetailsResponse;
import me.jessefu.livedata_arch.net.httpResponse.ZhihuLatestNewsResponse;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by icourt on 2018/8/24.
 */

public class HttpManager {
    private static final String TAG = "HttpManager";
    private static final long NETWORK_TIMEOUT = 1000;
    private static final String BASE_URL = "http://news-at.zhihu.com/";
    private Retrofit mRetrofit;

    private ZhihuService mZhihuService;

    private HttpManager(){
        //init okhttp client
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);

        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mZhihuService = mRetrofit.create(ZhihuService.class);
    }

    private static class SingletonHolder{
        private static final HttpManager INSTANCE = new HttpManager();
    }

    public static HttpManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**获取新闻列表*/
    public Observable<ZhihuLatestNewsResponse> getLatestNews(){
        return mZhihuService.getLatestNews();
    }

    /**获取新闻详情
     * @param articleId */
    public Observable<ArticleDetailsResponse> getArticleDetail(String articleId){
        return mZhihuService.getArticleDetail(articleId)
                ;
    }
}
