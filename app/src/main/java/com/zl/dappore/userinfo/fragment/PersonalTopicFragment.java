package com.zl.dappore.userinfo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.qsmaxmin.qsbase.mvp.adapter.QsListAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsListFragment;

/**
 * 字工场原创字体
 *
 * @CreateBy qsmaxmin
 * @Date 16/8/2
 * @Description
 */
public class PersonalTopicFragment extends QsListFragment {

    public static Fragment getInstance(Bundle bundle) {
        PersonalTopicFragment fragment = new PersonalTopicFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public QsListAdapterItem getListAdapterItem(int i) {
        return null;
    }

    @Override
    public void initData(Bundle bundle) {

        showContentView();
    }
}
