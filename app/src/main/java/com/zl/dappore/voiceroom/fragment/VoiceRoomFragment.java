package com.zl.dappore.voiceroom.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.zl.dappore.R;
import com.zl.dappore.common.agora.AgoraHelper;
import com.zl.dappore.common.agora.IRtcEngineEventListener;
import com.zl.dappore.common.event.VoiceRoomEvent;
import com.zl.dappore.common.event.VoiceRoomSettingEvent;
import com.zl.dappore.common.model.UserConfig;
import com.zl.dappore.onlinelist.OnlineListActivity;
import com.zl.dappore.voiceroom.fragment.chatroom.ChatRoomFragment;
import com.zl.dappore.voiceroom.fragment.roomrole.RoomRoleOperationBarFragment;
import com.zl.dappore.voiceroom.fragment.voicerole.VoiceAuditorOperationDialogFragment;
import com.zl.dappore.voiceroom.fragment.voicerole.VoiceClientOperationDialogFragment;
import com.zl.dappore.voiceroom.fragment.voicerole.VoiceHolderOperationDialogFragment;
import com.zl.dappore.voiceroom.fragment.voicerole.VoiceRoleInfoDialogFragment;
import com.zl.dappore.voiceroom.fragment.voicerole.VoiceRoleOperationDialogFragment;
import com.zl.dappore.voiceroom.fragment.voicerole.voiceclient.VoiceAdmin2ClientOperationDialogFragment;
import com.zl.dappore.voiceroom.fragment.voicerole.voiceclient.VoiceHolder2ClientOperationDialogFragment;
import com.zl.dappore.voiceroom.fragment.voicerole.voiceempty.VoiceAdmin2EmptyOperationDialogFragment;
import com.zl.dappore.voiceroom.fragment.voicerole.voiceempty.VoiceHolder2EmptyOperationDialogFragment;
import com.zl.dappore.voiceroom.fragment.voicerole.voiceuser.VoiceClient2UserOperationDialogFragment;
import com.zl.dappore.voiceroom.listener.OnVoiceClientListener;
import com.zl.dappore.voiceroom.model.voicerole.BaseVoiceRole;
import com.zl.dappore.voiceroom.model.voicerole.VoiceRole;
import com.zl.dappore.voiceroom.model.voiceroom.VoiceRoom;
import com.zl.dappore.voiceroom.model.VoiceRoomConstants;
import com.zl.dappore.voiceroom.model.voiceroom.VoiceRoomResponse;
import com.zl.dappore.voiceroom.presenter.VoiceRoomPresenter;

import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.agora.rtc.IRtcEngineEventHandler;

public class VoiceRoomFragment extends QsFragment<VoiceRoomPresenter> implements OnVoiceClientListener, IRtcEngineEventListener {

    @Bind(R.id.ll_back)
    LinearLayout llBack;
    @Bind(R.id.tv_name_voice_room)
    TextView tvNameVoiceRoom;
    @Bind(R.id.tv_id_voice_room)
    TextView tvIdVoiceRoom;
    @Bind(R.id.tv_users_voice_room)
    TextView tvUsersVoiceRoom;
    @Bind(R.id.btn_more_voice_room)
    ImageButton btnMoreVoiceRoom;
    @Bind(R.id.iv_decorate_voice_room)
    ImageView ivDecorateVoiceRoom;
    @Bind(R.id.iv_logo_voice_room)
    ImageView ivLogoVoiceRoom;
    @Bind(R.id.iv_animation_voice_room)
    ImageView ivAnimationVoiceRoom;
    @Bind(R.id.rl_holder_voice_room)
    RelativeLayout rlHolderVoiceRoom;
    @Bind(R.id.tv_holder_name_voice_room)
    TextView tvHolderNameVoiceRoom;
    @Bind(R.id.tv_announce_voice_room)
    TextView tv_announce_voice_room;
    @Bind(R.id.chatroomframe)
    RelativeLayout chatroomframe;
    @Bind(R.id.chatroomlayout)
    LinearLayout chatroomlayout;

    VoiceRoom voiceRoom;
    VoiceRole voiceHolder;
    List<VoiceRole> voiceClients;
    VoiceRole user;
    String voiceRoomId = "";
    String userId = "";

    VoiceClientGridFragment voiceClientGridFragment;
    RoomRoleOperationBarFragment roomRoleOperationBarFragment;

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

        voiceRoomId = arguments.getString(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROOM_ID);
        userId = arguments.getString(VoiceRoomConstants.BUNDLE_KEY_REQUEST_USER_ID);

        L.i(initTag(), " voiceRoomId " + voiceRoomId + " userId " + userId);

        AgoraHelper.getInstance().joinChannel("room1", 11);
        voiceClientGridFragment = (VoiceClientGridFragment) getChildFragmentManager().findFragmentById(R.id.f_voice_room);
        roomRoleOperationBarFragment = (RoomRoleOperationBarFragment) getChildFragmentManager().findFragmentById(R.id.bar);

        requstData(userId);
//        creatOrJoinVoiceRoom();

//        initChatRoom();

        loadingClose();
        showContentView();
    }

    public void setCurrentData(VoiceRoomResponse responce) {
        if (responce == null)
            return;

        if (responce.voiceRoom != null) {
            voiceRoom = new VoiceRoom();
            voiceRoom = responce.voiceRoom;
        }

        if (responce.voiceHolder != null) {
            voiceHolder = new VoiceRole();
            voiceHolder = responce.voiceHolder;
        }

        user = new VoiceRole();
        user.id = userId;
        user.name = "user" + userId;
        user.logo = "http://staging.dappore.com/xNdqnHirMbzFYW9BXkmKPZ3n";
        user.permission = responce.voiceUserPermission;

        if (responce.voiceClients != null && responce.voiceClients.size() > 0) {
            voiceClients = new ArrayList<>();
            voiceClients.addAll(responce.voiceClients);
        }

        Bundle bundle = new Bundle();
        bundle.putSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROOM, voiceRoom);
        bundle.putSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_HOLDER, voiceHolder);
        bundle.putSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE_USER, user);
//        bundle.putSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROOM_RESPONSE, responce);
        bundle.putSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_CLIENTS, (Serializable) voiceClients);
        roomRoleOperationBarFragment.setArguments(bundle);
    }

    private void creatOrJoinVoiceRoom() {
        if (voiceRoom == null)
            return;

        String token = UserConfig.getInstance().getAuthToken();
        //用户信息
        if (true) {
            getPresenter().createVoiceRoom(token, "1");//TODO 已创建房间 自己进自己
        } else {
            getPresenter().joinVoiceRoom(token, voiceRoom.voiceRoomId);//
        }

    }

    @ThreadPoint(ThreadType.MAIN)
    public void initChatRoom() {
        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.chatroomlayout, ChatRoomFragment.getInstance(), ChatRoomFragment.class.getSimpleName());
    }

    public void requstData(String userId) {
        getPresenter().requstData(userId);
    }

    @ThreadPoint(ThreadType.MAIN)
    public void updateVoiceClientGridView(List<VoiceRole> data) {
        if (data == null || data.size() <= 0)
            return;

        voiceClientGridFragment.updateVoiceClientGridView(data);
    }

    @ThreadPoint(ThreadType.MAIN)
    public void updateVoiceHolderView(VoiceRole data) {
        if (data == null)
            return;
        QsHelper.getInstance().getImageHelper().createRequest().load(data.logo).circleCrop().into(ivLogoVoiceRoom);
        tvHolderNameVoiceRoom.setText(data.name);
    }

    @ThreadPoint(ThreadType.MAIN)
    public void updateVoiceRoomView(VoiceRoom data) {
        if (data == null)
            return;

        this.voiceRoom = data;

        tvNameVoiceRoom.setText(data.voiceRoomName);
        tvIdVoiceRoom.setText("ID:" + data.voiceRoomId);
        tvUsersVoiceRoom.setText(data.voiceRoomOnlines + "人在线");
        tv_announce_voice_room.setText(data.voiceRoomAnnounce);
    }

    @OnClick({R.id.ll_back, R.id.tv_name_voice_room, R.id.tv_id_voice_room, R.id.tv_users_voice_room, R.id.btn_more_voice_room, R.id.iv_decorate_voice_room, R.id.iv_logo_voice_room, R.id.iv_animation_voice_room, R.id.rl_holder_voice_room, R.id.tv_holder_name_voice_room})
    public void onViewClick(View view) {
        super.onViewClick(view);

        Bundle bundle;
        switch (view.getId()) {
            case R.id.ll_back:
                activityFinish();
                break;
            case R.id.tv_name_voice_room:
                break;
            case R.id.tv_id_voice_room:
                break;
            case R.id.tv_users_voice_room:
                bundle = new Bundle();
                bundle.putString(VoiceRoomConstants.BUNDLE_KEY_REQUEST_ID, "1");
                bundle.putInt(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE, BaseVoiceRole.VOICE_HOLDER);
                QsHelper.getInstance().intent2Activity(OnlineListActivity.class, bundle);
                break;
            case R.id.btn_more_voice_room:
                bundle = new Bundle();
                bundle.putSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE_USER, user);
                bundle.putSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROOM, voiceRoom);
                RoomOperationDialogFragment.getInstance(bundle).show();
                break;
            case R.id.iv_decorate_voice_room:
                break;
            case R.id.iv_logo_voice_room:
                break;
            case R.id.iv_animation_voice_room:
                break;
            case R.id.rl_holder_voice_room:
                bundle = new Bundle();
                bundle.putSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE_USER, user);
                bundle.putSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_CLIENT_OR_AUDITOR, voiceHolder);
                showVoiceUser2VoiceHolderDialog(user, voiceHolder, bundle);
                break;
            case R.id.tv_holder_name_voice_room:
                break;
        }
    }

    @Subscribe
    public void onEvent(VoiceRoomEvent event) {
        if (event == null)
            return;

//        switch (event.state) {
//            case COMMENT:
//                requestVideoDetail();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        setCommentReward(event.reward);
//                    }
//                }, 20);
//                break;
//        }
    }

    @Subscribe
    public void onEvent(VoiceRoomSettingEvent event) {
        if (event == null)
            return;

        switch (event.state) {
            case STATE_FRESH_AFTER_SETTING:
                break;
        }
    }

    @Override
    public boolean isOpenEventBus() {
        return true;
    }

    @Override
    public void onItemSelect(VoiceRole data, int position, int totalCount) {
        L.i(initTag(), " onItemSelect " + data);
        Bundle bundle = new Bundle();
        bundle.putSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE_USER, user);
        bundle.putSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_CLIENT_OR_AUDITOR, data);
        switch (user.permission) {
            case VoiceRole.PERMISSION_HOLDER:
                showVoiceHolder2VoiceRoleDialog(user, data, bundle);
                break;
            case VoiceRole.PERMISSION_ADMIN:
                showVoiceAdmin2VoiceRoleDialog(user, data, bundle);
                break;
            case VoiceRole.PERMISSION_GUEST:
                showVoiceGuest2VoiceRoleDialog(user, data, bundle);
                break;
        }
    }

    /*房主*/
    private void showVoiceUser2VoiceHolderDialog(VoiceRole user, VoiceRole holder, Bundle bundle) {
        if (user == null || holder == null || bundle == null)
            return;

        if (holder.isVoiceRoleUsing()) {
            /*非空麦位*/

            if (holder.isCurrentVoiceUser(user.id)) {
                //房主主页
            } else {
                VoiceRoleInfoDialogFragment voiceRoleInfoDialogFragment = VoiceRoleInfoDialogFragment.getInstance(bundle);
                voiceRoleInfoDialogFragment.show();
            }
        } else {
            /*空麦位*/
        }
    }

    /*房主*/
    private void showVoiceHolder2VoiceRoleDialog(VoiceRole user, VoiceRole data, Bundle bundle) {
        if (user == null || data == null || bundle == null)
            return;

        VoiceRoleOperationDialogFragment fragment;
        if (data.isVoiceRoleUsing()) {
            /*非空麦位*/

            if (data.isCurrentVoiceUser(user.id)) {
                //房主主页
            } else {
                fragment = VoiceHolder2ClientOperationDialogFragment.getInstance(bundle);
                fragment.show();
            }
        } else {
            /*空麦位*/

            //麦上用户

            //麦下用户
            fragment = VoiceHolder2EmptyOperationDialogFragment.getInstance(bundle);
            fragment.show();
        }
    }

    /*麦上管理员、麦下管理员*/
    private void showVoiceAdmin2VoiceRoleDialog(VoiceRole user, VoiceRole data, Bundle bundle) {
        if (user == null || data == null || bundle == null)
            return;

        VoiceRoleOperationDialogFragment fragment;
        if (data.isVoiceRoleUsing()) {
            /*非空麦位*/

            if (data.isCurrentVoiceUser(user.id)) {
                //本人（麦上管理员）
                fragment = VoiceClient2UserOperationDialogFragment.getInstance(bundle);
                fragment.show();
            } else {
                //非本人（其他麦位用户）
                fragment = VoiceAdmin2ClientOperationDialogFragment.getInstance(bundle);
                fragment.show();
            }
        } else {
            /*空麦位*/

            //麦上用户

            //麦下用户
            fragment = VoiceAdmin2EmptyOperationDialogFragment.getInstance(bundle);
            fragment.show();
        }
    }

    /*麦上普通用户、麦下普通用户*/
    private void showVoiceGuest2VoiceRoleDialog(VoiceRole user, VoiceRole data, Bundle bundle) {
        if (user == null || data == null || bundle == null)
            return;

        VoiceRoleOperationDialogFragment fragment;
        if (data.isVoiceRoleUsing()) {
            /*非空麦位*/

            if (data.isCurrentVoiceUser(user.id)) {
                //本人（麦上普通用户）
                fragment = VoiceClient2UserOperationDialogFragment.getInstance(bundle);
                fragment.show();
            } else {
                //非本人（其他麦位用户）
                VoiceRoleInfoDialogFragment voiceRoleInfoDialogFragment = VoiceRoleInfoDialogFragment.getInstance(bundle);
                voiceRoleInfoDialogFragment.show();
            }
        } else {
            /*空麦位*/

            //麦上用户 换麦

            //麦下用户 上麦

        }
    }

    public void onItemSelect2(VoiceRole data, int position, int totalCount) {
        L.i(initTag(), " onItemSelect " + data);
        Bundle bundle = new Bundle();
        bundle.putSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE_USER, user);
        bundle.putSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_CLIENT_OR_AUDITOR, data);
        VoiceRoleOperationDialogFragment fragment;
        switch (user.voiceRole) {
            case VoiceRole.VOICE_HOLDER:
                fragment = VoiceHolderOperationDialogFragment.getInstance(bundle);
                fragment.show();
                break;
            case VoiceRole.VOICE_CLIENT:
            case VoiceRole.VOICE_ADMIN_CLIENT:
                fragment = VoiceClientOperationDialogFragment.getInstance(bundle);
                fragment.show();
                break;
            case VoiceRole.VOICE_AUDITOR:
            case VoiceRole.VOICE_ADMIN_AUDITOR:
                fragment = VoiceAuditorOperationDialogFragment.getInstance(bundle);
                fragment.show();
                break;
        }
    }

    @Override
    public void onJoinChannelSuccess(String channel, int uid, int elapsed) {

    }

    @Override
    public void onRejoinChannelSuccess(String channel, int uid, int elapsed) {

    }

    @Override
    public void onLeaveChannel(IRtcEngineEventHandler.RtcStats stats) {

    }

    @Override
    public void onClientRoleChanged(int oldRole, int newRole) {

    }

    @Override
    public void onUserJoined(int uid, int elapsed) {

    }

    @Override
    public void onUserOffline(int uid, int reason) {

    }

    @Override
    public void onUserMuteAudio(int uid, boolean muted) {

    }

}