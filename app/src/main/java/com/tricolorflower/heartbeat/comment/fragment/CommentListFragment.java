package com.tricolorflower.heartbeat.comment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsPullRecyclerFragment;
import com.tricolorflower.heartbeat.comment.CommentI;
import com.tricolorflower.heartbeat.comment.UserCommentI;
import com.tricolorflower.heartbeat.comment.adapter.CommentListRecyclerAdapterItem;
import com.tricolorflower.heartbeat.comment.model.Comment;
import com.tricolorflower.heartbeat.comment.model.CommentConstants;
import com.tricolorflower.heartbeat.comment.model.CommentRequstBody;
import com.tricolorflower.heartbeat.comment.presenter.CommentListPresenter;


public class CommentListFragment extends QsPullRecyclerFragment<CommentListPresenter, Comment> implements UserCommentI {

    String id;

    public static Fragment getInstance(Bundle extras) {
        CommentListFragment fragment = new CommentListFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    CommentI commentI;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null) {
            commentI = (CommentI) context;
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments == null) return;
        id = arguments.getString(CommentConstants.BUNDLE_KEY_COMMENT_REQUEST_ID);
        L.i(initTag(), " id " + id);
//        id = "3245";

        requestCommentList(false);
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
        return new CommentListRecyclerAdapterItem(mInflater, parent, this);
    }

    public void requestCommentList(String content, String star_count) {
        CommentRequstBody body = new CommentRequstBody(content, star_count);
        getPresenter().requestCommentList(id, body);
    }

    @ThreadPoint(ThreadType.MAIN)
    @Override
    public void onPraise(int pos, Comment data) {
        if (data == null || data.commenter == null)
            return;
        L.i(initTag(), " onFavorite " + data);
        if (!enablePraise) {
            QsToast.show("请稍候...");
            return;
        }
        L.i(initTag(), " requestFavorite " + data);

        enablePraise = false;
        getPresenter().requestFavorite(pos, data.id);
    }

    private boolean enablePraise = true;

    public void enablePraise(boolean enable) {
        enablePraise = enable;
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setCommentSuccess() {
        if (commentI != null) {
            commentI.hideComment();
        }
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setFavoriteSuccess(int pos) {
        if (getRecyclerView() != null && getData() != null && getData().size() > pos) {
            getData().get(pos).liked = true;
            getData().get(pos).likedCount += 1;
            getRecyclerView().getAdapter().notifyItemChanged(pos);
        }
    }
}