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
import com.zl.dappore.voiceroom.fragment.chatroom.ChatRoomFragment;
import com.zl.dappore.voiceroom.fragment.voiceorole.VoiceAuditorOperationDialogFragment;
import com.zl.dappore.voiceroom.fragment.voiceorole.VoiceClientOperationDialogFragment;
import com.zl.dappore.voiceroom.fragment.voiceorole.VoiceHolderOperationDialogFragment;
import com.zl.dappore.voiceroom.fragment.voiceorole.VoiceRoleOperationDialogFragment;
import com.zl.dappore.voiceroom.listener.OnVoiceClientListener;
import com.zl.dappore.voiceroom.model.VoiceRole;
import com.zl.dappore.voiceroom.model.VoiceRoom;
import com.zl.dappore.voiceroom.model.VoiceRoomConstants;
import com.zl.dappore.voiceroom.presenter.VoiceRoomPresenter;

import org.greenrobot.eventbus.Subscribe;

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
    @Bind(R.id.tv_greeting_voice_room)
    TextView tv_greeting_voice_room;
    @Bind(R.id.chatroomframe)
    RelativeLayout chatroomframe;
    @Bind(R.id.chatroomlayout)
    LinearLayout chatroomlayout;

    VoiceRoom room;
    VoiceRole user;
    String id = "";
    String channelId = "";
    int voiceRole = 0;
    VoiceClientGridFragment voiceClientGridFragment;

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

        id = arguments.getString(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_ID);
        channelId = arguments.getString(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_CHANNEL_ID);
        voiceRole = arguments.getInt(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_VOICE_ROLE);


        user = new VoiceRole();
        user.id = id;
        user.name = "user" + id;
        user.logo = "http://staging.dappore.com/xNdqnHirMbzFYW9BXkmKPZ3n";
        user.voiceRole = voiceRole;

        AgoraHelper.getInstance().joinChannel("room1", 11);

        voiceClientGridFragment = (VoiceClientGridFragment) getChildFragmentManager().findFragmentById(R.id.f_voice_room);
        voiceClientGridFragment.setArguments(arguments);

        L.i(initTag(), " id " + id + " channelId " + channelId + " voiceRole " + voiceRole);
        getPresenter().requstData(id);
//        initChatRoom();

        loadingClose();
        showContentView();



    }

    @ThreadPoint(ThreadType.MAIN)
    public void initChatRoom() {
        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.chatroomlayout, ChatRoomFragment.getInstance(), ChatRoomFragment.class.getSimpleName());
    }

    public void requstData(int voiceRole) {
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

        this.room = data;

        tvNameVoiceRoom.setText(data.name);
        tvIdVoiceRoom.setText("ID:" + data.id);
        tvUsersVoiceRoom.setText("0人在线");
        tv_greeting_voice_room.setText(data.greeting);
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
                break;
            case R.id.btn_more_voice_room:
                bundle = new Bundle();
                bundle.putSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE_USER, user);
                bundle.putSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROOM, room);
                RoomOperationDialogFragment.getInstance(bundle).show();
                break;
            case R.id.iv_decorate_voice_room:
                break;
            case R.id.iv_logo_voice_room:
                break;
            case R.id.iv_animation_voice_room:
                break;
            case R.id.rl_holder_voice_room:
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
        VoiceRoleOperationDialogFragment fragment;
        switch (user.voiceRole) {
            case VoiceRole.VOICE_HOLDER:
//                fragment = VoiceRoleOperationDialogFragment.getInstance(bundle);
//                fragment.show();
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