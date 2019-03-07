package com.zl.dappore.home.adapter;


import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.zl.dappore.R;
import com.zl.dappore.appdetail.AppDetailActivity;
import com.zl.dappore.appdetail.model.App;
import com.zl.dappore.appdetail.model.AppDetailConstants;
import com.zl.dappore.common.utils.CommonUtils;
import com.zl.dappore.common.widget.BeautyRatingBar;
import com.zl.dappore.common.widget.shadow.ShadowDrawable;
import com.zl.dappore.web.WebViewActivity;
import com.zl.dappore.web.model.WebConstants;

/**
 * Created by zhang on 2017/3/17.
 */
public class AppRecyclerAdapterItem extends QsRecycleAdapterItem<App> {

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
    BeautyRatingBar rbScoreRankNew;
    @Bind(R.id.ll_pos_rank_new)
    LinearLayout llPosRankNew;
    @Bind(R.id.rl_item_rank_new)
    RelativeLayout rlItemRankNew;
    @Bind(R.id.tv_score_rank_new)
    TextView tvScoreRankNew;

    private App appDetail;
    private boolean isItemPos = false;

    public AppRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent, boolean isItemPos) {
        super(inflater, parent);
        this.isItemPos = isItemPos;
    }

    @Override
    protected int itemViewLayoutId() {
        return R.layout.item_rank_new;
    }

    @Override
    protected void onBindItemData(App data, int position, int totalCount) {
        this.appDetail = data;

        if (isItemPos) {
            llPosRankNew.setVisibility(View.VISIBLE);
            tvPosRankNew.setText((position + 4) + "");
        } else {
            llPosRankNew.setVisibility(View.GONE);
        }
        ShadowDrawable.setShadowDrawable(rlItemRankNew, Color.parseColor("#ffffff"), 0,
                R.color.color_shadow_gray,
                CommonUtils.dp2px(5), new int[]{CommonUtils.dp2px(5), CommonUtils.dp2px(5), CommonUtils.dp2px(5), CommonUtils.dp2px(5)}, 0, 0);
        QsHelper.getInstance().getImageHelper().createRequest().load(data.logoUrl).roundedCorners(CommonUtils.dp2px(9), CommonUtils.dp2px(9), CommonUtils.dp2px(9), CommonUtils.dp2px(9)).into(ivImgRankNew);
        tvTitleRankNew.setText(data.name);
        tvDesRankNew.setText(data.description);
        if(!TextUtils.isEmpty(data.starCount)){
            rbScoreRankNew.setRating(Float.parseFloat(data.starCount));
        }
        tvScoreRankNew.setText(data.score + "分");

    }

    @OnClick({R.id.rl_item_rank_new, R.id.iv_download_rank_new})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.rl_item_rank_new:
                Bundle bundle = new Bundle();
                bundle.putString(AppDetailConstants.BUNDLE_KEY_APPDETAIL_REQUEST_ID, appDetail.id);
                QsHelper.getInstance().intent2Activity(AppDetailActivity.class, bundle);
                break;
            case R.id.iv_download_rank_new:
                bundle = new Bundle();
                bundle.putString(WebConstants.BUNDLE_TITLE_KEY, appDetail.name);
                bundle.putString(WebConstants.BUNDLE_URL_KEY, appDetail.website);
                QsHelper.getInstance().intent2Activity(WebViewActivity.class, bundle);
                break;
        }
    }
}