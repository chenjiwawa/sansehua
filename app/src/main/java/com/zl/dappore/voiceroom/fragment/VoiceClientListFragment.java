package com.zl.dappore.voiceroom.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsRecyclerFragment;
import com.zl.dappore.voiceroom.adapter.VoiceClientListRecyclerAdapterItem;
import com.zl.dappore.voiceroom.model.voicerole.VoiceRole;
import com.zl.dappore.voiceroom.model.voiceroom.VoiceRoom;
import com.zl.dappore.voiceroom.model.VoiceRoomConstants;
import com.zl.dappore.voiceroom.presenter.VoiceClientListPresenter;

import java.util.ArrayList;
import java.util.List;


public class VoiceClientListFragment extends QsRecyclerFragment<VoiceClientListPresenter, VoiceRole> {

    VoiceRoom voiceRoom;
    VoiceRole voiceHolder;
    List<VoiceRole> voiceClients;
    VoiceRole user;

    public static VoiceClientListFragment getInstance(Bundle extras) {
        VoiceClientListFragment fragment = new VoiceClientListFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        initArgumentData();
        getRecyclerView().setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        updateVoiceClientListView(voiceClients);
        showContentView();
    }

    private void initArgumentData() {
        Bundle arguments = getArguments();
        if (arguments == null) return;

        voiceRoom = (VoiceRoom) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROOM);
        voiceHolder = (VoiceRole) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_HOLDER);
        voiceClients = (ArrayList<VoiceRole>) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_CLIENTS);
        user = (VoiceRole) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE_USER);

        L.i(initTag(), " initArgumentData voiceRoom " + voiceRoom);
        L.i(initTag(), " initArgumentData voiceHolder " + voiceHolder);
        L.i(initTag(), " initArgumentData voiceClients " + voiceClients);
        L.i(initTag(), " initArgumentData user " + user);

    }

    @ThreadPoint(ThreadType.MAIN)
    public void updateVoiceClientListView(List<VoiceRole> data) {
        if (data == null || data.size() <= 0)
            return;

        L.i(initTag(), " updateVoiceClientListView " + data);
        setData(data);

    }


    public void requstData() {
        getPresenter().requstData();
    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        return new VoiceClientListRecyclerAdapterItem(mInflater, parent);
    }
}