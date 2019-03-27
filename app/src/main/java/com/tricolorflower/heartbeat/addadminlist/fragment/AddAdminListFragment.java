package com.tricolorflower.heartbeat.addadminlist.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsPullRecyclerFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.addadminlist.adapter.AddAdminRecyclerAdapterItem;
import com.tricolorflower.heartbeat.addadminlist.model.AddAdminListConstants;
import com.tricolorflower.heartbeat.addadminlist.presenter.AddAdminListPresenter;
import com.tricolorflower.heartbeat.common.event.VoiceRoleOperationEvent;
import com.tricolorflower.heartbeat.common.listener.ItemListener;
import com.tricolorflower.heartbeat.common.listener.ItemSingleSelectListener;
import com.tricolorflower.heartbeat.common.model.UserConfig;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;

import java.util.ArrayList;
import java.util.List;


public class AddAdminListFragment extends QsPullRecyclerFragment<AddAdminListPresenter, VoiceRole> implements ItemSingleSelectListener<VoiceRole> {

    VoiceRoom voiceRoom;
    VoiceRole voiceHolder;
    List<VoiceRole> voiceClients;
    VoiceRole user;
    String token;

    String namestr;
    VoiceRole data;

    public static AddAdminListFragment getInstance(Bundle extras) {
        AddAdminListFragment fragment = new AddAdminListFragment();
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

        requestData(token, namestr);
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
        token = UserConfig.getInstance().getAuthToken();

        L.i(initTag(), " initArgumentData voiceRoom " + voiceRoom);
        L.i(initTag(), " initArgumentData voiceHolder " + voiceHolder);
        L.i(initTag(), " initArgumentData voiceClients " + voiceClients);
        L.i(initTag(), " initArgumentData user " + user);

    }

    @Override
    public void onRefresh() {
        requestData(token, namestr);
    }

    @Override
    public void onLoad() {
        requestData(token, namestr);
    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        return new AddAdminRecyclerAdapterItem(mInflater, parent, this);
    }

    private void requestData(String token, String namestr) {
        getPresenter().searchAdmin(token, namestr);
    }

    @Override
    public int emptyLayoutId() {
        return R.layout.empty_layout_add_admin_list;
    }

    @Override
    public void onItemClick(VoiceRole data, int preposition, int position, int totalCount) {
        if (data == null)
            return;

        if (data.isSelect) {
            //选中 to 未选中
            this.data = null;
        } else {
            //未选中 to 选中
            this.data = data;
        }

        if (preposition == position) {
            notifyItemChanged(position, !getData().get(position).isSelect);
        } else {
            notifyItemChanged(preposition, false);
            notifyItemChanged(position, true);
        }

        QsHelper.getInstance().eventPost(new VoiceRoleOperationEvent.OnLetRoleLoginEvent(data));
    }

    public void notifyItemChanged(int pos, boolean isSelect) {
        if (getRecyclerView() != null && getData() != null && getData().size() > pos) {
            getData().get(pos).isSelect = isSelect;
            getRecyclerView().getAdapter().notifyItemChanged(pos);
        }
    }

    public void commit() {
        if (data == null) {

            QsToast.show("请选中！");
            return;
        }

        getPresenter().addAdmin(token, data.id);
    }

    public void setCommitSuccessView() {
        activityFinish();
    }

}