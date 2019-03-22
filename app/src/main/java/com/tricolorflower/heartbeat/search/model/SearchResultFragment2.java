package com.tricolorflower.heartbeat.search.model;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.mvp.fragment.QsViewPagerFragment;
import com.qsmaxmin.qsbase.mvp.model.QsModelPager;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.utils.CommonUtils;
import com.tricolorflower.heartbeat.home.model.HomeConstants;
import com.tricolorflower.heartbeat.search.fragment.SearchAppFragment;

/**
 * 字工场原创字体
 *
 * @CreateBy qsmaxmin
 * @Date 16/8/2
 * @Description
 */
public class SearchResultFragment2 extends QsViewPagerFragment<SearchResultPresenter2> {
    private String hotWord = "";

    @Override
    public int layoutId() {
        return R.layout.fragment_search_result;
    }

    public static SearchResultFragment2 getInstance(Bundle bundle) {
        SearchResultFragment2 fragment = new SearchResultFragment2();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        Bundle bundle = getArguments();
        hotWord = bundle.getString(SearchConstants.BUNDLE_KEY_SEARCH_REQUEST_HOT_WORD);
        initViewPager(getModelPagers(), 1);
        showContentView();
    }

    @Override
    public void initViewPager(QsModelPager[] modelPagers, int offScreenPageLimit) {
        super.initViewPager(modelPagers, offScreenPageLimit);
    }

    public QsModelPager[] getModelPagers() {

        QsModelPager appModel = createModelPager(SearchConstants.INDEX_APP_SEARCH);
        Bundle bundle = new Bundle();
        bundle.putString(SearchConstants.BUNDLE_KEY_SEARCH_REQUEST_HOT_WORD, hotWord);
        appModel.fragment = SearchAppFragment.getInstance(bundle);

        QsModelPager mediaModel = createModelPager(SearchConstants.INDEX_MEDIA_SEARCH);
        bundle = new Bundle();
        bundle.putString(SearchConstants.BUNDLE_KEY_SEARCH_REQUEST_HOT_WORD, hotWord);
        mediaModel.fragment = SearchAppFragment.getInstance(bundle);

        return new QsModelPager[]{appModel, mediaModel};
    }

    @ThreadPoint(ThreadType.MAIN)
    public void updateViewPager(String hotWord) {
        QsModelPager appModel = createModelPager(SearchConstants.INDEX_APP_SEARCH);
        Bundle bundle = new Bundle();
        bundle.putString(SearchConstants.BUNDLE_KEY_SEARCH_REQUEST_HOT_WORD, hotWord);
        appModel.fragment = SearchAppFragment.getInstance(bundle);

        QsModelPager mediaModel = createModelPager(SearchConstants.INDEX_MEDIA_SEARCH);
        bundle = new Bundle();
        bundle.putString(SearchConstants.BUNDLE_KEY_SEARCH_REQUEST_HOT_WORD, hotWord);
        mediaModel.fragment = SearchAppFragment.getInstance(bundle);

        initViewPager(new QsModelPager[]{appModel, mediaModel}, 1);
    }

    private QsModelPager createModelPager(int index) {
        QsModelPager modelPager = new QsModelPager();
        modelPager.position = index;
        modelPager.title = getString(SearchConstants.NAME_TABS_SEARCH[index]);
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
        return R.layout.item_search_tab;
    }
}
