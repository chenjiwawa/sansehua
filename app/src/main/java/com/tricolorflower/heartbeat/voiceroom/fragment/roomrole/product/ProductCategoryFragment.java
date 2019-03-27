package com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.product;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.fragment.QsViewPagerFragment;
import com.qsmaxmin.qsbase.mvp.model.QsModelPager;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.utils.CommonUtils;
import com.tricolorflower.heartbeat.home.model.HomeConstants;
import com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.emoji.EmojiGridFragment;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.ProductList;
import com.tricolorflower.heartbeat.voiceroom.presenter.ProductCategoryPresenter;

/**
 * 字工场原创字体
 *
 * @CreateBy qsmaxmin
 * @Date 16/8/2
 * @Description
 */
public class ProductCategoryFragment extends QsViewPagerFragment<ProductCategoryPresenter> {

    @Bind(R.id.rl_tab_recommend)
    RelativeLayout rl_tab_recommend;
    @Bind(R.id.rl_tab_rank)
    RelativeLayout rl_tab_rank;
    @Bind(R.id.rl_tab_category)
    RelativeLayout rl_tab_category;

    ProductPagerFragment giftProductPagerFragment;

    public static ProductCategoryFragment getInstance(Bundle bundle) {
        ProductCategoryFragment fragment = new ProductCategoryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_product_category;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        initViewPager(getModelPagers(), 1);

        showContentView();
    }


    public QsModelPager[] getModelPagers() {
        QsModelPager modelRecommend = createModelPager(HomeConstants.INDEX_RECOMMEND);
        giftProductPagerFragment = (ProductPagerFragment) ProductPagerFragment.getInstance(getArguments());
        modelRecommend.fragment = giftProductPagerFragment;

        QsModelPager modelRank = createModelPager(HomeConstants.INDEX_RANK);
        modelRank.fragment = (ProductPagerFragment) ProductPagerFragment.getInstance(getArguments());

        QsModelPager modelCategory = createModelPager(HomeConstants.INDEX_CATEGORY);
        modelCategory.fragment = (ProductPagerFragment) ProductPagerFragment.getInstance(getArguments());

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
        return R.layout.item_product_category_tab;
    }

    /**
     * 点击事件
     */
    @OnClick({R.id.rl_tab_recommend, R.id.rl_tab_rank, R.id.rl_tab_category})
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
        }
    }

    public ProductList.Product getSelectGiftProduct() {
        if (giftProductPagerFragment != null) {
            return giftProductPagerFragment.getSelectProduct();
        }

        return null;
    }


}
