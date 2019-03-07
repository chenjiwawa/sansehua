package com.zl.dappore.comment;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.qsmaxmin.qsbase.mvp.QsABActivity;
import com.zl.dappore.R;
import com.zl.dappore.comment.fragment.CommentListFragment;
import com.zl.dappore.comment.model.CommentConstants;
import com.zl.dappore.common.utils.KeyboardHelper;
import com.zl.dappore.common.widget.BeautyRatingBar;

public class CommentListActivity extends QsABActivity implements BeautyRatingBar.OnRatingChangeListener, CommentI {

    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.rb_score_input)
    BeautyRatingBar rbScoreInput;
    @Bind(R.id.tv_score_input)
    TextView tvScoreInput;
    @Bind(R.id.ll_score_input)
    LinearLayout llScoreInput;
    @Bind(R.id.iv_icon_input)
    ImageView ivIconInput;
    @Bind(R.id.et_content_input)
    EditText etContentInput;
    @Bind(R.id.rl_content_input)
    RelativeLayout rlContentInput;
    @Bind(R.id.tv_coment_input)
    TextView tvComentInput;
    @Bind(R.id.rl_comment_input)
    RelativeLayout rlCommentInput;
    CommentListFragment fragment;
    boolean commentable;

    @Override
    public int actionbarLayoutId() {
        return R.layout.actionbar_title_back;
    }

    @Override
    public int layoutId() {
        return R.layout.activity_commentlist;
    }

    @Override
    public void initData(Bundle bundle) {
        tv_title.setText("评论");

        Bundle extras = getIntent().getExtras();
        commentable = extras.getBoolean(CommentConstants.BUNDLE_KEY_COMMENT_REQUEST_COMMENTABLE, true);
        fragment = (CommentListFragment) CommentListFragment.getInstance(extras == null ? new Bundle() : extras);
        commitFragment(fragment);

        etContentInput.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                int screenHeight = getWindow().getDecorView().getRootView().getHeight();
                int keyboardHeight = screenHeight - rect.bottom;
                if (keyboardHeight == 0) {
                    ivIconInput.setVisibility(View.VISIBLE);
                    llScoreInput.setVisibility(View.GONE);
                } else {
                    ivIconInput.setVisibility(View.GONE);
                    llScoreInput.setVisibility(View.VISIBLE);
                }
            }
        });
        rbScoreInput.setOnRatingChangeListener(this);
        if (commentable) {
            rlCommentInput.setVisibility(View.VISIBLE);
        } else {
            rlCommentInput.setVisibility(View.GONE);
        }
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        KeyboardHelper.hideSoftInputFromWindow(this);
//        return super.dispatchTouchEvent(ev);
//    }

    @Override
    public void hideComment() {
        KeyboardHelper.hideSoftInputFromWindow(this);
        rlCommentInput.setVisibility(View.GONE);
    }

    @OnClick({R.id.ll_back, R.id.tv_coment_input})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.ll_back:
                activityFinish();
                break;
            case R.id.tv_coment_input:
                if (TextUtils.isEmpty(etContentInput.getText().toString())) {
                    QsToast.show("请输入评论！");
                    return;
                }

                if (fragment != null) {
                    fragment.requestCommentList(etContentInput.getText().toString(), rbScoreInput.getRating()+ "");
                }
                break;
        }
    }

    @Override
    public void onRatingChange(float RatingCount) {
        tvScoreInput.setText((RatingCount * 2) + "分");
    }
}
