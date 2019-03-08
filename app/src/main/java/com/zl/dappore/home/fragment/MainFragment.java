package com.zl.dappore.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zl.dappore.R;
import com.zl.dappore.appdetail.AppDetailActivity;
import com.zl.dappore.appdetail.model.AppDetailConstants;
import com.zl.dappore.common.utils.CommonUtils;
import com.zl.dappore.home.CategoryTypeI;
import com.zl.dappore.home.model.HomeConstants;
import com.zl.dappore.home.presenter.MainFragmentPresenter;
import com.zl.dappore.search.SearcherActivity;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.fragment.QsViewPagerFragment;
import com.qsmaxmin.qsbase.mvp.model.QsModelPager;
import com.zl.dappore.voiceroom.VoiceRoomActivity;
import com.zl.dappore.voiceroom.model.VoiceRoomConstants;

/**
 * 字工场原创字体
 *
 * @CreateBy qsmaxmin
 * @Date 16/8/2
 * @Description
 */
public class MainFragment extends QsViewPagerFragment<MainFragmentPresenter> implements CategoryTypeI {

    @Bind(R.id.rl_tab_recommend)
    RelativeLayout rl_tab_recommend;
    @Bind(R.id.rl_tab_rank)
    RelativeLayout rl_tab_rank;
    @Bind(R.id.rl_tab_category)
    RelativeLayout rl_tab_category;

    @Override
    public int layoutId() {
        return R.layout.fragment_main;
    }

    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        initViewPager(getModelPagers(), 1);

        showContentView();
    }


    public QsModelPager[] getModelPagers() {
        QsModelPager modelRecommend = createModelPager(HomeConstants.INDEX_RECOMMEND);
        RecommendFragment recommendFragment = (RecommendFragment) RecommendFragment.getInstance();
        recommendFragment.setCategoryTypeI(this);
        modelRecommend.fragment = recommendFragment;

        QsModelPager modelRank = createModelPager(HomeConstants.INDEX_RANK);
        modelRank.fragment = RankFragment.getInstance();

        QsModelPager modelCategory = createModelPager(HomeConstants.INDEX_CATEGORY);
        modelCategory.fragment = CategoryFragment.getInstance();

        return new QsModelPager[]{modelRecommend, modelRank, modelCategory};
    }


    private QsModelPager createModelPager(int index) {
        QsModelPager modelPager = new QsModelPager();
        modelPager.position = index;
        modelPager.title = getString(HomeConstants.NAME_TABS_MAIN[index]);
        return modelPager;
    }


    @Override
    public int getTabsIndicatorColor() {
        return getResources().getColor(R.color.color_white);
    }

    @Override
    public int getTabsTitleColor() {
        return getResources().getColor(R.color.color_white);
    }

    @Override
    public int getTabsIndicatorMargin() {
        return CommonUtils.dp2px(14);
    }

    @Override
    protected int getTabsBackgroundResource() {
        return android.R.color.transparent;
    }

    @Override
    protected int getTabsSelectedTitleColor() {
        return getResources().getColor(R.color.color_white);
    }

    @Override
    protected float getTabsUnderlineHeight() {
        return 2;
    }

    @Override
    public void initTab(View view, QsModelPager modelPager) {
        TextView tv_tab = (TextView) view.findViewById(R.id.tv_tab);
        if (TextUtils.isEmpty(modelPager.title)) {
            tv_tab.setVisibility(View.GONE);
        } else {
            tv_tab.setText(modelPager.title);
        }

        if (modelPager.position == HomeConstants.INDEX_HOME) {
            setTabsIcon(view, true);
        } else {
            setTabsIcon(view, false);
        }
    }

    private void setTabsIcon(final View view, boolean isSelected) {
        if (view != null) {
            final TextView tv_tab = (TextView) view.findViewById(R.id.tv_tab);

            if (tv_tab != null) {
                if (isSelected) {
                    tv_tab.setTextSize(16);
                } else {
                    tv_tab.setTextSize(13);
                }
            }
        }
    }

    /**
     * viewPager滑动事件
     */
    @Override
    public void onPageSelected(View current, View old, int currentPosition, int oldPosition) {
        super.onPageSelected(current, old, currentPosition, oldPosition);
        if (old != null) {
            setTabsIcon(old, false);
        }
        if (current != null) {
            setTabsIcon(current, true);
        }
    }

    @Override
    public int getTabItemLayout() {
        return R.layout.item_main_tab;
    }

    /**
     * 点击事件
     */
    @OnClick({R.id.rl_tab_recommend, R.id.rl_tab_rank, R.id.rl_tab_category, R.id.btn_search})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.rl_tab_recommend:
                pager.setCurrentItem(0);
                break;
            case R.id.rl_tab_rank:
                pager.setCurrentItem(1);
                break;
            case R.id.rl_tab_category:
                pager.setCurrentItem(2);
                break;
            case R.id.btn_search:
                //TODO
//                QsHelper.getInstance().intent2Activity(SearcherActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_CHANNEL_ID, "111");
                bundle.putInt(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_VOICE_ROLE, 0);
                QsHelper.getInstance().intent2Activity(VoiceRoomActivity.class, bundle);
                break;
        }
    }


    @Override
    public void onCategoryTypeSelect(String type) {
        L.i(initTag(), "CategoryTypeI " + type);

        if (TextUtils.isEmpty(type))
            return;

        if (getViewPagerAdapter().getCount() > 2) {
            pager.setCurrentItem(2);
            if (getViewPagerAdapter().getData(2).fragment != null && getViewPagerAdapter().getData(2).fragment instanceof CategoryFragment) {
                CategoryFragment categoryFragment = (CategoryFragment) getViewPagerAdapter().getData(2).fragment;
                categoryFragment.setCategoryTypeSelect(type);
            }
        }
    }
}
