package com.zl.dappore.home.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.viewpager.autoscroll.AutoScrollViewPager;
import com.qsmaxmin.qsbase.common.widget.viewpager.autoscroll.CirclePageIndicator;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsPullRecyclerFragment;
import com.zl.dappore.R;
import com.zl.dappore.appdetail.model.App;
import com.zl.dappore.common.utils.CommonUtils;
import com.zl.dappore.common.widget.shadow.ShadowDrawable;
import com.zl.dappore.common.widget.transformer.ScalePageTransformer;
import com.zl.dappore.home.CategoryTypeI;
import com.zl.dappore.home.adapter.PosterInfinitePageAdapter;
import com.zl.dappore.home.adapter.RecommendAppAdapter;
import com.zl.dappore.home.adapter.RecommendCategoryAdapterItem;
import com.zl.dappore.home.model.PosterList;
import com.zl.dappore.home.presenter.RecommendPresenter;
import com.zl.dappore.recommendlist.RecommendListActivity;
import com.zl.dappore.recommendlist.model.RecommendListConstants;

import java.util.ArrayList;
import java.util.List;


public class RecommendFragment extends QsPullRecyclerFragment<RecommendPresenter, App.AppTaxon> {

    /*header*/
    @Bind(R.id.rl_head_recommend)
    RelativeLayout rl_head_recommend;
    @Bind(R.id.asvp_banner)
    AutoScrollViewPager asvp_banner;
    @Bind(R.id.iv_banner_bg)
    ImageView iv_banner_bg;
    @Bind(R.id.icpi_indicator)
    CirclePageIndicator icpi_indicator;
    @Bind(R.id.rl_banner)
    RelativeLayout rlBanner;
    @Bind(R.id.rv_recommend_app)
    RecyclerView rvRecommendApp;
    @Bind(R.id.rl_recommend)
    RelativeLayout rlRecommend;

    public static Fragment getInstance() {
        return new RecommendFragment();
    }

    @Override
    public int getHeaderLayout() {
        return R.layout.header_recommend;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        loading("加载中...");
        initRecommendView();

        getPresenter().requestPosterList();
        getPresenter().requestAppTaxonsList();
        getPresenter().requestAppListByNew();
    }

    @Override
    public boolean isOpenViewState() {
        return false;
    }

    @Override
    public void onRefresh() {
        getPresenter().requestPosterList();
        getPresenter().requestAppTaxonsList();
        getPresenter().requestAppListByNew();
    }

    @Override
    public void onLoad() {
    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        return new RecommendCategoryAdapterItem(layoutInflater, viewGroup, categoryTypeI);
    }

    public void updateBanner(PosterList posterList) {

        /*轮播图*/
        if (posterList != null && posterList.posters != null) {
            ArrayList<String> arrayList = new ArrayList<>();
            for (PosterList.Poster model : posterList.posters) {
                arrayList.add(model.imageUrl);
            }
            setBannerData(arrayList);
        }
    }

    @ThreadPoint(ThreadType.MAIN)
    private void setBannerData(ArrayList<String> urls) {
        if (urls == null || urls.size() <= 0) {
            return;
        }

        rlBanner.setVisibility(View.VISIBLE);
        PosterInfinitePageAdapter infiniteAdapter = new PosterInfinitePageAdapter();
        infiniteAdapter.addData(urls);// 添加数据
        infiniteAdapter.setPlaceholder(R.mipmap.bg_default);
//        infiniteAdapter.setiInfiniteStatePagerAdapter(this);// 增加点击事件
        if (urls.size() <= 1) {
            infiniteAdapter.enableInfinite(false);
        } else {
            infiniteAdapter.enableInfinite(true);
        }
        asvp_banner.setAdapter(infiniteAdapter);
        asvp_banner.setPageTransformer(true, new ScalePageTransformer());
        asvp_banner.setOffscreenPageLimit(2);
        asvp_banner.setInterval(3000);// 轮播速度-毫秒
        asvp_banner.setAutoScrollDurationFactor(6);
        icpi_indicator.setViewPager(asvp_banner);// 交给indicator
        icpi_indicator.notifyDataSetChanged();// 刷新
        startBanner();
        iv_banner_bg.setVisibility(View.GONE);
    }

    @Override
    public void onPause() {
        super.onPause();
        stopBanner();
    }

    @Override
    public void onResume() {
        super.onResume();
        startBanner();
    }


    private void stopBanner() {
        L.i(initTag(), "stopBanner");
        if (asvp_banner != null) {
            asvp_banner.stopAutoScroll();
        }
    }

    private void startBanner() {
        L.i(initTag(), "startBanner");
        if (asvp_banner != null) {
            asvp_banner.startAutoScroll();
        }
    }

    @ThreadPoint(ThreadType.MAIN)
    public void initRecommendView() {

        ShadowDrawable.setShadowDrawable(rlRecommend, Color.parseColor("#ffffff"), 0,
                R.color.color_shadow_gray,
                CommonUtils.dp2px(5), new int[]{CommonUtils.dp2px(5), CommonUtils.dp2px(0), CommonUtils.dp2px(5), CommonUtils.dp2px(5)}, 0, 0);
        rlRecommend.setVisibility(View.VISIBLE);
        rvRecommendApp.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        L.i(initTag(), " setRecommendList " + rvRecommendApp + " " + layoutManager);
        rvRecommendApp.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider_list_6));
        rvRecommendApp.addItemDecoration(dividerItemDecoration);
        rvRecommendApp.setAdapter(new RecommendAppAdapter(new ArrayList<>()));
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setRecommendList(List<App> apps) {
        if (apps == null)
            return;
        L.i(initTag(), "setRecommendList " + apps.toString());
        L.i(initTag(), "setRecommendList " + apps.size());

        rvRecommendApp.setAdapter(new RecommendAppAdapter(apps));
    }

    @ThreadPoint(ThreadType.MAIN)
    public void hideRecommendList() {
        rlRecommend.setVisibility(View.GONE);
        rvRecommendApp.setVisibility(View.GONE);
    }

    @ThreadPoint(ThreadType.MAIN)
    public void hideBanners() {
        rlBanner.setVisibility(View.GONE);
    }

    private CategoryTypeI categoryTypeI;

    public void setCategoryTypeI(CategoryTypeI categoryTypeI) {
        this.categoryTypeI = categoryTypeI;

        L.i(initTag(), "CategoryTypeI ");
    }

    @OnClick({R.id.rl_recommend})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.rl_recommend:
                Bundle bundle = new Bundle();
                bundle.putString(RecommendListConstants.BUNDLE_KEY_RECOMMENDLIST_REQUEST_NAME, "最新推荐");
                bundle.putInt(RecommendListConstants.BUNDLE_KEY_RECOMMENDLIST_REQUEST_TYPE, RecommendListConstants.TYPE_NEW);
                QsHelper.getInstance().intent2Activity(RecommendListActivity.class, bundle);
                break;
        }
    }
}
