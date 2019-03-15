package com.zl.dappore.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.mvp.QsViewPagerActivity;
import com.qsmaxmin.qsbase.mvp.model.QsModelPager;
import com.zl.dappore.R;
import com.zl.dappore.common.event.HomeEvent;
import com.zl.dappore.common.utils.KeyboardHelper;
import com.zl.dappore.home.fragment.MessageFragment;
import com.zl.dappore.home.fragment.MainFragment;
import com.zl.dappore.home.fragment.VideoFragment;
import com.zl.dappore.home.fragment.MineFragment;
import com.zl.dappore.home.model.HomeConstants;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class HomeActivity extends QsViewPagerActivity<HomePresenter> implements CategoryTypeI{


    @Override public void initData(Bundle bundle) {
        getViewPager().setCanScroll(false);
    }

    @Override public QsModelPager[] getModelPagers() {
        QsModelPager mainPager = createViewPagerModel(HomeConstants.INDEX_HOME);
        mainPager.fragment = MainFragment.getInstance();

        QsModelPager findPager = createViewPagerModel(HomeConstants.INDEX_FIND);
        findPager.fragment = MessageFragment.getInstance();

        QsModelPager mediaPager = createViewPagerModel(HomeConstants.INDEX_MEDIA);
        mediaPager.fragment = VideoFragment.getInstance();

        QsModelPager minePager = createViewPagerModel(HomeConstants.INDEX_MINE);
        minePager.fragment = MineFragment.getInstance();

        return new QsModelPager[]{mainPager, findPager, mediaPager, minePager};
    }


    private QsModelPager createViewPagerModel(int position) {
        QsModelPager viewPagerModel = new QsModelPager();
        viewPagerModel.position = position;
        viewPagerModel.title = QsHelper.getInstance().getString(HomeConstants.NAME_TABS[position]);
        viewPagerModel.iconDefault = HomeConstants.ICON_TABS_DEFAULT[position];
        viewPagerModel.icon = HomeConstants.ICON_TABS_SELECTED[position];
        return viewPagerModel;
    }


    @Override public void initTab(View view, QsModelPager modelPager) {
        //选中-设置图片
        ImageView iv_tab_selected = (ImageView) view.findViewById(R.id.iv_tab_selected);
        iv_tab_selected.setImageResource(modelPager.icon);
        //未选中 - 设置图片
        ImageView iv_tab_unselected = (ImageView) view.findViewById(R.id.iv_tab_unselected);
        iv_tab_unselected.setImageResource(modelPager.iconDefault);
        //选中 - 设置标题
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
            final View iv_tab_selected = view.findViewById(R.id.iv_tab_selected);
            final View iv_tab_unselected = view.findViewById(R.id.iv_tab_unselected);
            final TextView tv_tab = (TextView) view.findViewById(R.id.tv_tab);

            if (iv_tab_selected != null && iv_tab_unselected != null && tv_tab != null) {
                if (isSelected) {
                    tv_tab.setTextColor(getResources().getColor(R.color.color_green_title));
                    iv_tab_selected.setVisibility(View.VISIBLE);
                    iv_tab_unselected.setVisibility(View.GONE);
                } else {
                    tv_tab.setTextColor(getResources().getColor(R.color.color_black_title));
                    iv_tab_selected.setVisibility(View.GONE);
                    iv_tab_unselected.setVisibility(View.VISIBLE);
                }
            }
        }
    }


    /**
     * viewPager滑动事件
     */
    @Override public void onPageSelected(View current, View old, int currentPosition, int oldPosition) {
        super.onPageSelected(current, old, currentPosition, oldPosition);
        if (old != null) {
            setTabsIcon(old, false);
        }
        if (current != null) {
            setTabsIcon(current, true);
        }
    }

    @Override public int getTabItemLayout() {
        return R.layout.item_home_tab;
    }

    @Override
    protected void onResume() {
        super.onResume();
        KeyboardHelper.hideSoftInputFromWindow(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
     public void onEvent(HomeEvent.OnScrollViewpager event) {
        setIndex(event.index, false);
    }

    @Override public boolean isOpenEventBus() {
        return true;
    }

    @Override
    public void onCategoryTypeSelect(String type) {
        getViewPager().setCurrentItem(0);
    }
}
