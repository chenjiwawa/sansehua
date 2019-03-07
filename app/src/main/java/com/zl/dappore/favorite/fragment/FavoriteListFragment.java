package com.zl.dappore.favorite.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.mvp.fragment.QsViewPagerFragment;
import com.qsmaxmin.qsbase.mvp.model.QsModelPager;
import com.zl.dappore.R;
import com.zl.dappore.common.utils.CommonUtils;
import com.zl.dappore.favorite.model.FavoriteConstants;
import com.zl.dappore.home.model.HomeConstants;
import com.zl.dappore.search.presenter.SearchResultPresenter;

/**
 * 字工场原创字体
 *
 * @CreateBy qsmaxmin
 * @Date 16/8/2
 * @Description
 */
public class FavoriteListFragment extends QsViewPagerFragment<SearchResultPresenter> {

    @Override
    public int layoutId() {
        return R.layout.fragment_favorite;
    }

    public static FavoriteListFragment getInstance() {
        FavoriteListFragment fragment = new FavoriteListFragment();
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        Bundle bundle = getArguments();
        initViewPager(getModelPagers(), 1);
        showContentView();
    }

    @Override
    public void initViewPager(QsModelPager[] modelPagers, int offScreenPageLimit) {
        super.initViewPager(modelPagers, offScreenPageLimit);
    }


    @Override
    public QsModelPager[] getModelPagers() {

        QsModelPager appModel = createModelPager(FavoriteConstants.INDEX_APP);
        appModel.fragment = FavoriteAppListFragment.getInstance();

        QsModelPager mediaModel = createModelPager(FavoriteConstants.INDEX_MEDIA);
        mediaModel.fragment = FavoriteVideoListFragment.getInstance();

        return new QsModelPager[]{appModel, mediaModel};
    }

    @ThreadPoint(ThreadType.MAIN)
    public void updateViewPager() {
        QsModelPager appModel = createModelPager(FavoriteConstants.INDEX_APP);
        appModel.fragment = FavoriteAppListFragment.getInstance();

        QsModelPager mediaModel = createModelPager(FavoriteConstants.INDEX_MEDIA);
        mediaModel.fragment = FavoriteVideoListFragment.getInstance();

        initViewPager(new QsModelPager[]{appModel, mediaModel}, 1);
    }

    private QsModelPager createModelPager(int index) {
        QsModelPager modelPager = new QsModelPager();
        modelPager.position = index;
        modelPager.title = getString(FavoriteConstants.NAME_TABS[index]);
        return modelPager;
    }


    @Override
    public int getTabsIndicatorColor() {
        return getResources().getColor(R.color.color_black_title);
    }

    @Override
    public int getTabsTitleColor() {
        return getResources().getColor(R.color.color_stroke_gray);
    }

    @Override
    public int getTabsIndicatorMargin() {
        return CommonUtils.dp2px(39);
    }

    @Override
    protected float getTabsIndicatorWidth() {
        return CommonUtils.dp2px(39);
    }

    @Override
    protected int getTabsBackgroundResource() {
        return android.R.color.transparent;
    }

    @Override
    protected int getTabsSelectedTitleColor() {
        return getResources().getColor(R.color.color_black_title);
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
                    tv_tab.setTextColor(QsHelper.getInstance().getColor(R.color.color_black_title));
                } else {
                    tv_tab.setTextSize(15);
                    tv_tab.setTextColor(QsHelper.getInstance().getColor(R.color.color_stroke_gray));
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
