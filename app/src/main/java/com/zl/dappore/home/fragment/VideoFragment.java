package com.zl.dappore.home.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.zl.dappore.common.dialog.SortTypeDialog;
import com.zl.dappore.common.event.VideoEditEvent;
import com.zl.dappore.common.utils.CommonUtils;
import com.zl.dappore.common.widget.shadow.ShadowDrawable;
import com.zl.dappore.home.model.HomeConstants;
import com.zl.dappore.home.presenter.VideoPresenter;
import com.zl.dappore.search.SearcherActivity;
import com.zl.dappore.userinfo.UserInfoActivity;
import com.zl.dappore.videodetail.model.Video;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * 字工场原创字体
 *
 * @CreateBy qsmaxmin
 * @Date 16/8/2
 * @Description
 */
public class VideoFragment extends QsViewPagerFragment<VideoPresenter> {

    @Bind(R.id.ll_tabs_category)
    LinearLayout ll_tabs_category;
    @Bind(R.id.tv_sort_type_category)
    TextView tvSortTypeCategory;
    @Bind(R.id.iv_to_video)
    ImageView ivToVideo;
    @Bind(android.R.id.title)
    TextView title;
    @Bind(R.id.iv_search)
    ImageView ivSearch;
    @Bind(R.id.rl_title_video)
    RelativeLayout rlTitleVideo;
    @Bind(R.id.ab_title_video)
    View ab_title_video;

    private String sortType = "";
    private List<Video.VideoTaxon> videoTaxons;


    @Override
    public int layoutId() {
        return R.layout.fragment_video;
    }

    public static VideoFragment getInstance() {
        return new VideoFragment();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        title.setText("推荐");
        sortType = getString(R.string.sort_type_new);
        ShadowDrawable.setShadowDrawable(ll_tabs_category, Color.parseColor("#ffffff"), 0,
                R.color.color_shadow_gray,
                CommonUtils.dp2px(5), new int[]{CommonUtils.dp2px(0), CommonUtils.dp2px(0), CommonUtils.dp2px(0), CommonUtils.dp2px(1)}, 0, 0);
        showContentView();
        getPresenter().requestVideoTaxons();
    }

    private QsModelPager createModelPager(int index, String tabName) {
        QsModelPager modelPager = new QsModelPager();
        modelPager.position = index;
        modelPager.title = tabName;
        return modelPager;
    }

    @ThreadPoint(ThreadType.MAIN)
    public void updateViewPager(List<Video.VideoTaxon> videoTaxons) {
        if (videoTaxons == null)
            return;

        this.videoTaxons = videoTaxons;
        List<QsModelPager> modelPagers = new ArrayList<>();
        for (int i = 0; i < videoTaxons.size(); i++) {
            QsModelPager model = createModelPager(i, videoTaxons.get(i).name);
            Bundle bundle = new Bundle();
            bundle.putString(HomeConstants.BUNDLE_KEY_VIDEO_CATEGORY_REQUEST_ID, videoTaxons.get(i).id);
            bundle.putString(HomeConstants.BUNDLE_KEY_CATEGORY_REQUEST_SORT_TYPE, sortType);
            model.fragment = VideoListFragment.getInstance(bundle);
//            model.fragment = TestFragment.getInstance();
            modelPagers.add(model);
        }

        initViewPager(modelPagers.toArray(new QsModelPager[0]), 1);
    }

    public void onTitleShow(boolean isVisible) {
        L.i(initTag(), " onTitleShow ab_title_video " + ab_title_video + " rlTitleVideo " + rlTitleVideo);

//        if (ab_title_video == null)
//            return;
//
//        L.i(initTag(), " onTitleShow " + isVisible);
//        if (isVisible && ab_title_video.getVisibility() != View.VISIBLE) {
//            ab_title_video.setVisibility(View.VISIBLE);
//        } else {
//            ab_title_video.setVisibility(View.GONE);
//        }
    }

    public boolean isToolbarVisible() {
        if (ab_title_video != null) {
            return ab_title_video.getVisibility() == View.VISIBLE ? true : false;
        }

        return false;
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

    @OnClick({R.id.tv_sort_type_category, R.id.iv_to_video, R.id.iv_search})
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
                        getPresenter().requestVideoTaxons();
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
            case R.id.iv_to_video:
                if (getPresenter().checkLogin(HomeConstants.REQUESTCODE_LOGIN_VIDEOEDIT)) {
                }
                break;
            case R.id.iv_search:
                QsHelper.getInstance().intent2Activity(SearcherActivity.class);
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

    @Subscribe
    public void onEvent(VideoEditEvent event) {
        if (event.mState == VideoEditEvent.State.STATE_PUBLISH) {
            getPresenter().requestVideoTaxons();
        }
    }

}
