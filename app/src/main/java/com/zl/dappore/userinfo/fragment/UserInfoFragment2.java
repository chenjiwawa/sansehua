//package com.zl.dappore.userinfo.fragment;
//
//import android.Manifest;
//import android.app.Activity;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
//import com.qsmaxmin.qsbase.common.aspect.ThreadType;
//import com.qsmaxmin.qsbase.common.config.PropertyCallback;
//import com.qsmaxmin.qsbase.common.log.L;
//import com.qsmaxmin.qsbase.common.utils.QsHelper;
//import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
//import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
//import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
//import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
//import com.zl.dappore.R;
//import com.zl.dappore.common.event.LoginEvent;
//import com.zl.dappore.common.event.UserInfoEvent;
//import com.zl.dappore.common.model.AppConfig;
//import com.zl.dappore.common.model.UserConfig;
//import com.zl.dappore.common.utils.permission.PermissionUtil;
//import com.zl.dappore.photo.PhotoCutActivity;
//import com.zl.dappore.photo.PhotoViewLocalActivity;
//import com.zl.dappore.photo.model.PhotoConstants;
//import com.zl.dappore.userinfo.presenter.UserInfoFragmentPresenter;
//
//import org.greenrobot.eventbus.Subscribe;
//
//import java.io.File;
//
///**
// * @CreateBy qsmaxmin
// * @Date 16/9/18  下午3:32
// * @Description
// */
//public class UserInfoFragment2 extends QsFragment<UserInfoFragmentPresenter> {
//
//    @Bind(R.id.text_login_out)
//    TextView text_login_out;
//    @Bind(R.id.avatar_icon)
//    ImageView avatar_icon;
//    @Bind(R.id.nickname)
//    TextView nickname;
//    @Bind(R.id.layout_nick)
//    RelativeLayout layout_nick;
//
//    private String imageFilePath;
//
//    @Override
//    public int layoutId() {
//        return R.layout.fragment_userinfo;
//    }
//
//    @Override
//    public void onActionBar() {
//        super.onActionBar();
//        setActivityTitle("个人资料");
//    }
//
//    public static UserInfoFragment2 getInstance(Bundle extras) {
//        UserInfoFragment2 myInfoFragment = new UserInfoFragment2();
//        myInfoFragment.setArguments(extras);
//        return myInfoFragment;
//    }
//
//    @Override
//    public void initData(Bundle savedInstanceState) {
//        updateView(true);
//        showContentView();
//    }
//
//    @ThreadPoint(ThreadType.MAIN)
//    private void updateView(boolean showProgressbar) {
//        QsHelper.getInstance().getImageHelper().createRequest().load(UserConfig.getInstance().getAvatarUrl()).error(R.mipmap.icon_my_font).into(avatar_icon);
//        nickname.setText(UserConfig.getInstance().getName());
//        String userPhone = UserConfig.getInstance().getMobile();
//    }
//
//    @OnClick({R.id.avatar_icon, R.id.layout_nick, R.id.text_login_out})
//    public void onViewClick(View view) {
//        super.onViewClick(view);
//        switch (view.getId()) {
//            case R.id.layout_nick:
//                commitBackStackFragment(ChangeNickFragment.getInstance());
//                break;
//            case R.id.avatar_icon:
////                ShowSelectDialog();
////                gotoPhotoChooseActivity();
//                requestOpenCamera();
//                break;
//            case R.id.text_login_out:
//                requestLogout();
//
////                RoomOperationDialogFragment dialogFragment = RoomOperationDialogFragment.getInstance();
////                dialogFragment.setTitle(QsHelper.getInstance().getString(R.string.tip_title));
////                dialogFragment.setMessage(QsHelper.getInstance().getString(R.string.make_sure_exit));
////                dialogFragment.setOnDialogListener(new RoomOperationDialogFragment.OnDialogListener() {
////                    @Override
////                    public void onConfirm() {
////                        requestLogout();
////                    }
////
////                    @Override
////                    public void onCancel() {
////                    }
////                });
////                dialogFragment.show();
//
////                ColorSimpleDialogFragment.createBuilder()//
////                        .setTitle(R.string.tip_title)//
////                        .setMessage(getString(R.string.make_sure_exit))//
////                        .setPositiveButtonText(R.string.str_ok)//
////                        .setNegativeButtonText(R.string.str_cancel)//
////                        .setOnDialogClickListener(new ColorSimpleDialogFragment.SimpleDialogClickListener() {
////                            @Override
////                            public void onPositiveButtonClick(int i) {
////                                requestLogout();
////                            }
////
////                            @Override
////                            public void onNegativeButtonClick(int i) {
////                            }
////
////                            @Override
////                            public void onNeutralButtonClick(int i) {
////                            }
////                        }).setRequestCode(0).showAllowingStateLoss();
//                break;
//        }
//    }
//
//    @ThreadPoint(ThreadType.MAIN)
//    private void requestLogout() {
//        try {
//            loading(true);
//            AppConfig.getInstance().updateCurrentUserId("", new PropertyCallback() {
//                @Override
//                public void onSuccess() {
//                    UserConfig.getInstance().logout(new PropertyCallback() {
//                        @Override
//                        public void onSuccess() {
//                            QsHelper.getInstance().eventPost(new LoginEvent.onLogin(LoginEvent.LoginState.STATE_LOGOUT, 0));
//                            QsToast.show("退出成功");
//                            text_login_out.setVisibility(View.GONE);
//                            loadingClose();
//                            UserInfoFragment2.this.getActivity().finish();
//                        }
//                    });
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            QsToast.show("无法退出");
//        }
//    }
//
//
//    private void ShowSelectDialog() {
////        SelectIconDialog instance = SelectIconDialog.getInstance();
////        instance.setOnIconSelectedListener(new SelectIconDialog.PopupMenuSelectInterface() {
////            @Override
////            public void onIconSelected(int selectType) {
////                if (!NetworkUtils.isNetworkConnected()) {
////                    QsToast.show(getString(R.string.network_error_msg));
////                    return;
////                }
////                switch (selectType) {
////                    case SelectIconDialog.Select_Photos:
////                        gotoPhotoChooseActivity();
////                        break;
////                    case SelectIconDialog.Select_Camera:
////                        requestOpenCamera();
////                        break;
////                    default:
////                        break;
////                }
////
////            }
////        });
////        QsHelper.commitDialogFragment(instance);
//
//    }
//
//    private void requestOpenCamera() {
//        PermissionUtil.getInstance()//
//                .setActivity(getActivity())//
//                .setShowCustomDialog(true)//
//                .addWantPermission(Manifest.permission.CAMERA)//
//                .setListener(new PermissionUtil.PermissionListener() {
//                    @Override
//                    public void onPermissionCallback(int requestCode, boolean isGrantedAll) {
//                        if (isGrantedAll) {
//                            getPresenter().requestOpenCamera();
//                        }
//                    }
//                }).startRequest();
//
//    }
//
//
//    public void openCamera(Intent intent, File imageFile, Uri mCameraUri) {
//        this.imageFilePath = imageFile.getAbsolutePath();
//        L.e(initTag(), "mCameraUri  ******** imageFilePath:" + imageFilePath);
//        startActivityForResult(intent, PhotoConstants.REQUESTCODE_OPEN_CAMERA);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            case PhotoConstants.REQUESTCODE_OPEN_CAMERA://相机关闭回调
//                if (resultCode == Activity.RESULT_OK && !TextUtils.isEmpty(imageFilePath)) {
//                    Intent intent = new Intent(getActivity(), PhotoCutActivity.class);
//                    intent.putExtra(PhotoConstants.BUNDLE_KEY_PHOTO_PATH, imageFilePath);
//                    L.i(initTag(), "onActivityResult************相机 path=" + imageFilePath);
//                    startActivityForResult(intent, PhotoConstants.REQUESTCODE_OPEN_PHOTO_CUT);
//                }
//                break;
//            case PhotoConstants.REQUESTCODE_OPEN_PHOTO://相册关闭回调
//                L.i(initTag(), "onActivityResult************相册");
//                break;
//
//            case PhotoConstants.REQUESTCODE_OPEN_PHOTO_CUT://裁剪页关闭回调
//                L.i(initTag(), "onActivityResult************裁剪");
//                break;
//        }
//    }
//
//    private void gotoPhotoChooseActivity() {
//        intent2Activity(PhotoViewLocalActivity.class);
//    }
//
//
//    /**
//     * 当相册页点击了照相机
//     */
//    @Subscribe
//    public void onEvent(UserInfoEvent.OnAlbumCameraClick event) {
//        L.i(initTag(), "onEvent  OnAlbumCameraClick");
//        requestOpenCamera();
//    }
//
//    /**
//     * 昵称更改成功
//     */
//    @Subscribe
//    public void onEvent(UserInfoEvent.OnUserInfoDataChanged event) {
//        L.i(initTag(), "onEvent  OnUserInfoDataChanged");
//        updateView(false);
//    }
//
//    @Override
//    public boolean isOpenEventBus() {
//        return true;
//    }
//
////    @Override
////    public boolean fragmentState() {
////        return false;
////    }
//}
