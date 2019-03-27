package com.tricolorflower.heartbeat.invitelist.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsPullRecyclerFragment;
import com.tricolorflower.heartbeat.common.event.RoomRoleOperationEvent;
import com.tricolorflower.heartbeat.common.event.VoiceRoleOperationEvent;
import com.tricolorflower.heartbeat.common.listener.ItemListener;
import com.tricolorflower.heartbeat.invitelist.adapter.InviteRecyclerAdapterItem;
import com.tricolorflower.heartbeat.invitelist.model.InviteListConstants;
import com.tricolorflower.heartbeat.invitelist.presenter.InviteListPresenter;
import com.tricolorflower.heartbeat.voicerolelist.adapter.OnlineUserRecyclerAdapterItem;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;

import java.util.ArrayList;
import java.util.List;


public class InviteListFragment extends QsPullRecyclerFragment<InviteListPresenter, VoiceRole> implements ItemListener<VoiceRole> {

    VoiceRoom voiceRoom;
    VoiceRole voiceHolder;
    List<VoiceRole> voiceClients;
    VoiceRole user;

    public static Fragment getInstance(Bundle extras) {
        InviteListFragment fragment = new InviteListFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    protected View initView(LayoutInflater inflater) {
        return super.initView(inflater);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initArgumentData();

        requestVoiceRoleList(false);
        getRecyclerView().setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
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

    @Override
    public void onRefresh() {
        requestVoiceRoleList(false);
    }

    @Override
    public void onLoad() {
        requestVoiceRoleList(true);
    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        return new InviteRecyclerAdapterItem(mInflater, parent, this);
    }

    private void requestVoiceRoleList(boolean isLoadingMore) {
        getPresenter().requestVoiceRoleList(isLoadingMore, "", 0);
    }

    @Override
    public void onItemClick(VoiceRole data, int position, int totalCount) {

        QsHelper.getInstance().eventPost(new VoiceRoleOperationEvent.OnLetRoleLoginEvent(data));
        activityFinish();
    }
}