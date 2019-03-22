package com.tricolorflower.heartbeat.photo.fragment;

import android.os.Bundle;

import com.qsmaxmin.qsbase.mvp.adapter.QsListAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsListFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.utils.CommonUtils;
import com.tricolorflower.heartbeat.common.utils.image.ModelImageGroup;
import com.tricolorflower.heartbeat.photo.adapter.PhotoDirListAdapterItem;
import com.tricolorflower.heartbeat.photo.model.PhotoConstants;

import java.util.ArrayList;

/**
 * @CreateBy qsmaxmin
 * @Date 2016/10/26 15:24
 * @Description
 */
public class PhotoDirListFragment extends QsListFragment{
    ArrayList<ModelImageGroup> list;

    public static PhotoDirListFragment getInstance(Bundle bundle) {
        PhotoDirListFragment photoDirListFragment = new PhotoDirListFragment();
        photoDirListFragment.setArguments(bundle);
        return photoDirListFragment;
    }

    @Override
    public void onActionBar() {
        super.onActionBar();
        setActivityTitle(getString(R.string.photo_dir_list_title), PhotoConstants.KEY_TO_PHOTO_DIR_LIST);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getListView().setDivider(null);
        getListView().setDividerHeight(0);
        list = (ArrayList<ModelImageGroup>) getArguments().getSerializable(PhotoConstants.BUNDLE_KEY_PHOTO_LIST);
        setData(list);
    }

    @Override
    public QsListAdapterItem getListAdapterItem(int i) {
        int length = (int) (CommonUtils.getScreenWidth() * 0.3);
        return new PhotoDirListAdapterItem(length, length);
    }
}
