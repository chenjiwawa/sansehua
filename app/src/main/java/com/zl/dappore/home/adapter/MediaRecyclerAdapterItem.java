package com.zl.dappore.home.adapter;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.zl.dappore.R;
import com.zl.dappore.appdetail.AppDetailActivity;
import com.zl.dappore.appdetail.model.App;
import com.zl.dappore.appdetail.model.AppDetailConstants;

/**
 * Created by zhang on 2017/3/17.
 */
public class MediaRecyclerAdapterItem extends QsRecycleAdapterItem<App> {

    @Bind(R.id.rl_item_rank_new)
    RelativeLayout rl_item_rank_new;
    @Bind(R.id.tv_pos_rank_new)
    TextView tvPosRankNew;
    @Bind(R.id.iv_img_rank_new)
    ImageView ivImgRankNew;
    @Bind(R.id.tv_title_rank_new)
    TextView tvTitleRankNew;
    @Bind(R.id.tv_des_rank_new)
    TextView tvDesRankNew;
    @Bind(R.id.iv_download_rank_new)
    Button ivDownloadRankNew;
    @Bind(R.id.rb_score_rank_new)
    RatingBar rbScoreRankNew;

    App appDetail;

    public MediaRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent);
    }

    @Override
    protected int itemViewLayoutId() {
        return R.layout.item_rank_new;
    }

    @Override
    protected void onBindItemData(App data, int position, int totalCount) {
//        this.appDetail=data;
//        tvPosRankNew.setText(position + "");
//        QsHelper.getInstance().getImageHelper().createRequest().load(data.logoUrl).roundedCorners(CommonUtils.dp2px(9),CommonUtils.dp2px(9),CommonUtils.dp2px(9),CommonUtils.dp2px(9)).into(ivImgRankNew);
//        tvTitleRankNew.setText(data.name);
//        tvDesRankNew.setText(data.description);
//        rbScoreRankNew.setRating(1.5f);

    }

    @OnClick({R.id.rl_item_rank_new, R.id.iv_download_rank_new}) public void onViewClick(View view) {
        super.onViewClick(view);
        QsToast.show("onViewClick");
        switch (view.getId()) {
            case R.id.rl_item_rank_new:
                QsToast.show("rl_item_rank_new");
                Bundle bundle = new Bundle();
                bundle.putString(AppDetailConstants.BUNDLE_KEY_APPDETAIL_REQUEST_ID, appDetail.id);
                QsHelper.getInstance().intent2Activity(AppDetailActivity.class, bundle);
                break;
            case R.id.iv_download_rank_new:
                QsToast.show("iv_download_rank_new");
                break;
        }
    }
}