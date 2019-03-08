package com.zl.dappore.voiceroom.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.qsmaxmin.qsbase.mvp.fragment.QsRecyclerFragment;
import com.zl.dappore.R;
import com.zl.dappore.appdetail.fragment.AppDetailFragment;
import com.zl.dappore.appdetail.model.AppDetailConstants;
import com.zl.dappore.common.widget.itemdecoration.DividerGridItemDecoration;
import com.zl.dappore.voiceroom.adapter.VoiceClientRecyclerAdapterItem;
import com.zl.dappore.voiceroom.model.VoiceClient;
import com.zl.dappore.voiceroom.model.VoiceRoomConstants;
import com.zl.dappore.voiceroom.presenter.VoiceClientPresenter;
import com.zl.dappore.voiceroom.presenter.VoiceRoomPresenter;


public class VoiceRoomFragment extends QsFragment<VoiceRoomPresenter> {

    String channelId = "";
    int voiceRole = 0;

    public static VoiceRoomFragment getInstance(Bundle extras) {
        VoiceRoomFragment fragment = new VoiceRoomFragment();
        fragment.setArguments(extras);
        return fragment;
    }


    @Override
    public int layoutId() {
        return R.layout.fragment_voice_room;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments == null) return;

        channelId = arguments.getString(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_CHANNEL_ID);
        voiceRole = arguments.getInt(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_VOICE_ROLE);

        VoiceClientFragment voiceClientFragment = (VoiceClientFragment) getChildFragmentManager().findFragmentById(R.id.f_voice_room);
        voiceClientFragment.setArguments(arguments);

        L.i(initTag(), " channelId " + channelId + " voiceRole " + voiceRole);
        loadingClose();
        showContentView();
    }


    public void requstData(int voiceRole) {
    }

}