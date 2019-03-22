package com.tricolorflower.heartbeat.common.utils;

import android.content.Intent;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.dialog.SimpleDialogFragment;
import com.tricolorflower.heartbeat.common.event.AppEvent;
import com.tricolorflower.heartbeat.common.service.UpdateService;
import com.tricolorflower.heartbeat.setting.model.CheckUpdateResponse;

public class AppUpdateUtil {

    /**
     * 根据自升级接口下发，做出的判断
     */
    public static void checkUpdateResult(final CheckUpdateResponse result, boolean showDialog) {
        if (result == null) {
            return;
        }
        QsHelper.getInstance().eventPost(new AppEvent.HasNewVersion(result.hasNewVersion()));
        try {
            if (result.hasNewVersion()) {
                if (result.mustUpdateToNewVersion()) {
                    SimpleDialogFragment dialogFragment = SimpleDialogFragment.getInstance();
                    dialogFragment.setTitle(QsHelper.getInstance().getString(R.string.index_new_version_dlg));
                    dialogFragment.setMessage(QsHelper.getInstance().getString(R.string.index_new_version_dlg_tip));
                    dialogFragment.setConfirm(QsHelper.getInstance().getString(R.string.index_new_version_dlg_ok));
                    dialogFragment.setCancel(QsHelper.getInstance().getString(R.string.index_new_version_dlg_cancel));
                    dialogFragment.setOnDialogListener(new SimpleDialogFragment.OnDialogListener() {
                        @Override
                        public void onConfirm() {
                            startDownload(result);
                        }

                        @Override
                        public void onCancel() {
                            //强制升级，没有升级，自动退出
                            QsHelper.getInstance().getScreenHelper().popAllActivityExceptMain(null);
                        }
                    });
                    dialogFragment.setCancelable(false);
                    dialogFragment.show();

//                    ColorSimpleDialogFragment.createBuilder()//
//                            .setTitle(R.string.index_new_version_dlg)//
//                            .setMessage(R.string.index_new_version_dlg_tip)//
//                            .setPositiveButtonText(R.string.index_new_version_dlg_ok)//
//                            .setNegativeButtonText(R.string.index_new_version_dlg_cancel)//
//                            .setOnDialogClickListener(new ColorSimpleDialogFragment.SimpleDialogClickListener() {
//                                @Override
//                                public void onPositiveButtonClick(int i) {
//                                    startDownload(result);
//                                }
//
//                                @Override
//                                public void onNegativeButtonClick(int i) {
//                                    //强制升级，没有升级，自动退出
//                                    QsHelper.getInstance().getScreenHelper().popAllActivityExceptMain(null);
//                                }
//
//                                @Override
//                                public void onNeutralButtonClick(int i) {
//
//                                }
//                            }).setRequestCode(0).setCancelable(false).showAllowingStateLoss();
                } else if (showDialog) {

                    SimpleDialogFragment dialogFragment = SimpleDialogFragment.getInstance();
                    dialogFragment.setTitle(QsHelper.getInstance().getString(R.string.str_settingsOthersActivity_new_version));
                    dialogFragment.setMessage(QsHelper.getInstance().getString(R.string.str_settingsOthersActivity_download_now));
                    dialogFragment.setConfirm(QsHelper.getInstance().getString(R.string.str_settingsOthersActivity_reverse_now));
                    dialogFragment.setCancel(QsHelper.getInstance().getString(R.string.str_settingsOthersActivity_no_reverse));
                    dialogFragment.setOnDialogListener(new SimpleDialogFragment.OnDialogListener() {
                        @Override
                        public void onConfirm() {
                            startDownload(result);
                        }

                        @Override
                        public void onCancel() {

                        }
                    });
                    dialogFragment.show();

//                    ColorSimpleDialogFragment.createBuilder()//
//                            .setTitle(R.string.str_settingsOthersActivity_new_version)//
//                            .setMessage(R.string.str_settingsOthersActivity_download_now)//
//                            .setPositiveButtonText(R.string.str_settingsOthersActivity_reverse_now)//
//                            .setNegativeButtonText(R.string.str_settingsOthersActivity_no_reverse)//
//                            .setOnDialogClickListener(new ColorSimpleDialogFragment.SimpleDialogClickListener() {
//                                @Override
//                                public void onPositiveButtonClick(int i) {
//                                    startDownload(result);
//                                }
//
//                                @Override
//                                public void onNegativeButtonClick(int i) {
//
//                                }
//
//                                @Override
//                                public void onNeutralButtonClick(int i) {
//                                }
//                            }).setRequestCode(0).showAllowingStateLoss();
                }
            } else if (showDialog) {

                SimpleDialogFragment dialogFragment = SimpleDialogFragment.getInstance();
                dialogFragment.setTitle(QsHelper.getInstance().getString(R.string.str_settingsOthersActivity_tip));
                dialogFragment.setMessage(QsHelper.getInstance().getString(R.string.str_settingsOthersActivity_is_newest));
                dialogFragment.setConfirm(QsHelper.getInstance().getString(R.string.str_settingsOthersActivity_ok));
                dialogFragment.show();

//                ColorSimpleDialogFragment.createBuilder()//
//                        .setTitle(R.string.str_settingsOthersActivity_tip)//
//                        .setMessage(R.string.str_settingsOthersActivity_is_newest)//
//                        .setPositiveButtonText(R.string.str_settingsOthersActivity_ok)//
//                        .setRequestCode(0).showAllowingStateLoss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void startDownload(CheckUpdateResponse result) {
        QsToast.show("正在后台下载更新...");
        Intent updateIntent = new Intent(QsHelper.getInstance().getScreenHelper().currentActivity(), UpdateService.class);
        updateIntent.putExtra("filename", QsHelper.getInstance().getString(R.string.app_name) + ".apk");
        updateIntent.putExtra("urldownload", result.downloadurl);
        QsHelper.getInstance().getScreenHelper().currentActivity().startService(updateIntent);
    }
}
