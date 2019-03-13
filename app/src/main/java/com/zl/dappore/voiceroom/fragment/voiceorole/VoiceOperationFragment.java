package com.zl.dappore.voiceroom.fragment.voiceorole;

import android.os.Bundle;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.zl.dappore.R;
import com.zl.dappore.voiceroom.model.VoiceRoomConstants;
import com.zl.dappore.voiceroom.presenter.VoiceOperationPresenter;


public class VoiceOperationFragment extends QsFragment<VoiceOperationPresenter> {

    String channelId = "";
    int voiceRole = 0;

    public static VoiceOperationFragment getInstance(Bundle extras) {
        VoiceOperationFragment fragment = new VoiceOperationFragment();
        fragment.setArguments(extras);
        return fragment;
    }


    @Override
    public int layoutId() {
        return R.layout.fragment_voice_operation;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        //TODO
        arguments = new Bundle();
        if (arguments == null) return;

        channelId = arguments.getString(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_CHANNEL_ID);
        voiceRole = arguments.getInt(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_VOICE_ROLE);

        L.i(initTag(), " channelId " + channelId + " voiceRole " + voiceRole);
        loadingClose();
        showContentView();
    }


    public void requstData(int voiceRole) {
    }

}