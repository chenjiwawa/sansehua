package com.zl.dappore.voiceroom.fragment.voiceorole;

import android.os.Bundle;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.zl.dappore.R;
import com.zl.dappore.voiceroom.fragment.VoiceClientGridFragment;
import com.zl.dappore.voiceroom.model.VoiceRoomConstants;


public class VoiceClientOperationFragment extends QsFragment {

    String channelId = "";
    int voiceRole = 0;

    public static VoiceClientOperationFragment getInstance(Bundle extras) {
        VoiceClientOperationFragment fragment = new VoiceClientOperationFragment();
        fragment.setArguments(extras);
        return fragment;
    }


    @Override
    public int layoutId() {
        return R.layout.fragment_voice_client_operation;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle arguments = getArguments();

        //TODO
        arguments = new Bundle();
        if (arguments == null) return;

        channelId = arguments.getString(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_CHANNEL_ID);
        voiceRole = arguments.getInt(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_VOICE_ROLE);

        VoiceClientGridFragment voiceClientGridFragment = (VoiceClientGridFragment) getChildFragmentManager().findFragmentById(R.id.f_voice_room);
        voiceClientGridFragment.setArguments(arguments);

        L.i(initTag(), " channelId " + channelId + " voiceRole " + voiceRole);
        loadingClose();
        showContentView();
    }


    public void requstData(int voiceRole) {
    }

}