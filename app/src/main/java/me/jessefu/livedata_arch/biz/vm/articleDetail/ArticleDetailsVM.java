package me.jessefu.livedata_arch.biz.vm.articleDetail;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.widget.Toast;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.jessefu.livedata_arch.base.BaseApp;
import me.jessefu.livedata_arch.biz.model.articleDetail.ArticleDetailsModel;
import me.jessefu.livedata_arch.biz.model.articleDetail.IArticleDetailsModel;
import me.jessefu.livedata_arch.net.entity.ArticleDetailEntity;
import me.jessefu.livedata_arch.net.httpResponse.ArticleDetailsResponse;

/**
 * Created by icourt on 2018/8/24.
 */

public class ArticleDetailsVM extends ViewModel{
    private static final String TAG = "ArticleDetailsVM";

    public MutableLiveData<ArticleDetailEntity> getLiveDataEntity() {
        return liveDataEntity;
    }

    private MutableLiveData<ArticleDetailEntity> liveDataEntity;

    private IArticleDetailsModel model;

    public ArticleDetailsVM(){
        model = new ArticleDetailsModel();
        liveDataEntity = new MutableLiveData<>();
    }

    public void start(String id){
        getArticleDetails(id);
    }

    public void getArticleDetails(String id){
        model.getArticleDetail(id)
                .subscribe(new ArticleDetailObserver());
    }

    private class ArticleDetailObserver implements Observer<ArticleDetailsResponse>{

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(ArticleDetailsResponse articleDetailsResponse) {
            ArticleDetailEntity entity = transformData(articleDetailsResponse);
            liveDataEntity.setValue(entity);
        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(BaseApp.getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete() {

        }
    }

    /**transform http response -> entity*/
    private ArticleDetailEntity transformData(ArticleDetailsResponse response) {
        ArticleDetailEntity entity = new ArticleDetailEntity();
        entity.setId(response.getId());
        entity.setBody(response.getBody());
        entity.setImage(response.getImage());
        entity.setShareUrl(response.getShareUrl());
        entity.setTitle(response.getTitle());
        entity.setType(response.getType());
        return entity;

    }
}
