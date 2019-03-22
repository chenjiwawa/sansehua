package com.tricolorflower.heartbeat.videodetail.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.comment.UserCommentI;
import com.tricolorflower.heartbeat.comment.model.Comment;
import com.tricolorflower.heartbeat.common.utils.CommonUtils;
import com.tricolorflower.heartbeat.common.utils.DateTimeUtils;


public class VideoCommentListAdapterItem extends QsRecycleAdapterItem<Comment> {

    @Bind(R.id.iv_img_video_comment)
    ImageView ivImgVideoComment;
    @Bind(R.id.tv_title_video_comment)
    TextView tvTitleVideoComment;
    @Bind(R.id.tv_time_video_comment)
    TextView tvTimeVideoComment;
    @Bind(R.id.tv_des_video_comment)
    TextView tvDesVideoComment;
    @Bind(R.id.tv_like_video_comment)
    public TextView tvLikeVideoComment;

    UserCommentI userCommentI;
    Comment data;

    public VideoCommentListAdapterItem(LayoutInflater inflater, ViewGroup parent, UserCommentI userCommentI) {
        super(inflater, parent);
        this.userCommentI = userCommentI;
    }

    @Override
    protected int itemViewLayoutId() {
        return R.layout.item_video_comment;
    }

    @Override
    protected void onBindItemData(Comment comment, int i, int i1) {
        if (comment == null || comment.commenter == null)
            return;
        this.data = comment;
        L.i(initTag(), " requestCommentList onBindItemData  " + comment.id + "  " + comment.liked);

        QsHelper.getInstance().getImageHelper().createRequest().load(comment.commenter.avatarUrl).into(ivImgVideoComment);
        tvTitleVideoComment.setText("@" + comment.commenter.name);
        tvTimeVideoComment.setVisibility(View.GONE);
        if (TextUtils.isEmpty(comment.createdAt)) {
            tvTimeVideoComment.setText("");
        } else {
            tvTimeVideoComment.setText(DateTimeUtils.getDateTime(comment.createdAt));
        }

        tvDesVideoComment.setText(comment.content);
        String str = DateTimeUtils.getDateTime(comment.createdAt);
        SpannableString spannableString = new SpannableString("           " + str + "");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#CCCCCC"));
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(0.8f);
        spannableString.setSpan(colorSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvDesVideoComment.append(spannableString);

//        int sizePixel = CommonUtils.dp2px(8);
////        String textStr = "<font color='#CCCCCC' size='" + sizePixel + "'>" + str + "</font>";
//        String textStr = "<font color='#CCCCCC' size='6'>" + str + "</font>";
//        tvDesVideoComment.append(Html.fromHtml(textStr));

        tvLikeVideoComment.setText(comment.likedCount + "");
        if (data.liked) {
            Drawable drawable = QsHelper.getInstance().getDrawable(R.mipmap.ic_video_like_select);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvLikeVideoComment.setCompoundDrawables(null, drawable, null, null);
        } else {
            Drawable drawable = QsHelper.getInstance().getDrawable(R.mipmap.ic_video_like_unselect);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvLikeVideoComment.setCompoundDrawables(null, drawable, null, null);
        }
        tvLikeVideoComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userCommentI != null) {
                    userCommentI.onPraise(i, data);
                }
            }
        });
    }

    @OnClick({R.id.tv_like_video_comment, R.id.iv_img_video_comment})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.tv_like_video_comment:
                break;
            case R.id.iv_img_video_comment:
                break;
        }
    }
}