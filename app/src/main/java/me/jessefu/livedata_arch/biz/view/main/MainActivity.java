package me.jessefu.livedata_arch.biz.view.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.disposables.Disposable;
import me.jessefu.livedata_arch.R;
import me.jessefu.livedata_arch.base.BaseActivity;
import me.jessefu.livedata_arch.biz.view.adapter.RvMainAdapter;
import me.jessefu.livedata_arch.biz.view.articleDetail.ArticleDetailsActivity;
import me.jessefu.livedata_arch.biz.vm.main.MainVM;
import me.jessefu.livedata_arch.net.entity.ZhihuNewsEntity;

/**
 * 20180824
 *
 **/
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.cdl_main)
    CoordinatorLayout mcoordinatorLayout;
    @BindView(R.id.abl_main)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.tb_main)
    Toolbar mToolbar;
    @BindView(R.id.rv_main)
    RecyclerView mRecyclerView;
    @BindView(R.id.srl_main)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView.LayoutManager layoutManager;
    private RvMainAdapter mAdapter;


    private MainVM mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        initViewModel();
        initSubscribe();
    }

    protected void initSubscribe() {
        mViewModel.start();

        mViewModel.getLiveDataRefreshing().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                mSwipeRefreshLayout.setRefreshing(aBoolean);
            }
        });

        mViewModel.getLiveData().observe(this, new Observer<List<ZhihuNewsEntity>>() {
            @Override
            public void onChanged(@Nullable List<ZhihuNewsEntity> zhihuNewsEntities) {
                mAdapter.setNewData(zhihuNewsEntities);
            }
        });
    }

    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(MainVM.class);
    }

    protected void initViews() {
        mToolbar.setTitle("LiveData + MVVM Architechture");
        mToolbar.setTitleTextColor(ActivityCompat.getColor(this, R.color.white));
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new RvMainAdapter();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ZhihuNewsEntity entity = (ZhihuNewsEntity) adapter.getData().get(position);
                ArticleDetailsActivity.actionStart(getActivityContext(), String.valueOf(entity.getId()));
            }
        });
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mViewModel.start();
            }
        });

    }



}
