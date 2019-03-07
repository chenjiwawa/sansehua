package com.zl.dappore.home.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.zl.dappore.R;
import com.zl.dappore.home.model.GridItemVideo;
import com.zl.dappore.videodetail.VideoDetailActivity;
import com.zl.dappore.videodetail.model.VideoDetailConstants;

/**
 * Created by zhang on 2017/3/17.
 */
public class VideoRecyclerAdapterItem extends QsRecycleAdapterItem<GridItemVideo> {

    @Bind(R.id.iv_img_video1_grid)
    ImageView ivImgVideo1Search;
    @Bind(R.id.tv_award_video1_grid)
    TextView tvAwardVideo1Search;
    @Bind(R.id.tv_watch_video1_grid)
    TextView tvWatchVideo1Search;
    @Bind(R.id.iv_logo_video1_grid)
    ImageView ivLogoVideo1Search;
    @Bind(R.id.tv_name_video1_grid)
    TextView tvNameVideo1Search;
    @Bind(R.id.tv_des_video1_grid)
    TextView tvDesVideo1Search;
    @Bind(R.id.rl_item_video1_grid)
    RelativeLayout rlItemVideo1Search;

    @Bind(R.id.iv_img_video2_grid)
    ImageView ivImgVideo2Search;
    @Bind(R.id.tv_award_video2_grid)
    TextView tvAwardVideo2Search;
    @Bind(R.id.tv_watch_video2_grid)
    TextView tvWatchVideo2Search;
    @Bind(R.id.iv_logo_video2_grid)
    ImageView ivLogoVideo2Search;
    @Bind(R.id.tv_name_video2_grid)
    TextView tvNameVideo2Search;
    @Bind(R.id.tv_des_video2_grid)
    TextView tvDesVideo2Search;
    @Bind(R.id.rl_item_video2_grid)
    RelativeLayout rlItemVideo2Search;

    GridItemVideo itemData;

    public VideoRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent);
    }

    @Override
    protected int itemViewLayoutId() {
        return R.layout.item_video_grid;
    }

    @Override
    protected void onBindItemData(GridItemVideo itemData, int position, int totalCount) {
        this.itemData = itemData;
        L.i(initTag(), " onBindItemData " + itemData);

        if (itemData != null && itemData.videos != null && itemData.videos.size() > 0) {
            QsHelper.getInstance().getImageHelper().createRequest().load(itemData.videos.get(0).coverUrl).into(ivImgVideo1Search);
            tvNameVideo1Search.setText(itemData.videos.get(0).title + "");
            tvWatchVideo1Search.setText(itemData.videos.get(0).viewCount + "");
            QsHelper.getInstance().getImageHelper().createRequest().load(itemData.videos.get(0).coverUrl).circleCrop().into(ivLogoVideo1Search);
            tvDesVideo1Search.setText(itemData.videos.get(0).title);
        }

        if (itemData != null && itemData.videos != null && itemData.videos.size() > 1) {
            QsHelper.getInstance().getImageHelper().createRequest().load(itemData.videos.get(1).coverUrl).into(ivImgVideo2Search);
            tvNameVideo2Search.setText(itemData.videos.get(1).title + "");
            tvWatchVideo2Search.setText(itemData.videos.get(1).viewCount + "");
            QsHelper.getInstance().getImageHelper().createRequest().load(itemData.videos.get(1).coverUrl).circleCrop().into(ivLogoVideo2Search);
            tvDesVideo2Search.setText(itemData.videos.get(1).title);
        }
    }

    @OnClick({R.id.rl_item_video1_grid, R.id.iv_logo_video1_grid, R.id.rl_item_video2_grid, R.id.iv_logo_video2_grid})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.rl_item_video1_grid:
                if (itemData != null && itemData.videos != null && itemData.videos.size() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(VideoDetailConstants.BUNDLE_KEY_VIDEODETAIL_REQUEST_VIDEO, itemData.videos.get(0));
                    QsHelper.getInstance().intent2Activity(VideoDetailActivity.class, bundle);
                }
                break;
            case R.id.iv_logo_video1_grid:
                break;
            case R.id.rl_item_video2_grid:
                if (itemData != null && itemData.videos != null && itemData.videos.size() > 1) {
                    L.i(initTag(), " initData video " + itemData.videos.get(1));
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(VideoDetailConstants.BUNDLE_KEY_VIDEODETAIL_REQUEST_VIDEO, itemData.videos.get(1));
                    QsHelper.getInstance().intent2Activity(VideoDetailActivity.class, bundle);
                }
                break;
            case R.id.iv_logo_video2_grid:
                break;
        }
    }
}