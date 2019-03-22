package com.tricolorflower.heartbeat.videodetail.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsPullRecyclerFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.account.LoginActivity;
import com.tricolorflower.heartbeat.account.model.LoginConstant;
import com.tricolorflower.heartbeat.comment.UserCommentI;
import com.tricolorflower.heartbeat.comment.model.Comment;
import com.tricolorflower.heartbeat.common.event.LoginEvent;
import com.tricolorflower.heartbeat.common.event.VideoDetailEvent;
import com.tricolorflower.heartbeat.common.model.UserConfig;
import com.tricolorflower.heartbeat.common.utils.KeyboardHelper;
import com.tricolorflower.heartbeat.home.model.HomeConstants;
import com.tricolorflower.heartbeat.videodetail.adapter.VideoCommentListAdapterItem;
import com.tricolorflower.heartbeat.videodetail.model.CommentRequstBody;
import com.tricolorflower.heartbeat.videodetail.model.RewardResponse;
import com.tricolorflower.heartbeat.videodetail.model.VideoDetailConstants;
import com.tricolorflower.heartbeat.videodetail.presenter.VideoCommentListPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class VideoCommentListFragment extends QsPullRecyclerFragment<VideoCommentListPresenter, Comment> implements UserCommentI, TextWatcher, ViewTreeObserver.OnGlobalLayoutListener {
    public static final String TAG = "VideoCommentListFragment";

    @Bind(R.id.et_show_video_comment)
    EditText etShowVideoComment;
    @Bind(R.id.iv_show_video_comment)
    ImageView ivShowVideoComment;
    @Bind(R.id.rl_show_video_comment)
    RelativeLayout rlShowVideoComment;
    @Bind(R.id.tv_hint_video_comment)
    TextView tvHintVideoComment;

    String id;

    public static Fragment getInstance(Bundle extras) {
        VideoCommentListFragment fragment = new VideoCommentListFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_video_comment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments == null) return;
        id = arguments.getString(VideoDetailConstants.BUNDLE_KEY_VIDEODETAIL_REQUEST_ID);
        L.i(initTag(), " id " + id);
//        id = "3245";

        showContentView();
        requestCommentList(false);

        etShowVideoComment.addTextChangedListener(this);
        etShowVideoComment.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
//        etShowVideoComment.getViewTreeObserver().addOnGlobalLayoutListener(null);
    }

    private void requestCommentList(boolean isLoadingMore) {
        getPresenter().requestCommentList(isLoadingMore, id);
    }

    @Override
    public void onRefresh() {
        requestCommentList(false);
    }

    @Override
    public void onLoad() {
        requestCommentList(true);
    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        return new VideoCommentListAdapterItem(mInflater, parent, this);
    }

    public void requestCommentList(String content) {
        CommentRequstBody body = new CommentRequstBody(content);
        getPresenter().requestCommentList(id, body);
    }

    @ThreadPoint(ThreadType.MAIN)
    @Override
    public void onPraise(int pos, Comment data) {
        L.i(initTag(), " -onFavorite  " + pos + " " + data);
        if (data == null || data.commenter == null)
            return;
        L.i(initTag(), " onFavorite-  " + pos + " " + data);
        if (getPresenter().checkLogin(HomeConstants.REQUESTCODE_LOGIN_FAVORITE)) {
            if (!enablePraise) {
                QsToast.show("请稍候...");
                return;
            }
            L.i(initTag(), " requestFavorite " + data);

            enablePraise = false;

            if (data.liked) {
                getPresenter().requestNoFavorite(pos, data.id);
            } else {
                getPresenter().requestFavorite(pos, data.id);
            }
        }
    }

    private boolean enablePraise = true;

    public void enablePraise(boolean enable) {
        enablePraise = enable;
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setFavoriteSuccess(int pos) {
        if (getRecyclerView() != null && getData() != null && getData().size() > pos) {
            getData().get(pos).liked = true;
            getData().get(pos).likedCount += 1;
            getRecyclerView().getAdapter().notifyItemChanged(pos);
        }
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setNoFavoriteSuccess(int pos) {
        if (getRecyclerView() != null && getData() != null && getData().size() > pos) {
            getData().get(pos).liked = false;
            getData().get(pos).likedCount -= 1;
            getRecyclerView().getAdapter().notifyItemChanged(pos);
        }
    }

    @OnClick({R.id.tv_hint_video_comment, R.id.iv_show_video_comment, R.id.rl_show_video_comment})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.tv_hint_video_comment:
                if (UserConfig.getInstance().isLogin()) {
                    KeyboardHelper.showSoftInput(etShowVideoComment);
                } else {
                    getPresenter().checkLogin(HomeConstants.REQUESTCODE_LOGIN_COMMENT);
                }
                break;
            case R.id.iv_show_video_comment:
                if (getPresenter().checkLogin(HomeConstants.REQUESTCODE_LOGIN_COMMENT)) {

                    if (TextUtils.isEmpty(etShowVideoComment.getText().toString())) {
                        QsToast.show("请输入评论！");
                        return;
                    }

                    if (etShowVideoComment.length() > 1000) {
                        QsToast.show("评论内容超100字！");
                        return;
                    }

                    CommentRequstBody body = new CommentRequstBody(etShowVideoComment.getText().toString());
                    getPresenter().requestCommentList(id, body);
                }
                break;
            case R.id.rl_show_video_comment:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LoginEvent.onLogin event) {
        L.i(initTag(), " onEvent LoginEvent.onLogin " + event);
        if (event == null)
            return;

        KeyboardHelper.hideSoftInput(getActivity());
    }

    @Override
    public boolean canPullRefreshing() {
        return false;
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setCommentSuccess(RewardResponse.Reward reward) {
        KeyboardHelper.hideSoftInput(getActivity());
        if (getParentFragment() instanceof VideoCommentSheetFragment) {
            ((VideoCommentSheetFragment) getParentFragment()).dismiss();
        }

        QsHelper.getInstance().eventPost(new VideoDetailEvent.onUserEvent(VideoDetailEvent.onUserEvent.State.COMMENT, reward));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (etShowVideoComment.length() > 1000) {
            QsToast.show(getString(R.string.alert_maxlength));
        }
    }

    @Override
    public void onGlobalLayout() {
        if (getActivity() == null || getActivity().getWindow() == null)
            return;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int[] position = new int[2];
                etShowVideoComment.getLocationInWindow(position);
                int screenHeight = getActivity().getWindow().getDecorView().getRootView().getHeight();
                int keyboardHeight = screenHeight - position[1];

                if (keyboardHeight < screenHeight / 4) {
                    tvHintVideoComment.setVisibility(View.VISIBLE);
                    rlShowVideoComment.setVisibility(View.GONE);
                } else {
                    tvHintVideoComment.setVisibility(View.GONE);
                    rlShowVideoComment.setVisibility(View.VISIBLE);
                    etShowVideoComment.requestFocus();
                    etShowVideoComment.setText("");
                    etShowVideoComment.setHint("有爱评论，说点儿好听的～");
                }
            }
        }, 80);

    }

    public static Handler handler = new Handler();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacksAndMessages(null);
    }
}