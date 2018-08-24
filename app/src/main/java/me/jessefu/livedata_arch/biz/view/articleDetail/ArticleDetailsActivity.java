package me.jessefu.livedata_arch.biz.view.articleDetail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jude.swipbackhelper.SwipeBackHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessefu.livedata_arch.R;
import me.jessefu.livedata_arch.base.BaseActivity;
import me.jessefu.livedata_arch.biz.vm.articleDetail.ArticleDetailsVM;
import me.jessefu.livedata_arch.net.entity.ArticleDetailEntity;

/**
 * Created by icourt on 2018/8/24.
 */

public class ArticleDetailsActivity extends BaseActivity {
    private static final String TAG = "ArticleDetailsActivity";

    public static final String ARTICLE_ID = "articleId";
    public static void actionStart(@NonNull Context ctx, @NonNull String articleId){
            ctx.startActivity(
                    new Intent(ctx, ArticleDetailsActivity.class)
                        .putExtra(ARTICLE_ID, articleId));
    }

    @BindView(R.id.iv_ad_bg)
    ImageView mIvBackground;
    @BindView(R.id.iv_ad_back)
    ImageView mIvBack;
    @BindView(R.id.tv_ad_title)
    TextView mTvTitle;
    @BindView(R.id.wv_ad)
    WebView mWebView;

    private ArticleDetailsVM mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        setContentView(R.layout.activity_article_details);
        ButterKnife.bind(this);
        SwipeBackHelper.onCreate(this);
        initViews();
        initViewModel();
        initSubscribe();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }

    @Override
    protected void initViews() {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());

    }

    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(ArticleDetailsVM.class);
    }

    @Override
    protected void initSubscribe() {
        mViewModel.start(getIntent().getStringExtra(ARTICLE_ID));

        mViewModel.getLiveDataEntity().observe(this, new Observer<ArticleDetailEntity>() {
            @Override
            public void onChanged(@Nullable ArticleDetailEntity data) {
                mTvTitle.setText(data.getTitle());

                Glide.with(getActivityContext())
                        .load(data.getImage())
                        .apply(new RequestOptions().centerCrop())
                        .into(mIvBackground);
                loadWebView(data.getBody());
            }
        });
    }

    private void loadWebView(String body) {
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/zhihu.css\" type=\"text/css\">";
        String html = "<html><head>" + css + "</head><body>" + body
                + "</div>\n</div>\n<script>var list = document.getElementsByTagName('A');for(var i =0; i < list.length;i++){list[i].href='javascript:void(0)';}</script></body>\n</html>";
        html = html.replace("<div class=\"img-place-holder\">", "");
        mWebView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
    }

    @OnClick(R.id.iv_ad_back)
    public void onClickBack(){
        onBackPressed();
    }
}
