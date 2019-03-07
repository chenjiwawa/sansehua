package com.zl.dappore.videodetail.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.mvp.fragment.QsViewPagerFragment;
import com.qsmaxmin.qsbase.mvp.model.QsModelPager;
import com.zl.dappore.R;
import com.zl.dappore.home.CategoryTypeI;
import com.zl.dappore.home.model.HomeConstants;
import com.zl.dappore.userinfo.fragment.PersonalInfoFragment;
import com.zl.dappore.userinfo.model.PersonalConstants;
import com.zl.dappore.videodetail.model.VideoDetailConstants;

/**
 * 字工场原创字体
 *
 * @CreateBy qsmaxmin
 * @Date 16/8/2
 * @Description
 */
public class VideoDetailPagerFragment extends QsViewPagerFragment implements CategoryTypeI, ViewPager.OnPageChangeListener {

    @Override
    public int layoutId() {
        return R.layout.fragment_video_detail_pager;
    }

    public static Fragment getInstance(Bundle bundle) {
        VideoDetailPagerFragment fragment = new VideoDetailPagerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setTouch(boolean touch) {
        if (videoDetailListFragment != null) {
            videoDetailListFragment.setTouch(touch);
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        L.i(initTag(), " initData " + getArguments());

        initViewPager(getModelPagers(), 1);
        getViewPager().setCanScroll(true);
        getViewPager().setPageMargin(0);
        getViewPager().addOnPageChangeListener(this);

        showContentView();
    }

    VideoDetailListFragment videoDetailListFragment;

    public QsModelPager[] getModelPagers2() {
        QsModelPager detailModelPager = createModelPager(VideoDetailConstants.INDEX_VIDEODETAIL);
        videoDetailListFragment = (VideoDetailListFragment) VideoDetailListFragment.getInstance(getArguments());
        detailModelPager.fragment = videoDetailListFragment;

        return new QsModelPager[]{detailModelPager};
    }

    public QsModelPager[] getModelPagers() {
        QsModelPager detailModelPager = createModelPager(VideoDetailConstants.INDEX_VIDEODETAIL);
        videoDetailListFragment = (VideoDetailListFragment) VideoDetailListFragment.getInstance(getArguments());
        detailModelPager.fragment = videoDetailListFragment;

        QsModelPager authorModelPager = createModelPager(VideoDetailConstants.INDEX_VIDEOAUTHOR);
        Bundle bundle = getArguments();
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putBoolean(PersonalConstants.BUNDLE_KEY_PERSONAL_REQUEST_IS_USER, false);
        PersonalInfoFragment personalInfoFragment = (PersonalInfoFragment) PersonalInfoFragment.getInstance(bundle);
        authorModelPager.fragment = personalInfoFragment;

        return new QsModelPager[]{detailModelPager, authorModelPager};
    }

    private QsModelPager createModelPager(int index) {
        QsModelPager modelPager = new QsModelPager();
        modelPager.position = index;
        modelPager.title = getString(HomeConstants.NAME_TABS_MAIN[index]);
        return modelPager;
    }

    @Override
    public void onCategoryTypeSelect(String type) {
        L.i(initTag(), "CategoryTypeI " + type);
    }

    int curPage = 0;

    @Override
    public void onPageScrolled(int i, float v, int i1) {
//                L.i(initTag(), " onPageScrolled i " + i + " v " + v + " i1 " + i1 + " curPage " + curPage);

        L.i(initTag(), " onPageScrolled getCurrentItem " + getViewPager().getCurrentItem() + " curPage " + curPage);

        if (getViewPager().getCurrentItem() == 0 && i1 == 0) {
            L.i(initTag(), " onPageScrolled 左1 ");
        }

        if (getViewPager().getCurrentItem() + 1 == getViewPager().getAdapter().getCount() && i1 == 0) {
            L.i(initTag(), " onPageScrolled 右1 ");
        }

        if (curPage == 0 && i1 == 0) {
            L.i(initTag(), " onPageScrolled 左 ");
        }

        if (curPage + 1 == getViewPager().getAdapter().getCount() && i1 == 0) {
            L.i(initTag(), " onPageScrolled 右 ");
        }
    }

    @Override
    public void onPageSelected(int i) {
        curPage = i;
        L.i(initTag(), " onPageSelected i " + i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        L.i(initTag(), " onPageScrollStateChanged i " + i);
    }

    public void setPersonInfoPageSelect() {
        getViewPager().setCurrentItem(1);
    }

}
