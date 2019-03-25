package com.tricolorflower.heartbeat.home.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.RelativeLayout;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.tricolorflower.heartbeat.R;



public class MessageFragment extends QsFragment {

    @Bind(R.id.content)
    protected RelativeLayout content;
    @Bind(R.id.fragmentlayout)
    protected RelativeLayout fragmentlayout;

    @Override
    public int layoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void onActionBar() {
        setActivityTitle("mine");
    }

    public static Fragment getInstance() {
        return new MessageFragment();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        showContentView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setContentFragment();
    }

    protected void setContentFragment() {

    }

}
