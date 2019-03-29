package com.tricolorflower.heartbeat.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.RelativeLayout;

import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.tricolorflower.heartbeat.R;


public class EmptyFragment extends QsFragment {

    public static Fragment getInstance() {
        return new EmptyFragment();
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_empty;
    }

    @Override
    public void initData(Bundle bundle) {

    }
}
