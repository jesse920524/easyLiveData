package me.jessefu.livedata_arch.biz.vm.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import me.jessefu.livedata_arch.base.BaseApp;
import me.jessefu.livedata_arch.biz.model.main.IMainModel;
import me.jessefu.livedata_arch.biz.model.main.MainModel;
import me.jessefu.livedata_arch.net.entity.ZhihuNewsEntity;
import me.jessefu.livedata_arch.net.httpResponse.ZhihuLatestNewsResponse;

/**
 * Created by icourt on 2018/8/24.
 */

public class MainVM extends ViewModel {
    private static final String TAG = "MainVM";

    private IMainModel model;

    private MutableLiveData<List<ZhihuNewsEntity>> liveData;
    private MutableLiveData<Boolean> liveDataRefreshing;

    public MainVM(){
        liveData = new MutableLiveData<>();
        liveDataRefreshing = new MutableLiveData<>();
        model = new MainModel();
    }

    public void start(){
        liveDataRefreshing.setValue(true);
        getLatestNews();
    }

    public void getLatestNews(){

        model.getLatestNews()
                .subscribe(new LatestNewsObserver());
    }


    private class LatestNewsObserver implements Observer<ZhihuLatestNewsResponse>{

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(ZhihuLatestNewsResponse zhihuLatestNewsResponse) {
            Log.d(TAG, "onNext: " + zhihuLatestNewsResponse.getStories());
            if (!zhihuLatestNewsResponse.getStories().isEmpty()){
                Observable.just(zhihuLatestNewsResponse)
                        .map(new Function<ZhihuLatestNewsResponse, List<ZhihuNewsEntity>>() {
                            @Override
                            public List<ZhihuNewsEntity> apply(ZhihuLatestNewsResponse zhihuLatestNewsResponse) throws Exception {
                                List<ZhihuNewsEntity> result = new ArrayList<>();
                                for (ZhihuLatestNewsResponse.StoriesBean storyBean :
                                        zhihuLatestNewsResponse.getStories()) {
                                    ZhihuNewsEntity entity = new ZhihuNewsEntity();
                                    entity.setGaPrefix(storyBean.getGaPrefix());
                                    entity.setId(storyBean.getId());
                                    entity.setImages(storyBean.getImages());
                                    entity.setMultipic(storyBean.isMultipic());
                                    entity.setType(storyBean.getType());
                                    entity.setTitle(storyBean.getTitle());
                                    result.add(entity);
                                }
                                return result;
                            }
                        }).subscribe(new Consumer<List<ZhihuNewsEntity>>() {
                    @Override
                    public void accept(List<ZhihuNewsEntity> data) throws Exception {
                        liveData.setValue(data);
                    }
                });
            }


        }

        @Override
        public void onError(Throwable e) {
            liveDataRefreshing.setValue(false);
            Toast.makeText(BaseApp.getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete() {
            liveDataRefreshing.setValue(false);
        }
    }

    public MutableLiveData<List<ZhihuNewsEntity>> getLiveData() {
        return liveData;
    }

    public MutableLiveData<Boolean> getLiveDataRefreshing() {
        return liveDataRefreshing;
    }
}
