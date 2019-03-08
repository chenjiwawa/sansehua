package com.zl.dappore.voiceroom.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsRecyclerFragment;
import com.zl.dappore.R;
import com.zl.dappore.common.widget.itemdecoration.DividerGridItemDecoration;
import com.zl.dappore.voiceroom.adapter.VoiceClientRecyclerAdapterItem;
import com.zl.dappore.voiceroom.model.VoiceClient;
import com.zl.dappore.voiceroom.model.VoiceRoomConstants;
import com.zl.dappore.voiceroom.presenter.VoiceClientPresenter;


public class VoiceClientFragment extends QsRecyclerFragment<VoiceClientPresenter, VoiceClient> {
    String channelId = "";
    int voiceRole = 0;

    public static VoiceClientFragment getInstance(Bundle extras) {
        VoiceClientFragment fragment = new VoiceClientFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments == null) return;

        channelId = arguments.getString(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_CHANNEL_ID);
        voiceRole = arguments.getInt(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_VOICE_ROLE);
        L.i(initTag(), " channelId " + channelId + " voiceRole " + voiceRole);

        requstData(0);
        getRecyclerView().setLayoutManager(new GridLayoutManager(getContext(), 4));
        getRecyclerView().addItemDecoration(new DividerGridItemDecoration(getContext(), R.drawable.divider_grid_5));

        showContentView();
    }


    public void requstData(int voiceRole) {
        getPresenter().requstData(voiceRole);
    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        return new VoiceClientRecyclerAdapterItem(mInflater, parent);
    }
}