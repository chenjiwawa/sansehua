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
import com.zl.dappore.home.CategoryTypeI;
import com.zl.dappore.web.WebViewActivity;
import com.zl.dappore.web.model.WebConstants;

public class RecommendCategoryAdapterItem extends QsRecycleAdapterItem<App.AppTaxon> {

    @Bind(R.id.ll_item_recommend)
    LinearLayout ll_item_recommend;
    @Bind(R.id.rl_item_recommend)
    RelativeLayout rl_item_recommend;
    @Bind(R.id.tv_title_recommend)
    TextView tvTitleRecommend;

    @Bind(R.id.iv_img_item1_recommend)
    ImageView ivImgitem1Recommend;
    @Bind(R.id.tv_title_item1_recommend)
    TextView tvTitleitem1Recommend;
    @Bind(R.id.tv_des_item1_recommend)
    TextView tvDesitem1Recommend;
    @Bind(R.id.rb_score_item1_recommend)
    BeautyRatingBar rbScoreitem1Recommend;
    @Bind(R.id.tv_score_item1_recommend)
    TextView tvScoreitem1Recommend;
    @Bind(R.id.iv_download_item1_recommend)
    Button ivDownloaditem1Recommend;
    @Bind(R.id.tv_award_item1_recommend)
    TextView tvAwarditem1Recommend;
    @Bind(R.id.rl_item1_recommend)
    RelativeLayout rlitem1Recommend;

    @Bind(R.id.iv_img_item2_recommend)
    ImageView ivImgitem2Recommend;
    @Bind(R.id.tv_title_item2_recommend)
    TextView tvTitleitem2Recommend;
    @Bind(R.id.tv_des_item2_recommend)
    TextView tvDesitem2Recommend;
    @Bind(R.id.rb_score_item2_recommend)
    BeautyRatingBar rbScoreitem2Recommend;
    @Bind(R.id.tv_score_item2_recommend)
    TextView tvScoreitem2Recommend;
    @Bind(R.id.iv_download_item2_recommend)
    Button ivDownloaditem2Recommend;
    @Bind(R.id.tv_award_item2_recommend)
    TextView tvAwarditem2Recommend;
    @Bind(R.id.rl_item2_recommend)
    RelativeLayout rlitem2Recommend;

    @Bind(R.id.iv_img_item3_recommend)
    ImageView ivImgitem3Recommend;
    @Bind(R.id.tv_title_item3_recommend)
    TextView tvTitleitem3Recommend;
    @Bind(R.id.tv_des_item3_recommend)
    TextView tvDesitem3Recommend;
    @Bind(R.id.rb_score_item3_recommend)
    BeautyRatingBar rbScoreitem3Recommend;
    @Bind(R.id.tv_score_item3_recommend)
    TextView tvScoreitem3Recommend;
    @Bind(R.id.iv_download_item3_recommend)
    Button ivDownloaditem3Recommend;
    @Bind(R.id.tv_award_item3_recommend)
    TextView tvAwarditem3Recommend;
    @Bind(R.id.rl_item3_recommend)
    RelativeLayout rlitem3Recommend;

    App.AppTaxon appTaxon;
    private CategoryTypeI categoryTypeI;

    public RecommendCategoryAdapterItem(LayoutInflater inflater, ViewGroup parent, CategoryTypeI categoryTypeI) {
        super(inflater, parent);
        this.categoryTypeI = categoryTypeI;
    }

    @Override
    protected int itemViewLayoutId() {
        return R.layout.item_recommend_category;
    }

    @Override
    protected void onBindItemData(App.AppTaxon appTaxon, int i, int i1) {
        this.appTaxon = appTaxon;
        ShadowDrawable.setShadowDrawable(ll_item_recommend, Color.parseColor("#ffffff"), 0,
                R.color.color_shadow_gray,
                CommonUtils.dp2px(5), new int[]{CommonUtils.dp2px(5), CommonUtils.dp2px(0), CommonUtils.dp2px(5), CommonUtils.dp2px(5)}, 0, 0);
        tvTitleRecommend.setText(appTaxon.name);
        if (appTaxon != null && appTaxon.apps != null && 1 <= appTaxon.apps.size()) {
            rlitem1Recommend.setVisibility(View.VISIBLE);
            ShadowDrawable.setShadowDrawable(rlitem1Recommend, Color.parseColor("#ffffff"), 0,
                    R.color.color_shadow_gray,
                    CommonUtils.dp2px(5), new int[]{CommonUtils.dp2px(5), CommonUtils.dp2px(5), CommonUtils.dp2px(5), CommonUtils.dp2px(5)}, 0, 0);
            QsHelper.getInstance().getImageHelper().createRequest().load(appTaxon.apps.get(0).logoUrl).roundedCorners(CommonUtils.dp2px(9), CommonUtils.dp2px(9), CommonUtils.dp2px(9), CommonUtils.dp2px(9)).into(ivImgitem1Recommend);
            tvTitleitem1Recommend.setText(appTaxon.apps.get(0).name);
            tvDesitem1Recommend.setText(appTaxon.apps.get(0).description);
            if(!TextUtils.isEmpty(appTaxon.apps.get(0).starCount)){
                rbScoreitem1Recommend.setRating(Float.parseFloat(appTaxon.apps.get(0).starCount));
            }
            tvScoreitem1Recommend.setText(appTaxon.apps.get(0).score + "分");
            tvAwarditem1Recommend.setText("1");
        } else {
            rlitem1Recommend.setVisibility(View.GONE);
        }
        if (appTaxon != null && appTaxon.apps != null && 2 <= appTaxon.apps.size()) {
            rlitem2Recommend.setVisibility(View.VISIBLE);
            ShadowDrawable.setShadowDrawable(rlitem2Recommend, Color.parseColor("#ffffff"), 0,
                    R.color.color_shadow_gray,
                    CommonUtils.dp2px(5), new int[]{CommonUtils.dp2px(5), CommonUtils.dp2px(5), CommonUtils.dp2px(5), CommonUtils.dp2px(5)}, 0, 0);
            QsHelper.getInstance().getImageHelper().createRequest().load(appTaxon.apps.get(1).logoUrl).roundedCorners(CommonUtils.dp2px(9), CommonUtils.dp2px(9), CommonUtils.dp2px(9), CommonUtils.dp2px(9)).into(ivImgitem2Recommend);
            tvTitleitem2Recommend.setText(appTaxon.apps.get(1).name);
            tvDesitem2Recommend.setText(appTaxon.apps.get(1).description);
            if(!TextUtils.isEmpty(appTaxon.apps.get(1).starCount)){
                rbScoreitem2Recommend.setRating(Float.parseFloat(appTaxon.apps.get(1).starCount));
            }
            tvScoreitem2Recommend.setText(appTaxon.apps.get(1).score + "分");
            tvAwarditem2Recommend.setText("1");
        } else {
            rlitem2Recommend.setVisibility(View.GONE);
        }
        if (appTaxon != null && appTaxon.apps != null && 3 <= appTaxon.apps.size()) {
            rlitem3Recommend.setVisibility(View.VISIBLE);
            ShadowDrawable.setShadowDrawable(rlitem3Recommend, Color.parseColor("#ffffff"), 0,
                    R.color.color_shadow_gray,
                    CommonUtils.dp2px(5), new int[]{CommonUtils.dp2px(5), CommonUtils.dp2px(5), CommonUtils.dp2px(5), CommonUtils.dp2px(5)}, 0, 0);
            QsHelper.getInstance().getImageHelper().createRequest().load(appTaxon.apps.get(2).logoUrl).roundedCorners(CommonUtils.dp2px(9), CommonUtils.dp2px(9), CommonUtils.dp2px(9), CommonUtils.dp2px(9)).into(ivImgitem3Recommend);
            tvTitleitem3Recommend.setText(appTaxon.apps.get(2).name);
            tvDesitem3Recommend.setText(appTaxon.apps.get(2).description);
            if(!TextUtils.isEmpty(appTaxon.apps.get(2).starCount)){
                rbScoreitem3Recommend.setRating(Float.parseFloat(appTaxon.apps.get(2).starCount));
            }
            tvScoreitem3Recommend.setText(appTaxon.apps.get(2).score + "分");
            tvAwarditem3Recommend.setText("1");
        } else {
            rlitem3Recommend.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.rl_item_recommend, R.id.rl_item1_recommend, R.id.rl_item2_recommend, R.id.rl_item3_recommend, R.id.iv_download_item1_recommend, R.id.iv_download_item2_recommend, R.id.iv_download_item3_recommend})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.rl_item_recommend:
                L.i(initTag(), "CategoryTypeI " + appTaxon.id);
                if (categoryTypeI != null) {
                    categoryTypeI.onCategoryTypeSelect(appTaxon.id);
                }
                break;
            case R.id.rl_item1_recommend:
                Bundle bundle = new Bundle();
                bundle.putString(AppDetailConstants.BUNDLE_KEY_APPDETAIL_REQUEST_ID, appTaxon.apps.get(0).id);
                QsHelper.getInstance().intent2Activity(AppDetailActivity.class, bundle);
                break;
            case R.id.rl_item2_recommend:
                bundle = new Bundle();
                bundle.putString(AppDetailConstants.BUNDLE_KEY_APPDETAIL_REQUEST_ID, appTaxon.apps.get(1).id);
                QsHelper.getInstance().intent2Activity(AppDetailActivity.class, bundle);
                break;
            case R.id.rl_item3_recommend:
                bundle = new Bundle();
                bundle.putString(AppDetailConstants.BUNDLE_KEY_APPDETAIL_REQUEST_ID, appTaxon.apps.get(2).id);
                QsHelper.getInstance().intent2Activity(AppDetailActivity.class, bundle);
                break;
            case R.id.iv_download_item1_recommend:
                if (appTaxon != null && appTaxon.apps != null && 1 <= appTaxon.apps.size()) {
                    bundle = new Bundle();
                    bundle.putString(WebConstants.BUNDLE_TITLE_KEY, appTaxon.apps.get(0).name);
                    bundle.putString(WebConstants.BUNDLE_URL_KEY, appTaxon.apps.get(0).website);
                    QsHelper.getInstance().intent2Activity(WebViewActivity.class, bundle);
                }
                break;
            case R.id.iv_download_item2_recommend:
                if (appTaxon != null && appTaxon.apps != null && 2 <= appTaxon.apps.size()) {
                    bundle = new Bundle();
                    bundle.putString(WebConstants.BUNDLE_TITLE_KEY, appTaxon.apps.get(1).name);
                    bundle.putString(WebConstants.BUNDLE_URL_KEY, appTaxon.apps.get(1).website);
                    QsHelper.getInstance().intent2Activity(WebViewActivity.class, bundle);
                }
                break;
            case R.id.iv_download_item3_recommend:
                if (appTaxon != null && appTaxon.apps != null && 3 <= appTaxon.apps.size()) {
                    bundle = new Bundle();
                    bundle.putString(WebConstants.BUNDLE_TITLE_KEY, appTaxon.apps.get(2).name);
                    bundle.putString(WebConstants.BUNDLE_URL_KEY, appTaxon.apps.get(2).website);
                    QsHelper.getInstance().intent2Activity(WebViewActivity.class, bundle);
                }
                break;
        }
    }
}