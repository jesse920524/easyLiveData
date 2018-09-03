package me.jessefu.livedata_arch.base;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.ViewModelStoreOwner;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by icourt on 2018/8/24.
 */

public abstract class BaseActivity<T extends ViewModel> extends AppCompatActivity{

//    protected T mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected abstract void initViews();

    protected abstract void initViewModel();

    protected abstract void initSubscribe();

    protected Context getActivityContext(){
        return this;
    }

}
