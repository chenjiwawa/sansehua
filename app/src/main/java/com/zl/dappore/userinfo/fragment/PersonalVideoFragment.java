package com.zl.dappore.userinfo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsPullRecyclerFragment;
import com.zl.dappore.home.model.GridItemVideo;
import com.zl.dappore.userinfo.adapter.PersonalVideoRecyclerAdapterItem;
import com.zl.dappore.userinfo.model.PersonalConstants;
import com.zl.dappore.userinfo.presenter.PersonalVideoPresenter;


public class PersonalVideoFragment extends QsPullRecyclerFragment<PersonalVideoPresenter, GridItemVideo> {
    private String id = "";
    private boolean isUser = false;

    public static PersonalVideoFragment getInstance(Bundle bundle) {
        PersonalVideoFragment fragment = new PersonalVideoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        id = bundle.getString(PersonalConstants.BUNDLE_KEY_PERSONAL_REQUEST_ID);
        isUser = bundle.getBoolean(PersonalConstants.BUNDLE_KEY_PERSONAL_REQUEST_IS_USER, false);

        onRefresh();
    }


    public void requstDataList(boolean isLoadingMore) {
        getPresenter().requestVideoList(isLoadingMore, id);
    }

    @Override
    public void onRefresh() {
        requstDataList(false);
    }

    @Override
    public void onLoad() {
        requstDataList(true);
    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        return new PersonalVideoRecyclerAdapterItem(mInflater, parent, isUser);
    }
}
