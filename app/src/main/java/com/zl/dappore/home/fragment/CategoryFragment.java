package com.zl.dappore.home.fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import com.zl.dappore.R;
import com.zl.dappore.appdetail.model.App;
import com.zl.dappore.common.dialog.SortTypeDialog;
import com.zl.dappore.common.utils.CommonUtils;
import com.zl.dappore.common.widget.shadow.ShadowDrawable;
import com.zl.dappore.home.model.HomeConstants;
import com.zl.dappore.home.presenter.CategoryPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 字工场原创字体
 *
 * @CreateBy qsmaxmin
 * @Date 16/8/2
 * @Description
 */
public class CategoryFragment extends QsViewPagerFragment<CategoryPresenter> {

    @Bind(R.id.ll_tabs_category)
    LinearLayout ll_tabs_category;
    @Bind(R.id.tv_sort_type_category)
    TextView tvSortTypeCategory;

    private String sortType = "";
    private List<App.AppTaxon> appTaxons;

    @Override
    public int layoutId() {
        return R.layout.fragment_category;
    }

    public static CategoryFragment getInstance() {
        return new CategoryFragment();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        sortType = getString(R.string.sort_type_new);
        ShadowDrawable.setShadowDrawable(ll_tabs_category, Color.parseColor("#ffffff"), 0,
                R.color.color_shadow_gray,
                CommonUtils.dp2px(5), new int[]{CommonUtils.dp2px(0), CommonUtils.dp2px(0), CommonUtils.dp2px(0), CommonUtils.dp2px(5)}, 0, 0);
        showContentView();
        getPresenter().requestAppTaxons();

    }

    private QsModelPager createModelPager(int index, String tabName) {
        QsModelPager modelPager = new QsModelPager();
        modelPager.position = index;
        modelPager.title = tabName;
        return modelPager;
    }

    @ThreadPoint(ThreadType.MAIN)
    public void updateViewPager(List<App.AppTaxon> appTaxons) {
        if (appTaxons == null)
            return;

        this.appTaxons = appTaxons;
        List<QsModelPager> modelPagers = new ArrayList<>();
        for (int i = 0; i < appTaxons.size(); i++) {
            QsModelPager model = createModelPager(i, appTaxons.get(i).name);
            Bundle bundle = new Bundle();
            bundle.putString(HomeConstants.BUNDLE_KEY_CATEGORY_REQUEST_ID, appTaxons.get(i).id);
            bundle.putString(HomeConstants.BUNDLE_KEY_CATEGORY_REQUEST_SORT_TYPE, sortType);
            model.fragment = AppListFragment.getInstance(bundle);
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

                Drawable drawable = getResources().getDrawable(R.mipmap.ic_sort_down);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvSortTypeCategory.setCompoundDrawables(null, null, drawable, null);
                SortTypeDialog sortTypeDialog = SortTypeDialog.getInstance(getContext());
                sortTypeDialog.setSortType(sortType);
                sortTypeDialog.setOnSortTypeSelectedListener(new SortTypeDialog.OnSortTypeSelectedListener() {
                    @Override
                    public void onSortTypeSelected(View view) {
                        if (view instanceof TextView) {
                            TextView textView = (TextView) view;
                            sortType = textView.getText().toString();
                            tvSortTypeCategory.setText(sortType);

                            L.i("SortTypeDialog", " onSortTypeSelected type " + sortType);
                        }
                        getPresenter().requestAppTaxons();
                    }

                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Drawable drawable = getResources().getDrawable(R.mipmap.ic_sort_up);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        tvSortTypeCategory.setCompoundDrawables(null, null, drawable, null);
                    }
                });
                sortTypeDialog.show();
                break;
        }
    }

    @Override
    public QsModelPager[] getModelPagers() {
        return new QsModelPager[0];
    }

    @Override
    public int getTabItemLayout() {
        return R.layout.item_category_tab;
    }

    public void setCategoryTypeSelect(String type) {
        L.i(initTag(), "CategoryTypeI " + type);

        if (TextUtils.isEmpty(type))
            return;

        if (appTaxons != null && appTaxons.size() > 0) {
            for (int i = 0; i < appTaxons.size(); i++) {
                if (appTaxons.get(i).id.equals(type) && pager.getAdapter().getCount() > i) {
                    pager.setCurrentItem(i);
                    break;
                }
            }
        }
    }
}
