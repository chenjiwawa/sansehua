package com.zl.dappore.photo.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.RadioButton;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.qsmaxmin.qsbase.mvp.adapter.QsListAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsListFragment;
import com.zl.dappore.R;
import com.zl.dappore.common.event.UserInfoEvent;
import com.zl.dappore.common.model.AppConstants;
import com.zl.dappore.common.utils.CommonUtils;
import com.zl.dappore.common.utils.image.ModelImage;
import com.zl.dappore.common.utils.image.ModelImageGroup;
import com.zl.dappore.common.utils.permission.PermissionUtil;
import com.zl.dappore.photo.PhotoViewLocalActivity;
import com.zl.dappore.photo.adapter.PhotoMultipleChooseAdapterItem;
import com.zl.dappore.photo.model.ModelMultiplePhoto;
import com.zl.dappore.photo.model.PhotoConstants;

import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/8/11 10:54
 * @Description
 */

public class PhotoMultipleChooseFragment extends QsListFragment {
    public String imageFilePath;
    private static final int ITEM_COUNT = 3;
    private ArrayList<String> hasChooseList = new ArrayList<>();
    private OnItemViewSelectedListener listener = new OnItemViewSelectedListener() {
        @Override
        public void onItemSelected(RadioButton button, ModelMultiplePhoto model) {
            if (model != null && model.photoFile != null) {
                int size = hasChooseList.size();
                String path = model.photoFile.getAbsolutePath();
                boolean isChecked = button.isChecked();
                if (!isChecked) {
                    if (size >= 10) {
                        QsToast.show(QsHelper.getInstance().getString(R.string.maxnum, 10));
                        return;
                    }
                    if (!hasChooseList.contains(path)) {
                        hasChooseList.add(path);
                        button.setChecked(true);
                        model.isSelected = true;
                    }
                } else {
                    if (hasChooseList.contains(path)) {
                        hasChooseList.remove(path);
                        button.setChecked(false);
                        model.isSelected = false;
                    }
                }

                FragmentActivity activity = getActivity();
                if (activity instanceof PhotoViewLocalActivity) {
                    ((PhotoViewLocalActivity) activity).setOnChoosePhotoFragmentSelectedPhoto(hasChooseList.size());
                }
            }
        }
    };

    @Override
    public void onActionBar() {
        super.onActionBar();
        setActivityTitle(QsHelper.getInstance().getString(R.string.image), PhotoConstants.KEY_TO_PHOTO_CHOOSE_LIST);
    }

    @Override
    public QsListAdapterItem getListAdapterItem(int i) {
        int screenWidth = CommonUtils.getScreenWidth();
        return new PhotoMultipleChooseAdapterItem(screenWidth / 3, screenWidth / 3, getActivity(), listener);
    }

    public static PhotoMultipleChooseFragment getInstance(Bundle bundle) {
        PhotoMultipleChooseFragment fragment = new PhotoMultipleChooseFragment();
        if (bundle != null) fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getListView().setDivider(null);
        getListView().setDividerHeight(0);
        Bundle arguments = getArguments();
        if (arguments != null) {
            ArrayList<ModelImageGroup> list = (ArrayList<ModelImageGroup>) arguments.getSerializable(PhotoConstants.BUNDLE_KEY_PHOTO_LIST);
            if (list != null) {
                L.i(initTag(), "list size:" + list.size());
                ArrayList<ModelMultiplePhoto> fileList = new ArrayList<>();
                for (ModelImageGroup group : list) {
                    for (ModelImage modelImage : group.getImages()) {
                        ModelMultiplePhoto model = new ModelMultiplePhoto();
                        model.photoFile = new File(modelImage.path);
                        model.isSelected = false;
                        if (model.photoFile.exists()) {
                            fileList.add(model);
                        }
                    }
                }
                L.i(initTag(), "fileList size:" + fileList.size());
                Collections.sort(fileList, new FileComparator());
                setData(getBeautyList(fileList, true));
            }
        } else {
            setData(null);
        }
    }

    private ArrayList<ModelMultiplePhoto[]> getBeautyList(ArrayList<ModelMultiplePhoto> list, boolean hasCamera) {
        ArrayList<ModelMultiplePhoto[]> result = new ArrayList<>();
        ModelMultiplePhoto[] fileArr = new ModelMultiplePhoto[ITEM_COUNT];
        if (hasCamera) {
            list.add(0, null);
        }
        for (int i = 0; i < list.size(); i++) {
            int index = i % ITEM_COUNT;

            if (index == 0) {
                fileArr = new ModelMultiplePhoto[ITEM_COUNT];
                fileArr[index] = list.get(i);
            } else {
                fileArr[index] = list.get(i);
                if (index == ITEM_COUNT - 1) {
                    result.add(fileArr);
                }
            }
            if (i == list.size() - 1 && index != ITEM_COUNT - 1) {
                result.add(fileArr);
            }
        }
        return result;
    }

    public void onChoosePhotoComplete() {
        requestClose();
    }

    private void requestClose() {
        Intent intent = new Intent();
        intent.putStringArrayListExtra(PhotoConstants.BUNDLE_KEY_PHOTO_CHOOSE_LIST, hasChooseList);
        getActivity().setResult(Activity.RESULT_OK, intent);
        activityFinish();
    }

    public interface OnItemViewSelectedListener {
        void onItemSelected(RadioButton button, ModelMultiplePhoto model);
    }

    private class FileComparator implements Comparator<ModelMultiplePhoto> {
        @Override
        public int compare(ModelMultiplePhoto lhs, ModelMultiplePhoto rhs) {
            if (lhs != null && rhs != null && lhs.photoFile != null && rhs.photoFile != null) {
                if (lhs.photoFile.lastModified() == rhs.photoFile.lastModified()) {
                    return 0;
                } else if (lhs.photoFile.lastModified() < rhs.photoFile.lastModified()) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return 0;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        L.i(initTag(), "onActivityResult  .... " + requestCode + "   " + resultCode);
        if (requestCode == PhotoConstants.REQUESTCODE_OPEN_CAMERA) {
            if (resultCode == Activity.RESULT_OK && !TextUtils.isEmpty(imageFilePath)) {
                hasChooseList.add(imageFilePath);
                requestClose();
            }
        }
    }

    @Subscribe
    public void onEvent(UserInfoEvent.OnAlbumCameraClick event) {
        PermissionUtil.getInstance()//
                .setActivity(getActivity())//
                .setShowCustomDialog(true)//
                .addWantPermission(Manifest.permission.CAMERA)//
                .setListener(new PermissionUtil.PermissionListener() {
                    @Override
                    public void onPermissionCallback(int requestCode, boolean isGrantedAll) {
                        if (isGrantedAll) {
                            requestOpenCamera();
                        }
                    }
                }).startRequest();

    }

    /**
     * 打开摄像头来选取一张图片
     */
    private void requestOpenCamera() {
        if (QsHelper.getInstance().isSdCardAvailable()) {
            File directory = new File(Environment.getExternalStorageDirectory(), AppConstants.PATH_IMG);
            if (!directory.exists()) directory.mkdirs();
            File noMediaFile = new File(directory, ".Nomedia");
            if (!noMediaFile.exists()) {
                try {
                    noMediaFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            File imageFile;
            imageFile = new File(directory, System.currentTimeMillis() + ".jpg");
            L.i(initTag(), "requestOpenCamera  file=" + imageFile.getAbsolutePath());
            if (imageFile.exists()) {
                boolean isDelete = imageFile.delete();
            }
            try {
                boolean isCreate = imageFile.createNewFile();
                if (isCreate) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    Uri mCameraUri = Uri.fromFile(imageFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, mCameraUri);
                    this.imageFilePath = imageFile.getAbsolutePath();
                    startActivityForResult(intent, PhotoConstants.REQUESTCODE_OPEN_CAMERA);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            QsToast.show("您的sdcard不能读写，请确认您的sdcard是否是可写状态");
        }
    }

    @Override
    public boolean isOpenEventBus() {
        return true;
    }
}
