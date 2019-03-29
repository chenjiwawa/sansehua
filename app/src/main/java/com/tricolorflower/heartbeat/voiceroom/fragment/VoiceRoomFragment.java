package com.tricolorflower.heartbeat.voiceroom.fragment;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.agora.AgoraHelper;
import com.tricolorflower.heartbeat.common.agora.IRtcEngineEventListener;
import com.tricolorflower.heartbeat.common.event.RoomRoleOperationEvent;
import com.tricolorflower.heartbeat.common.event.VoiceRoomEvent;
import com.tricolorflower.heartbeat.common.event.VoiceRoomSettingEvent;
import com.tricolorflower.heartbeat.common.model.UserConfig;
import com.tricolorflower.heartbeat.onlinelist.OnlineListActivity;
import com.tricolorflower.heartbeat.voiceroom.fragment.chatroom.ChatRoomFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.RoomRoleOperationBarFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.category.position.VoiceAuditorOperationDialogFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.category.position.VoiceClientOperationDialogFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.category.permission.VoiceHolderOperationDialogFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.VoiceRoleInfoDialogFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.VoiceRoleOperationDialogFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.voiceclient.VoiceAdmin2ClientOperationDialogFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.voiceclient.VoiceGuest2ClientOperationDialogFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.voiceclient.VoiceHolder2ClientOperationDialogFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.voiceempty.VoiceAdmin2EmptyOperationDialogFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.voiceempty.VoiceGuest2EmptyOperationDialogFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.voiceempty.VoiceHolder2EmptyOperationDialogFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.voicerole.voiceuser.VoiceClient2UserOperationDialogFragment;
import com.tricolorflower.heartbeat.voiceroom.listener.OnVoiceClientListener;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.EnterVoiceRoomResponse;
import com.tricolorflower.heartbeat.voiceroom.presenter.VoiceRoomPresenter;

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
    @Bind(R.id.iv_mute_voice_room)
    ImageView ivMuteVoiceRoom;
    @Bind(R.id.rl_holder_voice_room)
    RelativeLayout rlHolderVoiceRoom;
    @Bind(R.id.tv_holder_name_voice_room)
    TextView tvHolderNameVoiceRoom;
    @Bind(R.id.tv_announce_voice_room)
    TextView tv_announce_voice_room;
    @Bind(R.id.iv_edit_announce_voice_room)
    ImageView iv_edit_announce_voice_room;
    @Bind(R.id.chatroomframe)
    RelativeLayout chatroomframe;
    @Bind(R.id.chatroomlayout)
    LinearLayout chatroomlayout;

    //server数据
    VoiceRoom voiceRoom;
    VoiceRole voiceHolder;
    List<VoiceRole> voiceClients;
    VoiceRole user;

    //传入Arguments
    String voiceRoomId = "";
    int userId = 0;
    boolean isCreatOrJoin = false;
    String token;
    int roomType = VoiceRoom.RoomType.CHAT_MAKE_FRIENDS;

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
        initArgumentData();

        AgoraHelper.getInstance().joinChannel("room1", 11);
        voiceClientGridFragment = (VoiceClientGridFragment) getChildFragmentManager().findFragmentById(R.id.f_voice_room);
        voiceClientGridFragment.setArguments(getArguments());
        roomRoleOperationBarFragment = (RoomRoleOperationBarFragment) getChildFragmentManager().findFragmentById(R.id.bar);

        requstData();
//        creatOrJoinVoiceRoom();

        initChatRoom();

        loadingClose();
        showContentView();
    }

    private void initArgumentData() {
        Bundle arguments = getArguments();
        if (arguments == null) return;

        voiceRoomId = arguments.getString(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROOM_ID);
        userId = arguments.getInt(VoiceRoomConstants.BUNDLE_KEY_REQUEST_USER_ID);
        isCreatOrJoin = arguments.getBoolean(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICEHOLDER_CREATE_OR_VOICEAUDITOR_JOIN);
        token = UserConfig.getInstance().getAuthToken();

        L.i(initTag(), " initArgumentData voiceRoomId " + voiceRoomId);
        L.i(initTag(), " initArgumentData userId " + userId);
        L.i(initTag(), " initArgumentData isCreatOrJoin " + isCreatOrJoin);
        L.i(initTag(), " initArgumentData token " + token);

    }

    private Bundle setArguments() {

        Bundle bundle = new Bundle();
        bundle.putSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROOM, voiceRoom);
        bundle.putSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_HOLDER, voiceHolder);
        bundle.putSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE_USER, user);
        bundle.putSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_CLIENTS, (Serializable) voiceClients);

        return bundle;
    }

    public void setCurrentData(EnterVoiceRoomResponse responce) {
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

        //用户信息
        if (isCreatOrJoin) {
            int userId = UserConfig.getInstance().getId();
            getPresenter().createVoiceRoom(token, roomType);//TODO 已创建房间 自己进自己
            QsToast.show("我是房间房主");
        } else {
            getPresenter().joinVoiceRoom(token, voiceRoom.voiceRoomId);//
            QsToast.show("我是房间听众");
        }

    }

    @ThreadPoint(ThreadType.MAIN)
    public void initChatRoom() {
        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.chatroomlayout, ChatRoomFragment.getInstance(), ChatRoomFragment.class.getSimpleName());
    }

    public void requstData() {
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

        if (data.isIdEmpty()) {
            //空麦位
            ivLogoVoiceRoom.setImageResource(R.mipmap.ic_add_voice_client);
            tvHolderNameVoiceRoom.setVisibility(View.INVISIBLE);
            ivMuteVoiceRoom.setVisibility(View.GONE);
        } else {
            //有人麦位
            if (!TextUtils.isEmpty(data.logo)) {
                QsHelper.getInstance().getImageHelper().createRequest().load(data.logo).circleCrop().into(ivLogoVoiceRoom);
            }
            tvHolderNameVoiceRoom.setVisibility(View.VISIBLE);
            tvHolderNameVoiceRoom.setText(data.name + "");
            if (data.isVoiceMute()) {
                ivMuteVoiceRoom.setVisibility(View.VISIBLE);
            } else {
                ivMuteVoiceRoom.setVisibility(View.GONE);
            }
        }
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

    @OnClick({R.id.ll_back, R.id.tv_name_voice_room, R.id.tv_id_voice_room, R.id.tv_users_voice_room, R.id.btn_more_voice_room, R.id.iv_decorate_voice_room, R.id.iv_logo_voice_room, R.id.iv_animation_voice_room, R.id.rl_holder_voice_room, R.id.tv_holder_name_voice_room, R.id.iv_edit_announce_voice_room})
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
                bundle = setArguments();
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
            case R.id.iv_edit_announce_voice_room:
                break;
        }
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

    /*   2 房主*/
    private void showVoiceUser2VoiceHolderDialog(VoiceRole user, VoiceRole holder, Bundle bundle) {
        if (user == null || holder == null || bundle == null)
            return;

        if (holder.isVoiceRoleUsingPosition()) {
            /*非空麦位*/

            if (holder.isCurrentVoiceUser(user.id)) {
                //房主主页
            } else {
                VoiceRoleInfoDialogFragment voiceRoleInfoDialogFragment = VoiceRoleInfoDialogFragment.getInstance(bundle);
                voiceRoleInfoDialogFragment.show();
            }
        } else {
            /*空麦位*/
            //房主自动上麦
        }
    }

    /*房主*/
    private void showVoiceHolder2VoiceRoleDialog(VoiceRole user, VoiceRole data, Bundle bundle) {
        if (user == null || data == null || bundle == null)
            return;

        VoiceRoleOperationDialogFragment fragment;
        if (data.isVoiceRoleUsingPosition()) {
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
        if (data.isVoiceRoleUsingPosition()) {
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
        if (data.isVoiceRoleUsingPosition()) {
            /*非空麦位*/

            if (data.isCurrentVoiceUser(user.id)) {
                //本人（麦上普通用户）
                fragment = VoiceClient2UserOperationDialogFragment.getInstance(bundle);
                fragment.show();
            } else {
                //非本人（其他麦位用户）
                VoiceGuest2ClientOperationDialogFragment voiceGuest2ClientOperationDialogFragment = VoiceGuest2ClientOperationDialogFragment.getInstance(bundle);
                voiceGuest2ClientOperationDialogFragment.show();
            }
        } else {
            /*空麦位*/

            //麦上用户 换麦

            //麦下用户 上麦
            VoiceGuest2EmptyOperationDialogFragment voiceGuest2EmptyOperationDialogFragment = VoiceGuest2EmptyOperationDialogFragment.getInstance(bundle);
            voiceGuest2EmptyOperationDialogFragment.show();
        }
    }

    public void onItemSelect2(VoiceRole data, int position, int totalCount) {
        L.i(initTag(), " onItemSelect " + data);
        Bundle bundle = new Bundle();
        bundle.putSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROOM, voiceRoom);
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

    /*进入房间*/
    @Subscribe
    public void onEvent(VoiceRoomEvent.OnEntry event) {
        if (event == null)
            return;

        //展示座驾
    }

    /*踢出房间*/
    @Subscribe
    public void onEvent(VoiceRoomEvent.OnLeave event) {
        if (event == null)
            return;

        AgoraHelper.getInstance().leaveChannel();
        activityFinish();
    }

    /*发送礼物*/
    @Subscribe
    public void onEvent(VoiceRoomEvent.OnProductReceived event) {
        if (event == null)
            return;

        //展示礼物

    }


    /********************刷新当前用户********************/
    /*添加管理员\移除管理员*/


    /********************刷新麦位********************/
    /*上麦\下麦*/
    /*解除封麦\封麦*/
    /*抱TA上麦\抱TA下麦*/
    /*关闭麦克风\打开麦克风*/
    /*打开\关闭公屏*/
    @Subscribe
    public void onEvent(VoiceRoomEvent event) {
        if (event == null)
            return;

        switch (event.state) {
            case FRESH:
                requstData();
                break;
        }
    }

    /*房主表情展示*/
    @Subscribe
    public void onEvent(RoomRoleOperationEvent.OnEmojiEvent event) {
        if (event == null || event.data != null)
            return;

        if (user.isVoiceHolder()) {
            //TODO
        }
    }

}