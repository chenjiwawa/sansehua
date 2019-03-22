package com.tricolorflower.heartbeat.userinfo.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.config.PropertyCallback;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.dialog.CommonDialog;
import com.tricolorflower.heartbeat.common.event.LoginEvent;
import com.tricolorflower.heartbeat.common.event.UserInfoEvent;
import com.tricolorflower.heartbeat.common.model.AppConfig;
import com.tricolorflower.heartbeat.common.model.UserConfig;
import com.tricolorflower.heartbeat.common.utils.permission.PermissionUtil;
import com.tricolorflower.heartbeat.photo.PhotoCutActivity;
import com.tricolorflower.heartbeat.photo.PhotoViewLocalActivity;
import com.tricolorflower.heartbeat.photo.model.PhotoConstants;
import com.tricolorflower.heartbeat.userinfo.presenter.UserInfoFragmentPresenter;

import org.greenrobot.eventbus.Subscribe;

import java.io.File;

/**
 * @CreateBy qsmaxmin
 * @Date 16/9/18  下午3:32
 * @Description
 */
public class UserInfoFragment extends QsFragment<UserInfoFragmentPresenter> {

    @Bind(R.id.ll_back)
    LinearLayout llBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl_title_back)
    RelativeLayout rlTitleBack;

    @Bind(R.id.rl_logo_userinfo)
    RelativeLayout rlLogoUserinfo;
    @Bind(R.id.tv_nick_userinfo)
    TextView tvNickUserinfo;
    @Bind(R.id.rl_nick_userinfo)
    RelativeLayout rlNickUserinfo;
    @Bind(R.id.rl_psd_userinfo)
    RelativeLayout rlPsdUserinfo;
    @Bind(R.id.tv_language_userinfo)
    TextView tvLanguageUserinfo;
    @Bind(R.id.rl_language_userinfo)
    RelativeLayout rlLanguageUserinfo;
    @Bind(R.id.tv_bind_userinfo)
    TextView tvBindUserinfo;
    @Bind(R.id.rl_bind_userinfo)
    RelativeLayout rlBindUserinfo;
    @Bind(R.id.ll_logout_userinfo)
    LinearLayout llLogoutUserinfo;
    @Bind(R.id.iv_logo_userinfo)
    ImageView ivLogoUserinfo;

    private String imageFilePath;

    @Override
    public int layoutId() {
        return R.layout.fragment_userinfo;
    }

    @Override
    public void onActionBar() {
        super.onActionBar();
        setActivityTitle("个人资料");
    }

    public static UserInfoFragment getInstance(Bundle extras) {
        UserInfoFragment myInfoFragment = new UserInfoFragment();
        myInfoFragment.setArguments(extras);
        return myInfoFragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvTitle.setText("个人中心");
        updateView(true);
        showContentView();
    }

    @ThreadPoint(ThreadType.MAIN)
    private void updateView(boolean showProgressbar) {
        QsHelper.getInstance().getImageHelper().createRequest().load(UserConfig.getInstance().getAvatarUrl()).error(R.mipmap.icon_my_font).into(ivLogoUserinfo);
        tvNickUserinfo.setText(UserConfig.getInstance().getName());
        String userPhone = UserConfig.getInstance().getMobile();
    }


    @ThreadPoint(ThreadType.MAIN)
    private void requestLogout() {
        try {
            loading(true);
            AppConfig.getInstance().updateCurrentUserId("", new PropertyCallback() {
                @Override
                public void onSuccess() {
                    UserConfig.getInstance().logout(new PropertyCallback() {
                        @Override
                        public void onSuccess() {
                            QsHelper.getInstance().eventPost(new LoginEvent.onLogin(LoginEvent.LoginState.STATE_LOGOUT, 0));
                            QsToast.show("退出成功");
                            llLogoutUserinfo.setVisibility(View.GONE);
                            loadingClose();
                            UserInfoFragment.this.getActivity().finish();
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            QsToast.show("无法退出");
        }
    }


    private void ShowSelectDialog() {
//        SelectIconDialog instance = SelectIconDialog.getInstance();
//        instance.setOnIconSelectedListener(new SelectIconDialog.PopupMenuSelectInterface() {
//            @Override
//            public void onIconSelected(int selectType) {
//                if (!NetworkUtils.isNetworkConnected()) {
//                    QsToast.show(getString(R.string.network_error_msg));
//                    return;
//                }
//                switch (selectType) {
//                    case SelectIconDialog.Select_Photos:
//                        gotoPhotoChooseActivity();
//                        break;
//                    case SelectIconDialog.Select_Camera:
//                        requestOpenCamera();
//                        break;
//                    default:
//                        break;
//                }
//
//            }
//        });
//        QsHelper.commitDialogFragment(instance);

    }

    private void requestOpenCamera() {
        PermissionUtil.getInstance()//
                .setActivity(getActivity())//
                .setShowCustomDialog(true)//
                .addWantPermission(Manifest.permission.CAMERA)//
                .setListener(new PermissionUtil.PermissionListener() {
                    @Override
                    public void onPermissionCallback(int requestCode, boolean isGrantedAll) {
                        if (isGrantedAll) {
                            getPresenter().requestOpenCamera();
                        }
                    }
                }).startRequest();

    }


    public void openCamera(Intent intent, File imageFile, Uri mCameraUri) {
        this.imageFilePath = imageFile.getAbsolutePath();
        L.e(initTag(), "mCameraUri  ******** imageFilePath:" + imageFilePath);
        startActivityForResult(intent, PhotoConstants.REQUESTCODE_OPEN_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PhotoConstants.REQUESTCODE_OPEN_CAMERA://相机关闭回调
                if (resultCode == Activity.RESULT_OK && !TextUtils.isEmpty(imageFilePath)) {
                    Intent intent = new Intent(getActivity(), PhotoCutActivity.class);
                    intent.putExtra(PhotoConstants.BUNDLE_KEY_PHOTO_PATH, imageFilePath);
                    L.i(initTag(), "onActivityResult************相机 path=" + imageFilePath);
                    startActivityForResult(intent, PhotoConstants.REQUESTCODE_OPEN_PHOTO_CUT);
                }
                break;
            case PhotoConstants.REQUESTCODE_OPEN_PHOTO://相册关闭回调
                L.i(initTag(), "onActivityResult************相册");
                break;

            case PhotoConstants.REQUESTCODE_OPEN_PHOTO_CUT://裁剪页关闭回调
                L.i(initTag(), "onActivityResult************裁剪");
                break;
        }
    }

    private void gotoPhotoChooseActivity() {
        intent2Activity(PhotoViewLocalActivity.class);
    }


    /**
     * 当相册页点击了照相机
     */
    @Subscribe
    public void onEvent(UserInfoEvent.OnAlbumCameraClick event) {
        L.i(initTag(), "onEvent  OnAlbumCameraClick");
        requestOpenCamera();
    }

    /**
     * 昵称更改成功
     */
    @Subscribe
    public void onEvent(UserInfoEvent.OnUserInfoDataChanged event) {
        L.i(initTag(), "onEvent  OnUserInfoDataChanged");
        updateView(false);
    }

    @Override
    public boolean isOpenEventBus() {
        return true;
    }


    @OnClick({R.id.ll_back, R.id.rl_logo_userinfo, R.id.rl_nick_userinfo, R.id.rl_psd_userinfo, R.id.rl_language_userinfo, R.id.tv_bind_userinfo, R.id.rl_bind_userinfo, R.id.ll_logout_userinfo})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.ll_back:
                onBackPressed();
                break;
            case R.id.rl_logo_userinfo:
                //                ShowSelectDialog();
//                gotoPhotoChooseActivity();
                requestOpenCamera();
                break;
            case R.id.rl_nick_userinfo:
                commitBackStackFragment(ChangeNickFragment.getInstance());
                break;
            case R.id.rl_psd_userinfo:
                break;
            case R.id.rl_language_userinfo:
                break;
            case R.id.tv_bind_userinfo:
                break;
            case R.id.rl_bind_userinfo:
                break;
            case R.id.ll_logout_userinfo:

                CommonDialog dialog=CommonDialog.getInstance(getContext());
                dialog.show();
                dialog.setTitle("是否退出当前帐号？");
                dialog.setListener(new CommonDialog.OnDialogListener() {
                    @Override
                    public void onConfirm() {
                        requestLogout();
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onDismiss(DialogInterface dialog) {

                    }
                });
                break;
        }
    }

//    @Override
//    public boolean fragmentState() {
//        return false;
//    }
}
