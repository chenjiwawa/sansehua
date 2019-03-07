package com.zl.dappore.userinfo.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.percentlayout.PercentRelativeLayout;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.zl.dappore.R;
import com.zl.dappore.home.model.GridItemVideo;
import com.zl.dappore.videodetail.VideoDetailActivity;
import com.zl.dappore.videodetail.model.VideoDetailConstants;

/**
 * Created by zhang on 2017/3/17.
 */
public class PersonalVideoRecyclerAdapterItem extends QsRecycleAdapterItem<GridItemVideo> {

    @Bind(R.id.iv_img_video1_grid)
    ImageView ivImgVideo1Grid;
    @Bind(R.id.btn_delete_video1_grid)
    Button btnDeleteVideo1Grid;
    @Bind(R.id.tv_award_video1_grid)
    TextView tvAwardVideo1Grid;
    @Bind(R.id.btn_watch_video1_grid)
    Button btnWatchVideo1Grid;
    @Bind(R.id.rl_item_video1_grid)
    PercentRelativeLayout rlItemVideo1Grid;
    @Bind(R.id.iv_img_video2_grid)
    ImageView ivImgVideo2Grid;
    @Bind(R.id.btn_delete_video2_grid)
    Button btnDeleteVideo2Grid;
    @Bind(R.id.tv_award_video2_grid)
    TextView tvAwardVideo2Grid;
    @Bind(R.id.btn_watch_video2_grid)
    Button btnWatchVideo2Grid;
    @Bind(R.id.rl_item_video2_grid)
    PercentRelativeLayout rlItemVideo2Grid;
    @Bind(R.id.iv_img_video3_grid)
    ImageView ivImgVideo3Grid;
    @Bind(R.id.btn_delete_video3_grid)
    Button btnDeleteVideo3Grid;
    @Bind(R.id.tv_award_video3_grid)
    TextView tvAwardVideo3Grid;
    @Bind(R.id.btn_watch_video3_grid)
    Button btnWatchVideo3Grid;
    @Bind(R.id.rl_item_video3_grid)
    PercentRelativeLayout rlItemVideo3Grid;
    GridItemVideo itemData;
    boolean isUser;

    public PersonalVideoRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent, boolean isUser) {
        super(inflater, parent);
        this.isUser = isUser;
    }

    @Override
    protected int itemViewLayoutId() {
        return R.layout.item_video_personal_grid;
    }

    @Override
    protected void onBindItemData(GridItemVideo itemData, int position, int totalCount) {
        this.itemData = itemData;
        L.i(initTag(), " onBindItemData " + itemData);
        btnDeleteVideo1Grid.setVisibility(isUser ? View.VISIBLE : View.GONE);
        btnDeleteVideo2Grid.setVisibility(isUser ? View.VISIBLE : View.GONE);
        btnDeleteVideo3Grid.setVisibility(isUser ? View.VISIBLE : View.GONE);

        if (itemData != null && itemData.videos != null && itemData.videos.size() > 0) {
            QsHelper.getInstance().getImageHelper().createRequest().load(itemData.videos.get(0).coverUrl).into(ivImgVideo1Grid);
            btnWatchVideo1Grid.setText(itemData.videos.get(0).viewCount + "");
        }

        if (itemData != null && itemData.videos != null && itemData.videos.size() > 1) {
            QsHelper.getInstance().getImageHelper().createRequest().load(itemData.videos.get(1).coverUrl).into(ivImgVideo1Grid);
            btnWatchVideo1Grid.setText(itemData.videos.get(1).viewCount + "");
        }

        if (itemData != null && itemData.videos != null && itemData.videos.size() > 2) {
            QsHelper.getInstance().getImageHelper().createRequest().load(itemData.videos.get(2).coverUrl).into(ivImgVideo1Grid);
            btnWatchVideo1Grid.setText(itemData.videos.get(2).viewCount + "");
        }
    }

    @OnClick({R.id.btn_delete_video1_grid, R.id.btn_watch_video1_grid, R.id.rl_item_video1_grid, R.id.btn_delete_video2_grid, R.id.btn_watch_video2_grid, R.id.rl_item_video2_grid, R.id.btn_delete_video3_grid, R.id.btn_watch_video3_grid, R.id.rl_item_video3_grid})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.btn_delete_video1_grid:
                break;
            case R.id.btn_watch_video1_grid:
                break;
            case R.id.rl_item_video1_grid:
                if (itemData != null && itemData.videos != null && itemData.videos.size() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(VideoDetailConstants.BUNDLE_KEY_VIDEODETAIL_REQUEST_VIDEO, itemData.videos.get(0));
                    QsHelper.getInstance().intent2Activity(VideoDetailActivity.class, bundle);
                }
                break;
            case R.id.btn_delete_video2_grid:
                break;
            case R.id.btn_watch_video2_grid:
                break;
            case R.id.rl_item_video2_grid:
                if (itemData != null && itemData.videos != null && itemData.videos.size() > 1) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(VideoDetailConstants.BUNDLE_KEY_VIDEODETAIL_REQUEST_VIDEO, itemData.videos.get(1));
                    QsHelper.getInstance().intent2Activity(VideoDetailActivity.class, bundle);
                }
                break;
            case R.id.btn_delete_video3_grid:
                break;
            case R.id.btn_watch_video3_grid:
                break;
            case R.id.rl_item_video3_grid:
                if (itemData != null && itemData.videos != null && itemData.videos.size() > 2) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(VideoDetailConstants.BUNDLE_KEY_VIDEODETAIL_REQUEST_VIDEO, itemData.videos.get(2));
                    QsHelper.getInstance().intent2Activity(VideoDetailActivity.class, bundle);
                }
                break;
        }
    }
}