package com.tricolorflower.heartbeat.voiceroom.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsRecyclerFragment;
import com.tricolorflower.heartbeat.common.event.VoiceRoleOperationEvent;
import com.tricolorflower.heartbeat.common.listener.ItemListener;
import com.tricolorflower.heartbeat.voiceroom.adapter.VoiceClientListRecyclerAdapterItem;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.LabelList;
import com.tricolorflower.heartbeat.voiceroom.presenter.VoiceClientListPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public class VoiceClientListFragment extends QsRecyclerFragment<VoiceClientListPresenter, VoiceRole> implements ItemListener<VoiceRole> {

    VoiceRoom voiceRoom;
    VoiceRole voiceHolder;
    List<VoiceRole> voiceClients;
    VoiceRole user;

    List<VoiceRole> selectVoiceRoles;

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
        selectVoiceRoles = new ArrayList<>();

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
        return new VoiceClientListRecyclerAdapterItem(mInflater, parent, this);
    }

    @Override
    public void onItemClick(VoiceRole data, int position, int totalCount) {
        if (data == null)
            return;

        notifyItemChanged(position, !getData().get(position).isSelect);

        if (getCurrentItem(position).isSelect) {
            selectVoiceRoles.add(getCurrentItem(position));
        } else {
            removeSelectVoiceRolesItem(getCurrentItem(position));
        }
    }

    public void notifyItemChanged(int pos, boolean isSelect) {
        if (getRecyclerView() != null && getData() != null && getData().size() > pos) {
            getData().get(pos).isSelect = isSelect;
            getRecyclerView().getAdapter().notifyItemChanged(pos);
        }
    }

    public VoiceRole getCurrentItem(int pos) {
        if (getRecyclerView() != null && getData() != null && getData().size() > pos) {
            return getData().get(pos);
        }

        return new VoiceRole();
    }

    public void removeSelectVoiceRolesItem(VoiceRole data) {
        if (data == null || selectVoiceRoles == null)
            return;

        ListIterator<VoiceRole> iterator = selectVoiceRoles.listIterator();
        while (iterator.hasNext()) {
            VoiceRole item = iterator.next();
            if (data.id == item.id) {
                iterator.remove();
            }
        }
    }

    public String getSelectVoiceRoleList() {
        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < selectVoiceRoles.size(); i++) {
            if (i == selectVoiceRoles.size() - 1) {
                buffer.append(selectVoiceRoles.get(i).id);
            } else {
                buffer.append(selectVoiceRoles.get(i).id).append(",");
            }
        }

        return buffer.toString();
    }
}