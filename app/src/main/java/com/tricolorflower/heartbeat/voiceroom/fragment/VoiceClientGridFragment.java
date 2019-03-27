package com.tricolorflower.heartbeat.voiceroom.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsRecyclerFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.event.RoomRoleOperationEvent;
import com.tricolorflower.heartbeat.common.event.VoiceRoomEvent;
import com.tricolorflower.heartbeat.common.model.UserConfig;
import com.tricolorflower.heartbeat.voiceroom.adapter.VoiceClientGridRecyclerAdapterItem;
import com.tricolorflower.heartbeat.voiceroom.listener.OnVoiceClientListener;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;
import com.tricolorflower.heartbeat.voiceroom.presenter.VoiceClientGridPresenter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class VoiceClientGridFragment extends QsRecyclerFragment<VoiceClientGridPresenter, VoiceRole> {

    VoiceRoom voiceRoom;
    VoiceRole voiceHolder;
    List<VoiceRole> voiceClients;
    VoiceRole user;

    public static VoiceClientGridFragment getInstance() {
        VoiceClientGridFragment fragment = new VoiceClientGridFragment();
        return fragment;
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_voice_client_grid;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initArgumentData();

        getRecyclerView().setLayoutManager(new GridLayoutManager(getContext(), 4));
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

    public void requstData() {
        getPresenter().requstData();
    }

    @ThreadPoint(ThreadType.MAIN)
    public void updateVoiceClientGridView(List<VoiceRole> data) {
        if (data == null || data.size() <= 0)
            return;

        L.i(initTag(), " updateVoiceClientGridView " + data);
        setData(data);

    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        OnVoiceClientListener onVoiceClientListener = null;
        if (getParentFragment() != null && getParentFragment() instanceof OnVoiceClientListener) {
            onVoiceClientListener = (OnVoiceClientListener) getParentFragment();
        }
        return new VoiceClientGridRecyclerAdapterItem(mInflater, parent, onVoiceClientListener);
    }

    /*麦位表情展示*/
    @Subscribe
    public void onEvent(RoomRoleOperationEvent.OnEmojiEvent event) {
        if (event == null || event.data != null)
            return;

        if (user.isVoiceClient()) {
            //TODO
        }
    }

    @Override
    public boolean isOpenEventBus() {
        return true;
    }
}