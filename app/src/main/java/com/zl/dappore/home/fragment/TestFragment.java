package com.zl.dappore.home.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.zl.dappore.R;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;


public class TestFragment extends QsFragment {

    @Override public int layoutId() {
        return R.layout.fragment_test;
    }

    @Override public void onActionBar() {
        setActivityTitle("mine");
    }

    public static Fragment getInstance() {
        return new TestFragment();
    }


    @Override public void initData(Bundle savedInstanceState) {

    }


    @Override public boolean isOpenViewState() {
        return false;
    }
}
