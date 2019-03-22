package com.tricolorflower.heartbeat.videodetail.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dinuscxj.progressbar.CircleProgressBar;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.dialog.share.ShareDialog;
import com.tricolorflower.heartbeat.common.dialog.share.ShareResultI;
import com.tricolorflower.heartbeat.common.event.LoginEvent;
import com.tricolorflower.heartbeat.common.event.UserInfoEvent;
import com.tricolorflower.heartbeat.common.event.VideoDetailEvent;
import com.tricolorflower.heartbeat.common.widget.LoveRelativeLayout;
import com.tricolorflower.heartbeat.common.widget.toast.StyleableToast;
import com.tricolorflower.heartbeat.home.model.HomeConstants;
import com.tricolorflower.heartbeat.videodetail.model.Author;
import com.tricolorflower.heartbeat.videodetail.model.RewardResponse;
import com.tricolorflower.heartbeat.videodetail.model.Video;
import com.tricolorflower.heartbeat.videodetail.model.VideoDetailConstants;
import com.tricolorflower.heartbeat.videodetail.presenter.VideoDetailFragmentPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class VideoDetailFragment extends QsFragment<VideoDetailFragmentPresenter> implements OnLikeListener, LoveRelativeLayout.GestureI {
    private static final String TAG2 = "2ideoDetailFragmen2";

    @Bind(R.id.iv_logo_video_detail)
    AppCompatImageView ivLogoVideoDetail;
    @Bind(R.id.iv_watch_video_detail)
    AppCompatImageView ivWatchVideoDetail;
    @Bind(R.id.rl_logo_video_detail)
    RelativeLayout rlLogoVideoDetail;
    @Bind(R.id.tv_praise_video_detail)
    TextView tvPraiseVideoDetail;
    @Bind(R.id.iv_commentinfo_video_detail)
    AppCompatImageView ivCommentinfoVideoDetail;
    @Bind(R.id.tv_commentinfo_video_detail)
    TextView tvCommentinfoVideoDetail;
    @Bind(R.id.rl_commentinfo_video_detail)
    RelativeLayout rlCommentinfoVideoDetail;
    @Bind(R.id.iv_share_video_detail)
    AppCompatImageView ivShareVideoDetail;
    @Bind(R.id.tv_share_video_detail)
    TextView tvShareVideoDetail;
    @Bind(R.id.rl_share_video_detail)
    RelativeLayout rlShareVideoDetail;
    @Bind(R.id.tv_title_video_detail)
    TextView tvTitleVideoDetail;
    @Bind(R.id.tv_des_video_detail)
    TextView tvDesVideoDetail;
    @Bind(R.id.test)
    TextView test;
    @Bind(R.id.test2)
    TextView test2;
    @Bind(R.id.btn_like_video_detail)
    LikeButton btnLikeVideoDetail;
    @Bind(R.id.pb_gift_video_detail)
    CircleProgressBar pbGiftVideoDetail;
    @Bind(R.id.iv_gift_video_detail)
    AppCompatImageView ivGiftVideoDetail;
    @Bind(R.id.rl_gift_video_detail)
    RelativeLayout rlGiftVideoDetail;
    @Bind(R.id.iv_comment_award_video_detail)
    AppCompatImageView ivCommentAwardVideoDetail;
    @Bind(R.id.iv_share_award_video_detail)
    AppCompatImageView ivShareAwardVideoDetail;
    @Bind(R.id.ll_love_video_detail)
    LoveRelativeLayout llLoveVideoDetail;

    Video video;
    int position = 0;
    String videoPath = "";

    public VideoDetailFragment() {
    }

    public static Fragment getInstance(Bundle bundle) {
        VideoDetailFragment fragment = new VideoDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_video_detail;
    }

    @Override
    public void initData(Bundle bundle) {
        position = (int) getArguments().getInt(VideoDetailConstants.BUNDLE_KEY_VIDEODETAIL_REQUEST_POSITION, 0);
        video = (Video) getArguments().getSerializable(VideoDetailConstants.BUNDLE_KEY_VIDEODETAIL_REQUEST_VIDEO);
        if (video == null) {
            video = new Video();
        }
        if (video.media == null) {
            video.media = new Video.Media();
        }
        if (video.author == null) {
            video.author = new Author();
        }
        if (video != null && video.media != null) {
            videoPath = video.media.url;
        }

        L.i(initTag(), " initData getArguments " + getArguments());

        L.i(initTag(), " initData position " + position + " videoPath " + videoPath + " " + video);

//        fragment.setVideoPlayerI(this);
        setView(video);
        requestVideoDetail();
        showContentView();
    }

    private void requestVideoDetail() {
        if (video == null)
            return;

        getPresenter().requestVideoDetail(video.id);
    }

    public String getTitle() {
        if (video != null) {
            return video.title;
        }

        return "";
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setView(Video video) {
        this.video = new Video();
        this.video = video;
        L.i(initTag(), " setView video " + video);
//        if (video == null || video.author == null)
//            return;

        test.setText(position + "");
        test2.setText(position + "");
        if (video != null) {
            setCollectionView(video.starred);
            if (video.author != null) {
                QsHelper.getInstance().getImageHelper().createRequest().load(video.author.avatarUrl).circleCrop().placeholder(R.mipmap.ic_logo_default).into(ivLogoVideoDetail);
                tvTitleVideoDetail.setText(video.author.name);
                L.i(initTag(), " setView video.author.name " + video.author.name);
            }
            btnLikeVideoDetail.setLiked(video.liked);
            btnLikeVideoDetail.setOnLikeListener(this);
            llLoveVideoDetail.setGestureI(this);
            tvPraiseVideoDetail.setText(video.likedCount + "");
            tvCommentinfoVideoDetail.setText(video.commentsCount + "");
//            tvPraiseVideoDetail.setText(video.viewCount + "");
            tvDesVideoDetail.setText(video.title);
            setAwardView(video.rewardable, video.viewed);
//            setAwardView(true, false);
        }

        refreshPersonalInfo(video, position);
        refreshVideoDetailListByLogin(isLogout2login);
    }

    private boolean isLogout2login = false;

    public boolean isLogout2login() {
        return isLogout2login;
    }

    public void setLogout2login(boolean logout2login) {
        isLogout2login = logout2login;
    }

    private void refreshVideoDetailListByLogin(boolean isLogout2login) {
        L.i(initTag(), " setDataByLogin setVideoDetailListByLogin isLogout2login " + isLogout2login);
        if (isLogout2login) {
            setLogout2login(false);
            if (getVideoDetailListFragment() != null) {
                getVideoDetailListFragment().refreshDataByLogin(video);
            }
        }
    }

    private VideoDetailListFragment getVideoDetailListFragment() {
        if (getParentFragment() != null && getParentFragment() instanceof VideoDetailListFragment) {
            return (VideoDetailListFragment) getParentFragment();
        }

        return null;
    }

    private int getPagerPosition() {
        if (getVideoDetailListFragment() != null) {
            return getVideoDetailListFragment().getCurrentItem();
        }
        return 0;
    }

    private void refreshPersonalInfo(Video video, int position) {
        if (video == null || position != getPagerPosition())
            return;

        if (video.author != null) {
            if (video.author.name == null) {
                video.author.name = position + "";
            }
        }
        QsHelper.getInstance().eventPost(new UserInfoEvent.OnPersonalInfoEvent(UserInfoEvent.OnPersonalInfoEvent.STATE_REFRESH, video.author));
    }

    public void refreshPersonalInfo() {
        refreshPersonalInfo(video, position);
    }

    private void setAwardView(boolean rewardable, boolean viewed) {
        if (rewardable) {
            if (viewed) {
                rlGiftVideoDetail.setVisibility(View.GONE);
                ivCommentAwardVideoDetail.setVisibility(View.VISIBLE);
                ivShareAwardVideoDetail.setVisibility(View.VISIBLE);
            } else {
                rlGiftVideoDetail.setVisibility(View.VISIBLE);
                ivCommentAwardVideoDetail.setVisibility(View.GONE);
                ivShareAwardVideoDetail.setVisibility(View.GONE);
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.view_rotate);
                ivGiftVideoDetail.startAnimation(animation);
            }
        } else {
            rlGiftVideoDetail.setVisibility(View.GONE);
            ivCommentAwardVideoDetail.setVisibility(View.GONE);
            ivShareAwardVideoDetail.setVisibility(View.GONE);
        }
    }


    public void setVideoDetailViewed(boolean viewed) {
        if (viewed) {
            requestVideoDetail();
        } else {
            setView(video);
        }
    }

    private void requestVideoDetailViewed() {
        L.i(initTag(), " requestVideoDetailViewed " + video);
        if (video == null)
            return;

        getPresenter().requestVideoDetailViewed(video.id);
    }

    @Override
    public boolean isOpenEventBus() {
        return true;
    }

    @Override
    @OnClick({R.id.rl_play_video_detail, R.id.ll_love_video_detail, R.id.iv_logo_video_detail, R.id.iv_watch_video_detail, R.id.rl_logo_video_detail, R.id.rl_commentinfo_video_detail, R.id.rl_share_video_detail})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.rl_play_video_detail:
                L.i(initTag(), " onViewClick rl_play_video_detail ");
                break;
            case R.id.rl_info_video_detail:
                L.i(initTag(), " onViewClick rl_info_video_detail ");
                break;
            case R.id.ll_love_video_detail:
                L.i(initTag(), " onViewClick ll_love_video_detail ");
                break;
            case R.id.iv_logo_video_detail:
            case R.id.iv_watch_video_detail:
            case R.id.rl_logo_video_detail:
                if (getParentFragment() != null && getParentFragment() instanceof VideoDetailListFragment) {
                    ((VideoDetailListFragment) getParentFragment()).showPersonInfo();
                }
                break;
            case R.id.rl_commentinfo_video_detail:
                if (video != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(VideoDetailConstants.BUNDLE_KEY_VIDEODETAIL_REQUEST_ID, video.id);
                    VideoCommentSheetFragment fragment = (VideoCommentSheetFragment) VideoCommentSheetFragment.getInstance(bundle);
                    fragment.show(getFragmentManager(), "VideoCommentSheetFragment");
                    L.i(initTag(), " VideoCommentSheetFragment " + bundle);
                }
                break;
            case R.id.rl_share_video_detail:
                ShareDialog shareDialog = ShareDialog.getInstance(getContext());
                shareDialog.setShareResultI(new ShareResultI() {
                    @Override
                    public void onSuccess() {
                        L.i(initTag(), " ShareDialog onSuccess ");
                        requestRewardByShare();
                    }

                    @Override
                    public void onFail() {
                        L.i(initTag(), " ShareDialog onFail ");
                    }
                });
                shareDialog.show();
                break;
            default:
                break;
        }
    }


    private void requestRewardByShare() {
        L.i(initTag(), " requestRewardByShare " + video);
        if (video == null)
            return;

        getPresenter().requestRewardByShare(video.id);
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setShareRewardAndRefreshData(RewardResponse.Reward reward) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setShareReward(reward);
            }
        }, 20);
        requestVideoDetail();
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setShareReward(RewardResponse.Reward reward) {
//        if (reward == null)
//            return;
//
//        StyleableToast.show("视频分享", "+" + reward.amount, QsHelper.getInstance().getDrawable(R.mipmap.ic_money));

        if (reward == null) {
            StyleableToast.show("视频分享", "+reward.amount", QsHelper.getInstance().getDrawable(R.mipmap.ic_money));
        } else {
            StyleableToast.show("视频分享", "+" + reward.amount, QsHelper.getInstance().getDrawable(R.mipmap.ic_money));
        }
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setCommentReward(RewardResponse.Reward reward) {
        if (reward == null)
            return;

        StyleableToast.show("视频留言", "+" + reward.amount, QsHelper.getInstance().getDrawable(R.mipmap.ic_money));

//        if (reward == null) {
//            StyleableToast.show("视频留言", "+reward.amount", QsHelper.getInstance().getDrawable(R.mipmap.ic_money));
//        } else {
//            StyleableToast.show("视频留言", "+" + reward.amount, QsHelper.getInstance().getDrawable(R.mipmap.ic_money));
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        L.i(TAG2, " onResume " + position + " " + videoPath);
    }

    @Override
    public void onPause() {
        super.onPause();
        L.i(TAG2, " onPause " + position + " " + videoPath);
    }

    @Override
    public void liked(LikeButton likeButton) {
        requstePraise(true);
    }

    @Override
    public void unLiked(LikeButton likeButton) {
        requstePraise(false);
    }

    @Override
    public void onDoubleTap() {
        requstePraise(true);
    }

    private boolean enablePraise = true;

    public void enablePraise(boolean enable) {
        enablePraise = enable;
    }

    public void requstePraise(boolean praise) {
        if (video == null)
            return;
        L.i(initTag(), " requstePraise " + video);

        if (getPresenter().checkLogin(HomeConstants.REQUESTCODE_LOGIN_FAVORITE)) {
            if (!enablePraise) {
                QsToast.show("请稍候...");
                return;
            }

            L.i(initTag(), " requestPraise " + video);

            enablePraise = false;
            if (praise) {
                getPresenter().requestPraise(video.id);
            } else {
                getPresenter().requestNoPraise(video.id);
            }
        }
    }

    public void setPraiseViewAndRefreshData(boolean praise) {
        setPraiseView(praise);
        requestVideoDetail();
    }

    public void setPraiseView(boolean praise) {
        btnLikeVideoDetail.setLiked(praise);
    }


    @ThreadPoint(ThreadType.MAIN)
    public void requestCollections() {
        L.i(initTag(), " btn_favor requestCollections " + video);
        if (video == null)
            return;

        if (video.starred) {
            getPresenter().requestCancelCollections(video.id);
        } else {
            getPresenter().requestComfirmCollections(video.id);
        }
    }

    @ThreadPoint(ThreadType.MAIN)
    public void enableCollection(boolean enable) {
        if (getParentFragment() instanceof VideoDetailListFragment) {
            ((VideoDetailListFragment) getParentFragment()).enableCollection(enable);
        }
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setCollectionViewAndRefreshData(boolean isCollection) {
        setCollectionView(isCollection);
        requestVideoDetail();
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setCollectionView(boolean isCollection) {
        if (getParentFragment() instanceof VideoDetailListFragment) {
            ((VideoDetailListFragment) getParentFragment()).setCollectionView(isCollection);
        }
    }

    public static Handler handler = new Handler();

    @Subscribe
    public void onEvent(VideoDetailEvent.onUserEvent event) {
        if (event == null)
            return;

        switch (event.state) {
            case COMMENT:
                requestVideoDetail();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setCommentReward(event.reward);
                    }
                }, 20);
                break;
        }
    }

    @Subscribe
    public void onEvent(VideoDetailEvent.onPageSelectEvent event) {
        if (event == null)
            return;

        L.i(TAG2, " onEvent VideoDetailEvent.onPageSelectEvent " + event.toString() + " position " + position);
        if (video != null && video.media != null) {
            L.i(TAG2, " onEvent VideoDetailEvent.onPageSelectEvent " + video.media.url + " position " + position);
        }
    }

    @Subscribe
    public void onEvent(VideoDetailEvent event) {
        if (event == null)
            return;

        L.i(TAG2, " onEvent VideoDetailEvent " + event.toString() + " position " + position);
        if (video != null && video.media != null) {
            L.i(TAG2, " onEvent VideoDetailEvent " + video.media.url + " position " + position);
        }
        if (event.state == VideoDetailEvent.State.STATE_REFRESH) {
            requestVideoDetail();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LoginEvent.onLogin event) {
        L.i(initTag(), " onEvent LoginEvent.onLogin " + event);
        if (event == null)
            return;

        if (event.mLoginState == LoginEvent.LoginState.STATE_SUCCESS || event.mLoginState == LoginEvent.LoginState.STATE_LOGOUT) {
            requestVideoDetail();
            setLogout2login(true);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
