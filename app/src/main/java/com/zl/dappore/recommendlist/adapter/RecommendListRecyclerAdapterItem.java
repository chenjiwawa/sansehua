package com.zl.dappore.recommendlist.adapter;


import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.zl.dappore.appdetail.AppDetailActivity;
import com.zl.dappore.appdetail.model.App;
import com.zl.dappore.appdetail.model.AppDetailConstants;
import com.zl.dappore.common.utils.CommonUtils;
import com.zl.dappore.common.widget.BeautyRatingBar;
import com.zl.dappore.common.widget.shadow.ShadowDrawable;
import com.zl.dappore.home.fragment.MineFragment;

/**
 * Created by zhang on 2017/3/17.
 */
public class RecommendListRecyclerAdapterItem extends QsRecycleAdapterItem<App> {

    @Bind(R.id.iv_img_recommend_list)
    ImageView ivImgRecommendList;
    @Bind(R.id.tv_type_recommend_list)
    TextView tvTypeRecommendList;
    @Bind(R.id.rb_score_recommend_list)
    BeautyRatingBar rbScoreRecommendList;
    @Bind(R.id.tv_score_recommend_list)
    TextView tvScoreRecommendList;
    @Bind(R.id.tv_name_recommend_list)
    TextView tvNameRecommendList;
    @Bind(R.id.tv_des_recommend_list)
    TextView tvDesRecommendList;
    @Bind(R.id.tv_rank_recommend_list)
    TextView tvRankRecommendList;
    @Bind(R.id.tv_data_recommend_list)
    TextView tvDataRecommendList;
    @Bind(R.id.ll_item_recommend_list)
    RelativeLayout llItemRecommendList;
    private App appDetail;

    public RecommendListRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent);
    }

    @Override
    protected int itemViewLayoutId() {
        return R.layout.item_recommend_list;
    }

    @Override
    protected void onBindItemData(App data, int position, int totalCount) {
        this.appDetail = data;
        L.i(initTag(), " onBindItemData " + data);
        ShadowDrawable.setShadowDrawable(llItemRecommendList, Color.parseColor("#ffffff"), 0,
                R.color.color_shadow_gray,
                CommonUtils.dp2px(5), new int[]{CommonUtils.dp2px(5), CommonUtils.dp2px(5), CommonUtils.dp2px(5), CommonUtils.dp2px(5)}, 0, 0);

        if (false && !TextUtils.isEmpty(data.logoUrl)) {
            QsHelper.getInstance().getImageHelper().createRequest().load(data.logoUrl).into(ivImgRecommendList);
        } else {
            QsHelper.getInstance().getImageHelper().createRequest().centerCrop().load("http://pizj829no.bkt.clouddn.com/Whf1Qy7eYRH5xCYxktv3ok5b").into(ivImgRecommendList);
        }
        if (data.appTaxon != null) {
            tvTypeRecommendList.setText(data.appTaxon.name);
        }
        if(!TextUtils.isEmpty(data.starCount)){
            rbScoreRecommendList.setRating(Float.parseFloat(data.starCount));
        }
        tvScoreRecommendList.setText(data.score + "分");
        tvNameRecommendList.setText(data.name);
        tvDesRecommendList.setText(data.description);
        tvRankRecommendList.setText("新品排名：test1");
        tvDataRecommendList.setText("交易量：test2");
    }

    @OnClick({R.id.rl_item_rank_new})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.rl_item_rank_new:
                Bundle bundle = new Bundle();
                bundle.putString(AppDetailConstants.BUNDLE_KEY_APPDETAIL_REQUEST_ID, appDetail.id);
                QsHelper.getInstance().intent2Activity(AppDetailActivity.class, bundle);
                break;
        }
    }
}