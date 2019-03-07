package com.zl.dappore.videodetail.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zl.dappore.R;
import com.zl.dappore.account.LoginActivity;
import com.zl.dappore.account.model.LoginConstant;
import com.zl.dappore.common.event.UserInfoEvent;
import com.zl.dappore.common.event.VideoDetailEvent;
import com.zl.dappore.common.event.VideoPlayerEvent;
import com.zl.dappore.common.model.UserConfig;
import com.zl.dappore.common.widget.toast.StyleableToast;
import com.zl.dappore.common.widget.verticalviewpager.VerticalViewPager;
import com.zl.dappore.home.model.HomeConstants;
import com.zl.dappore.videodetail.adapter.VideoDetailListFragmentAdapter;
import com.zl.dappore.videodetail.model.Author;
import com.zl.dappore.videodetail.model.Video;
import com.zl.dappore.videodetail.model.VideoDetailConstants;
import com.zl.dappore.videodetail.presenter.VideoDetailListPresenter;

import java.util.ArrayList;
import java.util.List;

public class VideoDetailListFragment extends QsFragment<VideoDetailListPresenter> implements OnRefreshListener, OnLoadMoreListener {

    @Bind(R.id.vvp_video_details)
    VerticalViewPager vvpVideoDetails;
    @Bind(R.id.rl_show_content_video_details)
    RelativeLayout rlShowContentVideoDetails;
    @Bind(R.id.rl_data_content_video_detail)
    RelativeLayout rlDataContentVideoDetail;
    @Bind(R.id.srl_video_detail)
    SmartRefreshLayout srlVideoDetail;
    @Bind(R.id.rl_video_detail)
    RelativeLayout rlVideoDetail;
    @Bind(R.id.btn_back)
    Button btnBack;
    @Bind(R.id.btn_favor)
    Button btnFavor;

    VideoDetailListFragmentAdapter adapter;
    List<Video> data = new ArrayList<>();
    Video video;
    private int pageNo = 0;
    private int pageSize = 3;

    public static Fragment getInstance(Bundle bundle) {
        VideoDetailListFragment fragment = new VideoDetailListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int layoutId() {
        return R.layout.fragment_video_detail_list;
    }

    @Override
    public void initData(Bundle bundle) {
        video = (Video) getArguments().getSerializable(VideoDetailConstants.BUNDLE_KEY_VIDEODETAIL_REQUEST_VIDEO);
        requestVideoList(false);
        showContentView();
        vvpVideoDetails.setPageMargin(0);
        vvpVideoDetails.setOffscreenPageLimit(2);
        srlVideoDetail.setOnRefreshListener(this);
        srlVideoDetail.setOnLoadMoreListener(this);
    }

    @Override
    @ThreadPoint(ThreadType.MAIN)
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        L.i(initTag(), " onRefresh ");
        requestVideoList(false);
    }

    @Override
    @ThreadPoint(ThreadType.MAIN)
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        L.i(initTag(), " onLoadMore ");
        requestVideoList(true);
    }

    public void setPageNo(int page) {
        this.pageNo = page;
    }

    private void requestVideoList(boolean isLoadingMore) {
        if (video == null)
            return;

        requestVideoList(isLoadingMore, video.id);
    }

    private void requestVideoList(boolean isLoadingMore, String id) {
        QsHelper.getInstance().eventPost(new VideoPlayerEvent(VideoPlayerEvent.State.STATE_STOP));
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getPresenter().requestVideoListByPage(isLoadingMore, id, "33", "", pageNo, pageSize);
            }
        }, 80);
    }

    @ThreadPoint(ThreadType.MAIN)
    public void refreshDataByLogin(Video currentVideo) {
        if (currentVideo == null)
            return;
        QsHelper.getInstance().eventPost(new VideoPlayerEvent(VideoPlayerEvent.State.STATE_STOP));

        L.i(initTag(), " setDataByLogin " + currentVideo);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                requestVideoList(false, currentVideo.id);
            }
        }, 80);
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setData(List<Video> videos) {
        L.i(initTag(), " setData videos " + videos);

        srlVideoDetail.finishRefresh();
        addBeforeViewPager(videos, pageNo);
        setShowView();

        setCurrentVideoPlay(vvpVideoDetails.getCurrentItem());
    }

    public int getCurrentItem() {
        if (vvpVideoDetails != null && vvpVideoDetails.getAdapter() != null && vvpVideoDetails.getAdapter().getCount() > 0) {
            return vvpVideoDetails.getCurrentItem();
        }

        return 0;
    }

    public VideoDetailFragment getCurrentFragment() {
        if (vvpVideoDetails != null && vvpVideoDetails.getAdapter() != null && vvpVideoDetails.getAdapter().getCount() > 0) {
            return (VideoDetailFragment) ((VideoDetailListFragmentAdapter) (vvpVideoDetails.getAdapter())).getItem(getCurrentItem());
        }

        return null;
    }

    @ThreadPoint(ThreadType.MAIN)
    public void addData(List<Video> videos) {
        L.i(initTag(), " addData videos " + videos);

        srlVideoDetail.finishLoadMore();
        addAfterViewPager(videos);
        setShowView();

        setCurrentVideoPlay(vvpVideoDetails.getCurrentItem());
    }

    private Handler handler = new Handler();

    private void setCurrentVideoPlay(int current) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                QsHelper.getInstance().eventPost(new VideoDetailEvent.onPageSelectEvent(current));
            }
        }, 100);
    }

    @ThreadPoint(ThreadType.MAIN)
    private void setShowView() {
        L.i(initTag(), " setShowView ");

        srlVideoDetail.setVisibility(View.GONE);
        rlShowContentVideoDetails.setVisibility(View.VISIBLE);

        rlShowContentVideoDetails.removeAllViews();
        rlDataContentVideoDetail.removeAllViews();
        rlShowContentVideoDetails.addView(vvpVideoDetails);
    }

    @ThreadPoint(ThreadType.MAIN)
    private void setRefreshView() {
        L.i(initTag(), " setRefreshView ");
        if (isTouch) {
            srlVideoDetail.setVisibility(View.VISIBLE);
            rlShowContentVideoDetails.setVisibility(View.GONE);

            rlShowContentVideoDetails.removeAllViews();
            rlDataContentVideoDetail.removeAllViews();
            rlDataContentVideoDetail.addView(vvpVideoDetails);

            srlVideoDetail.setEnableRefresh(true);
            srlVideoDetail.setEnableAutoLoadMore(false);
            srlVideoDetail.autoRefresh();
        }
    }

    @ThreadPoint(ThreadType.MAIN)
    private void setLoadMoreView() {
        L.i(initTag(), " setLoadMoreView ");
        if (isTouch) {
            srlVideoDetail.setVisibility(View.VISIBLE);
            rlShowContentVideoDetails.setVisibility(View.GONE);

            rlShowContentVideoDetails.removeAllViews();
            rlDataContentVideoDetail.removeAllViews();
            rlDataContentVideoDetail.addView(vvpVideoDetails);

            srlVideoDetail.setEnableRefresh(false);
            srlVideoDetail.setEnableAutoLoadMore(true);
            srlVideoDetail.autoLoadMore();
        }
    }

    int curPage = 0;

    boolean isTouch = false;

    public boolean isTouch() {
        return isTouch;
    }

    public void setTouch(boolean touch) {
        isTouch = touch;
    }

    public void showPersonInfo() {
        if (getParentFragment() != null && getParentFragment() instanceof VideoDetailPagerFragment) {
            ((VideoDetailPagerFragment) getParentFragment()).setPersonInfoPageSelect();
        }
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
////        L.i(initTag(), " dispatchTouchEvent event " + event.toString());
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                isTouch = true;
//                break;
//            case MotionEvent.ACTION_UP:
//                isTouch = false;
//                break;
//
//        }
//        return super.dispatchTouchEvent(event);
//    }

    @ThreadPoint(ThreadType.MAIN)
    public void addBeforeViewPager(List<Video> videos, int currentPage) {
        if (videos == null || videos.size() == 0) {
            return;
        }

        int currentItem = 0;
        List<Video> temp = new ArrayList<>();
        if (currentPage > 0 && data != null && data.size() > 0) {
            temp.addAll(videos);

            currentItem = temp.size();

            for (int i = 0; i < pageSize; i++) {
                if (i < data.size()) {
                    temp.add(data.get(i));
                }
            }
        } else {
            temp.addAll(videos);
        }
        setViewPager(temp, currentItem);
    }

    @ThreadPoint(ThreadType.MAIN)
    public void addAfterViewPager(List<Video> videos) {
        if (videos == null || videos.size() == 0) {
            return;
        }

        int currentItem = 0;
        List<Video> temp = new ArrayList<>();
        if (data != null && data.size() > 0) {
            for (int i = 0; i < pageSize; i++) {
                if (i < data.size()) {
                    temp.add(0, data.get(data.size() - 1 - i));
                }
            }
            if (temp.size() > 0) {
                currentItem = temp.size() - 1;
            }
            temp.addAll(videos);
        } else {
            temp.addAll(videos);
        }
        setViewPager(temp, currentItem);
    }

    @ThreadPoint(ThreadType.MAIN)
    private void setViewPager(List<Video> videos, int currentItem) {
        L.i(initTag(), " setViewPager videos " + videos);

        if (videos == null || videos.size() == 0) {
            showEmptyView();
            return;
        }

        data = new ArrayList<>();
        data.addAll(videos);
        showContentView();
        //viewPager.setPageTransformer(false, new ZoomOutTransformer());
        //viewPager.setPageTransformer(true, new StackTransformer());
        String title = "VideoDetailFragment";
        VideoDetailListFragmentAdapter.Holder holder = new VideoDetailListFragmentAdapter.Holder(getChildFragmentManager());
        for (int i = 0; i < videos.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(VideoDetailConstants.BUNDLE_KEY_VIDEODETAIL_REQUEST_VIDEO, videos.get(i));
            bundle.putInt(VideoDetailConstants.BUNDLE_KEY_VIDEODETAIL_REQUEST_POSITION, i);
            holder.add(VideoDetailFragment.getInstance(bundle));
        }

        L.i(initTag(), " setViewPager holder " + holder.getFragments().toString());
        adapter = holder.set();
        vvpVideoDetails.setAdapter(adapter);
        //If you setting other scroll mode, the scrolled fade is shown from either side of display.
        vvpVideoDetails.setOverScrollMode(View.OVER_SCROLL_NEVER);
        vvpVideoDetails.setCurrentItem(currentItem);

        vvpVideoDetails.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
//                L.i(initTag(), " onPageScrolled i " + i + " v " + v + " i1 " + i1 + " curPage " + curPage);
                if (curPage == 0 && i1 == 0) {
                    L.i(initTag(), " onPageScrolled 上 ");
                    setRefreshView();
                }

                if (curPage + 1 == vvpVideoDetails.getAdapter().getCount() && i1 == 0) {
                    L.i(initTag(), " onPageScrolled 下 ");
                    setLoadMoreView();
                }
            }

            @Override
            public void onPageSelected(int i) {
                curPage = i;
                L.i(initTag(), " onPageSelected i " + i);
                setShowView();

                //控制视频
                setCurrentVideoPlay(i);

                if (getCurrentFragment() != null) {
                    getCurrentFragment().refreshPersonalInfo();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                L.i(initTag(), " onPageScrollStateChanged i " + i);
            }
        });
    }

    private boolean enableCollection = true;

    public void enableCollection(boolean enable) {
        enableCollection = enable;
    }

    public void setCollectionView(boolean isCollection) {
        if (isCollection) {
            btnFavor.setBackgroundResource(R.mipmap.ic_favor_select);
        } else {
            btnFavor.setBackgroundResource(R.mipmap.ic_favor_normal);
        }
    }

    @Override
    @OnClick({R.id.btn_back, R.id.btn_favor})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.btn_back:
                StyleableToast.show("视频留言", "+10", QsHelper.getInstance().getDrawable(R.mipmap.ic_money));
                activityFinish();
                break;
            case R.id.btn_favor:
                if (getPresenter().checkLogin(HomeConstants.REQUESTCODE_LOGIN_COLLECTION)) {
                    if (!enableCollection) {
                        QsToast.show("请稍候...");
                        return;
                    }

                    L.i(initTag(), " btn_favor ");

                    if (adapter != null && adapter.getCount() > 0) {
                        Fragment fragment = adapter.getItem(vvpVideoDetails.getCurrentItem());

                        L.i(initTag(), " btn_favor " + adapter.getCount() + " " + fragment);
                        if (fragment instanceof VideoDetailFragment) {
                            ((VideoDetailFragment) fragment).requestCollections();
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacksAndMessages(null);
    }
}
