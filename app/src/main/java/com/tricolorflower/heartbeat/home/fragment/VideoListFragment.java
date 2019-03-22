package com.tricolorflower.heartbeat.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsPullRecyclerFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.home.adapter.VideoRecyclerAdapterItem;
import com.tricolorflower.heartbeat.home.model.GridItemVideo;
import com.tricolorflower.heartbeat.home.model.HomeConstants;
import com.tricolorflower.heartbeat.home.presenter.VideoListPresenter;


public class VideoListFragment extends QsPullRecyclerFragment<VideoListPresenter, GridItemVideo> {

    private String categoryType = HomeConstants.CATEGORY_TYPE_DEFAULT;
    private String sortType = "";

    public static VideoListFragment getInstance(Bundle bundle) {
        VideoListFragment fragment = new VideoListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        sortType = getString(R.string.sort_type_new);
        Bundle bundle = getArguments();
        categoryType = bundle.getString(HomeConstants.BUNDLE_KEY_VIDEO_CATEGORY_REQUEST_ID, HomeConstants.CATEGORY_TYPE_DEFAULT);
        sortType = bundle.getString(HomeConstants.BUNDLE_KEY_CATEGORY_REQUEST_SORT_TYPE, getString(R.string.sort_type_new));

        closePullRefreshing();
        requstVideoList(false);

        getRecyclerView().setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        getRecyclerView().setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                L.i(initTag(), " onScrollStateChanged " + " newState " + newState);

//                if (getRecyclerView().getLayoutManager() != null && getRecyclerView().getLayoutManager() instanceof LinearLayoutManager) {
//                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getRecyclerView().getLayoutManager();
//                    if (linearLayoutManager.findFirstVisibleItemPosition() == 0) {
////                        openPullRefreshing();
//                    }
//                    AgoraLog.i(initTag(), " onScrollStateChanged " + linearLayoutManager.findFirstVisibleItemPosition());
//
//                    if (getParentFragment() != null && getParentFragment() instanceof VideoFragment) {
//                        VideoFragment videoFragment = (VideoFragment) getParentFragment();
//                        if (linearLayoutManager.findFirstVisibleItemPosition() == 0 && videoFragment.isToolbarVisible() && !canPullRefreshing()) {
//                            openPullRefreshing();
//                        }
//                        AgoraLog.i(initTag(), " onScrollStateChanged " + linearLayoutManager.findFirstVisibleItemPosition() + " " + videoFragment.isToolbarVisible());
//                    }
//                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                L.i(initTag(), " onScrolled " + " dx " + dx + " dy " + dy);

////                if (getParentFragment() instanceof VideoFragment) {
////                    VideoFragment videoFragment = (VideoFragment) getParentFragment();
////                    if (dy > 0) {
////                        videoFragment.onTitleShow(false);
////                    } else {
////                        videoFragment.onTitleShow(true);
////                    }
////                }
//
//                if (dy > 0) {
//
//                } else {
////                    openPullRefreshing();
//                    closePullRefreshing();
//                }

            }
        });
    }

    private void requstVideoList(boolean isLoadingMore) {
        if (sortType.equals(getString(R.string.sort_type_new))) {
            getPresenter().requestVideoListByNew(isLoadingMore, categoryType, "1");
        } else if (sortType.equals(getString(R.string.sort_type_hot))) {
            getPresenter().requestVideoListByHot(isLoadingMore, categoryType, "1");
        } else {
            getPresenter().requestVideoListByScore(isLoadingMore, categoryType, "1");
        }
    }

    @Override
    public void onRefresh() {
        requstVideoList(false);
    }

    @Override
    public void onLoad() {
        requstVideoList(true);
    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        return new VideoRecyclerAdapterItem(mInflater, parent);
    }

    @Override
    protected void setViewState(int showState) {
        super.setViewState(showState);
    }

}