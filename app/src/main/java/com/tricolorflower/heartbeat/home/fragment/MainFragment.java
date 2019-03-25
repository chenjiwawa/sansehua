package com.tricolorflower.heartbeat.home.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.fragment.QsViewPagerFragment;
import com.qsmaxmin.qsbase.mvp.model.QsModelPager;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.utils.CommonUtils;
import com.tricolorflower.heartbeat.home.CategoryTypeI;
import com.tricolorflower.heartbeat.home.model.HomeConstants;
import com.tricolorflower.heartbeat.home.presenter.MainFragmentPresenter;
import com.tricolorflower.heartbeat.voiceroom.VoiceRoomActivity;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.VoiceAdminOperationDialogFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.VoiceGuestOperationDialogFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.VoiceHolderOperationDialogFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.VoiceRoleOperationDialogFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.voiceclient.VoiceAdmin2ClientOperationDialogFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.voiceclient.VoiceHolder2ClientOperationDialogFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.voiceempty.VoiceAdmin2EmptyOperationDialogFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.voiceempty.VoiceHolder2EmptyOperationDialogFragment;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;

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

    Bundle bundle;

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
    @OnClick({R.id.rl_tab_recommend, R.id.rl_tab_rank, R.id.rl_tab_category, R.id.btn_search0, R.id.btn_search})
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
            case R.id.btn_search0:
                //TODO
//                QsHelper.getInstance().intent2Activity(SearcherActivity.class);


//                VoiceRoleOperationDialogFragment.getInstance(null).show();

//                VoiceHolderOperationDialogFragment.getInstance(null).show();

//                VoiceGuestOperationDialogFragment.getInstance(null).show();

//                VoiceAdmin2ClientOperationDialogFragment.getInstance(null).show();

//                VoiceAdmin2EmptyOperationDialogFragment.getInstance(null).show();


                bundle = new Bundle();
                bundle.putString(VoiceRoomConstants.BUNDLE_KEY_REQUEST_USER_ID, "1");
                bundle.putString(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROOM_ID, "1");
                QsHelper.getInstance().intent2Activity(VoiceRoomActivity.class, bundle);

//                Bundle bundle = new Bundle();
//                QsHelper.getInstance().intent2Activity(ProductCategoryActivity.class, bundle);

//                Bundle bundle = new Bundle();
//                bundle.putString(RecommendListConstants.BUNDLE_KEY_RECOMMENDLIST_REQUEST_NAME, "同类推荐");
//                bundle.putInt(RecommendListConstants.BUNDLE_KEY_RECOMMENDLIST_REQUEST_TYPE, RecommendListConstants.TYPE_SIMILAR);
//                QsHelper.getInstance().intent2Activity(OnlineListActivity.class, bundle);

                break;
            case R.id.btn_search:
                //TODO
//                QsHelper.getInstance().intent2Activity(SearcherActivity.class);

//                VoiceAdminOperationDialogFragment.getInstance(null).show();

//                VoiceAdmin2ClientOperationDialogFragment.getInstance(null).show();

//                VoiceHolder2EmptyOperationDialogFragment.getInstance(null).show();

                bundle = new Bundle();
                bundle.putString(VoiceRoomConstants.BUNDLE_KEY_REQUEST_USER_ID, "11");
                bundle.putString(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROOM_ID, "1");
                QsHelper.getInstance().intent2Activity(VoiceRoomActivity.class, bundle);

//                Bundle bundle = new Bundle();
//                QsHelper.getInstance().intent2Activity(ProductCategoryActivity.class, bundle);

//                Bundle bundle = new Bundle();
//                bundle.putString(RecommendListConstants.BUNDLE_KEY_RECOMMENDLIST_REQUEST_NAME, "同类推荐");
//                bundle.putInt(RecommendListConstants.BUNDLE_KEY_RECOMMENDLIST_REQUEST_TYPE, RecommendListConstants.TYPE_SIMILAR);
//                QsHelper.getInstance().intent2Activity(OnlineListActivity.class, bundle);

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
