package com.tricolorflower.heartbeat.blacklist.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsPullRecyclerFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.blacklist.adapter.BlackRecyclerAdapterItem;
import com.tricolorflower.heartbeat.blacklist.model.BlackListConstants;
import com.tricolorflower.heartbeat.blacklist.presenter.BlackListPresenter;
import com.tricolorflower.heartbeat.common.event.VoiceRoleOperationEvent;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class BlackListFragment extends QsPullRecyclerFragment<BlackListPresenter, VoiceRole> {

    VoiceRoom voiceRoom;
    VoiceRole voiceHolder;
    List<VoiceRole> voiceClients;
    VoiceRole user;

    public static Fragment getInstance(Bundle extras) {
        BlackListFragment fragment = new BlackListFragment();
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

        requestData(false);
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
        requestData(false);
    }

    @Override
    public void onLoad() {
        requestData(true);
    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        return new BlackRecyclerAdapterItem(mInflater, parent);
    }

    private void requestData(boolean isLoadingMore) {
    }

    @Override
    public int emptyLayoutId() {
        return R.layout.empty_layout_black_list;
    }

    @Subscribe
    public void onEvent(VoiceRoleOperationEvent.OnAddBlackEvent event) {
        if (event == null || event.data == null)
            return;

        requestData(true);
    }

    @Subscribe
    public void onEvent(VoiceRoleOperationEvent.OnRemoveBlackEvent event) {
        if (event == null || event.data == null)
            return;

    }

    @Override
    public boolean isOpenEventBus() {
        return true;
    }

}