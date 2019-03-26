package com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.product;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.fragment.QsViewPagerFragment;
import com.qsmaxmin.qsbase.mvp.model.QsModelPager;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.appdetail.model.App;
import com.tricolorflower.heartbeat.common.model.BaseRequstBody;
import com.tricolorflower.heartbeat.common.model.UserConfig;
import com.tricolorflower.heartbeat.common.utils.CommonUtils;
import com.tricolorflower.heartbeat.common.widget.shadow.ShadowDrawable;
import com.tricolorflower.heartbeat.home.model.HomeConstants;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.ProductPageList;
import com.tricolorflower.heartbeat.voiceroom.presenter.ProductPagerPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 字工场原创字体
 *
 * @CreateBy qsmaxmin
 * @Date 16/8/2
 * @Description
 */
public class ProductPagerFragment extends QsViewPagerFragment<ProductPagerPresenter> {

    @Bind(R.id.ll_tabs_category)
    LinearLayout ll_tabs_category;

    public static ProductPagerFragment getInstance(Bundle bundle) {
        ProductPagerFragment fragment = new ProductPagerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_product_pager;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        showContentView();
        requestData();

    }

    private void requestData() {
        String token = UserConfig.getInstance().getAuthToken();
        getPresenter().requstData(new BaseRequstBody(token));
    }

    private QsModelPager createModelPager(int index, String tabName) {
        QsModelPager modelPager = new QsModelPager();
        modelPager.position = index;
        modelPager.title = tabName;
        return modelPager;
    }

    @ThreadPoint(ThreadType.MAIN)
    public void updateViewPager(List<ProductPageList.ProductPage> productPages) {
        if (productPages == null)
            return;

        List<QsModelPager> modelPagers = new ArrayList<>();
        for (int i = 0; i < productPages.size(); i++) {
            QsModelPager model = createModelPager(i, productPages.get(i).pageId + "");
            Bundle bundle = new Bundle();
            bundle.putInt(VoiceRoomConstants.BUNDLE_KEY_PRODUCTLIST_REQUEST_PAGE_NO, i + 1);
            model.fragment = ProductGridFragment.getInstance(bundle);
            modelPagers.add(model);
        }

        initViewPager(modelPagers.toArray(new QsModelPager[0]), 1);
    }

    @Override
    protected int getTabsIndicatorColor() {
        return getResources().getColor(R.color.transparent);
    }

    @Override
    public int getTabsIndicatorMargin() {
        return CommonUtils.dp2px(8);
    }


    @Override
    protected int getTabsSelectedTitleColor() {
        return getResources().getColor(R.color.color_black_title);
    }

    @Override
    protected boolean getTabsShouldExpand() {
        return false;
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
                    tv_tab.setTextSize(13);
                    tv_tab.setTextColor(QsHelper.getInstance().getColor(R.color.color_black_title));
                } else {
                    tv_tab.setTextSize(12);
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

    @OnClick({R.id.tv_sort_type_category})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.tv_sort_type_category:
                L.i(initTag(), " tv_sort_type_category ");
                break;
        }
    }

    @Override
    public QsModelPager[] getModelPagers() {
        return new QsModelPager[0];
    }

    @Override
    public int getTabItemLayout() {
        return R.layout.item_product_pager_tab;
    }

}
