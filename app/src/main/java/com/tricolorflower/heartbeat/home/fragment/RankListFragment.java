package com.tricolorflower.heartbeat.home.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.percentlayout.PercentLinearLayout;
import com.qsmaxmin.qsbase.common.widget.percentlayout.PercentRelativeLayout;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsPullRecyclerFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.appdetail.AppDetailActivity;
import com.tricolorflower.heartbeat.appdetail.model.App;
import com.tricolorflower.heartbeat.appdetail.model.AppDetailConstants;
import com.tricolorflower.heartbeat.common.utils.CommonUtils;
import com.tricolorflower.heartbeat.common.widget.shadow.ShadowDrawable;
import com.tricolorflower.heartbeat.home.adapter.AppRecyclerAdapterItem;
import com.tricolorflower.heartbeat.home.model.HomeConstants;
import com.tricolorflower.heartbeat.home.presenter.RankListPresenter;
import com.tricolorflower.heartbeat.web.WebViewActivity;
import com.tricolorflower.heartbeat.web.model.WebConstants;


public class RankListFragment extends QsPullRecyclerFragment<RankListPresenter, App> {

    @Bind(R.id.iv_name_rank_item2)
    ImageView ivNameRankItem2;
    @Bind(R.id.tv_name_rank_item2)
    TextView tvNameRankItem2;
    @Bind(R.id.tv_des_rank_item2)
    TextView tvDesRankItem2;
    @Bind(R.id.tv_download_rank_item2)
    TextView tvDownloadRankItem2;
    @Bind(R.id.rl_rank_item2)
    PercentLinearLayout rlRankItem2;
    @Bind(R.id.iv_name_rank_item1)
    ImageView ivNameRankItem1;
    @Bind(R.id.tv_name_rank_item1)
    TextView tvNameRankItem1;
    @Bind(R.id.tv_des_rank_item1)
    TextView tvDesRankItem1;
    @Bind(R.id.tv_download_rank_item1)
    TextView tvDownloadRankItem1;
    @Bind(R.id.rl_rank_item1)
    PercentLinearLayout rlRankItem1;
    @Bind(R.id.iv_name_rank_item3)
    ImageView ivNameRankItem3;
    @Bind(R.id.tv_name_rank_item3)
    TextView tvNameRankItem3;
    @Bind(R.id.tv_des_rank_item3)
    TextView tvDesRankItem3;
    @Bind(R.id.tv_download_rank_item3)
    TextView tvDownloadRankItem3;
    @Bind(R.id.rl_rank_item3)
    PercentLinearLayout rlRankItem3;
    @Bind(R.id.layout_header_ranklist)
    PercentRelativeLayout layoutHeaderRanklist;
    private String categoryType = HomeConstants.CATEGORY_TYPE_DEFAULT;
    private int index = HomeConstants.INDEX_HOT;
    private App app1;
    private App app2;
    private App app3;

    public static RankListFragment getInstance(Bundle bundle) {
        RankListFragment fragment = new RankListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getHeaderLayout() {
        return R.layout.header_ranklist;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        categoryType = bundle.getString(HomeConstants.BUNDLE_KEY_CATEGORY_REQUEST_ID, HomeConstants.CATEGORY_TYPE_DEFAULT);
        index = bundle.getInt(HomeConstants.BUNDLE_KEY_RANK_REQUEST_INDEX, HomeConstants.INDEX_HOT);

        requstAppList(false);
//        getPresenter().requestRankAppList(false, categoryType, sortType);

    }


    private void requstAppList(boolean isLoadingMore) {
        switch (index) {
            case HomeConstants.INDEX_HOT:
                getPresenter().requestRankAppListByPosition(isLoadingMore, "1");
                break;
            case HomeConstants.INDEX_NEW:
                getPresenter().requestRankAppListByNew(isLoadingMore, "1");
                break;
            case HomeConstants.INDEX_DAPP:
                getPresenter().requestRankAppListByScore(isLoadingMore, "1");
                break;
        }
    }

    @Override
    public void onRefresh() {
        requstAppList(false);
    }

    @Override
    public void onLoad() {
        requstAppList(true);
    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        return new AppRecyclerAdapterItem(mInflater, parent, true);
    }

    @Override
    protected void setViewState(int showState) {
        super.setViewState(showState);
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setRankItem1(App data) {
        if (data == null)
            return;
        app1 = data;

        rlRankItem1.setVisibility(View.VISIBLE);
        ShadowDrawable.setShadowDrawable(rlRankItem1, Color.parseColor("#ffffff"), 0,
                R.color.color_shadow_gray,
                CommonUtils.dp2px(5), new int[]{CommonUtils.dp2px(3), CommonUtils.dp2px(5), CommonUtils.dp2px(3), CommonUtils.dp2px(0)}, 0, 0);
        QsHelper.getInstance().getImageHelper().createRequest().load(data.logoUrl).circleCrop().into(ivNameRankItem1);
        tvNameRankItem1.setText(data.name);
        tvDesRankItem1.setText(data.description);
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setRankItem2(App data) {
        if (data == null)
            return;
        app2 = data;

        rlRankItem2.setVisibility(View.VISIBLE);
        ShadowDrawable.setShadowDrawable(rlRankItem2, Color.parseColor("#ffffff"), 0,
                R.color.color_shadow_gray,
                CommonUtils.dp2px(5), new int[]{CommonUtils.dp2px(5), CommonUtils.dp2px(5), CommonUtils.dp2px(0), CommonUtils.dp2px(0)}, 0, 0);
        QsHelper.getInstance().getImageHelper().createRequest().load(data.logoUrl).circleCrop().into(ivNameRankItem2);
        tvNameRankItem2.setText(data.name);
        tvDesRankItem2.setText(data.description);
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setRankItem3(App data) {
        if (data == null)
            return;
        app3 = data;

        rlRankItem3.setVisibility(View.VISIBLE);
        ShadowDrawable.setShadowDrawable(rlRankItem3, Color.parseColor("#ffffff"), 0,
                R.color.color_shadow_gray,
                CommonUtils.dp2px(5), new int[]{CommonUtils.dp2px(0), CommonUtils.dp2px(5), CommonUtils.dp2px(5), CommonUtils.dp2px(0)}, 0, 0);
        QsHelper.getInstance().getImageHelper().createRequest().load(data.logoUrl).circleCrop().into(ivNameRankItem3);
        tvNameRankItem3.setText(data.name);
        tvDesRankItem3.setText(data.description);
    }

    @OnClick({R.id.rl_rank_item1, R.id.rl_rank_item2, R.id.rl_rank_item3,R.id.tv_download_rank_item1,R.id.tv_download_rank_item2,R.id.tv_download_rank_item3})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.rl_rank_item1:
                if (app1 != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(AppDetailConstants.BUNDLE_KEY_APPDETAIL_REQUEST_ID, app1.id);
                    QsHelper.getInstance().intent2Activity(AppDetailActivity.class, bundle);
                }
                break;
            case R.id.rl_rank_item2:
                if (app2 != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(AppDetailConstants.BUNDLE_KEY_APPDETAIL_REQUEST_ID, app2.id);
                    QsHelper.getInstance().intent2Activity(AppDetailActivity.class, bundle);
                }
                break;
            case R.id.rl_rank_item3:
                if (app3 != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(AppDetailConstants.BUNDLE_KEY_APPDETAIL_REQUEST_ID, app2.id);
                    QsHelper.getInstance().intent2Activity(AppDetailActivity.class, bundle);
                }
                break;
            case R.id.tv_download_rank_item1:
                if (app1 != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(WebConstants.BUNDLE_TITLE_KEY, app1.name);
                    bundle.putString(WebConstants.BUNDLE_URL_KEY, app1.website);
                    QsHelper.getInstance().intent2Activity(WebViewActivity.class, bundle);
                }
                break;
            case R.id.tv_download_rank_item2:
                if (app2 != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(WebConstants.BUNDLE_TITLE_KEY, app2.name);
                    bundle.putString(WebConstants.BUNDLE_URL_KEY, app2.website);
                    QsHelper.getInstance().intent2Activity(WebViewActivity.class, bundle);
                }
                break;
            case R.id.tv_download_rank_item3:
                if (app3 != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(WebConstants.BUNDLE_TITLE_KEY, app3.name);
                    bundle.putString(WebConstants.BUNDLE_URL_KEY, app3.website);
                    QsHelper.getInstance().intent2Activity(WebViewActivity.class, bundle);
                }
                break;
        }
    }

    @Override
    public boolean canPullRefreshing() {
        return true;
    }

    @Override
    public boolean canPullLoading() {
        return true;
    }
}