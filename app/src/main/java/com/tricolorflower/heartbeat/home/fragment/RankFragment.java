package com.tricolorflower.heartbeat.home.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.RelativeLayout;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.home.model.HomeConstants;
import com.tricolorflower.heartbeat.home.presenter.RankPresenter;

import java.util.ArrayList;

import static com.tricolorflower.heartbeat.home.model.HomeConstants.TITLE_TABS_RANK;

/**
 * 字工场原创字体
 *
 * @CreateBy qsmaxmin
 * @Date 16/8/2
 * @Description
 */
public class RankFragment extends QsFragment<RankPresenter> implements OnTabSelectListener {
    @Bind(R.id.tl_rank)
    SegmentTabLayout tl_rank;
    @Bind(R.id.rl_content_rank)
    RelativeLayout rl_content_rank;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    public int layoutId() {
        return R.layout.fragment_rank;
    }


    public static RankFragment getInstance() {
        return new RankFragment();
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        setConentFragments();
        tl_rank.setTabData(TITLE_TABS_RANK, getActivity(), R.id.rl_content_rank, mFragments);
        tl_rank.setOnTabSelectListener(this);
        showContentView();
    }

    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public void onTabReselect(int position) {

    }

    private void setConentFragments() {

        Bundle hotBundle = new Bundle();
        hotBundle.putInt(HomeConstants.BUNDLE_KEY_RANK_REQUEST_INDEX, HomeConstants.INDEX_HOT);
        mFragments.add(RankListFragment.getInstance(hotBundle));

        Bundle newBundle = new Bundle();
        newBundle.putInt(HomeConstants.BUNDLE_KEY_RANK_REQUEST_INDEX, HomeConstants.INDEX_NEW);
        mFragments.add(RankListFragment.getInstance(newBundle));

        Bundle dappBundle = new Bundle();
        dappBundle.putInt(HomeConstants.BUNDLE_KEY_RANK_REQUEST_INDEX, HomeConstants.INDEX_DAPP);
        mFragments.add(RankListFragment.getInstance(dappBundle));
    }
}
