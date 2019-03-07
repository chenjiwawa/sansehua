package com.zl.dappore.comment.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.zl.dappore.R;
import com.zl.dappore.comment.UserCommentI;
import com.zl.dappore.comment.model.Comment;
import com.zl.dappore.common.utils.DateTimeUtils;
import com.zl.dappore.common.widget.BeautyRatingBar;
import com.zl.dappore.common.widget.textview.ExpandIconTextView;


public class CommentListRecyclerAdapterItem extends QsRecycleAdapterItem<Comment> {

    @Bind(R.id.iv_img_comment_list)
    ImageView ivImgCommentList;
    @Bind(R.id.tv_title_comment_list)
    TextView tvTitleCommentList;
    @Bind(R.id.rb_score_comment_list)
    BeautyRatingBar rbScoreCommentList;
    @Bind(R.id.tv_score_comment_list)
    TextView tvScoreCommentList;
    @Bind(R.id.tv_time_comment_list)
    TextView tvTimeCommentList;
    @Bind(R.id.tv_des_comment_list)
    TextView tvDesCommentList;
    @Bind(R.id.tv_like_comment_list)
    public TextView tvLikeCommentList;
    @Bind(R.id.tv_despart_comment_list)
    ExpandIconTextView tvDespartCommentList;
    @Bind(R.id.tv_show_des_comment_list)
    TextView tvShowDesCommentList;
    @Bind(R.id.ll_show_des_comment_list)
    LinearLayout llShowDesCommentList;

    UserCommentI userCommentI;
    Comment data;

    public CommentListRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent, UserCommentI userCommentI) {
        super(inflater, parent);
        this.userCommentI = userCommentI;
    }

    @Override
    protected int itemViewLayoutId() {
        return R.layout.item_comment_list;
    }

    @Override
    protected void onBindItemData(Comment comment, int i, int i1) {
        if (comment == null || comment.commenter == null)
            return;
        this.data = comment;

        QsHelper.getInstance().getImageHelper().createRequest().load(comment.commenter.avatarUrl).into(ivImgCommentList);
        tvTitleCommentList.setText(comment.commenter.name);
        rbScoreCommentList.setRating(comment.starCount);
        tvScoreCommentList.setText(comment.score);
        if(TextUtils.isEmpty(comment.createdAt)){
            tvTimeCommentList.setText("");
        }else {
            tvTimeCommentList.setText(DateTimeUtils.getDateTime(comment.createdAt));
        }
        tvDesCommentList.setText(comment.content);
        setDesPart();
        tvLikeCommentList.setText(comment.likedCount + "");
        if (data.liked) {
            tvLikeCommentList.setEnabled(false);
        } else {
            tvLikeCommentList.setEnabled(true);
        }
        tvLikeCommentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userCommentI != null) {
                    userCommentI.onPraise(i, data);
                }
            }
        });
    }

    @OnClick({R.id.tv_like_comment_list, R.id.rl_like_comment_list, R.id.tv_show_des_comment_list})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.rl_like_comment_list:
            case R.id.tv_like_comment_list:
                break;
            case R.id.tv_show_des_comment_list:
                setDesPart();
                break;
        }
    }

    private void setDesPart() {
        if (data == null || data.commenter == null)
            return;
        tvDespartCommentList.setVisibility(View.VISIBLE);
        tvDesCommentList.setVisibility(View.GONE);
        tvShowDesCommentList.setVisibility(View.GONE);
        tvDespartCommentList.setText(data.content);
        tvDespartCommentList.setExpandIconI(new ExpandIconTextView.ExpandIconI() {
            @Override
            public void onClick() {
                tvDespartCommentList.setVisibility(View.GONE);
                tvDesCommentList.setVisibility(View.VISIBLE);
                tvShowDesCommentList.setVisibility(View.VISIBLE);
            }
        });
    }
}