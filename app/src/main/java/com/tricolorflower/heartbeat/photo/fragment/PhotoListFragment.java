package com.tricolorflower.heartbeat.photo.fragment;

import android.os.Bundle;
import android.text.TextUtils;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.mvp.adapter.QsListAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsListFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.utils.CommonUtils;
import com.tricolorflower.heartbeat.common.utils.image.ModelImage;
import com.tricolorflower.heartbeat.common.utils.image.ModelImageGroup;
import com.tricolorflower.heartbeat.photo.adapter.PhotoListAdapterItem;
import com.tricolorflower.heartbeat.photo.model.PhotoConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @CreateBy qsmaxmin
 * @Date 2016/10/26 15:24
 * @Description
 */
public class PhotoListFragment extends QsListFragment {
    private static final int ITEM_COUNT = 3;
    private ArrayList<ModelImageGroup> list;
    private int mState;

    public static PhotoListFragment getInstance(Bundle bundle) {
        PhotoListFragment photoListFragment = new PhotoListFragment();
        if (bundle != null) photoListFragment.setArguments(bundle);
        return photoListFragment;
    }

    @Override
    public void onActionBar() {
        super.onActionBar();
        if (mState == PhotoConstants.KEY_TO_SINGLE_PHOTO_LIST && list != null && list.size() == 1 && list.get(0) != null && !TextUtils.isEmpty(list.get(0).getDirName())) {
            setActivityTitle(list.get(0).getDirName(), mState);
        } else {
            setActivityTitle(getString(R.string.photo_list_title), mState);
        }
    }


    @Override
    public QsListAdapterItem getListAdapterItem(int i) {
        int screenWidth = CommonUtils.getScreenWidth();
        return new PhotoListAdapterItem(screenWidth / 3, screenWidth / 3, getActivity());
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getListView().setDivider(null);
        getListView().setDividerHeight(0);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mState = arguments.getInt(PhotoConstants.BUNDLE_KEY_STATE);
            list = (ArrayList<ModelImageGroup>) arguments.getSerializable(PhotoConstants.BUNDLE_KEY_PHOTO_LIST);
            if (list != null) {
                L.i(initTag(), "list size:" + list.size());
                ArrayList<File> fileList = new ArrayList<>();
                for (ModelImageGroup group : list) {
                    for (ModelImage modelImage : group.getImages()) {
                        File file = new File(modelImage.path);
                        if (file.exists()) {
                            fileList.add(file);
                        }
                    }
                }
                L.i(initTag(),"fileList size:" + fileList.size());
                Collections.sort(fileList, new FileComparator());
                setData(getBeautyList(fileList, mState == PhotoConstants.KEY_TO_All_PHOTO_LIST));
            }
        } else {
            setData(null);
        }
    }

    private ArrayList<File[]> getBeautyList(ArrayList<File> list, boolean hasCamera) {
        ArrayList<File[]> result = new ArrayList<>();
        File[] fileArr = new File[ITEM_COUNT];
        if (hasCamera) {
            list.add(0, null);
        }
        for (int i = 0; i < list.size(); i++) {
            int index = i % ITEM_COUNT;

            if (index == 0) {
                fileArr = new File[ITEM_COUNT];
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

    private class FileComparator implements Comparator<File> {
        @Override
        public int compare(File lhs, File rhs) {
            if (lhs != null && rhs != null) {
                if (lhs.lastModified() == rhs.lastModified()) {
                    return 0;
                } else if (lhs.lastModified() < rhs.lastModified()) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return 0;
            }
        }
    }

//    @Override
//    public boolean fragmentState() {
//        return true;
//    }
}
