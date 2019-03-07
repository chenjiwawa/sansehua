package com.zl.dappore.search.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.zl.dappore.R;
import com.zl.dappore.videodetail.model.Video;

/**
 * Created by zhang on 2017/3/17.
 */
public class SearchVideoRecyclerAdapterItem extends QsRecycleAdapterItem<Video> {

    @Bind(R.id.iv_img_video_search)
    ImageView ivImgVideoSearch;
    @Bind(R.id.tv_award_video_search)
    TextView tvAwardVideoSearch;
    @Bind(R.id.tv_watch_video_search)
    TextView tvWatchVideoSearch;
    @Bind(R.id.iv_logo_video_search)
    ImageView ivLogoVideoSearch;
    @Bind(R.id.tv_name_video_search)
    TextView tvNameVideoSearch;
    @Bind(R.id.tv_des_video_search)
    TextView tvDesVideoSearch;
    @Bind(R.id.rl_item_video_search)
    RelativeLayout rlItemVideoSearch;

    Video video;

    public SearchVideoRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent);
    }

    @Override
    protected int itemViewLayoutId() {
        return R.layout.item_video_search;
    }

    @Override
    protected void onBindItemData(Video data, int position, int totalCount) {
        this.video = data;
        QsHelper.getInstance().getImageHelper().createRequest().load(data.coverUrl).into(ivImgVideoSearch);
        tvNameVideoSearch.setText(data.title + "");
        tvWatchVideoSearch.setText(data.viewCount+"");
        QsHelper.getInstance().getImageHelper().createRequest().load(data.coverUrl).circleCrop().into(ivLogoVideoSearch);
        tvDesVideoSearch.setText(data.title);
    }

    @OnClick({R.id.rl_item_video_search, R.id.iv_logo_video_search})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.rl_item_video_search:
                break;
            case R.id.iv_logo_video_search:
                break;
        }
    }
}