package me.jessefu.livedata_arch.biz.view.adapter;

import android.annotation.SuppressLint;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import me.jessefu.livedata_arch.R;
import me.jessefu.livedata_arch.net.entity.ZhihuNewsEntity;

/**
 * Created by icourt on 2018/8/24.
 */

public class RvMainAdapter extends BaseQuickAdapter<ZhihuNewsEntity, BaseViewHolder>
implements LayoutProvider{

    private int resId;
    public RvMainAdapter(int layoutResId) {
        super(layoutResId);
    }

    public RvMainAdapter(){
        super(R.layout.item_rv_main);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhihuNewsEntity item) {
        helper.setText(R.id.tv_item_rvmain_title, item.getTitle());

        Glide.with(mContext)
                .load(item.getImages().get(0))
                .apply(new RequestOptions().centerCrop())
                .into((ImageView) helper.getView(R.id.iv_item_rvmain_bg));
    }

    @SuppressLint("ResourceType")
    @Override
    public int provideLayoutId() {
        return R.layout.item_rv_main;
    }
}
