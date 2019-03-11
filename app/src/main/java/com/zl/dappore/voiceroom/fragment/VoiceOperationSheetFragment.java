package com.zl.dappore.voiceroom.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.qsmaxmin.qsbase.common.viewbind.ViewBindHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.zl.dappore.R;
import com.zl.dappore.common.utils.CommonUtils;
import com.zl.dappore.common.utils.KeyboardHelper;
import com.zl.dappore.videodetail.fragment.VideoCommentListFragment;
import com.zl.dappore.videodetail.fragment.VideoCommentSheetFragment;

public class VoiceOperationSheetFragment extends BottomSheetDialogFragment {
    public final static String TAG = VideoCommentSheetFragment.class.getSimpleName();

    @Bind(R.id.rl_comment_sheet_video_detail)
    RelativeLayout rlCommentSheetVideoDetail;

    VideoCommentListFragment fragment;

    /**
     * 顶部向下偏移量
     */
    private int topOffset = 0;
    private BottomSheetBehavior<FrameLayout> behavior;
    private View dialogView;

    public static Fragment getInstance(Bundle extras) {
        VideoCommentSheetFragment fragment = new VideoCommentSheetFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getContext() == null) {
            return super.onCreateDialog(savedInstanceState);
        }
        return new BottomSheetDialog(getContext(), R.style.TransBottomSheetDialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dialogView = inflater.inflate(R.layout.fragment_voice_operation_sheet, container, false);
        ViewBindHelper.bindView(this, dialogView);
        return dialogView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (dialogView != null) {
            ((View) dialogView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // 设置软键盘不自动弹出
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
        FrameLayout bottomSheet = dialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
        if (bottomSheet != null) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomSheet.getLayoutParams();
            layoutParams.height = getHeight();
            behavior = BottomSheetBehavior.from(bottomSheet);
            // 初始为展开状态
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

        fragment = (VideoCommentListFragment) VideoCommentListFragment.getInstance(getArguments());
        if (fragment != null && !fragment.isAdded()) {
            getChildFragmentManager().beginTransaction().replace(R.id.rl_comment_sheet_video_detail, VideoCommentListFragment.getInstance(getArguments())).commitAllowingStateLoss();
        }
    }

    /**
     * 获取屏幕高度
     *
     * @return height
     */
    private int getHeight() {
        int height = 1920;
        if (getContext() != null) {
            WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            Point point = new Point();
            if (wm != null) {
                // 使用Point已经减去了状态栏高度
                wm.getDefaultDisplay().getSize(point);
                height = point.y - getTopOffset();
            }
        }
        return height;
    }

    public int getTopOffset() {
        return CommonUtils.dp2px(160);
    }

    public void setTopOffset(int topOffset) {
        this.topOffset = topOffset;
    }

    public BottomSheetBehavior<FrameLayout> getBehavior() {
        return behavior;
    }


    @OnClick({R.id.rl_comment_sheet_video_detail})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rl_comment_sheet_video_detail:
                break;
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        KeyboardHelper.hideSoftInput(getActivity());
    }

    private FragmentTransaction transaction;

    /**
     * 切换Fragment
     *
     * @param ID
     * @param fragment
     */
    private void replaceFragment(int ID, Fragment fragment) {
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(ID, fragment);
        transaction.commitAllowingStateLoss();
    }

}