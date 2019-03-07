package com.zl.dappore.appdetail.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.zl.dappore.R;
import com.zl.dappore.appdetail.fragment.AppCommentI;
import com.zl.dappore.comment.model.Comment;
import com.zl.dappore.common.utils.DateTimeUtils;
import com.zl.dappore.common.widget.BeautyRatingBar;


public class CommentRecyclerAdapterItem extends QsRecycleAdapterItem<Comment> {

    @Bind(R.id.iv_img_comment)
    ImageView ivImgComment;
    @Bind(R.id.tv_title_comment)
    TextView tvTitleComment;
    @Bind(R.id.rb_score_comment)
    BeautyRatingBar rbScoreComment;
    @Bind(R.id.tv_score_comment)
    TextView tvScoreComment;
    @Bind(R.id.tv_time_comment)
    TextView tvTimeComment;
    @Bind(R.id.tv_des_comment)
    TextView tvDesComment;
    @Bind(R.id.tv_like_comment)
    TextView tvLikeComment;
    AppCommentI appCommentI;
    Comment data;

    public CommentRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent, AppCommentI appCommentI) {
        super(inflater, parent);
        this.appCommentI = appCommentI;
    }

    @Override
    protected int itemViewLayoutId() {
        return R.layout.item_comment;
    }

    @Override
    protected void onBindItemData(Comment comment, int i, int i1) {
        if (comment == null || comment.commenter == null)
            return;
        this.data = comment;

        QsHelper.getInstance().getImageHelper().createRequest().load(comment.commenter.avatarUrl).into(ivImgComment);
        tvTitleComment.setText(comment.commenter.name);
        rbScoreComment.setRating(comment.starCount);
        tvScoreComment.setText(comment.score);
        if(TextUtils.isEmpty(comment.createdAt)){
            tvTimeComment.setText("");
        }else {
            tvTimeComment.setText(DateTimeUtils.getDateTime(comment.createdAt));
        }
        tvDesComment.setText(comment.content);
        tvLikeComment.setText(comment.likedCount + "");
        if (data.liked) {
            tvLikeComment.setEnabled(false);
        } else {
            tvLikeComment.setEnabled(true);
        }
    }

    @OnClick({R.id.tv_like_comment, R.id.rl_like_comment})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.rl_like_comment:
            case R.id.tv_like_comment:
                if (appCommentI != null) {
                    appCommentI.onPraise(data);
                }
                break;
        }
    }
}